package com.ricky.terrariamod.util;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class KeyInputHandler {
    //TODO 銃のリロードに変える
    public static void onClientTick() {
        if (KeyBindings.RELOAD_KEY != null && KeyBindings.RELOAD_KEY.consumeClick()) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("reload"));
        }
    }
}
