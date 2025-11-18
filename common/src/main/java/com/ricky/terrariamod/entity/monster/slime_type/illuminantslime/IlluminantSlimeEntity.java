package com.ricky.terrariamod.entity.monster.slime_type.illuminantslime;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class IlluminantSlimeEntity extends Slime {
    public IlluminantSlimeEntity(EntityType<? extends Slime> entityType, Level world) {
        super(entityType, world);
        this.setSize(2, true);
        this.refreshDimensions();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 32)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.FOLLOW_RANGE, 10.0)
                .add(Attributes.ARMOR, 2f)
                .add(Attributes.ATTACK_DAMAGE, 10);
    }
    @Override
    public void remove(@NotNull RemovalReason reason) {
        this.setSize(1, true);
        super.remove(reason);
    }
    @Override
    public void setSize(int size, boolean resetHealth) {
        super.setSize(2, resetHealth);
        this.refreshDimensions();
        Objects.requireNonNull(this.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(24);
        this.setHealth(24);
        Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(6.0);
        Objects.requireNonNull(this.getAttribute(Attributes.FOLLOW_RANGE)).setBaseValue(40);
    }
    @Override
    protected void dealDamage(@NotNull LivingEntity target) {
        super.dealDamage(target);
    }
}
