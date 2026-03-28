package com.ricky.terrariamod.entity.monster.flying_type.giant_cursed_skull;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GiantCursedSkullRenderer extends MobRenderer<GiantCursedSkullEntity, GiantCursedSkullModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");

    public GiantCursedSkullRenderer(EntityRendererProvider.Context context) {
        // shadowRadius を大きめに（体が大きい）
        super(context, new GiantCursedSkullModel(context.bakeLayer(GiantCursedSkullModel.LAYER_LOCATION)), 1.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(GiantCursedSkullEntity entity) {
        return TEXTURE;
    }
}
