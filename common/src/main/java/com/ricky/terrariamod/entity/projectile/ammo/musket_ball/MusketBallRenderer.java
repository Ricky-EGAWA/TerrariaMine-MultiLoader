package com.ricky.terrariamod.entity.projectile.ammo.musket_ball;

import com.ricky.terrariamod.Constants;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MusketBallRenderer extends MusketBallModel<MusketBallEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/projectiles/musket_ball.png");

    public MusketBallRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull MusketBallEntity entity) {
        return TEXTURE;
    }
}
