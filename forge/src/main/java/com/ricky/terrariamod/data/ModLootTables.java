package com.ricky.terrariamod.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class ModLootTables extends LootTableProvider {
    public ModLootTables(PackOutput output) {
        super(output, Set.of(), List.of(
            new SubProviderEntry(ModLootTableProvider::new, LootContextParamSets.BLOCK)
        ));
    }
}
