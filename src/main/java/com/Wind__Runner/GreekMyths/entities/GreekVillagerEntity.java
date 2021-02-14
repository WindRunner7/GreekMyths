package com.Wind__Runner.GreekMyths.entities;

import com.Wind__Runner.GreekMyths.init.ModEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.horse.CoatColors;
import net.minecraft.entity.passive.horse.CoatTypes;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import sun.awt.ModalExclude;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Comparator;

public class GreekVillagerEntity extends VillagerEntity {
    private static final DataParameter<Integer> VILLAGER_VARIANT = EntityDataManager.createKey(GreekVillagerEntity.class, DataSerializers.VARINT);
    public GreekVillagerEntity(EntityType<? extends VillagerEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(VILLAGER_VARIANT, 0);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Variant", this.getVillagerVariant());
    }

    private void setVillagerVariant(int p_234242_1_) {
        this.dataManager.set(VILLAGER_VARIANT, p_234242_1_);
    }

    private int getVillagerVariant() {
        return this.dataManager.get(VILLAGER_VARIANT);
    }

    private void setRandomVariant(VillagerVariants villagerVariants) {
        this.setVillagerVariant(villagerVariants.getId() & 255);
    }

    public VillagerVariants func_234239_eK_() {
        return VillagerVariants.func_234254_a_(this.getVillagerVariant() & 255);
    }


    public static AttributeModifierMap.MutableAttribute getAttributes() {
        return registerAttributes();

    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {

        this.setRandomVariant(Util.getRandomObject(VillagerVariants.values(), this.rand));
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public enum VillagerVariants {
        BASIC(0),
        BRAID_BLUE(1),
        BRAID_BLUE_BLONDE(2),
        BRAID_GREEN(3),
        BRAID_SAFFRON(4),
        VEIL(5),
        VEIL_CREAM(6),
        VEIL_CREAM_BLONDE(7),
        VEIL_CREAM_GINGER(8),
        VEIL_RED(9);

        private static final VillagerVariants[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(VillagerVariants::getId)).toArray((p_234255_0_) -> {
            return new VillagerVariants[p_234255_0_];
        });
        private final int id;

        private VillagerVariants(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public static VillagerVariants func_234254_a_(int p_234254_0_) {
            return VALUES[p_234254_0_ % VALUES.length];
        }
    }


}
