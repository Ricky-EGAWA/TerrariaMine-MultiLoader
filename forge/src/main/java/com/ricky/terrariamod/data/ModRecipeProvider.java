package com.ricky.terrariamod.data;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> COBALT_SMELTABLE = List.of(ModItems.COBALT_RAW.get(),
            ModBlocks.COBALT_ORE.get(), ModBlocks.DEEPSLATE_COBALT_ORE.get());
    private static final List<ItemLike> ORICHALCUM_SMELTABLE = List.of(ModItems.ORICHALCUM_RAW.get(),
            ModBlocks.ORICHALCUM_ORE.get(), ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get());
    private static final List<ItemLike> ADAMANTITE_SMELTABLE = List.of(ModItems.ADAMANTITE_RAW.get(),
            ModBlocks.ADAMANTITE_ORE.get(), ModBlocks.DEEPSLATE_ADAMANTITE_ORE.get());
    private static final List<ItemLike> HELLSTONE_SMELTABLE = List.of(ModItems.HELLSTONE_RAW.get(),
            ModBlocks.HELLSTONE_ORE.get());

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> exporter) {
        // 各種インゴットの生成レシピ
        generateSmeltingAndBlastingRecipes(exporter, COBALT_SMELTABLE, ModItems.COBALT_INGOT.get(), "cobalt");
        generateSmeltingAndBlastingRecipes(exporter, ORICHALCUM_SMELTABLE, ModItems.ORICHALCUM_INGOT.get(), "orichalcum");
        generateSmeltingAndBlastingRecipes(exporter, ADAMANTITE_SMELTABLE, ModItems.ADAMANTITE_INGOT.get(), "adamantite");
        generateSmeltingAndBlastingRecipes(exporter, HELLSTONE_SMELTABLE, ModItems.HELLSTONE_INGOT.get(), "hellstone");

        // 各種インゴットとブロックのコンパクトレシピ
        generateCompactingRecipes(exporter, ModItems.COBALT_INGOT.get(), ModBlocks.COBALT_BLOCK.get());
        generateCompactingRecipes(exporter, ModItems.ORICHALCUM_INGOT.get(), ModBlocks.ORICHALCUM_BLOCK.get());
        generateCompactingRecipes(exporter, ModItems.ADAMANTITE_INGOT.get(), ModBlocks.ADAMANTITE_BLOCK.get());
        generateCompactingRecipes(exporter, ModItems.HELLSTONE_INGOT.get(), ModBlocks.HELLSTONE_BLOCK.get());

        // 各種ツールのレシピ
        generateToolRecipes(exporter, ModItems.COBALT_INGOT.get(), "cobalt");
        generateToolRecipes(exporter, ModItems.ORICHALCUM_INGOT.get(), "orichalcum");
        generateToolRecipes(exporter, ModItems.ADAMANTITE_INGOT.get(), "adamantite");
        generateToolRecipes(exporter, ModItems.HELLSTONE_INGOT.get(), "hellstone");
        // 各種防具のレシピ
        generateArmorRecipes(exporter, ModItems.COBALT_INGOT.get(), "cobalt");
        generateArmorRecipes(exporter, ModItems.ORICHALCUM_INGOT.get(), "orichalcum");
        generateArmorRecipes(exporter, ModItems.ADAMANTITE_INGOT.get(), "adamantite");
        generateArmorRecipes(exporter, ModItems.HELLSTONE_INGOT.get(), "hellstone");
        generateArmorRecipes(exporter, Blocks.OAK_LOG, "oak");
        generateArmorRecipes(exporter, Blocks.PUMPKIN, "pumpkin");
        generateArmorRecipes(exporter, Blocks.CACTUS, "cactus");
        generateArmorRecipes(exporter, Blocks.OBSIDIAN, "obsidian");

        //階段　ハーフブロックなど
        generateBlockRecipes(exporter, ModBlocks.EBON_PLANKS.get(),"ebon");
        generateBlockRecipes(exporter, ModBlocks.CRIM_PLANKS.get(),"crim");
        generateBlockRecipes(exporter, ModBlocks.PEARL_PLANKS.get(),"pearl");
        //原木から木
        generateWoodBlockRecipes(exporter, ModBlocks.EBON_LOG.get(),"ebon");
        generateWoodBlockRecipes(exporter, ModBlocks.CRIM_LOG.get(),"crim");
        generateWoodBlockRecipes(exporter, ModBlocks.PEARL_LOG.get(),"pearl");
        generateWoodBlockRecipes(exporter, ModBlocks.STRIPPED_EBON_LOG.get(),"stripped_ebon");
        generateWoodBlockRecipes(exporter, ModBlocks.STRIPPED_CRIM_LOG.get(),"stripped_crim");
        generateWoodBlockRecipes(exporter, ModBlocks.STRIPPED_PEARL_LOG.get(),"stripped_pearl");
        //木材
        generatePlankBlockRecipes(exporter, ModBlocks.EBON_WOOD.get(),"ebon");
        generatePlankBlockRecipes(exporter, ModBlocks.CRIM_WOOD.get(),"crim");
        generatePlankBlockRecipes(exporter, ModBlocks.PEARL_WOOD.get(),"pearl");
        generatePlankBlockRecipes(exporter, ModBlocks.EBON_LOG.get(),"ebon_wood");
        generatePlankBlockRecipes(exporter, ModBlocks.CRIM_LOG.get(),"crim_wood");
        generatePlankBlockRecipes(exporter, ModBlocks.PEARL_LOG.get(),"pearl_wood");
        generatePlankBlockRecipes(exporter, ModBlocks.STRIPPED_EBON_WOOD.get(),"stripped_ebon");
        generatePlankBlockRecipes(exporter, ModBlocks.STRIPPED_CRIM_WOOD.get(),"stripped_crim");
        generatePlankBlockRecipes(exporter, ModBlocks.STRIPPED_PEARL_WOOD.get(),"stripped_pearl");
        generatePlankBlockRecipes(exporter, ModBlocks.STRIPPED_EBON_LOG.get(),"stripped_ebon_wood");
        generatePlankBlockRecipes(exporter, ModBlocks.STRIPPED_CRIM_LOG.get(),"stripped_crim_wood");
        generatePlankBlockRecipes(exporter, ModBlocks.STRIPPED_PEARL_LOG.get(),"stripped_pearl_wood");

        //その他
        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.ROCKET.get(),"ABA","BAB","ABA",Items.GUNPOWDER,Items.IRON_INGOT,"rocket",3);
