package com.obsidian_core.archaic_quest.client.render.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.tile.AztecCraftingStationBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class AztecCraftingStationRenderer implements BlockEntityRenderer<AztecCraftingStationBlockEntity> {

    private static final ResourceLocation texture = ArchaicQuest.resourceLoc("textures/block/aztec_crafting_station.png");

    private ModelPart table;
    private ModelPart cube5_r1;
    private ModelPart cube8_r1;
    private ModelPart misc;
    private ModelPart hammer;
    private ModelPart chissel;
    private ModelPart paper;
    private ModelPart cube17_r1;
    private ModelPart patterntiles;
    private ModelPart cube13_r1;
    private ModelPart cube12_r1;
    private ModelPart cube14_r1;

    public AztecCraftingStationRenderer(BlockEntityRendererProvider.Context context) {
        //setupModel();
    }

    /*
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

     */
    
    @Override
    public void render(AztecCraftingStationBlockEntity tileEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int textureOverlay) {
        BlockState state = tileEntity.getLevel() == null ? AQBlocks.AZTEC_CRAFTING_STATION.get().defaultBlockState() : tileEntity.getBlockState();
        Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        float rotation = direction.toYRot();

        poseStack.pushPose();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(-rotation));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));

        double x, z;

        switch (direction) {
            // NORTH & default
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

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(texture));
        table.render(poseStack, vertexConsumer, packedLight, textureOverlay);
        misc.render(poseStack, vertexConsumer, packedLight, textureOverlay);

        poseStack.popPose();
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
