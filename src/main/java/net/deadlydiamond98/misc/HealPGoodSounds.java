package net.deadlydiamond98.misc;

import net.deadlydiamond98.HealingPrettyGood;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class HealPGoodSounds {
    public static final SoundEvent HEART_PICKUP = registerSound("entity.heart.pickup");
    public static final SoundEvent MUSIC_DISC_HEARTSTEP = registerSound("music_disc.heartstep");

    private static SoundEvent registerSound(String name) {
        Identifier id = new Identifier(HealingPrettyGood.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void register() {}
}
