package com.obsidian_core.archaic_quest.client.render.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class TempAztecDungeonDoorRenderer implements BlockEntityRenderer<AztecDungeonDoorBlockEntity> {

    private final ModelPart door;
    private final ModelPart frame;

    public TempAztecDungeonDoorRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart root = context.bakeLayer(AQModelLayers.AZTEC_DUNGEON_DOOR);
        this.door = root.getChild("door");
        this.frame = root.getChild("frame");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(108, 181).addBox(-17.0F, -39.0F, 0.0F, 32.0F, 39.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 24.0F, -2.0F));

        PartDefinition frame = partdefinition.addOrReplaceChild("frame", CubeListBuilder.create().texOffs(48, 192).addBox(16.0F, -48.0F, -8.0F, 8.0F, 48.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 192).addBox(-24.0F, -48.0F, -8.0F, 8.0F, 48.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(96, 224).addBox(-16.0F, -48.0F, -8.0F, 32.0F, 3.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(90, 153).addBox(-16.0F, -45.0F, -11.0F, 32.0F, 6.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(55, 211).addBox(13.0F, -39.0F, -8.01F, 3.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(2, 211).addBox(-16.0F, -39.0F, -8.01F, 3.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 211).addBox(-16.0F, -39.0F, 2.01F, 3.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(41, 211).addBox(13.0F, -39.0F, 2.01F, 3.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube12_r1 = frame.addOrReplaceChild("cube12_r1", CubeListBuilder.create().texOffs(230, 0).addBox(0.0F, -41.0F, -5.0F, 5.0F, 41.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(230, 0).addBox(0.0F, -41.0F, 7.0F, 5.0F, 41.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, 0.0F, -5.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition cube10_r1 = frame.addOrReplaceChild("cube10_r1", CubeListBuilder.create().texOffs(230, 0).addBox(-5.0F, -41.0F, -5.0F, 5.0F, 41.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(230, 0).addBox(-5.0F, -41.0F, 7.0F, 5.0F, 41.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.0F, 0.0F, -5.0F, 0.0F, 0.0F, -0.1745F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void render(AztecDungeonDoorBlockEntity tileEntity, float partialTick, PoseStack matrixStack, MultiBufferSource bufferSource, int packedLight, int textureOverlay) {
        Direction direction = tileEntity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        float rotation = direction.toYRot();

        matrixStack.pushPose();
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(-rotation));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));

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
        matrixStack.translate(x, -1.5D, z);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(tileEntity.getDoorType().getTextureLocation()));

        if (tileEntity.getDoorType().isFrame()) {
            renderDoorFrame(matrixStack, vertexConsumer, packedLight, textureOverlay);
        }
        else {
            renderDoor(tileEntity, partialTick, matrixStack, vertexConsumer, packedLight, textureOverlay);
        }
        matrixStack.popPose();
    }


    private void renderDoor(AztecDungeonDoorBlockEntity tileEntity, float partialTick, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int textureOverlay) {
        frame.render(poseStack, vertexConsumer, packedLight, textureOverlay);

        poseStack.pushPose();

        int doorPos = tileEntity.getDoorPosition();
        float precision = doorPos >= 60 || doorPos <= 0 ? 0.0F : partialTick;

        switch (tileEntity.getDoorState()) {
            default:
                break;
            case STAND_BY:
                precision = 0.0F;
                break;
            case CLOSING:
                precision = -precision;
                break;
        }

        double y = (double) (tileEntity.getDoorPosition() + precision) / 25.0D;
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
