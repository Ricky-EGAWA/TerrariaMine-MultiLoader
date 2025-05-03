package com.ricky.terrariamod.entity.projectile.ammo.rocket;

import com.ricky.terrariamod.entity.ModEntities;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class RocketEntity extends AbstractArrow {
    private int explosionPower = 3;
    private int lifeTime = 0;

    public RocketEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public RocketEntity(Level level, LivingEntity owner, int explosionPower) {
        super(ModEntities.ROCKET.get(), owner, level);
        this.explosionPower = explosionPower;
    }

    @Override
    public void tick() {
        super.tick();
        lifeTime++;
    }

    @Override
    protected void onHit(@NotNull HitResult hitResult) {
        if (lifeTime < 5 || hitResult.getType() == HitResult.Type.MISS) {
            return;
        }

        super.onHit(hitResult);

        if (!this.level().isClientSide) {
            boolean mobGriefing = this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);

            this.level().explode(
                this,
                this.getX(),
                this.getY(),
                this.getZ(),
                this.explosionPower,
                mobGriefing,
                Level.ExplosionInteraction.MOB
            );

            this.discard();
        }
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }
}
