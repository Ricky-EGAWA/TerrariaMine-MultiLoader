package com.ricky.terrariamod.worldgen;

import com.mojang.serialization.Codec;
import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.platform.services.ForgeWorldGenHelper;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.Nullable;

public class ForgeWorldGen {
    @Nullable
    private static Codec<ForgeBiomeModifier> libBiomeModifierCodec = null;

    public static void init(IEventBus modEventBus) {
        modEventBus.<RegisterEvent>addListener(event -> {
            event.register(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, registry -> {
                registry.register(new ResourceLocation(Constants.MOD_ID, "lib_biome_modifier"), libBiomeModifierCodec = Codec.unit(ForgeBiomeModifier.INSTANCE));
            });
        });
    }

    private static class ForgeBiomeModifier implements BiomeModifier {

        private static final ForgeBiomeModifier INSTANCE = new ForgeBiomeModifier();

        @Override
        public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
            ForgeWorldGenHelper.modifyBiome(biome, phase, builder);
        }

        @Override
        public Codec<? extends BiomeModifier> codec() {
            if (libBiomeModifierCodec != null) {
                return libBiomeModifierCodec;
            } else {
                return Codec.unit(INSTANCE);
            }
        }

    }
}