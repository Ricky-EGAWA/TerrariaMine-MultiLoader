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
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D) // ハート6個分
                .add(Attributes.FOLLOW_RANGE, 16.0D);
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
        private int chargeTime;

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
        public void start() {
            this.chargeTime = 0;
        }

        @Override
        public void tick() {
            LivingEntity target = this.skull.getTarget();
            if (target == null) {
                return;
            }

            double distance = this.skull.distanceToSqr(target);

            // プレイヤーに5ブロック（25平方ブロック）まで近づいたら突進
            if (distance < 25.0D && distance > 4.0D) {
                // ターゲットに向かって移動
                Vec3 targetPos = target.position();
                Vec3 skullPos = this.skull.position();
                Vec3 direction = targetPos.subtract(skullPos).normalize();

                this.skull.setDeltaMovement(direction.scale(0.4D));
                this.skull.lookAt(target, 30.0F, 30.0F);
            } else if (distance <= 4.0D) {
                // 突進攻撃
                this.chargeTime++;
                if (this.chargeTime >= 10) {
                    // 突進
                    Vec3 targetPos = target.position();
                    Vec3 skullPos = this.skull.position();
                    Vec3 chargeDirection = targetPos.subtract(skullPos).normalize();

                    this.skull.setDeltaMovement(chargeDirection.scale(0.8D));

                    // 接触したらダメージ
                    if (this.skull.distanceToSqr(target) < 2.0D) {
                        this.skull.doHurtTarget(target);
                        this.chargeTime = 0;
                    }
                }
            } else {
                // 遠すぎるので通常移動
                this.chargeTime = 0;
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}
