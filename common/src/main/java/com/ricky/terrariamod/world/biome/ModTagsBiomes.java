package com.ricky.terrariamod.world.biome;

import com.ricky.terrariamod.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModTagsBiomes {
    public static final TagKey<Biome> IS_TERRARIA_DIMENSION = TagKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, "is_terraria_dimension"));
}
