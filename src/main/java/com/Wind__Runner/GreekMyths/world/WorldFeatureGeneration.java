package com.Wind__Runner.GreekMyths.world;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.config.OreGenConfig;
import com.Wind__Runner.GreekMyths.init.ModBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GreekMyths.MOD_ID)
public final class WorldFeatureGeneration {

    public static final class ModConfiguredFeatures {
        public static final ConfiguredFeature<?, ?> ORE_VEINS_SILVER = Feature.ORE.withConfiguration(
                new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                        ModBlocks.SILVER_ORE.get().getDefaultState(), 9)).range(64).square().func_242731_b(OreGenConfig.silver_ore_chance.get());

        public static final ConfiguredFeature<?, ?> ORE_VEINS_COPPER = Feature.ORE.withConfiguration(
                new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                        ModBlocks.COPPER_ORE.get().getDefaultState(), 9)).range(64).square().func_242731_b(OreGenConfig.copper_ore_chance.get());
    }

    private static void registerConfiguredFeature(String name, ConfiguredFeature<?, ?> configuredFeature) {
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(GreekMyths.MOD_ID, name), configuredFeature);
    }

    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        registerConfiguredFeature("silver_ore_veins", ModConfiguredFeatures.ORE_VEINS_SILVER);
    }

    @SubscribeEvent
    public static void addFeaturesToBiomes(BiomeLoadingEvent biome) {

        if (biome.getCategory() == Biome.Category.NETHER) {

        } else if (biome.getCategory() == Biome.Category.THEEND) {

        } else {
            addOverWorldOres(biome);
        }
    }

    private static void addOverWorldOres(BiomeLoadingEvent biome) {
        biome.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.ORE_VEINS_SILVER);
        biome.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.ORE_VEINS_COPPER);
    }




}
