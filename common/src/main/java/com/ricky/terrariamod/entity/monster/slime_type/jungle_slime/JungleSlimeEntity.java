package com.ricky.terrariamod.entity.monster.slime_type.jungle_slime;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;

public class JungleSlimeEntity extends Slime {
    public JungleSlimeEntity(EntityType<? extends Slime> entityType, Level world) {
        super(entityType, world);
        this.setSize(2, true); // サイズを2に固定
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
    public void remove(RemovalReason reason) {
        // スライムが死亡したときの分裂を防ぐためにサイズを1に固定
        this.setSize(1, true); // サイズを1に固定
        super.remove(reason);  // 通常の死亡処理を呼び出す
    }
    @Override
    public void setSize(int size, boolean resetHealth) {
        super.setSize(2, resetHealth);
        this.refreshDimensions();
        // 体力の設定
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(24);
        this.setHealth(24);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(6.0);
        this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(40);
    }
    @Override
    protected void dealDamage(LivingEntity target) {
        super.dealDamage(target);

        // Slowness 効果を付与
        if (target != null) {
            target.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 0), this);
        }
    }
}
