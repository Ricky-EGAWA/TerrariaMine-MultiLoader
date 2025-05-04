package com.ricky.terrariamod.util;

import com.ricky.terrariamod.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModLootTableInjector {

    public static LootPool getLootPoolFor(ResourceLocation id) {
        if (id.equals(new ResourceLocation("minecraft", "chests/jungle_temple"))) {
            return buildLootPool(ModItems.MAGIC_MIRROR.get(), 0.5f);
        }

        if (id.equals(new ResourceLocation("minecraft", "chests/desert_pyramid"))) {
            return buildLootPool(ModItems.MAGIC_MIRROR.get(), 0.16f);
        }

        if (id.equals(new ResourceLocation("minecraft", "chests/ancient_city"))) {
            return buildLootPool(ModItems.METAL_DETECTOR.get(), 0.5f);
        }

        if (id.equals(new ResourceLocation("minecraft", "chests/buried_treasure"))) {
            return buildLootPool(ModItems.INFINITE_WATER_BUCKET.get(), 1f);
        }

        if (id.equals(new ResourceLocation("minecraft", "gameplay/fishing/treasure"))) {
            return buildLootPool(ModItems.INFINITE_WATER_BUCKET.get(), 0.01f);
        }

        if (id.equals(new ResourceLocation("minecraft", "chests/bastion_treasure"))) {
            return buildLootPool(ModItems.INFINITE_LAVA_BUCKET.get(), 0.5f);
        }

        if (id.equals(new ResourceLocation("minecraft", "entities/piglin_brute"))) {
            return buildLootPool(ModItems.INFINITE_LAVA_BUCKET.get(), 1f);
        }

        return null;
    }

    private static LootPool buildLootPool(Item item, float chance) {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .when(LootItemRandomChanceCondition.randomChance(chance))
                .add(LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f))))
                .build();
    }
}
