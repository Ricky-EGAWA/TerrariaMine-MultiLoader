package com.ricky.terrariamod.event;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.util.ModLootTableInjector;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class ModForgeLootInjector {

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation id = event.getName();

        // 共通の LootPool を取得
        LootPool customPool = ModLootTableInjector.getLootPoolFor(id);
        if (customPool != null) {
            event.getTable().addPool(customPool);
        }
    }
}
