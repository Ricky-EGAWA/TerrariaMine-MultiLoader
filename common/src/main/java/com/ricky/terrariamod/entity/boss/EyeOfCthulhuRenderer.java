package com.ricky.terrariamod.entity.boss;

import com.mojang.blaze3d.vertex.PoseStack;
import com.ricky.terrariamod.Constants;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class EyeOfCthulhuRenderer extends MobRenderer<EyeOfCthulhuEntity, EyeOfCthulhuModelBase<EyeOfCthulhuEntity>> {
    private final EyeOfCthulhuModelOne<EyeOfCthulhuEntity> modelPhaseOne;
    private final EyeOfCthulhuModelTwo<EyeOfCthulhuEntity> modelPhaseTwo;

    public EyeOfCthulhuRenderer(EntityRendererProvider.Context context) {
        super(context, new EyeOfCthulhuModelOne<>(context.bakeLayer(EyeOfCthulhuModelOne.LAYER_LOCATION)), 0.5f);
        this.modelPhaseOne = new EyeOfCthulhuModelOne<>(context.bakeLayer(EyeOfCthulhuModelOne.LAYER_LOCATION));
        this.modelPhaseTwo = new EyeOfCthulhuModelTwo<>(context.bakeLayer(EyeOfCthulhuModelTwo.LAYER_LOCATION));
    }

    @Override
    public void render(EyeOfCthulhuEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int packedLight) {
        // モデルの切り替え（Forgeでは model フィールドが final のため、draw メソッドでモデルを直接選択する必要がある）
        if (entity.getCurrentPhase() == 2) {
            this.model = modelPhaseTwo;
        } else {
            this.model = modelPhaseOne;
        }

        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(EyeOfCthulhuEntity entity) {
        return entity.getCurrentPhase() == 1
                ? new ResourceLocation(Constants.MOD_ID, "textures/entity/eye_of_cthulhu.png")
                : new ResourceLocation(Constants.MOD_ID, "textures/entity/eye_of_cthulhu2.png");
    }
}
