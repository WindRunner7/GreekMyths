package com.Wind__Runner.GreekMyths.init;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.entities.PegasusEntity;
import com.Wind__Runner.GreekMyths.structures.GreekCampStructure;
import com.Wind__Runner.GreekMyths.structures.ModConfiguredStructures;
import com.Wind__Runner.GreekMyths.structures.ModStructures;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.*;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.lwjgl.system.CallbackI;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = GreekMyths.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlerForge {

    //Class loader
    public static void register() {
    }

    /**
     * This is the event you will use to add anything to any biome.
     * This includes spawns, changing the biome's looks, messing with its surfacebuilders,
     * adding carvers, spawning new features... etc
     * <p>
     * Here, we will use this to add our structure to all biomes.
     */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void biomeModification(final BiomeLoadingEvent event) {
        // Add our structure to all biomes including other modded biomes.
        // You can skip or add only to certain biomes based on stuff like biome category,
        // temperature, scale, precipitation, mod id, etc. All kinds of options!
        //
        // You can even use the BiomeDictionary as well! To use BiomeDictionary, do
        // RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName()) to get the biome's
        // registrykey. Then that can be fed into the dictionary to get the biome's types.
        event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_GREEK_CAMP);
    }

    /**
     * Will go into the world's chunkgenerator and manually add our structure spacing.
     * If the spacing is not added, the structure doesn't spawn.
     * <p>
     * Use this for dimension blacklists for your structure.
     * (Don't forget to attempt to remove your structure too from
     * the map if you are blacklisting that dimension! It might have
     * your structure in it already.)
     * <p>
     * Basically use this to make absolutely sure the chunkgenerator
     * can or cannot spawn your structure.
     */
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();
            // Prevent spawning our structure in Vanilla's superflat world as
            // people seem to want their superflat worlds free of modded structures.
            // Also that vanilla superflat is really tricky and buggy to work with in my experience.
            if (serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator &&
                    serverWorld.getDimensionKey().equals(World.OVERWORLD)) {
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());
            // putIfAbsent so people can override the spacing with dimension datapacks themselves if they wish to customize spacing more precisely per dimension.
            tempMap.putIfAbsent(ModStructures.GREEK_CAMP.get(), DimensionStructuresSettings.field_236191_b_.get(ModStructures.GREEK_CAMP.get()));
            serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
        }
    }


    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void addStructureSpawnList(final StructureSpawnListGatherEvent event) {
        if (event.getStructure() instanceof GreekCampStructure) {
            event.addEntitySpawn(ModEntities.MOD_FREINDLY_SOLDIER, new MobSpawnInfo.Spawners(ModEntities.GREEKSOLDIER.get(), 1000, 2, 8));
            event.addEntitySpawn(ModEntities.MOD_FREINDLY, new MobSpawnInfo.Spawners(ModEntities.GREEKVILLAGER.get(), 1000, 2, 8));
            GreekMyths.LOGGER.debug(event.getEntitySpawns(ModEntities.MOD_FREINDLY_SOLDIER).toString());
            GreekMyths.LOGGER.debug(event.getEntitySpawns(ModEntities.MOD_FREINDLY).toString());
        }
    }

    @SubscribeEvent
    public static void checkBreakEvent(final BlockEvent.BreakEvent breakEvent) {
        if (breakEvent.getPlayer().world.isRemote == false) {
            if (!breakEvent.getPlayer().isCreative() && breakEvent.getPlayer().world.getDimensionKey() == GreekMyths.LABYRINTH &&
                    breakEvent.getState().getBlock() != Blocks.FIRE) {
                breakEvent.getPlayer().sendMessage(new StringTextComponent(new TranslationTextComponent("This Land Is Guarded By an Ancient Power").getString()), Util.DUMMY_UUID);
                breakEvent.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void checkPlaceEvent(final BlockEvent.EntityPlaceEvent entityPlaceEvent) {
        if (entityPlaceEvent.getEntity() instanceof PlayerEntity && entityPlaceEvent.getEntity().world.isRemote == false) {
            if (!((PlayerEntity) entityPlaceEvent.getEntity()).isCreative() &&
                    ((PlayerEntity) entityPlaceEvent.getEntity()).world.getDimensionKey() == GreekMyths.LABYRINTH &&
                    entityPlaceEvent.getPlacedBlock().getBlock() != Blocks.FIRE) {
                ((PlayerEntity) entityPlaceEvent.getEntity()).sendMessage(new StringTextComponent(new TranslationTextComponent("This Land Is Guarded By an Ancient Power").getString()), Util.DUMMY_UUID);
                ((PlayerEntity) entityPlaceEvent.getEntity()).inventory.decrStackSize(((PlayerEntity) entityPlaceEvent.getEntity()).inventory.getSlotFor(new ItemStack(entityPlaceEvent.getState().getBlock())), 1);
                ((PlayerEntity) entityPlaceEvent.getEntity()).inventory.placeItemBackInInventory(entityPlaceEvent.getEntity().world, new ItemStack(entityPlaceEvent.getState().getBlock()));

                entityPlaceEvent.setCanceled(true);

            }
        }
    }

    @SubscribeEvent
    public static void checkExplosionEvent(final ExplosionEvent.Detonate explosionEvent) {
        if (explosionEvent.getWorld().getDimensionKey() == GreekMyths.LABYRINTH) {
            explosionEvent.getAffectedBlocks().clear();
        }
    }

}
