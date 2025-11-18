package com.ricky.terrariamod.entity.monster.slime_type.illuminantslime;

import com.ricky.terrariamod.Constants;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Slime;
import org.jetbrains.annotations.NotNull;

public class IlluminantSlimeRenderer extends SlimeRenderer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID,"textures/entity/illuminant_slime.png");
    public IlluminantSlimeRenderer(EntityRendererProvider.Context $$0) {
        super($$0);
    }
    public @NotNull ResourceLocation getTextureLocation(@NotNull Slime pEntity) {
        return TEXTURE;
    }
}
