package com.ricky.terrariamod.entity.monster.flying_type.cursed_skull;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

/**
 * CursedSkull Renderer
 * hitbox: 0.8 x 0.8
 * MobRenderer はデフォルトで Y+1.501 オフセットを適用する
 * scale(1.6) でモデルを hitbox サイズに拡大し、
 * translate で MobRenderer のデフォルトオフセットを打ち消して hitbox 中心に合わせる
 * 計算: -(1.501 - hitbox_height/2) / scale = -(1.501 - 0.4) / 1.6 ≈ -0.688
 */
public class CursedSkullRenderer extends MobRenderer<CursedSkullEntity, CursedSkullModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");

    public CursedSkullRenderer(EntityRendererProvider.Context context) {
        super(context, new CursedSkullModel(context.bakeLayer(CursedSkullModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(CursedSkullEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(CursedSkullEntity entity, float entityYaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        float scale = 1.6F;
        poseStack.scale(scale, scale, scale);
        // MobRenderer の Y+1.501 を打ち消し、hitbox 中心に合わせる
        poseStack.translate(0.0, -1.501, 0.0);

        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
        poseStack.popPose();
    }
}