//        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.IRON_BOW," AB","A B"," AB",Items.IRON_INGOT,Items.STRING,"iron_bow",1);
//        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.GOLD_BOW," AB","A B"," AB",Items.GOLD_INGOT,Items.STRING,"gold_bow",1);
//        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.DIAMOND_BOW," AB","A B"," AB",Items.DIAMOND,Items.STRING,"diamond_bow",1);

        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.AMETHYST_STAFF.get(),"  B"," A ","A  ",Items.COPPER_INGOT,Items.AMETHYST_SHARD,"amethyst_staff",1);
        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.EMERALD_STAFF.get(),"  B"," A ","A  ",Items.IRON_INGOT,Items.EMERALD,"emerald_staff",1);
        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.PHOENIX_BLASTER.get(),"AAA","ABA","AAA",ModItems.HELLSTONE_INGOT.get(),ModItems.HANDGUN.get(),"phoenix_blaster",1);
    }

    private void generateSmeltingAndBlastingRecipes(Consumer<FinishedRecipe> exporter, List<ItemLike> smeltables, ItemLike result, String name) {
        // Smelting recipes
        for (ItemLike input : smeltables) {
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.MISC, result, 0.7f, 200)
                    .unlockedBy("has_" + name, has(input)) // 個別にhasを適用
                    .save(exporter, new ResourceLocation(Constants.MOD_ID, "smelting_" + name + "_from_" + getItemName(input)));
        }

        // Blasting recipes
        for (ItemLike input : smeltables) {
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), RecipeCategory.MISC, result, 0.7f, 100)
                    .unlockedBy("has_" + name, has(input)) // 個別にhasを適用
                    .save(exporter, new ResourceLocation(Constants.MOD_ID, "blasting_" + name + "_from_" + getItemName(input)));
        }
    }
    private void generateCompactingRecipes(Consumer<FinishedRecipe> exporter, ItemLike ingot, ItemLike block) {
        generateCompactingAndReversingRecipes(exporter, ingot, block, getItemName(ingot));
    }
    private void generateToolRecipes(Consumer<FinishedRecipe> exporter, ItemLike ingot, String materialName) {
        // ピッケル
        createStickItemRecipe(exporter, RecipeCategory.TOOLS, ModItems.getPickaxe(materialName), "III", " S ", " S ", ingot, "_pickaxe",1);
        // アックス
        createStickItemRecipe(exporter, RecipeCategory.TOOLS, ModItems.getAxe(materialName), " II", " SI", " S ", ingot, "_axe",1);
        // ソード
        createStickItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.getSword(materialName), " I ", " I ", " S ", ingot, "_sword",1);
        // シャベル
        createStickItemRecipe(exporter, RecipeCategory.TOOLS, ModItems.getShovel(materialName), " I ", " S ", " S ", ingot, "_shovel",1);
    }
    private void createStickItemRecipe(Consumer<FinishedRecipe> exporter, RecipeCategory category, ItemLike result, String pattern1, String pattern2, String pattern3, ItemLike material, String suffix, int count) {
        ShapedRecipeBuilder.shaped(category, result, count)
                .define('I', material)
                .define('S', Items.STICK)
                .pattern(pattern1)
                .pattern(pattern2)
                .pattern(pattern3)
                .unlockedBy("has_" + getItemName(material), has(material))
                .save(exporter, new ResourceLocation(Constants.MOD_ID, getItemName(result) + suffix));
    }
    private void generateArmorRecipes(Consumer<FinishedRecipe> exporter, ItemLike ingot, String materialName) {
        // ヘルメットのレシピ
        createOneItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.getHelmet(materialName), "III", "I I", "   ", ingot, materialName, "_helmet",1);
        // チェストプレートのレシピ
        createOneItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.getChestplate(materialName), "I I", "III", "III", ingot, materialName, "_chestplate",1);
        // レギンスのレシピ
        createOneItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.getLeggings(materialName), "III", "I I", "I I", ingot, materialName, "_leggings",1);
        // ブーツのレシピ
        createOneItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.getBoots(materialName), "   ", "I I", "I I", ingot, materialName, "_boots",1);
    }
    private void createOneItemRecipe(Consumer<FinishedRecipe> exporter, RecipeCategory category, ItemLike armor, String row1, String row2, String row3, ItemLike ingot, String materialName, String armorType, int num) {
        ShapedRecipeBuilder.shaped(category, armor, num)
                .pattern(row1)
                .pattern(row2)
                .pattern(row3)
                .define('I', ingot)
                .unlockedBy("has_" + getItemName(ingot), has(ingot))
                .save(exporter, new ResourceLocation(Constants.MOD_ID, materialName + armorType));
    }
    private void createTwoItemRecipe(Consumer<FinishedRecipe> exporter, RecipeCategory category, ItemLike armor, String row1, String row2, String row3, ItemLike item1, ItemLike item2, String materialName, int num) {
        ShapedRecipeBuilder.shaped(category, armor, num)
                .pattern(row1)
                .pattern(row2)
                .pattern(row3)
                .define('A', item1)
                .define('B', item2)
                .unlockedBy("has_" + getItemName(item1), has(item1))
                .unlockedBy("has_" + getItemName(item2), has(item2))
                .save(exporter, new ResourceLocation(Constants.MOD_ID, materialName));
    }
    private void generateBlockRecipes(Consumer<FinishedRecipe> exporter, ItemLike block, String materialName) {
        // Slab
        createOneItemRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.getSlab(materialName), "   ", "III", "   ", block, materialName, "_slab", 6);
        // Stairs
        createOneItemRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.getStairs(materialName), "I  ", "II ", "III", block, materialName, "_stairs", 4);
        // Fence
        createStickItemRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.getFence(materialName), "   ", "ISI", "ISI", block, "_fence", 3);
        // Fence Gate
        createStickItemRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.getFenceGate(materialName), "   ", "SIS", "SIS", block, "_fence_gate", 1);
        // Button
        createOneItemRecipe(exporter, RecipeCategory.REDSTONE, ModBlocks.getButton(materialName), "   ", " I ", "   ", block, materialName, "_button", 1);
        // Pressure Plate
        createOneItemRecipe(exporter, RecipeCategory.REDSTONE, ModBlocks.getPressurePlate(materialName), "   ", " II", "   ", block, materialName, "_pressure_plate", 1);
    }
    private void generateWoodBlockRecipes(Consumer<FinishedRecipe> exporter, ItemLike block, String materialName) {
        // 木
        createOneItemRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.getWood(materialName), "II ", "II ", "   ", block, materialName, "_wood",3);
    }
    private void generatePlankBlockRecipes(Consumer<FinishedRecipe> exporter, ItemLike block, String materialName) {
        // 板材
        createOneItemRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.getPlanks(materialName), "   ", " I ", "   ", block, materialName, "_planks",4);
    }
    private void generateCompactingRecipe(Consumer<FinishedRecipe> exporter, ItemLike ingot, ItemLike block, String name) {
        // Block from Ingots
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .define('#', ingot)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_" + name, has(ingot))
                .save(exporter, new ResourceLocation(Constants.MOD_ID, name + "_block_from_ingots"));
    }
    private void generateReversingCompactingRecipe(Consumer<FinishedRecipe> exporter, ItemLike ingot, ItemLike block, String name) {
        // Ingots from Block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 9)
                .requires(block)
                .unlockedBy("has_" + name + "_block", has(block))
                .save(exporter, new ResourceLocation(Constants.MOD_ID, name + "_ingots_from_block"));
    }
    private void generateCompactingAndReversingRecipes(Consumer<FinishedRecipe> exporter, ItemLike ingot, ItemLike block, String name) {
        // Block from Ingots (圧縮)
        generateCompactingRecipe(exporter, ingot, block, name);

        // Ingots from Block (逆圧縮)
        generateReversingCompactingRecipe(exporter, ingot, block, name);
    }





    protected static @NotNull String getItemName(ItemLike itemLike) {
        return itemLike.asItem().getDescriptionId().replace("item.", "").replace("block.", "").replace('.', '_');
    }
}
