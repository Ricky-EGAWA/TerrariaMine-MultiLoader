package com.ricky.terrariamod.data;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
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
        // COBALT 系
        blockWithItem(ModBlocks.COBALT_BLOCK);
        blockWithItem(ModBlocks.COBALT_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_COBALT_ORE);

        // ORICHALCUM 系
        blockWithItem(ModBlocks.ORICHALCUM_BLOCK);
        blockWithItem(ModBlocks.ORICHALCUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ORICHALCUM_ORE);

        // ADAMANTITE 系
        blockWithItem(ModBlocks.ADAMANTITE_BLOCK);
        blockWithItem(ModBlocks.ADAMANTITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ADAMANTITE_ORE);

        // HELLSTONE 系
        blockWithItem(ModBlocks.HELLSTONE_BLOCK);
        blockWithItem(ModBlocks.HELLSTONE_ORE);

        // EBON 系
        blockWithItem(ModBlocks.EBON_STONE);
        blockWithItem(ModBlocks.EBON_SAND);
        registerCustomBlock(ModBlocks.EBON_SANDSTONE);
        blockWithItem(ModBlocks.EBON_ICE);

        // CRIM 系
        blockWithItem(ModBlocks.CRIM_STONE);
        blockWithItem(ModBlocks.CRIM_SAND);
        registerCustomBlock(ModBlocks.CRIM_SANDSTONE);
        blockWithItem(ModBlocks.CRIM_ICE);

        // PEARL 系
        blockWithItem(ModBlocks.PEARL_STONE);
        blockWithItem(ModBlocks.PEARL_SAND);
        registerCustomBlock(ModBlocks.PEARL_SANDSTONE);
        blockWithItem(ModBlocks.PEARL_ICE);

        // 花系
        plantBlockWithItem(ModBlocks.DEATH_WEED);
        pottedPlantBlockWithItem(ModBlocks.POTTED_DEATH_WEED, ModBlocks.DEATH_WEED);
        plantBlockWithItem(ModBlocks.SHIVER_THORN);
        pottedPlantBlockWithItem(ModBlocks.POTTED_SHIVER_THORN, ModBlocks.SHIVER_THORN);
        plantBlockWithItem(ModBlocks.VILE_MUSHROOM);
        pottedPlantBlockWithItem(ModBlocks.POTTED_VILE_MUSHROOM, ModBlocks.VILE_MUSHROOM);
        plantBlockWithItem(ModBlocks.VICIOUS_MUSHROOM);
        pottedPlantBlockWithItem(ModBlocks.POTTED_VICIOUS_MUSHROOM, ModBlocks.VICIOUS_MUSHROOM);

        // GLOWING 系
        blockWithItem(ModBlocks.GLOWING_MOSS);
        blockWithItem(ModBlocks.GLOWING_MUSHROOM_BLOCK);
        blockWithItem(ModBlocks.GLOWING_MUSHROOM_STEM);

        // ICICLE 系
//        blockWithItem(ModBlocks.ICICLE);

        //region 木材
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
//        saplingBlock(ModBlocks.EBON_SAPLING);

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
//        saplingBlock(ModBlocks.CRIM_SAPLING);

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
//        saplingBlock(ModBlocks.PEARL_SAPLING);

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
        // ブロックの登録名（ID）を取得
        String descriptionId = blockRegistryObject.get().getDescriptionId();
        String blockName = descriptionId.substring(descriptionId.lastIndexOf('.') + 1);

        // モデルの作成
        ModelFile model = models().getBuilder(blockName)
                .parent(models().getExistingFile(new ResourceLocation("minecraft", "block/cube_bottom_top")))
                .texture("top", modLoc("block/" + blockName + "_top"))
                .texture("side", modLoc("block/" + blockName + "_side"))
                .texture("bottom", modLoc("block/" + blockName + "_bottom"));

        // モデルを登録
        simpleBlockWithItem(blockRegistryObject.get(), model);
    }
}