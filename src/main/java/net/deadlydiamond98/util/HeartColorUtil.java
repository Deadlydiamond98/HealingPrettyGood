package net.deadlydiamond98.util;

import net.deadlydiamond98.HealingPrettyGood;
import net.deadlydiamond98.koalalib.util.ColorHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HeartColorUtil {

    public static final List<Integer> PRIDE = List.of(
            0xff4027,
            0xf96527,
            0xff8b27,
            0xffb127,
            0xffd727,
            0xc2ff26,
            0x68ff27,
            0x48ff7e,
            0x27ffd4,
            0x27cdea,
            0x279bff,
            0x3561ff,
            0x4227ff,
            0x6827ff,
            0x8e27ff,
            0xb427ff,
            0xda27ff,
            0xed27d8,
            0xff27b1,
            0xf9336c
    );
    public static final List<Integer> REGULAR = List.of(0xFF2B2B, 0xDB23FA);

    public static List<Integer> current = PRIDE;
    public static int currentColor = 0xF23E27;
    public static int lastColor = 0xF23E27;
    public static int index = 0;
    public static int time = 0;

    public static void transitionColor() {
        currentColor = ColorHelper.lerpHexColor(currentColor, current.get(index), 0.01f);

        if (currentColor == lastColor) {
            index = (index + 1) % current.size();
        }

        lastColor = currentColor;
    }

    public static int[] getColorARGB() {
        transitionColor();
        return ColorHelper.hexToARGB(currentColor);
    }
}
