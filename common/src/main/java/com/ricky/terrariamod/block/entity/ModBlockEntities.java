package com.ricky.terrariamod.block.entity;

import com.ricky.terrariamod.platform.Services;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static Supplier<BlockEntityType<GoldenChestBlockEntity>> GOLDEN_CHEST;

    public static void register() {
        GOLDEN_CHEST = Services.PLATFORM.registerGoldenChestBlockEntity();
    }
}
