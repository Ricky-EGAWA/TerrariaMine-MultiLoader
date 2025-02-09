package com.ricky.terrariamod.entity;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.entity.monster.zombie_type.blood_mummy.BloodMummyEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.BLOOD_MUMMY.get(), BloodMummyEntity.createAttributes().build());
    }
}