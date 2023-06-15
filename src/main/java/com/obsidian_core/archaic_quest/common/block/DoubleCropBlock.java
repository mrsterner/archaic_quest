package com.obsidian_core.archaic_quest.common.block;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Random;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ActionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.world.BlockGetter;
import net.minecraft.world.world.ItemLike;
import net.minecraft.world.world.World;
import net.minecraft.world.world.WorldReader;
import net.minecraft.world.world.block.Block;
import net.minecraft.world.world.block.Blocks;
import net.minecraft.world.world.block.CropBlock;
import net.minecraft.world.world.block.SoundType;
import net.minecraft.world.world.block.state.BlockBehaviour;
import net.minecraft.world.world.block.state.BlockState;
import net.minecraft.world.world.block.state.StateDefinition;
import net.minecraft.world.world.block.state.properties.BooleanProperty;
import net.minecraft.world.world.block.state.properties.IntegerProperty;
import net.minecraft.world.world.material.Material;
import net.minecraft.world.world.material.MaterialColor;
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

    protected static final BlockBehaviour.Settings DEFAULT_PROPS = BlockBehaviour.Settings.of(Material.PLANT, MaterialColor.COLOR_GREEN).instabreak().noCollission().nonOpaque().sound(SoundType.GRASS);
    public static final BooleanProperty IS_TOP = BooleanProperty.create("top");

    public static final IntegerProperty AGE_4 = IntegerProperty.create("age", 0, 4);

    private final Supplier<ItemLike> seed;


    public DoubleCropBlock(Settings properties, @Nonnull Supplier<ItemLike> seed) {
        super(properties);
        this.seed = seed;
        setDefaultState(getDefaultState().with(IS_TOP, false));

        if (getShapes().getFirst().length - 1 != getMaxAge() || getShapes().getSecond().length - 1 != getMaxAge())
            throw new IllegalArgumentException("Tried constructing a double crop with inconsistent voxel shape array sizes. Hopefully this didn't make it into a release.");

        if (getMaxAge() <= 0) {
            throw new IllegalArgumentException("Tried constructing a double block crop with a max age less or equal to 0. Hopefully this didn't make it into a release.");
        }

        if (getDoublingAge() > getMaxAge())
            throw new IllegalArgumentException("Tried constructing a double block crop with a doubling age greater than max age. Hopefully this didn't make it into a release.");
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
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
        return state.get(IS_TOP);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !isMaxAge(state) && !isTop(state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random randomSource) {
        if (!world.isAreaLoaded(pos, 1)) return;

        if (world.getRawBrightness(pos, 0) >= 9) {
            int age = getAge(state);

            if (age < getMaxAge() && !isTop(state)) {
                float growthSpeed = getGrowthSpeed(this, world, pos);

                if (ForgeHooks.onCropsGrowPre(world, pos, state, randomSource.nextInt((int)(25.0F / growthSpeed) + 1) == 0)) {
                    int newAge = age + 1;
                    world.setBlockState(pos, getStateForAge(newAge), 2);

                    if (newAge >= getDoublingAge()) {
                        world.setBlockState(pos.up(), getStateForAge(newAge).with(IS_TOP, true), 2);
                    }
                    ForgeHooks.onCropsGrowPost(world, pos, state);
                }
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult use(BlockState state, World world, BlockPos pos, PlayerEntity player, InteractionHand hand, BlockHitResult hitResult) {
        if (getAge(state) >= maxAge()) {
            if (isTop(state)) {
                world.setBlockState(pos, getStateForAge(getOnHarvestAge()).with(IS_TOP, true), 2);
                world.setBlockState(pos.down(), getStateForAge(getOnHarvestAge()), 2);
            }
            else {
                world.setBlockState(pos, getStateForAge(getOnHarvestAge()), 2);
                world.setBlockState(pos.up(), getStateForAge(getOnHarvestAge()).with(IS_TOP, true), 2);
            }
            if (!world.isClient()) {
                List<ItemStack> drops = getDrops(getStateForAge(maxAge()), (ServerWorld) world, pos, null);

                for (ItemStack stack : drops) {
                    popResource(world, pos, stack);
                }
            }
            return ActionResult.sidedSuccess(world.isClient());
        }
        return ActionResult.PASS;
    }

    @Override
    public BlockState getStateForAge(int age) {
        return getDefaultState().with(IS_TOP, false).with(getAgeProperty(), age);
    }

    @Override
    public void growCrops(World world, BlockPos pos, BlockState state) {
        int age = this.getAge(state) + getBonemealAgeIncrease(world);
        int maxAge = this.getMaxAge();

        if (age > maxAge) {
            age = maxAge;
        }
        world.setBlockState(pos, getStateForAge(age), 2);

        if (age >= getDoublingAge()) {
            world.setBlockState(pos.up(), getStateForAge(age).with(IS_TOP, true), 2);
        }
    }

    @Override
    protected int getBonemealAgeIncrease(World world) {
        return MathHelper.nextInt(world.random, 2, 3);
    }

    @Override
    public boolean canSurvive(BlockState state, WorldReader world, BlockPos pos) {
        return world.getRawBrightness(pos, 0) >= 8 && validPosition(world, state, pos);
    }

    private boolean validPosition(WorldReader world, BlockState state, BlockPos pos) {
        BlockPos downPos = pos.down();

        if (state.getBlock() == this) { //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            BlockState downState = world.getBlockState(downPos);

            if (isTop(state)) {
                return downState.is(this) && !isTop(downState);
            }
            else {
                if (getAge(state) >= getDoublingAge()) {
                    return mayPlaceOn(downState, world, downPos) && world.getBlockState(pos.up()).is(this);
                }
                return mayPlaceOn(downState, world, downPos);
            }
        }
        return world.getBlockState(downPos).canSustainPlant(world, downPos, Direction.UP, this);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
        return state.is(Blocks.FARMLAND) && pos.up(2).getY() <= world.getMaxBuildHeight();
    }

    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof Ravager && ForgeEventFactory.getMobGriefingEvent(world, entity)) {
            world.destroyBlock(pos, true, entity);
        }
        if (state.get(getAgeProperty()) == getMaxAge() && !isTop(state))
            entity.makeStuckInBlock(state, new Vec3(0.95D, 0.95D, 0.95D));
        super.entityInside(state, world, pos, entity);
    }

    @Override
    public ItemLike getBaseSeedId() {
        return seed.get();
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos pos, BlockState state, boolean clientSide) {
        return !this.isMaxAge(state) && !isTop(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(IS_TOP, getAgeProperty());
    }
}
