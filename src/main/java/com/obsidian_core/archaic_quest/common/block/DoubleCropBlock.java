package com.obsidian_core.archaic_quest.common.block;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Supplier;

public abstract class DoubleCropBlock extends CropsBlock {

    protected static final AbstractBlock.Properties DEFAULT_PROPS = AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN).instabreak().noCollission().noOcclusion().sound(SoundType.GRASS);
    public static final IntegerProperty AGE_4 = IntegerProperty.create("age", 0, 4);
    public static final BooleanProperty IS_TOP = BooleanProperty.create("is_top");

    private final Supplier<IItemProvider> seed;


    public DoubleCropBlock(Properties properties, @Nonnull Supplier<IItemProvider> seed) {
        super(properties);
        this.seed = seed;
        registerDefaultState(stateDefinition.any().setValue(IS_TOP, false));

        if (getShapes().getFirst().length - 1 != getMaxAge() || getShapes().getSecond().length - 1 != getMaxAge())
            throw new IllegalArgumentException("Tried constructing a double crop with inconsistent voxel shape array sizes. Hopefully this didn't make it into a release.");

        if (getMaxAge() <= 0) {
            throw new IllegalArgumentException("Tried constructing a double block crop with a max age less or equal to 0. Hopefully this didn't make it into a release.");
        }

        if (getDoublingAge() > getMaxAge())
            throw new IllegalArgumentException("Tried constructing a double block crop with a doubling age greater than max age. Hopefully this didn't make it into a release.");
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        VoxelShape[] shapes = isTop(state) ? getShapes().getSecond() : getShapes().getFirst();
        return shapes[getAge(state)];
    }

    /**
     *  @return A Pair of VoxelShape arrays. The first array is used for the lower block,
     * the second array is used for the top block. Both arrays must be the same size as
     * the crop's max age.
     */
    @Nonnull
    public abstract Pair<VoxelShape[], VoxelShape[]> getShapes();

    /** @return The crop age for when the crop should grow its top block. */
    public abstract int getDoublingAge();

    public abstract int maxAge();

    public int getMaxAge() {
        return maxAge();
    }

    public abstract IntegerProperty ageProperty();

    @Override
    public final IntegerProperty getAgeProperty() {
        return ageProperty();
    }

    public boolean isTop(BlockState state) {
        return state.getValue(IS_TOP);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !isMaxAge(state) && !isTop(state);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isAreaLoaded(pos, 1)) return;

        if (world.getRawBrightness(pos, 0) >= 9) {
            int age = getAge(state);

            if (age < getMaxAge()) {
                float growthSpeed = getGrowthSpeed(this, world, pos);

                if (ForgeHooks.onCropsGrowPre(world, pos, state, random.nextInt((int)(25.0F / growthSpeed) + 1) == 0)) {
                    world.setBlock(pos, getStateForAge(age + 1), 2);
                    ForgeHooks.onCropsGrowPost(world, pos, state);
                }
            }
        }
    }

    public void growCrops(World world, BlockPos pos, BlockState state) {
        int age = this.getAge(state) + getBonemealAgeIncrease(world);
        int maxAge = this.getMaxAge();

        if (age > maxAge) {
            age = maxAge;
        }

        world.setBlock(pos, getStateForAge(age), 2);
    }

    protected int getBonemealAgeIncrease(World world) {
        return MathHelper.nextInt(world.random, 2, 3);
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        return world.getRawBrightness(pos, 0) >= 8 && validPosition(world, state, pos);
    }

    private boolean validPosition(IWorldReader world, BlockState state, BlockPos pos) {
        BlockPos belowPos = pos.below();
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return world.getBlockState(belowPos).canSustainPlant(world, belowPos, Direction.UP, this);
        return mayPlaceOn(world.getBlockState(belowPos), world, belowPos);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        return isTop(world.getBlockState(pos.above()))
                ? state.is(this) && !isTop(state)
                : state.is(Blocks.FARMLAND) && pos.above(2).getY() <= world.getMaxBuildHeight();
    }

    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof RavagerEntity && ForgeEventFactory.getMobGriefingEvent(world, entity)) {
            world.destroyBlock(pos, true, entity);
        }
        else {
            if (state.getValue(getAgeProperty()) == getMaxAge() && !isTop(state))
                entity.getDeltaMovement().multiply(0.8D, 0.8D, 0.8D);
        }
        super.entityInside(state, world, pos, entity);
    }

    @Override
    protected IItemProvider getBaseSeedId() {
        return seed.get();
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader world, BlockPos pos, BlockState state, boolean clientSide) {
        return !this.isMaxAge(state) && !isTop(state);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(IS_TOP).add(getAgeProperty());
    }
}
