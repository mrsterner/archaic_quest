package com.obsidian_core.archaic_quest.common.worldgen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.obsidian_core.archaic_quest.common.core.register.AQStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.world.ChunkPos;
import net.minecraft.world.world.worldgen.Heightmap;
import net.minecraft.world.world.worldgen.heightproviders.HeightProvider;
import net.minecraft.world.world.worldgen.structure.Structure;
import net.minecraft.world.world.worldgen.structure.StructureType;
import net.minecraft.world.world.worldgen.structure.pools.JigsawPlacement;
import net.minecraft.world.world.worldgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

public class AztecVillageStructure extends Structure {

    public static final Codec<AztecVillageStructure> CODEC = RecordCodecBuilder.<AztecVillageStructure>mapCodec(instance ->
            instance.group(AztecVillageStructure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, AztecVillageStructure::new)).codec();

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<Identifier> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int maxDistanceFromCenter;


    public AztecVillageStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<Identifier> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter) {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }


    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
        int startY = 0;

        // Turns the chunk coordinates into actual coordinates we can use. (Gets corner of that chunk)
        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), startY, chunkPos.getMinBlockZ());


        Optional<Structure.GenerationStub> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        context,
                        startPool,
                        startJigsawName,
                        size,
                        blockPos,
                        false,
                        projectStartToHeightmap,
                        maxDistanceFromCenter);

        return structurePiecesGenerator;
    }

    @Override
    public StructureType<?> type() {
        return AQStructures.AZTEC_VILLAGE.get();
    }
}
