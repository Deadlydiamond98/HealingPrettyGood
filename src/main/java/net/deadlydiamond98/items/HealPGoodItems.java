package net.deadlydiamond98.items;

import net.deadlydiamond98.HealingPrettyGood;
import net.deadlydiamond98.blocks.HealPGoodBlocks;
import net.deadlydiamond98.items.heartapplier.CrystalHeartItem;
import net.deadlydiamond98.items.heartapplier.EmptyHeartContainerItem;
import net.deadlydiamond98.items.heartapplier.HeartContainerItem;
import net.deadlydiamond98.koalalib.common.items.ModSharedItems;
import net.deadlydiamond98.misc.HealPGoodSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class HealPGoodItems {
    public static final FoodComponent CRYSTAL_APPLE_FOOD = new FoodComponent.Builder().hunger(4).saturationModifier(1.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 2400, 0), 1)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 2)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400, 0), 1)
            .alwaysEdible().build();

    public static final Item HEART_CRYSTAL_SLIVER = register("heart_crystal_sliver", new Item(new FabricItemSettings()));
    public static final Item HEART_CRYSTAL_SHARD = register("heart_crystal_shard", new Item(new FabricItemSettings()));

    public static final Item HEART_BOTTLE = register("heart_bottle", new HeartBottleItem(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item HEART_COOKIE = register("heart_cookie", new HealingFood(new FabricItemSettings().food(FoodComponents.COOKIE), 1));
    public static final Item CRYSTAL_APPLE = register("crystal_apple", new HealingFood(new FabricItemSettings().food(CRYSTAL_APPLE_FOOD).rarity(Rarity.RARE), 8));

    public static final Item CRYSTAL_HEART = register("crystal_heart", new CrystalHeartItem(new FabricItemSettings().maxCount(16).rarity(Rarity.RARE)));
    public static final Item HEART_CONTAINER = register("heart_container", new HeartContainerItem(new FabricItemSettings().maxCount(16).rarity(Rarity.RARE)));
    public static final Item EMPTY_HEART_CONTAINER = register("empty_heart_container", new EmptyHeartContainerItem(new FabricItemSettings().maxCount(16).rarity(Rarity.UNCOMMON)));

    public static final Item MUSIC_DISC_HEARTSTEP = register("music_disc_heartstep", new MusicDiscItem(
            10, HealPGoodSounds.MUSIC_DISC_HEARTSTEP, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 120
    ));

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(HealingPrettyGood.MOD_ID, id), item);
    }

    public static void register() {}
}
