package com.Wind__Runner.GreekMyths.data;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.init.ModBlocks;
import com.Wind__Runner.GreekMyths.init.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
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
        getOrCreateBuilder(ModTags.Blocks.STORAGE_BLOCKS_SILVER).add(ModBlocks.SILVER_BLOCK.get());
        getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_SILVER);
    }
}
