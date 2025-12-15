package com.ricky.terrariamod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

/**
 * Shared rendering logic for chest items.
 * Platform-specific implementations will call this class.
 */
public class ChestItemRenderer {
    private static ChestItemRenderer instance;

    private ModelPart chestLid;
    private ModelPart chestBase;
    private ModelPart chestLatch;

    public static final Material GOLDEN_CHEST_LOCATION = new Material(
            Sheets.CHEST_SHEET,
            new ResourceLocation(Constants.MOD_ID, "entity/chest/golden_chest")
    );

    public static final Material LOCKED_GOLDEN_CHEST_LOCATION = new Material(
            Sheets.CHEST_SHEET,
            new ResourceLocation(Constants.MOD_ID, "entity/chest/locked_golden_chest")
    );

    private boolean initialized = false;

    public static ChestItemRenderer getInstance() {
        if (instance == null) {
            instance = new ChestItemRenderer();
        }
        return instance;
    }

    private ChestItemRenderer() {
    }

    private void ensureInitialized() {
        if (!initialized && Minecraft.getInstance().getEntityModels() != null) {
            try {
                ModelPart chestModel = Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.CHEST);
                this.chestBase = chestModel.getChild("bottom");
                this.chestLid = chestModel.getChild("lid");
                this.chestLatch = chestModel.getChild("lock");
                initialized = true;
            } catch (Exception e) {
                // Model not yet available, will try again later
            }
        }
    }

    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack,
                             MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ensureInitialized();
        if (!initialized) {
            return;
        }

        if (!(stack.getItem() instanceof BlockItem blockItem)) {
            return;
        }

        Block block = blockItem.getBlock();
        Material material;

        if (block == ModBlocks.GOLDEN_CHEST.get()) {
            material = GOLDEN_CHEST_LOCATION;
        } else if (block == ModBlocks.LOCKED_GOLDEN_CHEST.get()) {
            material = LOCKED_GOLDEN_CHEST_LOCATION;
        } else {
            return;
        }

        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);

        VertexConsumer vertexConsumer = material.buffer(bufferSource, RenderType::entityCutout);

        // Reset rotation for lid (closed state)
        chestLid.xRot = 0;
        chestLatch.xRot = 0;

        // Render all parts
        chestLid.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        chestLatch.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        chestBase.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        poseStack.popPose();
    }

    public boolean shouldRender(ItemStack stack) {
        if (!(stack.getItem() instanceof BlockItem blockItem)) {
            return false;
        }
        Block block = blockItem.getBlock();
        return block == ModBlocks.GOLDEN_CHEST.get() || block == ModBlocks.LOCKED_GOLDEN_CHEST.get();
    }
}
