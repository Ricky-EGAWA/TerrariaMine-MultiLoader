package com.ricky.terrariamod;

import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.entity.ModEntities;
import com.ricky.terrariamod.entity.monster.zombie_type.blood_mummy.BloodMummyRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
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
//        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOWING_MUSHROOM.get(), RenderType.cutout());
//        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_GLOWING_MUSHROOM.get(), RenderType.cutout());
        //tree
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EBON_LEAVES.get(), RenderType.cutout());
//        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EBON_SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRIM_LEAVES.get(), RenderType.cutout());
//        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRIM_SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEARL_LEAVES.get(), RenderType.cutout());
//        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEARL_SAPLING.get(), RenderType.cutout());
        //endregion

        EntityRendererRegistry.register(ModEntities.BLOOD_MUMMY.get(), BloodMummyRenderer::new);
    }
}