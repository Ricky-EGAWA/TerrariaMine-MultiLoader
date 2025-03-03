package com.ricky.terrariamod;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

	public static final String MOD_ID = "terrariamod";
	public static final String MOD_NAME = "TerrariaMine";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

	public static ResourceLocation locate(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}