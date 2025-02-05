package com.ricky.terrariamod.item;

import com.ricky.terrariamod.Constants;
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

    public static final RegistryObject<CreativeModeTab> TERRARIA_TAB = CREATIVE_TABS.register("terraria_tab",
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemGroup.terrariamod"))
                    .icon(() -> new ItemStack(ModItems.COBALT_INGOT.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.COBALT_INGOT.get());
                        // 他のアイテムを追加
                    })
                    .build()
    );

    public static void register() {
        // クラスのロードを強制して登録を確実に行う
    }
}
