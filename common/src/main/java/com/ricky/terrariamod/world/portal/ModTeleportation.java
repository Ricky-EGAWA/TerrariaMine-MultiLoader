package com.ricky.terrariamod.world.portal;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.border.WorldBorder;

import java.util.Optional;

public class ModTeleportation {
    public static final ResourceKey<Level> TERRARIA_DIMENSION = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation("terraria_dimension"));

    public static void teleportPlayer(ServerPlayer player) {
        ServerLevel currentLevel = player.serverLevel();
        ServerLevel targetLevel = currentLevel.dimension() == Level.OVERWORLD
                ? player.server.getLevel(TERRARIA_DIMENSION)
                : player.server.getLevel(Level.OVERWORLD);

        if (targetLevel == null || targetLevel == currentLevel) return;

        BlockPos playerPos = player.blockPosition();
        CustomPortalForcer forcer = new CustomPortalForcer(targetLevel);
        WorldBorder worldBorder = targetLevel.getWorldBorder();

        Optional<BlockUtil.FoundRectangle> portal = forcer.findPortalAround(playerPos, false, worldBorder);

        System.out.println(portal);
        targetLevel.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(playerPos), 3, playerPos);


        if (portal.isEmpty()) {
            System.out.println("create portal");
            portal = forcer.createPortal(playerPos, Direction.Axis.X);
        }
        portal.ifPresent(rectangle -> {
            BlockPos min = rectangle.minCorner;
            int width = rectangle.axis1Size;
            int height = rectangle.axis2Size;

            BlockPos targetPos = min.offset(width, 0, height);
            System.out.println("teleport");
            player.teleportTo(targetLevel,
                    targetPos.getX() + 0.5,
                    targetPos.getY(),
                    targetPos.getZ() + 0.5,
                    player.getYRot(),
                    player.getXRot()
            );
        });

    }
}
