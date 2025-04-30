package com.ricky.terrariamod.mixin;

import com.ricky.terrariamod.util.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class EntityDataSaverMixin implements IEntityDataSaver {
    private CompoundTag persistentData;

    @Override
    public CompoundTag getPersistentData() {
        if (persistentData == null) {
            persistentData = new CompoundTag();
        }
        return persistentData;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    private void injectSaveData(CompoundTag nbt, CallbackInfo ci) {
        if (persistentData != null) {
            nbt.put("mana_data", persistentData);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    private void injectLoadData(CompoundTag nbt, CallbackInfo ci) {
        if (nbt.contains("mana_data", 10)) {
            persistentData = nbt.getCompound("mana_data");
        }
    }
}

