package com.ricky.terrariamod.entity.monster.flying_type.dungeon_spirit;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * DungeonSpirit Renderer
 * hitbox: 0.6 x 0.6
 * scale(1.2) でモデルを hitbox サイズに拡大
 * 計算: -(1.501 - hitbox_height/2) / scale = -(1.501 - 0.3) / 1.2 ≈ -1.001
 * 半透明レンダリングで幽霊感を演出
 */
public class DungeonSpiritRenderer extends MobRenderer<DungeonSpiritEntity, DungeonSpiritModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");

    public DungeonSpiritRenderer(EntityRendererProvider.Context context) {
        super(context, new DungeonSpiritModel(context.bakeLayer(DungeonSpiritModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DungeonSpiritEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(@NotNull DungeonSpiritEntity entity, float entityYaw, float partialTick,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        float scale = 1.2F;
        poseStack.scale(scale, scale, scale);
        poseStack.translate(0.0, -1.501, 0.0);

        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
        poseStack.popPose();
    }

    @Override
    protected @Nullable RenderType getRenderType(@NotNull DungeonSpiritEntity entity,
                                                  boolean bodyVisible, boolean translucent, boolean glowing) {
        return RenderType.entityTranslucentCull(TEXTURE);
    }
}
