package com.ricky.terrariamod.data;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

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
    }


    private void blockWithItem(Supplier<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
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