package net.deadlydiamond98.misc;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.deadlydiamond98.util.ExtraHealthHelper;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collection;

public class HealPGoodCommands {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            setHealth(dispatcher, "setTemp", ExtraHealthHelper::setTempHealth, ExtraHealthHelper::getTempHealth);
            setHealth(dispatcher, "setPerm", ExtraHealthHelper::setPermHealth, ExtraHealthHelper::getPermHealth);
        });
    }

    private static void setHealth(CommandDispatcher<ServerCommandSource> dispatcher, String arg, HealthSetter setHealth, HealthGetter getHealth) {
        dispatcher.register(CommandManager.literal("healpgood").requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal(arg)
                        .then(CommandManager.argument("targets", EntityArgumentType.players())
                                .then(CommandManager.argument("count", IntegerArgumentType.integer(0))
                                        .executes(context -> executeSetHealth(
                                                context.getSource(),
                                                setHealth,
                                                getHealth,
                                                EntityArgumentType.getEntities(context, "targets"),
                                                IntegerArgumentType.getInteger(context, "count"))
                                        )))));
    }

    private static int executeSetHealth(ServerCommandSource source, HealthSetter setHealth, HealthGetter getHealth, Collection<? extends Entity> targets, int count) {
        if (count < 0) {
            return 0;
        }

        for (Entity target : targets) {
            ServerPlayerEntity player = (ServerPlayerEntity) target;
            int health = getHealth.getHealth(player);

            setHealth.setHealth(player, count);

            if (health < count) {
                player.heal((count * 2) - health);
            } else if (health > count) {
                player.damage(HealPGoodDamageTypes.of(player.getWorld(), HealPGoodDamageTypes.USE_EMPTY_HEART_CONTAINER), (count * 2) - health);
                player.heal(0);
            }
        }

        if (targets.size() == 1) {
            source.sendFeedback(() -> Text.translatable("commands.healpgood.healthSet.success.single", targets.iterator().next().getDisplayName()), true);
        } else {
            source.sendFeedback(() -> Text.translatable("commands.healpgood.healthSet.success.multiple", targets.size()), true);
        }
        return targets.size();
    }


    @FunctionalInterface
    interface HealthSetter {
        void setHealth(PlayerEntity player, int amount);
    }
    @FunctionalInterface
    interface HealthGetter {
        int getHealth(PlayerEntity player);
    }
}
