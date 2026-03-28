package com.ricky.terrariamod.entity.monster.flying_type.giant_cursed_skull;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

/**
 * GiantCursedSkull Renderer
 * hitbox: 2.0 x 2.0
 * scale(4.0) でモデルを hitbox サイズに拡大
 * 計算: -(1.501 - hitbox_height/2) / scale = -(1.501 - 1.0) / 4.0 ≈ -0.125
 */
public class GiantCursedSkullRenderer extends MobRenderer<GiantCursedSkullEntity, GiantCursedSkullModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");

    public GiantCursedSkullRenderer(EntityRendererProvider.Context context) {
        super(context, new GiantCursedSkullModel(context.bakeLayer(GiantCursedSkullModel.LAYER_LOCATION)), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(GiantCursedSkullEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(GiantCursedSkullEntity entity, float entityYaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        float scale = 4.0F;
        poseStack.scale(scale, scale, scale);
        poseStack.translate(0.0, -1.501, 0.0);

        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
        poseStack.popPose();
    }
}
