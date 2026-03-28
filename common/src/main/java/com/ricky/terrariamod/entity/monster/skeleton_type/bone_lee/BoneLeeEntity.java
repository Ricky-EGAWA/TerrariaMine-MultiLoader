package com.ricky.terrariamod.entity.monster.skeleton_type.bone_lee;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class BoneLeeEntity extends Skeleton {

    public BoneLeeEntity(EntityType<? extends Skeleton> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected boolean isSunBurnTick() {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                // Terraria: 速度极快，踢腿攻击
                .add(Attributes.MOVEMENT_SPEED, 0.45D)
                // Terraria: 85 damage -> MC: ~11
                .add(Attributes.ATTACK_DAMAGE, 11.0D)
                // Terraria: 22 defense -> MC: ~8
                .add(Attributes.ARMOR, 8.0D)
                // Terraria: 1000 HP -> MC: ~80
                .add(Attributes.MAX_HEALTH, 80.0D)
                // 踢腿攻击，高击退
                .add(Attributes.ATTACK_KNOCKBACK, 2.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void populateDefaultEquipmentSlots(net.minecraft.util.RandomSource random,
            net.minecraft.world.level.ServerLevelAccessor level) {
        // Bone Lee 不持弓，空手近战
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    @Override
    public void die(DamageSource damageSource) {
        if (!this.level().isClientSide) {
            // 掉落骨头 2~4 个
            int boneCount = 2 + this.random.nextInt(3);
            this.spawnAtLocation(Items.BONE, boneCount);

            // 少量概率掉落 Nunchakus（用绳子代替）
            if (this.random.nextFloat() < 0.05F) {
                this.spawnAtLocation(Items.STRING, 2);
            }
        }
        super.die(damageSource);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean result = super.doHurtTarget(target);
        // 踢腿：额外击退效果已通过 ATTACK_KNOCKBACK 属性实现
        return result;
    }
}
