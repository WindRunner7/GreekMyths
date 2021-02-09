package com.Wind__Runner.GreekMyths.data;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.init.ModItems;
import com.Wind__Runner.GreekMyths.init.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, GreekMyths.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        //Blocks
        copy(ModTags.Blocks.ORES_SILVER, ModTags.Items.ORES_SILVER);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ModTags.Blocks.STORAGE_BLOCKS_SILVER, ModTags.Items.STORAGE_BLOCKS_SILVER);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        copy(ModTags.Blocks.ORES_COPPER, ModTags.Items.ORES_COPPER);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ModTags.Blocks.STORAGE_BLOCKS_COPPER, ModTags.Items.STORAGE_BLOCKS_COPPER);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        //Items
        getOrCreateBuilder(ModTags.Items.INGOT_SILVER).add(ModItems.SILVER_INGOT.get());
        getOrCreateBuilder(Tags.Items.INGOTS).addTag(ModTags.Items.INGOT_SILVER);

        getOrCreateBuilder(ModTags.Items.INGOT_COPPER).add(ModItems.COPPER_INGOT.get());
        getOrCreateBuilder(Tags.Items.INGOTS).addTag(ModTags.Items.INGOT_COPPER);

        //Armor
        getOrCreateBuilder(ModTags.Items.ARMOR_GREEK_HELMET).add(ModItems.GREEK_ARMOR_HELMET.get());
        getOrCreateBuilder(ModTags.Items.ARMOR_GREEK_CHESTPLATE).add(ModItems.GREEK_ARMOR_CHESTPLATE.get());
        getOrCreateBuilder(ModTags.Items.ARMOR_GREEK_LEGGINGS).add(ModItems.GREEK_ARMOR_LEGGINGS.get());
        getOrCreateBuilder(ModTags.Items.ARMOR_GREEK_BOOTS).add(ModItems.GREEK_ARMOR_BOOTS.get());

        getOrCreateBuilder(ModTags.Items.ARMOR_MYTHIC_HELMET).add(ModItems.MYTHIC_ARMOR_HELMET.get());
        getOrCreateBuilder(ModTags.Items.ARMOR_MYTHIC_CHESTPLATE).add(ModItems.MYTHIC_ARMOR_CHESTPLATE.get());
        getOrCreateBuilder(ModTags.Items.ARMOR_MYTHIC_LEGGINGS).add(ModItems.MYTHIC_ARMOR_LEGGINGS.get());
        getOrCreateBuilder(ModTags.Items.ARMOR_MYTHIC_BOOTS).add(ModItems.MYTHIC_ARMOR_BOOTS.get());
    }
}
