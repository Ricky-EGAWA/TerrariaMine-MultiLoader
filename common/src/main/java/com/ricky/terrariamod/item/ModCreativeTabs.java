package com.ricky.terrariamod.item;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.ModBlocks;
import com.ricky.terrariamod.registry.RegistryObject;
import com.ricky.terrariamod.registry.RegistryProvider;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;

public class ModCreativeTabs {
    // RegistryProvider<CreativeModeTab> を使用してタブを登録
    public static final RegistryProvider<CreativeModeTab> CREATIVE_TABS =
            RegistryProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TOOL_TAB = CREATIVE_TABS.register("tool_tab",
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemgroup.tool_tab"))
                    .icon(() -> new ItemStack(ModItems.COBALT_SWORD.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.COBALT_SWORD.get());
                        output.accept(ModItems.COBALT_PICKAXE.get());
                        output.accept(ModItems.COBALT_AXE.get());
                        output.accept(ModItems.COBALT_SHOVEL.get());
                        output.accept(ModItems.ORICHALCUM_SWORD.get());
                        output.accept(ModItems.ORICHALCUM_PICKAXE.get());
                        output.accept(ModItems.ORICHALCUM_AXE.get());
                        output.accept(ModItems.ORICHALCUM_SHOVEL.get());
                        output.accept(ModItems.ADAMANTITE_SWORD.get());
                        output.accept(ModItems.ADAMANTITE_PICKAXE.get());
                        output.accept(ModItems.ADAMANTITE_AXE.get());
                        output.accept(ModItems.ADAMANTITE_SHOVEL.get());
                        output.accept(ModItems.HELLSTONE_SWORD.get());
                        output.accept(ModItems.HELLSTONE_PICKAXE.get());
                        output.accept(ModItems.HELLSTONE_AXE.get());
                        output.accept(ModItems.HELLSTONE_SHOVEL.get());
                        // 必要なアイテムをさらに追加
                    })
                    .build()
    );


    public static final RegistryObject<CreativeModeTab> TERRARIA_TAB = CREATIVE_TABS.register("item_tab",
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemgroup.item_tab"))
                    .icon(() -> new ItemStack(ModItems.COBALT_INGOT.get()))
                    .displayItems((parameters, output) -> {
                        // 既存のアイテムを追加
                        output.accept(ModItems.COBALT_INGOT.get());
                        output.accept(ModItems.ORICHALCUM_INGOT.get());
                        output.accept(ModItems.ADAMANTITE_INGOT.get());
                        output.accept(ModItems.HELLSTONE_INGOT.get());
                        output.accept(ModItems.COBALT_RAW.get());
                        output.accept(ModItems.ORICHALCUM_RAW.get());
                        output.accept(ModItems.ADAMANTITE_RAW.get());
                        output.accept(ModItems.HELLSTONE_RAW.get());
                        output.accept(ModItems.SPIDER_FANG.get());
                        // 他のアイテムも追加することができます
                        output.accept(ModItems.COBALT_SHOVEL.get());
                        output.accept(ModItems.COBALT_PICKAXE.get());
                    })
                    .build()
    );

    public static final RegistryObject<CreativeModeTab> BLOCK_TAB = CREATIVE_TABS.register("block_tab",
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemgroup.block_tab"))
                    .icon(() -> new ItemStack(ModBlocks.COBALT_BLOCK.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.COBALT_BLOCK.get());
                        // 他のアイテムを追加
                    })
                    .build()
    );

    public static void register() {
        // クラスのロードを強制して登録を確実に行う
    }
}
