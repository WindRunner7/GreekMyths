package com.Wind__Runner.GreekMyths.entities.goals;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityPredicates;

import java.util.EnumSet;
import java.util.List;

public class FollowPlayerGoal extends Goal {

    protected final MobEntity entity;
    protected Entity closestEntity;
    protected final float maxDistance;
    private int followTime;
    protected final float chance;
    protected final Class<? extends LivingEntity> watchedClass;
    protected final EntityPredicate field_220716_e;

    public FollowPlayerGoal(MobEntity entityIn, float maxDistance) {
        this(entityIn, maxDistance, 0.1F);
    }

    public FollowPlayerGoal(MobEntity entityIn, float maxDistance, float chanceIn) {
        this.entity = entityIn;
        this.watchedClass = PlayerEntity.class;
        this.maxDistance = maxDistance;
        this.chance = chanceIn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
        this.field_220716_e = (new EntityPredicate()).setDistance((double) maxDistance).allowFriendlyFire().allowInvulnerable().setSkipAttackChecks().setCustomPredicate((target) -> {
            return EntityPredicates.notRiding(entityIn).test(target);
        });

    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean shouldExecute() {
        if (this.entity.getRNG().nextFloat() >= this.chance) {
            return false;
        } else {
            if (this.entity.getAttackTarget() != null) {
                this.closestEntity = this.entity.getAttackTarget();
            }

            this.closestEntity = this.entity.world.getClosestPlayer(this.field_220716_e, this.entity, this.entity.getPosX(), this.entity.getPosYEye(), this.entity.getPosZ());


            return this.closestEntity != null;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        if (!this.closestEntity.isAlive()) {
            return false;
        } else if (this.entity.getDistanceSq(this.closestEntity) > (double) (this.maxDistance * this.maxDistance)) {
            return false;
        } else {
            return this.followTime > 0;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.followTime = 40 + this.entity.getRNG().nextInt(40);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.closestEntity = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        this.entity.getNavigator().tryMoveToEntityLiving(this.closestEntity, 0.5f);
        --followTime;
    }


}
