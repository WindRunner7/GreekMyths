package com.Wind__Runner.GreekMyths.client.renders;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.client.models.HippocampusModel;
import com.Wind__Runner.GreekMyths.client.models.PegasusModel;
import com.Wind__Runner.GreekMyths.entities.HippocampusEntity;
import com.Wind__Runner.GreekMyths.entities.PegasusEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.HorseMarkingsLayer;
import net.minecraft.client.renderer.entity.layers.LeatherHorseArmorLayer;
import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.passive.horse.CoatColors;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import java.util.Map;

public class PegasusEntityRender <T extends PegasusEntity, M extends PegasusModel<T>> extends MobRenderer<PegasusEntity, PegasusModel<PegasusEntity>> {

    private static final Map<CoatColors, ResourceLocation> field_239383_a_ = Util.make(Maps.newEnumMap(CoatColors.class), (p_239384_0_) -> {
        p_239384_0_.put(CoatColors.WHITE, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/pegasus_entity.png"));
        p_239384_0_.put(CoatColors.CREAMY, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/pegasus_entity.png"));
        p_239384_0_.put(CoatColors.CHESTNUT, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/pegasus_entity.png"));
        p_239384_0_.put(CoatColors.BROWN, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/pegasus_entity.png"));
        p_239384_0_.put(CoatColors.BLACK, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/pegasus_entity.png"));
        p_239384_0_.put(CoatColors.GRAY, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/pegasus_entity.png"));
        p_239384_0_.put(CoatColors.DARKBROWN, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/pegasus_entity.png"));
    });

    private float scale;
    public PegasusEntityRender(EntityRendererManager renderManagerIn, M peg, float scaleIn) {
        super(renderManagerIn, (PegasusModel<PegasusEntity>) peg, 0.75F);
        this.scale = scaleIn;
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(HorseEntity entity) {
        return field_239383_a_.get(entity.func_234239_eK_());
    }

    @Override
    public ResourceLocation getEntityTexture(PegasusEntity entity) {
        return new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/pegasus_entity.png");
    }

    public static class RenderFactory implements IRenderFactory {

        @Override
        public EntityRenderer createRenderFor(EntityRendererManager manager) {
            return new PegasusEntityRender(manager, new PegasusModel(0), 1.1f);
        }
    }

}
