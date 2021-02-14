package com.Wind__Runner.GreekMyths.client.renders;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.client.models.GreekVillagerModel;
import com.Wind__Runner.GreekMyths.entities.GreekVillagerEntity;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.CoatColors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import java.util.Map;

public class GreekVillagerEntityRender extends MobRenderer<GreekVillagerEntity, GreekVillagerModel> {

    public GreekVillagerEntityRender(EntityRendererManager rendererManager) {
        super(rendererManager, new GreekVillagerModel(), 0f);
    }

    private static final Map<GreekVillagerEntity.VillagerVariants, ResourceLocation> field_239383_a_ = Util.make(Maps.newEnumMap(GreekVillagerEntity.VillagerVariants.class), (p_239384_0_) -> {
        p_239384_0_.put(GreekVillagerEntity.VillagerVariants.BASIC, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greek_villagers/villagerbasic_entity.png"));
        p_239384_0_.put(GreekVillagerEntity.VillagerVariants.BRAID_BLUE, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greek_villagers/villagerbraidblue_entity.png"));
        p_239384_0_.put(GreekVillagerEntity.VillagerVariants.BRAID_BLUE_BLONDE, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greek_villagers/villagerbraidblueblonde_entity.png"));
        p_239384_0_.put(GreekVillagerEntity.VillagerVariants.BRAID_GREEN, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greek_villagers/villagerbraidgreen_entity.png"));
        p_239384_0_.put(GreekVillagerEntity.VillagerVariants.BRAID_SAFFRON, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greek_villagers/villagerbraidsaffron_entity.png"));
        p_239384_0_.put(GreekVillagerEntity.VillagerVariants.VEIL, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greek_villagers/villagerveil_entity.png"));
        p_239384_0_.put(GreekVillagerEntity.VillagerVariants.VEIL_CREAM, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greek_villagers/villagerveilcream_entity.png"));
        p_239384_0_.put(GreekVillagerEntity.VillagerVariants.VEIL_CREAM_BLONDE, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greek_villagers/villagerveilcreamblonde_entity.png"));
        p_239384_0_.put(GreekVillagerEntity.VillagerVariants.VEIL_CREAM_GINGER, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greek_villagers/villagerveilcreamginger_entity.png"));
        p_239384_0_.put(GreekVillagerEntity.VillagerVariants.VEIL_RED, new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greek_villagers/villagerveilred_entity.png"));
    });

    @Override
    public ResourceLocation getEntityTexture(GreekVillagerEntity entity) {
        return field_239383_a_.get(entity.func_234239_eK_());
//        return new ResourceLocation(GreekMyths.MOD_ID, "textures/entity/greekvillager_entity.png");
    }

    public static class RenderFactory implements IRenderFactory{

        @Override
        public EntityRenderer createRenderFor(EntityRendererManager manager) {
            return new GreekVillagerEntityRender(manager);
        }
    }

}
