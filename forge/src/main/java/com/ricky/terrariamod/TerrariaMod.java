package com.ricky.terrariamod;

import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(Constants.MOD_ID)
public class TerrariaMod {
    public TerrariaMod() {
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        //アイテムの追加
        ModItems.register();
        ModBlocks.register();
    }
}
