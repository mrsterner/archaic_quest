package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonDoorBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class AztecDungeonDoorRenderer implements BlockEntityRenderer<AztecDungeonDoorBlockEntity> {

    private final ModelPart door;
    private final ModelPart doorFrame;

    private final ModelPart frame;

    public AztecDungeonDoorRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart root = context.bakeLayer(AQModelLayers.AZTEC_DUNGEON_DOOR);
        this.door = root.getChild("door");
        this.doorFrame = root.getChild("door_frame");
        this.frame = root.getChild("frame");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(108, 181).addBox(-17.0F, -39.0F, 0.0F, 32.0F, 39.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 24.0F, -2.0F));

        PartDefinition doorFrame = partdefinition.addOrReplaceChild("door_frame", CubeListBuilder.create().texOffs(48, 192).addBox(16.0F, -48.0F, -8.0F, 8.0F, 48.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 192).addBox(-24.0F, -48.0F, -8.0F, 8.0F, 48.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(96, 224).addBox(-16.0F, -48.0F, -8.0F, 32.0F, 3.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(90, 153).addBox(-16.0F, -45.0F, -11.0F, 32.0F, 6.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(55, 211).addBox(13.0F, -39.0F, -8.01F, 3.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(2, 211).addBox(-16.0F, -39.0F, -8.01F, 3.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 211).addBox(-16.0F, -39.0F, 2.01F, 3.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(41, 211).addBox(13.0F, -39.0F, 2.01F, 3.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube12_r1 = doorFrame.addOrReplaceChild("cube12_r1", CubeListBuilder.create().texOffs(230, 0).addBox(0.0F, -41.0F, -5.0F, 5.0F, 41.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(230, 0).addBox(0.0F, -41.0F, 7.0F, 5.0F, 41.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, 0.0F, -5.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition cube10_r1 = doorFrame.addOrReplaceChild("cube10_r1", CubeListBuilder.create().texOffs(230, 0).addBox(-5.0F, -41.0F, -5.0F, 5.0F, 41.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(230, 0).addBox(-5.0F, -41.0F, 7.0F, 5.0F, 41.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.0F, 0.0F, -5.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition frame = partdefinition.addOrReplaceChild("frame", CubeListBuilder.create().texOffs(48, 192).addBox(15.0F, -48.0F, -6.0F, 8.0F, 48.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 192).addBox(-25.0F, -48.0F, -6.0F, 8.0F, 48.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(96, 224).addBox(-17.0F, -48.0F, -6.0F, 32.0F, 3.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(90, 153).addBox(-17.0F, -45.0F, -9.0F, 32.0F, 6.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(55, 211).addBox(12.0F, -39.0F, -6.01F, 3.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(2, 211).addBox(-17.0F, -39.0F, -6.01F, 3.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 211).addBox(-17.0F, -39.0F, 4.01F, 3.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(41, 211).addBox(12.0F, -39.0F, 4.01F, 3.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 24.0F, -2.0F));

        PartDefinition cube10_fr1 = frame.addOrReplaceChild("cube10_fr1", CubeListBuilder.create().texOffs(206, 0).addBox(-5.0F, -41.0F, -5.0F, 5.0F, 41.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(20.0F, 0.0F, -3.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition cube12_fr1 = frame.addOrReplaceChild("cube12_fr1", CubeListBuilder.create().texOffs(206, 0).addBox(0.0F, -41.0F, -5.0F, 5.0F, 41.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.0F, 0.0F, -3.0F, 0.0F, 0.0F, 0.1745F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void render(AztecDungeonDoorBlockEntity dungeonDoor, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int textureOverlay) {
        Direction direction = dungeonDoor.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        float rotation = direction.toYRot();

        poseStack.pushPose();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(-rotation));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));

        double x, z;

        switch (direction) {
            default -> {
                x = 0.5D;
                z = -0.5D;
            }
            case SOUTH -> {
                x = -0.5D;
                z = 0.5D;
            }
            case WEST -> {
                x = -0.5D;
                z = -0.5D;
            }
            case EAST -> {
                x = 0.5D;
                z = 0.5D;
            }
        }
        poseStack.translate(x, -1.5D, z);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(dungeonDoor.getDoorType().getTextureLocation()));

        if (dungeonDoor.getDoorType().isFrame()) {
            renderDoorFrame(poseStack, vertexConsumer, packedLight, textureOverlay);
        }
        else {
            renderDoor(dungeonDoor, partialTick, poseStack, vertexConsumer, packedLight, textureOverlay);
        }
        poseStack.popPose();
    }


    private void renderDoor(AztecDungeonDoorBlockEntity dungeonDoor, float partialTick, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int textureOverlay) {
        doorFrame.render(poseStack, vertexConsumer, packedLight, textureOverlay);

        poseStack.pushPose();

        int doorPos = dungeonDoor.getDoorPosition();
        float precision = doorPos >= 60 || doorPos <= 0 ? 0.0F : partialTick;

        switch (dungeonDoor.getDoorState()) {
            default:
                break;
            case STAND_BY:
                precision = 0.0F;
                break;
            case CLOSING:
                precision = -precision;
                break;
        }

        double y = (double) (dungeonDoor.getDoorPosition() + precision) / 25.0D;
        poseStack.translate(0.0D, y, 0.0D);
        door.render(poseStack, vertexConsumer, packedLight, textureOverlay);

        poseStack.popPose();
    }

    private void renderDoorFrame(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int textureOverlay) {
        frame.render(poseStack, vertexConsumer, packedLight, textureOverlay);
    }

    @Override
    public boolean shouldRenderOffScreen(AztecDungeonDoorBlockEntity tileEntity) {
        return true;
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
