package com.ricky.terrariamod.entity.monster.skeleton_type.diabolist;

import com.ricky.terrariamod.entity.ModEntities;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

/**
 * Diabolist の Inferno Bolt
 * Terraria原作の特性を再現:
 *   - 発射後に加速する（毎TickでDeltaMovementをスケール）
 *   - 橙色の炎粒子を放出
 *   - 壁貫通
 */
public class DiabolistInfernoBallEntity extends ThrowableProjectile {
    private int lifeTime = 120; // 6秒
    private float damage  = 10.0F;
    /** 加速率: 毎Tickで速度をこの倍率で増やす（最大速度に達するまで） */
    private static final double ACCELERATION = 1.04;
    private static final double MAX_SPEED    = 1.6;

    public DiabolistInfernoBallEntity(EntityType<? extends ThrowableProjectile> type, Level level) {
        super(type, level);
        this.noPhysics = true;
        this.setNoGravity(true);
    }

    public DiabolistInfernoBallEntity(Level level, LivingEntity owner, float damage) {
        super(ModEntities.DIABOLIST_INFERNO_BALL.get(), owner, level);
        this.damage   = damage;
        this.noPhysics = true;
        this.setNoGravity(true);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    public void tick() {
        super.tick();

        if (--this.lifeTime <= 0) {
            this.discard();
            return;
        }

        // 発射後に加速（Terraria原作の特性）
        Vec3 movement = this.getDeltaMovement();
        double currentSpeed = movement.length();
        if (currentSpeed < MAX_SPEED) {
            this.setDeltaMovement(movement.scale(ACCELERATION));
        }

        // 移動
        Vec3 newPos = this.position().add(this.getDeltaMovement());
        this.setPos(newPos.x, newPos.y, newPos.z);

        // エンティティとの当たり判定
        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult) hitResult);
        }

        // 橙色の炎粒子
        if (this.level().isClientSide) {
            spawnParticles();
        }
    }

    private void spawnParticles() {
        Vec3 pos = this.position();
        for (int i = 0; i < 4; i++) {
            double ox = this.random.nextGaussian() * 0.06;
            double oy = this.random.nextGaussian() * 0.06;
            double oz = this.random.nextGaussian() * 0.06;
            // 橙色（R=1.0, G=0.45, B=0.0）
            this.level().addParticle(
                    new DustParticleOptions(new Vector3f(1.0f, 0.45f, 0.0f), 1.2F),
                    pos.x + ox, pos.y + oy, pos.z + oz,
                    0, 0, 0);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        Entity owner  = this.getOwner();
        if (entity == owner) return;

        DamageSource src = this.damageSources().mobProjectile(
                this, owner instanceof LivingEntity le ? le : null);
        if (entity.hurt(src, this.damage)) {
            this.discard();
        }
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return super.canHitEntity(entity) && entity != this.getOwner();
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        return true;
    }
}
