package com.ricky.terrariamod;

import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.block.entity.ModBlockEntities;
import com.ricky.terrariamod.entity.ModEntities;
import com.ricky.terrariamod.item.ModCreativeTabs;
import com.ricky.terrariamod.item.ModItems;
import com.ricky.terrariamod.networking.ModPackets;
import com.ricky.terrariamod.platform.Services;
import com.ricky.terrariamod.platform.services.ForgeNetworkHelper;
import com.ricky.terrariamod.worldgen.ForgeWorldGen;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class TerrariaMod {
    public TerrariaMod() {
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();

        ModItems.register();
        ModBlocks.register();
        ModBlockEntities.register();
        ModCreativeTabs.register();
        ModEntities.register();

        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ForgeWorldGen.init(modBus);
    }
    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModPackets.registerS2CPackets();
            ModPackets.registerC2SPackets();
            ((ForgeNetworkHelper) Services.NETWORK).registerClientHandlers();
        }
    }
}
