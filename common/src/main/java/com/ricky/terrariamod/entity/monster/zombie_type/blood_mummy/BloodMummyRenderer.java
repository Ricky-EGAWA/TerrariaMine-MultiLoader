package com.ricky.terrariamod.entity.monster.zombie_type.blood_mummy;

import com.ricky.terrariamod.Constants;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class BloodMummyRenderer extends ZombieRenderer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID,"textures/entity/blood_mummy.png");
    public BloodMummyRenderer(EntityRendererProvider.Context $$0) {
        super($$0);
    }
    public ResourceLocation getTextureLocation(Zombie pEntity) {
        return TEXTURE;
    }
}
