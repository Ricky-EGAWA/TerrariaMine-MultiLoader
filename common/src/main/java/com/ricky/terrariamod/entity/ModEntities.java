package com.ricky.terrariamod.entity;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.entity.monster.zombie_type.blood_mummy.BloodMummyEntity;
import com.ricky.terrariamod.registry.RegistryObject;
import com.ricky.terrariamod.registry.RegistryProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final RegistryProvider<EntityType<?>> ENTITY_TYPES =
            RegistryProvider.get(Registries.ENTITY_TYPE, Constants.MOD_ID);

    public static final RegistryObject<EntityType<BloodMummyEntity>> BLOOD_MUMMY =
            ENTITY_TYPES.register("blood_mummy", () -> EntityType.Builder.of(BloodMummyEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.95f).build("blood_mummy"));

    // 登録メソッド
    public static void register() {
    }
}
