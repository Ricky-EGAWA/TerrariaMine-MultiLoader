package com.ricky.terrariamod.entity.monster.slime_type.corrupt_slime;

import com.ricky.terrariamod.Constants;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Slime;

public class CorruptSlimeRenderer extends SlimeRenderer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID,"textures/entity/corrupt_slime.png");
    public CorruptSlimeRenderer(EntityRendererProvider.Context $$0) {
        super($$0);
    }
    public ResourceLocation getTextureLocation(Slime pEntity) {
        return TEXTURE;
    }
}
