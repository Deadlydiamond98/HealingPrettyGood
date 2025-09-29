package net.deadlydiamond98.misc;

import net.deadlydiamond98.koalalib.config.CFGProperties;
import net.deadlydiamond98.util.SeasonalHeart;

public class HealPGoodConfig {

    public static class Main {
        public static SeasonalHeart seasonalHearts = SeasonalHeart.VANILLA;
        public static boolean dragonDrops = true;
        public static double heartLanternX = 7.0;
        public static double heartLanternY = 3.5;
    }
    public static class Hearts {
        @CFGProperties(min = 0, max = 1)
        public static double regularDropChance = 0.15;
        public static double passiveAugment = -0.4;
        public static double healthAugment = 0.01;
        @CFGProperties(min = 0)
        public static int healthToHearts = 15;
        public static double chanceDecay = 0.05;
    }
    public static class HealthUpgrades {
        public static boolean healOnRespawn = false;
        public static int heartCrystalMax = 10;
        public static int heartContainerMax = 10;
    }
}
