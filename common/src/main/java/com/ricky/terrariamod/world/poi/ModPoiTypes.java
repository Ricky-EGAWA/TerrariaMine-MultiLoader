package com.ricky.terrariamod.world.poi;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.registry.RegistryProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;

import java.util.HashSet;

public class ModPoiTypes {
    public static final RegistryProvider<PoiType> POI_TYPES =
            RegistryProvider.get(Registries.POINT_OF_INTEREST_TYPE, Constants.MOD_ID);

    public static final ResourceKey<PoiType> PORTAL_POI_KEY = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE,
            new ResourceLocation("terrariamod", "portal_poi"));

    public static void register() {
        // 空メソッド：ロードトリガー用（Forge側では DeferredRegister をフックする）
    }
}
