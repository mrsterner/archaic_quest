package com.obsidian_core.archaic_quest.common.block;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public abstract class DoubleCropBlock extends CropBlock {

    protected static final Settings DEFAULT_PROPS = Settings.of(Material.PLANT, MapColor.GREEN).breakInstantly().noCollision().nonOpaque().sounds(BlockSoundGroup.GRASS);
    public static final BooleanProperty IS_TOP = BooleanProperty.of("top");

    public static final IntProperty AGE_4 = IntProperty.of("age", 0, 4);

    private final ItemConvertible seed;


    public DoubleCropBlock(Settings properties, @NotNull ItemConvertible seed) {
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
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape[] shapes = isTop(state) ? getShapes().getSecond() : getShapes().getFirst();
        return shapes[getAge(state)];
    }

    /**
     * @return A Pair of VoxelShape arrays. The first array is used for the lower block,
     * the second array is used for the top block. Both arrays must be the same size as
     * the crop's max age.
     */
    public abstract @NotNull Pair<VoxelShape[], VoxelShape[]> getShapes();

    /** @return The crop age for when the crop should grow its top block. */
    public abstract int getDoublingAge();

    public abstract int maxAge();

    public int getMaxAge() {
        return maxAge();
    }

    public abstract IntProperty ageProperty();

    @Override
    public final IntProperty getAgeProperty() {
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
    public boolean hasRandomTicks(BlockState state) {
        return !isMature(state) && !isTop(state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random randomSource) {
        if (!world.isAreaLoaded(pos, 1)) return;

        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int age = getAge(state);

            if (age < getMaxAge() && !isTop(state)) {
                float growthSpeed = getAvailableMoisture(this, world, pos);

                if (ForgeHooks.onCropsGrowPre(world, pos, state, randomSource.nextInt((int)(25.0F / growthSpeed) + 1) == 0)) {
                    int newAge = age + 1;
                    world.setBlockState(pos, withAge(newAge), 2);

                    if (newAge >= getDoublingAge()) {
                        world.setBlockState(pos.up(), withAge(newAge).with(IS_TOP, true), 2);
                    }
                    ForgeHooks.onCropsGrowPost(world, pos, state);
                }
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        if (getAge(state) >= maxAge()) {
            if (isTop(state)) {
                world.setBlockState(pos, withAge(getOnHarvestAge()).with(IS_TOP, true), 2);
                world.setBlockState(pos.down(), withAge(getOnHarvestAge()), 2);
            }
            else {
                world.setBlockState(pos, withAge(getOnHarvestAge()), 2);
                world.setBlockState(pos.up(), withAge(getOnHarvestAge()).with(IS_TOP, true), 2);
            }
            if (!world.isClient()) {
                List<ItemStack> drops = Block.getDroppedStacks(withAge(maxAge()), (ServerWorld) world, pos, null);

                for (ItemStack stack : drops) {
                    Block.dropStack(world, pos, stack);
                }
            }
            return ActionResult.success(world.isClient());
        }
        return ActionResult.PASS;
    }

    @Override
    public BlockState withAge(int age) {
        return getDefaultState().with(IS_TOP, false).with(getAgeProperty(), age);
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int age = this.getAge(state) + getGrowthAmount(world);
        int maxAge = this.getMaxAge();

        if (age > maxAge) {
            age = maxAge;
        }
        world.setBlockState(pos, withAge(age), 2);

        if (age >= getDoublingAge()) {
            world.setBlockState(pos.up(), withAge(age).with(IS_TOP, true), 2);
        }
    }

    @Override
    protected int getGrowthAmount(World world) {
        return MathHelper.nextInt(world.random, 2, 3);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBaseLightLevel(pos, 0) >= 8 && validPosition(world, state, pos);
    }

    private boolean validPosition(WorldView world, BlockState state, BlockPos pos) {
        BlockPos downPos = pos.down();

        if (state.getBlock() == this) { //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            BlockState downState = world.getBlockState(downPos);

            if (isTop(state)) {
                return downState.isOf(this) && !isTop(downState);
            }
            else {
                if (getAge(state) >= getDoublingAge()) {
                    return canPlantOnTop(downState, world, downPos) && world.getBlockState(pos.up()).isOf(this);
                }
                return canPlantOnTop(downState, world, downPos);
            }
        }
        return world.getBlockState(downPos).canSustainPlant(world, downPos, Direction.UP, this);
    }

    @Override
    protected boolean canPlantOnTop(BlockState state, BlockView world, BlockPos pos) {
        return state.isOf(Blocks.FARMLAND) && pos.up(2).getY() <= world.getTopY();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof RavagerEntity && ForgeEventFactory.getMobGriefingEvent(world, entity)) {
            world.breakBlock(pos, true, entity);
        }
        if (state.get(getAgeProperty()) == getMaxAge() && !isTop(state)) {
            entity.slowMovement(state, new Vec3d(0.95D, 0.95D, 0.95D));
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public ItemConvertible getSeedsItem() {
        return seed;
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean clientSide) {
        return !this.isMature(state) && !isTop(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(IS_TOP, getAgeProperty());
    }
}
