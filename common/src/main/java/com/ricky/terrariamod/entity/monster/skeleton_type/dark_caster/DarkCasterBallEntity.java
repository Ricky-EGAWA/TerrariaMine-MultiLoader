package com.ricky.terrariamod.entity.monster.skeleton_type.dark_caster;

import com.ricky.terrariamod.entity.ModEntities;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class DarkCasterBallEntity extends Projectile {
    private int lifeTime = 100; // 5秒（100 ticks）
    private float damage = 10.0F;

    public DarkCasterBallEntity(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
        this.noPhysics = true; // 壁貫通
        this.setNoGravity(true);
    }

    public DarkCasterBallEntity(Level level, LivingEntity owner, float damage) {
        super(ModEntities.DARK_CASTER_BALL.get(), level);
        this.setOwner(owner);
        this.damage = damage;
        this.noPhysics = true;
        this.setNoGravity(true);
    }

    @Override
    protected void defineSynchedData() {
        // 必要なEntityDataを定義（今回は特になし）
    }

    @Override
    public void tick() {
        super.tick();

        // 寿命チェック
        if (--this.lifeTime <= 0) {
            this.discard();
            return;
        }

        // 移動
        Vec3 movement = this.getDeltaMovement();
        Vec3 newPos = this.position().add(movement);
        this.setPos(newPos.x, newPos.y, newPos.z);

        // エンティティとの当たり判定（noPhysics = trueでもエンティティには当たる）
        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult) hitResult);
        }

        // パーティクル
        if (this.level().isClientSide) {
            spawnParticles();
        }
    }

    private void spawnParticles() {
        Vec3 pos = this.position();
        for (int i = 0; i < 3; i++) {
            double offsetX = this.random.nextGaussian() * 0.05;
            double offsetY = this.random.nextGaussian() * 0.05;
            double offsetZ = this.random.nextGaussian() * 0.05;

            this.level().addParticle(
                    new DustParticleOptions(new Vector3f(0.0f, 0.5f, 1.0f), 1.0F),
                    pos.x + offsetX,
                    pos.y + offsetY,
                    pos.z + offsetZ,
                    0, 0, 0
            );
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        Entity owner = this.getOwner();

        // 自分自身と発射者には当たらない
        if (entity == owner) {
            return;
        }

        // ダメージを与える
        DamageSource damageSource = this.damageSources().mobProjectile(this, owner instanceof LivingEntity ? (LivingEntity) owner : null);
        if (entity.hurt(damageSource, this.damage)) {
            // ヒットしたら消える
            this.discard();
        }
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        // 発射者とその仲間には当たらない
        return super.canHitEntity(entity) && entity != this.getOwner();
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        return true;
    }
}
