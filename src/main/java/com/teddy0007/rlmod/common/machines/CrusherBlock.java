package com.teddy0007.rlmod.common.machines;

import java.util.stream.Stream;

import com.teddy0007.rlmod.common.te.CrusherTileEntity;
import com.teddy0007.rlmod.core.init.ModTileEntitiyTypes;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class CrusherBlock extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	private static final VoxelShape SHAPE_N = Stream
			.of(Block.makeCuboidShape(0, 0, 0, 16, 5, 16), Block.makeCuboidShape(2, 10, 2, 14, 14, 13),
					Block.makeCuboidShape(4, 13, 4, 12, 20, 11), Block.makeCuboidShape(0, 16, 0, 16, 21, 16),
					Block.makeCuboidShape(-1, 5, 4, 1, 19, 6), Block.makeCuboidShape(15, 5, 4, 17, 19, 6))
			.reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	private static final VoxelShape SHAPE_E = Stream
			.of(Block.makeCuboidShape(0, 0, 0, 16, 5, 16), Block.makeCuboidShape(3, 10, 2, 14, 14, 14),
					Block.makeCuboidShape(5, 13, 4, 12, 20, 12), Block.makeCuboidShape(0, 16, 0, 16, 21, 16),
					Block.makeCuboidShape(10, 5, -1, 12, 19, 1), Block.makeCuboidShape(10, 5, 15, 12, 19, 17))
			.reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	private static final VoxelShape SHAPE_W = Stream
			.of(Block.makeCuboidShape(0, 0, 0, 16, 5, 16), Block.makeCuboidShape(2, 10, 2, 13, 14, 14),
					Block.makeCuboidShape(4, 13, 4, 11, 20, 12), Block.makeCuboidShape(0, 16, 0, 16, 21, 16),
					Block.makeCuboidShape(4, 5, 15, 6, 19, 17), Block.makeCuboidShape(4, 5, -1, 6, 19, 1))
			.reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	private static final VoxelShape SHAPE_S = Stream
			.of(Block.makeCuboidShape(0, 0, 0, 16, 5, 16), Block.makeCuboidShape(2, 10, 3, 14, 14, 14),
					Block.makeCuboidShape(4, 13, 5, 12, 20, 12), Block.makeCuboidShape(0, 16, 0, 16, 21, 16),
					Block.makeCuboidShape(15, 5, 10, 17, 19, 12), Block.makeCuboidShape(-1, 5, 10, 1, 19, 12))
			.reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

	public CrusherBlock(Properties properties) {
		super(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestTool(ToolType.PICKAXE).harvestLevel(2)
				.setRequiresTool());

		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.get(FACING)) {
		case EAST:
			return SHAPE_E;
		case SOUTH:
			return SHAPE_S;
		case WEST:
			return SHAPE_W;
		default:
			return SHAPE_N;
		
		}
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntitiyTypes.CRUSHER_TILE_ENTITY_TYPE.get().create();
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@Override
	public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
		return state.with(FACING, direction.rotate(state.get(FACING)));
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote()) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof CrusherTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (CrusherTileEntity) te, pos);
			}
		}
		return ActionResultType.SUCCESS;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		worldIn.setBlockState(pos, state.with(BlockStateProperties.POWERED, false));
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}
}
