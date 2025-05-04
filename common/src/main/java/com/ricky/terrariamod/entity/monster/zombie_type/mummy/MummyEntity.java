package com.ricky.terrariamod.entity.monster.zombie_type.mummy;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class MummyEntity extends Zombie {
    public MummyEntity(EntityType<? extends Zombie> $$0, Level $$1) {
        super($$0, $$1);
    }
    @Override
    protected boolean isSunBurnTick() {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.ATTACK_DAMAGE, 15.0D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.MAX_HEALTH, 50D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (target instanceof LivingEntity livingTarget) {
            boolean success = super.doHurtTarget(target);

//            if (success) {
//                livingTarget.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 140), this);
//            }
            return success;
        }
        return false;
    }
}
