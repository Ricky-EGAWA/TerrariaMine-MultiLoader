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
    private final ModelPart lid;
    private final ModelPart bottom;
    private final ModelPart lock;

    public static final Material LOCKED_GOLDEN_CHEST_LOCATION = new Material(
            Sheets.CHEST_SHEET,
            new ResourceLocation(Constants.MOD_ID, "entity/chest/locked_golden_chest")
    );

    public LockedGoldenChestBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart modelPart = context.bakeLayer(ModelLayers.CHEST);
        this.bottom = modelPart.getChild("bottom");
        this.lid = modelPart.getChild("lid");
        this.lock = modelPart.getChild("lock");
    }

    @Override
    public void render(LockedGoldenChestBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        BlockState blockState = blockEntity.getBlockState();
        float rotation = 0.0F;

        if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            Direction direction = blockState.getValue(BlockStateProperties.HORIZONTAL_FACING);
            rotation = direction.toYRot();
        }

        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(-rotation));
        poseStack.translate(-0.5F, -0.5F, -0.5F);

        // Always closed (openness = 0)
        float openness = 0.0F;

        Material material = LOCKED_GOLDEN_CHEST_LOCATION;
        VertexConsumer vertexConsumer = material.buffer(bufferSource, RenderType::entityCutout);

        this.render(poseStack, vertexConsumer, this.lid, this.lock, this.bottom, openness, packedLight, packedOverlay);

        poseStack.popPose();
    }

    private void render(PoseStack poseStack, VertexConsumer vertexConsumer, ModelPart lid, ModelPart lock,
                        ModelPart bottom, float openness, int packedLight, int packedOverlay) {
        lid.xRot = -(openness * ((float) Math.PI / 2F));
        lock.xRot = lid.xRot;
        lid.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        lock.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
