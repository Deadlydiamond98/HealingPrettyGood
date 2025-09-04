package net.deadlydiamond98;

import net.deadlydiamond98.koalalib.ToggleableContent;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealingPrettyGood implements ModInitializer {
	public static final String MODID = "healpgood";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {


		ToggleableContent.enableEnderSouls(true);

		LOGGER.info("Healing Pretty Good is fully initialized");
	}
}