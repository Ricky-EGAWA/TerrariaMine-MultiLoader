package com.ricky.terrariamod.entity.projectile.magic;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ricky.terrariamod.Constants;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;

public class MagicBallRenderer extends EntityRenderer<MagicBallEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/projectiles/magic_ball.png");

    private final MagicBallModel model;

    public MagicBallRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new MagicBallModel(context.bakeLayer(MagicBallModel.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull MagicBallEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        // モデルの回転などの処理（必要に応じて調整）
        poseStack.translate(0.0D, 0.0D, 0.0D);
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.scale(-1.0F, -1.0F, 1.0F);

        VertexConsumer vertexConsumer = buffer.getBuffer(model.renderType(getTextureLocation(entity)));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull MagicBallEntity entity) {
        return TEXTURE;
    }
}
