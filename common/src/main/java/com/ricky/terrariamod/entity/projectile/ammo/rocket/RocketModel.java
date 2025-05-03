package com.ricky.terrariamod.entity.projectile.ammo.rocket;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public abstract class RocketModel<T extends AbstractArrow> extends EntityRenderer<T> {
    public RocketModel(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(T entity, float entityYaw, float partialTicks, PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        poseStack.mulPose(Axis.YP.rotationDegrees(interpolateRotation(entity.yRotO, entity.getYRot(), partialTicks) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(interpolateRotation(entity.xRotO, entity.getXRot(), partialTicks)));

        float shake = (float) entity.shakeTime - partialTicks;
        if (shake > 0.0F) {
            float angle = -net.minecraft.util.Mth.sin(shake * 3.0F) * shake;
            poseStack.mulPose(Axis.ZP.rotationDegrees(angle));
        }

        poseStack.mulPose(Axis.XP.rotationDegrees(45.0F));
        poseStack.scale(0.05625F, 0.05625F, 0.05625F);
        poseStack.translate(-4.0F, 0.0F, 0.0F);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(getTextureLocation(entity)));
        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix4f = pose.pose();
        Matrix3f matrix3f = pose.normal();

        // 両面 (front + back)
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, -3, -3, 0.0F, 0.21875F, -1, 0, 0, packedLight);
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, -3, 3, 0.21875F, 0.21875F, -1, 0, 0, packedLight);
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, 3, 3, 0.21875F, 0.4375F, -1, 0, 0, packedLight);
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, 3, -3, 0.0F, 0.4375F, -1, 0, 0, packedLight);

        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, 3, -3, 0.0F, 0.21875F, 1, 0, 0, packedLight);
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, 3, 3, 0.21875F, 0.21875F, 1, 0, 0, packedLight);
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, -3, 3, 0.21875F, 0.4375F, 1, 0, 0, packedLight);
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, -3, -3, 0.0F, 0.4375F, 1, 0, 0, packedLight);

        for (int i = 0; i < 4; ++i) {
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            this.vertex(matrix4f, matrix3f, vertexConsumer, 0, -3, 0, 0.0F, 0.0F, 0, 1, 0, packedLight);
            this.vertex(matrix4f, matrix3f, vertexConsumer, 10, -3, 0, 0.3125F, 0.0F, 0, 1, 0, packedLight);
            this.vertex(matrix4f, matrix3f, vertexConsumer, 10, 3, 0, 0.3125F, 0.21875F, 0, 1, 0, packedLight);
            this.vertex(matrix4f, matrix3f, vertexConsumer, 0, 3, 0, 0.0F, 0.21875F, 0, 1, 0, packedLight);
        }

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    protected void vertex(Matrix4f pose, Matrix3f normal, VertexConsumer consumer, int x, int y, int z, float u, float v, int normalX, int normalY, int normalZ, int light) {
        consumer.vertex(pose, (float)x, (float)y, (float)z)
            .color(255, 255, 255, 255)
            .uv(u, v)
            .overlayCoords(OverlayTexture.NO_OVERLAY)
            .uv2(light)
            .normal(normal, (float)normalX, (float)normalY, (float)normalZ)
            .endVertex();
    }

    private static float interpolateRotation(float prev, float current, float partialTicks) {
        float f = current - prev;
        while (f < -180.0F) f += 360.0F;
        while (f >= 180.0F) f -= 360.0F;
        return prev + partialTicks * f;
    }
}
