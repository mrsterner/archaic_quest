package com.obsidian_core.archaic_quest.client.render.tile;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class AztecDungeonDoorRenderer implements BlockEntityRenderer<AztecDungeonDoorBlockEntity> {

    private ModelPart door;
    private ModelPart frame;
    private ModelPart cube11_r1;
    private ModelPart cube10_r1;

    private ModelPart doorFrame;
    private ModelPart frameElement;
    private ModelPart frame12_r1;
    private ModelPart frame10_r1;


    public AztecDungeonDoorRenderer(BlockEntityRendererProvider.Context context) {
        //setupModel();
    }

    /*
    private void setupModel() {
        int textureWidth = 256;
        int textureHeight = 256;

        //--------- DOOR --------- //
        door = new ModelPart(textureWidth, textureHeight, 0, 0);
        door.setPos(1.0F, 24.0F, -2.0F);
        door.texOffs(108, 181).addBox(-17.0F, -39.0F, 0.0F, 32.0F, 39.0F, 4.0F, 0.0F, false);

        frame = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        frame.setPos(0.0F, 24.0F, 0.0F);
        frame.texOffs(48, 192).addBox(16.0F, -48.0F, -8.0F, 8.0F, 48.0F, 16.0F, 0.0F, false);
        frame.texOffs(0, 192).addBox(-24.0F, -48.0F, -8.0F, 8.0F, 48.0F, 16.0F, 0.0F, false);
        frame.texOffs(96, 224).addBox(-16.0F, -48.0F, -8.0F, 32.0F, 3.0F, 16.0F, 0.0F, false);
        frame.texOffs(90, 153).addBox(-16.0F, -45.0F, -11.0F, 32.0F, 6.0F, 22.0F, 0.0F, false);
        frame.texOffs(55, 211).addBox(13.0F, -39.0F, -8.01F, 3.0F, 11.0F, 6.0F, 0.0F, false);
        frame.texOffs(2, 211).addBox(-16.0F, -39.0F, -8.01F, 3.0F, 11.0F, 6.0F, 0.0F, false);
        frame.texOffs(22, 211).addBox(-16.0F, -39.0F, 2.01F, 3.0F, 11.0F, 6.0F, 0.0F, false);
        frame.texOffs(41, 211).addBox(13.0F, -39.0F, 2.01F, 3.0F, 11.0F, 6.0F, 0.0F, false);

        cube11_r1 = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        cube11_r1.setPos(-21.0F, 0.0F, 7.0F);
        frame.addChild(cube11_r1);
        setRotationAngle(cube11_r1, 0.0F, 0.0F, 0.1745F);
        cube11_r1.texOffs(230, 0).addBox(0.0F, -41.0F, -5.0F, 5.0F, 41.0F, 8.0F, 0.0F, false);
        cube11_r1.texOffs(230, 0).addBox(0.0F, -41.0F, -17.0F, 5.0F, 41.0F, 8.0F, 0.0F, false);

        cube10_r1 = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        cube10_r1.setPos(21.0F, 0.0F, -5.0F);
        frame.addChild(cube10_r1);
        setRotationAngle(cube10_r1, 0.0F, 0.0F, -0.1745F);
        cube10_r1.texOffs(230, 0).addBox(-5.0F, -41.0F, -5.0F, 5.0F, 41.0F, 8.0F, 0.0F, false);
        cube10_r1.texOffs(230, 0).addBox(-5.0F, -41.0F, 7.0F, 5.0F, 41.0F, 8.0F, 0.0F, false);

        //------- DOOR FRAME ------- //
        doorFrame = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        doorFrame.setPos(1.0F, 24.0F, -2.0F);


        frameElement = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        frameElement.setPos(0.0F, 24.0F, 0.0F);
        frameElement.texOffs(48, 192).addBox(16.0F, -48.0F, -8.0F, 8.0F, 48.0F, 16.0F, 0.0F, false);
        frameElement.texOffs(0, 192).addBox(-24.0F, -48.0F, -8.0F, 8.0F, 48.0F, 16.0F, 0.0F, false);
        frameElement.texOffs(96, 224).addBox(-16.0F, -48.0F, -8.0F, 32.0F, 3.0F, 16.0F, 0.0F, false);
        frameElement.texOffs(90, 153).addBox(-16.0F, -45.0F, -11.0F, 32.0F, 6.0F, 22.0F, 0.0F, false);
        frameElement.texOffs(55, 211).addBox(13.0F, -39.0F, -8.01F, 3.0F, 11.0F, 6.0F, 0.0F, false);
        frameElement.texOffs(2, 211).addBox(-16.0F, -39.0F, -8.01F, 3.0F, 11.0F, 6.0F, 0.0F, false);
        frameElement.texOffs(22, 211).addBox(-16.0F, -39.0F, 2.01F, 3.0F, 11.0F, 6.0F, 0.0F, false);
        frameElement.texOffs(41, 211).addBox(13.0F, -39.0F, 2.01F, 3.0F, 11.0F, 6.0F, 0.0F, false);

        frame12_r1 = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        frame12_r1.setPos(-21.0F, 0.0F, -5.0F);
        frameElement.addChild(frame12_r1);
        setRotationAngle(frame12_r1, 0.0F, 0.0F, 0.1745F);
        frame12_r1.texOffs(206, 0).addBox(0.0F, -41.0F, -5.0F, 5.0F, 41.0F, 20.0F, 0.0F, false);

        cube10_r1 = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        cube10_r1.setPos(21.0F, 0.0F, -5.0F);
        frameElement.addChild(cube10_r1);
        setRotationAngle(cube10_r1, 0.0F, 0.0F, -0.1745F);
        cube10_r1.texOffs(206, 0).addBox(-5.0F, -41.0F, -5.0F, 5.0F, 41.0F, 20.0F, 0.0F, false);
    }

     */

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
        doorFrame.render(poseStack, vertexConsumer, packedLight, textureOverlay);
        frameElement.render(poseStack, vertexConsumer, packedLight, textureOverlay);
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
