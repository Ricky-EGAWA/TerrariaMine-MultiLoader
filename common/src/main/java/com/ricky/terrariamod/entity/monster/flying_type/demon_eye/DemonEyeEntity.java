package com.ricky.terrariamod.entity.monster.flying_type.demon_eye;

import com.ricky.terrariamod.entity.monster.flying_type.FlyMoveControl;
import com.ricky.terrariamod.entity.monster.flying_type.FlyRandomlyGoal;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class DemonEyeEntity extends FlyingMob {
    public DemonEyeEntity(EntityType<? extends FlyingMob> type, Level level) {
        super(type, level);
        this.moveControl = new FlyMoveControl(this);
    }

    // 属性の定義
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.ARMOR, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 7);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new FlyRandomlyGoal(this));
        this.targetSelector.addGoal(1, new TrackPlayerGoal(this));
    }

    // ===== プレイヤー追尾ゴール =====
    private static class TrackPlayerGoal extends Goal {
        private final DemonEyeEntity demon;
        private Player target;

        public TrackPlayerGoal(DemonEyeEntity demon) {
            this.demon = demon;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            this.target = this.demon.level().getNearestPlayer(this.demon, 15.0);
            return this.target != null && !this.target.isCreative() && !this.target.isSpectator();
        }

        @Override
        public void start() {
            this.demon.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.5);
        }

        @Override
        public void tick() {
            if (this.target != null) {
                this.demon.getLookControl().setLookAt(target, 30.0F, 30.0F);
                double distance = this.demon.distanceToSqr(target);
                if (distance < 4.0) {
                    this.demon.doHurtTarget(target);
                } else {
                    this.demon.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.5);
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            return this.target != null && this.target.isAlive()
                    && !this.target.isCreative() && !this.target.isSpectator()
                    && this.demon.distanceToSqr(target) < 225.0;
        }
    }
}

