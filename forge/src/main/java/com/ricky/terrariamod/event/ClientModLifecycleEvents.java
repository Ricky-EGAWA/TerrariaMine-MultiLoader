package com.ricky.terrariamod.event;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.entity.ModEntities;
import com.ricky.terrariamod.entity.boss.EyeOfCthulhuModelOne;
import com.ricky.terrariamod.entity.boss.EyeOfCthulhuModelTwo;
import com.ricky.terrariamod.entity.boss.EyeOfCthulhuRenderer;
import com.ricky.terrariamod.entity.monster.bat_type.ice_bat.IceBatRenderer;
import com.ricky.terrariamod.entity.monster.bat_type.illuminant_bat.IlluminantBatRenderer;
import com.ricky.terrariamod.entity.monster.bat_type.jungle_bat.JungleBatRenderer;
import com.ricky.terrariamod.entity.monster.bat_type.lava_bat.LavaBatRenderer;
import com.ricky.terrariamod.entity.monster.flying_type.crimera.CrimeraModel;
import com.ricky.terrariamod.entity.monster.flying_type.crimera.CrimeraRenderer;
import com.ricky.terrariamod.entity.monster.flying_type.cursed_skull.CursedSkullModel;
import com.ricky.terrariamod.entity.monster.flying_type.cursed_skull.CursedSkullRenderer;
import com.ricky.terrariamod.entity.monster.flying_type.demon_eye.DemonEyeModel;
import com.ricky.terrariamod.entity.monster.flying_type.demon_eye.DemonEyeRenderer;
import com.ricky.terrariamod.entity.monster.flying_type.eater_of_soul.EaterOfSoulModel;
import com.ricky.terrariamod.entity.monster.flying_type.eater_of_soul.EaterOfSoulRenderer;
import com.ricky.terrariamod.entity.monster.slime_type.corrupt_slime.CorruptSlimeRenderer;
import com.ricky.terrariamod.entity.monster.slime_type.crim_slime.CrimSlimeRenderer;
import com.ricky.terrariamod.entity.monster.slime_type.dungeon_slime.DungeonSlimeRenderer;
import com.ricky.terrariamod.entity.monster.slime_type.ice_slime.IceSlimeRenderer;
import com.ricky.terrariamod.entity.monster.slime_type.jungle_slime.JungleSlimeRenderer;
import com.ricky.terrariamod.entity.monster.slime_type.sand_slime.SandSlimeRenderer;
import com.ricky.terrariamod.entity.monster.skeleton_type.angry_bones.AngryBonesRenderer;
import com.ricky.terrariamod.entity.monster.skeleton_type.dark_caster.DarkCasterBallRenderer;
import com.ricky.terrariamod.entity.monster.skeleton_type.dark_caster.DarkCasterRenderer;
import com.ricky.terrariamod.entity.monster.zombie_type.blood_mummy.BloodMummyRenderer;
import com.ricky.terrariamod.entity.monster.zombie_type.dark_mummy.DarkMummyRenderer;
import com.ricky.terrariamod.entity.monster.zombie_type.light_mummy.LightMummyRenderer;
import com.ricky.terrariamod.entity.monster.zombie_type.mummy.MummyRenderer;
import com.ricky.terrariamod.entity.monster.zombie_type.spore_zombie.SporeZombieRenderer;
import com.ricky.terrariamod.entity.projectile.ammo.musket_ball.MusketBallRenderer;
import com.ricky.terrariamod.entity.projectile.ammo.rocket.RocketRenderer;
import com.ricky.terrariamod.entity.projectile.magic.MagicBallModel;
import com.ricky.terrariamod.entity.projectile.magic.MagicBallRenderer;
import com.ricky.terrariamod.platform.services.ForgeKeyBindHelper;
import com.ricky.terrariamod.util.KeyBindings;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModLifecycleEvents  {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.ANGRY_BONES.get(), AngryBonesRenderer::new);
        EntityRenderers.register(ModEntities.DARK_CASTER.get(), DarkCasterRenderer::new);

        EntityRenderers.register(ModEntities.MUMMY.get(), MummyRenderer::new);
        EntityRenderers.register(ModEntities.BLOOD_MUMMY.get(), BloodMummyRenderer::new);
        EntityRenderers.register(ModEntities.DARK_MUMMY.get(), DarkMummyRenderer::new);
        EntityRenderers.register(ModEntities.LIGHT_MUMMY.get(), LightMummyRenderer::new);
        EntityRenderers.register(ModEntities.SPORE_ZOMBIE.get(), SporeZombieRenderer::new);

        EntityRenderers.register(ModEntities.ICE_SLIME.get(), IceSlimeRenderer::new);
        EntityRenderers.register(ModEntities.SAND_SLIME.get(), SandSlimeRenderer::new);
        EntityRenderers.register(ModEntities.JUNGLE_SLIME.get(), JungleSlimeRenderer::new);
        EntityRenderers.register(ModEntities.DUNGEON_SLIME.get(), DungeonSlimeRenderer::new);
        EntityRenderers.register(ModEntities.CORRUPT_SLIME.get(), CorruptSlimeRenderer::new);
        EntityRenderers.register(ModEntities.CRIM_SLIME.get(), CrimSlimeRenderer::new);

        EntityRenderers.register(ModEntities.LAVA_BAT.get(), LavaBatRenderer::new);
        EntityRenderers.register(ModEntities.ICE_BAT.get(), IceBatRenderer::new);
        EntityRenderers.register(ModEntities.JUNGLE_BAT.get(), JungleBatRenderer::new);
        EntityRenderers.register(ModEntities.ILLUMINANT_BAT.get(), IlluminantBatRenderer::new);

        EntityRenderers.register(ModEntities.DEMON_EYE.get(), DemonEyeRenderer::new);
        EntityRenderers.register(ModEntities.CRIMERA.get(), CrimeraRenderer::new);
        EntityRenderers.register(ModEntities.EATER_OU_SOUL.get(), EaterOfSoulRenderer::new);
        EntityRenderers.register(ModEntities.CURSED_SKULL.get(), CursedSkullRenderer::new);

        EntityRenderers.register(ModEntities.EYE_OF_CTHULHU.get(), EyeOfCthulhuRenderer::new);

        EntityRenderers.register(ModEntities.MAGIC_BALL.get(), MagicBallRenderer::new);
        EntityRenderers.register(ModEntities.DARK_CASTER_BALL.get(), DarkCasterBallRenderer::new);
        EntityRenderers.register(ModEntities.ROCKET.get(), RocketRenderer::new);
        EntityRenderers.register(ModEntities.MUSKET_BALL.get(), MusketBallRenderer::new);
    }
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DemonEyeModel.LAYER_LOCATION, DemonEyeModel::createBodyLayer);
        event.registerLayerDefinition(CrimeraModel.LAYER_LOCATION, CrimeraModel::createBodyLayer);
        event.registerLayerDefinition(EaterOfSoulModel.LAYER_LOCATION, EaterOfSoulModel::createBodyLayer);
        event.registerLayerDefinition(CursedSkullModel.LAYER_LOCATION, CursedSkullModel::createBodyLayer);
        event.registerLayerDefinition(EyeOfCthulhuModelOne.LAYER_LOCATION, EyeOfCthulhuModelOne::createBodyLayer);
        event.registerLayerDefinition(EyeOfCthulhuModelTwo.LAYER_LOCATION, EyeOfCthulhuModelTwo::createBodyLayer);

        event.registerLayerDefinition(MagicBallModel.LAYER_LOCATION, MagicBallModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        KeyBindings.init(new ForgeKeyBindHelper());
        event.register(KeyBindings.RELOAD_KEY);
    }
}
