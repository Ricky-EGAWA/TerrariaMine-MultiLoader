package com.ricky.terrariamod.networking.packet;

import com.ricky.terrariamod.util.IEntityDataSaver;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

public class ManaC2SPacket {
    private final int mana;

    public ManaC2SPacket(int mana) {
        this.mana = mana;
    }

    public ManaC2SPacket(FriendlyByteBuf buf) {
        this.mana = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(mana);
    }
    public static ManaC2SPacket decode(FriendlyByteBuf buf) {
        return new ManaC2SPacket(buf.readInt());
    }

    public void handle(Player player) {
        if (!player.level().isClientSide) {
            ((IEntityDataSaver)player).getPersistentData().putInt("mana", mana);
        }
    }

    public int getMana() {
        return mana;
    }
}
