package com.obsidian_core.archaic_quest.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.obsidian_core.archaic_quest.common.entity.living.Tlatlaomi;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class TlatlaomiModel extends EntityModel<Tlatlaomi> {

    private final ModelPart tlatlaomi;
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart jaw;


    public TlatlaomiModel(ModelPart root) {
        final ModelPart main = root.getChild("tlatlaomi");

        tlatlaomi = main;
        head = main.getChild("head");
        rightArm = main.getChild("rArm01");
        leftArm = main.getChild("lArm01");
        rightLeg = main.getChild("lowerRibs").getChild("rLeg01");
        leftLeg = main.getChild("lowerRibs").getChild("lLeg01");
        jaw = head.getChild("lowerJaw");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition tlatlaomi = partdefinition.addOrReplaceChild("tlatlaomi", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -2.0F, 0.0F, 10.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(28, 13).addBox(-1.0F, -2.5F, 4.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 54).addBox(-5.5F, -2.25F, -0.5F, 11.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -3.0F));

        PartDefinition innerTendons02_r1 = tlatlaomi.addOrReplaceChild("innerTendons02_r1", CubeListBuilder.create().texOffs(112, 24).addBox(0.25F, -2.0F, -5.0F, 0.0F, 13.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, -1.0908F, 0.0F));

        PartDefinition innerTendons01_r1 = tlatlaomi.addOrReplaceChild("innerTendons01_r1", CubeListBuilder.create().texOffs(97, 24).addBox(-0.25F, -2.0F, -5.0F, 0.0F, 13.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 1.0908F, 0.0F));

        PartDefinition cape = tlatlaomi.addOrReplaceChild("cape", CubeListBuilder.create().texOffs(104, 44).addBox(-5.0F, -1.0F, -0.75F, 10.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 5.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition capeLower = cape.addOrReplaceChild("capeLower", CubeListBuilder.create().texOffs(105, 62).addBox(-5.0F, 0.0F, -0.75F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition lowerRibs = tlatlaomi.addOrReplaceChild("lowerRibs", CubeListBuilder.create().texOffs(1, 13).addBox(-4.5F, 0.0F, -3.0F, 9.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(29, 24).addBox(-1.0F, -0.5F, 1.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 6.0F, 4.0F));

        PartDefinition belt = lowerRibs.addOrReplaceChild("belt", CubeListBuilder.create().texOffs(0, 25).addBox(-4.5F, -2.0F, -3.5F, 9.0F, 3.0F, 5.0F, new CubeDeformation(0.02F)), PartPose.offset(0.0F, 6.0F, 0.0F));

        PartDefinition loinclothFront = belt.addOrReplaceChild("loinclothFront", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -3.0F));

        PartDefinition cube_r1 = loinclothFront.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(18, 35).addBox(-4.0F, -1.0F, -0.75F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition loinclothBack = belt.addOrReplaceChild("loinclothBack", CubeListBuilder.create().texOffs(19, 44).addBox(-4.0F, 0.0F, 0.5F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition lLeg01 = lowerRibs.addOrReplaceChild("lLeg01", CubeListBuilder.create().texOffs(0, 35).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.25F, 6.0F, -1.0F));

        PartDefinition lLeg02 = lLeg01.addOrReplaceChild("lLeg02", CubeListBuilder.create().texOffs(0, 49).addBox(-1.5F, -0.01F, -0.25F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, -1.75F));

        PartDefinition rLeg01 = lowerRibs.addOrReplaceChild("rLeg01", CubeListBuilder.create().texOffs(0, 35).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.25F, 6.0F, -1.0F));

        PartDefinition rLeg02 = rLeg01.addOrReplaceChild("rLeg02", CubeListBuilder.create().texOffs(0, 49).mirror().addBox(-1.5F, -0.01F, -0.25F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 9.0F, -1.75F));

        PartDefinition head = tlatlaomi.addOrReplaceChild("head", CubeListBuilder.create().texOffs(38, 0).addBox(-3.5F, -7.8644F, -6.4652F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(56, 15).addBox(-2.0F, -1.8644F, -6.4652F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.01F))
                .texOffs(38, 13).addBox(-4.0F, -6.8644F, -6.7152F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -2.25F, 4.0F, -0.0436F, 0.0F, 0.0F));

        PartDefinition headpiece = head.addOrReplaceChild("headpiece", CubeListBuilder.create().texOffs(69, 0).addBox(-4.5F, -1.5F, -3.75F, 9.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(69, 12).addBox(-2.5F, -3.0F, -3.0F, 5.0F, 9.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(96, 0).addBox(-2.0F, -6.0F, -3.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(97, 16).addBox(-2.0F, -3.0F, 5.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(56, 19).addBox(-1.5F, -3.0F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.8644F, -3.2152F));

        PartDefinition lFeatherBundle = headpiece.addOrReplaceChild("lFeatherBundle", CubeListBuilder.create(), PartPose.offsetAndRotation(2.5F, -2.0F, 3.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition lFeather01 = lFeatherBundle.addOrReplaceChild("lFeather01", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 1.0F, 0.0F, -0.8658F, 0.1329F, 0.1124F));

        PartDefinition cube_r2 = lFeather01.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 23).addBox(0.0F, -13.0F, -2.0F, 0.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.5672F, 0.0F));

        PartDefinition lFeather02 = lFeatherBundle.addOrReplaceChild("lFeather02", CubeListBuilder.create(), PartPose.offsetAndRotation(0.25F, 1.0F, -1.0F, -0.5178F, 0.0735F, 0.1585F));

        PartDefinition cube_r3 = lFeather02.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(47, 23).addBox(0.0F, -13.0F, -2.0F, 0.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.48F, 0.0F));

        PartDefinition lFeather03 = lFeatherBundle.addOrReplaceChild("lFeather03", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, 1.0F, -2.0F, -0.1289F, 0.0227F, 0.1731F));

        PartDefinition cube_r4 = lFeather03.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(38, 23).addBox(0.0F, -13.0F, -2.0F, 0.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.5672F, 0.0F));

        PartDefinition lFeather04 = lFeatherBundle.addOrReplaceChild("lFeather04", CubeListBuilder.create(), PartPose.offsetAndRotation(0.25F, 2.0F, 2.0F, 0.3079F, 0.1248F, 0.0396F));

        PartDefinition cube_r5 = lFeather04.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(41, 27).addBox(1.0F, -3.0F, -2.0F, 0.0F, 5.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition rFeatherBundle = headpiece.addOrReplaceChild("rFeatherBundle", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, -2.0F, 3.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition rFeather01 = rFeatherBundle.addOrReplaceChild("rFeather01", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 1.0F, 0.0F, -0.8658F, -0.1329F, -0.1124F));

        PartDefinition cube_r6 = rFeather01.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(56, 23).mirror().addBox(0.0F, -13.0F, -2.0F, 0.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5672F, 0.0F));

        PartDefinition rFeather02 = rFeatherBundle.addOrReplaceChild("rFeather02", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.25F, 1.0F, -1.0F, -0.5178F, -0.0735F, -0.1585F));

        PartDefinition cube_r7 = rFeather02.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(47, 23).mirror().addBox(0.0F, -13.0F, -2.0F, 0.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.48F, 0.0F));

        PartDefinition rFeather03 = rFeatherBundle.addOrReplaceChild("rFeather03", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, 1.0F, -2.0F, -0.1289F, -0.0227F, -0.1731F));

        PartDefinition cube_r8 = rFeather03.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(38, 23).mirror().addBox(0.0F, -13.0F, -2.0F, 0.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5672F, 0.0F));

        PartDefinition rFeather04 = rFeatherBundle.addOrReplaceChild("rFeather04", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.25F, 2.0F, 2.0F, 0.3079F, -0.1248F, -0.0396F));

        PartDefinition cube_r9 = rFeather04.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(41, 27).mirror().addBox(-1.0F, -3.0F, -2.0F, 0.0F, 5.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition headdresstassle = headpiece.addOrReplaceChild("headdresstassle", CubeListBuilder.create().texOffs(0, 66).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 8.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 7.0F, -0.9599F, 0.0F, 0.0F));

        PartDefinition tassleFeather01 = headdresstassle.addOrReplaceChild("tassleFeather01", CubeListBuilder.create().texOffs(0, 75).addBox(0.0F, -1.0F, 0.0F, 0.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 11.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition tassleFeather02 = headdresstassle.addOrReplaceChild("tassleFeather02", CubeListBuilder.create().texOffs(0, 80).addBox(0.25F, -1.0F, 0.0F, 0.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 11.0F, -0.0436F, 0.0F, 0.0F));

        PartDefinition tassleFeather03 = headdresstassle.addOrReplaceChild("tassleFeather03", CubeListBuilder.create().texOffs(0, 85).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5F, 11.0F, -0.3491F, 0.0F, 0.0F));

        PartDefinition tassleFeather2 = headdresstassle.addOrReplaceChild("tassleFeather2", CubeListBuilder.create().texOffs(0, 75).addBox(-0.25F, -1.0F, 0.0F, 0.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 11.0F, -0.48F, 0.0F, 0.0F));

        PartDefinition headdress_tail = headpiece.addOrReplaceChild("headdress_tail", CubeListBuilder.create().texOffs(70, 30).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, 4.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition lowerJaw = head.addOrReplaceChild("lowerJaw", CubeListBuilder.create().texOffs(38, 19).addBox(-3.0F, -0.1736F, -5.9848F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.8644F, -0.2152F, 0.1745F, 0.0F, 0.0F));

        PartDefinition lArm01 = tlatlaomi.addOrReplaceChild("lArm01", CubeListBuilder.create().texOffs(40, 45).addBox(0.0F, -2.0F, -1.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.0436F));

        PartDefinition lArm02 = lArm01.addOrReplaceChild("lArm02", CubeListBuilder.create().texOffs(55, 46).addBox(-1.5F, -0.01F, -1.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(38, 58).mirror().addBox(-2.0F, 5.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, 7.0F, 1.0F));

        PartDefinition rArm01 = tlatlaomi.addOrReplaceChild("rArm01", CubeListBuilder.create().texOffs(40, 45).mirror().addBox(-3.0F, -2.0F, -1.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.0436F));

        PartDefinition rArm02 = rArm01.addOrReplaceChild("rArm02", CubeListBuilder.create().texOffs(55, 46).mirror().addBox(-1.5F, -0.01F, -1.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(15, 58).mirror().addBox(-2.0F, 5.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.5F, 7.0F, 1.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Tlatlaomi tlatlaomi, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = tlatlaomi.getFallFlyingTicks() > 4;

        head.yRot = netHeadYaw * ((float)Math.PI / 180F);

        if (flag) {
            this.head.xRot = (-(float)Math.PI / 4F);
        }
        else {
            this.head.xRot = headPitch * ((float)Math.PI / 180F);
        }

        float f = 1.0F;
        if (flag) {
            f = (float)tlatlaomi.getDeltaMovement().lengthSqr();
            f /= 0.2F;
            f *= f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        rightArm.zRot = 0.0F;
        leftArm.zRot = 0.0F;
        rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
        rightLeg.yRot = 0.0F;
        leftLeg.yRot = 0.0F;
        rightLeg.zRot = 0.0F;
        leftLeg.zRot = 0.0F;

        if (riding) {
            rightArm.xRot += (-(float)Math.PI / 5F);
            leftArm.xRot += (-(float)Math.PI / 5F);
            rightLeg.xRot = -1.4137167F;
            rightLeg.yRot = ((float)Math.PI / 10F);
            rightLeg.zRot = 0.07853982F;
            leftLeg.xRot = -1.4137167F;
            leftLeg.yRot = (-(float)Math.PI / 10F);
            leftLeg.zRot = -0.07853982F;
        }
        rightArm.yRot = 0.0F;
        leftArm.yRot = 0.0F;

        AnimationUtils.bobArms(rightArm, leftArm, ageInTicks);
        jaw.xRot = (Mth.sin(ageInTicks / 18.0F)) / 8.0F + 0.25F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        tlatlaomi.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
