package com.ricky.terrariamod.entity.boss;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.NotNull;

public abstract class EyeOfCthulhuModelBase<T extends EyeOfCthulhuEntity> extends EntityModel<T> {

    public EyeOfCthulhuModelBase() {
        // 共通の初期化処理（必要に応じて）
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // アニメーションのロジック（必要に応じてサブクラスで実装）
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        // モデルパーツの描画処理（ここではまだ何も描画していません）
    }

    /**
     * モデルのルートパーツ（必要な場合、サブクラスで返す）
     */
    public abstract ModelPart root();
}
