package com.ricky.terrariamod.data;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.item.ModItems;
import com.ricky.terrariamod.registry.RegistryObject;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // アイテムモデルの登録
        simpleItem(ModItems.COBALT_INGOT);
        simpleItem(ModItems.ORICHALCUM_INGOT);
        simpleItem(ModItems.ADAMANTITE_INGOT);
        simpleItem(ModItems.HELLSTONE_INGOT);
        simpleItem(ModItems.COBALT_RAW);
        simpleItem(ModItems.ORICHALCUM_RAW);
        simpleItem(ModItems.ADAMANTITE_RAW);
        simpleItem(ModItems.HELLSTONE_RAW);
        simpleItem(ModItems.SPIDER_FANG);
        // 他のアイテムを追加することができます

        handheldItem(ModItems.COBALT_PICKAXE);
        handheldItem(ModItems.COBALT_AXE);
        handheldItem(ModItems.COBALT_SHOVEL);
        handheldItem(ModItems.COBALT_SWORD);
        handheldItem(ModItems.ORICHALCUM_PICKAXE);
        handheldItem(ModItems.ORICHALCUM_AXE);
        handheldItem(ModItems.ORICHALCUM_SHOVEL);
        handheldItem(ModItems.ORICHALCUM_SWORD);
        handheldItem(ModItems.ADAMANTITE_PICKAXE);
        handheldItem(ModItems.ADAMANTITE_AXE);
        handheldItem(ModItems.ADAMANTITE_SHOVEL);
        handheldItem(ModItems.ADAMANTITE_SWORD);
        handheldItem(ModItems.HELLSTONE_PICKAXE);
        handheldItem(ModItems.HELLSTONE_AXE);
        handheldItem(ModItems.HELLSTONE_SHOVEL);
        handheldItem(ModItems.HELLSTONE_SWORD);

    }


    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Constants.MOD_ID,"item/" + item.getId().getPath()));
    }
    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Constants.MOD_ID,"item/" + item.getId().getPath()));
    }
}