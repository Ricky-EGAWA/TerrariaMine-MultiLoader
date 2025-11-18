package com.ricky.terrariamod.entity.monster.bat_type.illuminant_bat;

import com.ricky.terrariamod.entity.monster.bat_type.BatAttackGoal;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class IlluminantBatEntity extends Bat {
    public IlluminantBatEntity(EntityType<? extends Bat> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.3f)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.ATTACK_DAMAGE, 5);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new BatAttackGoal(this, 0.45));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity target) {
        return super.doHurtTarget(target);
    }
}
