package com.ricky.terrariamod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.ricky.terrariamod.entity.boss.EyeOfCthulhuEntity;

import java.util.List;

public class SuspiciousLookingEyeItem extends Item {

    public SuspiciousLookingEyeItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            long timeOfDay = level.getDayTime() % 24000;

            if (isNight(timeOfDay)) {
                BlockPos spawnPos = serverPlayer.blockPosition().offset(10, 7, 0);
                EyeOfCthulhuEntity.summonBoss((ServerLevel) level, spawnPos);

                serverPlayer.displayClientMessage(Component.translatable("message.terrariamod.summon_success"), true);
                stack.shrink(1);
                return InteractionResultHolder.success(stack);
            } else {
                serverPlayer.displayClientMessage(Component.translatable("message.terrariamod.not_night"), true);
                return InteractionResultHolder.fail(stack);
            }
        }

        return InteractionResultHolder.pass(stack);
    }

    private boolean isNight(long timeOfDay) {
        return timeOfDay >= 12541 && timeOfDay <= 23458;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.terrariamod.summon_eye.tooltip"));
    }
}
