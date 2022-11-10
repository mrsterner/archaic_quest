package com.obsidian_core.archaic_quest.client.render.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.tile.AztecDungeonDoorTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class AztecDungeonDoorRenderer extends TileEntityRenderer<AztecDungeonDoorTileEntity> {

    private static final ResourceLocation texture = ArchaicQuest.resourceLoc("textures/block/aztec_dungeon_door.png");

    private ModelRenderer door;
    private ModelRenderer frame;
    private ModelRenderer cube11_r1;
    private ModelRenderer cube10_r1;


    public AztecDungeonDoorRenderer(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
        setupModel();
    }

    private void setupModel() {
        int textureWidth = 256;
        int textureHeight = 256;

        door = new ModelRenderer(textureWidth, textureHeight, 0, 0);
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
    }

    @Override
    public void render(AztecDungeonDoorTileEntity tileEntity, float partialTick, MatrixStack matrixStack, IRenderTypeBuffer renderType, int packedLight, int textureOverlay) {
        Direction direction = tileEntity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        float rotation = direction.toYRot();

        matrixStack.pushPose();
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(-rotation));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));

        double x, z;

        switch (direction) {
            default:
            case NORTH:
                x = 0.5D;
                z = -0.5D;
                break;
            case SOUTH:
                x = -0.5D;
                z = 0.5D;
                break;
            case WEST:
                x = -0.5D;
                z = -0.5D;
                break;
            case EAST:
                x = 0.5D;
                z = 0.5D;
                break;
        }
        matrixStack.translate(x, -1.5D, z);

        IVertexBuilder vertexBuilder = renderType.getBuffer(RenderType.entityCutout(texture));
        frame.render(matrixStack, vertexBuilder, packedLight, textureOverlay);

        matrixStack.pushPose();

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
        matrixStack.translate(0.0D, y, 0.0D);
        door.render(matrixStack, vertexBuilder, packedLight, textureOverlay);

        matrixStack.popPose();

        matrixStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(AztecDungeonDoorTileEntity tileEntity) {
        return true;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
