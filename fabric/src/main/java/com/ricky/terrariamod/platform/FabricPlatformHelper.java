package com.ricky.terrariamod.platform;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.block.entity.GoldenChestBlockEntity;
import com.ricky.terrariamod.block.entity.LockedGoldenChestBlockEntity;
import com.ricky.terrariamod.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class FabricPlatformHelper implements IPlatformHelper {
    private static BlockEntityType<GoldenChestBlockEntity> GOLDEN_CHEST_TYPE;
    private static BlockEntityType<LockedGoldenChestBlockEntity> LOCKED_GOLDEN_CHEST_TYPE;

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public Supplier<BlockEntityType<GoldenChestBlockEntity>> registerGoldenChestBlockEntity() {
        GOLDEN_CHEST_TYPE = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                new ResourceLocation(Constants.MOD_ID, "golden_chest"),
                FabricBlockEntityTypeBuilder.create(GoldenChestBlockEntity::new, ModBlocks.GOLDEN_CHEST.get()).build()
        );
        return () -> GOLDEN_CHEST_TYPE;
    }

    @Override
    public Supplier<BlockEntityType<LockedGoldenChestBlockEntity>> registerLockedGoldenChestBlockEntity() {
        LOCKED_GOLDEN_CHEST_TYPE = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                new ResourceLocation(Constants.MOD_ID, "locked_golden_chest"),
                FabricBlockEntityTypeBuilder.create(LockedGoldenChestBlockEntity::new, ModBlocks.LOCKED_GOLDEN_CHEST.get()).build()
        );
        return () -> LOCKED_GOLDEN_CHEST_TYPE;
    }
}
