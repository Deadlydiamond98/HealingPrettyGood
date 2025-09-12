package net.deadlydiamond98.blocks;

import net.deadlydiamond98.entities.HeartPickupEntity;
import net.deadlydiamond98.misc.HealPGoodAdvancements;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class HeartCrystalBlock extends ExperienceDroppingBlock {

    public static final BooleanProperty PLAYERMADE = BooleanProperty.of("playermade");

    public HeartCrystalBlock(Settings settings, IntProvider experience) {
        super(settings, experience);
        setDefaultState(getDefaultState().with(PLAYERMADE, false));
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(PLAYERMADE, ctx.getPlayer() != null);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (!world.isClient() && !state.get(PLAYERMADE)) {
            HealPGoodAdvancements.HEART_CRYSTAL_BREAK.trigger((ServerPlayerEntity) player);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(PLAYERMADE);
    }

    @Override
    protected void dropExperience(ServerWorld world, BlockPos pos, int size) {
        super.dropExperience(world, pos, size);
        if (world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && world.getRandom().nextFloat() < 0.5) {
            HeartPickupEntity.spawn(world, pos.toCenterPos(), 1);
        }
    }
}
