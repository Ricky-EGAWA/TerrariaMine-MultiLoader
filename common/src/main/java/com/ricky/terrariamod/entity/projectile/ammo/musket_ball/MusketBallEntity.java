package com.ricky.terrariamod.entity.projectile.ammo.musket_ball;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import com.ricky.terrariamod.entity.ModEntities;
import org.jetbrains.annotations.NotNull;

public class MusketBallEntity extends AbstractArrow {

    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(MusketBallEntity.class, EntityDataSerializers.INT);
    private float damageValue = 0;

    public MusketBallEntity(EntityType<? extends MusketBallEntity> entityType, Level level) {
        super(entityType, level);
    }

    public MusketBallEntity(Level level, LivingEntity owner) {
        super(ModEntities.MUSKET_BALL.get(), owner, level);
    }

    public MusketBallEntity(Level level, LivingEntity owner, float damage) {
        super(ModEntities.MUSKET_BALL.get(), owner, level);
        this.damageValue = damage;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(COLOR, -1);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        // 必要ならカラーやダメージも保存
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
    }

    @Override
    protected void onHitEntity(net.minecraft.world.phys.@NotNull EntityHitResult result) {
        super.onHitEntity(result);

        if (result.getEntity() instanceof LivingEntity target) {
            DamageSource source = this.damageSources().arrow(this, this.getOwner());
            float dmg = this.damageValue != 0 ? this.damageValue : 2.5F;
            target.hurt(source, dmg);
            target.invulnerableTime = 0;
        }
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public void handleEntityEvent(byte id) {
        super.handleEntityEvent(id);
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        this.discard();
    }
}
