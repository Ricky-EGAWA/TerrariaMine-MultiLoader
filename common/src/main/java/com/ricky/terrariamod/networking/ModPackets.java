package com.ricky.terrariamod.networking;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.networking.packet.ManaC2SPacket;
import com.ricky.terrariamod.networking.packet.ManaSyncDataS2CPacket;
import com.ricky.terrariamod.networking.packet.ReloadC2SPacket;
import com.ricky.terrariamod.platform.Services;
import com.ricky.terrariamod.platform.services.INetworkHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ModPackets {
    public static final ResourceLocation RELOAD_PACKET = new ResourceLocation(Constants.MOD_ID, "reload_packet");
    public static final ResourceLocation MANA_PACKET = new ResourceLocation(Constants.MOD_ID, "mana_packet");

    public static void registerC2SPackets() {
        Services.NETWORK.register(new INetworkHelper.MessageHandler<>(
            RELOAD_PACKET,
            ReloadC2SPacket.class,
            ReloadC2SPacket::encode,
            ReloadC2SPacket::decode,
            ReloadC2SPacket::handle,
            INetworkHelper.MessageBoundSide.SERVER
        ));

        Services.NETWORK.register(new INetworkHelper.MessageHandler<>(
            MANA_PACKET,
            ManaC2SPacket.class,
            ManaC2SPacket::encode,
            ManaC2SPacket::decode,
            ManaC2SPacket::handle,
            INetworkHelper.MessageBoundSide.SERVER
        ));
    }

    public static void registerS2CPackets() {
        Services.NETWORK.register(new INetworkHelper.MessageHandler<>(
            MANA_PACKET,
            ManaSyncDataS2CPacket.class,
            ManaSyncDataS2CPacket::encode,
            ManaSyncDataS2CPacket::decode,
            ManaSyncDataS2CPacket::handle,
            INetworkHelper.MessageBoundSide.CLIENT
        ));
    }

    public static void sendToServer(Object msg) {
        Services.NETWORK.sendToServer(msg);
    }

    public static void sendToPlayer(Player player, Object msg) {
        Services.NETWORK.sendTo(player, msg);
    }

    public static void sendToNearby(Level level, BlockPos pos, Object msg) {
        Services.NETWORK.sendToNearby(level, pos, msg);
    }
}