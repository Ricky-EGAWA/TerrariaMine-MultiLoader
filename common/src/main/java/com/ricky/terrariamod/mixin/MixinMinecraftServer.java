package com.ricky.terrariamod.mixin;

import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.world.biome.ModSurfaceRules;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.progress.ChunkProgressListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import terrablender.api.SurfaceRuleManager;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {


    @Inject(method = "createLevels", at = @At("RETURN"))
    private void hackyAddSurfaceRules(ChunkProgressListener $$0, CallbackInfo ci) {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Constants.MOD_ID, ModSurfaceRules.makeCustomSurfaceRules());
    }
}
