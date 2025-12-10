package com.ricky.terrariamod.block;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.custom.*;
import com.ricky.terrariamod.registry.RegistryProvider;
import com.ricky.terrariamod.world.ModConfiguredFeatures;
import com.ricky.terrariamod.world.portal.ModPortalBlock;
import com.ricky.terrariamod.world.tree.CrimSaplingGenerator;
import com.ricky.terrariamod.world.tree.EbonSaplingGenerator;
import com.ricky.terrariamod.world.tree.PearlSaplingGenerator;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

public class ModBlocks {
    public static final RegistryProvider<Block> BLOCKS =
            RegistryProvider.get(Registries.BLOCK, Constants.MOD_ID);
    public static final RegistryProvider<Item> ITEMS =
            RegistryProvider.get(Registries.ITEM, Constants.MOD_ID);


    //region
    public static final Supplier<Block> COBALT_BLOCK = registerBlockWithItem("cobalt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> COBALT_ORE = registerBlockWithItem("cobalt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).strength(50f, 1200f), UniformInt.of(2, 5)));
    public static final Supplier<Block> DEEPSLATE_COBALT_ORE = registerBlockWithItem("deepslate_cobalt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).strength(50f, 1200f), UniformInt.of(2, 5)));
    public static final Supplier<Block> ORICHALCUM_BLOCK = registerBlockWithItem("orichalcum_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> ORICHALCUM_ORE = registerBlockWithItem("orichalcum_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).strength(50f, 1200f), UniformInt.of(2, 5)));
    public static final Supplier<Block> DEEPSLATE_ORICHALCUM_ORE = registerBlockWithItem("deepslate_orichalcum_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).strength(50f, 1200f), UniformInt.of(2, 5)));
    public static final Supplier<Block> ADAMANTITE_BLOCK = registerBlockWithItem("adamantite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> ADAMANTITE_ORE = registerBlockWithItem("adamantite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).strength(50f, 1200f), UniformInt.of(2, 5)));
    public static final Supplier<Block> DEEPSLATE_ADAMANTITE_ORE = registerBlockWithItem("deepslate_adamantite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).strength(50f, 1200f), UniformInt.of(2, 5)));
    public static final Supplier<Block> HELLSTONE_BLOCK = registerBlockWithItem("hellstone_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> HELLSTONE_ORE = registerBlockWithItem("hellstone_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).strength(50f, 1200f), UniformInt.of(2, 5)));
    //endregion

    //region
    public static final Supplier<Block> EBON_STONE = registerBlockWithItem("ebon_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final Supplier<Block> EBON_SAND = registerBlockWithItem("ebon_sand",
            () -> new SandBlock(10947717, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final Supplier<Block> EBON_SANDSTONE = registerBlockWithItem("ebon_sandstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final Supplier<Block> EBON_ICE = registerBlockWithItem("ebon_ice",
            () -> new IceBlock(BlockBehaviour.Properties.copy(Blocks.ICE)));
    public static final Supplier<Block> CRIM_STONE = registerBlockWithItem("crim_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final Supplier<Block> CRIM_SAND = registerBlockWithItem("crim_sand",
            () -> new SandBlock(11732246, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final Supplier<Block> CRIM_SANDSTONE = registerBlockWithItem("crim_sandstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final Supplier<Block> CRIM_ICE = registerBlockWithItem("crim_ice",
            () -> new IceBlock(BlockBehaviour.Properties.copy(Blocks.ICE)));
    public static final Supplier<Block> PEARL_STONE = registerBlockWithItem("pearl_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final Supplier<Block> PEARL_SAND = registerBlockWithItem("pearl_sand",
            () -> new SandBlock(15987186, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final Supplier<Block> PEARL_SANDSTONE = registerBlockWithItem("pearl_sandstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final Supplier<Block> PEARL_ICE = registerBlockWithItem("pearl_ice",
            () -> new IceBlock(BlockBehaviour.Properties.copy(Blocks.ICE)));
    //endregion

    //region 花 キノコ
    public static final Supplier<Block> DEATH_WEED = registerBlockWithItem("death_weed",
            () -> new FlowerBlock(MobEffects.HARM, 10, BlockBehaviour.Properties.copy(Blocks.ALLIUM)));
    public static final Supplier<Block> POTTED_DEATH_WEED = registerBlockWithItem("potted_death_weed",
            () -> new FlowerPotBlock(DEATH_WEED.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final Supplier<Block> SHIVER_THORN = registerBlockWithItem("shiver_thorn",
            () -> new FlowerBlock(MobEffects.HARM, 10, BlockBehaviour.Properties.copy(Blocks.ALLIUM)));
    public static final Supplier<Block> POTTED_SHIVER_THORN = registerBlockWithItem("potted_shiver_thorn",
            () -> new FlowerPotBlock(SHIVER_THORN.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final Supplier<Block> VILE_MUSHROOM = registerBlockWithItem("vile_mushroom",
            () -> new FlowerBlock(MobEffects.HARM, 10, BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM)));
    public static final Supplier<Block> POTTED_VILE_MUSHROOM = registerBlockWithItem("potted_vile_mushroom",
            () -> new FlowerPotBlock(VILE_MUSHROOM.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final Supplier<Block> VICIOUS_MUSHROOM = registerBlockWithItem("vicious_mushroom",
            () -> new FlowerBlock(MobEffects.HARM, 10, BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM)));
    public static final Supplier<Block> POTTED_VICIOUS_MUSHROOM = registerBlockWithItem("potted_vicious_mushroom",
            () -> new FlowerPotBlock(VICIOUS_MUSHROOM.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));
    //endregion

    //region 木
    public static final Supplier<Block> EBON_LOG = registerBlockWithItem("ebon_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final Supplier<Block> EBON_WOOD = registerBlockWithItem("ebon_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final Supplier<Block> STRIPPED_EBON_LOG = registerBlockWithItem("stripped_ebon_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final Supplier<Block> STRIPPED_EBON_WOOD = registerBlockWithItem("stripped_ebon_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Supplier<Block> EBON_PLANKS = registerBlockWithItem("ebon_planks",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<Block> EBON_LEAVES = registerBlockWithItem("ebon_leaves",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noOcclusion()));
    public static final Supplier<Block> EBON_SAPLING = registerBlockWithItem("ebon_sapling",
            () -> new ModSaplingBlock(new EbonSaplingGenerator(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).noOcclusion()));
    public static final Supplier<Block> POTTED_EBON_SAPLING = registerBlockWithItem("potted_ebon_sapling",
            () -> new FlowerPotBlock(EBON_SAPLING.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static final Supplier<Block> CRIM_LOG = registerBlockWithItem("crim_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final Supplier<Block> CRIM_WOOD = registerBlockWithItem("crim_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final Supplier<Block> STRIPPED_CRIM_LOG = registerBlockWithItem("stripped_crim_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final Supplier<Block> STRIPPED_CRIM_WOOD = registerBlockWithItem("stripped_crim_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Supplier<Block> CRIM_PLANKS = registerBlockWithItem("crim_planks",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<Block> CRIM_LEAVES = registerBlockWithItem("crim_leaves",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noOcclusion()));
    public static final Supplier<Block> CRIM_SAPLING = registerBlockWithItem("crim_sapling",
            () -> new ModSaplingBlock(new CrimSaplingGenerator(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).noOcclusion()));
    public static final Supplier<Block> POTTED_CRIM_SAPLING = registerBlockWithItem("potted_crim_sapling",
            () -> new FlowerPotBlock(CRIM_SAPLING.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static final Supplier<Block> PEARL_LOG = registerBlockWithItem("pearl_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final Supplier<Block> PEARL_WOOD = registerBlockWithItem("pearl_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final Supplier<Block> STRIPPED_PEARL_LOG = registerBlockWithItem("stripped_pearl_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final Supplier<Block> STRIPPED_PEARL_WOOD = registerBlockWithItem("stripped_pearl_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Supplier<Block> PEARL_PLANKS = registerBlockWithItem("pearl_planks",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<Block> PEARL_LEAVES = registerBlockWithItem("pearl_leaves",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noOcclusion()));
    public static final Supplier<Block> PEARL_SAPLING = registerBlockWithItem("pearl_sapling",
            () -> new ModSaplingBlock(new PearlSaplingGenerator(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).noOcclusion()));
    public static final Supplier<Block> POTTED_PEARL_SAPLING = registerBlockWithItem("potted_pearl_sapling",
            () -> new FlowerPotBlock(PEARL_SAPLING.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    //endregion

    //region 階段など
    public static final Supplier<Block> EBON_STAIRS = registerBlockWithItem("ebon_stairs",
            () -> new ModStairBlock(ModBlocks.EBON_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
    public static final Supplier<Block> EBON_SLAB = registerBlockWithItem("ebon_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
    public static final Supplier<Block> EBON_BUTTON = registerBlockWithItem("ebon_button",
            () -> new ModButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.IRON, 10, true));
    public static final Supplier<Block> EBON_PRESSURE_PLATE = registerBlockWithItem("ebon_pressure_plate",
            () -> new ModPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.IRON));
    public static final Supplier<Block> EBON_FENCE = registerBlockWithItem("ebon_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final Supplier<Block> EBON_FENCE_GATE = registerBlockWithItem("ebon_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), WoodType.ACACIA));

    public static final Supplier<Block> CRIM_STAIRS = registerBlockWithItem("crim_stairs",
            () -> new ModStairBlock(ModBlocks.CRIM_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
    public static final Supplier<Block> CRIM_SLAB = registerBlockWithItem("crim_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
    public static final Supplier<Block> CRIM_BUTTON = registerBlockWithItem("crim_button",
            () -> new ModButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.IRON, 10, true));
    public static final Supplier<Block> CRIM_PRESSURE_PLATE = registerBlockWithItem("crim_pressure_plate",
            () -> new ModPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.IRON));
    public static final Supplier<Block> CRIM_FENCE = registerBlockWithItem("crim_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final Supplier<Block> CRIM_FENCE_GATE = registerBlockWithItem("crim_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), WoodType.ACACIA));

    public static final Supplier<Block> PEARL_STAIRS = registerBlockWithItem("pearl_stairs",
            () -> new ModStairBlock(ModBlocks.PEARL_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
    public static final Supplier<Block> PEARL_SLAB = registerBlockWithItem("pearl_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
    public static final Supplier<Block> PEARL_BUTTON = registerBlockWithItem("pearl_button",
            () -> new ModButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.IRON, 10, true));
    public static final Supplier<Block> PEARL_PRESSURE_PLATE = registerBlockWithItem("pearl_pressure_plate",
            () -> new ModPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.IRON));
    public static final Supplier<Block> PEARL_FENCE = registerBlockWithItem("pearl_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final Supplier<Block> PEARL_FENCE_GATE = registerBlockWithItem("pearl_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), WoodType.ACACIA));

    //endregion

    public static final Supplier<Block> GLOWING_MUSHROOM = registerBlockWithItem("glowing_mushroom",
            () -> new MushroomBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM).lightLevel(state -> 6), ModConfiguredFeatures.HUGE_GLOWING_MUSHROOM));
    public static final Supplier<Block> POTTED_GLOWING_MUSHROOM = registerBlockWithItem("potted_glowing_mushroom",
            () -> new FlowerPotBlock(GLOWING_MUSHROOM.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final Supplier<Block> GLOWING_MOSS = registerBlockWithItem("glowing_moss_block",
            () -> new MossBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK).lightLevel(state -> 6).mapColor(MapColor.COLOR_BLUE)));
    public static final Supplier<Block> ICICLE = registerBlockWithItem("icicle",
            () -> new PointedDripstoneBlock(BlockBehaviour.Properties.copy(Blocks.POINTED_DRIPSTONE)));
    public static final Supplier<Block> GLOWING_MUSHROOM_BLOCK = registerBlockWithItem("glowing_mushroom_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM_BLOCK).lightLevel(state -> 6)));
    public static final Supplier<Block> GLOWING_MUSHROOM_STEM = registerBlockWithItem("glowing_mushroom_stem",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM).lightLevel(state -> 6)));

    public static final Supplier<Block> PORTAL_BLOCK = registerBlockWithItem("portal_block",
            () -> new ModPortalBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_PORTAL)));

    //region ダンジョン用ブロック
    public static final Supplier<Block> DUNGEON_TILE_GREEN = registerBlockWithItem("dungeon_tile_green",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final Supplier<Block> DUNGEON_TILE_GREEN_STAIRS = registerBlockWithItem("dungeon_tile_green_stairs",
            () -> new ModStairBlock(ModBlocks.DUNGEON_TILE_GREEN.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE_STAIRS)));
    public static final Supplier<Block> DUNGEON_TILE_GREEN_SLAB = registerBlockWithItem("dungeon_tile_green_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB)));
    public static final Supplier<Block> DUNGEON_TILE_GREEN_WALL = registerBlockWithItem("dungeon_tile_green_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)));

    public static final Supplier<Block> DUNGEON_BRICK_GREEN = registerBlockWithItem("dungeon_brick_green",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final Supplier<Block> DUNGEON_BRICK_GREEN_STAIRS = registerBlockWithItem("dungeon_brick_green_stairs",
            () -> new ModStairBlock(ModBlocks.DUNGEON_BRICK_GREEN.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE_STAIRS)));
    public static final Supplier<Block> DUNGEON_BRICK_GREEN_SLAB = registerBlockWithItem("dungeon_brick_green_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB)));
    public static final Supplier<Block> DUNGEON_BRICK_GREEN_WALL = registerBlockWithItem("dungeon_brick_green_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)));
    //endregion

    public static Block getSlab(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_SLAB.get();
            case "crim" -> CRIM_SLAB.get();
            case "pearl" -> PEARL_SLAB.get();
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getStairs(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_STAIRS.get();
            case "crim" -> CRIM_STAIRS.get();
            case "pearl" -> PEARL_STAIRS.get();
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getFence(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_FENCE.get();
            case "crim" -> CRIM_FENCE.get();
            case "pearl" -> PEARL_FENCE.get();
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getFenceGate(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_FENCE_GATE.get();
            case "crim" -> CRIM_FENCE_GATE.get();
            case "pearl" -> PEARL_FENCE_GATE.get();
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getButton(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_BUTTON.get();
            case "crim" -> CRIM_BUTTON.get();
            case "pearl" -> PEARL_BUTTON.get();
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getPressurePlate(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_PRESSURE_PLATE.get();
            case "crim" -> CRIM_PRESSURE_PLATE.get();
            case "pearl" -> PEARL_PRESSURE_PLATE.get();
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getWood(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_WOOD.get();
            case "crim" -> CRIM_WOOD.get();
            case "pearl" -> PEARL_WOOD.get();
            case "stripped_ebon" -> STRIPPED_EBON_WOOD.get();
            case "stripped_crim" -> STRIPPED_CRIM_WOOD.get();
            case "stripped_pearl" -> STRIPPED_PEARL_WOOD.get();
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getPlanks(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon", "ebon_wood", "stripped_ebon", "stripped_ebon_wood" -> EBON_PLANKS.get();
            case "crim", "crim_wood", "stripped_crim", "stripped_crim_wood" -> CRIM_PLANKS.get();
            case "pearl", "pearl_wood", "stripped_pearl", "stripped_pearl_wood" -> PEARL_PLANKS.get();
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }


    private static Supplier<Block> registerBlockWithItem(String name, Supplier<Block> blockSupplier) {
        Supplier<Block> block = BLOCKS.register(name, blockSupplier);
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
    public static void register() {
    }
}
