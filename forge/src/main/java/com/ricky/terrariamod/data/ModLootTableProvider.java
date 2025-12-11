package com.ricky.terrariamod.data;

import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.item.ModItems;
import com.ricky.terrariamod.registry.RegistryObject;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;

public class ModLootTableProvider extends BlockLootSubProvider {
    public ModLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // Example blocks
        dropSelf(ModBlocks.COBALT_BLOCK.get());
        dropSelf(ModBlocks.ORICHALCUM_BLOCK.get());
        dropSelf(ModBlocks.ADAMANTITE_BLOCK.get());
        dropSelf(ModBlocks.HELLSTONE_BLOCK.get());

        // Ores
        add(ModBlocks.COBALT_ORE.get(), createOreDrop(ModBlocks.COBALT_ORE.get(), ModItems.COBALT_RAW.get()));
        add(ModBlocks.DEEPSLATE_COBALT_ORE.get(), createOreDrop(ModBlocks.DEEPSLATE_COBALT_ORE.get(), ModItems.COBALT_RAW.get()));
        add(ModBlocks.ORICHALCUM_ORE.get(), createOreDrop(ModBlocks.ORICHALCUM_ORE.get(), ModItems.ORICHALCUM_RAW.get()));
        add(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get(), createOreDrop(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get(), ModItems.ORICHALCUM_RAW.get()));
        add(ModBlocks.ADAMANTITE_ORE.get(), createOreDrop(ModBlocks.ADAMANTITE_ORE.get(), ModItems.ADAMANTITE_RAW.get()));
        add(ModBlocks.DEEPSLATE_ADAMANTITE_ORE.get(), createOreDrop(ModBlocks.DEEPSLATE_ADAMANTITE_ORE.get(), ModItems.ADAMANTITE_RAW.get()));
        add(ModBlocks.HELLSTONE_ORE.get(), createOreDrop(ModBlocks.HELLSTONE_ORE.get(), ModItems.HELLSTONE_RAW.get()));

        dropSelf(ModBlocks.EBON_STONE.get());
        dropSelf(ModBlocks.EBON_ICE.get());
        dropSelf(ModBlocks.EBON_SAND.get());
        dropSelf(ModBlocks.EBON_SANDSTONE.get());
        dropSelf(ModBlocks.CRIM_STONE.get());
        dropSelf(ModBlocks.CRIM_ICE.get());
        dropSelf(ModBlocks.CRIM_SAND.get());
        dropSelf(ModBlocks.CRIM_SANDSTONE.get());
        dropSelf(ModBlocks.PEARL_STONE.get());
        dropSelf(ModBlocks.PEARL_ICE.get());
        dropSelf(ModBlocks.PEARL_SAND.get());
        dropSelf(ModBlocks.PEARL_SANDSTONE.get());
        dropSelf(ModBlocks.DEATH_WEED.get());
        dropPottedContents(ModBlocks.POTTED_DEATH_WEED.get());
        dropSelf(ModBlocks.SHIVER_THORN.get());
        dropPottedContents(ModBlocks.POTTED_SHIVER_THORN.get());
        dropSelf(ModBlocks.VILE_MUSHROOM.get());
        dropPottedContents(ModBlocks.POTTED_VILE_MUSHROOM.get());
        dropSelf(ModBlocks.VICIOUS_MUSHROOM.get());
        dropPottedContents(ModBlocks.POTTED_VICIOUS_MUSHROOM.get());
        dropSelf(ModBlocks.GLOWING_MUSHROOM.get());
        dropPottedContents(ModBlocks.POTTED_GLOWING_MUSHROOM.get());
        dropSelf(ModBlocks.GLOWING_MUSHROOM_BLOCK.get());
        dropSelf(ModBlocks.GLOWING_MUSHROOM_STEM.get());
        dropSelf(ModBlocks.GLOWING_MOSS.get());
        dropSelf(ModBlocks.ICICLE.get());

