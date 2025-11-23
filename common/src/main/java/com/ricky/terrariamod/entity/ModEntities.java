package com.ricky.terrariamod.entity;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.entity.boss.EyeOfCthulhuEntity;
import com.ricky.terrariamod.entity.monster.bat_type.ice_bat.IceBatEntity;
import com.ricky.terrariamod.entity.monster.bat_type.illuminant_bat.IlluminantBatEntity;
import com.ricky.terrariamod.entity.monster.bat_type.jungle_bat.JungleBatEntity;
import com.ricky.terrariamod.entity.monster.bat_type.lava_bat.LavaBatEntity;
import com.ricky.terrariamod.entity.monster.flying_type.crimera.CrimeraEntity;
import com.ricky.terrariamod.entity.monster.flying_type.cursed_skull.CursedSkullEntity;
import com.ricky.terrariamod.entity.monster.flying_type.demon_eye.DemonEyeEntity;
import com.ricky.terrariamod.entity.monster.flying_type.eater_of_soul.EaterOfSoulEntity;
import com.ricky.terrariamod.entity.monster.slime_type.corrupt_slime.CorruptSlimeEntity;
import com.ricky.terrariamod.entity.monster.slime_type.crim_slime.CrimSlimeEntity;
import com.ricky.terrariamod.entity.monster.slime_type.dungeon_slime.DungeonSlimeEntity;
import com.ricky.terrariamod.entity.monster.slime_type.ice_slime.IceSlimeEntity;
import com.ricky.terrariamod.entity.monster.slime_type.illuminantslime.IlluminantSlimeEntity;
import com.ricky.terrariamod.entity.monster.slime_type.jungle_slime.JungleSlimeEntity;
import com.ricky.terrariamod.entity.monster.slime_type.sand_slime.SandSlimeEntity;
import com.ricky.terrariamod.entity.monster.skeleton_type.angry_bones.AngryBonesEntity;
import com.ricky.terrariamod.entity.monster.skeleton_type.dark_caster.DarkCasterEntity;
import com.ricky.terrariamod.entity.monster.zombie_type.blood_mummy.BloodMummyEntity;
import com.ricky.terrariamod.entity.monster.zombie_type.dark_mummy.DarkMummyEntity;
import com.ricky.terrariamod.entity.monster.zombie_type.light_mummy.LightMummyEntity;
import com.ricky.terrariamod.entity.monster.zombie_type.mummy.MummyEntity;
import com.ricky.terrariamod.entity.monster.zombie_type.spore_zombie.SporeZombieEntity;
import com.ricky.terrariamod.entity.projectile.ammo.musket_ball.MusketBallEntity;
import com.ricky.terrariamod.entity.projectile.ammo.rocket.RocketEntity;
import com.ricky.terrariamod.entity.projectile.magic.MagicBallEntity;
import com.ricky.terrariamod.registry.RegistryObject;
import com.ricky.terrariamod.registry.RegistryProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final RegistryProvider<EntityType<?>> ENTITY_TYPES =
            RegistryProvider.get(Registries.ENTITY_TYPE, Constants.MOD_ID);
    //region skeleton type
    public static final RegistryObject<EntityType<AngryBonesEntity>> ANGRY_BONES =
            ENTITY_TYPES.register("angry_bones", () -> EntityType.Builder.of(AngryBonesEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.99f).build("angry_bones"));
    public static final RegistryObject<EntityType<DarkCasterEntity>> DARK_CASTER =
            ENTITY_TYPES.register("dark_caster", () -> EntityType.Builder.of(DarkCasterEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.99f).build("dark_caster"));
    //endregion
    //region zombie type
    public static final RegistryObject<EntityType<MummyEntity>> MUMMY =
            ENTITY_TYPES.register("mummy", () -> EntityType.Builder.of(MummyEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.95f).build("mummy"));
    public static final RegistryObject<EntityType<BloodMummyEntity>> BLOOD_MUMMY =
            ENTITY_TYPES.register("blood_mummy", () -> EntityType.Builder.of(BloodMummyEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.95f).build("blood_mummy"));
    public static final RegistryObject<EntityType<DarkMummyEntity>> DARK_MUMMY =
            ENTITY_TYPES.register("dark_mummy", () -> EntityType.Builder.of(DarkMummyEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.95f).build("dark_mummy"));
    public static final RegistryObject<EntityType<LightMummyEntity>> LIGHT_MUMMY =
            ENTITY_TYPES.register("light_mummy", () -> EntityType.Builder.of(LightMummyEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.95f).build("light_mummy"));
    public static final RegistryObject<EntityType<SporeZombieEntity>> SPORE_ZOMBIE =
            ENTITY_TYPES.register("spore_zombie", () -> EntityType.Builder.of(SporeZombieEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.95f).build("spore_zombie"));
    //endregion
    //region slime type
    public static final RegistryObject<EntityType<IceSlimeEntity>> ICE_SLIME =
            ENTITY_TYPES.register("ice_slime", () -> EntityType.Builder.of(IceSlimeEntity::new, MobCategory.MONSTER)
                    .sized(1.02f, 1.02f).build("ice_slime"));
    public static final RegistryObject<EntityType<SandSlimeEntity>> SAND_SLIME =
            ENTITY_TYPES.register("sand_slime", () -> EntityType.Builder.of(SandSlimeEntity::new, MobCategory.MONSTER)
                    .sized(1.02f, 1.02f).build("sand_slime"));
    public static final RegistryObject<EntityType<JungleSlimeEntity>> JUNGLE_SLIME =
            ENTITY_TYPES.register("jungle_slime", () -> EntityType.Builder.of(JungleSlimeEntity::new, MobCategory.MONSTER)
                    .sized(1.02f, 1.02f).build("jungle_slime"));
    public static final RegistryObject<EntityType<DungeonSlimeEntity>> DUNGEON_SLIME =
            ENTITY_TYPES.register("dungeon_slime", () -> EntityType.Builder.of(DungeonSlimeEntity::new, MobCategory.MONSTER)
                    .sized(1.02f, 1.02f).build("dungeon_slime"));
    public static final RegistryObject<EntityType<CorruptSlimeEntity>> CORRUPT_SLIME =
            ENTITY_TYPES.register("corrupt_slime", () -> EntityType.Builder.of(CorruptSlimeEntity::new, MobCategory.MONSTER)
                    .sized(1.02f, 1.02f).build("corrupt_slime"));
    public static final RegistryObject<EntityType<CrimSlimeEntity>> CRIM_SLIME =
            ENTITY_TYPES.register("crim_slime", () -> EntityType.Builder.of(CrimSlimeEntity::new, MobCategory.MONSTER)
                    .sized(1.02f, 1.02f).build("crim_slime"));
    public static final RegistryObject<EntityType<IlluminantSlimeEntity>> ILLUMINANT_SLIME =
            ENTITY_TYPES.register("illuminant_slime", () -> EntityType.Builder.of(IlluminantSlimeEntity::new, MobCategory.MONSTER)
                    .sized(1.02f, 1.02f).build("illuminant_slime"));
    //endregion
    //region bat type
    public static final RegistryObject<EntityType<LavaBatEntity>> LAVA_BAT =
            ENTITY_TYPES.register("lava_bat", () -> EntityType.Builder.of(LavaBatEntity::new, MobCategory.MONSTER)
                    .sized(0.5f, 0.9f).build("lava_bat"));
    public static final RegistryObject<EntityType<IceBatEntity>> ICE_BAT =
            ENTITY_TYPES.register("ice_bat", () -> EntityType.Builder.of(IceBatEntity::new, MobCategory.MONSTER)
                    .sized(0.5f, 0.9f).build("ice_bat"));
    public static final RegistryObject<EntityType<JungleBatEntity>> JUNGLE_BAT =
            ENTITY_TYPES.register("jungle_bat", () -> EntityType.Builder.of(JungleBatEntity::new, MobCategory.MONSTER)
                    .sized(0.5f, 0.9f).build("jungle_bat"));
    public static final RegistryObject<EntityType<IlluminantBatEntity>> ILLUMINANT_BAT =
            ENTITY_TYPES.register("illuminant_bat", () -> EntityType.Builder.of(IlluminantBatEntity::new, MobCategory.MONSTER)
                    .sized(0.5f, 0.9f).build("illuminant_bat"));
    //endregion
    //region flying type
    public static final RegistryObject<EntityType<DemonEyeEntity>> DEMON_EYE =
            ENTITY_TYPES.register("demon_eye", () -> EntityType.Builder.of(DemonEyeEntity::new, MobCategory.MONSTER)
                    .sized(0.7f, 0.7f).build("demon_eye"));
    public static final RegistryObject<EntityType<CrimeraEntity>> CRIMERA =
            ENTITY_TYPES.register("crimera", () -> EntityType.Builder.of(CrimeraEntity::new, MobCategory.MONSTER)
                    .sized(1f, 0.5f).build("crimera"));
    public static final RegistryObject<EntityType<EaterOfSoulEntity>> EATER_OU_SOUL =
            ENTITY_TYPES.register("eater_of_soul", () -> EntityType.Builder.of(EaterOfSoulEntity::new, MobCategory.MONSTER)
                    .sized(1f, 0.5f).build("eater_of_soul"));
    public static final RegistryObject<EntityType<CursedSkullEntity>> CURSED_SKULL =
            ENTITY_TYPES.register("cursed_skull", () -> EntityType.Builder.of(CursedSkullEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 0.8f).build("cursed_skull"));
    //endregion
    //region boss
    public static final RegistryObject<EntityType<EyeOfCthulhuEntity>> EYE_OF_CTHULHU =
            ENTITY_TYPES.register("eye_of_cthulhu", ()-> EntityType.Builder.of(EyeOfCthulhuEntity::new, MobCategory.MONSTER)
                    .sized(3f, 3f).build("eye_of_cthulhu"));
    //endregion
    //region projectile
    public static final RegistryObject<EntityType<MagicBallEntity>> MAGIC_BALL =
            ENTITY_TYPES.register("magic_ball", () ->
                    EntityType.Builder.<MagicBallEntity>of(MagicBallEntity::new, MobCategory.MISC)
                            .sized(0.3f, 0.3f)
                            .clientTrackingRange(80)
                            .updateInterval(10)
                            .build("magic_ball")
            );
    public static final RegistryObject<EntityType<RocketEntity>> ROCKET =
            ENTITY_TYPES.register("rocket", () ->
                    EntityType.Builder.<RocketEntity>of(RocketEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(80)
                            .updateInterval(10)
                            .build("rocket")
            );
    public static final RegistryObject<EntityType<MusketBallEntity>> MUSKET_BALL =
            ENTITY_TYPES.register("musket_ball", () ->
                    EntityType.Builder.<MusketBallEntity>of(MusketBallEntity::new, MobCategory.MISC)
                            .sized(0.05f, 0.05f)
                            .clientTrackingRange(80)
                            .updateInterval(10)
                            .build("musket_ball")
            );
    //endregion

    public static void register() {
    }
}
