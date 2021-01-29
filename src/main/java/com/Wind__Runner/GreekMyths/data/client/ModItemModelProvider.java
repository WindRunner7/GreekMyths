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

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder(itemGenerated, "silver_ingot");

        builder(itemGenerated, "greekarmor_helmet");
        builder(itemGenerated, "greekarmor_chestplate");
        builder(itemGenerated, "greekarmor_leggings");
        builder(itemGenerated, "greekarmor_boots");

        builder(itemGenerated, "mythicarmor_helmet");
        builder(itemGenerated, "mythicarmor_chestplate");
        builder(itemGenerated, "mythicarmor_leggings");
        builder(itemGenerated, "mythicarmor_boots");
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
