package com.Wind__Runner.GreekMyths.client.renders;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.client.models.HippocampusModel;
import com.Wind__Runner.GreekMyths.entities.HippocampusEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class HippocampusEntityRender extends MobRenderer<HippocampusEntity, HippocampusModel> {

    public HippocampusEntityRender(EntityRendererManager rendererManager) {
        super(rendererManager, new HippocampusModel(), 0f);
    }

    @Override
    public ResourceLocation getEntityTexture(HippocampusEntity entity) {
        return new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/hippocampus_entity.png");
    }

    public static class RenderFactory implements IRenderFactory {

        @Override
        public EntityRenderer createRenderFor(EntityRendererManager manager) {
            return new HippocampusEntityRender(manager);
        }
    }

}
