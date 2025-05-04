package com.ricky.terrariamod.world;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.ModBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.tags.BlockTags;

import java.util.List;

public class ModConfiguredFeatures {

    // ResourceKey for our cobalt ore feature
    public static final ResourceKey<ConfiguredFeature<?, ?>> COBALT_ORE = createKey("cobalt_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EBON_TREE = createKey("ebon_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRIM_TREE = createKey("crim_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEARL_TREE = createKey("pearl_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_GLOWING_MUSHROOM = createKey("huge_glowing_mushroom");

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Constants.locate(name));
    }
}
