package com.ricky.terrariamod.entity.monster.flying_type.crimera;

import com.ricky.terrariamod.Constants;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CrimeraRenderer extends MobRenderer<CrimeraEntity, CrimeraModel<CrimeraEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/crimera.png");

    public CrimeraRenderer(EntityRendererProvider.Context context) {
        super(context, new CrimeraModel<>(context.bakeLayer(CrimeraModel.LAYER_LOCATION)), 0.6f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull CrimeraEntity entity) {
        return TEXTURE;
    }
}
