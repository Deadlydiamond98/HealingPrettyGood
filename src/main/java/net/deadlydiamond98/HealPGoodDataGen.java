package net.deadlydiamond98;

import net.deadlydiamond98.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class HealPGoodDataGen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(HealPGoodModelGen::new);

		pack.addProvider(HealPGoodItemTagGen::new);
		pack.addProvider(HealPGoodBlockTagGen::new);
		pack.addProvider(HealPGoodLootTableGen::new);
		pack.addProvider(HealPGoodRecipeDatagen::new);
	}
}
