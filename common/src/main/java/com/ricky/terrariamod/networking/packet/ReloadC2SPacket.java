package com.ricky.terrariamod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

public class ReloadC2SPacket {
    private final boolean attackFlag;

    public ReloadC2SPacket(boolean attackFlag) {
        this.attackFlag = attackFlag;
    }

    public static void encode(ReloadC2SPacket msg, FriendlyByteBuf buf) {
        buf.writeBoolean(msg.attackFlag);
    }

    public static ReloadC2SPacket decode(FriendlyByteBuf buf) {
        return new ReloadC2SPacket(buf.readBoolean());
    }

    public static void handle(ReloadC2SPacket msg, Player player) {
        if (msg.attackFlag) {
//            if (player.getMainHandItem().getItem() instanceof ShotgunItem shotgunItem) {
//                shotgunItem.reload(player, Hand.MAIN_HAND);
//            }
//            if (player.getMainHandItem().getItem() instanceof SniperRifleItem sniperRifleItem) {
//                sniperRifleItem.reload(player, Hand.MAIN_HAND);
//            }
        }
    }
}
