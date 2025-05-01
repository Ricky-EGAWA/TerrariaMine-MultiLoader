package com.ricky.terrariamod.entity.monster.flying_type.eater_of_soul;

import com.ricky.terrariamod.Constants;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class EaterOfSoulRenderer extends MobRenderer<EaterOfSoulEntity, EaterOfSoulModel<EaterOfSoulEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/eater_of_soul.png");

    public EaterOfSoulRenderer(EntityRendererProvider.Context context) {
        super(context, new EaterOfSoulModel<>(context.bakeLayer(EaterOfSoulModel.LAYER_LOCATION)), 0.6f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull EaterOfSoulEntity entity) {
        return TEXTURE;
    }
}
