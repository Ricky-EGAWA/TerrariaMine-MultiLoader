package com.ricky.terrariamod.block.custom;

import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModSaplingBlock extends SaplingBlock {

    public ModSaplingBlock(AbstractTreeGrower treeGrower, BlockBehaviour.Properties properties) {
        super(treeGrower, properties);
    }
}
