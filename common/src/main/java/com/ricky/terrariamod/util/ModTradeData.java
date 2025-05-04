package com.ricky.terrariamod.util;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import com.ricky.terrariamod.item.ModItems;

import java.util.ArrayList;
import java.util.List;

public class ModTradeData {

    public static List<TradeOfferEntry> getWeaponSmithLevel4Trades() {
        List<TradeOfferEntry> trades = new ArrayList<>();
        trades.add(new TradeOfferEntry(new ItemStack(Items.EMERALD, 10), new ItemStack(ModItems.ROCKET.get(), 5), 10, 10, 0.2f));
        trades.add(new TradeOfferEntry(new ItemStack(Items.EMERALD, 10), new ItemStack(ModItems.MUSKET_BALL.get(), 10), 10, 10, 0.2f));
        return trades;
    }

    public static List<TradeOfferEntry> getWeaponSmithLevel5Trades() {
        List<TradeOfferEntry> trades = new ArrayList<>();
//        trades.add(new TradeOfferEntry(new ItemStack(Items.EMERALD_BLOCK, 10), new ItemStack(ModItems.SHOTGUN.get()), 2, 10, 0.2f));
//        trades.add(new TradeOfferEntry(new ItemStack(Items.EMERALD_BLOCK, 10), new ItemStack(ModItems.SNIPER_RIFLE.get()), 2, 10, 0.2f));
        trades.add(new TradeOfferEntry(new ItemStack(Items.EMERALD_BLOCK, 10), new ItemStack(ModItems.ROCKET_LAUNCHER.get()), 2, 10, 0.2f));
        return trades;
    }

    public record TradeOfferEntry(ItemStack costA, ItemStack result, int maxUses, int xp, float priceMultiplier) {
        public MerchantOffer toOffer() {
            return new MerchantOffer(costA, result, maxUses, xp, priceMultiplier);
        }
    }
}
