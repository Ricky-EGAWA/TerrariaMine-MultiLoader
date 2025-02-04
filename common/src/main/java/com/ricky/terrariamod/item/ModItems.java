package com.ricky.terrariamod.item;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.registry.RegistryObject;
import com.ricky.terrariamod.registry.RegistryProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.core.registries.Registries;

public class ModItems {
    // RegistryProvider<Item> を使用してアイテムを登録
    public static final RegistryProvider<Item> ITEMS =
            RegistryProvider.get(Registries.ITEM, Constants.MOD_ID);

    // アイテムの登録
    public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot",
            () -> new Item(new Properties())
    );

    public static void register() {
        // ここを呼び出すことでクラスのロードを強制し、登録が確実に行われる
    }
}
