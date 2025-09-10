package net.deadlydiamond98.misc;

import net.deadlydiamond98.HealingPrettyGood;
import net.deadlydiamond98.blocks.HealPGoodBlocks;
import net.deadlydiamond98.items.HealPGoodItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class HealPGoodTab {

    public static final Identifier TAB_ID = new Identifier(HealingPrettyGood.MOD_ID, "healpgood_tab");

    public static final ItemGroup HEALPGOOD_TAB = Registry.register(Registries.ITEM_GROUP, TAB_ID, FabricItemGroup.builder()
            .displayName(Text.translatable("itemgroup.healpgood")).icon(HealPGoodItems.CRYSTAL_HEART::getDefaultStack).entries((displayContext, entry) -> {


                // Items

                entry.add(HealPGoodItems.HEART_CRYSTAL_SHARD);
                entry.add(HealPGoodItems.CRYSTAL_HEART);
                entry.add(HealPGoodItems.HEART_CONTAINER);
                entry.add(HealPGoodItems.EMPTY_HEART_CONTAINER);
                entry.add(HealPGoodItems.HEART_BOTTLE);
                entry.add(HealPGoodItems.MUSIC_DISC_HEARTSTEP);

                // Blocks

                entry.add(HealPGoodBlocks.HEART_CRYSTAL_BLOCK);
                HealPGoodBlocks.POLISHED_HEART_CRYSTAL.addToCreative(entry);
                HealPGoodBlocks.HEART_CRYSTAL_BRICKS.addToCreative(entry);

            }).build()
    );

    public static void register() {

    }
}
