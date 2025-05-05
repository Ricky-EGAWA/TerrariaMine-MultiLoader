package com.ricky.terrariamod.world.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class CustomPortalTeleporter {

    public void teleportToEndDimension(Player player, BlockPos portalPos) {
        if (player instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            ServerLevel currentLevel = serverPlayer.serverLevel();
            ServerLevel endLevel = currentLevel.getServer().getLevel(Level.END);  // エンドディメンションにテレポート

            // プレイヤーがポータルを通過したときにエンドディメンションにテレポート
            serverPlayer.teleportTo(endLevel, portalPos.getX(), portalPos.getY(), portalPos.getZ(), serverPlayer.getYRot(), serverPlayer.getXRot());

            // メッセージを表示（オプション）
//            serverPlayer.sendMessage(Component.translatable("You have entered the End!"), true);
        }
    }
}
