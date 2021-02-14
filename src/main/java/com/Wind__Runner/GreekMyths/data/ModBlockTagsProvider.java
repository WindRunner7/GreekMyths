package com.Wind__Runner.GreekMyths.data;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.init.ModBlocks;
import com.Wind__Runner.GreekMyths.init.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;


public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, GreekMyths.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags(){
        //Blocks
        getOrCreateBuilder(ModTags.Blocks.ORES_SILVER).add(ModBlocks.SILVER_ORE.get());
        getOrCreateBuilder(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_SILVER);
        getOrCreateBuilder(ModTags.Blocks.ORES_COPPER).add(ModBlocks.COPPER_ORE.get());
        getOrCreateBuilder(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_COPPER);
        getOrCreateBuilder(ModTags.Blocks.ORES_TIN).add(ModBlocks.TIN_ORE.get());
        getOrCreateBuilder(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_TIN);

        getOrCreateBuilder(ModTags.Blocks.STORAGE_BLOCKS_SILVER).add(ModBlocks.SILVER_BLOCK.get());
        getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_SILVER);
        getOrCreateBuilder(ModTags.Blocks.STORAGE_BLOCKS_COPPER).add(ModBlocks.COPPER_BLOCK.get());
        getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_COPPER);
        getOrCreateBuilder(ModTags.Blocks.STORAGE_BLOCKS_TIN).add(ModBlocks.TIN_BLOCK.get());
        getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_TIN);
        getOrCreateBuilder(ModTags.Blocks.STORAGE_BLOCKS_BRONZE).add(ModBlocks.BRONZE_BLOCK.get());
        getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_BRONZE);

        getOrCreateBuilder(BlockTags.WALLS).add(ModBlocks.LIMESTONE_BRICK_WALL.get());
        getOrCreateBuilder(BlockTags.WALLS).add(ModBlocks.MUD_BRICK_WALL.get());
    }
}
