package com.Wind__Runner.GreekMyths.data;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.data.client.ModBlockStateProvider;
import com.Wind__Runner.GreekMyths.data.client.ModItemModelProvider;
import com.Wind__Runner.GreekMyths.data.loot.ModLootTablesProvider;
import com.Wind__Runner.GreekMyths.data.recipes.ModRecipesProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = GreekMyths.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    public DataGenerators() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(new ModBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(new ModItemModelProvider(gen, existingFileHelper));

        //TAGS
        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(gen, existingFileHelper);
        gen.addProvider(blockTags);
        gen.addProvider(new ModItemTagsProvider(gen, blockTags, existingFileHelper));

        gen.addProvider(new ModLootTablesProvider(gen));
        gen.addProvider(new ModRecipesProvider(gen));
    }
}
