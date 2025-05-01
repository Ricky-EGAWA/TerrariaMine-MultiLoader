package com.ricky.terrariamod.item;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.item.armor.ModArmorItem;
import com.ricky.terrariamod.item.armor.ModArmorMaterials;
import com.ricky.terrariamod.item.custom.InfiniteLavaBucketItem;
import com.ricky.terrariamod.item.custom.InfiniteWaterBucketItem;
import com.ricky.terrariamod.item.custom.MagicMirrorItem;
import com.ricky.terrariamod.item.custom.MetalDetectorItem;
import com.ricky.terrariamod.item.tool.ModAxeItem;
import com.ricky.terrariamod.item.tool.ModPickaxeItem;
import com.ricky.terrariamod.item.tool.ModToolMaterial;
import com.ricky.terrariamod.item.weapon.magic.AmethystStaffItem;
import com.ricky.terrariamod.registry.RegistryObject;
import com.ricky.terrariamod.registry.RegistryProvider;
import net.minecraft.world.item.*;
import net.minecraft.core.registries.Registries;

public class ModItems {
    // RegistryProvider<Item> を使用してアイテムを登録
    public static final RegistryProvider<Item> ITEMS =
            RegistryProvider.get(Registries.ITEM, Constants.MOD_ID);
    //region 特殊アイテム
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties()));
    public static final RegistryObject<Item> INFINITE_LAVA_BUCKET = ITEMS.register("infinite_lava_bucket",
            () -> new InfiniteLavaBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> INFINITE_WATER_BUCKET = ITEMS.register("infinite_water_bucket",
            () -> new InfiniteWaterBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_MIRROR = ITEMS.register("magic_mirror",
            () -> new MagicMirrorItem(new Item.Properties()));
    //endregion

    //region インゴット & 素材アイテム
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
    //endregion

    //region ツール & 武器
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

    public static final RegistryObject<Item> AMETHYST_STAFF = ITEMS.register("amethyst_staff",
            () -> new AmethystStaffItem(new Item.Properties()));
    //endregion

    //region 防具
    public static final RegistryObject<Item> OAK_HELMET = ITEMS.register("oak_helmet",
            () -> new ModArmorItem(ModArmorMaterials.OAK, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> OAK_CHESTPLATE = ITEMS.register("oak_chestplate",
            () -> new ArmorItem(ModArmorMaterials.OAK, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> OAK_LEGGINGS = ITEMS.register("oak_leggings",
            () -> new ArmorItem(ModArmorMaterials.OAK, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> OAK_BOOTS = ITEMS.register("oak_boots",
            () -> new ArmorItem(ModArmorMaterials.OAK, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> OBSIDIAN_HELMET = ITEMS.register("obsidian_helmet",
            () -> new ModArmorItem(ModArmorMaterials.OBSIDIAN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIAN_CHESTPLATE = ITEMS.register("obsidian_chestplate",
            () -> new ArmorItem(ModArmorMaterials.OBSIDIAN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIAN_LEGGINGS = ITEMS.register("obsidian_leggings",
            () -> new ArmorItem(ModArmorMaterials.OBSIDIAN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> OBSIDIAN_BOOTS = ITEMS.register("obsidian_boots",
            () -> new ArmorItem(ModArmorMaterials.OBSIDIAN, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> CACTUS_HELMET = ITEMS.register("cactus_helmet",
            () -> new ModArmorItem(ModArmorMaterials.CACTUS, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> CACTUS_CHESTPLATE = ITEMS.register("cactus_chestplate",
            () -> new ArmorItem(ModArmorMaterials.CACTUS, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> CACTUS_LEGGINGS = ITEMS.register("cactus_leggings",
            () -> new ArmorItem(ModArmorMaterials.CACTUS, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> CACTUS_BOOTS = ITEMS.register("cactus_boots",
            () -> new ArmorItem(ModArmorMaterials.CACTUS, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> PUMPKIN_HELMET = ITEMS.register("pumpkin_helmet",
            () -> new ModArmorItem(ModArmorMaterials.PUMPKIN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> PUMPKIN_CHESTPLATE = ITEMS.register("pumpkin_chestplate",
            () -> new ArmorItem(ModArmorMaterials.PUMPKIN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> PUMPKIN_LEGGINGS = ITEMS.register("pumpkin_leggings",
            () -> new ArmorItem(ModArmorMaterials.PUMPKIN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> PUMPKIN_BOOTS = ITEMS.register("pumpkin_boots",
            () -> new ArmorItem(ModArmorMaterials.PUMPKIN, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> GLASS_HELMET = ITEMS.register("glass_helmet",
            () -> new ModArmorItem(ModArmorMaterials.GLASS, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> NIGHT_HELMET = ITEMS.register("night_helmet",
            () -> new ModArmorItem(ModArmorMaterials.NIGHT, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> COBALT_HELMET = ITEMS.register("cobalt_helmet",
            () -> new ModArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> COBALT_CHESTPLATE = ITEMS.register("cobalt_chestplate",
            () -> new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> COBALT_LEGGINGS = ITEMS.register("cobalt_leggings",
            () -> new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> COBALT_BOOTS = ITEMS.register("cobalt_boots",
            () -> new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> ORICHALCUM_HELMET = ITEMS.register("orichalcum_helmet",
            () -> new ModArmorItem(ModArmorMaterials.ORICHALCUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_CHESTPLATE = ITEMS.register("orichalcum_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ORICHALCUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_LEGGINGS = ITEMS.register("orichalcum_leggings",
            () -> new ArmorItem(ModArmorMaterials.ORICHALCUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_BOOTS = ITEMS.register("orichalcum_boots",
            () -> new ArmorItem(ModArmorMaterials.ORICHALCUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> ADAMANTITE_HELMET = ITEMS.register("adamantite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.ADAMANTITE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ADAMANTITE_CHESTPLATE = ITEMS.register("adamantite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ADAMANTITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ADAMANTITE_LEGGINGS = ITEMS.register("adamantite_leggings",
            () -> new ArmorItem(ModArmorMaterials.ADAMANTITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ADAMANTITE_BOOTS = ITEMS.register("adamantite_boots",
            () -> new ArmorItem(ModArmorMaterials.ADAMANTITE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> HELLSTONE_HELMET = ITEMS.register("hellstone_helmet",
            () -> new ModArmorItem(ModArmorMaterials.HELLSTONE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> HELLSTONE_CHESTPLATE = ITEMS.register("hellstone_chestplate",
            () -> new ArmorItem(ModArmorMaterials.HELLSTONE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> HELLSTONE_LEGGINGS = ITEMS.register("hellstone_leggings",
            () -> new ArmorItem(ModArmorMaterials.HELLSTONE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> HELLSTONE_BOOTS = ITEMS.register("hellstone_boots",
            () -> new ArmorItem(ModArmorMaterials.HELLSTONE, ArmorItem.Type.BOOTS, new Item.Properties()));
    //endregion

//    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
//            () -> new MetalDetectorItem(new Item.Properties().durability(64)));

    // 登録メソッド
    public static void register() {
    }
}
