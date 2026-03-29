package com.ricky.terrariamod.entity.monster.flying_type.dungeon_spirit;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

/**
 * Dungeon Spirit モデル（第4版）
 * 🔥🔥🔥  ← 3本の炎（上方向に揺れる）
 *  ( o o ) ← 丸い発光球体（3層で球形を模倣）
 *   \_/
 *
 * 上方に炎、下方に球体という構成
 * テクスチャ: dungeon_spirit.png (64x64)
 */
public class DungeonSpiritModel extends EntityModel<DungeonSpiritEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation("terrariamod", "dungeon_spirit"), "main");

    // 炎（球体の上に配置）
    private final ModelPart flameLeft;
    private final ModelPart flameMid;
    private final ModelPart flameRight;
    // 球体（3層で丸みを表現）
    private final ModelPart bodyTop;
    private final ModelPart bodyMid;
    private final ModelPart bodyBot;

    public DungeonSpiritModel(ModelPart root) {
        this.flameLeft  = root.getChild("flame_l");
        this.flameMid   = root.getChild("flame_m");
        this.flameRight = root.getChild("flame_r");
        this.bodyTop    = root.getChild("body_top");
        this.bodyMid    = root.getChild("body_mid");
        this.bodyBot    = root.getChild("body_bot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition parts = mesh.getRoot();

        // === 炎 (上方向、球体の上端から生える) ===
        // 短くした: 左右=5, 中央=7
        // texOffs: flame_l(0,0) flame_m(0,9) flame_r(0,18)

        // 左炎: 2x5x2 texOffs(0,0)
        parts.addOrReplaceChild("flame_l",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2),
                PartPose.offset(-2.5F, -8.0F, 0.0F));

        // 中炎: 2x7x2 texOffs(0,9) 最長
        parts.addOrReplaceChild("flame_m",
                CubeListBuilder.create()
                        .texOffs(0, 9)
                        .addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2),
                PartPose.offset(0.0F, -8.0F, 0.0F));

        // 右炎: 2x5x2 texOffs(0,18)
        parts.addOrReplaceChild("flame_r",
                CubeListBuilder.create()
                        .texOffs(0, 18)
                        .addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2),
                PartPose.offset(2.5F, -8.0F, 0.0F));

        // === 球体（3層で球形を模倣）===
        // body_top: 6x2x4 texOffs(0,27)
        parts.addOrReplaceChild("body_top",
                CubeListBuilder.create()
                        .texOffs(0, 27)
                        .addBox(-3.0F, -8.0F, -2.0F, 6, 2, 4),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // body_mid: 8x4x6 texOffs(0,31) 最大・目がある
        parts.addOrReplaceChild("body_mid",
                CubeListBuilder.create()
                        .texOffs(0, 31)
                        .addBox(-4.0F, -6.0F, -3.0F, 8, 4, 6),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // body_bot: 6x2x4 texOffs(0,42)
        parts.addOrReplaceChild("body_bot",
                CubeListBuilder.create()
                        .texOffs(0, 42)
                        .addBox(-3.0F, -2.0F, -2.0F, 6, 2, 4),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(DungeonSpiritEntity entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {

        // 球体は正面を向く
        this.bodyMid.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.bodyTop.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.bodyBot.yRot = netHeadYaw * ((float) Math.PI / 180F);

        // 全体の上下浮遊
        // 注意: ModelPart.y は PartPose.offset.y を上書きする
        // 球体: PartPose Y=0 なので bob をそのまま設定
        // 炎:   PartPose Y=-8 なので (-8 + bob) を設定して球体上端に合わせる
        float bob = (float) Math.sin(ageInTicks * 0.07F) * 0.7F;
        this.bodyTop.y    = bob;
        this.bodyMid.y    = bob;
        this.bodyBot.y    = bob;
        this.flameLeft.y  = bob - 8.0F;
        this.flameMid.y   = bob - 8.0F;
        this.flameRight.y = bob - 8.0F;

        // 炎が上方向に揺れる（根元から揺れる = zRot）
        // 位相差 2π/3 ≈ 2.09 で3本が独立して揺れる
        float t   = ageInTicks * 0.13F;
        float amp = 0.28F;

        this.flameLeft.zRot  = (float) Math.sin(t)           * amp;
        this.flameLeft.xRot  = (float) Math.sin(t + 0.5F)    * 0.10F;

        this.flameMid.zRot   = (float) Math.sin(t + 2.09F)   * amp;
        this.flameMid.xRot   = (float) Math.sin(t + 2.59F)   * 0.10F;

        this.flameRight.zRot = (float) Math.sin(t + 4.19F)   * amp;
        this.flameRight.xRot = (float) Math.sin(t + 4.69F)   * 0.10F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer,
                               int packedLight, int packedOverlay,
                               float red, float green, float blue, float alpha) {
        // 炎を先に描画（半透明の場合に球体が透けて見えないよう）
        this.flameLeft .render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.flameMid  .render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.flameRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        // 球体
        this.bodyTop   .render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodyMid   .render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodyBot   .render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
