package net.deadlydiamond98.items.heartapplier;

import net.deadlydiamond98.items.HealPGoodItems;
import net.deadlydiamond98.koalalib.common.advancement.CustomAdvancement;
import net.deadlydiamond98.misc.HealPGoodAdvancements;
import net.deadlydiamond98.misc.HealPGoodSounds;
import net.deadlydiamond98.util.ExtraHealthHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EmptyHeartContainerItem extends AbstractHeartItem {
    public EmptyHeartContainerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ItemStack stack = user.getStackInHand(hand);

            if (ExtraHealthHelper.removePermHealth(user, 2)) {
                user.giveItemStack(new ItemStack(HealPGoodItems.HEART_CONTAINER));
                return consumeHeart(user, stack, false);
            } else if (ExtraHealthHelper.removeTempHealth(user, 2)) {
                return consumeHeart(user, stack, false);
            }
            return fail(user, stack);
        }

        return super.use(world, user, hand);
    }

    @Override
    protected CustomAdvancement getAdvancement() {
        return HealPGoodAdvancements.EMPTY_HEART_CONTAINER_USED;
    }

    @Override
    protected SoundEvent getSound() {
        return HealPGoodSounds.EMPTY_HEART_CONTAINER_USED;
    }

}
