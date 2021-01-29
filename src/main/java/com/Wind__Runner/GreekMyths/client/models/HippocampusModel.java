package com.Wind__Runner.GreekMyths.client.models;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.Wind__Runner.GreekMyths.entities.HippocampusEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

/**
 * Hippocampus - Wind__Runner
 * Created using Tabula 8.0.0
 */
@Mod.EventBusSubscriber(modid = GreekMyths.MOD_ID, value = Dist.CLIENT)
public class HippocampusModel extends EntityModel<HippocampusEntity> {
    public ModelRenderer Neck;
    public ModelRenderer HorseBody;
    public ModelRenderer LeftFrontFin;
    public ModelRenderer RigthFrontFin;
    public ModelRenderer DolphinBody;
    public ModelRenderer Face;
    public ModelRenderer Mane;
    public ModelRenderer nose;
    public ModelRenderer bridelL;
    public ModelRenderer bridelR;
    public ModelRenderer bridelmain;
    public ModelRenderer bridelnose;
    public ModelRenderer Crown;
    public ModelRenderer saddle;
    public ModelRenderer TopFin;
    public ModelRenderer TopFin_1;
    public ModelRenderer LeftFin;
    public ModelRenderer RightFin;
    public ModelRenderer Tail;
    public ModelRenderer TailFin;
    public ModelRenderer[] bridel;

