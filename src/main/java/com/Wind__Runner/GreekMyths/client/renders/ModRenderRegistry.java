package com.Wind__Runner.GreekMyths.client.renders;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.entities.GreekVillagerEntity;
import com.Wind__Runner.GreekMyths.init.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = GreekMyths.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ModRenderRegistry {

    @SubscribeEvent
    public static void registryEntityRenders(final FMLClientSetupEvent event){
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GREEKVILLAGER.get(), new GreekVillagerEntityRender.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HIPPOCAMPUS.get(), new HippocampusEntityRender.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.PEGASUS.get(), new PegasusEntityRender.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GREEKSOLDIER.get(), new GreekSoldierEntityRender.RenderFactory());
    }
}
