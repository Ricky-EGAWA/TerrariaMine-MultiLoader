package com.ricky.terrariamod.world.portal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;

import com.ricky.terrariamod.block.ModBlocks;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap.Types;

public class CustomPortalForcer {
    private static final int TICKET_RADIUS = 3;
    private static final int SEARCH_RADIUS = 128;
    private static final int CREATE_RADIUS = 16;
    private static final int FRAME_HEIGHT = 5;
    private static final int FRAME_WIDTH = 4;
    private static final int FRAME_BOX = 3;
    private static final int FRAME_HEIGHT_START = -1;
    private static final int FRAME_HEIGHT_END = 4;
    private static final int FRAME_WIDTH_START = -1;
    private static final int FRAME_WIDTH_END = 3;
    private static final int FRAME_BOX_START = -1;
    private static final int FRAME_BOX_END = 2;
    private static final int NOTHING_FOUND = -1;
    private final ServerLevel level;

    public CustomPortalForcer(ServerLevel level) {
        this.level = level;
    }

    // カスタムポータル周辺を探す
    public Optional<BlockUtil.FoundRectangle> findPortalAround(BlockPos pos, boolean isLarge, WorldBorder worldBorder) {
        int searchRadius = isLarge ? 16 : 128;
        BlockState targetPortalState = ModBlocks.PORTAL_BLOCK.get().defaultBlockState();

        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        BlockPos closestPortal = null;
        double closestDistance = Double.MAX_VALUE;

        for (int dx = -searchRadius; dx <= searchRadius; dx++) {
            for (int dy = -searchRadius; dy <= searchRadius; dy++) {
                for (int dz = -searchRadius; dz <= searchRadius; dz++) {
                    mutablePos.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);

                    if (!worldBorder.isWithinBounds(mutablePos)) continue;

                    BlockState state = this.level.getBlockState(mutablePos);
                    if (state.is(targetPortalState.getBlock())) {
                        double distance = pos.distSqr(mutablePos);
                        if (distance < closestDistance) {
                            closestPortal = mutablePos.immutable();
                            closestDistance = distance;
                        }
                    }
                }
            }
        }

        if (closestPortal != null) {
            this.level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(closestPortal), 3, closestPortal);
            BlockState portalState = this.level.getBlockState(closestPortal);
            Direction.Axis axis = portalState.getValue(ModPortalBlock.AXIS); // カスタムポータルブロックが AXIS を持っている前提
            return Optional.ofNullable(BlockUtil.getLargestRectangleAround(closestPortal, axis, 21, Axis.Y, 21, blockPos ->
                    this.level.getBlockState(blockPos).is(portalState.getBlock())));
        }

        return Optional.empty();
    }



    // カスタムポータルを作成
    public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pos, Direction.Axis axis) {
        System.out.println("createPortal called");

        Direction direction = Direction.get(AxisDirection.POSITIVE, axis);
        Direction right = direction.getClockWise();
        double bestDistance = -1.0;
        BlockPos bestPos = null;
        WorldBorder border = level.getWorldBorder();
        int maxHeight = Math.min(level.getMaxBuildHeight(), level.getMinBuildHeight() + level.getLogicalHeight()) - 1;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        Iterator<BlockPos.MutableBlockPos> iterator = BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH).iterator();

        while (iterator.hasNext()) {
            BlockPos.MutableBlockPos candidate = iterator.next();
            int surfaceY = Math.min(maxHeight, level.getHeight(Types.MOTION_BLOCKING, candidate.getX(), candidate.getZ()));
            candidate.setY(surfaceY);

            if (!border.isWithinBounds(candidate)) continue;
            if (!border.isWithinBounds(candidate.mutable().move(direction))) continue;

            candidate.move(direction.getOpposite());

            for (int y = surfaceY; y >= level.getMinBuildHeight(); y--) {
                candidate.setY(y);
                if (canPortalReplaceBlock(candidate)) {
                    int bottomY = y;
                    while (bottomY > level.getMinBuildHeight() && canPortalReplaceBlock(candidate.move(Direction.DOWN))) {
                        bottomY--;
                    }

                    if (bottomY + 4 > maxHeight) continue;

                    int height = y - bottomY;
                    if (height >= 3) {
                        candidate.setY(bottomY);

                        if (canHostFrame(candidate, mutable, direction, 0)) {
                            double distance = pos.distSqr(candidate);
                            if (bestDistance == -1.0 || distance < bestDistance) {
                                bestDistance = distance;
                                bestPos = candidate.immutable();
                            }
                        }
                    }

                    break; // Skip further checking at this column
                }
            }
        }

        if (bestPos == null) {
            // Fallback position
            int minY = Math.max(level.getMinBuildHeight(), 70);
            int clampedY = Mth.clamp(pos.getY(), minY, maxHeight - 9);
            bestPos = new BlockPos(pos.getX(), clampedY, pos.getZ()).immutable();

            if (!border.isWithinBounds(bestPos)) return Optional.empty();

            // Clear portal space
            for (int dx = -1; dx < 2; dx++) {
                for (int dz = -1; dz < 3; dz++) {
                    for (int dy = -1; dy < 4; dy++) {
                        BlockState state = dy < 0 ? Blocks.END_STONE.defaultBlockState() : Blocks.AIR.defaultBlockState();
                        mutable.setWithOffset(bestPos, dx * direction.getStepX() + dz * right.getStepX(), dy, dx * direction.getStepZ() + dz * right.getStepZ());
                        level.setBlockAndUpdate(mutable, state);
                    }
                }
            }
        }

        // Place frame
        BlockState frameState = Blocks.END_STONE.defaultBlockState();
        for (int i = -1; i < 3; i++) {
            for (int j = -1; j < 4; j++) {
                if (i == -1 || i == 2 || j == -1 || j == 3) {
                    mutable.setWithOffset(bestPos, i * direction.getStepX(), j, i * direction.getStepZ());
                    level.setBlock(mutable, frameState, 3);
                }
            }
        }

        // Place custom portal blocks
        BlockState portalState = ModBlocks.PORTAL_BLOCK.get().defaultBlockState().setValue(ModPortalBlock.AXIS, axis);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                mutable.setWithOffset(bestPos, i * direction.getStepX(), j, i * direction.getStepZ());
                level.setBlock(mutable, portalState, 18);
            }
        }

        return Optional.of(new BlockUtil.FoundRectangle(bestPos, 2, 3));
    }


    private boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pos) {
        BlockState blockState = this.level.getBlockState(pos);
        return blockState.canBeReplaced() && blockState.getFluidState().isEmpty();
    }

    private boolean canHostFrame(BlockPos pos, BlockPos.MutableBlockPos mutablePos, Direction direction, int offset) {
        Direction clockwise = direction.getClockWise();

        for (int i = -1; i < 3; i++) {
            for (int j = -1; j < 4; j++) {
                mutablePos.setWithOffset(pos, direction.getStepX() * i + clockwise.getStepX() * offset, j, direction.getStepZ() * i + clockwise.getStepZ() * offset);
                if (j < 0 && !this.level.getBlockState(mutablePos).isSolid()) {
                    return false;
                }

                if (j >= 0 && !this.canPortalReplaceBlock(mutablePos)) {
                    return false;
                }
            }
        }

        return true;
    }
}
