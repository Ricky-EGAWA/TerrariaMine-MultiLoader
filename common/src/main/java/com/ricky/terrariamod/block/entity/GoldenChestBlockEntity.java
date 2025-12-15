package com.ricky.terrariamod.block.entity;

import com.ricky.terrariamod.block.custom.GoldenChestBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;

public class GoldenChestBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
    private final ChestLidController chestLidController = new ChestLidController();

    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(Level level, BlockPos pos, BlockState state) {
            GoldenChestBlockEntity.playSound(level, pos, state, SoundEvents.CHEST_OPEN);
        }

        @Override
        protected void onClose(Level level, BlockPos pos, BlockState state) {
            GoldenChestBlockEntity.playSound(level, pos, state, SoundEvents.CHEST_CLOSE);
        }

        @Override
        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int oldCount, int newCount) {
            GoldenChestBlockEntity.this.signalOpenCount(level, pos, state, oldCount, newCount);
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (player.containerMenu instanceof ChestMenu chestMenu) {
                Container container = chestMenu.getContainer();
                return container == GoldenChestBlockEntity.this ||
                        (container instanceof CompoundContainer compoundContainer &&
                                compoundContainer.contains(GoldenChestBlockEntity.this));
            }
            return false;
        }
    };

    public GoldenChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GOLDEN_CHEST.get(), pos, state);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.terrariamod.golden_chest");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
        return ChestMenu.threeRows(id, playerInventory, this);
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items);
        }
    }

    public static void lidAnimateTick(Level level, BlockPos pos, BlockState state, GoldenChestBlockEntity blockEntity) {
        blockEntity.chestLidController.tickLid();
    }

    static void playSound(Level level, BlockPos pos, BlockState state, SoundEvent sound) {
        ChestType chestType = state.getValue(GoldenChestBlock.TYPE);
        double x = (double) pos.getX() + 0.5;
        double y = (double) pos.getY() + 0.5;
        double z = (double) pos.getZ() + 0.5;

        if (chestType == ChestType.LEFT) {
            net.minecraft.core.Direction direction = GoldenChestBlock.getConnectedDirection(state);
            x += (double) direction.getStepX() * 0.5;
            z += (double) direction.getStepZ() * 0.5;
        }

        level.playSound(null, x, y, z, sound, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public boolean triggerEvent(int id, int param) {
        if (id == 1) {
            this.chestLidController.shouldBeOpen(param > 0);
            return true;
        }
        return super.triggerEvent(id, param);
    }

    @Override
    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int oldCount, int newCount) {
        Block block = state.getBlock();
        level.blockEvent(pos, block, 1, newCount);
    }

    @Override
    public float getOpenNess(float partialTicks) {
        return this.chestLidController.getOpenness(partialTicks);
    }

    public static MenuProvider createDoubleContainer(GoldenChestBlockEntity left, GoldenChestBlockEntity right) {
        return new DoubleChestMenuProvider(left, right);
    }

    private static class DoubleChestMenuProvider implements MenuProvider {
        private final GoldenChestBlockEntity left;
        private final GoldenChestBlockEntity right;
        private final CompoundContainer container;

        public DoubleChestMenuProvider(GoldenChestBlockEntity left, GoldenChestBlockEntity right) {
            this.left = left;
            this.right = right;
            this.container = new CompoundContainer(left, right);
        }

        @Override
        public Component getDisplayName() {
            if (left.hasCustomName()) {
                return left.getDisplayName();
            } else if (right.hasCustomName()) {
                return right.getDisplayName();
            }
            return Component.translatable("block.terrariamod.golden_chest_double");
        }

        @Override
        public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
            if (left.canOpen(player) && right.canOpen(player)) {
                left.unpackLootTable(playerInventory.player);
                right.unpackLootTable(playerInventory.player);
                return ChestMenu.sixRows(id, playerInventory, container);
            }
            return null;
        }
    }
}
