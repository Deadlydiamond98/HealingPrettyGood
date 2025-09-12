package net.deadlydiamond98.events;

import net.deadlydiamond98.misc.HealPGoodConfig;
import net.deadlydiamond98.util.ExtraHealthHelper;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

public class HealPGoodAfterRespawn {
    public static void register() {
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            int permHealth = ExtraHealthHelper.getPermHealth(oldPlayer);
            ExtraHealthHelper.setPermHealth(newPlayer, permHealth);

            if (HealPGoodConfig.HealthUpgrades.healOnRespawn) {
                newPlayer.heal(permHealth * 2);
            }
        });
    }
}
