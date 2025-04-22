package com.ricky.terrariamod.world.biome;

import com.ricky.terrariamod.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.*;

public class ModBiomes {
    public static final ResourceKey<Biome> CRIM_DESERT_BIOME = createKey("crim_desert_biome");
    public static final ResourceKey<Biome> EBON_DESERT_BIOME = createKey("ebon_desert_biome");
    public static final ResourceKey<Biome> PEARL_DESERT_BIOME = createKey("pearl_desert_biome");
    public static final ResourceKey<Biome> CRIM_BIOME = createKey("crim_biome");
    public static final ResourceKey<Biome> EBON_BIOME = createKey("ebon_biome");
    public static final ResourceKey<Biome> PEARL_BIOME = createKey("pearl_biome");
    public static final ResourceKey<Biome> CRIM_ICE_BIOME = createKey("crim_ice_biome");
    public static final ResourceKey<Biome> EBON_ICE_BIOME = createKey("ebon_ice_biome");
    public static final ResourceKey<Biome> PEARL_ICE_BIOME = createKey("pearl_ice_biome");
    //地下
    public static final ResourceKey<Biome> GLOWING_MUSHROOM_BIOME = createKey("glowing_mushroom_biome");

    private static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, Constants.locate(name));
    }

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
