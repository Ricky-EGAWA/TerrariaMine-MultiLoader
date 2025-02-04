package com.ricky.terrariamod.item;

import com.ricky.terrariamod.Constants;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item.Properties;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModItems {
    public static final Map<ResourceLocation, Supplier<Item>> ITEMS = new LinkedHashMap<>();
    List<Supplier<Item>> mod_items = new LinkedList<>();

    public static final Supplier<Item> COBALT_INGOT = register("cobalt_ingot",
            () -> new Item(new Properties())
    );

    private static Supplier<Item> register(String name, Supplier<Item> itemSupplier) {
        ResourceLocation id = new ResourceLocation(Constants.MOD_ID, name);
        ITEMS.put(id, itemSupplier);
        return itemSupplier;
    }

    public static void register() {
        // 呼び出すだけでリストに追加される
    }
}
