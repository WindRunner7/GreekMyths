package com.Wind__Runner.GreekMyths.client.models;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.entities.GreekVillagerEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GreekMyths.MOD_ID, value = Dist.CLIENT)
public class GreekVillagerModel extends PlayerModel<GreekVillagerEntity> {

    public GreekVillagerModel() {
        super(0, true);
    }
}
