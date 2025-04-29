package com.ricky.terrariamod.util;

import com.ricky.terrariamod.platform.services.IKeyBindHelper;
import net.minecraft.client.KeyMapping;

public class KeyBindings {
    public static KeyMapping RELOAD_KEY;

    public static final String KEY_CATEGORY = "key.category.terrariamod";
    public static final String KEY_RELOAD = "key.terrariamod.reload";

    public static void init(IKeyBindHelper helper) {
        RELOAD_KEY = helper.create(KEY_RELOAD, KEY_CATEGORY);
    }
}
