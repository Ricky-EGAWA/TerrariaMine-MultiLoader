package com.ricky.terrariamod.entity.monster.skeleton_type.blue_armored_bones;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class BlueArmoredBonesEntity extends Skeleton {
    private boolean hasInitializedEquipment = false;

    public BlueArmoredBonesEntity(EntityType<? extends Skeleton> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected boolean isSunBurnTick() {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25F)
                // Blue Armored Bones 有更高的攻击力 (Terraria: 75 damage)
                .add(Attributes.ATTACK_DAMAGE, 9.0D)
                // 更高的防御力 (Terraria: 22 defense)
                .add(Attributes.ARMOR, 8.0D)
                // 更高的生命值 (Terraria: 300 HP)
                .add(Attributes.MAX_HEALTH, 60D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide && !hasInitializedEquipment) {
            setupEquipment();
            hasInitializedEquipment = true;
        }
    }

    private void setupEquipment() {
        // 蓝色（LIGHT_BLUE）装甲
        int blueColor = DyeColor.LIGHT_BLUE.getTextColor();

        // 头盔
        ItemStack helmet = new ItemStack(Items.LEATHER_HELMET);
        CompoundTag helmetTag = helmet.getOrCreateTag();
        CompoundTag helmetDisplay = helmetTag.getCompound("display");
        helmetDisplay.putInt("color", blueColor);
        helmetTag.put("display", helmetDisplay);
        this.setItemSlot(EquipmentSlot.HEAD, helmet);

        // 胸甲
        ItemStack chestplate = new ItemStack(Items.LEATHER_CHESTPLATE);
        CompoundTag chestTag = chestplate.getOrCreateTag();
        CompoundTag chestDisplay = chestTag.getCompound("display");
        chestDisplay.putInt("color", blueColor);
        chestTag.put("display", chestDisplay);
        this.setItemSlot(EquipmentSlot.CHEST, chestplate);

        // 护腿
        ItemStack leggings = new ItemStack(Items.LEATHER_LEGGINGS);
        CompoundTag legsTag = leggings.getOrCreateTag();
        CompoundTag legsDisplay = legsTag.getCompound("display");
        legsDisplay.putInt("color", blueColor);
        legsTag.put("display", legsDisplay);
        this.setItemSlot(EquipmentSlot.LEGS, leggings);

        // 靴子
        ItemStack boots = new ItemStack(Items.LEATHER_BOOTS);
        CompoundTag bootsTag = boots.getOrCreateTag();
        CompoundTag bootsDisplay = bootsTag.getCompound("display");
        bootsDisplay.putInt("color", blueColor);
        bootsTag.put("display", bootsDisplay);
        this.setItemSlot(EquipmentSlot.FEET, boots);

        // 防具掉落率为 0
        this.setDropChance(EquipmentSlot.HEAD, 0.0F);
        this.setDropChance(EquipmentSlot.CHEST, 0.0F);
        this.setDropChance(EquipmentSlot.LEGS, 0.0F);
        this.setDropChance(EquipmentSlot.FEET, 0.0F);

        // 主手持铁剑
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
    }

    @Override
    public void die(DamageSource damageSource) {
        if (!this.level().isClientSide) {
            // 掉落骨头
            int boneCount = 2 + this.random.nextInt(3); // 2~4 个
            this.spawnAtLocation(Items.BONE, boneCount);

            // 有几率掉落铁锭
            if (random.nextFloat() < 0.2F) {
                this.spawnAtLocation(Items.IRON_INGOT, 1);
            }
        }
        super.die(damageSource);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        return super.doHurtTarget(target);
    }
}
