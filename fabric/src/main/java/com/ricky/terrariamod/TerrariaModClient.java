package com.ricky.terrariamod;

import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.client.render.ManaHudOverlay;
import com.ricky.terrariamod.entity.ModEntities;
import com.ricky.terrariamod.entity.monster.bat_type.ice_bat.IceBatRenderer;
import com.ricky.terrariamod.entity.monster.bat_type.jungle_bat.JungleBatRenderer;
import com.ricky.terrariamod.entity.monster.bat_type.lava_bat.LavaBatRenderer;
import com.ricky.terrariamod.entity.monster.flying_type.crimera.CrimeraModel;
import com.ricky.terrariamod.entity.monster.flying_type.crimera.CrimeraRenderer;
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
import com.ricky.terrariamod.entity.monster.zombie_type.blood_mummy.BloodMummyRenderer;
import com.ricky.terrariamod.entity.monster.zombie_type.dark_mummy.DarkMummyRenderer;
import com.ricky.terrariamod.entity.monster.zombie_type.light_mummy.LightMummyRenderer;
import com.ricky.terrariamod.entity.monster.zombie_type.mummy.MummyRenderer;
import com.ricky.terrariamod.networking.ModPackets;
import com.ricky.terrariamod.platform.services.FabricKeyBindHelper;
import com.ricky.terrariamod.platform.services.FabricNetworkHelper;
import com.ricky.terrariamod.util.KeyBindings;
import com.ricky.terrariamod.util.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.renderer.RenderType;

public class TerrariaModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        //region 半透明ブロック
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEATH_WEED.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEATH_WEED.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SHIVER_THORN.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SHIVER_THORN.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VILE_MUSHROOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_VILE_MUSHROOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VICIOUS_MUSHROOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_VICIOUS_MUSHROOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOWING_MUSHROOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_GLOWING_MUSHROOM.get(), RenderType.cutout());
        //tree
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EBON_LEAVES.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EBON_SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRIM_LEAVES.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRIM_SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEARL_LEAVES.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEARL_SAPLING.get(), RenderType.cutout());
        //endregion

        //region エンティティ
        EntityRendererRegistry.register(ModEntities.MUMMY.get(), MummyRenderer::new);
        EntityRendererRegistry.register(ModEntities.LIGHT_MUMMY.get(), LightMummyRenderer::new);
        EntityRendererRegistry.register(ModEntities.BLOOD_MUMMY.get(), BloodMummyRenderer::new);
        EntityRendererRegistry.register(ModEntities.DARK_MUMMY.get(), DarkMummyRenderer::new);

        EntityRendererRegistry.register(ModEntities.ICE_SLIME.get(), IceSlimeRenderer::new);
        EntityRendererRegistry.register(ModEntities.SAND_SLIME.get(), SandSlimeRenderer::new);
        EntityRendererRegistry.register(ModEntities.JUNGLE_SLIME.get(), JungleSlimeRenderer::new);
        EntityRendererRegistry.register(ModEntities.DUNGEON_SLIME.get(), DungeonSlimeRenderer::new);
        EntityRendererRegistry.register(ModEntities.CORRUPT_SLIME.get(), CorruptSlimeRenderer::new);
        EntityRendererRegistry.register(ModEntities.CRIM_SLIME.get(), CrimSlimeRenderer::new);

        EntityRendererRegistry.register(ModEntities.LAVA_BAT.get(), LavaBatRenderer::new);
        EntityRendererRegistry.register(ModEntities.ICE_BAT.get(), IceBatRenderer::new);
        EntityRendererRegistry.register(ModEntities.JUNGLE_BAT.get(), JungleBatRenderer::new);

        EntityRendererRegistry.register(ModEntities.DEMON_EYE.get(), DemonEyeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(DemonEyeModel.LAYER_LOCATION, DemonEyeModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntities.CRIMERA.get(), CrimeraRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(CrimeraModel.LAYER_LOCATION, CrimeraModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntities.EATER_OU_SOUL.get(), EaterOfSoulRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(EaterOfSoulModel.LAYER_LOCATION, EaterOfSoulModel::createBodyLayer);
        //endregion

        KeyBindings.init(new FabricKeyBindHelper());
        ClientTickEvents.END_CLIENT_TICK.register(client -> KeyInputHandler.onClientTick());

        ModPackets.registerS2CPackets();
        ModPackets.registerC2SPackets();
        FabricNetworkHelper.initializeClientHandlers();

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> ManaHudOverlay.render(drawContext));
    }
}