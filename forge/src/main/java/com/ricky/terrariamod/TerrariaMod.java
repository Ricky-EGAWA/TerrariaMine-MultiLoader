package com.ricky.terrariamod;

import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.entity.ModEntities;
import com.ricky.terrariamod.item.ModCreativeTabs;
import com.ricky.terrariamod.item.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class TerrariaMod {
    public TerrariaMod() {
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();

        //アイテムの追加
        ModItems.register();
        ModBlocks.register();
        ModCreativeTabs.register();
        ModEntities.register();
    }
}
