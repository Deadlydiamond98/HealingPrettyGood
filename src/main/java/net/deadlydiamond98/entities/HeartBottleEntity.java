package net.deadlydiamond98.entities;

import net.deadlydiamond98.items.HealPGoodItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class HeartBottleEntity extends ThrownItemEntity {

    public HeartBottleEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public HeartBottleEntity(World world, PlayerEntity user) {
        super(HealPGoodEntities.HEART_BOTTLE_ENTITY, user, world);
    }

    @Override
    protected Item getDefaultItem() {
        return HealPGoodItems.HEART_BOTTLE;
    }

    @Override
    protected float getGravity() {
        return 0.07f;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (this.getWorld() instanceof ServerWorld) {
            this.getWorld().syncWorldEvent(WorldEvents.SPLASH_POTION_SPLASHED, this.getBlockPos(), StatusEffects.RESISTANCE.getColor());
            int i = 3 + this.getWorld().random.nextInt(5) + this.getWorld().random.nextInt(5);
            HeartPickupEntity.spawn((ServerWorld)this.getWorld(), this.getPos(), i);
            this.discard();
        }
    }
}
