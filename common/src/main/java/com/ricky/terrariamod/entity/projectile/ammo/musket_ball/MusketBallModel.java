package com.ricky.terrariamod.entity.projectile.ammo.musket_ball;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.projectile.AbstractArrow;
import com.mojang.math.Axis;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public abstract class MusketBallModel<T extends AbstractArrow> extends EntityRenderer<T> {

    public MusketBallModel(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(T entity, float entityYaw, float partialTicks, PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));

        float dot = 0.03125F;
        float shake = (float)entity.shakeTime - partialTicks;
        if (shake > 0.0F) {
            float shakeAngle = -Mth.sin(shake * 3.0F) * shake;
            poseStack.mulPose(Axis.ZP.rotationDegrees(shakeAngle));
        }

        poseStack.mulPose(Axis.XP.rotationDegrees(45.0F));
        poseStack.scale(0.05625F, 0.05625F, 0.05625F);
        poseStack.translate(-4.0F, 0.0F, 0.0F);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(getTextureLocation(entity)));
        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix4f = pose.pose();
        Matrix3f normalMatrix = pose.normal();

        // main body faces
        this.vertex(matrix4f, normalMatrix, vertexConsumer, 0, -1, -1, 0*dot, 0*dot, -1, 0, 0, packedLight);
        this.vertex(matrix4f, normalMatrix, vertexConsumer, 0, -1, 1, 0*dot, 12*dot, -1, 0, 0, packedLight);
        this.vertex(matrix4f, normalMatrix, vertexConsumer, 0, 1, 1, 12*dot, 12*dot, -1, 0, 0, packedLight);
        this.vertex(matrix4f, normalMatrix, vertexConsumer, 0, 1, -1, 12*dot, 0*dot, -1, 0, 0, packedLight);
        this.vertex(matrix4f, normalMatrix, vertexConsumer, 0, 1, -1, 0*dot, 0*dot, 1, 0, 0, packedLight);
        this.vertex(matrix4f, normalMatrix, vertexConsumer, 0, 1, 1, 0*dot, 12*dot, 1, 0, 0, packedLight);
        this.vertex(matrix4f, normalMatrix, vertexConsumer, 0, -1, 1, 12*dot, 12*dot, 1, 0, 0, packedLight);
        this.vertex(matrix4f, normalMatrix, vertexConsumer, 0, -1, -1, 12*dot, 0*dot, 1, 0, 0, packedLight);

        // rotating faces
        for (int i = 0; i < 4; ++i) {
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            this.vertex(matrix4f, normalMatrix, vertexConsumer, -1, -1, 0, 0*dot, 0*dot, 0, 1, 0, packedLight);
            this.vertex(matrix4f, normalMatrix, vertexConsumer, 1, -1, 0, 0*dot, 12*dot, 0, 1, 0, packedLight);
            this.vertex(matrix4f, normalMatrix, vertexConsumer, 1, 1, 0, 12*dot, 12*dot, 0, 1, 0, packedLight);
            this.vertex(matrix4f, normalMatrix, vertexConsumer, -1, 1, 0, 12*dot, 0*dot, 0, 1, 0, packedLight);
        }

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    public void vertex(Matrix4f positionMatrix, Matrix3f normalMatrix, VertexConsumer vertexConsumer,
                       int x, int y, int z, float u, float v,
                       int normalX, int normalZ, int normalY, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, z)
                      .color(255, 255, 255, 255)
                      .uv(u, v)
                      .overlayCoords(OverlayTexture.NO_OVERLAY)
                      .uv2(light)
                      .normal(normalMatrix, normalX, normalY, normalZ)
                      .endVertex();
    }
}
