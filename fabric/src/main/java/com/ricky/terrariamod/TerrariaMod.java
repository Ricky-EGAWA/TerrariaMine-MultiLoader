package com.ricky.terrariamod;

import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.item.ModCreativeTabs;
import com.ricky.terrariamod.item.ModItems;
import net.fabricmc.api.ModInitializer;

public class TerrariaMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();

        //アイテムの追加
        ModItems.register();
        ModBlocks.register();
        //クリエイティブタブ
        ModCreativeTabs.register();

    }
}
