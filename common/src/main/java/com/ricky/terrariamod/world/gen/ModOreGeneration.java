package com.ricky.terrariamod.world.gen;


import com.ricky.terrariamod.platform.Services;
import com.ricky.terrariamod.platform.services.IWorldGenHelper;
import com.ricky.terrariamod.world.ModPlacedFeatures;
import com.ricky.terrariamod.world.biome.ModTagsBiomes;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres() {
        System.out.println("generate ore");
        Services.WORLD_GEN.addFeatureToBiomes(matchesTag(ModTagsBiomes.IS_TERRARIA_DIMENSION),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.COBALT_ORE_PLACED_KEY);
        Services.WORLD_GEN.addFeatureToBiomes(matchesTag(ModTagsBiomes.IS_TERRARIA_DIMENSION),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORICHALCUM_ORE_PLACED_KEY);
        Services.WORLD_GEN.addFeatureToBiomes(matchesTag(ModTagsBiomes.IS_TERRARIA_DIMENSION),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ADAMANTITE_ORE_PLACED_KEY);

        Services.WORLD_GEN.addFeatureToBiomes(matchesTag(BiomeTags.IS_NETHER),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.NETHER_HELLSTONE_ORE_PLACED_KEY);

//        WORLD_GEN.addFeatureToBiomes(matchesTag(BiomeTags.IS_END),
//                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.END_COBALT_ORE_PLACED_KEY);

    }
    private static IWorldGenHelper.BiomePredicate matchesTag(TagKey<Biome> tag) {
        return (resourceLocation, biome) -> biome.is(tag);
    }
}
