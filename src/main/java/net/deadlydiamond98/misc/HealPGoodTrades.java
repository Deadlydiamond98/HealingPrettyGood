package net.deadlydiamond98.misc;

import net.deadlydiamond98.items.HealPGoodItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class HealPGoodTrades {
    public static void register() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 5, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(HealPGoodItems.HEART_BOTTLE, 1),
                    3,
                    2,
                    0.1f
            ));
        });

        TradeOfferHelper.registerWanderingTraderOffers(2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 9),
                    new ItemStack(HealPGoodItems.HEART_CRYSTAL_SHARD, 1),
                    3,
                    2,
                    0.1f
            ));
        });
    }
}
