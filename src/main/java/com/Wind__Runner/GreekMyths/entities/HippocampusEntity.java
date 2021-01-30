package com.Wind__Runner.GreekMyths.entities;

import com.Wind__Runner.GreekMyths.entities.goals.FollowPlayerGoal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class HippocampusEntity extends DolphinEntity implements IRideable, IEquipable, IJumpingMount {

    protected float jumpPower;
    protected boolean horseJumping;
    private static final DataParameter<Boolean> SADDLED = EntityDataManager.createKey(PigEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.createKey(PigEntity.class, DataSerializers.VARINT);
    private final BoostHelper boostHelper = new BoostHelper(this.dataManager, BOOST_TIME, SADDLED);

    public HippocampusEntity(EntityType<? extends DolphinEntity> type, World worldIN) {
        super(type, worldIN);

    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return this.isInWater() ? SoundEvents.ENTITY_SKELETON_HORSE_AMBIENT_WATER : SoundEvents.ENTITY_HORSE_AMBIENT;
    }

    public static boolean canHippocampusSpawnOn(final EntityType<? extends MobEntity> entity, final IWorld world,
                                     final SpawnReason reason, final BlockPos pos, final Random rand) {
        if (pos.getY() <= 25 || pos.getY() >= world.getSeaLevel()) {
            return false;
        }

        RegistryKey<Biome> biome = world.func_242406_i(pos).orElse(Biomes.PLAINS);
        return (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN)) && world.getFluidState(pos).isTagged(FluidTags.WATER);
    }


    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.95F;
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(SADDLED, false);
        this.dataManager.register(BOOST_TIME, 0);
    }

    public static AttributeModifierMap.MutableAttribute getAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 40.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, (double) 1.2F).createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FindWaterGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(5, new DolphinJumpGoal(this, 10));
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, (double) 1.2F, true));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.goalSelector.addGoal(5, new FollowPlayerGoal(this, 6.0F));
        this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, GuardianEntity.class, 8.0F, 1.0D, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, GuardianEntity.class)).setCallsForHelp());
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected boolean canBeRidden(Entity entityIn) {
        return true;
    }

    @Override
    public boolean canBeRiddenInWater() {
        return true;
    }

    public ActionResultType func_230254_b_(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        if (this.isHorseSaddled() && !this.isBeingRidden() && !p_230254_1_.isSecondaryUseActive()) {
            if (!this.world.isRemote) {
                p_230254_1_.startRiding(this);
            }

            return ActionResultType.func_233537_a_(this.world.isRemote);
        } else {
            ActionResultType actionresulttype = super.func_230254_b_(p_230254_1_, p_230254_2_);
            if (!actionresulttype.isSuccessOrConsume()) {
                ItemStack itemstack = p_230254_1_.getHeldItem(p_230254_2_);
                return itemstack.getItem() == Items.SADDLE ? itemstack.interactWithEntity(p_230254_1_, this, p_230254_2_) : ActionResultType.PASS;
            } else {
                return actionresulttype;
            }
        }
    }

    public boolean canBeSteered() {
        return true;
    }

    @Override
    public boolean func_230264_L__() {
        return this.isAlive();
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        this.boostHelper.setSaddledToNBT(compound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.boostHelper.setSaddledFromNBT(compound);
    }

    @Override
    public void func_230266_a_(@Nullable SoundCategory p_230266_1_) {
        this.boostHelper.setSaddledFromBoolean(true);
        if (p_230266_1_ != null) {
            this.world.playMovingSound((PlayerEntity) null, this, SoundEvents.ENTITY_HORSE_SADDLE, p_230266_1_, 0.5F, 1.0F);
        }
    }

    @Override
    public boolean isHorseSaddled() {
        return this.boostHelper.getSaddled();
    }

    @Override
    public boolean boost() {
        return this.boostHelper.boost(this.getRNG());
    }

    @Override
    public void travelTowards(Vector3d travelVec) {
        super.travel(travelVec);
    }

    @Override
    public float getMountedSpeed() {
        return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 1F;
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        if (BOOST_TIME.equals(key) && this.world.isRemote) {
            this.boostHelper.updateData();
        }

        super.notifyDataManagerChange(key);
    }

    protected void dropInventory() {
        super.dropInventory();
        if (this.isHorseSaddled()) {
            this.entityDropItem(Items.SADDLE);
        }
    }

    @Override
    public boolean canPassengerSteer() {
        Entity entity = this.getControllingPassenger();
        if (entity instanceof PlayerEntity) {
            return ((PlayerEntity) entity).isUser();
        } else {
            return !this.world.isRemote;
        }
    }

    @Nullable
    @Override
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    @Override
    public void travel(Vector3d travelVector) {
        if (this.isAlive()) {
            if (this.isBeingRidden() && this.canBeSteered() && this.isHorseSaddled()) {
                LivingEntity livingentity = (LivingEntity) this.getControllingPassenger();
                if (livingentity != null) {
                    this.rotationYaw = livingentity.rotationYaw;
                    this.prevRotationYaw = this.rotationYaw;
                    this.rotationPitch = livingentity.rotationPitch;
                    this.setRotation(this.rotationYaw, this.rotationPitch);
                    this.renderYawOffset = this.rotationYaw;
                    this.rotationYawHead = this.renderYawOffset;
                    float side = livingentity.moveStrafing * 0.5F;
                    float forward = livingentity.moveForward;
                    if (forward <= 0.0F) {
                        forward *= 0.25F;
                    }


                    if (this.jumpPower > 0.0F && !this.isHorseJumping() && this.inWater) {
                        double d0 = (double) this.jumpPower * (double) this.getJumpFactor();
                        double d1;
                        if (this.isPotionActive(Effects.JUMP_BOOST)) {
                            d1 = d0 + (double) ((float) (this.getActivePotionEffect(Effects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
                        } else {
                            d1 = d0;
                        }

                        Vector3d vector3d = this.getMotion();
                        this.setMotion(vector3d.x, vector3d.y + d1, vector3d.z);
                        this.setHorseJumping(true);
                        net.minecraftforge.common.ForgeHooks.onLivingJump(this);
                        if (forward > 0.0F) {
                            float f2 = MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F));
                            float f3 = MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F));
                            this.setMotion(this.getMotion().add((double) (-0.4F * f2 * this.jumpPower), 0.0D, (double) (0.4F * f3 * this.jumpPower)));
                        }

                        this.jumpPower = 0.0F;
                    }

                    this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
                    if (this.canPassengerSteer()) {
                        this.setAIMoveSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                        this.travelRiding(new Vector3d((double) side, travelVector.y, (double) forward));

                    } else if (livingentity instanceof PlayerEntity) {
                        this.setMotion(Vector3d.ZERO);
                    }

                    if (this.inWater) {
                        this.jumpPower = 0.0F;
                        this.setHorseJumping(false);
                    }
                }
                this.func_233629_a_(this, false);

            } else {
                this.jumpMovementFactor = 0.02F;
                super.travel(travelVector);
            }
        }
    }

    @Override
    protected float getWaterSlowDown() {
        return 0.9f;
    }

    private void travelRiding(Vector3d travelVector) {
        if (this.isServerWorld() || this.canPassengerSteer()) {

            ModifiableAttributeInstance gravity = this.getAttribute(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get());
            double d0 = gravity.getValue();

            boolean flag = this.getMotion().y <= 0.0D;
            if (flag && this.isPotionActive(Effects.SLOW_FALLING)) {
                this.fallDistance = 0.0F;
            }

            if (this.isInWater()) {
                double d8 = this.getPosY();
                float f5 = this.isSprinting() ? 0.98F : this.getWaterSlowDown();
                float f6 = 0.02F;

                f6 *= (float) this.getAttribute(net.minecraftforge.common.ForgeMod.SWIM_SPEED.get()).getValue();
                this.moveRelative(f6, travelVector);
                this.move(MoverType.SELF, this.getMotion());
                Vector3d vector3d6 = this.getMotion();
                if (this.collidedHorizontally && this.isOnLadder()) {
                    vector3d6 = new Vector3d(vector3d6.x, 0.2D, vector3d6.z);
                }

                this.setMotion(vector3d6.mul((double) f5, (double) 0.8F, (double) f5));

                Vector3d vectorLook = this.getControllingPassenger().getLookVec();
                vectorLook = new Vector3d(vectorLook.x * travelVector.z, vectorLook.y * travelVector.z, vectorLook.z * travelVector.z);
                travelVector = new Vector3d(travelVector.x, 0, 0);
                this.moveRelative(f6, travelVector);
                this.move(MoverType.SELF, this.getMotion());
                double l = vectorLook.lengthSquared();
                vectorLook = (d0 > 1.0D ? vectorLook.normalize() : vectorLook).scale((double) f6);
                this.setMotion(this.getMotion().add(vectorLook));
                Vector3d vector3d2 = this.func_233626_a_(d0, flag, this.getMotion());
                this.setMotion(vector3d2);

            } else {
                BlockPos blockpos = this.getPositionUnderneath();
                float f3 = this.world.getBlockState(this.getPositionUnderneath()).getSlipperiness(world, this.getPositionUnderneath(), this);
                float f4 = this.onGround ? f3 * 0.91F : 0.91F;
                Vector3d vector3d5 = this.func_233633_a_(travelVector, f3);
                double d2 = vector3d5.y;
                if (this.isPotionActive(Effects.LEVITATION)) {
                    d2 += (0.05D * (double) (this.getActivePotionEffect(Effects.LEVITATION).getAmplifier() + 1) - vector3d5.y) * 0.2D;
                    this.fallDistance = 0.0F;
                } else if (this.world.isRemote && !this.world.isBlockLoaded(blockpos)) {
                    if (this.getPosY() > 0.0D) {
                        d2 = -0.1D;
                    } else {
                        d2 = 0.0D;
                    }
                } else if (!this.hasNoGravity()) {
                    d2 -= d0;
                }

                this.setMotion(vector3d5.x * (double) f4, d2 * (double) 0.98F, vector3d5.z * (double) f4);
            }
        }

        this.func_233629_a_(this, this instanceof IFlyingAnimal);
    }

    public boolean isHorseJumping() {
        return this.horseJumping;
    }

    public void setHorseJumping(boolean jumping) {
        this.horseJumping = jumping;
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public void setJumpPower(int jumpPowerIn) {
        if (this.isHorseSaddled()) {
            if (jumpPowerIn < 0) {
                jumpPowerIn = 0;
            }
            if (jumpPowerIn >= 90) {
                this.jumpPower = 1.0F;
            } else {
                this.jumpPower = 0.4F + 0.4F * (float) jumpPowerIn / 90.0F;
            }
        }
    }

    @Override
    public boolean canJump() {
        return this.isHorseSaddled();
    }

    @Override
    public void handleStartJump(int jumpPower) {
        //this.playJumpSound();
    }

    @Override
    public void handleStopJump() {
    }
}