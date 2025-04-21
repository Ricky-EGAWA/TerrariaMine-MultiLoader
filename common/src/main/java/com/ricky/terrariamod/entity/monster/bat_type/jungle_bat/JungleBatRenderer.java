package com.ricky.terrariamod.entity.monster.bat_type.jungle_bat;

import com.ricky.terrariamod.Constants;
import net.minecraft.client.renderer.entity.BatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ambient.Bat;

public class JungleBatRenderer extends BatRenderer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID,"textures/entity/jungle_bat.png");
    public JungleBatRenderer(EntityRendererProvider.Context $$0) {
        super($$0);
    }
    public ResourceLocation getTextureLocation(Bat pEntity) {
        return TEXTURE;
    }
}
