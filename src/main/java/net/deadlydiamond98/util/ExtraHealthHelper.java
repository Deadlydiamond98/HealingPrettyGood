package net.deadlydiamond98.util;

import net.deadlydiamond98.misc.HealPGoodConfig;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class ExtraHealthHelper {
    private static final UUID PERM_UUID = UUID.fromString("ffd34a3d-591f-445b-968f-057152634a3d");
    private static final UUID TEMP_UUID = UUID.fromString("c1732758-a5cb-4825-bc2c-53e95a3d290b");

    public static boolean addPermHealth(PlayerEntity player, int amount) {
        boolean canAdd = getPermHealth(player) + amount <= HealPGoodConfig.HealthUpgrades.heartContainerMax * 2;
        if (canAdd) {
            setPermHealth(player, getPermHealth(player) + amount);
        }
        return canAdd;
    }

    public static boolean addTempHealth(PlayerEntity player, int amount) {
        boolean canAdd = getTempHealth(player) + amount <= HealPGoodConfig.HealthUpgrades.heartCrystalMax * 2;
        if (canAdd) {
            setTempHealth(player, getTempHealth(player) + amount);
        }
        return canAdd;
    }

    public static boolean removePermHealth(PlayerEntity player, int amount) {
        boolean canRemove = getPermHealth(player) - amount >= 0;
        if (canRemove) {
            setPermHealth(player, getPermHealth(player) - amount);
        }
        return canRemove;
    }

    public static boolean removeTempHealth(PlayerEntity player, int amount) {
        boolean canRemove = getTempHealth(player) - amount >= 0;
        if (canRemove) {
            setTempHealth(player, getTempHealth(player) - amount);
        }
        return canRemove;
    }

    public static void setPermHealth(PlayerEntity player, int amount) {
        cast(player).healpgood$setPermHealth(amount);
        applyHealth(PERM_UUID, "permanent", player, amount);
    }

    public static void setTempHealth(PlayerEntity player, int amount) {
        cast(player).healpgood$setTempHealth(amount);
        applyHealth(TEMP_UUID, "temporary", player, amount);
    }

    public static int getPermHealth(PlayerEntity player) {
        return cast(player).healpgood$getPermHealth();
    }

    public static int getTempHealth(PlayerEntity player) {
        return cast(player).healpgood$getTempHealth();
    }

    private static void applyHealth(UUID uuid, String name, PlayerEntity player, int amount) {
        EntityAttributeInstance healthAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (healthAttribute == null) return;

        if (healthAttribute.getModifier(uuid) != null) {
            healthAttribute.removeModifier(uuid);
        }

        EntityAttributeModifier modifier = new EntityAttributeModifier(uuid, name, amount, EntityAttributeModifier.Operation.ADDITION);
        healthAttribute.addPersistentModifier(modifier);
    }

    private static IPlayerHealth cast(PlayerEntity player) {
        return (IPlayerHealth) player;
    }

//    public static void removePermHealth(PlayerEntity player) {
//        removeHealth(PERM_UUID, player);
//    }
//
//    public static void removeTempHealth(PlayerEntity player) {
//        removeHealth(TEMP_UUID, player);
//    }
//
//    public static void removeHealth(UUID uuid, PlayerEntity player) {
//        EntityAttributeInstance healthAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
//        if (healthAttribute != null && healthAttribute.getModifier(uuid) != null) {
//            healthAttribute.removeModifier(uuid);
//        }
//    }
}
