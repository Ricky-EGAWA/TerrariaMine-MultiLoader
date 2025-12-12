package com.ricky.terrariamod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.entity.LockedGoldenChestBlockEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class LockedGoldenChestBlockEntityRenderer implements BlockEntityRenderer<LockedGoldenChestBlockEntity> {
    // Single chest model parts
    private final ModelPart singleChestLid;
    private final ModelPart singleChestBase;
    private final ModelPart singleChestLatch;

    // Double chest left model parts
    private final ModelPart doubleChestLeftLid;
    private final ModelPart doubleChestLeftBase;
    private final ModelPart doubleChestLeftLatch;

    // Double chest right model parts
    private final ModelPart doubleChestRightLid;
    private final ModelPart doubleChestRightBase;
    private final ModelPart doubleChestRightLatch;

    // Textures
    public static final Material LOCKED_GOLDEN_CHEST_LOCATION = new Material(
            Sheets.CHEST_SHEET,
            new ResourceLocation(Constants.MOD_ID, "entity/chest/locked_golden_chest")
    );

    public static final Material LOCKED_GOLDEN_CHEST_LEFT_LOCATION = new Material(
            Sheets.CHEST_SHEET,
            new ResourceLocation(Constants.MOD_ID, "entity/chest/locked_golden_chest_left")
    );

    public static final Material LOCKED_GOLDEN_CHEST_RIGHT_LOCATION = new Material(
            Sheets.CHEST_SHEET,
            new ResourceLocation(Constants.MOD_ID, "entity/chest/locked_golden_chest_right")
    );

    public LockedGoldenChestBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        // Single chest model
        ModelPart singleChestModel = context.bakeLayer(ModelLayers.CHEST);
        this.singleChestBase = singleChestModel.getChild("bottom");
        this.singleChestLid = singleChestModel.getChild("lid");
        this.singleChestLatch = singleChestModel.getChild("lock");

        // Double chest left model
        ModelPart doubleChestLeftModel = context.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT);
        this.doubleChestLeftBase = doubleChestLeftModel.getChild("bottom");
        this.doubleChestLeftLid = doubleChestLeftModel.getChild("lid");
        this.doubleChestLeftLatch = doubleChestLeftModel.getChild("lock");

        // Double chest right model
        ModelPart doubleChestRightModel = context.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT);
        this.doubleChestRightBase = doubleChestRightModel.getChild("bottom");
        this.doubleChestRightLid = doubleChestRightModel.getChild("lid");
        this.doubleChestRightLatch = doubleChestRightModel.getChild("lock");
    }

    @Override
    public void render(LockedGoldenChestBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState blockState = blockEntity.getBlockState();

        poseStack.pushPose();

        // Get rotation from facing direction
        float rotation = 0.0F;
        if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            Direction direction = blockState.getValue(BlockStateProperties.HORIZONTAL_FACING);
            rotation = direction.toYRot();
        }

        // Apply transformations
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(-rotation));
        poseStack.translate(-0.5F, -0.5F, -0.5F);

        // Always closed (locked chest cannot be opened)
        float openness = 0.0F;

        // Render single chest (for now, double chest support can be added when block supports it)
        Material material = LOCKED_GOLDEN_CHEST_LOCATION;
        VertexConsumer vertexConsumer = material.buffer(bufferSource, RenderType::entityCutout);

        this.renderChest(poseStack, vertexConsumer, this.singleChestLid, this.singleChestLatch,
                this.singleChestBase, openness, packedLight, packedOverlay);

        poseStack.popPose();
    }

    private void renderChest(PoseStack poseStack, VertexConsumer vertexConsumer, ModelPart lid, ModelPart latch,
                             ModelPart base, float openness, int packedLight, int packedOverlay) {
        // Apply rotation to lid and latch based on openness (always 0 for locked chest)
        lid.xRot = -(openness * ((float) Math.PI / 2F));
        latch.xRot = lid.xRot;

        // Render all parts
        lid.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        latch.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        base.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
