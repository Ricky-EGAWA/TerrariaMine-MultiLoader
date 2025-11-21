package com.ricky.terrariamod.world.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModPortalBlock extends NetherPortalBlock {
    public ModPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && entity instanceof ServerPlayer player) {
            if (!player.isPassenger() && !player.isVehicle() && player.canChangeDimensions()) {
                // ポータル内にいるかチェック
                if (player.isOnPortalCooldown()) {
                    player.setPortalCooldown();
                } else {
                    // ポータルタイマーをインクリメント（バニラと同じ）
                    if (player.portalTime >= player.getPortalWaitTime()) {
                        // 80ティック（4秒）経過したのでテレポート
                        player.setPortalCooldown();
                        ModTeleporter.teleportPlayer(player);
                        player.portalTime = 0;
                    }
                }
            }
        }
    }
}
