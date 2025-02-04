package com.ricky.terrariamod.item;

import com.ricky.terrariamod.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static ResourceLocation getIdentifier() {
        return Constants.locate(Constants.MOD_ID + "_tab");
    }

    public static Component getDisplayName() {
        return Component.translatable("itemGroup." + ModCreativeTabs.getIdentifier().toLanguageKey());
    }

    public static ItemStack getIcon() {
        return ModItems.COBALT_INGOT.get().getDefaultInstance();
    }
}
