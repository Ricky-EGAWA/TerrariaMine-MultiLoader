package com.ricky.terrariamod.item.armor;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.item.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;


public enum ModArmorMaterials implements ArmorMaterial {

    OAK("oak", 5, new int[] {1,2,2,1}, 10,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.of(Blocks.OAK_LOG)),
    COPPER("copper", 12, new int[] {2,5,4,2}, 9,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.of(Items.COPPER_INGOT)),
    OBSIDIAN("obsidian", 35, new int[] {3,8,6,3}, 7,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.of(Blocks.OBSIDIAN)),
    CACTUS("cactus", 8, new int[] {2,5,3,1}, 19,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.of(Blocks.CACTUS)),
    PUMPKIN("pumpkin", 8, new int[] {2,5,3,1}, 19,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.of(Blocks.PUMPKIN)),
    GLASS("glass", 25, new int[] {2,0,0,0}, 9,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.of(Blocks.GLASS)),
    NIGHT("night", 25, new int[] {2,0,0,0}, 19,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.of(Blocks.GLASS)),

    COBALT("cobalt", 35, new int[] {4,8,6,4}, 15,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1f, () -> Ingredient.of(ModItems.COBALT_INGOT.get())),
    ORICHALCUM("orichalcum", 45, new int[] {5,12,9,4}, 15,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.2f, () -> Ingredient.of(ModItems.ORICHALCUM_INGOT.get())),

    ADAMANTITE("adamantite", 50, new int[] {6,16,12,6}, 15,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 5f, 0.3f, () -> Ingredient.of(ModItems.ADAMANTITE_INGOT.get())),

    HELLSTONE("hellstone", 40, new int[] {3,8,6,3}, 15,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 3f, 0.1f, () -> Ingredient.of(ModItems.ADAMANTITE_INGOT.get()));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASEDURABILITY = {11,16,15,13};

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound,
                      float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient){
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }
    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return 0;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return 0;
    }

    @Override
    public int getEnchantmentValue() {
        return 0;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return Constants.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
