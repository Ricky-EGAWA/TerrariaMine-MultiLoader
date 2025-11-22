package com.ricky.terrariamod.entity.monster.skeleton_type.angry_bones;

import com.mojang.blaze3d.vertex.PoseStack;
import com.ricky.terrariamod.Constants;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class AngryBonesRenderer extends HumanoidMobRenderer<Zombie, SkeletonModel<Zombie>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");

    public AngryBonesRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.SKELETON)), 0.5F);
        // 防具レイヤーを追加
        this.addLayer(new HumanoidArmorLayer<>(this,
                new SkeletonModel<>(context.bakeLayer(ModelLayers.SKELETON_INNER_ARMOR)),
                new SkeletonModel<>(context.bakeLayer(ModelLayers.SKELETON_OUTER_ARMOR)),
                context.getModelManager()));
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(Zombie entity, PoseStack poseStack, float partialTickTime) {
        poseStack.scale(1.0F, 1.0F, 1.0F);
    }
}
