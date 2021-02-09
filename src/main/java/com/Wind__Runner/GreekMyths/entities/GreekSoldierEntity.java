package com.Wind__Runner.GreekMyths.entities;

import com.Wind__Runner.GreekMyths.GreekMyths;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;

public class GreekSoldierEntity extends CreatureEntity implements IAngerable {

    private int attackTimer;
    private static final RangedInteger field_234196_bu_ = TickRangeConverter.convertRange(20, 39);
    private int field_234197_bv_;
    private UUID field_234198_bw_;

    public GreekSoldierEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }


    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(2, new ReturnToVillageGoal(this, 0.6D, false));
        this.goalSelector.addGoal(4, new PatrolVillageGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        //this.targetSelector.addGoal(1, new DefendVillageTargetGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::func_233680_b_));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (p_234199_0_) -> {
            return p_234199_0_ instanceof IMob && !(p_234199_0_ instanceof CreeperEntity);
        }));
        this.targetSelector.addGoal(4, new ResetAngerGoal<>(this, false));
    }

    public static boolean canSoldierSpawnOn(final EntityType<? extends MobEntity> entity, final IWorld world,
                                                final SpawnReason reason, final BlockPos pos, final Random rand) {
        GreekMyths.LOGGER.debug("TESTING GREEK SOLDIER");
        return true;
    }

    public static AttributeModifierMap.MutableAttribute getAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 25.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, (double) 0.35F).createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    protected void collideWithEntity(Entity entityIn) {
        if (entityIn instanceof IMob && !(entityIn instanceof CreeperEntity) && this.getRNG().nextInt(20) == 0) {
            this.setAttackTarget((LivingEntity) entityIn);
        }

        super.collideWithEntity(entityIn);
    }

    public boolean canAttack(EntityType<?> typeIn) {
        return typeIn == EntityType.CREEPER ? false : super.canAttack(typeIn);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        this.writeAngerNBT(compound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.readAngerNBT((ServerWorld) this.world, compound);
    }

    public void func_230258_H__() {
        this.setAngerTime(field_234196_bu_.getRandomWithinRange(this.rand));
    }

    public void setAngerTime(int time) {
        this.field_234197_bv_ = time;
    }

    public int getAngerTime() {
        return this.field_234197_bv_;
    }

    public void setAngerTarget(@Nullable UUID target) {
        this.field_234198_bw_ = target;
    }

    public UUID getAngerTarget() {
        return this.field_234198_bw_;
    }

    private float func_226511_et_() {
        return (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        this.attackTimer = 10;
        this.world.setEntityState(this, (byte) 4);
        float f = this.func_226511_et_();
        float f1 = (int) f > 0 ? f / 2.0F + (float) this.rand.nextInt((int) f) : f;
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
        if (flag) {
            entityIn.setMotion(entityIn.getMotion().add(0.0D, (double) 0.4F, 0.0D));
            this.applyEnchantments(this, entityIn);
        }

        this.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, 1.0F, 1.0F);
        return flag;
    }


    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
    }

}
