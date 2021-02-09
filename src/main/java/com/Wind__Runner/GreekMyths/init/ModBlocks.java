package com.Wind__Runner.GreekMyths.init;

import com.Wind__Runner.GreekMyths.GreekMyths;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final RegistryObject<Block> SILVER_ORE = register("silver_ore", () ->
            new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3, 10).harvestLevel(2).sound(SoundType.STONE)));

    public static final RegistryObject<Block> SILVER_BLOCK = register("silver_block", () ->
            new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3, 10).sound(SoundType.METAL)));

    public static final RegistryObject<Block> COPPER_ORE = register("copper_ore", () ->
            new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3, 10).harvestLevel(2).sound(SoundType.STONE)));

    public static final RegistryObject<Block> COPPER_BLOCK = register("copper_block", () ->
            new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3, 10).sound(SoundType.METAL)));

    public static final RegistryObject<Block> LIMESTONE = register("lime_stone", () ->
            new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3, 10).sound(SoundType.STONE)));
    public static final RegistryObject<Block> LIMESTONE_BRICKS = register("lime_stone_bricks", () ->
            new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3, 10).sound(SoundType.STONE)));
    public static final RegistryObject<RotatedPillarBlock> LIMESTONE_PILLAR = register("lime_stone_pillar", () ->
            new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3, 10).sound(SoundType.STONE)));
    public static final RegistryObject<StairsBlock> LIMESTONE_BRICK_STAIRS = register("lime_stone_brick_stairs", () ->
            new StairsBlock(() -> LIMESTONE_BRICKS.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)));
    public static final RegistryObject<SlabBlock> LIMESTONE_BRICK_SLAB = register("lime_stone_brick_slab", () ->
            new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3, 10).sound(SoundType.STONE)));
    public static final RegistryObject<WallBlock> LIMESTONE_BRICK_WALL = register("lime_stone_brick_wall", () ->
            new WallBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3, 10).sound(SoundType.STONE)));


    public static void register() {

    }

    public static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }

    public static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().group(GreekMyths.ITEM_GROUP)));
        return ret;
    }
}
