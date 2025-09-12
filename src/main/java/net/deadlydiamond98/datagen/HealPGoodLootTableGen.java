package net.deadlydiamond98.datagen;

import net.deadlydiamond98.blocks.HealPGoodBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class HealPGoodLootTableGen extends FabricBlockLootTableProvider {
    public HealPGoodLootTableGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        HealPGoodBlocks.POLISHED_HEART_CRYSTAL.generateLootTables(this);
        HealPGoodBlocks.HEART_CRYSTAL_BRICKS.generateLootTables(this);
        addDrop(HealPGoodBlocks.HEART_LANTERN);
    }
}
