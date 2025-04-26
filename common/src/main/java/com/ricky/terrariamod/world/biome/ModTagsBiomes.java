package com.ricky.terrariamod.world.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModTagsBiomes {
    public static final TagKey<Biome> IS_TERRARIA_DIMENSION = TagKey.create(Registries.BIOME, new ResourceLocation("terrariamod", "is_terraria_dimension"));
}
