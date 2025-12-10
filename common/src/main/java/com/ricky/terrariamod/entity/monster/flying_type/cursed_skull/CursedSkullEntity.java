package com.ricky.terrariamod.entity.monster.flying_type.cursed_skull;

import com.ricky.terrariamod.entity.monster.flying_type.FlyMoveControl;
import com.ricky.terrariamod.entity.monster.flying_type.FlyRandomlyGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class CursedSkullEntity extends FlyingMob {
    public CursedSkullEntity(EntityType<? extends FlyingMob> type, Level level) {
        super(type, level);
        this.moveControl = new FlyMoveControl(this);
        this.noPhysics = true; // 壁をすり抜ける
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D) // ハート10個分
                .add(Attributes.MOVEMENT_SPEED, 0.15D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D) // ハート6個分
                .add(Attributes.FOLLOW_RANGE, 25.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ChargeAttackGoal(this));
        this.goalSelector.addGoal(5, new FlyRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        this.noPhysics = true; // 常に壁をすり抜ける
    }

    @Override
    public void die(DamageSource damageSource) {
        // 骨を1~3個ドロップ
        if (!this.level().isClientSide) {
            int boneCount = 1 + this.random.nextInt(3);
            this.spawnAtLocation(Items.BONE, boneCount);
        }
        super.die(damageSource);
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public void checkDespawn() {
        // デスポーンしない
    }

    // 突進攻撃AIゴール
    static class ChargeAttackGoal extends Goal {
        private final CursedSkullEntity skull;

        public ChargeAttackGoal(CursedSkullEntity skull) {
            this.skull = skull;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (this.skull.getTarget() == null) {
                return false;
            } else {
                return this.skull.getTarget().isAlive();
            }
        }

        @Override
        public boolean canContinueToUse() {
            return this.canUse();
        }

        @Override
        public void tick() {
            LivingEntity target = this.skull.getTarget();
            if (target == null) {
                return;
            }

            // ターゲットの位置を取得（頭の高さに合わせる）
            Vec3 targetPos = new Vec3(target.getX(), target.getY() + target.getEyeHeight() * 0.5, target.getZ());
            Vec3 skullPos = this.skull.position();

            double distance = this.skull.distanceToSqr(target);

            // ターゲットに向かう方向ベクトル
            Vec3 direction = targetPos.subtract(skullPos).normalize();

            // 距離に応じて速度を変える
            double speed;
            if (distance < 9.0D) {
                // 3ブロック以内: 高速突進
                speed = 0.2D;
            } else if (distance < 25.0D) {
                // 5ブロック以内: 中速移動
                speed = 0.3D;
            } else {
                // それ以上: 低速接近
                speed = 0.6D;
            }

            // 移動を適用
            this.skull.setDeltaMovement(direction.scale(speed));

            // ターゲットを見る
            this.skull.lookAt(target, 30.0F, 30.0F);

            // 接触判定でダメージ
            if (distance < 2.5D && this.skull.hasLineOfSight(target)) {
                this.skull.doHurtTarget(target);
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}
