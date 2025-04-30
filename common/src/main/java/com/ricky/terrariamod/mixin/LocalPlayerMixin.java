package com.ricky.terrariamod.mixin;

import com.ricky.terrariamod.util.IEntityDataSaver;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin implements IEntityDataSaver {
    private final CompoundTag persistentData = new CompoundTag();

    @Override
    public CompoundTag getPersistentData() {
        return persistentData;
    }
}
