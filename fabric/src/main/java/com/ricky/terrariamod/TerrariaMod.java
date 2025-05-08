package com.ricky.terrariamod;

import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.entity.ModEntities;
import com.ricky.terrariamod.entity.boss.EyeOfCthulhuEntity;
import com.ricky.terrariamod.entity.monster.bat_type.ice_bat.IceBatEntity;
import com.ricky.terrariamod.entity.monster.bat_type.jungle_bat.JungleBatEntity;
import com.ricky.terrariamod.entity.monster.bat_type.lava_bat.LavaBatEntity;
import com.ricky.terrariamod.entity.monster.flying_type.crimera.CrimeraEntity;
import com.ricky.terrariamod.entity.monster.flying_type.demon_eye.DemonEyeEntity;
import com.ricky.terrariamod.entity.monster.flying_type.eater_of_soul.EaterOfSoulEntity;
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
import com.ricky.terrariamod.networking.ModPackets;
import com.ricky.terrariamod.util.ModFabricTrades;
import com.ricky.terrariamod.util.ModLootTableInjector;
import com.ricky.terrariamod.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.level.storage.loot.LootPool;

public class TerrariaMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();

        ModItems.register();
        ModBlocks.register();
        ModCreativeTabs.register();
        ModEntities.register();

        ModFabricTrades.registerTrades();

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, builder, source) -> {
            if (source.isBuiltin()) {
                LootPool pool = ModLootTableInjector.getLootPoolFor(id);
                if (pool != null) {
                    builder.pool(pool);
                }
            }
        });
        ModWorldGeneration.generateModWorldGen();

        //region entity
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

        FabricDefaultAttributeRegistry.register(ModEntities.DEMON_EYE.get(), DemonEyeEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CRIMERA.get(), CrimeraEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.EATER_OU_SOUL.get(), EaterOfSoulEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.EYE_OF_CTHULHU.get(), EyeOfCthulhuEntity.createAttributes());
        //endregion

        ModPackets.registerC2SPackets();
        ModPackets.registerS2CPackets();
    }
}
