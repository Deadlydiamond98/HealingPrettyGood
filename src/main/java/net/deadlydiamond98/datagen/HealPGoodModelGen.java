package net.deadlydiamond98.datagen;

import net.deadlydiamond98.blocks.HealPGoodBlocks;
import net.deadlydiamond98.items.HealPGoodItems;
import net.deadlydiamond98.koalalib.util.datagen.ItemModelDatagenUtil;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class HealPGoodModelGen extends FabricModelProvider {

    public HealPGoodModelGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(HealPGoodBlocks.HEART_CRYSTAL_BLOCK);
        HealPGoodBlocks.POLISHED_HEART_CRYSTAL.generateModels(blockStateModelGenerator, true);
        HealPGoodBlocks.HEART_CRYSTAL_BRICKS.generateModels(blockStateModelGenerator);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        ItemModelDatagenUtil.registerGenerated(itemModelGenerator,
                HealPGoodItems.MUSIC_DISC_HEARTSTEP,
                HealPGoodItems.HEART_CRYSTAL_SHARD,
                HealPGoodItems.HEART_PIECE,
                HealPGoodItems.HEART_CRYSTAL_SLIVER,
                HealPGoodItems.CRYSTAL_HEART,
                HealPGoodItems.HEART_CONTAINER,
                HealPGoodItems.EMPTY_HEART_CONTAINER,
                HealPGoodItems.HEART_BOTTLE,
                HealPGoodItems.HEART_COOKIE,
                HealPGoodItems.CRYSTAL_APPLE,
                HealPGoodBlocks.HEART_LANTERN.asItem()
        );
    }
}
