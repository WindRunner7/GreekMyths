package com.Wind__Runner.GreekMyths.data.loot;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.init.ModBlocks;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Collectors;


public class ModBlockLootTables extends BlockLootTables {
    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.inverted();
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    @Override
    protected void addTables() {
        registerDropSelfLootTable(ModBlocks.SILVER_ORE.get());
        registerDropSelfLootTable(ModBlocks.SILVER_BLOCK.get());
        registerDropSelfLootTable(ModBlocks.COPPER_BLOCK.get());
        registerDropSelfLootTable(ModBlocks.COPPER_ORE.get());
        registerDropSelfLootTable(ModBlocks.TIN_BLOCK.get());
        registerDropSelfLootTable(ModBlocks.TIN_ORE.get());
        registerDropSelfLootTable(ModBlocks.BRONZE_BLOCK.get());


        registerDropSelfLootTable(ModBlocks.LIMESTONE.get());
        registerDropSelfLootTable(ModBlocks.LIMESTONE_BRICKS.get());
        registerDropSelfLootTable(ModBlocks.LIMESTONE_PILLAR.get());
        registerDropSelfLootTable(ModBlocks.LIMESTONE_BRICK_STAIRS.get());
        registerDropSelfLootTable(ModBlocks.LIMESTONE_BRICK_WALL.get());
        registerDropSelfLootTable(ModBlocks.LIMESTONE_BRICK_SLAB.get());

        registerDropSelfLootTable(ModBlocks.MUD_BRICKS.get());
        registerDropSelfLootTable(ModBlocks.MUD_BRICK_STAIRS.get());
        registerDropSelfLootTable(ModBlocks.MUD_BRICK_WALL.get());
        registerDropSelfLootTable(ModBlocks.MUD_BRICK_SLAB.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> GreekMyths.MOD_ID.equals(block.getRegistryName().getNamespace()))
                .collect(Collectors.toSet());
    }

}
