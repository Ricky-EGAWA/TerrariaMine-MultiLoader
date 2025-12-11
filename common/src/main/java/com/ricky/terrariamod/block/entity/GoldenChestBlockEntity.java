package com.ricky.terrariamod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GoldenChestBlockEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(Level level, BlockPos pos, BlockState state) {
            GoldenChestBlockEntity.this.playSound(SoundEvents.CHEST_OPEN);
        }

        @Override
        protected void onClose(Level level, BlockPos pos, BlockState state) {
            GoldenChestBlockEntity.this.playSound(SoundEvents.CHEST_CLOSE);
        }

        @Override
        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int oldCount, int newCount) {
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (player.containerMenu instanceof ChestMenu chestMenu) {
                Container container = chestMenu.getContainer();
                return container == GoldenChestBlockEntity.this;
            }
            return false;
        }
    };

    // Animation variables
    public float openness;
    public float oOpenness;

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

    public static void tick(Level level, BlockPos pos, BlockState state, GoldenChestBlockEntity blockEntity) {
        blockEntity.oOpenness = blockEntity.openness;
        int openers = blockEntity.openersCounter.getOpenerCount();

        if (openers > 0 && blockEntity.openness == 0.0F) {
            // Starting to open
        }

        if (openers == 0 && blockEntity.openness > 0.0F) {
            blockEntity.openness -= 0.1F;
            if (blockEntity.openness < 0.0F) {
                blockEntity.openness = 0.0F;
            }
        } else if (openers > 0 && blockEntity.openness < 1.0F) {
            blockEntity.openness += 0.1F;
            if (blockEntity.openness > 1.0F) {
                blockEntity.openness = 1.0F;
            }
        }
    }

    private void playSound(SoundEvent sound) {
        double x = (double) this.worldPosition.getX() + 0.5;
        double y = (double) this.worldPosition.getY() + 0.5;
        double z = (double) this.worldPosition.getZ() + 0.5;
        this.level.playSound(null, x, y, z, sound, SoundSource.BLOCKS, 0.5F,
                this.level.random.nextFloat() * 0.1F + 0.9F);
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

    public float getOpenNess(float partialTick) {
        return this.oOpenness + (this.openness - this.oOpenness) * partialTick;
    }
}
