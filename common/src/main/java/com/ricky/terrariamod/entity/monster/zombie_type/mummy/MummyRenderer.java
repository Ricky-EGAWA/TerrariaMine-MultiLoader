package com.ricky.terrariamod.entity.monster.zombie_type.mummy;

import com.ricky.terrariamod.Constants;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class MummyRenderer extends ZombieRenderer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID,"textures/entity/mummy.png");
    public MummyRenderer(EntityRendererProvider.Context $$0) {
        super($$0);
    }
    public ResourceLocation getTextureLocation(Zombie pEntity) {
        return TEXTURE;
    }
}
