package com.ricky.terrariamod.world.biome;

import com.ricky.terrariamod.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModTagsBiomes {
    public static final TagKey<Biome> IS_TERRARIA_DIMENSION = TagKey.create(Registries.BIOME,
            new ResourceLocation(Constants.MOD_ID, "is_terraria_dimension"));

    public static final TagKey<Biome> IS_EBON = TagKey.create(Registries.BIOME,
            new ResourceLocation(Constants.MOD_ID, "is_ebon"));
    public static final TagKey<Biome> IS_CRIM = TagKey.create(Registries.BIOME,
            new ResourceLocation(Constants.MOD_ID, "is_crim"));
    public static final TagKey<Biome> IS_PEARL = TagKey.create(Registries.BIOME,
            new ResourceLocation(Constants.MOD_ID, "is_pearl"));
    public static final TagKey<Biome> IS_CORRUPTION = TagKey.create(Registries.BIOME,
            new ResourceLocation(Constants.MOD_ID, "is_corruption"));
    public static final TagKey<Biome> IS_GLOWING_MUSHROOM = TagKey.create(Registries.BIOME,
            new ResourceLocation(Constants.MOD_ID, "is_glowing_mushroom"));
    public static final TagKey<Biome> IS_ICE = TagKey.create(Registries.BIOME,
            new ResourceLocation(Constants.MOD_ID, "is_ice"));
}
