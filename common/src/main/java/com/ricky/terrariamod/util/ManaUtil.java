package com.ricky.terrariamod.util;

import com.ricky.terrariamod.networking.ModPackets;
import com.ricky.terrariamod.networking.packet.ManaSyncDataS2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class ManaUtil {
    public static void addMana(IEntityDataSaver player, int amount){
        CompoundTag nbt = player.getPersistentData();
        int mana = nbt.getInt("mana");
        if(mana + amount >= 200) {
            mana = 200;
        } else {
            mana += amount;
        }

        nbt.putInt("mana", mana);
        syncMana(mana, (ServerPlayer) player);
    }

    public static void removeMana(IEntityDataSaver player, int amount) {
        CompoundTag nbt = player.getPersistentData();
        int mana = nbt.getInt("mana");
        if(mana - amount < 0) {
            mana = 0;
        } else {
            mana -= amount;
        }

        nbt.putInt("mana", mana);
        syncMana(mana, (ServerPlayer) player);
    }
    public static boolean useMana(Player playerEntity, int currentMana) {
        if (playerEntity.isCreative()) {
            return true;
        }
        IEntityDataSaver player = (IEntityDataSaver) playerEntity;
        int confuseMana = player.getPersistentData().getInt("mana");
        //マナが足りない場合
        if (confuseMana < currentMana) {
            return false;
        }
        removeMana(player,currentMana);
        return true;
    }

    public static void syncMana(int mana, ServerPlayer player) {
        ModPackets.sendToPlayer(player, new ManaSyncDataS2CPacket(mana));
    }
}
