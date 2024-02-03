package p455w0rd.embersified.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import p455w0rd.embersified.blocks.tiles.TileEmitter;
import teamroots.embers.block.BlockTEBase;

/**
 * @author p455w0rd
 *
 */
public class BlockEmitter extends BlockTEBase {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public BlockEmitter() {
		super(Material.ROCK, "ember_rf_emitter", true);
	}

	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				FACING
		});
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return side != state.getValue(FACING);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEmitter();
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (world.getTileEntity(pos) instanceof TileEmitter) {
			((TileEmitter) world.getTileEntity(pos)).updateNeighbors(world);
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		TileEntity t = world.getTileEntity(pos);
		if (t instanceof TileEmitter) {
			((TileEmitter) t).updateNeighbors(world);
			t.markDirty();
		}
		if (world.isAirBlock(pos.offset(state.getValue(FACING), -1))) {
			world.setBlockToAir(pos);
			dropBlockAsItem(world, pos, state, 0);
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (state.getValue(FACING)) {
		case UP: {
			return new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.9375, 0.75);
		}
		case DOWN: {
			return new AxisAlignedBB(0.25, 0.0625, 0.25, 0.75, 1.0, 0.75);
		}
		case NORTH: {
			return new AxisAlignedBB(0.25, 0.25, 0.0625, 0.75, 0.75, 1.0);
		}
		case SOUTH: {
			return new AxisAlignedBB(0.25, 0.25, 0.0, 0.75, 0.75, 0.9375);
		}
		case WEST: {
			return new AxisAlignedBB(0.0625, 0.25, 0.25, 1.0, 0.75, 0.75);
		}
		case EAST: {
			return new AxisAlignedBB(0.0, 0.25, 0.25, 0.9375, 0.75, 0.75);
		}
		}
		return new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.9375, 0.75);
	}

	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
