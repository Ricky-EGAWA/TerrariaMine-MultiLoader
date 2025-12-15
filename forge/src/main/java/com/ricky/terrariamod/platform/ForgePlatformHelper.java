package com.ricky.terrariamod.platform;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.block.entity.GoldenChestBlockEntity;
import com.ricky.terrariamod.block.entity.LockedGoldenChestBlockEntity;
import com.ricky.terrariamod.block.item.ForgeChestBlockItem;
import com.ricky.terrariamod.platform.services.IPlatformHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ForgePlatformHelper implements IPlatformHelper {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Constants.MOD_ID);

    private static RegistryObject<BlockEntityType<GoldenChestBlockEntity>> GOLDEN_CHEST_TYPE;
    private static RegistryObject<BlockEntityType<LockedGoldenChestBlockEntity>> LOCKED_GOLDEN_CHEST_TYPE;
    private static boolean registered = false;

    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    private void ensureRegistered() {
        if (!registered) {
            BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
            registered = true;
        }
    }

    @Override
    public Supplier<BlockEntityType<GoldenChestBlockEntity>> registerGoldenChestBlockEntity() {
        ensureRegistered();
        GOLDEN_CHEST_TYPE = BLOCK_ENTITIES.register("golden_chest",
                () -> BlockEntityType.Builder.of(GoldenChestBlockEntity::new, ModBlocks.GOLDEN_CHEST.get()).build(null));
        return GOLDEN_CHEST_TYPE;
    }

    @Override
    public Supplier<BlockEntityType<LockedGoldenChestBlockEntity>> registerLockedGoldenChestBlockEntity() {
        ensureRegistered();
        LOCKED_GOLDEN_CHEST_TYPE = BLOCK_ENTITIES.register("locked_golden_chest",
                () -> BlockEntityType.Builder.of(LockedGoldenChestBlockEntity::new, ModBlocks.LOCKED_GOLDEN_CHEST.get()).build(null));
        return LOCKED_GOLDEN_CHEST_TYPE;
    }

    @Override
    public BlockItem createChestBlockItem(Block block, Item.Properties properties) {
        return new ForgeChestBlockItem(block, properties);
    }
}