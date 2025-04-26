package com.ricky.terrariamod.worldgen;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.world.biome.ModSurfaceRules;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import terrablender.api.SurfaceRuleManager;


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
}
