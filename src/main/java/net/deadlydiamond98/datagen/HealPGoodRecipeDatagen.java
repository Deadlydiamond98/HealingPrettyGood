package net.deadlydiamond98.datagen;

import net.deadlydiamond98.blocks.HealPGoodBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.function.Consumer;

public class HealPGoodRecipeDatagen extends FabricRecipeProvider {

    public HealPGoodRecipeDatagen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        HealPGoodBlocks.POLISHED_HEART_CRYSTAL.generateRecipesStone(exporter, HealPGoodBlocks.HEART_CRYSTAL_BLOCK);
        HealPGoodBlocks.HEART_CRYSTAL_BRICKS.generateRecipesStone(exporter, HealPGoodBlocks.HEART_CRYSTAL_BLOCK, HealPGoodBlocks.POLISHED_HEART_CRYSTAL.base);
    }
}
