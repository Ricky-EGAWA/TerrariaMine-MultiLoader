package com.ricky.terrariamod.world.biome;


import com.ricky.terrariamod.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource SNOW = makeStateRule(Blocks.SNOW);
    private static final SurfaceRules.RuleSource GRAVEL =makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource MYCELIUM = makeStateRule(Blocks.MYCELIUM);
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource EBON_SAND = makeStateRule(ModBlocks.EBON_SAND.get());
    private static final SurfaceRules.RuleSource EBON_SANDSTONE = makeStateRule(ModBlocks.EBON_SANDSTONE.get());
    private static final SurfaceRules.RuleSource EBON_STONE = makeStateRule(ModBlocks.EBON_STONE.get());
    private static final SurfaceRules.RuleSource EBON_ICE = makeStateRule(ModBlocks.EBON_ICE.get());
    private static final SurfaceRules.RuleSource CRIM_SAND = makeStateRule(ModBlocks.CRIM_SAND.get());
    private static final SurfaceRules.RuleSource CRIM_SANDSTONE = makeStateRule(ModBlocks.CRIM_SANDSTONE.get());
    private static final SurfaceRules.RuleSource CRIM_STONE = makeStateRule(ModBlocks.CRIM_STONE.get());
    private static final SurfaceRules.RuleSource CRIM_ICE = makeStateRule(ModBlocks.CRIM_ICE.get());
    private static final SurfaceRules.RuleSource PEARL_SAND = makeStateRule(ModBlocks.PEARL_SAND.get());
    private static final SurfaceRules.RuleSource PEARL_SANDSTONE = makeStateRule(ModBlocks.PEARL_SANDSTONE.get());
    private static final SurfaceRules.RuleSource PEARL_STONE = makeStateRule(ModBlocks.PEARL_STONE.get());
    private static final SurfaceRules.RuleSource PEARL_ICE = makeStateRule(ModBlocks.PEARL_ICE.get());
    private static final SurfaceRules.RuleSource GLOWING_MOSS = makeStateRule(ModBlocks.GLOWING_MOSS.get());

    public static SurfaceRules.RuleSource makeCustomSurfaceRules() {
        System.out.println("crim desert surface");
        System.out.println(ModBiomes.CRIM_BIOME);
        System.out.println(ModBiomes.CRIM_DESERT_BIOME);
        System.out.println(ModBiomes.CRIM_ICE_BIOME);
        System.out.println(ModBiomes.EBON_BIOME);
        System.out.println(ModBiomes.EBON_DESERT_BIOME);
        System.out.println(ModBiomes.EBON_ICE_BIOME);
        System.out.println(ModBiomes.PEARL_BIOME);
        System.out.println(ModBiomes.PEARL_DESERT_BIOME);
        System.out.println(ModBiomes.PEARL_ICE_BIOME);
        System.out.println(ModBiomes.GLOWING_MUSHROOM_BIOME);

        SurfaceRules.ConditionSource crimDesertBiomeCondition = SurfaceRules.isBiome(ModBiomes.CRIM_DESERT_BIOME);
        SurfaceRules.ConditionSource ebonDesertBiomeCondition = SurfaceRules.isBiome(ModBiomes.EBON_DESERT_BIOME);
        SurfaceRules.ConditionSource pearlDesertBiomeCondition = SurfaceRules.isBiome(ModBiomes.PEARL_DESERT_BIOME);
        SurfaceRules.ConditionSource crimBiomeCondition = SurfaceRules.isBiome(ModBiomes.CRIM_BIOME);
        SurfaceRules.ConditionSource ebonBiomeCondition = SurfaceRules.isBiome(ModBiomes.EBON_BIOME);
        SurfaceRules.ConditionSource pearlBiomeCondition = SurfaceRules.isBiome(ModBiomes.PEARL_BIOME);
        SurfaceRules.ConditionSource crimIceBiomeCondition = SurfaceRules.isBiome(ModBiomes.CRIM_ICE_BIOME);
        SurfaceRules.ConditionSource ebonIceBiomeCondition = SurfaceRules.isBiome(ModBiomes.EBON_ICE_BIOME);
        SurfaceRules.ConditionSource pearlIceBiomeCondition = SurfaceRules.isBiome(ModBiomes.PEARL_ICE_BIOME);
        //underground
        SurfaceRules.ConditionSource glowingMushroomBiomeCondition = SurfaceRules.isBiome(ModBiomes.GLOWING_MUSHROOM_BIOME);

        SurfaceRules.RuleSource surfaceRule_crim_desert = surfaceRuleMaker(CRIM_SAND, CRIM_SAND, CRIM_SANDSTONE, ModBlocks.CRIM_STONE.get().defaultBlockState(), CRIM_SAND, CRIM_STONE, CRIM_STONE);
        SurfaceRules.RuleSource surfaceRule_ebon_desert = surfaceRuleMaker(EBON_SAND, EBON_SAND, EBON_SANDSTONE, ModBlocks.EBON_STONE.get().defaultBlockState(), EBON_SAND, EBON_STONE, EBON_STONE);
        SurfaceRules.RuleSource surfaceRule_pearl_desert = surfaceRuleMaker(PEARL_SAND, PEARL_SAND, PEARL_SANDSTONE, ModBlocks.PEARL_STONE.get().defaultBlockState(), PEARL_SAND, PEARL_STONE, PEARL_STONE);
        SurfaceRules.RuleSource surfaceRule_crim = surfaceRuleMaker(GRASS_BLOCK, DIRT, CRIM_STONE, ModBlocks.CRIM_STONE.get().defaultBlockState(), GRAVEL, CRIM_STONE, CRIM_STONE);
        SurfaceRules.RuleSource surfaceRule_ebon = surfaceRuleMaker(GRASS_BLOCK, DIRT, EBON_STONE, ModBlocks.EBON_STONE.get().defaultBlockState(), GRAVEL, EBON_STONE, EBON_STONE);
        SurfaceRules.RuleSource surfaceRule_pearl = surfaceRuleMaker(GRASS_BLOCK, DIRT, PEARL_STONE, ModBlocks.PEARL_STONE.get().defaultBlockState(), GRAVEL, PEARL_STONE, PEARL_STONE);
        SurfaceRules.RuleSource surfaceRule_crim_ice = surfaceRuleMaker(SNOW, CRIM_ICE, CRIM_ICE, ModBlocks.CRIM_ICE.get().defaultBlockState(), CRIM_ICE, CRIM_STONE, CRIM_STONE);
        SurfaceRules.RuleSource surfaceRule_ebon_ice = surfaceRuleMaker(SNOW, EBON_ICE, EBON_ICE, ModBlocks.EBON_ICE.get().defaultBlockState(), EBON_ICE, EBON_STONE, EBON_STONE);
        SurfaceRules.RuleSource surfaceRule_pearl_ice = surfaceRuleMaker(SNOW, PEARL_ICE, PEARL_ICE, ModBlocks.PEARL_ICE.get().defaultBlockState(), PEARL_STONE, EBON_STONE, PEARL_STONE);

        SurfaceRules.RuleSource surfaceRule_glowing_mushroom = undergroundRule(GLOWING_MOSS,MYCELIUM);

        SurfaceRules.RuleSource crimDesertBiomeRule = SurfaceRules.ifTrue(crimDesertBiomeCondition, surfaceRule_crim_desert);
        SurfaceRules.RuleSource ebonDesertBiomeRule = SurfaceRules.ifTrue(ebonDesertBiomeCondition, surfaceRule_ebon_desert);
        SurfaceRules.RuleSource pearlDesertBiomeRule = SurfaceRules.ifTrue(pearlDesertBiomeCondition, surfaceRule_pearl_desert);
        SurfaceRules.RuleSource crimBiomeRule = SurfaceRules.ifTrue(crimBiomeCondition, surfaceRule_crim);
        SurfaceRules.RuleSource ebonBiomeRule = SurfaceRules.ifTrue(ebonBiomeCondition, surfaceRule_ebon);
        SurfaceRules.RuleSource pearlBiomeRule = SurfaceRules.ifTrue(pearlBiomeCondition, surfaceRule_pearl);
        SurfaceRules.RuleSource crimIceBiomeRule = SurfaceRules.ifTrue(crimIceBiomeCondition, surfaceRule_crim_ice);
        SurfaceRules.RuleSource ebonIceBiomeRule = SurfaceRules.ifTrue(ebonIceBiomeCondition, surfaceRule_ebon_ice);
        SurfaceRules.RuleSource pearlIceBiomeRule = SurfaceRules.ifTrue(pearlIceBiomeCondition, surfaceRule_pearl_ice);
        SurfaceRules.RuleSource glowingMushroomRule = SurfaceRules.ifTrue(glowingMushroomBiomeCondition, surfaceRule_glowing_mushroom);

        return SurfaceRules.sequence(
                crimDesertBiomeRule,
                ebonDesertBiomeRule,
                pearlDesertBiomeRule,
                crimBiomeRule,
                ebonBiomeRule,
                pearlBiomeRule,
                crimIceBiomeRule,
                ebonIceBiomeRule,
                pearlIceBiomeRule,
                glowingMushroomRule
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
    private static SurfaceRules.RuleSource surfaceRuleMaker(SurfaceRules.RuleSource surface, SurfaceRules.RuleSource first, SurfaceRules.RuleSource second, BlockState base, SurfaceRules.RuleSource underwater, SurfaceRules.RuleSource ceiling, SurfaceRules.RuleSource floor){
        SurfaceRules.ConditionSource isSurface = SurfaceRules.abovePreliminarySurface();
        SurfaceRules.ConditionSource subSurfaceLayer_floor = SurfaceRules.stoneDepthCheck(3, true, CaveSurface.FLOOR);
        SurfaceRules.ConditionSource subSurfaceLayer_ceiling = SurfaceRules.stoneDepthCheck(3, true, CaveSurface.CEILING);
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(isSurface, surfaceRule(first,second,surface,underwater)),
                SurfaceRules.state(base),
                SurfaceRules.ifTrue(subSurfaceLayer_floor, floor),
                SurfaceRules.ifTrue(subSurfaceLayer_ceiling, ceiling)
        );
    }
    private static SurfaceRules.RuleSource surfaceRule(SurfaceRules.RuleSource first, SurfaceRules.RuleSource second, SurfaceRules.RuleSource surface,SurfaceRules.RuleSource underwater){
        SurfaceRules.ConditionSource topLayer = SurfaceRules.stoneDepthCheck(2, true, CaveSurface.FLOOR);
        SurfaceRules.ConditionSource subSurfaceLayer = SurfaceRules.stoneDepthCheck(3, true, CaveSurface.FLOOR);
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource waterSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, surface), underwater);
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR), waterSurface),
                SurfaceRules.ifTrue(topLayer,first),
                SurfaceRules.ifTrue(subSurfaceLayer,second)
        );
    }
    public static SurfaceRules.RuleSource undergroundRule(SurfaceRules.RuleSource ceiling, SurfaceRules.RuleSource floor){
        SurfaceRules.ConditionSource ceilingLayer = SurfaceRules.stoneDepthCheck(2,true,CaveSurface.CEILING);
        SurfaceRules.ConditionSource floorLayer = SurfaceRules.stoneDepthCheck(1,true,CaveSurface.FLOOR);
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(ceilingLayer, ceiling),
                SurfaceRules.ifTrue(floorLayer,floor)
        );

    }
}
