package com.ricky.terrariamod.entity.monster.flying_type.cursed_skull;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CursedSkullRenderer extends MobRenderer<CursedSkullEntity, CursedSkullModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");

    public CursedSkullRenderer(EntityRendererProvider.Context context) {
        super(context, new CursedSkullModel(context.bakeLayer(CursedSkullModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(CursedSkullEntity entity) {
        return TEXTURE;
    }
}
