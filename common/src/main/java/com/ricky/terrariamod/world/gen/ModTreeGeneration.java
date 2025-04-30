package com.ricky.terrariamod.world.gen;

import com.ricky.terrariamod.platform.Services;
import com.ricky.terrariamod.platform.services.IWorldGenHelper;
import com.ricky.terrariamod.world.ModPlacedFeatures;
import com.ricky.terrariamod.world.biome.ModTagsBiomes;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.ServiceLoader;

public class ModTreeGeneration {

    public static void generateTrees() {
        System.out.println("generate trees");
        
        // Terraria次元に生やす木
        Services.WORLD_GEN.addFeatureToBiomes(matchesTag(ModTagsBiomes.IS_EBON),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.EBON_PLACED_KEY);
        Services.WORLD_GEN.addFeatureToBiomes(matchesTag(ModTagsBiomes.IS_CRIM),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CRIM_PLACED_KEY);
        Services.WORLD_GEN.addFeatureToBiomes(matchesTag(ModTagsBiomes.IS_PEARL),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PEARL_PLACED_KEY);

        Services.WORLD_GEN.addFeatureToBiomes(matchesTag(ModTagsBiomes.IS_CORRUPTION),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModPlacedFeatures.DEATH_WEED_PLACED_KEY);
        Services.WORLD_GEN.addFeatureToBiomes(matchesTag(ModTagsBiomes.IS_ICE),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModPlacedFeatures.SHIVER_THORN_PLACED_KEY);
        Services.WORLD_GEN.addFeatureToBiomes(matchesTag(ModTagsBiomes.IS_GLOWING_MUSHROOM),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.GLOWING_MUSHROOM_PLACED_KEY);
        Services.WORLD_GEN.addFeatureToBiomes(matchesTag(ModTagsBiomes.IS_GLOWING_MUSHROOM),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.GLOWING_HUGE_MUSHROOM_PLACED_KEY);


        // ネザーに生やす木 (もし必要なら)
        // WORLD_GEN.addFeatureToBiomes(matchesTag(BiomeTags.IS_NETHER),
        //        GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NETHER_TREE_PLACED_KEY);

        // エンドに生やす木 (もし必要なら)
        // WORLD_GEN.addFeatureToBiomes(matchesTag(BiomeTags.IS_END),
        //        GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.END_TREE_PLACED_KEY);
    }

    private static IWorldGenHelper.BiomePredicate matchesTag(TagKey<Biome> tag) {
        return (resourceLocation, biome) -> biome.is(tag);
    }
}
