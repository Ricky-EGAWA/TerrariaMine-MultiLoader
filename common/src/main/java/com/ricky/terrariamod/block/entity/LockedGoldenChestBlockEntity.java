package com.ricky.terrariamod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LockedGoldenChestBlockEntity extends BlockEntity {
    public LockedGoldenChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LOCKED_GOLDEN_CHEST.get(), pos, state);
    }
}
