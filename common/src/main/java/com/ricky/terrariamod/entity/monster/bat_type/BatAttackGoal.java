package com.ricky.terrariamod.entity.monster.bat_type;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.phys.Vec3;

public class BatAttackGoal extends Goal {
    private final Bat lavaBat;
    private LivingEntity target;
    private final double speed;
    private final double attackRange = 1.8D;
    private int attackCooldown = 0;

    public BatAttackGoal(Bat lavaBat, double speed) {
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
