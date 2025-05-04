package com.ricky.terrariamod.entity.monster.slime_type.ice_slime;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;

public class IceSlimeEntity extends Slime {
    public IceSlimeEntity(EntityType<? extends Slime> entityType, Level world) {
        super(entityType, world);
        this.setSize(2, true);
        this.refreshDimensions();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 24)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.FOLLOW_RANGE, 10.0)
                .add(Attributes.ARMOR, 0f)
                .add(Attributes.ATTACK_DAMAGE, 10);
    }
    @Override
    public void remove(Entity.RemovalReason reason) {
        this.setSize(1, true);
        super.remove(reason);
    }
    @Override
    public void setSize(int size, boolean resetHealth) {
        super.setSize(2, resetHealth);
        this.refreshDimensions();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(24);
        this.setHealth(24);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(6.0);
        this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(40);
    }
    @Override
    protected void dealDamage(LivingEntity target) {
        super.dealDamage(target);

        if (target != null) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 140, 0), this);
        }
    }
}
