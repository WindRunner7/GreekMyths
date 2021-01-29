package com.Wind__Runner.GreekMyths;

import com.Wind__Runner.GreekMyths.client.renders.ModRenderRegistry;
import com.Wind__Runner.GreekMyths.config.Config;
import com.Wind__Runner.GreekMyths.init.ModItems;
import com.Wind__Runner.GreekMyths.init.Registration;
import cpw.mods.modlauncher.EnumerationHelper;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



// The value here should match an entry in the META-INF/mods.toml file
@Mod(GreekMyths.MOD_ID)
public class GreekMyths {


    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "greekmyths";
    public static GreekMyths instance;

    public GreekMyths() {
        instance = this;

        //Config Files
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.serverConfig);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.clientConfig);
        Config.loadConfig(Config.clientConfig, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-client.toml").toString());
        Config.loadConfig(Config.serverConfig, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-server.toml").toString());



        //Register New Items and blocks via Deferred Registration
        Registration.register();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }



    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
//        LOGGER.info("HELLO from server starting");
    }

    public static final ItemGroup ITEM_GROUP = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.GREEK_ARMOR_HELMET.get());
        }
    };

}