package net.deadlydiamond98.items.heartapplier;

import net.deadlydiamond98.koalalib.common.misc.CustomAdvancement;
import net.deadlydiamond98.koalalib.common.misc.ModSharedSounds;
import net.deadlydiamond98.misc.HealPGoodDamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.TypedActionResult;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHeartItem extends Item {

    public static final List<Item> HEALTH_MODIFIERS = new ArrayList<>();

    public AbstractHeartItem(Settings settings) {
        super(settings);
        HEALTH_MODIFIERS.add(this);
    }

    protected TypedActionResult<ItemStack> consumeHeart(PlayerEntity user, ItemStack stack, boolean heal) {
        if (heal) {
            user.heal(2);
        } else {
            user.damage(HealPGoodDamageTypes.of(user.getWorld(), HealPGoodDamageTypes.USE_EMPTY_HEART_CONTAINER), 2);
            user.heal(0);
        }
        applyCooldown(user);
        stack.decrement(1);
        getAdvancement().trigger((ServerPlayerEntity) user);
        user.playSound(getSound(), SoundCategory.PLAYERS, 1,1);
        return TypedActionResult.consume(stack);
    }

    protected TypedActionResult<ItemStack> fail(PlayerEntity user, ItemStack stack) {
        user.playSound(ModSharedSounds.CONSOLE_CRAFT_FAIL, SoundCategory.PLAYERS, 0.5f, 1);
        return TypedActionResult.fail(stack);
    }

    protected static void applyCooldown(PlayerEntity user) {
        HEALTH_MODIFIERS.forEach(item -> user.getItemCooldownManager().set(item, 20));
    }

    protected abstract CustomAdvancement getAdvancement();
    protected abstract SoundEvent getSound();
}
