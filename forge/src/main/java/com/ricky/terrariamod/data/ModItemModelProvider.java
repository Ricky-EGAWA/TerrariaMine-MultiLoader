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
        simpleItem(ModItems.METAL_DETECTOR);
        simpleItem(ModItems.INFINITE_LAVA_BUCKET);
        simpleItem(ModItems.INFINITE_WATER_BUCKET);
        simpleItem(ModItems.MAGIC_MIRROR);
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
        handheldItem(ModItems.AMETHYST_STAFF);

        //region 防具
        simpleItem(ModItems.OAK_HELMET);
        simpleItem(ModItems.OAK_CHESTPLATE);
        simpleItem(ModItems.OAK_LEGGINGS);
        simpleItem(ModItems.OAK_BOOTS);

        simpleItem(ModItems.COPPER_HELMET);
        simpleItem(ModItems.COPPER_CHESTPLATE);
        simpleItem(ModItems.COPPER_LEGGINGS);
        simpleItem(ModItems.COPPER_BOOTS);

        simpleItem(ModItems.OBSIDIAN_HELMET);
        simpleItem(ModItems.OBSIDIAN_CHESTPLATE);
        simpleItem(ModItems.OBSIDIAN_LEGGINGS);
        simpleItem(ModItems.OBSIDIAN_BOOTS);

        simpleItem(ModItems.CACTUS_HELMET);
        simpleItem(ModItems.CACTUS_CHESTPLATE);
        simpleItem(ModItems.CACTUS_LEGGINGS);
        simpleItem(ModItems.CACTUS_BOOTS);

        simpleItem(ModItems.PUMPKIN_HELMET);
        simpleItem(ModItems.PUMPKIN_CHESTPLATE);
        simpleItem(ModItems.PUMPKIN_LEGGINGS);
        simpleItem(ModItems.PUMPKIN_BOOTS);

        simpleItem(ModItems.GLASS_HELMET);
        simpleItem(ModItems.NIGHT_HELMET);
        simpleItem(ModItems.COBALT_HELMET);
        simpleItem(ModItems.COBALT_CHESTPLATE);
        simpleItem(ModItems.COBALT_LEGGINGS);
        simpleItem(ModItems.COBALT_BOOTS);

        simpleItem(ModItems.ORICHALCUM_HELMET);
        simpleItem(ModItems.ORICHALCUM_CHESTPLATE);
        simpleItem(ModItems.ORICHALCUM_LEGGINGS);
        simpleItem(ModItems.ORICHALCUM_BOOTS);

        simpleItem(ModItems.ADAMANTITE_HELMET);
        simpleItem(ModItems.ADAMANTITE_CHESTPLATE);
        simpleItem(ModItems.ADAMANTITE_LEGGINGS);
        simpleItem(ModItems.ADAMANTITE_BOOTS);

        simpleItem(ModItems.HELLSTONE_HELMET);
        simpleItem(ModItems.HELLSTONE_CHESTPLATE);
        simpleItem(ModItems.HELLSTONE_LEGGINGS);
        simpleItem(ModItems.HELLSTONE_BOOTS);
        //endregion
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