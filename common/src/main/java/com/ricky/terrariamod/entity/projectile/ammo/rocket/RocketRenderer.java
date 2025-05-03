package com.ricky.terrariamod.entity.projectile.ammo.rocket;

import com.ricky.terrariamod.Constants;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RocketRenderer extends RocketModel<RocketEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/projectiles/rocket.png");

    public RocketRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull RocketEntity entity) {
        return TEXTURE;
    }
}
