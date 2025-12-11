package com.ricky.terrariamod.block.entity;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.registry.RegistryProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final RegistryProvider<BlockEntityType<?>> BLOCK_ENTITIES =
            RegistryProvider.get(Registries.BLOCK_ENTITY_TYPE, Constants.MOD_ID);

    @SuppressWarnings("DataFlowIssue")
    public static final Supplier<BlockEntityType<GoldenChestBlockEntity>> GOLDEN_CHEST =
            BLOCK_ENTITIES.register("golden_chest",
                    () -> BlockEntityType.Builder.of(
                            (pos, state) -> new GoldenChestBlockEntity(pos, state),
                            ModBlocks.GOLDEN_CHEST.get()
                    ).build(null));

    public static void register() {
        // Force class loading
    }
}
