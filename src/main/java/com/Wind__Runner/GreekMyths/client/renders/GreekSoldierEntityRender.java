package com.Wind__Runner.GreekMyths.client.renders;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.client.models.GreekSoldierModel;
import com.Wind__Runner.GreekMyths.client.models.GreekVillagerModel;
import com.Wind__Runner.GreekMyths.entities.GreekSoldierEntity;
import com.Wind__Runner.GreekMyths.entities.GreekVillagerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class GreekSoldierEntityRender extends MobRenderer<GreekSoldierEntity, GreekSoldierModel> {

    public GreekSoldierEntityRender(EntityRendererManager rendererManager) {
        super(rendererManager, new GreekSoldierModel(), 0f);
    }

    @Override
    public ResourceLocation getEntityTexture(GreekSoldierEntity entity) {
        return new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greeksoldier_entity.png");
    }

    public static class RenderFactory implements IRenderFactory{

        @Override
        public EntityRenderer createRenderFor(EntityRendererManager manager) {
            return new GreekSoldierEntityRender(manager);
        }
    }

}
