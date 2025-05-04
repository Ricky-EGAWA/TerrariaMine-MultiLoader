package com.ricky.terrariamod.item.custom;

import com.ricky.terrariamod.block.ModBlocks;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            Player player = pContext.getPlayer();
            BlockPos center = player.blockPosition();
            Level level = player.level();
            boolean foundBlock = false;

            int range = 10;

            for (BlockPos pos : BlockPos.betweenClosed(
                    center.offset(-range, -range, -range),
                    center.offset(range, range, range))) {
                BlockState state = level.getBlockState(pos);

                if (isValuableBlock(state)) {
                    outputValuableCoordinates(pos, player, state.getBlock());
                    foundBlock = true;
                    break;
                }
            }

            if(!foundBlock) {
                player.sendSystemMessage(Component.translatable("message.terrariamod.metal_detector.not_found"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.translatable("message.terrariamod.metal_detector.found", I18n.get(block.getDescriptionId())));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModBlocks.COBALT_ORE.get()) || state.is(ModBlocks.ORICHALCUM_ORE.get())
                || state.is(ModBlocks.ADAMANTITE_ORE.get());
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.terrariamod.metal_detector.tooltip"));
        super.appendHoverText(stack, level, tooltip, flag);
    }

}
