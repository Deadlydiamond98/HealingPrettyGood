package net.deadlydiamond98.entities;

import net.deadlydiamond98.HealingPrettyGood;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class HealPGoodEntities {

    public static final EntityType<HeartPickupEntity> HEART_PICKUP_ENTITY = register("heart", create(HeartPickupEntity.class, 0.5f, 0.5f));
    public static final EntityType<HeartBottleEntity> HEART_BOTTLE_ENTITY = register("heart_bottle", create(HeartBottleEntity.class, 0.25f, 0.25f));

    public static <T extends Entity> EntityType<T> register(String name, FabricEntityTypeBuilder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(HealingPrettyGood.MOD_ID, name), builder.build());
    }

    public static <T extends Entity> FabricEntityTypeBuilder<T> create(Class<T> entityClass, float width, float height) {
        return FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory(entityClass)).dimensions(EntityDimensions.fixed(width, height));
    }

    public static <T extends Entity> EntityType.EntityFactory<T> factory(Class<T> entityClass) {
        return (EntityType<T> type, World world) -> {
            try {
                return entityClass.getConstructor(EntityType.class, World.class).newInstance(type, world);
            } catch (Exception ignored) {
                return null;
            }
        };
    }

    public static void register() {}
}
