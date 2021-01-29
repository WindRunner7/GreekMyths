package com.Wind__Runner.GreekMyths.client.renders;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.client.models.GreekVillagerModel;
import com.Wind__Runner.GreekMyths.entities.GreekVillagerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class GreekVillagerEntityRender extends MobRenderer<GreekVillagerEntity, GreekVillagerModel> {

    public GreekVillagerEntityRender(EntityRendererManager rendererManager) {
        super(rendererManager, new GreekVillagerModel(), 0f);
    }

    @Override
    public ResourceLocation getEntityTexture(GreekVillagerEntity entity) {
        return new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greekvillager_entity.png");
    }

    public static class RenderFactory implements IRenderFactory{

        @Override
        public EntityRenderer createRenderFor(EntityRendererManager manager) {
            return new GreekVillagerEntityRender(manager);
        }
    }

}
