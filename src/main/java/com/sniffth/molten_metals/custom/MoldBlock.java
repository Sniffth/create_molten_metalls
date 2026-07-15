package com.sniffth.molten_metals.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MoldBlock extends Block {
    protected static final VoxelShape FACING_X = Block.box(5, 0, 3, 11, 4, 13);
    protected static final VoxelShape FACING_Z = Block.box(3, 0, 5, 13, 4, 11);

    public MoldBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(BlockStateProperties.FACING);
        return direction.getAxis() == Direction.Axis.X ? FACING_X : FACING_Z;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockState = this.defaultBlockState();
        return blockState.setValue(BlockStateProperties.FACING, context.getHorizontalDirection());
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canAttach(level, pos, Direction.DOWN);
    }

    public static boolean canAttach(LevelReader reader, BlockPos pos, Direction direction) {
        BlockPos supportPos = pos.relative(direction);
        return reader.getBlockState(supportPos).isFaceSturdy(reader, supportPos, direction.getOpposite());
    }
}
