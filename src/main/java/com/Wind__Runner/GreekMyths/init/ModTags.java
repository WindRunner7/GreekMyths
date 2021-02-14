package com.Wind__Runner.GreekMyths.init;

import com.Wind__Runner.GreekMyths.GreekMyths;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class ModTags {
    public static final class Blocks {
        public static final ITag.INamedTag<Block> ORES_SILVER = forge("ores/silver");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_SILVER = forge("storage_blocks/silver");
        public static final ITag.INamedTag<Block> ORES_COPPER = forge("ores/copper");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_COPPER = forge("storage_blocks/copper");
        public static final ITag.INamedTag<Block> ORES_TIN = forge("ores/tin");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_TIN = forge("storage_blocks/tin");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_BRONZE = forge("storage_blocks/bronze");

        private static ITag.INamedTag<Block> forge(String path) {
            return BlockTags.makeWrapperTag(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Block> mod(String path) {
            return BlockTags.makeWrapperTag(new ResourceLocation(GreekMyths.MOD_ID, path).toString());
        }
    }

    public static final class Items {
        //Blocks as Items
        public static final ITag.INamedTag<Item> ORES_SILVER = forge("ores/silver");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_SILVER = forge("storage_blocks/silver");
        public static final ITag.INamedTag<Item> ORES_COPPER = forge("ores/copper");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_COPPER = forge("storage_blocks/copper");
        public static final ITag.INamedTag<Item> ORES_TIN = forge("ores/tin");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_TIN = forge("storage_blocks/tin");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_BRONZE = forge("storage_blocks/bronze");

        //Items
        public static final ITag.INamedTag<Item> INGOT_SILVER = forge("ingot/silver");
        public static final ITag.INamedTag<Item> INGOT_COPPER = forge("ingot/copper");
        public static final ITag.INamedTag<Item> INGOT_TIN = forge("ingot/tin");
        public static final ITag.INamedTag<Item> INGOT_BRONZE = forge("ingot/bronze");

        //Armor

        public static  final  ITag.INamedTag<Item> ARMOR_GREEK_HELMET = mod("armor/greek_helmet");
        public static  final  ITag.INamedTag<Item> ARMOR_GREEK_CHESTPLATE = mod("armor/greek_chestplate");
        public static  final  ITag.INamedTag<Item> ARMOR_GREEK_LEGGINGS = mod("armor/greek_leggings");
        public static  final  ITag.INamedTag<Item> ARMOR_GREEK_BOOTS = mod("armor/greek_boots");

        public static  final  ITag.INamedTag<Item> ARMOR_MYTHIC_HELMET = mod("armor/mythic_helmet");
        public static  final  ITag.INamedTag<Item> ARMOR_MYTHIC_CHESTPLATE = mod("armor/mythic_chestplate");
        public static  final  ITag.INamedTag<Item> ARMOR_MYTHIC_LEGGINGS = mod("armor/mythic_leggings");
        public static  final  ITag.INamedTag<Item> ARMOR_MYTHIC_BOOTS = mod("armor/mythic_boots");


        private static ITag.INamedTag<Item> forge(String path) {
            return ItemTags.makeWrapperTag(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Item> mod(String path) {
            return ItemTags.makeWrapperTag(new ResourceLocation(GreekMyths.MOD_ID, path).toString());
        }
    }
}
