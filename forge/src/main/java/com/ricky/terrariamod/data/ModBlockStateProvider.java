package com.ricky.terrariamod.data;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Constants.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //region
        blockWithItem(ModBlocks.COBALT_BLOCK);
        blockWithItem(ModBlocks.COBALT_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_COBALT_ORE);

        // ORICHALCUM 
        blockWithItem(ModBlocks.ORICHALCUM_BLOCK);
        blockWithItem(ModBlocks.ORICHALCUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ORICHALCUM_ORE);

        // ADAMANTITE 
        blockWithItem(ModBlocks.ADAMANTITE_BLOCK);
        blockWithItem(ModBlocks.ADAMANTITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ADAMANTITE_ORE);

        // HELLSTONE 
        blockWithItem(ModBlocks.HELLSTONE_BLOCK);
        blockWithItem(ModBlocks.HELLSTONE_ORE);
        //endregion

        //region 
        // EBON 
        blockWithItem(ModBlocks.EBON_STONE);
        blockWithItem(ModBlocks.EBON_SAND);
        registerCustomBlock(ModBlocks.EBON_SANDSTONE);
        blockWithItem(ModBlocks.EBON_ICE);

        // CRIM 
        blockWithItem(ModBlocks.CRIM_STONE);
        blockWithItem(ModBlocks.CRIM_SAND);
        registerCustomBlock(ModBlocks.CRIM_SANDSTONE);
        blockWithItem(ModBlocks.CRIM_ICE);

        // PEARL 
        blockWithItem(ModBlocks.PEARL_STONE);
        blockWithItem(ModBlocks.PEARL_SAND);
        registerCustomBlock(ModBlocks.PEARL_SANDSTONE);
        blockWithItem(ModBlocks.PEARL_ICE);
        //endregion

        // 花
        plantBlockWithItem(ModBlocks.DEATH_WEED);
        pottedPlantBlockWithItem(ModBlocks.POTTED_DEATH_WEED, ModBlocks.DEATH_WEED);
        plantBlockWithItem(ModBlocks.SHIVER_THORN);
        pottedPlantBlockWithItem(ModBlocks.POTTED_SHIVER_THORN, ModBlocks.SHIVER_THORN);
        plantBlockWithItem(ModBlocks.VILE_MUSHROOM);
        pottedPlantBlockWithItem(ModBlocks.POTTED_VILE_MUSHROOM, ModBlocks.VILE_MUSHROOM);
        plantBlockWithItem(ModBlocks.VICIOUS_MUSHROOM);
        pottedPlantBlockWithItem(ModBlocks.POTTED_VICIOUS_MUSHROOM, ModBlocks.VICIOUS_MUSHROOM);

        // GLOWING 
        blockWithItem(ModBlocks.GLOWING_MOSS);
        blockWithItem(ModBlocks.GLOWING_MUSHROOM_BLOCK);
        blockWithItem(ModBlocks.GLOWING_MUSHROOM_STEM);
        plantBlockWithItem(ModBlocks.GLOWING_MUSHROOM);
        pottedPlantBlockWithItem(ModBlocks.POTTED_GLOWING_MUSHROOM, ModBlocks.GLOWING_MUSHROOM);

        // ICICLE 
