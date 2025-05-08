package com.ricky.terrariamod.entity.boss;

import com.ricky.terrariamod.entity.ModEntities;
import com.ricky.terrariamod.entity.monster.flying_type.FlyMoveControl;
import com.ricky.terrariamod.entity.monster.flying_type.FlyRandomlyGoal;
import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.Objects;

public class EyeOfCthulhuEntity extends FlyingMob {
    private int phase = 1;
    private int summonCooldown = 100;
    private final ServerBossEvent bossBar = new ServerBossEvent(
            Component.literal("Eye Of Cthulhu"),
            BossEvent.BossBarColor.RED,
            BossEvent.BossBarOverlay.PROGRESS
    );

    public EyeOfCthulhuEntity(EntityType<? extends FlyingMob> type, Level world) {
        super(type, world);
        this.moveControl = new FlyMoveControl(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ARMOR, 8.0D)
                .add(Attributes.ATTACK_DAMAGE, 7.0D);
    }

    private void summonDemonEyes() {
        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        RandomSource random = this.getRandom();

        for (int i = 0; i < 3; i++) {
            int offsetX = (int)((random.nextDouble() - 0.5) * 10.0);
            int offsetY = (int)((random.nextDouble() - 0.5) * 10.0);
            int offsetZ = (int)((random.nextDouble() - 0.5) * 10.0);

            BlockPos spawnPos = this.blockPosition().offset(offsetX, offsetY, offsetZ);

            Mob demonEye = ModEntities.DEMON_EYE.get().create(serverLevel);
            if (demonEye != null) {
                demonEye.moveTo(spawnPos, 0.0F, 0.0F);
                serverLevel.addFreshEntity(demonEye);
            }
        }
    }

    public int getCurrentPhase() {
        return phase;
    }

    private void switchToPhaseTwo() {
        if (phase == 2) return;
        phase = 2;

        Objects.requireNonNull(this.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.3D);
        Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(11.0D);

        this.bossBar.setColor(BossEvent.BossBarColor.YELLOW);

        this.level().broadcastEntityEvent(this, (byte) 63);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new FlyRandomlyGoal(this));
        this.targetSelector.addGoal(1, new TrackPlayerGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        float healthProgress = this.getHealth() / this.getMaxHealth();
        bossBar.setProgress(healthProgress);

        if (this.getHealth() <= this.getMaxHealth() / 2 && phase == 1) {
            switchToPhaseTwo();
        }

        if (phase == 1 && !this.level().isClientSide) {
            if (summonCooldown > 0) {
                summonCooldown--;
            } else {
                summonDemonEyes();
                summonCooldown = 200;
            }
        }
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        boolean damaged = super.hurt(source, amount);
        if (damaged) {
            float healthProgress = this.getHealth() / this.getMaxHealth();
            bossBar.setProgress(healthProgress);
        }
        return damaged;
    }

    @Override
    public void die(@NotNull DamageSource source) {
        super.die(source);
        bossBar.removeAllPlayers();
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        bossBar.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        bossBar.removePlayer(player);
    }

    public static void summonBoss(ServerLevel world, BlockPos pos) {
        EyeOfCthulhuEntity boss = ModEntities.EYE_OF_CTHULHU.get().create(world);
        if (boss != null) {
            boss.moveTo(pos, 0.0F, 0.0F);
            world.addFreshEntity(boss);
        }
    }

    static class TrackPlayerGoal extends Goal {
        private final EyeOfCthulhuEntity eye;
        private Player target;

        public TrackPlayerGoal(EyeOfCthulhuEntity eye) {
            this.eye = eye;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            this.target = this.eye.level().getNearestPlayer(this.eye, 40.0D);
            return this.target != null && !this.target.isCreative() && !this.target.isSpectator();
        }

        @Override
        public void start() {
            this.eye.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.5D);
        }

        @Override
        public void tick() {
            if (this.target != null) {
                this.eye.lookAt(Anchor.FEET, this.target.position());
                double distance = this.eye.distanceToSqr(this.target);
                if (distance < 9.0D) {
                    this.eye.doHurtTarget(this.target);
                } else {
                    this.eye.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.5D);
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            return this.target != null && this.target.isAlive()
                    && !this.target.isCreative() && !this.target.isSpectator()
                    && this.eye.distanceToSqr(this.target) < 225.0D;
        }
    }
}
