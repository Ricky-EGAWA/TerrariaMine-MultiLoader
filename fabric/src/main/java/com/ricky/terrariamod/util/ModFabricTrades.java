package com.ricky.terrariamod.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;

public class ModFabricTrades {

    public static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 4,
                factories -> ModTradeData.getWeaponSmithLevel4Trades().forEach(entry ->
                        factories.add((entity, random) -> entry.toOffer()))
        );

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 5,
                factories -> ModTradeData.getWeaponSmithLevel5Trades().forEach(entry ->
                        factories.add((entity, random) -> entry.toOffer()))
        );
    }
}
