package net.deadlydiamond98.datagen;

import net.deadlydiamond98.blocks.HealPGoodBlocks;
import net.deadlydiamond98.items.HealPGoodItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class HealPGoodBlockTagGen extends FabricTagProvider.BlockTagProvider {

    public HealPGoodBlockTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(
                HealPGoodBlocks.HEART_CRYSTAL_BLOCK
        ).add(HealPGoodBlocks.POLISHED_HEART_CRYSTAL.getAll()
        ).add(HealPGoodBlocks.HEART_CRYSTAL_BRICKS.getAll());

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL).add(
                HealPGoodBlocks.HEART_LANTERN
        );

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                HealPGoodBlocks.HEART_CRYSTAL_BLOCK,
                HealPGoodBlocks.HEART_LANTERN
        ).add(HealPGoodBlocks.POLISHED_HEART_CRYSTAL.getAll()
        ).add(HealPGoodBlocks.HEART_CRYSTAL_BRICKS.getAll());

    }
}
