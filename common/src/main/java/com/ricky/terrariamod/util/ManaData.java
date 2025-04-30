package com.ricky.terrariamod.util;

import net.minecraft.nbt.CompoundTag;

public class ManaData {
    private int mana = 20;

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void copyFrom(ManaData source) {
        this.mana = source.mana;
    }

    public CompoundTag saveNbt() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("mana", mana);
        return nbt;
    }

    public void loadNbt(CompoundTag nbt) {
        this.mana = nbt.getInt("mana");
    }
}
