package net.deadlydiamond98.misc;

import net.deadlydiamond98.HealingPrettyGood;
import net.deadlydiamond98.koalalib.common.misc.CustomAdvancement;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.util.Identifier;

public class HealPGoodAdvancements {
    public static final CustomAdvancement HEART_CRYSTAL_BREAK = register("heart_crystal_break");
    public static final CustomAdvancement CRYSTAL_HEART_USED = register("crystal_heart_use");
    public static final CustomAdvancement HEART_CONTAINER_USED = register("heart_container_use");
    public static final CustomAdvancement EMPTY_HEART_CONTAINER_USED = register("empty_heart_container_use");
    public static final CustomAdvancement MAX_HEALTH = register("max_health");

    public static CustomAdvancement register(String name) {
        return Criteria.register(new CustomAdvancement(new Identifier(HealingPrettyGood.MOD_ID, name)));
    }

    public static void register() {}
}
