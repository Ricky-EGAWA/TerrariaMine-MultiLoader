package com.ricky.terrariamod.data;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (ResourceLocation id : ModItems.ITEMS.keySet()) {
            simpleItem(id);
        }
    }

    private ItemModelBuilder simpleItem(ResourceLocation itemLocation) {
        return withExistingParent(itemLocation.getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(Constants.MOD_ID, "item/" + itemLocation.getPath()));
    }
}