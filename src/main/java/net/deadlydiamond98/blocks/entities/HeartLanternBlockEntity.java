package net.deadlydiamond98.blocks.entities;

import net.deadlydiamond98.misc.HealPGoodConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class HeartLanternBlockEntity extends BlockEntity {

    private int age;

    public HeartLanternBlockEntity(BlockPos pos, BlockState state) {
        super(HealPGoodBlockEntities.HEART_LANTERN, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState blockState, HeartLanternBlockEntity entity) {
        if (!world.isClient()) {
            if (entity.age++ % 60 == 0) {
                Box box = new Box(pos).expand(
                        HealPGoodConfig.Main.heartLanternX,
                        HealPGoodConfig.Main.heartLanternY,
                        HealPGoodConfig.Main.heartLanternX
                );
                List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);
                list.forEach(player -> player.addStatusEffect(
                        new StatusEffectInstance(StatusEffects.REGENERATION, 60, 0, true, true))
                );
            }
        }
    }
}
