package net.deadlydiamond98.blocks;

import net.deadlydiamond98.HealingPrettyGood;
import net.deadlydiamond98.koalalib.KoalaLib;
import net.deadlydiamond98.koalalib.common.blocksets.BaseStairSlabBlockset;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class HealPGoodBlocks {

    public static final AbstractBlock.Settings HEART_CRYSTAL = FabricBlockSettings
            .copyOf(Blocks.AMETHYST_BLOCK)
            .strength(3.0f, 3.0f)
            .requiresTool()
            .luminance(15);

    public static final Block HEART_CRYSTAL_BLOCK = register("heart_crystal_block",
            new HeartCrystalBlock(HEART_CRYSTAL, UniformIntProvider.create(3, 7)));

    public static final BaseStairSlabBlockset POLISHED_HEART_CRYSTAL = new BaseStairSlabBlockset(
            HealingPrettyGood.MOD_ID, "polished_heart_crystal", HEART_CRYSTAL);

    public static final BaseStairSlabBlockset HEART_CRYSTAL_BRICKS = new BaseStairSlabBlockset(
            HealingPrettyGood.MOD_ID, "heart_crystal_bricks", HEART_CRYSTAL);

    public static final Block HEART_LANTERN = register("heart_lantern", new HeartLanternBlock(HEART_CRYSTAL));

    private static Block register(String id, Block block) {
        Block registeredBlock = Registry.register(Registries.BLOCK, new Identifier(HealingPrettyGood.MOD_ID, id), block);
        registerItem(id, registeredBlock);
        return registeredBlock;
    }

    private static void registerItem(String id, Block block) {
        Registry.register(Registries.ITEM, new Identifier(HealingPrettyGood.MOD_ID, id), new BlockItem(block, new FabricItemSettings()));
    }

    public static void register() {}
}
