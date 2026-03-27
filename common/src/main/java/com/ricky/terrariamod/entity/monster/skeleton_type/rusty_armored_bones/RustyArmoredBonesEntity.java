package com.ricky.terrariamod.entity.monster.skeleton_type.rusty_armored_bones;

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

public class RustyArmoredBonesEntity extends Skeleton {
    private boolean hasInitializedEquipment = false;

    public RustyArmoredBonesEntity(EntityType<? extends Skeleton> entityType, Level level) {
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
                // Rusty Armored Bones (Terraria: 75 damage)
                .add(Attributes.ATTACK_DAMAGE, 9.0D)
                // 更高护甲 (Terraria: 26 defense, 比 Blue 略高)
                .add(Attributes.ARMOR, 10.0D)
                // 更高生命值 (Terraria: 350 HP, 比 Blue 略高)
                .add(Attributes.MAX_HEALTH, 70D)
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
        // 铁锈色（BROWN）装甲
        int rustColor = DyeColor.BROWN.getTextColor();

        // 头盔
        ItemStack helmet = new ItemStack(Items.LEATHER_HELMET);
        CompoundTag helmetTag = helmet.getOrCreateTag();
        CompoundTag helmetDisplay = helmetTag.getCompound("display");
        helmetDisplay.putInt("color", rustColor);
        helmetTag.put("display", helmetDisplay);
        this.setItemSlot(EquipmentSlot.HEAD, helmet);

        // 胸甲
        ItemStack chestplate = new ItemStack(Items.LEATHER_CHESTPLATE);
        CompoundTag chestTag = chestplate.getOrCreateTag();
        CompoundTag chestDisplay = chestTag.getCompound("display");
        chestDisplay.putInt("color", rustColor);
        chestTag.put("display", chestDisplay);
        this.setItemSlot(EquipmentSlot.CHEST, chestplate);

        // 护腿
        ItemStack leggings = new ItemStack(Items.LEATHER_LEGGINGS);
        CompoundTag legsTag = leggings.getOrCreateTag();
        CompoundTag legsDisplay = legsTag.getCompound("display");
        legsDisplay.putInt("color", rustColor);
        legsTag.put("display", legsDisplay);
        this.setItemSlot(EquipmentSlot.LEGS, leggings);

        // 靴子
        ItemStack boots = new ItemStack(Items.LEATHER_BOOTS);
        CompoundTag bootsTag = boots.getOrCreateTag();
        CompoundTag bootsDisplay = bootsTag.getCompound("display");
        bootsDisplay.putInt("color", rustColor);
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

            // 有几率掉落铁锭 (比 Blue 略高几率)
            if (random.nextFloat() < 0.25F) {
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
