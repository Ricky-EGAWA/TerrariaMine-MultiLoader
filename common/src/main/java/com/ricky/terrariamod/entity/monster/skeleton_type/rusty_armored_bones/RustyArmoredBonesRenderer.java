package com.ricky.terrariamod.entity.monster.skeleton_type.rusty_armored_bones;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Skeleton;

public class RustyArmoredBonesRenderer extends HumanoidMobRenderer<Skeleton, HumanoidModel<Skeleton>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");

    public RustyArmoredBonesRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.SKELETON)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this,
                new HumanoidModel<>(context.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)),
                new HumanoidModel<>(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)),
                context.getModelManager()));
    }

    @Override
    public ResourceLocation getTextureLocation(Skeleton entity) {
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(Skeleton entity) {
        return false;
    }
}
