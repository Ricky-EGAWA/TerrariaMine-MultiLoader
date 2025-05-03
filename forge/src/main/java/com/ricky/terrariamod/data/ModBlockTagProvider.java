package com.ricky.terrariamod.data;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        // Tool tags
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.COBALT_ORE.get())
                .add(ModBlocks.DEEPSLATE_COBALT_ORE.get())
                .add(ModBlocks.COBALT_BLOCK.get())
                .add(ModBlocks.ORICHALCUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get())
                .add(ModBlocks.ORICHALCUM_BLOCK.get())
                .add(ModBlocks.ADAMANTITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_ADAMANTITE_ORE.get())
                .add(ModBlocks.ADAMANTITE_BLOCK.get())
                .add(ModBlocks.HELLSTONE_ORE.get())
                .add(ModBlocks.HELLSTONE_BLOCK.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL);

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.COBALT_BLOCK.get())
                .add(ModBlocks.ORICHALCUM_BLOCK.get())
                .add(ModBlocks.ADAMANTITE_BLOCK.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.HELLSTONE_BLOCK.get());

        // Custom tool levels (Forge-style)
        this.tag(TagKey.create(net.minecraft.core.registries.Registries.BLOCK,
                new ResourceLocation("forge", "needs_tool_level_4")))
                .add(ModBlocks.COBALT_ORE.get())
                .add(ModBlocks.DEEPSLATE_COBALT_ORE.get());

        this.tag(TagKey.create(net.minecraft.core.registries.Registries.BLOCK,
                new ResourceLocation("forge", "needs_tool_level_5")))
                .add(ModBlocks.ORICHALCUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get());

        this.tag(TagKey.create(net.minecraft.core.registries.Registries.BLOCK,
                new ResourceLocation("forge", "needs_tool_level_6")))
                .add(ModBlocks.ADAMANTITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_ADAMANTITE_ORE.get());

        // Wood and structural tags
        this.tag(BlockTags.FENCES)
                .add(ModBlocks.EBON_FENCE.get())
                .add(ModBlocks.CRIM_FENCE.get())
                .add(ModBlocks.PEARL_FENCE.get());

        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.EBON_FENCE_GATE.get())
                .add(ModBlocks.CRIM_FENCE_GATE.get())
                .add(ModBlocks.PEARL_FENCE_GATE.get());

        this.tag(BlockTags.WALLS); // Add if any wall blocks

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.EBON_LOG.get())
                .add(ModBlocks.EBON_WOOD.get())
                .add(ModBlocks.STRIPPED_EBON_LOG.get())
                .add(ModBlocks.STRIPPED_EBON_WOOD.get())
                .add(ModBlocks.CRIM_LOG.get())
                .add(ModBlocks.CRIM_WOOD.get())
                .add(ModBlocks.STRIPPED_CRIM_LOG.get())
                .add(ModBlocks.STRIPPED_CRIM_WOOD.get())
                .add(ModBlocks.PEARL_LOG.get())
                .add(ModBlocks.PEARL_WOOD.get())
                .add(ModBlocks.STRIPPED_PEARL_LOG.get())
                .add(ModBlocks.STRIPPED_PEARL_WOOD.get());
    }
}
