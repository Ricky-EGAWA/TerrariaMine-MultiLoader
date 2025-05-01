package com.ricky.terrariamod.event;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.entity.ModEntities;
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
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MUMMY.get(), DarkMummyEntity.createAttributes().build());
        event.put(ModEntities.BLOOD_MUMMY.get(), BloodMummyEntity.createAttributes().build());
        event.put(ModEntities.DARK_MUMMY.get(), DarkMummyEntity.createAttributes().build());
        event.put(ModEntities.LIGHT_MUMMY.get(), LightMummyEntity.createAttributes().build());

        event.put(ModEntities.ICE_SLIME.get(), IceSlimeEntity.createAttributes().build());
        event.put(ModEntities.SAND_SLIME.get(), SandSlimeEntity.createAttributes().build());
        event.put(ModEntities.JUNGLE_SLIME.get(), JungleSlimeEntity.createAttributes().build());
        event.put(ModEntities.DUNGEON_SLIME.get(), DungeonSlimeEntity.createAttributes().build());
        event.put(ModEntities.CORRUPT_SLIME.get(), CorruptSlimeEntity.createAttributes().build());
        event.put(ModEntities.CRIM_SLIME.get(), CrimSlimeEntity.createAttributes().build());

        event.put(ModEntities.LAVA_BAT.get(), LavaBatEntity.createAttributes().build());
        event.put(ModEntities.ICE_BAT.get(), IceBatEntity.createAttributes().build());
        event.put(ModEntities.JUNGLE_BAT.get(), JungleBatEntity.createAttributes().build());

        event.put(ModEntities.DEMON_EYE.get(), DemonEyeEntity.createAttributes().build());
        event.put(ModEntities.CRIMERA.get(), CrimeraEntity.createAttributes().build());
        event.put(ModEntities.EATER_OU_SOUL.get(), EaterOfSoulEntity.createAttributes().build());
    }
}