//        blockWithItem(ModBlocks.ICICLE);

        //region wood
        logBlock(((RotatedPillarBlock) ModBlocks.EBON_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.EBON_WOOD.get()), blockTexture(ModBlocks.EBON_LOG.get()), blockTexture(ModBlocks.EBON_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_EBON_LOG.get()), blockTexture(ModBlocks.STRIPPED_EBON_LOG.get()),
                new ResourceLocation(Constants.MOD_ID, "block/stripped_ebon_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_EBON_WOOD.get()), blockTexture(ModBlocks.STRIPPED_EBON_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_EBON_LOG.get()));
        blockItem(ModBlocks.EBON_LOG);
        blockItem(ModBlocks.EBON_WOOD);

        blockItem(ModBlocks.STRIPPED_EBON_LOG);
        blockItem(ModBlocks.STRIPPED_EBON_WOOD);
        blockWithItem(ModBlocks.EBON_PLANKS);
        leavesBlock(ModBlocks.EBON_LEAVES);
        plantBlockWithItem(ModBlocks.EBON_SAPLING);
        pottedPlantBlockWithItem(ModBlocks.POTTED_EBON_SAPLING, ModBlocks.EBON_SAPLING);

        logBlock(((RotatedPillarBlock) ModBlocks.CRIM_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.CRIM_WOOD.get()), blockTexture(ModBlocks.CRIM_LOG.get()), blockTexture(ModBlocks.CRIM_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_CRIM_LOG.get()), blockTexture(ModBlocks.STRIPPED_CRIM_LOG.get()),
                new ResourceLocation(Constants.MOD_ID, "block/stripped_crim_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_CRIM_WOOD.get()), blockTexture(ModBlocks.STRIPPED_CRIM_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_CRIM_LOG.get()));
        blockItem(ModBlocks.CRIM_LOG);
        blockItem(ModBlocks.CRIM_WOOD);
        blockItem(ModBlocks.STRIPPED_CRIM_LOG);
        blockItem(ModBlocks.STRIPPED_CRIM_WOOD);
        blockWithItem(ModBlocks.CRIM_PLANKS);
        leavesBlock(ModBlocks.CRIM_LEAVES);
        plantBlockWithItem(ModBlocks.CRIM_SAPLING);
        pottedPlantBlockWithItem(ModBlocks.POTTED_CRIM_SAPLING, ModBlocks.CRIM_SAPLING);

        logBlock(((RotatedPillarBlock) ModBlocks.PEARL_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.PEARL_WOOD.get()), blockTexture(ModBlocks.PEARL_LOG.get()), blockTexture(ModBlocks.PEARL_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_PEARL_LOG.get()), blockTexture(ModBlocks.STRIPPED_PEARL_LOG.get()),
                new ResourceLocation(Constants.MOD_ID, "block/stripped_pearl_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_PEARL_WOOD.get()), blockTexture(ModBlocks.STRIPPED_PEARL_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_PEARL_LOG.get()));
        blockItem(ModBlocks.PEARL_LOG);
        blockItem(ModBlocks.PEARL_WOOD);
        blockItem(ModBlocks.STRIPPED_PEARL_LOG);
        blockItem(ModBlocks.STRIPPED_PEARL_WOOD);
        blockWithItem(ModBlocks.PEARL_PLANKS);
        leavesBlock(ModBlocks.PEARL_LEAVES);
        plantBlockWithItem(ModBlocks.PEARL_SAPLING);
        pottedPlantBlockWithItem(ModBlocks.POTTED_PEARL_SAPLING, ModBlocks.PEARL_SAPLING);

        //endregion

        //region stairs etc
        stairsBlock(((StairBlock) ModBlocks.EBON_STAIRS.get()), blockTexture(ModBlocks.EBON_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.EBON_SLAB.get()), blockTexture(ModBlocks.EBON_PLANKS.get()), blockTexture(ModBlocks.EBON_PLANKS.get()));
        buttonBlock(((ButtonBlock) ModBlocks.EBON_BUTTON.get()), blockTexture(ModBlocks.EBON_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.EBON_PRESSURE_PLATE.get()), blockTexture(ModBlocks.EBON_PLANKS.get()));
        fenceBlock(((FenceBlock) ModBlocks.EBON_FENCE.get()), blockTexture(ModBlocks.EBON_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.EBON_FENCE_GATE.get()), blockTexture(ModBlocks.EBON_PLANKS.get()));
        blockItem(ModBlocks.EBON_STAIRS);
        blockItem(ModBlocks.EBON_SLAB);
        blockItem(ModBlocks.EBON_PRESSURE_PLATE);
        blockItem(ModBlocks.EBON_FENCE_GATE);

        stairsBlock(((StairBlock) ModBlocks.CRIM_STAIRS.get()), blockTexture(ModBlocks.CRIM_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.CRIM_SLAB.get()), blockTexture(ModBlocks.CRIM_PLANKS.get()), blockTexture(ModBlocks.CRIM_PLANKS.get()));
        buttonBlock(((ButtonBlock) ModBlocks.CRIM_BUTTON.get()), blockTexture(ModBlocks.CRIM_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.CRIM_PRESSURE_PLATE.get()), blockTexture(ModBlocks.CRIM_PLANKS.get()));
        fenceBlock(((FenceBlock) ModBlocks.CRIM_FENCE.get()), blockTexture(ModBlocks.CRIM_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.CRIM_FENCE_GATE.get()), blockTexture(ModBlocks.CRIM_PLANKS.get()));
        blockItem(ModBlocks.CRIM_STAIRS);
        blockItem(ModBlocks.CRIM_SLAB);
        blockItem(ModBlocks.CRIM_PRESSURE_PLATE);
        blockItem(ModBlocks.CRIM_FENCE_GATE);

        stairsBlock(((StairBlock) ModBlocks.PEARL_STAIRS.get()), blockTexture(ModBlocks.PEARL_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.PEARL_SLAB.get()), blockTexture(ModBlocks.PEARL_PLANKS.get()), blockTexture(ModBlocks.PEARL_PLANKS.get()));
        buttonBlock(((ButtonBlock) ModBlocks.PEARL_BUTTON.get()), blockTexture(ModBlocks.PEARL_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.PEARL_PRESSURE_PLATE.get()), blockTexture(ModBlocks.PEARL_PLANKS.get()));
        fenceBlock(((FenceBlock) ModBlocks.PEARL_FENCE.get()), blockTexture(ModBlocks.PEARL_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.PEARL_FENCE_GATE.get()), blockTexture(ModBlocks.PEARL_PLANKS.get()));
        blockItem(ModBlocks.PEARL_STAIRS);
        blockItem(ModBlocks.PEARL_SLAB);
        blockItem(ModBlocks.PEARL_PRESSURE_PLATE);
        blockItem(ModBlocks.PEARL_FENCE_GATE);
        //endregion

        //region Dungeon Blocks
        // Dungeon Tile Green
        blockWithItem(ModBlocks.DUNGEON_TILE_GREEN);
        stairsBlock(((StairBlock) ModBlocks.DUNGEON_TILE_GREEN_STAIRS.get()), blockTexture(ModBlocks.DUNGEON_TILE_GREEN.get()));
        slabBlock(((SlabBlock) ModBlocks.DUNGEON_TILE_GREEN_SLAB.get()), blockTexture(ModBlocks.DUNGEON_TILE_GREEN.get()), blockTexture(ModBlocks.DUNGEON_TILE_GREEN.get()));
        wallBlock(((WallBlock) ModBlocks.DUNGEON_TILE_GREEN_WALL.get()), blockTexture(ModBlocks.DUNGEON_TILE_GREEN.get()));
        blockItem(ModBlocks.DUNGEON_TILE_GREEN_STAIRS);
        blockItem(ModBlocks.DUNGEON_TILE_GREEN_SLAB);
        wallBlockItem(ModBlocks.DUNGEON_TILE_GREEN_WALL, ModBlocks.DUNGEON_TILE_GREEN);

        blockWithItem(ModBlocks.DUNGEON_BRICK_GREEN);
        stairsBlock(((StairBlock) ModBlocks.DUNGEON_BRICK_GREEN_STAIRS.get()), blockTexture(ModBlocks.DUNGEON_BRICK_GREEN.get()));
        slabBlock(((SlabBlock) ModBlocks.DUNGEON_BRICK_GREEN_SLAB.get()), blockTexture(ModBlocks.DUNGEON_BRICK_GREEN.get()), blockTexture(ModBlocks.DUNGEON_BRICK_GREEN.get()));
        wallBlock(((WallBlock) ModBlocks.DUNGEON_BRICK_GREEN_WALL.get()), blockTexture(ModBlocks.DUNGEON_BRICK_GREEN.get()));
        blockItem(ModBlocks.DUNGEON_BRICK_GREEN_STAIRS);
        blockItem(ModBlocks.DUNGEON_BRICK_GREEN_SLAB);
        wallBlockItem(ModBlocks.DUNGEON_BRICK_GREEN_WALL, ModBlocks.DUNGEON_BRICK_GREEN);

        // Fragile Blocks - use same texture as regular blocks
        blockWithItem(ModBlocks.DUNGEON_TILE_GREEN_FRAGILE);
        blockWithItem(ModBlocks.DUNGEON_BRICK_GREEN_FRAGILE);

        // Spike Block
        blockWithItem(ModBlocks.SPIKE_BLOCK);

        // Chest Blocks - both use entity rendering, register particle texture only models
        // Locked Golden Chest - uses entity rendering (always closed)
        ModelFile lockedChestModel = models().getBuilder("locked_golden_chest")
                .texture("particle", modLoc("entity/chest/locked_golden_chest"));
        horizontalBlock(ModBlocks.LOCKED_GOLDEN_CHEST.get(), lockedChestModel);

        // Golden Chest - uses entity rendering for animation
        ModelFile goldenChestModel = models().getBuilder("golden_chest")
                .texture("particle", modLoc("entity/chest/golden_chest"));
        horizontalBlock(ModBlocks.GOLDEN_CHEST.get(), goldenChestModel);
        //endregion
    }


    private void blockWithItem(Supplier<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void leavesBlock(Supplier<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
    private void saplingBlock(Supplier<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
    private void blockItem(Supplier<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(Constants.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void plantBlockWithItem(Supplier<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), models().cross(blockTexture(blockRegistryObject.get()).getPath(),blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
    private void pottedPlantBlockWithItem(Supplier<Block> blockRegistryObject,Supplier<Block> plantRegistryObject) {
        String descriptionId = blockRegistryObject.get().getDescriptionId();
        String blockName = descriptionId.substring(descriptionId.lastIndexOf('.') + 1);
        simpleBlockWithItem(blockRegistryObject.get(), models().singleTexture(blockName, new ResourceLocation("flower_pot_cross"), "plant",blockTexture(plantRegistryObject.get())).renderType("cutout"));
    }
    private void registerCustomBlock(Supplier<Block> blockRegistryObject) {
        String descriptionId = blockRegistryObject.get().getDescriptionId();
        String blockName = descriptionId.substring(descriptionId.lastIndexOf('.') + 1);

        ModelFile model = models().getBuilder(blockName)
                .parent(models().getExistingFile(new ResourceLocation("minecraft", "block/cube_bottom_top")))
                .texture("top", modLoc("block/" + blockName + "_top"))
                .texture("side", modLoc("block/" + blockName + "_side"))
                .texture("bottom", modLoc("block/" + blockName + "_bottom"));

        simpleBlockWithItem(blockRegistryObject.get(), model);
    }

    private void wallBlockItem(Supplier<Block> wallBlock, Supplier<Block> textureBlock) {
        String wallName = ForgeRegistries.BLOCKS.getKey(wallBlock.get()).getPath();
        simpleBlockItem(wallBlock.get(), models().wallInventory(wallName + "_inventory", blockTexture(textureBlock.get())));
    }
}