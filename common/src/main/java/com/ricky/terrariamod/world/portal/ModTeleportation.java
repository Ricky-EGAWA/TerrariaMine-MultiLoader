package com.ricky.terrariamod.world.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class ModTeleportation {
    public static final ResourceKey<Level> TERRARIA_DIMENSION = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation("terraria_dimension"));

    public static void teleportPlayer(ServerPlayer player) {
        ServerLevel currentLevel = player.serverLevel();
        ServerLevel targetLevel = currentLevel.dimension() == Level.OVERWORLD
                ? player.server.getLevel(TERRARIA_DIMENSION)
                : player.server.getLevel(Level.OVERWORLD);

        if (targetLevel != null && targetLevel != currentLevel) {
            BlockPos targetPos = findSpawnPos(targetLevel);
            player.teleportTo(targetLevel, targetPos.getX() + 0.5, targetPos.getY(), targetPos.getZ() + 0.5, player.getYRot(), player.getXRot());
        }
    }

    private static BlockPos findSpawnPos(ServerLevel level) {
        // 必要ならポータル生成ロジックと連携
        return level.getSharedSpawnPos();
    }
}
