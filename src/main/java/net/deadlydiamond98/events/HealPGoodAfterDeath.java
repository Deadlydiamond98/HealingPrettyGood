package net.deadlydiamond98.events;

import net.deadlydiamond98.entities.HeartPickupEntity;
import net.deadlydiamond98.misc.HealPGoodConfig;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;


public class HealPGoodAfterDeath {
    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {

            Random random = entity.getRandom();

            if (random.nextFloat() <= HealPGoodConfig.Hearts.regularDropChance) {
                if (entity.getWorld() instanceof ServerWorld serverWorld) {
                    HeartPickupEntity.spawn(serverWorld, entity.getPos(), 1);
                }
            }
        });
    }
}
