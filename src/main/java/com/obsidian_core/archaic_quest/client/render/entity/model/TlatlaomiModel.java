package com.obsidian_core.archaic_quest.client.render.entity.model;

import com.obsidian_core.archaic_quest.common.entity.living.Tlatlaomi;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.CrossbowPosing;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

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

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData tlatlaomi = partdefinition.addChild("tlatlaomi", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -2.0F, 0.0F, 10.0F, 8.0F, 5.0F, new Dilation(0.0F))
                .uv(28, 13).cuboid(-1.0F, -2.5F, 4.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F))
                .uv(66, 54).cuboid(-5.5F, -2.25F, -0.5F, 11.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.0F, -3.0F));

        ModelPartData innerTendons02_r1 = tlatlaomi.addChild("innerTendons02_r1", ModelPartBuilder.create().uv(112, 24).cuboid(0.25F, -2.0F, -5.0F, 0.0F, 13.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 3.0F, 0.0F, -1.0908F, 0.0F));

        ModelPartData innerTendons01_r1 = tlatlaomi.addChild("innerTendons01_r1", ModelPartBuilder.create().uv(97, 24).cuboid(-0.25F, -2.0F, -5.0F, 0.0F, 13.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 3.0F, 0.0F, 1.0908F, 0.0F));

        ModelPartData cape = tlatlaomi.addChild("cape", ModelPartBuilder.create().uv(104, 44).cuboid(-5.0F, -1.0F, -0.75F, 10.0F, 17.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 5.0F, 0.2182F, 0.0F, 0.0F));

        ModelPartData capeLower = cape.addChild("capeLower", ModelPartBuilder.create().uv(105, 62).cuboid(-5.0F, 0.0F, -0.75F, 10.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, 0.0F));

        ModelPartData lowerRibs = tlatlaomi.addChild("lowerRibs", ModelPartBuilder.create().uv(1, 13).cuboid(-4.5F, 0.0F, -3.0F, 9.0F, 6.0F, 4.0F, new Dilation(0.0F))
                .uv(29, 24).cuboid(-1.0F, -0.5F, 1.0F, 2.0F, 7.0F, 1.0F, new Dilation(-0.01F)), ModelTransform.pivot(0.0F, 6.0F, 4.0F));

        ModelPartData belt = lowerRibs.addChild("belt", ModelPartBuilder.create().uv(0, 25).cuboid(-4.5F, -2.0F, -3.5F, 9.0F, 3.0F, 5.0F, new Dilation(0.02F)), ModelTransform.pivot(0.0F, 6.0F, 0.0F));

        ModelPartData loinclothFront = belt.addChild("loinclothFront", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.0F, -3.0F));

        ModelPartData cube_r1 = loinclothFront.addChild("cube_r1", ModelPartBuilder.create().uv(18, 35).cuboid(-4.0F, -1.0F, -0.75F, 8.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        ModelPartData loinclothBack = belt.addChild("loinclothBack", ModelPartBuilder.create().uv(19, 44).cuboid(-4.0F, 0.0F, 0.5F, 8.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        ModelPartData lLeg01 = lowerRibs.addChild("lLeg01", ModelPartBuilder.create().uv(0, 35).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.25F, 6.0F, -1.0F));

        ModelPartData lLeg02 = lLeg01.addChild("lLeg02", ModelPartBuilder.create().uv(0, 49).cuboid(-1.5F, -0.01F, -0.25F, 3.0F, 9.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 9.0F, -1.75F));

        ModelPartData rLeg01 = lowerRibs.addChild("rLeg01", ModelPartBuilder.create().uv(0, 35).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-2.25F, 6.0F, -1.0F));

        ModelPartData rLeg02 = rLeg01.addChild("rLeg02", ModelPartBuilder.create().uv(0, 49).mirrored().cuboid(-1.5F, -0.01F, -0.25F, 3.0F, 9.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 9.0F, -1.75F));

        ModelPartData head = tlatlaomi.addChild("head", ModelPartBuilder.create().uv(38, 0).cuboid(-3.5F, -7.8644F, -6.4652F, 7.0F, 6.0F, 7.0F, new Dilation(0.0F))
                .uv(56, 15).cuboid(-2.0F, -1.8644F, -6.4652F, 4.0F, 1.0F, 1.0F, new Dilation(0.01F))
                .uv(38, 13).cuboid(-4.0F, -6.8644F, -6.7152F, 8.0F, 4.0F, 1.0F, new Dilation(0.1F)), ModelTransform.of(0.0F, -2.25F, 4.0F, -0.0436F, 0.0F, 0.0F));

        ModelPartData headpiece = head.addChild("headpiece", ModelPartBuilder.create().uv(69, 0).cuboid(-4.5F, -1.5F, -3.75F, 9.0F, 3.0F, 8.0F, new Dilation(0.0F))
                .uv(69, 12).cuboid(-2.5F, -3.0F, -3.0F, 5.0F, 9.0F, 8.0F, new Dilation(0.0F))
                .uv(96, 0).cuboid(-2.0F, -6.0F, -3.0F, 4.0F, 3.0F, 12.0F, new Dilation(0.0F))
                .uv(97, 16).cuboid(-2.0F, -3.0F, 5.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.0F))
                .uv(56, 19).cuboid(-1.5F, -3.0F, -4.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -7.8644F, -3.2152F));

        ModelPartData lFeatherBundle = headpiece.addChild("lFeatherBundle", ModelPartBuilder.create(), ModelTransform.of(2.5F, -2.0F, 3.0F, 0.0F, 0.0F, 0.1309F));

        ModelPartData lFeather01 = lFeatherBundle.addChild("lFeather01", ModelPartBuilder.create(), ModelTransform.of(1.0F, 1.0F, 0.0F, -0.8658F, 0.1329F, 0.1124F));

        ModelPartData cube_r2 = lFeather01.addChild("cube_r2", ModelPartBuilder.create().uv(56, 23).cuboid(0.0F, -13.0F, -2.0F, 0.0F, 13.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.5672F, 0.0F));

        ModelPartData lFeather02 = lFeatherBundle.addChild("lFeather02", ModelPartBuilder.create(), ModelTransform.of(0.25F, 1.0F, -1.0F, -0.5178F, 0.0735F, 0.1585F));

        ModelPartData cube_r3 = lFeather02.addChild("cube_r3", ModelPartBuilder.create().uv(47, 23).cuboid(0.0F, -13.0F, -2.0F, 0.0F, 13.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.48F, 0.0F));

        ModelPartData lFeather03 = lFeatherBundle.addChild("lFeather03", ModelPartBuilder.create(), ModelTransform.of(-0.5F, 1.0F, -2.0F, -0.1289F, 0.0227F, 0.1731F));

        ModelPartData cube_r4 = lFeather03.addChild("cube_r4", ModelPartBuilder.create().uv(38, 23).cuboid(0.0F, -13.0F, -2.0F, 0.0F, 13.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.5672F, 0.0F));

        ModelPartData lFeather04 = lFeatherBundle.addChild("lFeather04", ModelPartBuilder.create(), ModelTransform.of(0.25F, 2.0F, 2.0F, 0.3079F, 0.1248F, 0.0396F));

        ModelPartData cube_r5 = lFeather04.addChild("cube_r5", ModelPartBuilder.create().uv(41, 27).cuboid(1.0F, -3.0F, -2.0F, 0.0F, 5.0F, 13.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

        ModelPartData rFeatherBundle = headpiece.addChild("rFeatherBundle", ModelPartBuilder.create(), ModelTransform.of(-2.5F, -2.0F, 3.0F, 0.0F, 0.0F, -0.1309F));

        ModelPartData rFeather01 = rFeatherBundle.addChild("rFeather01", ModelPartBuilder.create(), ModelTransform.of(-1.0F, 1.0F, 0.0F, -0.8658F, -0.1329F, -0.1124F));

        ModelPartData cube_r6 = rFeather01.addChild("cube_r6", ModelPartBuilder.create().uv(56, 23).mirrored().cuboid(0.0F, -13.0F, -2.0F, 0.0F, 13.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.5672F, 0.0F));

        ModelPartData rFeather02 = rFeatherBundle.addChild("rFeather02", ModelPartBuilder.create(), ModelTransform.of(-0.25F, 1.0F, -1.0F, -0.5178F, -0.0735F, -0.1585F));

        ModelPartData cube_r7 = rFeather02.addChild("cube_r7", ModelPartBuilder.create().uv(47, 23).mirrored().cuboid(0.0F, -13.0F, -2.0F, 0.0F, 13.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.48F, 0.0F));

        ModelPartData rFeather03 = rFeatherBundle.addChild("rFeather03", ModelPartBuilder.create(), ModelTransform.of(0.5F, 1.0F, -2.0F, -0.1289F, -0.0227F, -0.1731F));

        ModelPartData cube_r8 = rFeather03.addChild("cube_r8", ModelPartBuilder.create().uv(38, 23).mirrored().cuboid(0.0F, -13.0F, -2.0F, 0.0F, 13.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.5672F, 0.0F));

        ModelPartData rFeather04 = rFeatherBundle.addChild("rFeather04", ModelPartBuilder.create(), ModelTransform.of(-0.25F, 2.0F, 2.0F, 0.3079F, -0.1248F, -0.0396F));

        ModelPartData cube_r9 = rFeather04.addChild("cube_r9", ModelPartBuilder.create().uv(41, 27).mirrored().cuboid(-1.0F, -3.0F, -2.0F, 0.0F, 5.0F, 13.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        ModelPartData headdresstassle = headpiece.addChild("headdresstassle", ModelPartBuilder.create().uv(0, 66).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 8.0F, 13.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 7.0F, -0.9599F, 0.0F, 0.0F));

        ModelPartData tassleFeather01 = headdresstassle.addChild("tassleFeather01", ModelPartBuilder.create().uv(0, 75).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 4.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 11.0F, 0.0873F, 0.0F, 0.0F));

        ModelPartData tassleFeather02 = headdresstassle.addChild("tassleFeather02", ModelPartBuilder.create().uv(0, 80).cuboid(0.25F, -1.0F, 0.0F, 0.0F, 4.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 11.0F, -0.0436F, 0.0F, 0.0F));

        ModelPartData tassleFeather03 = headdresstassle.addChild("tassleFeather03", ModelPartBuilder.create().uv(0, 85).cuboid(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.5F, 11.0F, -0.3491F, 0.0F, 0.0F));

        ModelPartData tassleFeather2 = headdresstassle.addChild("tassleFeather2", ModelPartBuilder.create().uv(0, 75).cuboid(-0.25F, -1.0F, 0.0F, 0.0F, 4.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.0F, 11.0F, -0.48F, 0.0F, 0.0F));

        ModelPartData headdress_tail = headpiece.addChild("headdress_tail", ModelPartBuilder.create().uv(70, 30).cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 6.0F, 4.0F, 0.1745F, 0.0F, 0.0F));

        ModelPartData lowerJaw = head.addChild("lowerJaw", ModelPartBuilder.create().uv(38, 19).cuboid(-3.0F, -0.1736F, -5.9848F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.8644F, -0.2152F, 0.1745F, 0.0F, 0.0F));

        ModelPartData lArm01 = tlatlaomi.addChild("lArm01", ModelPartBuilder.create().uv(40, 45).cuboid(0.0F, -2.0F, -1.0F, 3.0F, 9.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.0436F));

        ModelPartData lArm02 = lArm01.addChild("lArm02", ModelPartBuilder.create().uv(55, 46).cuboid(-1.5F, -0.01F, -1.5F, 3.0F, 9.0F, 3.0F, new Dilation(0.0F))
                .uv(38, 58).mirrored().cuboid(-2.0F, 5.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(1.5F, 7.0F, 1.0F));

        ModelPartData rArm01 = tlatlaomi.addChild("rArm01", ModelPartBuilder.create().uv(40, 45).mirrored().cuboid(-3.0F, -2.0F, -1.0F, 3.0F, 9.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.0436F));

        ModelPartData rArm02 = rArm01.addChild("rArm02", ModelPartBuilder.create().uv(55, 46).mirrored().cuboid(-1.5F, -0.01F, -1.5F, 3.0F, 9.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
                .uv(15, 58).mirrored().cuboid(-2.0F, 5.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-1.5F, 7.0F, 1.0F));

        return TexturedModelData.of(meshdefinition, 128, 128);
    }

    @Override
    public void setAngles(Tlatlaomi tlatlaomi, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
            f = (float)tlatlaomi.getVelocity().lengthSquared();
            f /= 0.2F;
            f *= f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        rightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        leftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        rightArm.zRot = 0.0F;
        leftArm.zRot = 0.0F;
        rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
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

        CrossbowPosing.swingArms(rightArm, leftArm, ageInTicks);
        jaw.xRot = (MathHelper.sin(ageInTicks / 18.0F)) / 8.0F + 0.25F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        tlatlaomi.render(matrices, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
