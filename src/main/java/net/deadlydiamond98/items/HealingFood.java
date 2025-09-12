package net.deadlydiamond98.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HealingFood extends Item {

    private final int health;

    public HealingFood(Settings settings, int health) {
        super(settings);
        this.health = health;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.heal(this.health);
        return super.finishUsing(stack, world, user);
    }
}
