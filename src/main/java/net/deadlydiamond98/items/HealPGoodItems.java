package net.deadlydiamond98.items;

import net.deadlydiamond98.HealingPrettyGood;
import net.deadlydiamond98.misc.HealPGoodSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class HealPGoodItems {

    public static final Item HEART_CRYSTAL_SHARD = register("heart_crystal_shard", new Item(new FabricItemSettings()));
    public static final Item CRYSTAL_HEART = register("crystal_heart", new Item(new FabricItemSettings()));
    public static final Item HEART_CONTAINER = register("heart_container", new Item(new FabricItemSettings()));
    public static final Item EMPTY_HEART_CONTAINER = register("empty_heart_container", new Item(new FabricItemSettings()));

    public static final Item MUSIC_DISC_HEARTSTEP = register("music_disc_heartstep", new MusicDiscItem(
            10, HealPGoodSounds.MUSIC_DISC_HEARTSTEP, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 120
    ));

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(HealingPrettyGood.MOD_ID, id), item);
    }

    public static void register() {}
}
