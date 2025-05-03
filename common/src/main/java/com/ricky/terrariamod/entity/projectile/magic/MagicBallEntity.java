package com.ricky.terrariamod.entity.projectile.magic;

import com.ricky.terrariamod.entity.ModEntities;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;

import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class MagicBallEntity extends AbstractArrow {
    private ItemStack staffStack;
    private boolean dealtDamage;

    private int lifeTime = 200;
    private int hitCount = 0;
    private int reflectionCount = 0;

    private static final EntityDataAccessor<Integer> MAX_HIT = SynchedEntityData.defineId(MagicBallEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MAX_REFLECT = SynchedEntityData.defineId(MagicBallEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DAMAGE = SynchedEntityData.defineId(MagicBallEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> COLOR_R = SynchedEntityData.defineId(MagicBallEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> COLOR_G = SynchedEntityData.defineId(MagicBallEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> COLOR_B = SynchedEntityData.defineId(MagicBallEntity.class, EntityDataSerializers.FLOAT);

    public MagicBallEntity(EntityType<? extends MagicBallEntity> type, Level level) {
        super(type, level);
        this.staffStack = ItemStack.EMPTY;
        this.setNoGravity(true);
    }

    public MagicBallEntity(Level level, LivingEntity owner, ItemStack stack, int hitCount, int reflectionCount, int damage, float r, float g, float b) {
        super(ModEntities.MAGIC_BALL.get(), owner, level);
        this.staffStack = stack.copy();
        this.setCustomValues(hitCount, reflectionCount, damage, r, g, b);
        this.setNoGravity(true);
    }

    private void setCustomValues(int hitCount, int reflectionCount, int damage, float r, float g, float b) {
        this.entityData.set(MAX_HIT, hitCount);
        this.entityData.set(MAX_REFLECT, reflectionCount);
        this.entityData.set(DAMAGE, damage);
        this.entityData.set(COLOR_R, r);
        this.entityData.set(COLOR_G, g);
        this.entityData.set(COLOR_B, b);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MAX_HIT, 0);
        this.entityData.define(MAX_REFLECT, 0);
        this.entityData.define(DAMAGE, 0);
        this.entityData.define(COLOR_R, 0.0f);
        this.entityData.define(COLOR_G, 0.0f);
        this.entityData.define(COLOR_B, 0.0f);
    }

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);

        if (this.lifeTime-- <= 0) {
            this.discard();
        }

        if (this.level().isClientSide) {
            spawnTailParticles();
            spawnCircleParticles();
        }

        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }
    }

    private void spawnTailParticles() {
        if (!level().isClientSide) return;

        Vec3 pos = this.position();
        float r = this.entityData.get(COLOR_R);
        float g = this.entityData.get(COLOR_G);
        float b = this.entityData.get(COLOR_B);

        for (int i = 0; i < 5; i++) {
            double offsetX = random.nextGaussian() * 0.05;
            double offsetY = random.nextGaussian() * 0.05;
            double offsetZ = random.nextGaussian() * 0.05;

            level().addParticle(
                    new DustParticleOptions(new Vector3f(r, g, b), 1.0F),
                    pos.x + offsetX,
                    pos.y + offsetY,
                    pos.z + offsetZ,
                    0, 0, 0
            );
        }
    }

    private void spawnCircleParticles() {
        if (!level().isClientSide) return;

        Vec3 pos = this.position();
        float r = this.entityData.get(COLOR_R);
        float g = this.entityData.get(COLOR_G);
        float b = this.entityData.get(COLOR_B);
        double radius = 0.2;

        for (int i = 0; i < 10; i++) {
            double angle = this.random.nextDouble() * 2 * Math.PI;
            double offsetX = Math.cos(angle) * radius;
            double offsetZ = Math.sin(angle) * radius;
            double offsetY = random.nextGaussian() * 0.02;

            level().addParticle(
                new DustParticleOptions(new Vector3f(r, g, b), 1.0F),
                pos.x + offsetX,
                pos.y + offsetY,
                pos.z + offsetZ,
                0, 0, 0
            );
        }
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return this.staffStack.copy();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        DamageSource source = this.damageSources().trident(this, this.getOwner());
        this.dealtDamage = true;

        if (entity.hurt(source, this.entityData.get(DAMAGE))) {
            if (entity instanceof LivingEntity) {
                this.onHit();
            }
        }
    }

    protected void onHit() {
        if (hitCount >= this.entityData.get(MAX_HIT)) {
            this.discard();
            return;
        }
        hitCount++;
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult hitResult) {
        if (reflectionCount >= this.entityData.get(MAX_REFLECT)) {
            this.discard();
            return;
        }

        Vec3 velocity = this.getDeltaMovement();
        Vec3 normal = Vec3.atLowerCornerOf(hitResult.getDirection().getNormal()).normalize();
        Vec3 reflected = velocity.subtract(normal.scale(2 * velocity.dot(normal))).scale(0.9);

        this.setDeltaMovement(reflected);
        Vec3 hitPos = hitResult.getLocation();
        this.setPos(hitPos.x + reflected.x * 0.01,
                    hitPos.y + reflected.y * 0.01,
                    hitPos.z + reflected.z * 0.01);

        this.inGroundTime = 0;
        reflectionCount++;
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Staff")) {
            this.staffStack = ItemStack.of(tag.getCompound("Staff"));
        }
        this.dealtDamage = tag.getBoolean("DealtDamage");
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("Staff", this.staffStack.save(new CompoundTag()));
        tag.putBoolean("DealtDamage", this.dealtDamage);
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        return true;
    }
}
