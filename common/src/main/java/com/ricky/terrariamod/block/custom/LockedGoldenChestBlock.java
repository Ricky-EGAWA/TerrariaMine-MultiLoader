package com.ricky.terrariamod.block.custom;

import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

public class LockedGoldenChestBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public LockedGoldenChestBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);

        // Check if player is holding a golden key
        if (itemStack.is(ModItems.GOLDEN_KEY.get())) {
            if (!level.isClientSide) {
                // Consume the golden key
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }

                // Replace with unlocked golden chest, keeping the same facing direction
                BlockState newState = ModBlocks.GOLDEN_CHEST.get().defaultBlockState()
                        .setValue(GoldenChestBlock.FACING, state.getValue(FACING));
                level.setBlock(pos, newState, 3);

                // Play unlock sound
                level.playSound(null, pos, SoundEvents.CHEST_LOCKED, SoundSource.BLOCKS, 1.0f, 1.0f);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }

    // Prevent explosion destruction
    @Override
    public float getExplosionResistance() {
        return 3600000.0F;
    }
}
