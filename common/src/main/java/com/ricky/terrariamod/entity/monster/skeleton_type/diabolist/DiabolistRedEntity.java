package com.ricky.terrariamod.entity.monster.skeleton_type.diabolist;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/**
 * Diabolist (Red) — 赤い悪魔魔法師（困難モード地下城）
 * Terraria原作：テレポートして炎のInferno Boltを発射する
 *   弾は発射後に加速する特性を持つ
 * HP: 400 / ダメージ: 80 / 防御: 20
 * 赤・白2変種あり、ステータスは同じ
 */
public class DiabolistRedEntity extends Skeleton {
    private boolean hasInitializedEquipment = false;

    public DiabolistRedEntity(EntityType<? extends Skeleton> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.01D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.ARMOR, 4.0D)          // 防御20 → MC換算約4
                .add(Attributes.MAX_HEALTH, 40.0D)    // HP400 → MC換算約40
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TeleportAndShootGoal(this));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && !hasInitializedEquipment) {
            setupEquipment();
            hasInitializedEquipment = true;
        }
    }

    private void setupEquipment() {
        // 赤いローブ
        int redRobeColor = 0xAA1A1A;

        ItemStack chestplate = new ItemStack(Items.LEATHER_CHESTPLATE);
        CompoundTag chestTag = chestplate.getOrCreateTag();
        CompoundTag chestDisplay = chestTag.getCompound("display");
        chestDisplay.putInt("color", redRobeColor);
        chestTag.put("display", chestDisplay);
        this.setItemSlot(EquipmentSlot.CHEST, chestplate);
        this.setDropChance(EquipmentSlot.CHEST, 0.0F);

        ItemStack leggings = new ItemStack(Items.LEATHER_LEGGINGS);
        CompoundTag legsTag = leggings.getOrCreateTag();
        CompoundTag legsDisplay = legsTag.getCompound("display");
        legsDisplay.putInt("color", redRobeColor);
        legsTag.put("display", legsDisplay);
        this.setItemSlot(EquipmentSlot.LEGS, leggings);
        this.setDropChance(EquipmentSlot.LEGS, 0.0F);

        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    @Override
    protected boolean isSunBurnTick() {
        return false;
    }

    @Override
    public void die(DamageSource damageSource) {
        if (!this.level().isClientSide) {
            this.spawnAtLocation(Items.BONE, 1 + this.random.nextInt(2));
        }
        super.die(damageSource);
    }

    /**
     * Inferno Bolt を発射する
     * Terraria原作: 発射後に加速する炎の弾、DiabolistInfernoBallEntityで実装
     */
    public void shootInfernoBolt(LivingEntity target) {
        if (this.level().isClientSide) return;

        Vec3 casterPos = new Vec3(this.getX(), this.getEyeY() - 0.1, this.getZ());
        Vec3 targetPos  = new Vec3(target.getX(), target.getEyeY(), target.getZ());
        Vec3 direction  = targetPos.subtract(casterPos).normalize();

        DiabolistInfernoBallEntity bolt = new DiabolistInfernoBallEntity(
                this.level(), this, 10.0F);
        bolt.setPos(casterPos.x, casterPos.y, casterPos.z);
        bolt.setDeltaMovement(direction.scale(0.7)); // 初速は遅め（後で加速）

        this.level().addFreshEntity(bolt);
        this.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 1.0F);
    }

    public boolean teleportTowards(LivingEntity target) {
        if (this.level().isClientSide) return false;

        for (int i = 0; i < 10; i++) {
            double distance = 5.0 + this.random.nextDouble() * 5.0;
            double angle    = this.random.nextDouble() * 2 * Math.PI;
            double x = target.getX() + Math.cos(angle) * distance;
            double y = target.getY();
            double z = target.getZ() + Math.sin(angle) * distance;
            BlockPos targetPos = new BlockPos((int) x, (int) y, (int) z);

            for (int yOffset = -5; yOffset <= 5; yOffset++) {
                BlockPos checkPos = targetPos.offset(0, yOffset, 0);
                if (this.level().getBlockState(checkPos.below()).isSolid()
                        && !this.level().getBlockState(checkPos).isSolid()
                        && !this.level().getBlockState(checkPos.above()).isSolid()) {
                    this.teleportTo(checkPos.getX() + 0.5, checkPos.getY(), checkPos.getZ() + 0.5);
                    this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                    return true;
                }
            }
        }
        return false;
    }

    /** テレポート → Inferno Bolt 3連射 → クールダウン → 繰り返し */
    static class TeleportAndShootGoal extends Goal {
        private final DiabolistRedEntity diabolist;
        private LivingEntity target;
        private int actionTimer;
        private int shotsFired;

        private static final int SHOTS_PER_BURST     = 3;
        private static final int TICKS_BETWEEN_SHOTS = 20; // 1秒間隔（炎弾は遅い代わりに加速する）
        private static final int COOLDOWN_AFTER_BURST = 50; // 2.5秒

        public TeleportAndShootGoal(DiabolistRedEntity diabolist) {
            this.diabolist = diabolist;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity t = this.diabolist.getTarget();
            if (t == null || !t.isAlive()) return false;
            this.target = t;
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            return this.target != null && this.target.isAlive();
        }

        @Override
        public void start() {
            this.actionTimer = 0;
            this.shotsFired  = 0;
            this.diabolist.teleportTowards(this.target);
        }

        @Override
        public void tick() {
            if (this.target == null) return;
            this.diabolist.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
            this.actionTimer++;

            if (this.shotsFired < SHOTS_PER_BURST) {
                if (this.actionTimer >= TICKS_BETWEEN_SHOTS) {
                    this.diabolist.shootInfernoBolt(this.target);
                    this.shotsFired++;
                    this.actionTimer = 0;
                }
            } else if (this.actionTimer >= COOLDOWN_AFTER_BURST) {
                this.diabolist.teleportTowards(this.target);
                this.shotsFired  = 0;
                this.actionTimer = 0;
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() { return true; }
    }
}
