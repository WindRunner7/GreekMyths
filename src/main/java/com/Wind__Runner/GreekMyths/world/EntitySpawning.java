package com.Wind__Runner.GreekMyths.world;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.concurrent.ITaskQueue;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GreekMyths.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntitySpawning {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void addBiomeSpawns(final BiomeLoadingEvent event) {
        if(event.getCategory() == Biome.Category.OCEAN) {
            addSpawns(event, ModEntities.HIPPOCAMPUS.get(), 100, 1, 2);
        }
        addSpawns(event, ModEntities.GREEKVILLAGER.get(), 100, 1, 3);
        addSpawns(event, ModEntities.PEGASUS.get(), 100, 1, 3);
    }

    private static void addSpawns(final BiomeLoadingEvent biome, final EntityType<?> entity, final int chance, final int min, final int max) {

        if (biome.getCategory() == Biome.Category.NETHER) {

        } else if (biome.getCategory() == Biome.Category.THEEND) {

        } else {
            biome.getSpawns().withSpawner(entity.getClassification(), new MobSpawnInfo.Spawners(entity, chance, min, max));
        }
    }

}
