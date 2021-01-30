package com.Wind__Runner.GreekMyths.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;


public class PegasusEntity extends HorseEntity {

    private static final DataParameter<Byte> FLYINGSTATUS = EntityDataManager.createKey(PegasusEntity.class, DataSerializers.BYTE);

    public PegasusEntity(EntityType<? extends HorseEntity> type, World worldIn) {
        super(type, worldIn);

    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(FLYINGSTATUS, (byte)0);
    }

    protected float prevFlapAmountRearing;
    protected float flapAmountRearing;
    protected float prevFlapAmount;
    protected float flapAmount;


    public static AttributeModifierMap.MutableAttribute getAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.HORSE_JUMP_STRENGTH).createMutableAttribute(Attributes.MAX_HEALTH, 53.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, (double) 0.225F);
    }

    @Override
    public void travel(Vector3d travelVector) {
        if (this.isAlive()) {
            if (this.isBeingRidden() && this.canBeSteered() && this.isHorseSaddled()) {
                LivingEntity livingentity = (LivingEntity) this.getControllingPassenger();
                this.rotationYaw = livingentity.rotationYaw;
                this.prevRotationYaw = this.rotationYaw;
                this.rotationPitch = livingentity.rotationPitch * 0.5F;
                this.setRotation(this.rotationYaw, this.rotationPitch);
                this.renderYawOffset = this.rotationYaw;
                this.rotationYawHead = this.renderYawOffset;
                float f = livingentity.moveStrafing * 0.5F;
                float f1 = livingentity.moveForward;
                if (this.isFlying()) {
                    this.travelFlying(travelVector);
                    if (this.jumpPower > 0.1F && !this.inWater && !this.onGround) {
                        ModifiableAttributeInstance gravity = this.getAttribute(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get());
                        double d0 = gravity.getValue();
                        setFlightBoost(true);
                        Vector3d vectorLook = this.getControllingPassenger().getLookVec();
                        vectorLook = (d0 > 1.0D ? vectorLook.normalize() : vectorLook).scale((double) this.jumpPower);
                        this.setMotion(this.getMotion().add(vectorLook));
                        this.jumpPower = 0;
                    }
                } else if (!this.isFlying()) {
                    if (f1 <= 0.0F) {
                        f1 *= 0.25F;
                        this.gallopTime = 0;
                    }

                    if (this.onGround && this.jumpPower == 0.0F && this.isRearing()) {
                        f = 0.0F;
                        f1 = 0.0F;
                    }
                    if (this.jumpPower > 0.9F && !this.isHorseJumping() && this.onGround && f1 > 0.0F) {
                        double d0 = this.getHorseJumpStrength() * 2 * (double) this.jumpPower * (double) this.getJumpFactor();
                        double d1;
                        if (this.isPotionActive(Effects.JUMP_BOOST)) {
                            d1 = d0 + (double) ((float) (this.getActivePotionEffect(Effects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
                        } else {
                            d1 = d0;
                        }
                        Vector3d vector3d = this.getMotion();
                        this.setMotion(vector3d.x, d1, vector3d.z);
                        //this.setHorseJumping(true);
                        //this.isAirBorne = true;
                        this.setFlying(true);
                        this.jumpPower = 0.0F;
                    } else {
                        if (this.jumpPower > 0.0F && !this.isHorseJumping() && this.onGround) {
                            double d0 = this.getHorseJumpStrength() * 1.4 * (double) this.jumpPower * (double) this.getJumpFactor();
                            double d1;
                            if (this.isPotionActive(Effects.JUMP_BOOST)) {
                                d1 = d0 + (double) ((float) (this.getActivePotionEffect(Effects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
                            } else {
                                d1 = d0;
                            }

                            Vector3d vector3d = this.getMotion();
                            this.setMotion(vector3d.x, d1, vector3d.z);
                            this.setHorseJumping(true);
                            this.isAirBorne = true;
                            net.minecraftforge.common.ForgeHooks.onLivingJump(this);
                            if (f1 > 0.0F) {
                                float f2 = MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F));
                                float f3 = MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F));
                                this.setMotion(this.getMotion().add((double) (-0.4F * f2 * this.jumpPower), 0.0D, (double) (0.4F * f3 * this.jumpPower)));
                            }

                            this.jumpPower = 0.0F;
                        }


                        this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
                        if (this.canPassengerSteer()) {
                            this.setAIMoveSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                            super.travel(new Vector3d((double) f, travelVector.y, (double) f1));
                        } else if (livingentity instanceof PlayerEntity) {
                            this.setMotion(Vector3d.ZERO);
                        }

                        if (this.onGround) {
                            this.jumpPower = 0.0F;
                            this.setHorseJumping(false);
                        }

                        this.func_233629_a_(this, false);
                    }
                }
            } else {
                this.jumpMovementFactor = 0.02F;
                this.setFlying(false);
                super.travel(travelVector);
            }

        }
    }


    private void travelFlying(Vector3d travelVector) {
        if (this.isServerWorld() || this.canPassengerSteer()) {
            ModifiableAttributeInstance gravity = this.getAttribute(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get());
            double d0 = gravity.getValue();
            Vector3d vector3d = this.getMotion();
            if (vector3d.y > -0.5D) {
                this.fallDistance = 1.0F;
            }

            Vector3d vector3d1 = this.getControllingPassenger().getLookVec();
            float f = this.getControllingPassenger().rotationPitch * ((float) Math.PI / 180F);
            double d1 = Math.sqrt(vector3d1.x * vector3d1.x + vector3d1.z * vector3d1.z);
            double d3 = Math.sqrt(horizontalMag(vector3d));
            double d4 = vector3d1.length();
            float f1 = MathHelper.cos(f);
            f1 = (float) ((double) f1 * (double) f1 * Math.min(1.0D, d4 / 0.4D));
            vector3d = this.getMotion().add(0.0D, d0 * (-1.0D + (double) f1 * 0.75D), 0.0D);
            if (vector3d.y < 0.0D && d1 > 0.0D) {
                double d5 = vector3d.y * -0.1D * (double) f1;
                vector3d = vector3d.add(vector3d1.x * d5 / d1, d5, vector3d1.z * d5 / d1);
            }

            if (f < 0.0F && d1 > 0.0D) {
                double d9 = d3 * (double) (-MathHelper.sin(f)) * 0.04D;
                vector3d = vector3d.add(-vector3d1.x * d9 / d1, d9 * 3.2D, -vector3d1.z * d9 / d1);
            }

            if (d1 > 0.0D) {
                vector3d = vector3d.add((vector3d1.x / d1 * d3 - vector3d.x) * 0.1D, 0.0D, (vector3d1.z / d1 * d3 - vector3d.z) * 0.1D);
            }

            this.setMotion(vector3d.mul((double) 0.99F, (double) 0.98F, (double) 0.99F));
            this.move(MoverType.SELF, this.getMotion());
            if (this.collidedHorizontally && !this.world.isRemote) {
                double d10 = Math.sqrt(horizontalMag(this.getMotion()));
                double d6 = d3 - d10;
                float f2 = (float) (d6 * 10.0D - 3.0D);
                if (f2 > 0.0F) {
                    this.playSound(this.getFallSound((int) f2), 1.0F, 1.0F);
                    this.attackEntityFrom(DamageSource.FLY_INTO_WALL, 1f);
                }
            }

            if (this.onGround || this.inWater) {
                this.setFlying(false);
                this.setRearing(false);
            }
        }
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

    public void tick() {
        super.tick();
        if (this.getControllingPassenger() != null) {
            this.getControllingPassenger().fallDistance = 1.0F;
        }
        this.fallDistance = 1.0F;

        this.prevFlapAmountRearing = this.flapAmountRearing;
        this.prevFlapAmount = this.flapAmount;
        if (this.isRearing()) {
            this.flapAmountRearing += 0.05;
            if (this.flapAmountRearing > 1.0F) {
                this.flapAmountRearing = 1.0F;
            }
            //GreekMyths.LOGGER.info(flapAmount);
        }
//        else if (this.isHorseJumping()) {
//            this.flapAmountRearing += 0.05;
//            if (this.flapAmountRearing > 1.0F) {
//                this.flapAmountRearing = 1.0F;
//            }
//        }
        else {
            this.flapAmountRearing = 0;
        }
        if (isFlying()) {
            if (!this.getWingsOut()) {
                setRearing(true);
                if (flapAmountRearing >= 0.85) {
                    this.setWingsOut(true);
                    flapAmountRearing = 0;
                    setRearing(false);
                }
            } else {
                setWatchableBoolean(1, true); //renderFlight = true
                this.flapAmount += 0.05;
                if (this.flapAmount > 0.7F) {
                    this.flapAmount = 0F;
                    if (this.getFlightBoost()&& !this.getFlightBoosted()) {
                        this.setFlightBoosted(true);
                    } else {
                        this.setFlightBoosted(false);
                        this.setFlightBoost(false);
                    }
                }
            }
        } else {
            setWatchableBoolean(1, false); //renderFlight = false;
            if (this.getWingsOut()) {
                if (flapAmountRearing == 0) {
                    this.setRearing(true);
                    this.flapAmountRearing = 0.15f;
                } else if (flapAmountRearing >= 1) {
                    flapAmountRearing = 0;
                    setRearing(false);
                    this.setWingsOut(false);
                }

            } else {
                flapAmount = 0;
            }
        }

    }

    public boolean isFlying() {
        return getWatchableBoolean(2); //
    }

    public void setFlying(boolean isFlying) {
        setWatchableBoolean(2, isFlying); //
    }

    public float getFlapAmountRearing(float p_110223_1_) {
        return MathHelper.lerp(p_110223_1_, this.prevFlapAmountRearing, this.flapAmountRearing);
    }

    public float getFlapAmount(float p_110223_1_) {
        return MathHelper.lerp(p_110223_1_, this.prevFlapAmount, this.flapAmount);
    }


    public boolean getFlightBoost() {
        return getWatchableBoolean(4);
    }

    public void setFlightBoost(boolean boost) {
        setWatchableBoolean(4, boost);
    }

    public boolean getFlightBoosted() {
        return getWatchableBoolean(8);
    }

    public void setFlightBoosted(boolean boosted) {
        setWatchableBoolean(8, boosted);
    }

    public boolean getWingsOut() {
        return getWatchableBoolean(16);
    }

    public void setWingsOut(boolean wing) {
        setWatchableBoolean(16, wing);
    }


    public boolean isRenderFlight() {
        return getWatchableBoolean(1);
    }

    protected boolean getWatchableBoolean(int p_110233_1_) {
        return (this.dataManager.get(FLYINGSTATUS) & p_110233_1_) != 0;
    }

    protected void setWatchableBoolean(int p_110208_1_, boolean p_110208_2_) {
        byte b0 = this.dataManager.get(FLYINGSTATUS);
        if (p_110208_2_) {
            this.dataManager.set(FLYINGSTATUS, (byte)(b0 | p_110208_1_));
        } else {
            this.dataManager.set(FLYINGSTATUS, (byte)(b0 & ~p_110208_1_));
        }

    }

}
