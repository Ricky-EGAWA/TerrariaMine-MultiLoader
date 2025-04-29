package com.ricky.terrariamod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class InfiniteWaterBucketItem extends BucketItem {

    public InfiniteWaterBucketItem(Properties properties) {
        super(Fluids.WATER, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        Player player = context.getPlayer();
        BlockPos offsetPos = pos.relative(direction);

        if (!level.mayInteract(player, pos)) {
            return InteractionResult.FAIL;
        }

        BlockHitResult hitResult = new BlockHitResult(context.getClickLocation(), direction, pos, false);

        if (this.emptyContents(player, level, offsetPos, hitResult)) {
            this.checkExtraContent(player, level, context.getItemInHand(), offsetPos);
            if (player != null) {
                player.awardStat(Stats.ITEM_USED.get(this));
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);

        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemStack);
        }

        BlockPos pos = hitResult.getBlockPos();
        Direction direction = hitResult.getDirection();
        BlockPos offsetPos = pos.relative(direction);

        if (!level.mayInteract(player, pos)) {
            return InteractionResultHolder.fail(itemStack);
        }

        if (this.emptyContents(player, level, offsetPos, hitResult)) {
            this.checkExtraContent(player, level, itemStack, offsetPos);
            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.success(itemStack);
        }

        return InteractionResultHolder.fail(itemStack);
    }
}
