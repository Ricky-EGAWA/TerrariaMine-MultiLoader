package com.ricky.terrariamod.platform;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import com.ricky.terrariamod.platform.services.IRegistryFactory;
import com.ricky.terrariamod.registry.RegistryObject;
import com.ricky.terrariamod.registry.RegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import net.minecraftforge.registries.DeferredRegister;

public class ForgeRegistryProvider implements IRegistryFactory {

  @Override
  public <T> RegistryProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId) {
    final var containerOpt = ModList.get().getModContainerById(modId);
    if (containerOpt.isEmpty())
      throw new NullPointerException("Cannot find mod container for id " + modId);
    final var cont = containerOpt.get();
    if (cont instanceof FMLModContainer fmlModContainer) {
      final var register = DeferredRegister.create(resourceKey, modId);
      register.register(fmlModContainer.getEventBus());
      return new Provider<>(modId, register);
    } else {
      throw new ClassCastException("The container of the mod " + modId + " is not a FML one!");
    }
  }

  private static class Provider<T> implements RegistryProvider<T> {
    private final String modId;
    private final DeferredRegister<T> registry;

    private final Set<RegistryObject<T>> entries = new HashSet<>();
    private final Set<RegistryObject<T>> entriesView = Collections.unmodifiableSet(entries);

    private Provider(String modId, DeferredRegister<T> registry) {
      this.modId = modId;
      this.registry = registry;
    }

    @Override
    public String getModId() {
      return modId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <I extends T> RegistryObject<I> register(String name, Supplier<? extends I> supplier) {
      final var obj = registry.<I>register(name, supplier);
      final var ro = new RegistryObject<I>() {

        @Override
        public ResourceKey<I> getResourceKey() {
          return obj.getKey();
        }

        @Override
        public ResourceLocation getId() {
          return obj.getId();
        }

        @Override
        public I get() {
          return obj.get();
        }

        @Override
        public Holder<I> asHolder() {
          return obj.getHolder().orElseThrow();
        }
      };
      entries.add((RegistryObject<T>) ro);
      return ro;
    }

    @Override
    public Set<RegistryObject<T>> getEntries() {
      return entriesView;
    }
  }
}