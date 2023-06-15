package com.obsidian_core.archaic_quest.common.block;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerWorld;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.World;
import net.minecraft.world.level.WorldReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public abstract class DoubleCropBlock extends CropBlock {

    protected static final BlockBehaviour.Properties DEFAULT_PROPS = BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN).instabreak().noCollission().noOcclusion().sound(SoundType.GRASS);
    public static final BooleanProperty IS_TOP = BooleanProperty.create("top");

    public static final IntegerProperty AGE_4 = IntegerProperty.create("age", 0, 4);

    private final Supplier<ItemLike> seed;


    public DoubleCropBlock(Properties properties, @Nonnull Supplier<ItemLike> seed) {
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
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
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

    /**
     * @return The age the crop should revert to
     * when right-click harvested.
     */
    public abstract int getOnHarvestAge();

    public boolean isTop(BlockState state) {
        return state.getValue(IS_TOP);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !isMaxAge(state) && !isTop(state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerWorld level, BlockPos pos, RandomSource randomSource) {
        if (!level.isAreaLoaded(pos, 1)) return;

        if (level.getRawBrightness(pos, 0) >= 9) {
            int age = getAge(state);

            if (age < getMaxAge() && !isTop(state)) {
                float growthSpeed = getGrowthSpeed(this, level, pos);

                if (ForgeHooks.onCropsGrowPre(level, pos, state, randomSource.nextInt((int)(25.0F / growthSpeed) + 1) == 0)) {
                    int newAge = age + 1;
                    level.setBlock(pos, getStateForAge(newAge), 2);

                    if (newAge >= getDoublingAge()) {
                        level.setBlock(pos.above(), getStateForAge(newAge).setValue(IS_TOP, true), 2);
                    }
                    ForgeHooks.onCropsGrowPost(level, pos, state);
                }
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState state, World world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (getAge(state) >= maxAge()) {
            if (isTop(state)) {
                world.setBlock(pos, getStateForAge(getOnHarvestAge()).setValue(IS_TOP, true), 2);
                world.setBlock(pos.below(), getStateForAge(getOnHarvestAge()), 2);
            }
            else {
                world.setBlock(pos, getStateForAge(getOnHarvestAge()), 2);
                world.setBlock(pos.above(), getStateForAge(getOnHarvestAge()).setValue(IS_TOP, true), 2);
            }
            if (!world.isClientSide) {
                List<ItemStack> drops = getDrops(getStateForAge(maxAge()), (ServerWorld) world, pos, null);

                for (ItemStack stack : drops) {
                    popResource(world, pos, stack);
                }
            }
            return InteractionResult.sidedSuccess(world.isClientSide);
        }
        return InteractionResult.PASS;
    }

    @Override
    public BlockState getStateForAge(int age) {
        return defaultBlockState().setValue(IS_TOP, false).setValue(getAgeProperty(), age);
    }

    @Override
    public void growCrops(World level, BlockPos pos, BlockState state) {
        int age = this.getAge(state) + getBonemealAgeIncrease(level);
        int maxAge = this.getMaxAge();

        if (age > maxAge) {
            age = maxAge;
        }
        level.setBlock(pos, getStateForAge(age), 2);

        if (age >= getDoublingAge()) {
            level.setBlock(pos.above(), getStateForAge(age).setValue(IS_TOP, true), 2);
        }
    }

    @Override
    protected int getBonemealAgeIncrease(World level) {
        return Mth.nextInt(level.random, 2, 3);
    }

    @Override
    public boolean canSurvive(BlockState state, WorldReader level, BlockPos pos) {
        return level.getRawBrightness(pos, 0) >= 8 && validPosition(level, state, pos);
    }

    private boolean validPosition(WorldReader level, BlockState state, BlockPos pos) {
        BlockPos belowPos = pos.below();

        if (state.getBlock() == this) { //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            BlockState belowState = level.getBlockState(belowPos);

            if (isTop(state)) {
                return belowState.is(this) && !isTop(belowState);
            }
            else {
                if (getAge(state) >= getDoublingAge()) {
                    return mayPlaceOn(belowState, level, belowPos) && level.getBlockState(pos.above()).is(this);
                }
                return mayPlaceOn(belowState, level, belowPos);
            }
        }
        return level.getBlockState(belowPos).canSustainPlant(level, belowPos, Direction.UP, this);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Blocks.FARMLAND) && pos.above(2).getY() <= level.getMaxBuildHeight();
    }

    @Override
    public void entityInside(BlockState state, World level, BlockPos pos, Entity entity) {
        if (entity instanceof Ravager && ForgeEventFactory.getMobGriefingEvent(level, entity)) {
            level.destroyBlock(pos, true, entity);
        }
        if (state.getValue(getAgeProperty()) == getMaxAge() && !isTop(state))
            entity.makeStuckInBlock(state, new Vec3(0.95D, 0.95D, 0.95D));
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public ItemLike getBaseSeedId() {
        return seed.get();
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean clientSide) {
        return !this.isMaxAge(state) && !isTop(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(IS_TOP, getAgeProperty());
    }
}
