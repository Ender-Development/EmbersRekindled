package teamroots.embers.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.tileentity.TileEntityArchaicGeysir;

public class BlockArchaicGeysir extends BlockTEBase {

	public BlockArchaicGeysir(Material material, String name, boolean addToTab) {
		super(material, name, addToTab);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityArchaicGeysir();
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos){
		if (isReplaceable(world,pos.east())
				&& isReplaceable(world,pos.west())
				&& isReplaceable(world,pos.north())
				&& isReplaceable(world,pos.south())
				&& isReplaceable(world,pos.east().north())
				&& isReplaceable(world,pos.east().south())
				&& isReplaceable(world,pos.west().north())
				&& isReplaceable(world,pos.west().south())){
			return super.canPlaceBlockAt(world,pos);
		}
		return false;
	}

	private boolean isReplaceable(World world, BlockPos pos) {
		return world.getBlockState(pos).getBlock().isReplaceable(world, pos);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state){
		world.setBlockState(pos.north(), BlockRegister.ARCHAIC_EDGE.getStateFromMeta(0));
		world.setBlockState(pos.north().west(), BlockRegister.ARCHAIC_EDGE.getStateFromMeta(1));
		world.setBlockState(pos.west(), BlockRegister.ARCHAIC_EDGE.getStateFromMeta(2));
		world.setBlockState(pos.south().west(), BlockRegister.ARCHAIC_EDGE.getStateFromMeta(3));
		world.setBlockState(pos.south(), BlockRegister.ARCHAIC_EDGE.getStateFromMeta(4));
		world.setBlockState(pos.south().east(), BlockRegister.ARCHAIC_EDGE.getStateFromMeta(5));
		world.setBlockState(pos.east(), BlockRegister.ARCHAIC_EDGE.getStateFromMeta(6));
		world.setBlockState(pos.north().east(), BlockRegister.ARCHAIC_EDGE.getStateFromMeta(7));
	}
}
