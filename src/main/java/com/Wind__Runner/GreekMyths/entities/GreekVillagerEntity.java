package com.Wind__Runner.GreekMyths.entities;

import com.Wind__Runner.GreekMyths.init.ModEntities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.world.World;
import sun.awt.ModalExclude;

public class GreekVillagerEntity extends VillagerEntity {

    public GreekVillagerEntity(EntityType<? extends VillagerEntity> type, World worldIn) {
        super(type, worldIn);
    }

//    @Override
//    protected void registerGoals() {
//        super.registerGoals();
//    }

    public static AttributeModifierMap.MutableAttribute getAttributes() {
        return registerAttributes();
//        return MobEntity.func_233666_p_()
//                .createMutableAttribute(Attributes.MAX_HEALTH, 24.0D)
//                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5D)
//                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D);
    }

}
