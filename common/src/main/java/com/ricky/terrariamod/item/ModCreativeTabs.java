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

    public static final RegistryObject<CreativeModeTab> ARMOR_TAB = CREATIVE_TABS.register("armor_tab",
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemgroup.armor_tab"))
                    .icon(() -> new ItemStack(ModItems.COBALT_CHESTPLATE.get()))
                    .displayItems((parameters, output) -> {
                        // OAK Armor
                        output.accept(ModItems.OAK_HELMET.get());
                        output.accept(ModItems.OAK_CHESTPLATE.get());
                        output.accept(ModItems.OAK_LEGGINGS.get());
                        output.accept(ModItems.OAK_BOOTS.get());

                        // COPPER Armor
                        output.accept(ModItems.COPPER_HELMET.get());
                        output.accept(ModItems.COPPER_CHESTPLATE.get());
                        output.accept(ModItems.COPPER_LEGGINGS.get());
                        output.accept(ModItems.COPPER_BOOTS.get());

                        // OBSIDIAN Armor
                        output.accept(ModItems.OBSIDIAN_HELMET.get());
                        output.accept(ModItems.OBSIDIAN_CHESTPLATE.get());
                        output.accept(ModItems.OBSIDIAN_LEGGINGS.get());
                        output.accept(ModItems.OBSIDIAN_BOOTS.get());

                        // CACTUS Armor
                        output.accept(ModItems.CACTUS_HELMET.get());
                        output.accept(ModItems.CACTUS_CHESTPLATE.get());
                        output.accept(ModItems.CACTUS_LEGGINGS.get());
                        output.accept(ModItems.CACTUS_BOOTS.get());

                        // PUMPKIN Armor
                        output.accept(ModItems.PUMPKIN_HELMET.get());
                        output.accept(ModItems.PUMPKIN_CHESTPLATE.get());
                        output.accept(ModItems.PUMPKIN_LEGGINGS.get());
                        output.accept(ModItems.PUMPKIN_BOOTS.get());

                        // GLASS & NIGHT Armor
                        output.accept(ModItems.GLASS_HELMET.get());
                        output.accept(ModItems.NIGHT_HELMET.get());

                        // COBALT Armor
                        output.accept(ModItems.COBALT_HELMET.get());
                        output.accept(ModItems.COBALT_CHESTPLATE.get());
                        output.accept(ModItems.COBALT_LEGGINGS.get());
                        output.accept(ModItems.COBALT_BOOTS.get());

                        // ORICHALCUM Armor
                        output.accept(ModItems.ORICHALCUM_HELMET.get());
                        output.accept(ModItems.ORICHALCUM_CHESTPLATE.get());
                        output.accept(ModItems.ORICHALCUM_LEGGINGS.get());
                        output.accept(ModItems.ORICHALCUM_BOOTS.get());

                        // ADAMANTITE Armor
                        output.accept(ModItems.ADAMANTITE_HELMET.get());
                        output.accept(ModItems.ADAMANTITE_CHESTPLATE.get());
                        output.accept(ModItems.ADAMANTITE_LEGGINGS.get());
                        output.accept(ModItems.ADAMANTITE_BOOTS.get());

                        // HELLSTONE Armor
                        output.accept(ModItems.HELLSTONE_HELMET.get());
                        output.accept(ModItems.HELLSTONE_CHESTPLATE.get());
                        output.accept(ModItems.HELLSTONE_LEGGINGS.get());
                        output.accept(ModItems.HELLSTONE_BOOTS.get());
                    })
                    .build()
    );


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
                        output.accept(ModItems.AMETHYST_STAFF.get());
                        output.accept(ModItems.DIAMOND_STAFF.get());
                        output.accept(ModItems.EMERALD_STAFF.get());
                        output.accept(ModItems.WATER_BOLT.get());
                        output.accept(ModItems.MUSKET_BALL.get());
                        output.accept(ModItems.HANDGUN.get());
                        output.accept(ModItems.ROCKET.get());
                        output.accept(ModItems.PHOENIX_BLASTER.get());
                        output.accept(ModItems.ROCKET_LAUNCHER.get());
                    })
                    .build()
    );


    public static final RegistryObject<CreativeModeTab> TERRARIA_TAB = CREATIVE_TABS.register("item_tab",
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemgroup.item_tab"))
                    .icon(() -> new ItemStack(ModItems.COBALT_INGOT.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.COBALT_INGOT.get());
                        output.accept(ModItems.ORICHALCUM_INGOT.get());
                        output.accept(ModItems.ADAMANTITE_INGOT.get());
                        output.accept(ModItems.HELLSTONE_INGOT.get());
                        output.accept(ModItems.COBALT_RAW.get());
                        output.accept(ModItems.ORICHALCUM_RAW.get());
                        output.accept(ModItems.ADAMANTITE_RAW.get());
                        output.accept(ModItems.HELLSTONE_RAW.get());
                        output.accept(ModItems.SPIDER_FANG.get());
                        output.accept(ModItems.METAL_DETECTOR.get());
                        output.accept(ModItems.INFINITE_LAVA_BUCKET.get());
                        output.accept(ModItems.INFINITE_WATER_BUCKET.get());
                        output.accept(ModItems.MAGIC_MIRROR.get());
                        output.accept(ModItems.SUSPICIOUS_LOOKING_EYE.get());
                        output.accept(ModItems.LENS.get());
                    })
                    .build()
    );

    public static final RegistryObject<CreativeModeTab> BLOCK_TAB = CREATIVE_TABS.register("block_tab",
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemgroup.block_tab"))
                    .icon(() -> new ItemStack(ModBlocks.COBALT_BLOCK.get()))
                    .displayItems((parameters, output) -> {
                        //region
                        output.accept(ModBlocks.COBALT_BLOCK.get());
                        output.accept(ModBlocks.COBALT_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_COBALT_ORE.get());

                        // ORICHALCUM
                        output.accept(ModBlocks.ORICHALCUM_BLOCK.get());
                        output.accept(ModBlocks.ORICHALCUM_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get());

                        // ADAMANTITE
                        output.accept(ModBlocks.ADAMANTITE_BLOCK.get());
                        output.accept(ModBlocks.ADAMANTITE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_ADAMANTITE_ORE.get());

                        // HELLSTONE
                        output.accept(ModBlocks.HELLSTONE_BLOCK.get());
                        output.accept(ModBlocks.HELLSTONE_ORE.get());
                        //endregion

                        //region
                        output.accept(ModBlocks.EBON_STONE.get());
                        output.accept(ModBlocks.EBON_SAND.get());
                        output.accept(ModBlocks.EBON_SANDSTONE.get());
                        output.accept(ModBlocks.EBON_ICE.get());

                        // CRIM
                        output.accept(ModBlocks.CRIM_STONE.get());
                        output.accept(ModBlocks.CRIM_SAND.get());
                        output.accept(ModBlocks.CRIM_SANDSTONE.get());
                        output.accept(ModBlocks.CRIM_ICE.get());

                        // PEARL
                        output.accept(ModBlocks.PEARL_STONE.get());
                        output.accept(ModBlocks.PEARL_SAND.get());
                        output.accept(ModBlocks.PEARL_SANDSTONE.get());
                        output.accept(ModBlocks.PEARL_ICE.get());
                        //endregion

                        // flower
                        output.accept(ModBlocks.DEATH_WEED.get());
                        output.accept(ModBlocks.SHIVER_THORN.get());
                        output.accept(ModBlocks.VILE_MUSHROOM.get());
                        output.accept(ModBlocks.VICIOUS_MUSHROOM.get());

                        // GLOWING
                        output.accept(ModBlocks.GLOWING_MOSS.get());
                        output.accept(ModBlocks.GLOWING_MUSHROOM_BLOCK.get());
                        output.accept(ModBlocks.GLOWING_MUSHROOM_STEM.get());
                        output.accept(ModBlocks.GLOWING_MUSHROOM.get());

                        //region wood
                        output.accept(ModBlocks.EBON_LOG.get());
                        output.accept(ModBlocks.EBON_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_EBON_LOG.get());
                        output.accept(ModBlocks.STRIPPED_EBON_WOOD.get());
                        output.accept(ModBlocks.EBON_PLANKS.get());
                        output.accept(ModBlocks.EBON_LEAVES.get());
                        output.accept(ModBlocks.EBON_SAPLING.get());

                        output.accept(ModBlocks.CRIM_LOG.get());
                        output.accept(ModBlocks.CRIM_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_CRIM_LOG.get());
                        output.accept(ModBlocks.STRIPPED_CRIM_WOOD.get());
                        output.accept(ModBlocks.CRIM_PLANKS.get());
                        output.accept(ModBlocks.CRIM_LEAVES.get());
                        output.accept(ModBlocks.CRIM_SAPLING.get());

                        output.accept(ModBlocks.PEARL_LOG.get());
                        output.accept(ModBlocks.PEARL_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_PEARL_LOG.get());
                        output.accept(ModBlocks.STRIPPED_PEARL_WOOD.get());
                        output.accept(ModBlocks.PEARL_PLANKS.get());
                        output.accept(ModBlocks.PEARL_LEAVES.get());
                        output.accept(ModBlocks.PEARL_SAPLING.get());
                        //endregion

                        //region stairs
                        output.accept(ModBlocks.EBON_STAIRS.get());
                        output.accept(ModBlocks.EBON_SLAB.get());
                        output.accept(ModBlocks.EBON_BUTTON.get());
                        output.accept(ModBlocks.EBON_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.EBON_FENCE.get());
                        output.accept(ModBlocks.EBON_FENCE_GATE.get());
                        output.accept(ModBlocks.CRIM_STAIRS.get());
                        output.accept(ModBlocks.CRIM_SLAB.get());
                        output.accept(ModBlocks.CRIM_BUTTON.get());
                        output.accept(ModBlocks.CRIM_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.CRIM_FENCE.get());
                        output.accept(ModBlocks.CRIM_FENCE_GATE.get());
                        output.accept(ModBlocks.PEARL_STAIRS.get());
                        output.accept(ModBlocks.PEARL_SLAB.get());
                        output.accept(ModBlocks.PEARL_BUTTON.get());
                        output.accept(ModBlocks.PEARL_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.PEARL_FENCE.get());
                        output.accept(ModBlocks.PEARL_FENCE_GATE.get());

                        //endregion

                        // ICICLE
//                        output.accept(ModBlocks.ICICLE.get());
                    })
                    .build()
    );


    public static void register() {
        // クラスのロードを強制して登録を確実に行う
    }
}
