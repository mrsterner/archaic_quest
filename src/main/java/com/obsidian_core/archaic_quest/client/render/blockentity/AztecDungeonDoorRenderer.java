package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.obsidian_core.archaic_quest.common.blockentity.AztecDungeonDoorBlockEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;

public class AztecDungeonDoorRenderer implements BlockEntityRenderer<AztecDungeonDoorBlockEntity> {

    private final ModelPart door;
    private final ModelPart doorFrame;

    private final ModelPart frame;

    public AztecDungeonDoorRenderer(BlockEntityRendererFactory.Context context) {
        ModelPart root = context.getLayerModelPart(AQModelLayers.AZTEC_DUNGEON_DOOR);
        this.door = root.getChild("door");
        this.doorFrame = root.getChild("door_frame");
        this.frame = root.getChild("frame");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData door = partdefinition.addChild("door", ModelPartBuilder.create().uv(108, 181).cuboid(-17.0F, -39.0F, 0.0F, 32.0F, 39.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 24.0F, -2.0F));

        ModelPartData doorFrame = partdefinition.addChild("door_frame", ModelPartBuilder.create().uv(48, 192).cuboid(16.0F, -48.0F, -8.0F, 8.0F, 48.0F, 16.0F, new Dilation(0.0F))
                .uv(0, 192).cuboid(-24.0F, -48.0F, -8.0F, 8.0F, 48.0F, 16.0F, new Dilation(0.0F))
                .uv(96, 224).cuboid(-16.0F, -48.0F, -8.0F, 32.0F, 3.0F, 16.0F, new Dilation(0.0F))
                .uv(90, 153).cuboid(-16.0F, -45.0F, -11.0F, 32.0F, 6.0F, 22.0F, new Dilation(0.0F))
                .uv(55, 211).cuboid(13.0F, -39.0F, -8.01F, 3.0F, 11.0F, 6.0F, new Dilation(0.0F))
                .uv(2, 211).cuboid(-16.0F, -39.0F, -8.01F, 3.0F, 11.0F, 6.0F, new Dilation(0.0F))
                .uv(22, 211).cuboid(-16.0F, -39.0F, 2.01F, 3.0F, 11.0F, 6.0F, new Dilation(0.0F))
                .uv(41, 211).cuboid(13.0F, -39.0F, 2.01F, 3.0F, 11.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube12_r1 = doorFrame.addChild("cube12_r1", ModelPartBuilder.create().uv(230, 0).cuboid(0.0F, -41.0F, -5.0F, 5.0F, 41.0F, 8.0F, new Dilation(0.0F))
                .uv(230, 0).cuboid(0.0F, -41.0F, 7.0F, 5.0F, 41.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-21.0F, 0.0F, -5.0F, 0.0F, 0.0F, 0.1745F));

        ModelPartData cube10_r1 = doorFrame.addChild("cube10_r1", ModelPartBuilder.create().uv(230, 0).cuboid(-5.0F, -41.0F, -5.0F, 5.0F, 41.0F, 8.0F, new Dilation(0.0F))
                .uv(230, 0).cuboid(-5.0F, -41.0F, 7.0F, 5.0F, 41.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(21.0F, 0.0F, -5.0F, 0.0F, 0.0F, -0.1745F));

        ModelPartData frame = partdefinition.addChild("frame", ModelPartBuilder.create().uv(48, 192).cuboid(15.0F, -48.0F, -6.0F, 8.0F, 48.0F, 16.0F, new Dilation(0.0F))
                .uv(0, 192).cuboid(-25.0F, -48.0F, -6.0F, 8.0F, 48.0F, 16.0F, new Dilation(0.0F))
                .uv(96, 224).cuboid(-17.0F, -48.0F, -6.0F, 32.0F, 3.0F, 16.0F, new Dilation(0.0F))
                .uv(90, 153).cuboid(-17.0F, -45.0F, -9.0F, 32.0F, 6.0F, 22.0F, new Dilation(0.0F))
                .uv(55, 211).cuboid(12.0F, -39.0F, -6.01F, 3.0F, 11.0F, 6.0F, new Dilation(0.0F))
                .uv(2, 211).cuboid(-17.0F, -39.0F, -6.01F, 3.0F, 11.0F, 6.0F, new Dilation(0.0F))
                .uv(22, 211).cuboid(-17.0F, -39.0F, 4.01F, 3.0F, 11.0F, 6.0F, new Dilation(0.0F))
                .uv(41, 211).cuboid(12.0F, -39.0F, 4.01F, 3.0F, 11.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 24.0F, -2.0F));

        ModelPartData cube10_fr1 = frame.addChild("cube10_fr1", ModelPartBuilder.create().uv(206, 0).cuboid(-5.0F, -41.0F, -5.0F, 5.0F, 41.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(20.0F, 0.0F, -3.0F, 0.0F, 0.0F, -0.1745F));

        ModelPartData cube12_fr1 = frame.addChild("cube12_fr1", ModelPartBuilder.create().uv(206, 0).cuboid(0.0F, -41.0F, -5.0F, 5.0F, 41.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(-22.0F, 0.0F, -3.0F, 0.0F, 0.0F, 0.1745F));

        return TexturedModelData.of(meshdefinition, 256, 256);
    }

    @Override
    public void render(AztecDungeonDoorBlockEntity dungeonDoor, float partialTick, MatrixStack matrices, VertexConsumerProvider bufferSource, int packedLight, int textureOverlay) {
        Direction direction = dungeonDoor.getCachedState().get(Properties.HORIZONTAL_FACING);
        float rotation = direction.asRotation();

        matrices.push();
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-rotation));
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F));

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
        matrices.translate(x, -1.5D, z);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderLayer.getEntityCutout(dungeonDoor.getDoorType().getTextureLocation()));

        if (dungeonDoor.getDoorType().isFrame()) {
            renderDoorFrame(matrices, vertexConsumer, packedLight, textureOverlay);
        }
        else {
            renderDoor(dungeonDoor, partialTick, matrices, vertexConsumer, packedLight, textureOverlay);
        }
        matrices.pop();
    }


    private void renderDoor(AztecDungeonDoorBlockEntity dungeonDoor, float partialTick, MatrixStack matrices, VertexConsumer vertexConsumer, int packedLight, int textureOverlay) {
        doorFrame.render(matrices, vertexConsumer, packedLight, textureOverlay);

        matrices.push();

        int doorPos = dungeonDoor.getDoorPosition();
        float precision = doorPos >= 60 || doorPos <= 0 ? 0.0F : partialTick;

        switch (dungeonDoor.getDoorState()) {
            case STAND_BY -> precision = 0.0F;
            case CLOSING -> precision = -precision;
            default -> {
            }
        }

        double y = (double) (dungeonDoor.getDoorPosition() + precision) / 25.0D;
        matrices.translate(0.0D, y, 0.0D);
        door.render(matrices, vertexConsumer, packedLight, textureOverlay);

        matrices.pop();
    }

    private void renderDoorFrame(MatrixStack matrices, VertexConsumer vertexConsumer, int packedLight, int textureOverlay) {
        frame.render(matrices, vertexConsumer, packedLight, textureOverlay);
    }

    @Override
    public boolean rendersOutsideBoundingBox(AztecDungeonDoorBlockEntity tileEntity) {
        return true;
    }

    @Override
    public int getRenderDistance() {
        return 256;
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.pitch = x;
        modelRenderer.yaw = y;
        modelRenderer.roll = z;
    }
}
