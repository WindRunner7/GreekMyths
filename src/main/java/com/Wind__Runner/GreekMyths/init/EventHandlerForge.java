package com.Wind__Runner.GreekMyths.init;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.entities.PegasusEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GreekMyths.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlerForge {
//    @SubscribeEvent
//    public static void onLivingFall(final LivingAttackEvent event) {
//        GreekMyths.LOGGER.info("BASSEMFAOUR");
//        if (event.getEntity() instanceof PegasusEntity) {
//            event.setCanceled(true);
//        } else if (event.getEntity() instanceof PlayerEntity) {
//            if(event.getEntity().getRidingEntity() instanceof PegasusEntity){
//                event.setCanceled(true);
//            }
//        }
//    }
}
