package com.ricky.terrariamod.platform.services;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class FabricKeyBindHelper implements IKeyBindHelper {
    @Override
    public KeyMapping create(String translationKey, String category) {
        KeyMapping key = new KeyMapping(translationKey, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, category);
        return KeyBindingHelper.registerKeyBinding(key);
    }
}
