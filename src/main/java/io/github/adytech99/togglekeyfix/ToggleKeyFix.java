package io.github.adytech99.togglekeyfix;

import net.fabricmc.api.ClientModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToggleKeyFix implements ClientModInitializer {
	public static final String MOD_ID = "toggle-key-fix";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    /**
     * Runs the mod initializer on the client environment.
     */
	@Override
	public void onInitializeClient() {
		LOGGER.info("Because Mojang can be dumb.");
	}
}