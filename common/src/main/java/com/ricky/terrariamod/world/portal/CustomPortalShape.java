package com.ricky.terrariamod.world.portal;

import com.ricky.terrariamod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CustomPortalShape {
    private static final int MIN_WIDTH = 2;
    private static final int MAX_WIDTH = 21;
    private static final int MIN_HEIGHT = 3;
    private static final int MAX_HEIGHT = 21;

    private final Level level;
    private final BlockPos origin;
    private BlockPos bottomLeft;
    private Direction.Axis axis;
    private Direction rightDir;
    private int width;
    private int height;

    public CustomPortalShape(Level level, BlockPos pos, Direction.Axis axis) {
        this.level = level;
        this.origin = pos;
        this.axis = axis;
        this.rightDir = axis == Direction.Axis.X ? Direction.WEST : Direction.NORTH;
        this.bottomLeft = calculateBottomLeft(pos);
        if (bottomLeft != null) {
            this.width = calculateWidth();
            if (width > 0) {
                this.height = calculateHeight();
            }
        }
    }

    private BlockPos calculateBottomLeft(BlockPos pos) {
        BlockPos.MutableBlockPos mutable = pos.mutable();
        while (isAirOrPortal(level.getBlockState(mutable.below())) && mutable.getY() > level.getMinBuildHeight()) {
            mutable.move(Direction.DOWN);
        }

        int offset = getDistanceUntilEdge(mutable, rightDir.getOpposite()) - 1;
        return offset < 0 ? null : mutable.relative(rightDir.getOpposite(), offset);
    }

    private int getDistanceUntilEdge(BlockPos start, Direction dir) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int i = 0; i <= MAX_WIDTH; i++) {
            pos.set(start).move(dir, i);
            BlockState state = level.getBlockState(pos);
            if (isEndFrame(state)) return i;
            if (!isAirOrPortal(state)) break;
        }

        return 0;
    }

    private int calculateWidth() {
        int width = getDistanceUntilEdge(bottomLeft, rightDir);
        return width >= MIN_WIDTH && width <= MAX_WIDTH ? width : 0;
    }

    private int calculateHeight() {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int height = getDistanceUntilTop(pos);
        return height >= MIN_HEIGHT && height <= MAX_HEIGHT && hasTopFrame(pos, height) ? height : 0;
    }

    private int getDistanceUntilTop(BlockPos.MutableBlockPos pos) {
        for (int h = 0; h < MAX_HEIGHT; h++) {
            // 左のフレーム
            pos.set(bottomLeft).move(Direction.UP, h).move(rightDir, -1);
            if (!isEndFrame(level.getBlockState(pos))) {
                return h;
            }

            // 右のフレーム
            pos.set(bottomLeft).move(Direction.UP, h).move(rightDir, width);
            if (!isEndFrame(level.getBlockState(pos))) {
                return h;
            }

            // 中身が空かポータルか
            for (int w = 0; w < width; w++) {
                pos.set(bottomLeft).move(Direction.UP, h).move(rightDir, w);
                BlockState state = level.getBlockState(pos);
                if (!isAirOrPortal(state)) return h;
            }
        }
        return MAX_HEIGHT;
    }

    private boolean hasTopFrame(BlockPos.MutableBlockPos pos, int height) {
        for (int w = 0; w < width; w++) {
            pos.set(bottomLeft).move(Direction.UP, height).move(rightDir, w);
            if (!isEndFrame(level.getBlockState(pos))) {
                return false;
            }
        }
        return true;
    }


    public boolean isValid() {
        return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
    }

    public void createPortal() {
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                BlockPos pos = bottomLeft.relative(rightDir, w).relative(Direction.UP, h);
                level.setBlock(pos, ModBlocks.PORTAL_BLOCK.get().defaultBlockState().setValue(NetherPortalBlock.AXIS, axis), 2);
            }
        }
    }

    private boolean isAirOrPortal(BlockState state) {
        return state.isAir() || state.is(ModBlocks.PORTAL_BLOCK.get());
    }

    private boolean isEndFrame(BlockState state) {
        return state.is(Blocks.END_STONE); // ← フレームに使いたいブロックに置き換え
    }
}
