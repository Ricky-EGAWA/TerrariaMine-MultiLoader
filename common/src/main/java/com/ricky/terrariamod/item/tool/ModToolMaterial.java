package com.ricky.terrariamod.item.tool;

import com.ricky.terrariamod.item.ModItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterial implements Tier {
    COBALT_INGOT(5, 2200, 11f, 6f, 20,
            () -> Ingredient.of(ModItems.COBALT_INGOT.get())),

    ORICHALCUM_INGOT(6, 2500, 13f, 7.5f, 23,
            () -> Ingredient.of(ModItems.ORICHALCUM_INGOT.get())),

    ADAMANTITE_INGOT(7, 3000, 15f, 9f, 25,
            () -> Ingredient.of(ModItems.ADAMANTITE_INGOT.get())),

    HELLSTONE_INGOT(4, 1800, 10f, 5f, 20,
            () -> Ingredient.of(ModItems.HELLSTONE_INGOT.get()));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return 0;
    }

    @Override
    public float getSpeed() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamageBonus() {
        return 0;
    }

    @Override
    public int getLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
