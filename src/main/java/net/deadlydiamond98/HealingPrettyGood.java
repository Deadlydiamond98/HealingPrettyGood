package net.deadlydiamond98;

import net.deadlydiamond98.blocks.HealPGoodBlocks;
import net.deadlydiamond98.blocks.entities.HealPGoodBlockEntities;
import net.deadlydiamond98.events.HealPGoodAfterDeath;
import net.deadlydiamond98.events.HealPGoodAfterRespawn;
import net.deadlydiamond98.koalalib.updater.KoalaUpdateChecker;
import net.deadlydiamond98.misc.*;
import net.deadlydiamond98.entities.HealPGoodEntities;
import net.deadlydiamond98.items.HealPGoodItems;
import net.deadlydiamond98.koalalib.ToggleableContent;
import net.deadlydiamond98.koalalib.config.KoalaConfigCreator;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealingPrettyGood implements ModInitializer {
	public static final String MOD_ID = "healpgood";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		KoalaConfigCreator.addModConfig(MOD_ID, HealPGoodConfig.Main.class);
		KoalaConfigCreator.addModConfigCategory(MOD_ID, "hearts", HealPGoodConfig.Hearts.class);
		KoalaConfigCreator.addModConfigCategory(MOD_ID, "health_upgrades", HealPGoodConfig.HealthUpgrades.class);
		KoalaUpdateChecker.addModUpdateChecker(MOD_ID);
		ToggleableContent.enableEnderSouls();

		HealPGoodItems.register();
		HealPGoodBlocks.register();
		HealPGoodBlockEntities.register();
		HealPGoodSounds.register();
		HealPGoodTab.register();
		HealPGoodEntities.register();
		HealPGoodAdvancements.register();
		HealPGoodTrades.register();
		HealPGoodBrewingRecipes.register();

		HealPGoodAfterDeath.register();
		HealPGoodAfterRespawn.register();
		HealPGoodCommands.register();

		LOGGER.info("Healing Pretty Good is fully initialized");
	}
}