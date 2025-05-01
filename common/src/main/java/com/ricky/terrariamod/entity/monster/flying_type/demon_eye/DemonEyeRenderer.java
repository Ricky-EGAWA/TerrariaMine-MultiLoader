package com.ricky.terrariamod.entity.monster.flying_type.demon_eye;

import com.ricky.terrariamod.Constants;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class DemonEyeRenderer extends MobRenderer<DemonEyeEntity, DemonEyeModel<DemonEyeEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/demon_eye.png");

    public DemonEyeRenderer(EntityRendererProvider.Context context) {
        super(context, new DemonEyeModel<>(context.bakeLayer(DemonEyeModel.LAYER_LOCATION)), 0.6f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DemonEyeEntity entity) {
        return TEXTURE;
    }
}
