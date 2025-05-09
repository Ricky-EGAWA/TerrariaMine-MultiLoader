package com.ricky.terrariamod.networking.packet;

import com.ricky.terrariamod.util.IEntityDataSaver;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

public class ManaSyncDataS2CPacket {
    private final int mana;

    public ManaSyncDataS2CPacket(int mana) {
        this.mana = mana;
    }

    public ManaSyncDataS2CPacket(FriendlyByteBuf buf) {
        this.mana = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(mana);
    }
    public static ManaSyncDataS2CPacket decode(FriendlyByteBuf buf) {
        return new ManaSyncDataS2CPacket(buf.readInt());
    }

    public void handle(Player player) {
        if (player.level().isClientSide) {
            ((IEntityDataSaver)player).getPersistentData().putInt("mana", mana);
        }
    }

    public int getMana() {
        return mana;
    }
}
