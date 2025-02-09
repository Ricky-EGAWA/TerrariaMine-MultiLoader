package com.ricky.terrariamod.world.biome;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.registry.RegistryObject;
import com.ricky.terrariamod.registry.RegistryProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.*;

public class ModBiomes {
    public static final RegistryProvider<Biome> BIOMES =
            RegistryProvider.get(Registries.BIOME, Constants.MOD_ID);

    public static final RegistryObject<Biome> CRIM_DESERT_BIOME =
            BIOMES.register("crim_desert_biome", ModBiomes::createCrimDesert);

    private static Biome createCrimDesert() {
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .generationSettings(BiomeGenerationSettings.EMPTY)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColorOverride(0x7f03fc)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(0x22a1e6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .build();
    }

    // 登録メソッド
    public static void register() {
    }
}
