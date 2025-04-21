package com.ricky.terrariamod.entity.monster.bat_type.jungle_bat;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class JungleBatEntity extends Bat {
    public JungleBatEntity(EntityType<? extends Bat> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.3f)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.ATTACK_DAMAGE, 5);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new LavaBatAttackGoal(this, 0.45));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean success = super.doHurtTarget(target);
        if (success && target instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 40), this);
        }
        return success;
    }

    static class LavaBatAttackGoal extends Goal {
        private final JungleBatEntity lavaBat;
        private LivingEntity target;
        private final double speed;
        private final double attackRange = 1.8D;
        private int attackCooldown = 0;

        public LavaBatAttackGoal(JungleBatEntity lavaBat, double speed) {
            this.lavaBat = lavaBat;
            this.speed = speed;
        }

        @Override
        public boolean canUse() {
            this.target = this.lavaBat.getTarget();
            return this.target != null && this.target.isAlive();
        }

        @Override
        public void stop() {
            this.target = null;
            this.lavaBat.setDeltaMovement(Vec3.ZERO);
            this.attackCooldown = 0;
        }

        @Override
        public void tick() {
            if (this.target != null) {
                Vec3 targetEyePos = new Vec3(target.getX(), target.getEyeY(), target.getZ());
                Vec3 direction = targetEyePos.subtract(lavaBat.position()).normalize().scale(speed);

                lavaBat.setDeltaMovement(direction);
                lavaBat.getLookControl().setLookAt(target, 30.0F, 30.0F);

                if (attackCooldown <= 0 && lavaBat.distanceToSqr(target) < attackRange * attackRange) {
                    lavaBat.doHurtTarget(target);
                    attackCooldown = 10;
                }

                if (attackCooldown > 0) {
                    attackCooldown--;
                }
            }
        }
    }
}
