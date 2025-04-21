package com.ricky.terrariamod;

import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.entity.ModEntities;
import com.ricky.terrariamod.entity.monster.bat_type.ice_bat.IceBatEntity;
import com.ricky.terrariamod.entity.monster.bat_type.jungle_bat.JungleBatEntity;
import com.ricky.terrariamod.entity.monster.bat_type.lava_bat.LavaBatEntity;
import com.ricky.terrariamod.entity.monster.slime_type.corrupt_slime.CorruptSlimeEntity;
import com.ricky.terrariamod.entity.monster.slime_type.crim_slime.CrimSlimeEntity;
import com.ricky.terrariamod.entity.monster.slime_type.dungeon_slime.DungeonSlimeEntity;
import com.ricky.terrariamod.entity.monster.slime_type.ice_slime.IceSlimeEntity;
import com.ricky.terrariamod.entity.monster.slime_type.jungle_slime.JungleSlimeEntity;
import com.ricky.terrariamod.entity.monster.slime_type.sand_slime.SandSlimeEntity;
import com.ricky.terrariamod.entity.monster.zombie_type.blood_mummy.BloodMummyEntity;
import com.ricky.terrariamod.entity.monster.zombie_type.dark_mummy.DarkMummyEntity;
import com.ricky.terrariamod.entity.monster.zombie_type.light_mummy.LightMummyEntity;
import com.ricky.terrariamod.entity.monster.zombie_type.mummy.MummyEntity;
import com.ricky.terrariamod.item.ModCreativeTabs;
import com.ricky.terrariamod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

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
        ModEntities.register();

        //region エンティティ
        FabricDefaultAttributeRegistry.register(ModEntities.MUMMY.get(), MummyEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.BLOOD_MUMMY.get(), BloodMummyEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.DARK_MUMMY.get(), DarkMummyEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.LIGHT_MUMMY.get(), LightMummyEntity.createAttributes());

        FabricDefaultAttributeRegistry.register(ModEntities.ICE_SLIME.get(), IceSlimeEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SAND_SLIME.get(), SandSlimeEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.JUNGLE_SLIME.get(), JungleSlimeEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.DUNGEON_SLIME.get(), DungeonSlimeEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CORRUPT_SLIME.get(), CorruptSlimeEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CRIM_SLIME.get(), CrimSlimeEntity.createAttributes());

        FabricDefaultAttributeRegistry.register(ModEntities.LAVA_BAT.get(), LavaBatEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.ICE_BAT.get(), IceBatEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.JUNGLE_BAT.get(), JungleBatEntity.createAttributes());
        //endregion

    }
}
