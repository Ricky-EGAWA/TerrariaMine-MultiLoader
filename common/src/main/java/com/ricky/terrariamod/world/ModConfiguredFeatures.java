package com.ricky.terrariamod.world;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.block.ModBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.tags.BlockTags;

import java.util.List;

public class ModConfiguredFeatures {

    // ResourceKey for our cobalt ore feature
    public static final ResourceKey<ConfiguredFeature<?, ?>> COBALT_ORE = createKey("cobalt_ore");

    // 他の鉱石も同様にキーを定義
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORICHALCUM_ORE = createKey("orichalcum_ore");

    // 登録処理で参照するためのキーを生成
    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Constants.locate(name));
    }

    // bootstrapメソッドで登録
//    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
//        HolderGetter<RuleTest> ruleTests = context.lookup(Registries.RULE_TEST);
//
//        // 地上の石の代替ブロックを定義（バニラのSTONE_REPLACEABLE_TAGを使う）
//        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
//
//        // Cobalt Oreの登録
//        context.register(COBALT_ORE,
//                new ConfiguredFeature<>(Feature.ORE,
//                        new OreConfiguration(
//                                List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.COBALT_ORE.get().defaultBlockState())),
//                                9 // vein size
//                        )));
//
//        // Orichalcum Oreの登録（例）
//        context.register(ORICHALCUM_ORE,
//                new ConfiguredFeature<>(Feature.ORE,
//                        new OreConfiguration(
//                                List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.ORICHALCUM_ORE.get().defaultBlockState())),
//                                7
//                        )));
//    }
}
