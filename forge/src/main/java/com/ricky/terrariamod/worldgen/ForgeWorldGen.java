package com.ricky.terrariamod.worldgen;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.world.biome.ModSurfaceRules;
import com.ricky.terrariamod.world.gen.ModWorldGeneration;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import terrablender.api.SurfaceRuleManager;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeWorldGen {
    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(ForgeWorldGen::onModSetup);
    }

    public static void onModSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SurfaceRuleManager.addSurfaceRules(
                    SurfaceRuleManager.RuleCategory.OVERWORLD,
                    Constants.MOD_ID,
                    ModSurfaceRules.makeCustomSurfaceRules()
            );
        });
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        ModWorldGeneration.generateModWorldGen();
    }
}
