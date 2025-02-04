package com.ricky.terrariamod.block;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.registry.RegistryProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class ModBlocks {
    public static final RegistryProvider<Block> BLOCKS =
            RegistryProvider.get(Registries.BLOCK, Constants.MOD_ID);
    public static final RegistryProvider<Item> ITEMS =
            RegistryProvider.get(Registries.ITEM, Constants.MOD_ID);

    public static final Supplier<Block> COBALT_BLOCK = BLOCKS.register("cobalt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE))
    );
    public static final Supplier<Item> COBALT_BLOCK_ITEM = ITEMS.register("cobalt_block",
            () -> new BlockItem(COBALT_BLOCK.get(), new Item.Properties())
    );

    public static void register() {
    }
}