    public HippocampusModel() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.LeftFin = new ModelRenderer(this, 110, 44);
        this.LeftFin.mirror = true;
        this.LeftFin.setRotationPoint(2.0F, -2.0F, 4.0F);
        this.LeftFin.addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(LeftFin, 1.0471975511965976F, 0.0F, 2.0943951023931953F);
        this.saddle = new ModelRenderer(this, 26, 0);
        this.saddle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.saddle.addBox(-5.0F, -6.0F, -9.0F, 10.0F, 9.0F, 9.0F, 0.5F, 0.5F, 0.5F);
        this.bridelR = new ModelRenderer(this, 32, 2);
        this.bridelR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bridelR.addBox(-3.1F, -4.0F, -8.0F, 0.0F, 3.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(bridelR, -0.5235987755982988F, 0.0F, 0.0F);
        this.TopFin = new ModelRenderer(this, 100, 0);
        this.TopFin.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.TopFin.addBox(-0.5F, -3.0F, 6.0F, 1.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(TopFin, 1.0471975511965976F, 0.0F, 0.0F);
        this.RigthFrontFin = new ModelRenderer(this, 48, 21);
        this.RigthFrontFin.setRotationPoint(-4.0F, 14.0F, -10.0F);
        this.RigthFrontFin.addBox(-2.0F, 0.1F, -2.2F, 2.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(RigthFrontFin, -0.2617993877991494F, -0.19198621771937624F, 0.7853981633974483F);
        this.Neck = new ModelRenderer(this, 0, 35);
        this.Neck.setRotationPoint(0.0F, 4.0F, -12.0F);
        this.Neck.addBox(-2.05F, -4.0F, -2.0F, 4.0F, 12.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Neck, 0.5235987755982988F, 0.0F, 0.0F);
        this.Mane = new ModelRenderer(this, 80, 37);
        this.Mane.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mane.addBox(-0.5F, -7.5F, 3.01F, 1.0F, 16.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Mane, 0.11728612207217244F, 0.0F, 0.0F);
        this.Crown = new ModelRenderer(this, 50, 35);
        this.Crown.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Crown.addBox(-0.5F, -12.0F, -0.7F, 1.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Crown, -0.3127630032889644F, 0.0F, 0.0F);
        this.HorseBody = new ModelRenderer(this, 0, 32);
        this.HorseBody.setRotationPoint(0.0F, 11.0F, 5.0F);
        this.HorseBody.addBox(-5.0F, -6.0F, -17.0F, 10.0F, 10.0F, 22.0F, 0.05000019F, 0.05000019F, 0.049999237F);
        this.nose = new ModelRenderer(this, 0, 25);
        this.nose.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.nose.addBox(-2.0F, -9.0F, -7.0F, 4.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.TopFin_1 = new ModelRenderer(this, 100, 0);
        this.TopFin_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.TopFin_1.addBox(-0.5F, 2.0F, 8.0F, 1.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(TopFin_1, 1.0471975511965976F, 0.0F, 0.0F);
        this.DolphinBody = new ModelRenderer(this, 64, 0);
        this.DolphinBody.setRotationPoint(0.0F, 11.0F, 8.0F);
        this.DolphinBody.addBox(-4.0F, -5.0F, 0.0F, 8.0F, 7.0F, 13.0F, 0.0F, 0.0F, 0.0F);
        this.bridelnose = new ModelRenderer(this, 19, 0);
        this.bridelnose.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bridelnose.addBox(-2.0F, -9.0F, -4.0F, 4.0F, 5.0F, 2.0F, 0.20000005F, 0.19999981F, 0.19999993F);
        this.RightFin = new ModelRenderer(this, 110, 44);
        this.RightFin.setRotationPoint(-2.0F, -2.0F, 4.0F);
        this.RightFin.addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(RightFin, 1.0471975511965976F, 0.0F, -2.0943951023931953F);
        this.TailFin = new ModelRenderer(this, 83, 20);
        this.TailFin.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.TailFin.addBox(-5.0F, 1.5F, 0.0F, 10.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.bridelmain = new ModelRenderer(this, 1, 1);
        this.bridelmain.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bridelmain.addBox(-3.0F, -9.0F, -1.9F, 6.0F, 5.0F, 6.0F, 0.20000005F, 0.19999981F, 0.19999981F);
        this.LeftFrontFin = new ModelRenderer(this, 48, 21);
        this.LeftFrontFin.mirror = true;
        this.LeftFrontFin.setRotationPoint(4.0F, 14.0F, -10.0F);
        this.LeftFrontFin.addBox(0.0F, 0.1F, -2.2F, 2.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(LeftFrontFin, -0.2617993877991494F, 0.19198621771937624F, -0.7853981633974483F);
        this.Tail = new ModelRenderer(this, 63, 20);
        this.Tail.setRotationPoint(0.0F, -2.5F, 11.0F);
        this.Tail.addBox(-2.0F, -0.5F, 0.0F, 4.0F, 5.0F, 11.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Tail, -0.10471975511965977F, 0.0F, 0.0F);
        this.bridelL = new ModelRenderer(this, 32, 2);
        this.bridelL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bridelL.addBox(3.1F, -4.0F, -8.0F, 0.0F, 3.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(bridelL, -0.5235987755982988F, 0.0F, 0.0F);
        this.Face = new ModelRenderer(this, 0, 13);
        this.Face.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Face.addBox(-3.0F, -9.0F, -2.0F, 6.0F, 5.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.DolphinBody.addChild(this.LeftFin);
        this.HorseBody.addChild(this.saddle);
        this.Neck.addChild(this.bridelR);
        this.HorseBody.addChild(this.TopFin);
        this.Neck.addChild(this.Mane);
        this.Face.addChild(this.Crown);
        this.Neck.addChild(this.nose);
        this.DolphinBody.addChild(this.TopFin_1);
        this.Neck.addChild(this.bridelnose);
        this.DolphinBody.addChild(this.RightFin);
        this.Tail.addChild(this.TailFin);
        this.Neck.addChild(this.bridelmain);
        this.DolphinBody.addChild(this.Tail);
        this.Neck.addChild(this.bridelL);
        this.Neck.addChild(this.Face);
        this.bridel = new ModelRenderer[]{bridelL, bridelmain, bridelnose, bridelR};
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.RigthFrontFin, this.Neck, this.HorseBody, this.DolphinBody, this.LeftFrontFin).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(HippocampusEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean saddeled = entityIn.isHorseSaddled();
        boolean ridden = entityIn.isBeingRidden();
        saddle.showModel = saddeled;

        for(ModelRenderer modelrenderer : this.bridel) {
            modelrenderer.showModel = saddeled && ridden;
        }

        if (Entity.horizontalMag(entityIn.getMotion()) > 1.0E-7D) {
            this.DolphinBody.rotateAngleX = -0.1F * MathHelper.cos(ageInTicks * 0.3F);
            this.Tail.rotateAngleX = -0.1F * MathHelper.cos(ageInTicks * 0.3F);
            this.TailFin.rotateAngleX = -0.2F * MathHelper.cos(ageInTicks * 0.3F);
            this.RigthFrontFin.rotateAngleY = -0.1F * MathHelper.cos(ageInTicks * 0.3F);
            this.LeftFrontFin.rotateAngleY = +0.1F * MathHelper.cos(ageInTicks * 0.3F);
            //this.LeftFrontFin.rotateAngleY;
            //this.Neck.rotateAngleX = 0.2F + -0.2F * MathHelper.cos(ageInTicks * 0.3F);
        }

    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

