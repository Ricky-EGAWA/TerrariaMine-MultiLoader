package com.ricky.terrariamod.entity.monster.bat_type.lava_bat;

import com.ricky.terrariamod.Constants;
import net.minecraft.client.renderer.entity.BatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ambient.Bat;
import org.jetbrains.annotations.NotNull;

public class LavaBatRenderer extends BatRenderer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID,"textures/entity/lava_bat.png");
    public LavaBatRenderer(EntityRendererProvider.Context $$0) {
        super($$0);
    }
    public @NotNull ResourceLocation getTextureLocation(@NotNull Bat pEntity) {
        return TEXTURE;
    }
}
