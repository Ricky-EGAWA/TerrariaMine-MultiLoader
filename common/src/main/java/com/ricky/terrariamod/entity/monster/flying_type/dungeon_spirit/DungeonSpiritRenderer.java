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
 * Dungeon Spirit Renderer（第4版）
 * 🔥🔥🔥 (上)
 * ( o o ) (下・球体)
 *
 * モデル: 球体中心 Y=0 を基準に炎が上、球が下
 * hitbox: 0.6 x 0.6
 * scale = 0.6 で hitbox に合わせる（球体の 8units=0.5blocks → ×1.2 で 0.6blocks）
 * translate Y = -1.501 (MobRenderer デフォルト固定値)
 */
public class DungeonSpiritRenderer extends MobRenderer<DungeonSpiritEntity, DungeonSpiritModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("terrariamod", "textures/entity/dungeon_spirit.png");

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

        // 球体 8 units = 0.5 blocks → hitbox 0.6 blocks に合わせる → scale = 1.2
        // 炎はそのスケールで上に伸びる
        float scale = 1.2F;
        poseStack.scale(scale, scale, scale);
        poseStack.translate(0.0, -1.501, 0.0);

        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
        poseStack.popPose();
    }

    @Override
    protected @Nullable RenderType getRenderType(@NotNull DungeonSpiritEntity entity,
                                                  boolean bodyVisible, boolean translucent, boolean glowing) {
        // 半透明レンダリング（幽霊＆炎の透明感）
        return RenderType.entityTranslucentCull(TEXTURE);
    }
}
