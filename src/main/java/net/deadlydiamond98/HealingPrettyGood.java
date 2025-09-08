package net.deadlydiamond98;

import net.deadlydiamond98.blocks.HealPGoodBlocks;
import net.deadlydiamond98.config.HealPGoodConfig;
import net.deadlydiamond98.entities.HealPGoodEntities;
import net.deadlydiamond98.items.HealPGoodItems;
import net.deadlydiamond98.koalalib.ToggleableContent;
import net.deadlydiamond98.koalalib.config.KoalaConfigCreator;
import net.deadlydiamond98.misc.HealPGoodTab;
import net.deadlydiamond98.misc.HealPGoodSounds;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealingPrettyGood implements ModInitializer {
	public static final String MOD_ID = "healpgood";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		KoalaConfigCreator.addModConfig(MOD_ID, HealPGoodConfig.Main.class);
		ToggleableContent.enableEnderSouls();

		HealPGoodItems.register();
		HealPGoodBlocks.register();
		HealPGoodSounds.register();
		HealPGoodTab.register();
		HealPGoodEntities.register();

		LOGGER.info("Healing Pretty Good is fully initialized");

	}
}