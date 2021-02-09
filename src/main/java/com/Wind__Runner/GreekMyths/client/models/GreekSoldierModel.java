package com.Wind__Runner.GreekMyths.client.models;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.entities.GreekSoldierEntity;
import com.Wind__Runner.GreekMyths.entities.GreekVillagerEntity;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GreekMyths.MOD_ID, value = Dist.CLIENT)
public class GreekSoldierModel extends PlayerModel<GreekSoldierEntity> {

    public GreekSoldierModel() {
        super(0, false);
    }
}
