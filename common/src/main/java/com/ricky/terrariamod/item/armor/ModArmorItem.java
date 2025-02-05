package com.ricky.terrariamod.item.armor;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Map;

public class ModArmorItem extends ArmorItem {
    public static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.COBALT, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.ORICHALCUM, new MobEffectInstance(MobEffects.HEALTH_BOOST, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.ADAMANTITE, new MobEffectInstance(MobEffects.DAMAGE_BOOST, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.HELLSTONE, new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.GLASS, new MobEffectInstance(MobEffects.WATER_BREATHING, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.NIGHT, new MobEffectInstance(MobEffects.NIGHT_VISION, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.OBSIDIAN, new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.PUMPKIN, new MobEffectInstance(MobEffects.DAMAGE_BOOST, 219, 1,
                            false, false, true))
                    .build();

    public ModArmorItem(ArmorMaterial material, Type type, Item.Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!level.isClientSide) {
            if (entity instanceof Player player) {
                // Glass素材またはNight素材のヘルメットが装備されているか、またはフル装備の場合に効果を評価
                if (isWearingGlassHelmet(player) || isWearingNightHelmet(player) || hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }

        super.inventoryTick(stack, level, entity, slot, selected);
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, MobEffectInstance mapStatusEffect) {
        if (hasCorrectArmorOn(mapArmorMaterial, player)) {
            player.addEffect(new MobEffectInstance(mapStatusEffect));
        }
    }

    // Glassのヘルメットを装備しているか確認
    private boolean isWearingGlassHelmet(Player player) {
        ItemStack helmet = player.getInventory().armor.get(3);
        return !helmet.isEmpty() && ((ArmorItem) helmet.getItem()).getMaterial() == ModArmorMaterials.GLASS;
    }

    // Nightのヘルメットを装備しているか確認
    private boolean isWearingNightHelmet(Player player) {
        ItemStack helmet = player.getInventory().armor.get(3);
        return !helmet.isEmpty() && ((ArmorItem) helmet.getItem()).getMaterial() == ModArmorMaterials.NIGHT;
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().armor.get(0);
        ItemStack leggings = player.getInventory().armor.get(1);
        ItemStack breastplate = player.getInventory().armor.get(2);
        ItemStack helmet = player.getInventory().armor.get(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        // GlassおよびNight素材であれば各ヘルメットのみのチェックを行う
        if (material == ModArmorMaterials.GLASS) {
            return isWearingGlassHelmet(player);
        }
        if (material == ModArmorMaterials.NIGHT) {
            return isWearingNightHelmet(player);
        }

        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().armor.get(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().armor.get(1).getItem());
        ArmorItem breastplate = ((ArmorItem) player.getInventory().armor.get(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().armor.get(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }
}
