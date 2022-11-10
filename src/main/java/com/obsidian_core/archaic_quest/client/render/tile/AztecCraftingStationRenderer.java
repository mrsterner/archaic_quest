package com.obsidian_core.archaic_quest.client.render.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.tile.AztecCraftingStationTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class AztecCraftingStationRenderer extends TileEntityRenderer<AztecCraftingStationTileEntity> {

    private static final ResourceLocation texture = ArchaicQuest.resourceLoc("textures/block/aztec_crafting_station.png");

    private ModelRenderer table;
    private ModelRenderer cube5_r1;
    private ModelRenderer cube8_r1;
    private ModelRenderer misc;
    private ModelRenderer hammer;
    private ModelRenderer chissel;
    private ModelRenderer paper;
    private ModelRenderer cube17_r1;
    private ModelRenderer patterntiles;
    private ModelRenderer cube13_r1;
    private ModelRenderer cube12_r1;
    private ModelRenderer cube14_r1;

    public AztecCraftingStationRenderer(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
        setupModel();
    }

    private void setupModel() {
        int textureWidth = 256;
        int textureHeight = 256;

        table = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        table.setPos(0.0F, 24.0F, -8.0F);
        table.texOffs(0, 193).addBox(-16.0F, -4.0F, -8.0F, 32.0F, 4.0F, 24.0F, 0.0F, false);
        table.texOffs(173, 53).addBox(-16.0F, -12.0F, 12.99F, 2.0F, 8.0F, 1.0F, 0.0F, false);
        table.texOffs(176, 53).addBox(-16.0F, -12.0F, -5.99F, 2.0F, 8.0F, 1.0F, 0.0F, false);
        table.texOffs(173, 53).addBox(13.99F, -12.0F, 12.99F, 2.0F, 8.0F, 1.0F, 0.0F, false);
        table.texOffs(176, 53).addBox(13.99F, -12.0F, -5.99F, 2.0F, 8.0F, 1.0F, 0.0F, false);
        table.texOffs(6, 165).addBox(-14.0F, -12.0F, -6.001F, 28.0F, 8.0F, 20.0F, 0.0F, false);
        table.texOffs(0, 228).addBox(-19.0F, -16.0F, -8.0F, 38.0F, 4.0F, 24.0F, 0.0F, false);

        cube5_r1 = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        cube5_r1.setPos(14.0F, -4.0F, 13.0F);
        table.addChild(cube5_r1);
        setRotationAngle(cube5_r1, 0.0F, 0.0F, 0.2618F);
        cube5_r1.texOffs(71, 72).addBox(-1.0F, -9.0F, -9.0001F, 2.0F, 9.0F, 10.0F, 0.0F, false);
        cube5_r1.texOffs(0, 71).addBox(-1.0F, -9.0F, -18.9999F, 2.0F, 9.0F, 10.0F, 0.0F, false);

        cube8_r1 = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        cube8_r1.setPos(-14.0F, -4.0F, 19.0F);
        table.addChild(cube8_r1);
        setRotationAngle(cube8_r1, 0.0F, 0.0F, -0.2618F);
        cube8_r1.texOffs(0, 46).addBox(-1.0F, -9.0F, -15.0001F, 2.0F, 9.0F, 10.0F, 0.0F, false);
        cube8_r1.texOffs(40, 40).addBox(-1.0F, -9.0F, -25.0F, 2.0F, 9.0F, 10.0F, 0.0F, false);

        misc = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        misc.setPos(0.0F, 8.0F, -4.0F);
        setRotationAngle(misc, 0.0F, 3.1416F, 0.0F);


        hammer = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        hammer.setPos(11.0F, 0.0F, 5.0F);
        misc.addChild(hammer);
        setRotationAngle(hammer, -0.1745F, -0.8308F, 0.0F);
        hammer.texOffs(115, 6).addBox(-3.0F, -3.0F, -2.0F, 5.0F, 3.0F, 2.0F, 0.0F, false);
        hammer.texOffs(122, 46).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);

        chissel = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        chissel.setPos(-9.0F, 0.0F, 5.0F);
        misc.addChild(chissel);
        setRotationAngle(chissel, 0.0F, 0.3491F, 0.0F);
        chissel.texOffs(145, 67).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        chissel.texOffs(116, 7).addBox(-1.5F, -1.0F, -4.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);

        paper = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        paper.setPos(2.0F, 0.0F, 0.0F);
        misc.addChild(paper);
        setRotationAngle(paper, 0.0F, -0.3491F, 0.0F);
        paper.texOffs(155, 29).addBox(-0.5365F, -0.01F, -7.3187F, 5.0F, 0.0F, 6.0F, 0.0F, false);

        cube17_r1 = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        cube17_r1.setPos(0.0F, 0.7581F, -4.3137F);
        paper.addChild(cube17_r1);
        setRotationAngle(cube17_r1, -0.3491F, 0.0F, 0.0F);
        cube17_r1.texOffs(0, 0).addBox(-0.5365F, 0.3051F, -5.0868F, 5.0F, 0.0F, 2.0F, 0.0F, false);

        patterntiles = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        patterntiles.setPos(0.0F, 16.0F, -4.0F);
        misc.addChild(patterntiles);


        cube13_r1 = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        cube13_r1.setPos(-11.0F, -16.0F, 2.0F);
        patterntiles.addChild(cube13_r1);
        setRotationAngle(cube13_r1, 1.5708F, 0.0F, 0.0F);
        cube13_r1.texOffs(240, 248).addBox(-3.0F, -7.0F, 2.0F, 6.0F, 6.0F, 2.0F, 0.0F, false);

        cube12_r1 = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        cube12_r1.setPos(-10.0F, -16.0F, 2.0F);
        patterntiles.addChild(cube12_r1);
        setRotationAngle(cube12_r1, 0.1745F, 0.0F, 0.0F);
        cube12_r1.texOffs(240, 248).addBox(-4.0F, -6.1F, -0.25F, 6.0F, 6.0F, 2.0F, 0.0F, false);

        cube14_r1 = new ModelRenderer(textureWidth, textureHeight, 0, 0);
        cube14_r1.setPos(-10.0F, -14.0F, 2.0F);
        patterntiles.addChild(cube14_r1);
        setRotationAngle(cube14_r1, 1.5708F, 0.0F, 0.0F);
        cube14_r1.texOffs(240, 248).addBox(-3.0F, -7.0F, 2.0F, 6.0F, 6.0F, 2.0F, 0.0F, false);
    }
    
    @Override
    public void render(AztecCraftingStationTileEntity tileEntity, float partialTick, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int packedLight, int textureOverlay) {
        BlockState state = tileEntity.getLevel() == null ? AQBlocks.AZTEC_CRAFTING_STATION.get().defaultBlockState() : tileEntity.getBlockState();
        Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
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

        IVertexBuilder vertexBuilder = renderTypeBuffer.getBuffer(RenderType.entityCutout(texture));
        table.render(matrixStack, vertexBuilder, packedLight, textureOverlay);
        misc.render(matrixStack, vertexBuilder, packedLight, textureOverlay);

        /*
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

         */

        matrixStack.popPose();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
