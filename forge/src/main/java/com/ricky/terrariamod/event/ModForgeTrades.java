package com.ricky.terrariamod.event;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.util.ModTradeData;
import com.ricky.terrariamod.util.ModTradeData.TradeOfferEntry;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class ModForgeTrades {

    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.WEAPONSMITH) {
            List<VillagerTrades.ItemListing> level4 = event.getTrades().get(4);
            for (TradeOfferEntry entry : ModTradeData.getWeaponSmithLevel4Trades()) {
                level4.add((entity, random) -> entry.toOffer());
            }

            List<VillagerTrades.ItemListing> level5 = event.getTrades().get(5);
            for (TradeOfferEntry entry : ModTradeData.getWeaponSmithLevel5Trades()) {
                level5.add((entity, random) -> entry.toOffer());
            }
        }
    }
}
