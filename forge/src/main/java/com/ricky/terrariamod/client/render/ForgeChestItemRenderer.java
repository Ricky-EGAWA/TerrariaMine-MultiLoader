package com.ricky.terrariamod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class ForgeChestItemRenderer extends BlockEntityWithoutLevelRenderer {
    public static final ForgeChestItemRenderer INSTANCE = new ForgeChestItemRenderer();

    public ForgeChestItemRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack,
                             MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ChestItemRenderer.getInstance().renderByItem(stack, displayContext, poseStack, bufferSource, packedLight, packedOverlay);
    }
}
