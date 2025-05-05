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
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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
        PoiManager poiManager = this.level.getPoiManager();
        int searchRadius = isLarge ? 16 : 128;
        poiManager.ensureLoadedAndValid(this.level, pos, searchRadius);

        // getInSquare()の戻り値を明示的にキャスト
        Optional<PoiRecord> portalRecord = poiManager.getInSquare(poi -> {
                    // PoiTypes をカスタムポータルに変更
                    return poi.is(PoiTypes.NETHER_PORTAL);  // ここをカスタムポータルに変更
                }, pos, searchRadius, PoiManager.Occupancy.ANY)
                .filter(record -> worldBorder.isWithinBounds(record.getPos()))
                .sorted(Comparator.comparingDouble((PoiRecord record) -> record.getPos().distSqr(pos))  // 明示的にPoiRecordを指定
                        .thenComparingInt(record -> record.getPos().getY()))
                .findFirst();

        // Optional<PoiRecord>が存在する場合に処理を行う
        return portalRecord.map(record -> {
            BlockPos portalPos = record.getPos();  // PoiRecord型でgetPos()を呼び出し
            this.level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(portalPos), 3, portalPos);
            BlockState portalState = this.level.getBlockState(portalPos);
            return BlockUtil.getLargestRectangleAround(portalPos, portalState.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Axis.Y, 21, blockPos -> {
                return this.level.getBlockState(blockPos) == portalState;
            });
        });
    }


    // カスタムポータルを作成
    public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pos, Direction.Axis axis) {
        Direction direction = Direction.get(AxisDirection.POSITIVE, axis);
        double nearestDistance = -1.0;
        BlockPos nearestPos = null;
        double secondaryDistance = -1.0;
        BlockPos secondaryPos = null;
        WorldBorder worldBorder = this.level.getWorldBorder();
        int maxHeight = Math.min(this.level.getMaxBuildHeight(), this.level.getMinBuildHeight() + this.level.getLogicalHeight()) - 1;
        BlockPos.MutableBlockPos mutablePos = pos.mutable();
        Iterator<BlockPos.MutableBlockPos> spiralIterator = BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH).iterator();

        while (spiralIterator.hasNext()) {
            BlockPos.MutableBlockPos spiralPos = spiralIterator.next();
            int maxY = Math.min(maxHeight, this.level.getHeight(Types.MOTION_BLOCKING, spiralPos.getX(), spiralPos.getZ()));
            
            // ポータルのフレーム作成のロジック（ポータルブロックの設置）
            if (this.canHostFrame(spiralPos, mutablePos, direction, 0)) {
                double distance = pos.distSqr(spiralPos);
                if (this.canHostFrame(spiralPos, mutablePos, direction, -1) && this.canHostFrame(spiralPos, mutablePos, direction, 1) && (nearestDistance == -1.0 || nearestDistance > distance)) {
                    nearestDistance = distance;
                    nearestPos = spiralPos.immutable();
                }

                if (nearestDistance == -1.0 && (secondaryDistance == -1.0 || secondaryDistance > distance)) {
                    secondaryDistance = distance;
                    secondaryPos = spiralPos.immutable();
                }
            }
        }

        // ポータルフレーム設置
        if (nearestPos != null) {
            BlockState portalFrame = Blocks.END_STONE.defaultBlockState();
            for (int i = -1; i < 3; i++) {
                for (int j = -1; j < 4; j++) {
                    if (i == -1 || i == 2 || j == -1 || j == 3) {
                        mutablePos.setWithOffset(nearestPos, i * direction.getStepX(), j, i * direction.getStepZ());
                        this.level.setBlock(mutablePos, portalFrame, 3);
                    }
                }
            }

            // カスタムポータルのブロックを設置（カスタムポータルのために変更）
            BlockState customPortalState = ModBlocks.PORTAL_BLOCK.get().defaultBlockState().setValue(ModPortalBlock.AXIS, axis); // ここでカスタムポータルブロックを指定
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    mutablePos.setWithOffset(nearestPos, i * direction.getStepX(), j, i * direction.getStepZ());
                    this.level.setBlock(mutablePos, customPortalState, 18);
                }
            }

            return Optional.of(new BlockUtil.FoundRectangle(nearestPos.immutable(), 2, 3));
        }

        return Optional.empty();
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
