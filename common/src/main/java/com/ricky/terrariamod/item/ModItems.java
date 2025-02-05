package com.ricky.terrariamod.item;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.item.tool.ModAxeItem;
import com.ricky.terrariamod.item.tool.ModPickaxeItem;
import com.ricky.terrariamod.registry.RegistryObject;
import com.ricky.terrariamod.registry.RegistryProvider;
import net.minecraft.world.item.*;
import net.minecraft.core.registries.Registries;

public class ModItems {
    // RegistryProvider<Item> を使用してアイテムを登録
    public static final RegistryProvider<Item> ITEMS =
            RegistryProvider.get(Registries.ITEM, Constants.MOD_ID);

    // インゴット & 素材アイテム
    public static final RegistryObject<Item> SPIDER_FANG = ITEMS.register("spider_fang",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_INGOT = ITEMS.register("orichalcum_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ADAMANTITE_INGOT = ITEMS.register("adamantite_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HELLSTONE_INGOT = ITEMS.register("hellstone_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COBALT_RAW = ITEMS.register("cobalt_raw",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_RAW = ITEMS.register("orichalcum_raw",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ADAMANTITE_RAW = ITEMS.register("adamantite_raw",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HELLSTONE_RAW = ITEMS.register("hellstone_raw",
            () -> new Item(new Item.Properties()));

    // ツール & 武器
    public static final RegistryObject<Item> COBALT_PICKAXE = ITEMS.register("cobalt_pickaxe",
            () -> new ModPickaxeItem(ModToolMaterial.COBALT_INGOT, 2, -3f, new Item.Properties()));
    public static final RegistryObject<Item> COBALT_AXE = ITEMS.register("cobalt_axe",
            () -> new ModAxeItem(ModToolMaterial.COBALT_INGOT, 7, -3f, new Item.Properties()));
    public static final RegistryObject<Item> COBALT_SHOVEL = ITEMS.register("cobalt_shovel",
            () -> new ShovelItem(ModToolMaterial.COBALT_INGOT, 1, -3f, new Item.Properties()));
    public static final RegistryObject<Item> COBALT_SWORD = ITEMS.register("cobalt_sword",
            () -> new SwordItem(ModToolMaterial.COBALT_INGOT, 5, -2.4f, new Item.Properties()));

    public static final RegistryObject<Item> ORICHALCUM_PICKAXE = ITEMS.register("orichalcum_pickaxe",
            () -> new ModPickaxeItem(ModToolMaterial.ORICHALCUM_INGOT, 2, -3f, new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_AXE = ITEMS.register("orichalcum_axe",
            () -> new ModAxeItem(ModToolMaterial.ORICHALCUM_INGOT, 7, -3f, new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_SHOVEL = ITEMS.register("orichalcum_shovel",
            () -> new ShovelItem(ModToolMaterial.ORICHALCUM_INGOT, 1, -3f, new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_SWORD = ITEMS.register("orichalcum_sword",
            () -> new SwordItem(ModToolMaterial.ORICHALCUM_INGOT, 5, -2.4f, new Item.Properties()));

    public static final RegistryObject<Item> ADAMANTITE_PICKAXE = ITEMS.register("adamantite_pickaxe",
            () -> new ModPickaxeItem(ModToolMaterial.ADAMANTITE_INGOT, 2, -3f, new Item.Properties()));
    public static final RegistryObject<Item> ADAMANTITE_AXE = ITEMS.register("adamantite_axe",
            () -> new ModAxeItem(ModToolMaterial.ADAMANTITE_INGOT, 7, -3f, new Item.Properties()));
    public static final RegistryObject<Item> ADAMANTITE_SHOVEL = ITEMS.register("adamantite_shovel",
            () -> new ShovelItem(ModToolMaterial.ADAMANTITE_INGOT, 1, -3f, new Item.Properties()));
    public static final RegistryObject<Item> ADAMANTITE_SWORD = ITEMS.register("adamantite_sword",
            () -> new SwordItem(ModToolMaterial.ADAMANTITE_INGOT, 5, -2.4f, new Item.Properties()));

    public static final RegistryObject<Item> HELLSTONE_PICKAXE = ITEMS.register("hellstone_pickaxe",
            () -> new ModPickaxeItem(ModToolMaterial.HELLSTONE_INGOT, 2, -3f, new Item.Properties()));
    public static final RegistryObject<Item> HELLSTONE_AXE = ITEMS.register("hellstone_axe",
            () -> new ModAxeItem(ModToolMaterial.HELLSTONE_INGOT, 7, -3f, new Item.Properties()));
    public static final RegistryObject<Item> HELLSTONE_SHOVEL = ITEMS.register("hellstone_shovel",
            () -> new ShovelItem(ModToolMaterial.HELLSTONE_INGOT, 1, -3f, new Item.Properties()));
    public static final RegistryObject<Item> HELLSTONE_SWORD = ITEMS.register("hellstone_sword",
            () -> new SwordItem(ModToolMaterial.HELLSTONE_INGOT, 5, -2.4f, new Item.Properties()));

//    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
//            () -> new MetalDetectorItem(new Item.Properties().durability(64)));

    // 登録メソッド
    public static void register() {
    }
}
