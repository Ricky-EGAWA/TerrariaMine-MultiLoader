package com.ricky.terrariamod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class FragileBlock extends Block {
    private static final int BREAK_DELAY_TICKS = 10; // 0.5 seconds delay before breaking

    public FragileBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof Player && !level.isClientSide) {
            // Schedule the block to break after a short delay
            level.scheduleTick(pos, this, BREAK_DELAY_TICKS);
        }
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Break the block without dropping anything
        level.destroyBlock(pos, false);
    }
}
