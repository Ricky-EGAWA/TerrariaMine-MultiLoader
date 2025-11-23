package com.ricky.terrariamod.entity.monster.skeleton_type.angry_bones;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class AngryBonesEntity extends Zombie {
    private boolean hasInitializedEquipment = false;

    public AngryBonesEntity(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected boolean isSunBurnTick() {
        // スケルトンのように日光で燃えない
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25F)
                // ゾンビの攻撃力は3（イージー）、5（ノーマル）、7（ハード）
                // 2.5倍なので12.5（ノーマル基準）
                .add(Attributes.ATTACK_DAMAGE, 12.5D)
                .add(Attributes.ARMOR, 2.0D)
                // ゾンビの体力は20、2.5倍で50
                .add(Attributes.MAX_HEALTH, 50D)
                // ノックバック耐性を追加
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public void tick() {
        super.tick();

        // 初回のみ装備を設定
        if (!this.level().isClientSide && !hasInitializedEquipment) {
            setupEquipment();
            hasInitializedEquipment = true;
        }
    }

    private void setupEquipment() {
        // 赤茶色（RED）に染色された革防具を装備
        int brownColor = DyeColor.RED.getTextColor();

        // ヘルメット
        ItemStack helmet = new ItemStack(Items.LEATHER_HELMET);
        CompoundTag helmetTag = helmet.getOrCreateTag();
        CompoundTag helmetDisplay = helmetTag.getCompound("display");
        helmetDisplay.putInt("color", brownColor);
        helmetTag.put("display", helmetDisplay);
        this.setItemSlot(EquipmentSlot.HEAD, helmet);

        // チェストプレート
        ItemStack chestplate = new ItemStack(Items.LEATHER_CHESTPLATE);
        CompoundTag chestTag = chestplate.getOrCreateTag();
        CompoundTag chestDisplay = chestTag.getCompound("display");
        chestDisplay.putInt("color", brownColor);
        chestTag.put("display", chestDisplay);
        this.setItemSlot(EquipmentSlot.CHEST, chestplate);

        // レギンス
        ItemStack leggings = new ItemStack(Items.LEATHER_LEGGINGS);
        CompoundTag legsTag = leggings.getOrCreateTag();
        CompoundTag legsDisplay = legsTag.getCompound("display");
        legsDisplay.putInt("color", brownColor);
        legsTag.put("display", legsDisplay);
        this.setItemSlot(EquipmentSlot.LEGS, leggings);

        // ブーツ
        ItemStack boots = new ItemStack(Items.LEATHER_BOOTS);
        CompoundTag bootsTag = boots.getOrCreateTag();
        CompoundTag bootsDisplay = bootsTag.getCompound("display");
        bootsDisplay.putInt("color", brownColor);
        bootsTag.put("display", bootsDisplay);
        this.setItemSlot(EquipmentSlot.FEET, boots);

        // 防具のドロップ確率を0にする（装備は落とさない）
        this.setDropChance(EquipmentSlot.HEAD, 0.0F);
        this.setDropChance(EquipmentSlot.CHEST, 0.0F);
        this.setDropChance(EquipmentSlot.LEGS, 0.0F);
        this.setDropChance(EquipmentSlot.FEET, 0.0F);

        // 武器を持たせない（素手で攻撃）
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    @Override
    public void die(DamageSource damageSource) {
        // 骨を1~3個ドロップ
        if (!this.level().isClientSide) {
            int boneCount = 1 + this.random.nextInt(3); // 1~3の範囲
            this.spawnAtLocation(Items.BONE, boneCount);
        }
        super.die(damageSource);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        // 通常のゾンビの攻撃処理
        return super.doHurtTarget(target);
    }
}
