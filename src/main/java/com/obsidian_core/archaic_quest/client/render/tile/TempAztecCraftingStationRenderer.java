package com.obsidian_core.archaic_quest.client.render.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.tile.AztecCraftingStationBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class TempAztecCraftingStationRenderer implements BlockEntityRenderer<AztecCraftingStationBlockEntity> {

    private static final ResourceLocation texture = ArchaicQuest.resourceLoc("textures/block/aztec_crafting_station.png");

    private final ModelPart table;
    private final ModelPart misc;


    public TempAztecCraftingStationRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart root = context.bakeLayer(AQModelLayers.AZTEC_CRAFTING_STATION);
        this.table = root.getChild("table");
        this.misc = root.getChild("misc");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDef = new MeshDefinition();
        PartDefinition partDef = meshDef.getRoot();

        PartDefinition table = partDef.addOrReplaceChild("table", CubeListBuilder.create().texOffs(0, 193).addBox(-16.0F, -4.0F, -8.0F, 32.0F, 4.0F, 24.0F, new CubeDeformation(0.0F))
                .texOffs(173, 53).addBox(-16.0F, -12.0F, 12.99F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(176, 53).addBox(-16.0F, -12.0F, -5.99F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(173, 53).addBox(13.99F, -12.0F, 12.99F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(176, 53).addBox(13.99F, -12.0F, -5.99F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(6, 165).addBox(-14.0F, -12.0F, -6.001F, 28.0F, 8.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(0, 228).addBox(-19.0F, -16.0F, -8.0F, 38.0F, 4.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -8.0F));

        PartDefinition cube5_r1 = table.addOrReplaceChild("cube5_r1", CubeListBuilder.create().texOffs(71, 72).addBox(-1.0F, -9.0F, -9.0001F, 2.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 71).addBox(-1.0F, -9.0F, -18.9999F, 2.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, -4.0F, 13.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube8_r1 = table.addOrReplaceChild("cube8_r1", CubeListBuilder.create().texOffs(0, 46).addBox(-1.0F, -9.0F, -15.0001F, 2.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(40, 40).addBox(-1.0F, -9.0F, -25.0F, 2.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, -4.0F, 19.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition misc = partDef.addOrReplaceChild("misc", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 8.0F, -4.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition hammer = misc.addOrReplaceChild("hammer", CubeListBuilder.create().texOffs(115, 6).addBox(-3.0F, -3.0F, -2.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(122, 46).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, 0.0F, 5.0F, -0.1745F, -0.8308F, 0.0F));

        PartDefinition chissel = misc.addOrReplaceChild("chissel", CubeListBuilder.create().texOffs(145, 67).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(116, 7).addBox(-1.5F, -1.0F, -4.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 0.0F, 5.0F, 0.0F, 0.3491F, 0.0F));

        PartDefinition paper = misc.addOrReplaceChild("paper", CubeListBuilder.create().texOffs(155, 29).addBox(-0.5365F, -0.01F, -7.3187F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

        PartDefinition cube17_r1 = paper.addOrReplaceChild("cube17_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5365F, 0.3051F, -5.0868F, 5.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.7581F, -4.3137F, -0.3491F, 0.0F, 0.0F));

        PartDefinition patterntiles = misc.addOrReplaceChild("patterntiles", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, -4.0F));

        PartDefinition cube13_r1 = patterntiles.addOrReplaceChild("cube13_r1", CubeListBuilder.create().texOffs(240, 248).addBox(-3.0F, -7.0F, 2.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, -16.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube12_r1 = patterntiles.addOrReplaceChild("cube12_r1", CubeListBuilder.create().texOffs(240, 248).addBox(-4.0F, -6.1F, -0.25F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -16.0F, 2.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition cube14_r1 = patterntiles.addOrReplaceChild("cube14_r1", CubeListBuilder.create().texOffs(240, 248).addBox(-3.0F, -7.0F, 2.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -14.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        return LayerDefinition.create(meshDef, 256, 256);
    }

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
