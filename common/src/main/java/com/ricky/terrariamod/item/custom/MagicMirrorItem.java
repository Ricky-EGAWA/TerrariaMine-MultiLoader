package com.ricky.terrariamod.item.custom;

import com.ricky.terrariamod.util.ManaUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
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

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ParametersAreNonnullByDefault
public class MagicMirrorItem extends Item {

    public MagicMirrorItem(Properties settings) {
        super(settings);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (world.isClientSide) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }

        if (player instanceof ServerPlayer serverPlayer) {
            ManaUtil.useMana(player, 10);

            BlockPos respawnPos = serverPlayer.getRespawnPosition();
            if (respawnPos == null || !serverPlayer.getRespawnDimension().equals(world.dimension())) {
                respawnPos = world.getSharedSpawnPos();
            }

            MinecraftServer server = serverPlayer.server;
            BlockPos finalRespawnPos = respawnPos.immutable();

            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(() -> server.execute(() -> {
                if (serverPlayer.isAlive()) {
                    ServerLevel respawnWorld = server.getLevel(serverPlayer.getRespawnDimension());
                    if (respawnWorld == null) return;

                    serverPlayer.teleportTo(
                            respawnWorld,
                            finalRespawnPos.getX() + 0.5,
                            finalRespawnPos.getY(),
                            finalRespawnPos.getZ() + 0.5,
                            serverPlayer.getYRot(),
                            serverPlayer.getXRot()
                    );
                    serverPlayer.fallDistance = 0;
                }
            }), 2, TimeUnit.SECONDS);
            //TODO add effect

            return InteractionResultHolder.success(player.getItemInHand(hand));
        }

        return InteractionResultHolder.fail(player.getItemInHand(hand));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        tooltip.add(Component.translatable("tooltip.terrariamod.magic_mirror.tooltip"));
    }
}
