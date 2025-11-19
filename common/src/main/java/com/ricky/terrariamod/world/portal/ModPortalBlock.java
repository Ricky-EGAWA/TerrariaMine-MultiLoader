package com.ricky.terrariamod.world.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
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
        if (!level.isClientSide && entity.canChangeDimensions()) {
            // ポータル内にいることを記録
            if (entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            } else {
                // プレイヤーがポータル内にいる場合
                if (entity.level().dimension() != ModTeleporter.TERRARIA_DIMENSION &&
                    entity.level().dimension() != Level.OVERWORLD) {
                    entity.setPortalCooldown();
                    return;
                }

                // ポータルタイマーを更新
                if (entity instanceof ServerPlayer player) {
                    // バニラと同じく、portalTimeをカウント
                    // 最大80ティック（4秒）でテレポート
                    if (player.portalTime >= player.getPortalWaitTime()) {
                        ServerLevel serverLevel = (ServerLevel) level;
                        ServerLevel targetLevel = serverLevel.getServer().getLevel(
                            serverLevel.dimension() == Level.OVERWORLD
                                ? ModTeleporter.TERRARIA_DIMENSION
                                : Level.OVERWORLD
                        );

                        if (targetLevel != null) {
                            player.changeDimension(targetLevel, new ModTeleporter(targetLevel, Direction.Axis.X));
                        }

                        player.portalTime = 0;
                        player.setPortalCooldown();
                    }
                }
            }
        }
    }
}
