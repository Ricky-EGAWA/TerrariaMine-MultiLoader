package com.ricky.terrariamod.client.render;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ChestItemClientExtensions implements IClientItemExtensions {
    public static final ChestItemClientExtensions INSTANCE = new ChestItemClientExtensions();

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return ForgeChestItemRenderer.INSTANCE;
    }
}
