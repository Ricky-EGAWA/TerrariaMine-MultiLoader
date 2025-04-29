package com.ricky.terrariamod.platform.services;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ForgeKeyBindHelper implements IKeyBindHelper {

    @Override
    public KeyMapping create(String translationKey, String category) {
        return new KeyMapping(translationKey, KeyConflictContext.IN_GAME,
                InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, category); // 登録はイベントで行う
    }
}
