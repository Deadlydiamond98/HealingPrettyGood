package net.deadlydiamond98.events;

import net.deadlydiamond98.HealingPrettyGood;
import net.deadlydiamond98.entities.HeartPickupEntity;
import net.deadlydiamond98.misc.HealPGoodConfig;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;


public class HealPGoodAfterDeath {
    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {

            if (entity instanceof PlayerEntity || entity instanceof AmbientEntity || entity instanceof EnderDragonEntity) {
                return;
            }

            Random random = entity.getRandom();

            // Decrease Chance if Passive Mob
            double passiveAugment = entity instanceof PassiveEntity ? HealPGoodConfig.Hearts.passiveAugment : 0;
            // Increase Chance based on Health Amount
            double healthAugment = entity.getMaxHealth() * HealPGoodConfig.Hearts.healthAugment;
            // Final Chance
            double dropChance = HealPGoodConfig.Hearts.regularDropChance + passiveAugment + healthAugment;

            // Drop More Hearts if entity has more HP, but only if not 0 or less!!
            int healthPerHeart = HealPGoodConfig.Hearts.healthToHearts;
            int amount = healthPerHeart > 0 ? (int) (entity.getMaxHealth() / healthPerHeart) : 1;

            for (int i = 0; i < amount; i++) {

                if (random.nextFloat() <= dropChance) {

                    // Decrease Chance as Hearts Drop
                    dropChance -= HealPGoodConfig.Hearts.chanceDecay;

                    if (entity.getWorld() instanceof ServerWorld serverWorld) {
                        HeartPickupEntity.spawn(serverWorld, entity.getPos(), 1);
                    }
                }
            }
        });
    }
}
