package com.ricky.terrariamod.entity.monster.skeleton_type.diabolist;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.entity.projectile.magic.MagicBallModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * DiabolistInfernoBall のレンダラー
 * DarkCasterBall と同じモデルを使用し、橙色テクスチャで上書き予定
 * （テクスチャがない場合は magic_ball を流用）
 */
public class DiabolistInfernoBallRenderer extends EntityRenderer<DiabolistInfernoBallEntity> {
    // テクスチャは magic_ball を一時的に流用（専用テクスチャがあれば差し替え）
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/projectiles/magic_ball.png");

    private final MagicBallModel model;

    public DiabolistInfernoBallRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new MagicBallModel(context.bakeLayer(MagicBallModel.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull DiabolistInfernoBallEntity entity, float entityYaw, float partialTicks,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        poseStack.translate(0.0D, 0.0D, 0.0D);
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.scale(-1.0F, -1.0F, 1.0F);

        VertexConsumer vertexConsumer = buffer.getBuffer(model.renderType(getTextureLocation(entity)));
        // 橙色のティント (R=1.0, G=0.5, B=0.0)
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0f, 0.5f, 0.0f, 1.0f);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DiabolistInfernoBallEntity entity) {
        return TEXTURE;
    }
}
