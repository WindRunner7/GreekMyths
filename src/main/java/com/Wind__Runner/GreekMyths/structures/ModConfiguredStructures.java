package com.Wind__Runner.GreekMyths.structures;

import com.Wind__Runner.GreekMyths.GreekMyths;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.apache.logging.log4j.Level;

// Special Thanks to TelepathicGrunt and his tutorial on structures.
// https://github.com/TelepathicGrunt/StructureTutorialMod

public class ModConfiguredStructures {
    /**
     * Static instance of our structure so we can reference it and add it to biomes easily.
     */
    public static StructureFeature<?, ?> CONFIGURED_GREEK_CAMP = ModStructures.GREEK_CAMP.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
    public static StructureFeature<?, ?> CONFIGURED_LABYRINTH_CHUNK = ModStructures.LABYRINTH_CHUNK.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

    /**
     * Registers the configured structure which is what gets added to the biomes.
     * Noticed we are not using a forge registry because there is none for configured structures.
     *
     * We can register configured structures at any time before a world is clicked on and made.
     * But the best time to register configured features by code is honestly to do it in FMLCommonSetupEvent.
     */
    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(GreekMyths.MOD_ID, "configured_greek_camp"), CONFIGURED_GREEK_CAMP);
        Registry.register(registry, new ResourceLocation(GreekMyths.MOD_ID, "configured_labyrinth_chunk"), CONFIGURED_LABYRINTH_CHUNK);
        GreekMyths.LOGGER.log(Level.DEBUG, "TESTING FAOUR CONFIG");
        // Ok so, this part may be hard to grasp but basically, just add your structure to this to
        // prevent any sort of crash or issue with other mod's custom ChunkGenerators. If they use
        // FlatGenerationSettings.STRUCTURES in it and you don't add your structure to it, the game
        // could crash later when you attempt to add the StructureSeparationSettings to the dimension.
        //
        // (It would also crash with superflat worldtype if you omit the below line
        //  and attempt to add the structure's StructureSeparationSettings to the world)
        //
        // Note: If you want your structure to spawn in superflat, remove the FlatChunkGenerator check
        // in StructureTutorialMain.addDimensionalSpacing and then create a superflat world, exit it,
        // and re-enter it and your structures will be spawning. I could not figure out why it needs
        // the restart but honestly, superflat is really buggy and shouldn't be your main focus in my opinion.

        FlatGenerationSettings.STRUCTURES.put(ModStructures.GREEK_CAMP.get(), CONFIGURED_GREEK_CAMP);
        FlatGenerationSettings.STRUCTURES.put(ModStructures.LABYRINTH_CHUNK.get(), CONFIGURED_LABYRINTH_CHUNK);
    }
}
