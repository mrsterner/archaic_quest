package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.server.world.ServerWorld;

public class CoolVinesBlock extends Block implements IForgeShearable {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty CUT = BooleanProperty.create("cut");
    public static final BooleanProperty CAN_GROW = BooleanProperty.create("can_grow");

    private static final VoxelShape[] shapes = {
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D),
            Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D),
            Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 1.0D),
            Block.box(15.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 8.0D, 15.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 8.0D, 0.0D, 1.0D, 16.0D, 16.0D),
    };


    public CoolVinesBlock(Settings properties) {
        super(properties);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(CUT, false).with(CAN_GROW, true));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return shapes[state.get(FACING).get2DDataValue() + (state.get(CUT) ? 4 : 0)];
    }

    public boolean isCut(BlockState state) {
        return state.get(CUT);
    }

    public boolean canGrow(BlockState state) {
        return state.get(CAN_GROW);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return canGrow(state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isAreaLoaded(pos, 1)) return;

        if (random.nextInt(10) == 0) {
            BlockPos downPos = pos.down();

            if (downPos.getY() > 0 && world.getBlockState(downPos).isAir()) {
                world.setBlockState(downPos, state, 2);
            }
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction face = context.getClickedFace();
        BlockPos pos = context.getBlockPos();
        BlockPos behindPos = pos.relative(face.getOpposite());
        World world = context.getWorld();

        if (face == Direction.DOWN || face == Direction.UP) {
            face = context.getPlayerFacing().getOpposite();
        }
        BlockState behindState = world.getBlockState(behindPos);
        BlockState upState = world.getBlockState(pos.up());

        if (Block.isFaceFull(world.getBlockState(behindPos).getCollisionShape(world, behindPos), face)
                || behindState.isFaceSturdy(world, behindPos, face)
                || (behindState.getBlock() instanceof SlabBlock && behindState.get(SlabBlock.TPOSITIVE_YE) == SlabType.TOP)
                || (upState.getBlock() instanceof VerticalSlabBlock && upState.get(VerticalSlabBlock.SLAB_STATE).getDirection() == face )
                || upState.isFaceSturdy(world, pos.up(), Direction.DOWN)) {
            return getDefaultState().with(FACING, face);
        }
        return null;
    }

    @Deprecated
    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, WorldReader world, BlockPos pos) {
        Direction face = state.get(FACING);
        BlockPos behindPos = pos.relative(face.getOpposite());
        BlockState behindState = world.getBlockState(behindPos);
        BlockState upState = world.getBlockState(pos.up());

        return Block.isFaceFull(world.getBlockState(behindPos).getCollisionShape(world, behindPos), face)
                || behindState.isFaceSturdy(world, behindPos, face)
                || (behindState.getBlock() instanceof SlabBlock && behindState.get(SlabBlock.TPOSITIVE_YE) == SlabType.TOP)
                || (upState.getBlock() instanceof VerticalSlabBlock && upState.get(VerticalSlabBlock.SLAB_STATE).getDirection() == face )
                || upState.isFaceSturdy(world, pos.up(), Direction.DOWN)
                || (upState.is(this) && upState.get(FACING) == face && !upState.get(CUT));
    }

    @Deprecated
    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState newState, Direction direction, BlockState state, WorldAccessor world, BlockPos pos, BlockPos pos1) {
        return !newState.canSurvive(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.updateShape(newState, direction, state, world, pos, pos1);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, CUT, CAN_GROW);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
}