        dropSelf(ModBlocks.EBON_LOG.get());
        dropSelf(ModBlocks.EBON_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_EBON_LOG.get());
        dropSelf(ModBlocks.STRIPPED_EBON_WOOD.get());
        dropSelf(ModBlocks.EBON_PLANKS.get());
        dropSelf(ModBlocks.EBON_SAPLING.get());
        add(ModBlocks.EBON_LEAVES.get(), createLeavesDrops(ModBlocks.EBON_LEAVES.get(), ModBlocks.EBON_SAPLING.get(), 0.0025f));
        dropPottedContents(ModBlocks.POTTED_EBON_SAPLING.get());
        dropSelf(ModBlocks.CRIM_LOG.get());
        dropSelf(ModBlocks.CRIM_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_CRIM_LOG.get());
        dropSelf(ModBlocks.STRIPPED_CRIM_WOOD.get());
        dropSelf(ModBlocks.CRIM_PLANKS.get());
        dropSelf(ModBlocks.CRIM_SAPLING.get());
        add(ModBlocks.CRIM_LEAVES.get(), createLeavesDrops(ModBlocks.CRIM_LEAVES.get(), ModBlocks.CRIM_SAPLING.get(), 0.0025f));
        dropPottedContents(ModBlocks.POTTED_CRIM_SAPLING.get());
        dropSelf(ModBlocks.PEARL_LOG.get());
        dropSelf(ModBlocks.PEARL_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_PEARL_LOG.get());
        dropSelf(ModBlocks.STRIPPED_PEARL_WOOD.get());
        dropSelf(ModBlocks.PEARL_PLANKS.get());
        dropSelf(ModBlocks.PEARL_SAPLING.get());
        add(ModBlocks.PEARL_LEAVES.get(), createLeavesDrops(ModBlocks.PEARL_LEAVES.get(), ModBlocks.PEARL_SAPLING.get(), 0.0025f));
        dropPottedContents(ModBlocks.POTTED_PEARL_SAPLING.get());


        dropSelf(ModBlocks.EBON_STAIRS.get());
        dropSelf(ModBlocks.EBON_SLAB.get());
        dropSelf(ModBlocks.EBON_BUTTON.get());
        dropSelf(ModBlocks.EBON_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.EBON_FENCE.get());
        dropSelf(ModBlocks.EBON_FENCE_GATE.get());
        dropSelf(ModBlocks.CRIM_STAIRS.get());
        dropSelf(ModBlocks.CRIM_SLAB.get());
        dropSelf(ModBlocks.CRIM_BUTTON.get());
        dropSelf(ModBlocks.CRIM_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.CRIM_FENCE.get());
        dropSelf(ModBlocks.CRIM_FENCE_GATE.get());
        dropSelf(ModBlocks.PEARL_STAIRS.get());
        dropSelf(ModBlocks.PEARL_SLAB.get());
        dropSelf(ModBlocks.PEARL_BUTTON.get());
        dropSelf(ModBlocks.PEARL_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.PEARL_FENCE.get());
        dropSelf(ModBlocks.PEARL_FENCE_GATE.get());

        add(ModBlocks.PORTAL_BLOCK.get(), noDrop());

        // Dungeon Blocks
        dropSelf(ModBlocks.DUNGEON_TILE_GREEN.get());
        dropSelf(ModBlocks.DUNGEON_TILE_GREEN_SLAB.get());
        dropSelf(ModBlocks.DUNGEON_TILE_GREEN_STAIRS.get());
        dropSelf(ModBlocks.DUNGEON_TILE_GREEN_WALL.get());
        dropSelf(ModBlocks.DUNGEON_BRICK_GREEN.get());
        dropSelf(ModBlocks.DUNGEON_BRICK_GREEN_SLAB.get());
        dropSelf(ModBlocks.DUNGEON_BRICK_GREEN_STAIRS.get());
        dropSelf(ModBlocks.DUNGEON_BRICK_GREEN_WALL.get());

        // Fragile blocks - no drops when broken
        add(ModBlocks.DUNGEON_TILE_GREEN_FRAGILE.get(), noDrop());
        add(ModBlocks.DUNGEON_BRICK_GREEN_FRAGILE.get(), noDrop());

        // Special blocks
        dropSelf(ModBlocks.SPIKE_BLOCK.get());
        dropSelf(ModBlocks.LOCKED_GOLDEN_CHEST.get());
        dropSelf(ModBlocks.GOLDEN_CHEST.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }
}
