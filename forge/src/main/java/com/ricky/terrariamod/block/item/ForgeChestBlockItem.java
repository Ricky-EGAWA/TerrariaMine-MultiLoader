package com.ricky.terrariamod.block.item;

import com.ricky.terrariamod.client.render.ChestItemClientExtensions;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ForgeChestBlockItem extends BlockItem {
    public ForgeChestBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(ChestItemClientExtensions.INSTANCE);
    }
}
