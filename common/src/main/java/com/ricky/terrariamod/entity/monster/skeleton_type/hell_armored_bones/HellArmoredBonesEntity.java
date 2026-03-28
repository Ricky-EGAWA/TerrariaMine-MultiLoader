package com.ricky.terrariamod.entity.monster.skeleton_type.hell_armored_bones;

import net.minecraft.nbt.CompoundTag;
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

public class HellArmoredBonesEntity extends Skeleton {
    private boolean hasInitializedEquipment = false;

    public HellArmoredBonesEntity(EntityType<? extends Skeleton> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected boolean isSunBurnTick() {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.27F)
                // Hell Armored Bones: 最强の装甲骨 (Terraria: 80 damage)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                // 最高護甲 (Terraria: 28 defense)
                .add(Attributes.ARMOR, 12.0D)
                // 最高生命值 (Terraria: 400 HP)
                .add(Attributes.MAX_HEALTH, 80D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.7D)
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
        // 地狱装甲使用染色皮革装甲（深红/橙色混合）
        // 自定义 RGB: 暗红橙色 (0xA03020)
        int hellColor = 0xA03020;

        // 头盔
        ItemStack helmet = new ItemStack(Items.LEATHER_HELMET);
        CompoundTag helmetTag = helmet.getOrCreateTag();
        CompoundTag helmetDisplay = helmetTag.getCompound("display");
        helmetDisplay.putInt("color", hellColor);
        helmetTag.put("display", helmetDisplay);
        this.setItemSlot(EquipmentSlot.HEAD, helmet);

        // 胸甲
        ItemStack chestplate = new ItemStack(Items.LEATHER_CHESTPLATE);
        CompoundTag chestTag = chestplate.getOrCreateTag();
        CompoundTag chestDisplay = chestTag.getCompound("display");
        chestDisplay.putInt("color", hellColor);
        chestTag.put("display", chestDisplay);
        this.setItemSlot(EquipmentSlot.CHEST, chestplate);

        // 护腿
        ItemStack leggings = new ItemStack(Items.LEATHER_LEGGINGS);
        CompoundTag legsTag = leggings.getOrCreateTag();
        CompoundTag legsDisplay = legsTag.getCompound("display");
        legsDisplay.putInt("color", hellColor);
        legsTag.put("display", legsDisplay);
        this.setItemSlot(EquipmentSlot.LEGS, leggings);

        // 靴子
        ItemStack boots = new ItemStack(Items.LEATHER_BOOTS);
        CompoundTag bootsTag = boots.getOrCreateTag();
        CompoundTag bootsDisplay = bootsTag.getCompound("display");
        bootsDisplay.putInt("color", hellColor);
        bootsTag.put("display", bootsDisplay);
        this.setItemSlot(EquipmentSlot.FEET, boots);

        // 防具掉落率为 0
        this.setDropChance(EquipmentSlot.HEAD, 0.0F);
        this.setDropChance(EquipmentSlot.CHEST, 0.0F);
        this.setDropChance(EquipmentSlot.LEGS, 0.0F);
        this.setDropChance(EquipmentSlot.FEET, 0.0F);

        // 主手持钻石剑（地狱主题 = 更强的武器）
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean result = super.doHurtTarget(target);
        // Hell Armored Bones 攻击时点燃目标 (Terraria: On Fire! debuff)
        if (result) {
            target.setSecondsOnFire(4);
        }
        return result;
    }

    @Override
    public void die(DamageSource damageSource) {
        if (!this.level().isClientSide) {
            // 掉落骨头
            int boneCount = 2 + this.random.nextInt(4); // 2~5 个
            this.spawnAtLocation(Items.BONE, boneCount);

            // 有几率掉落烈焰粉
            if (random.nextFloat() < 0.2F) {
                this.spawnAtLocation(Items.BLAZE_POWDER, 1);
            }

            // 有几率掉落金锭
            if (random.nextFloat() < 0.15F) {
                this.spawnAtLocation(Items.GOLD_INGOT, 1);
            }
        }
        super.die(damageSource);
    }

    @Override
    public boolean fireImmune() {
        // Hell Armored Bones 免疫火焰伤害
        return true;
    }
}
