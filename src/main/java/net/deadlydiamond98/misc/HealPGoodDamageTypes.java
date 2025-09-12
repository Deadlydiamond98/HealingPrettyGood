package net.deadlydiamond98.misc;

import net.deadlydiamond98.HealingPrettyGood;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class HealPGoodDamageTypes {

    public static final RegistryKey<DamageType> USE_EMPTY_HEART_CONTAINER = register("empty_heart_container");

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }

    private static RegistryKey<DamageType> register(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(HealingPrettyGood.MOD_ID, name));
    }
}
