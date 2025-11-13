package net.deadlydiamond98.items.heartapplier;

import net.deadlydiamond98.koalalib.common.advancement.CustomAdvancement;
import net.deadlydiamond98.koalalib.common.items.interaction.IFloating;
import net.deadlydiamond98.misc.HealPGoodAdvancements;
import net.deadlydiamond98.misc.HealPGoodSounds;
import net.deadlydiamond98.util.ExtraHealthHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HeartContainerItem extends AbstractHeartItem implements IFloating {

    public HeartContainerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ItemStack stack = user.getStackInHand(hand);
            if (ExtraHealthHelper.addPermHealth(user, 2)) {
                return consumeHeart(user, stack, true);
            }
            return fail(user, stack);
        }

        return super.use(world, user, hand);
    }

    @Override
    protected CustomAdvancement getAdvancement() {
        return HealPGoodAdvancements.HEART_CONTAINER_USED;
    }

    @Override
    protected SoundEvent getSound() {
        return HealPGoodSounds.HEART_CONTAINER_USED;
    }
}

