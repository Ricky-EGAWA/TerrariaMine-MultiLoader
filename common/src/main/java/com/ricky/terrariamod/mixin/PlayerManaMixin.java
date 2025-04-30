package com.ricky.terrariamod.mixin;

import com.ricky.terrariamod.util.IEntityDataSaver;
import com.ricky.terrariamod.util.ManaUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerManaMixin {

    @Unique
    private int lastManaUpdateTick = 0;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo info) {
        Player player = (Player) (Object) this;

        MinecraftServer server = player.getServer();
        if (server == null) return;

        int currentTick = server.getTickCount();
        if (currentTick - lastManaUpdateTick >= 10) {
            ManaUtil.addMana((IEntityDataSaver)player,1);
            lastManaUpdateTick = currentTick;
        }
    }
}
