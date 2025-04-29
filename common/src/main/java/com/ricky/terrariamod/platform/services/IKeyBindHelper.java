package com.ricky.terrariamod.platform.services;

import net.minecraft.client.KeyMapping;

public interface IKeyBindHelper {
    KeyMapping create(String translationKey, String category);
}
