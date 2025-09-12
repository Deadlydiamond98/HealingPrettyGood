package net.deadlydiamond98.blocks.entities;

import net.deadlydiamond98.HealingPrettyGood;
import net.deadlydiamond98.blocks.HealPGoodBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class HealPGoodBlockEntities {

    public static final BlockEntityType<HeartLanternBlockEntity> HEART_LANTERN = register("heart_lantern",
            FabricBlockEntityTypeBuilder.create(HeartLanternBlockEntity::new, HealPGoodBlocks.HEART_LANTERN).build(null));

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> blockEntity) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(HealingPrettyGood.MOD_ID, name), blockEntity);
    }

    public static void register() {}
}
