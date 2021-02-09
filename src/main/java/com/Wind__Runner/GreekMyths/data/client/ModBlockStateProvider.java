package com.Wind__Runner.GreekMyths.data.client;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.init.ModBlocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, GreekMyths.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.SILVER_BLOCK.get());
        simpleBlock((ModBlocks.SILVER_ORE.get()));
        simpleBlock(ModBlocks.COPPER_BLOCK.get());
        simpleBlock((ModBlocks.COPPER_ORE.get()));
        simpleBlock((ModBlocks.LIMESTONE_BRICKS.get()));
        simpleBlock((ModBlocks.LIMESTONE.get()));
        logBlock(ModBlocks.LIMESTONE_PILLAR.get());
        stairsBlock(ModBlocks.LIMESTONE_BRICK_STAIRS.get(), new ResourceLocation(GreekMyths.MOD_ID + ":block/lime_stone_bricks"));
        wallBlock(ModBlocks.LIMESTONE_BRICK_WALL.get(), new ResourceLocation(GreekMyths.MOD_ID + ":block/lime_stone_bricks"));
        slabBlock(ModBlocks.LIMESTONE_BRICK_SLAB.get(), new ResourceLocation(GreekMyths.MOD_ID + ":block/lime_stone_bricks"), new ResourceLocation(GreekMyths.MOD_ID + ":block/lime_stone_bricks"));

    }
}
