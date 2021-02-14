package com.Wind__Runner.GreekMyths.structures;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.entities.GreekVillagerEntity;
import com.Wind__Runner.GreekMyths.entities.HippocampusEntity;
import com.Wind__Runner.GreekMyths.entities.PegasusEntity;
import com.Wind__Runner.GreekMyths.init.Registration;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.World;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

// Special Thanks to TelepathicGrunt and his tutorial on structures.
// https://github.com/TelepathicGrunt/StructureTutorialMod

@Mod.EventBusSubscriber(modid = GreekMyths.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModStructures {

    /**
     * Registers the structure itself and sets what its path is. In this case, the
     * structure will have the resourcelocation of structure_tutorial:run_down_house.
     *
     * It is always a good idea to register your Structures so that other mods and datapacks can
     * use them too directly from the registries. It great for mod/datapacks compatibility.
     *
     * IMPORTANT: Once you have set the name for your structure below and distributed your mod,
     * it should NEVER be changed or else it can cause worlds to become corrupted if they generated
     * any chunks with your mod with the old structure name. See MC-194811 in Mojang's bug tracker for details.
     *
     * Forge has an issue report here: https://github.com/MinecraftForge/MinecraftForge/issues/7363
     * Keep watch on that to know when it is safe to remove or change structure's registry names
     */

    //Class loader
    public static void register() {
    }

    public static final RegistryObject<Structure<NoFeatureConfig>> GREEK_CAMP = registerStructure("greek_camp", () -> (new GreekCampStructure(NoFeatureConfig.field_236558_a_)));
    public static final RegistryObject<Structure<NoFeatureConfig>> LABYRINTH_CHUNK = registerStructure("labyrinth_chunk", () -> (new LabyrinthChunkStructure(NoFeatureConfig.field_236558_a_)));


    /**
     * Helper method for registering all structures
     */
    private static <T extends Structure<?>> RegistryObject<T> registerStructure(String name, Supplier<T> structure) {
        return Registration.DEFERRED_REGISTRY_STRUCTURE.register(name, structure);
    }


    public static void setupStructures() {
        setupMapSpacingAndLand(
                GREEK_CAMP.get(), /* The instance of the structure */
                new StructureSeparationSettings(10 /* maximum distance apart in chunks between spawn attempts */,
                        5 /* minimum distance apart in chunks between spawn attempts */,
                        993857768 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
                true);
        setupMapSpacingAndLand(
                LABYRINTH_CHUNK.get(), /* The instance of the structure */
                new StructureSeparationSettings(1/* maximum distance apart in chunks between spawn attempts */,
                        1 /* minimum distance apart in chunks between spawn attempts */,
                        993857762 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
                true);
        GreekMyths.LOGGER.log(Level.DEBUG, "TESTING FAOUR SETUP");
        // Add more structures here and so on
    }

    /**
     * Adds the provided structure to the registry, and adds the separation settings.
     * The rarity of the structure is determined based on the values passed into
     * this method in the structureSeparationSettings argument. Called by registerFeatures.
     */
    public static <F extends Structure<?>> void setupMapSpacingAndLand(
            F structure,
            StructureSeparationSettings structureSeparationSettings,
            boolean transformSurroundingLand)
    {
        /*
         * We need to add our structures into the map in Structure alongside vanilla
         * structures or else it will cause errors. Called by registerStructure.
         *
         * If the registration is setup properly for the structure,
         * getRegistryName() should never return null.
         */
        Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);

        /*
         * Whether surrounding land will be modified automatically to conform to the bottom of the structure.
         * Basically, it adds land at the base of the structure like it does for Villages and Outposts.
         * Doesn't work well on structure that have pieces stacked vertically or change in heights.
         *
         * Note: The air space this method will create will be filled with water if the structure is below sealevel.
         * This means this is best for structure above sealevel so keep that in mind.
         */
        if(transformSurroundingLand){
            Structure.field_236384_t_ = ImmutableList.<Structure<?>>builder()
                            .addAll(Structure.field_236384_t_)
                            .add(structure)
                            .build();
        }

        /*
         * Adds the structure's spacing into several places so that the structure's spacing remains
         * correct in any dimension or worldtype instead of not spawning.
         *
         * However, it seems it doesn't always work for code made dimensions as they read from
         * this list beforehand. Use the WorldEvent.Load event in StructureTutorialMain to add
         * the structure spacing from this list into that dimension.
         */
        DimensionStructuresSettings.field_236191_b_ = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.field_236191_b_)
                        .put(structure, structureSeparationSettings)
                        .build();
    }

    @SubscribeEvent
    public static void setupStructureHandler(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            setupStructures();
            ModConfiguredStructures.registerConfiguredStructures();
        });
    }



}
