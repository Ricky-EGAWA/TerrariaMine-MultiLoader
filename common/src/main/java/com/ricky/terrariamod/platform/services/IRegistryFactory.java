package com.ricky.terrariamod.platform.services;

import com.ricky.terrariamod.registry.RegistryProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface IRegistryFactory {

  <T> RegistryProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId);

  default <T> RegistryProvider<T> create(Registry<T> registry, String modId) {
    return create(registry.key(), modId);
  }
}