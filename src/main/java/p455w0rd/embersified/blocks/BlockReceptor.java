package p455w0rd.embersified.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import p455w0rd.embersified.blocks.tiles.TileReceptor;
import teamroots.embers.block.BlockTEBase;

/**
 * @author p455w0rd
 *
 */
public class BlockReceptor extends BlockTEBase {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public BlockReceptor() {
		super(Material.ROCK, "ember_rf_receiver", true);
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer((Block) this, FACING);
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
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileReceptor();
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (world.isAirBlock(pos.offset(state.getValue(FACING), -1))) {
			world.setBlockToAir(pos);
			dropBlockAsItem(world, pos, state, 0);
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (state.getValue(FACING)) {
		case UP: {
			return new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.5, 0.75);
		}
		case DOWN: {
			return new AxisAlignedBB(0.25, 0.5, 0.25, 0.75, 1.0, 0.75);
		}
		case NORTH: {
			return new AxisAlignedBB(0.25, 0.25, 0.5, 0.75, 0.75, 1.0);
		}
		case SOUTH: {
			return new AxisAlignedBB(0.25, 0.25, 0.0, 0.75, 0.75, 0.5);
		}
		case WEST: {
			return new AxisAlignedBB(0.5, 0.25, 0.25, 1.0, 0.75, 0.75);
		}
		case EAST: {
			return new AxisAlignedBB(0.0, 0.25, 0.25, 0.5, 0.75, 0.75);
		}
		}
		return new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.5, 0.75);
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
	}

	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
