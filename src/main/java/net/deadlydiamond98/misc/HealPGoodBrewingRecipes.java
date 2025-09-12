package net.deadlydiamond98.misc;

import net.deadlydiamond98.items.HealPGoodItems;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;

public class HealPGoodBrewingRecipes extends BrewingRecipeRegistry {
    public static void register() {
        registerPotionRecipe(Potions.AWKWARD, HealPGoodItems.HEART_CRYSTAL_SHARD, Potions.HEALING);
    }
}
