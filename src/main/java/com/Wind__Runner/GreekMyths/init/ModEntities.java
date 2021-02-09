package com.Wind__Runner.GreekMyths.init;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.entities.GreekSoldierEntity;
import com.Wind__Runner.GreekMyths.entities.GreekVillagerEntity;
import com.Wind__Runner.GreekMyths.entities.HippocampusEntity;
import com.Wind__Runner.GreekMyths.entities.PegasusEntity;
import cpw.mods.modlauncher.EnumerationHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nullable;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = GreekMyths.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final EntityClassification MOD_FREINDLY = EntityClassification.create("ModFriendly", "ModFriendly", 20, true, true, 128);
    public static final EntityClassification MOD_WATER_CREATURE = EntityClassification.create("ModWater", "ModWater", 20, true, true, 128);
    public static final EntityClassification MOD_FREINDLY_CREATURE = EntityClassification.create("ModFriendlyCreature", "ModFriendlyCreature", 10, true, true, 128);
    public static final EntityClassification MOD_FREINDLY_SOLDIER = EntityClassification.create("ModFriendlySoldier", "ModFriendlySoldier", 15, true, true, 128);


    public static final RegistryObject<EntityType<?>> GREEKVILLAGER = register("greekvillager_entity", () -> EntityType.Builder.create(GreekVillagerEntity::new, MOD_FREINDLY).build(new ResourceLocation(GreekMyths.MOD_ID, "greekvillager_entity").toString()));

    public static final RegistryObject<EntityType<?>> GREEKSOLDIER = register("greeksoldier_entity", () -> EntityType.Builder.create(GreekSoldierEntity::new, MOD_FREINDLY_SOLDIER).build(new ResourceLocation(GreekMyths.MOD_ID, "greeksoldier_entity").toString()));

    public static final RegistryObject<EntityType<?>> HIPPOCAMPUS = register("hippocampus_entity", () -> EntityType.Builder.create(HippocampusEntity::new, MOD_WATER_CREATURE).size(1.2f, 1.3f).build(new ResourceLocation(GreekMyths.MOD_ID, "hippocampus_entity").toString()));

    public static final RegistryObject<EntityType<?>> PEGASUS = register("pegasus_entity", () -> EntityType.Builder.create(PegasusEntity::new, MOD_FREINDLY_CREATURE).size(1.36f, 1.6f).build(new ResourceLocation(GreekMyths.MOD_ID, "pegasus_entity").toString()));


//    public static final RegistryObject<Item> GREEK_VILLAGER_SPAWN_EGG = Registration.ITEMS.register("greek_villager_spawn_egg", () ->
//            new SpawnEggItem(GREEKVILLAGER.get(), 0x2f5882, 0x6f1499, new Item.Properties().group(GreekMyths.ITEM_GROUP)));

    public static void register() {
    }

    public static <T extends EntityType<?>> RegistryObject<T> register(String name, Supplier<T> entity) {
        RegistryObject<T> ret = Registration.ENTITIES.register(name, entity);
        return ret;
    }

    @SubscribeEvent
    public static void setupEntityHandler(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            setupEntity((EntityType<MobEntity>) GREEKVILLAGER.get(), GreekVillagerEntity::getAttributes, GreekVillagerEntity::canSpawnOn);
            setupEntity((EntityType<MobEntity>) GREEKSOLDIER.get(), GreekSoldierEntity::getAttributes, GreekSoldierEntity::canSoldierSpawnOn);
            setupEntity((EntityType<MobEntity>) HIPPOCAMPUS.get(), HippocampusEntity::getAttributes, HippocampusEntity::canHippocampusSpawnOn);
            setupEntity((EntityType<MobEntity>) PEGASUS.get(), PegasusEntity::getAttributes, PegasusEntity::canSpawnOn);
        });
    }

    public static <T extends MobEntity> void setupEntity(final EntityType<T> entityType, final Supplier<AttributeModifierMap.MutableAttribute> mapSupplier,
                                                         @Nullable final EntitySpawnPlacementRegistry.IPlacementPredicate<T> placementPredicate) {

        GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) entityType, mapSupplier.get().create());

        final EntitySpawnPlacementRegistry.PlacementType placementType = entityType.getClassification() == MOD_WATER_CREATURE ? EntitySpawnPlacementRegistry.PlacementType.IN_WATER : EntitySpawnPlacementRegistry.PlacementType.ON_GROUND;
        final EntitySpawnPlacementRegistry.IPlacementPredicate<T> placement = (entity, world, reason, pos, rand) -> placementPredicate.test(entity, world, reason, pos, rand);
        EntitySpawnPlacementRegistry.register(entityType, placementType, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, placement);
    }
}
