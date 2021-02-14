package com.Wind__Runner.GreekMyths.client.renders;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.client.models.GreekSoldierModel;
import com.Wind__Runner.GreekMyths.client.models.GreekVillagerModel;
import com.Wind__Runner.GreekMyths.entities.GreekSoldierEntity;
import com.Wind__Runner.GreekMyths.entities.GreekVillagerEntity;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import java.util.Map;

public class GreekSoldierEntityRender extends MobRenderer<GreekSoldierEntity, GreekSoldierModel> {

    public GreekSoldierEntityRender(EntityRendererManager rendererManager) {
        super(rendererManager, new GreekSoldierModel(), 0f);
    }

    private static final Map<GreekSoldierEntity.SoldierVariants, ResourceLocation> field_239383_a_ = Util.make(Maps.newEnumMap(GreekSoldierEntity.SoldierVariants.class), (p_239384_0_) -> {
        p_239384_0_.put(GreekSoldierEntity.SoldierVariants.SHORT_HAIR, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greek_soldiers/greeksoldiershorthair_entity.png"));
        p_239384_0_.put(GreekSoldierEntity.SoldierVariants.LONG_HAIR, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greek_soldiers/greeksoldierlonghair_entity.png"));
    });

    @Override
    public ResourceLocation getEntityTexture(GreekSoldierEntity entity) {
        return field_239383_a_.get(entity.func_234239_eK_());
    }
//    @Override
//    public ResourceLocation getEntityTexture(GreekSoldierEntity entity) {
//        return new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greeksoldier_entity.png");
//    }

    public static class RenderFactory implements IRenderFactory{

        @Override
        public EntityRenderer createRenderFor(EntityRendererManager manager) {
            return new GreekSoldierEntityRender(manager);
        }
    }

}
