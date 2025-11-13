package net.deadlydiamond98.util;

import net.deadlydiamond98.koalalib.init.KoalaLibSounds;
import net.deadlydiamond98.misc.HealPGoodSounds;
import net.minecraft.sound.SoundEvent;

public enum SeasonalHeart {
    VANILLA(2, "regular"),
    NOSEASON(2, "regular"),
    CHRISTMAS(2, "christmas"),
    PRIDE(20, "pride"),
    APRIL(2, KoalaLibSounds.BAGEL, "april");

    public final int frames;
    public final SoundEvent soundEvent;
    public final String texture;

    SeasonalHeart(int frames, SoundEvent soundEvent, String texture) {
        this.frames = frames;
        this.soundEvent = soundEvent;
        this.texture = texture;
    }

    SeasonalHeart(int frames, String texture) {
        this(frames, HealPGoodSounds.HEART_PICKUP, texture);
    }

    public boolean canChange() {
        return this == VANILLA;
    }

    public boolean cantChange() {
        return this == NOSEASON;
    }

}
