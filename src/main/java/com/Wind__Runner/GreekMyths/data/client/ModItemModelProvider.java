package com.Wind__Runner.GreekMyths.data.client;

import com.Wind__Runner.GreekMyths.GreekMyths;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, GreekMyths.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("silver_block", modLoc("block/silver_block"));
        withExistingParent("silver_ore", modLoc("block/silver_ore"));
        withExistingParent("copper_block", modLoc("block/copper_block"));
        withExistingParent("copper_ore", modLoc("block/copper_ore"));
        withExistingParent("tin_block", modLoc("block/tin_block"));
        withExistingParent("tin_ore", modLoc("block/tin_ore"));
        withExistingParent("bronze_block", modLoc("block/bronze_block"));

        withExistingParent("lime_stone", modLoc("block/lime_stone"));
        withExistingParent("lime_stone_bricks", modLoc("block/lime_stone_bricks"));
        withExistingParent("lime_stone_pillar", modLoc("block/lime_stone_pillar"));
        withExistingParent("lime_stone_brick_stairs", modLoc("block/lime_stone_brick_stairs"));
        withExistingParent("lime_stone_brick_slab", modLoc("block/lime_stone_brick_slab"));
        wallInventory("lime_stone_brick_wall", modLoc("block/lime_stone_bricks"));

        withExistingParent("mud_bricks", modLoc("block/mud_bricks"));
        withExistingParent("mud_brick_stairs", modLoc("block/mud_brick_stairs"));
        withExistingParent("mud_brick_slab", modLoc("block/mud_brick_slab"));
        wallInventory("mud_brick_wall", modLoc("block/mud_bricks"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder(itemGenerated, "silver_ingot");
        builder(itemGenerated, "copper_ingot");
        builder(itemGenerated, "tin_ingot");
        builder(itemGenerated, "bronze_ingot");

        builder(itemGenerated, "greekarmor_helmet");
        builder(itemGenerated, "greekarmor_chestplate");
        builder(itemGenerated, "greekarmor_leggings");
        builder(itemGenerated, "greekarmor_boots");

        builder(itemGenerated, "mythicarmor_helmet");
        builder(itemGenerated, "mythicarmor_chestplate");
        builder(itemGenerated, "mythicarmor_leggings");
        builder(itemGenerated, "mythicarmor_boots");

        builder(itemGenerated, "labyrinth_key");
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
