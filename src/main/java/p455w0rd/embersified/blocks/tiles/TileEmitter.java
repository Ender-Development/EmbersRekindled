package p455w0rd.embersified.blocks.tiles;

import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import p455w0rd.embersified.init.ModConfig.Options;
import teamroots.embers.SoundManager;
import teamroots.embers.api.capabilities.EmbersCapabilities;
import teamroots.embers.api.power.IEmberCapability;
import teamroots.embers.api.power.IEmberPacketProducer;
import teamroots.embers.api.power.IEmberPacketReceiver;
import teamroots.embers.block.BlockEmberPulser;
import teamroots.embers.entity.EntityEmberPacket;
import teamroots.embers.power.DefaultEmberCapability;
import teamroots.embers.tileentity.ITileEntityBase;
import teamroots.embers.util.Misc;

/**
 * @author p455w0rd
 *
 */
public class TileEmitter extends TileEntity implements ITileEntityBase, ITickable, IEmberPacketProducer {

	public static final double TRANSFER_RATE = 40.0;
	public static final double PULL_RATE = 10.0;
	public IEmberCapability embersCap = new DefaultEmberCapability();
	public IEnergyStorage forgeCap;
	public BlockPos target = null;
	public long ticksExisted = 0;
	Random random = new Random();
	int offset = random.nextInt(40);
	public EnumConnection up = EnumConnection.NONE;
	public EnumConnection down = EnumConnection.NONE;
	public EnumConnection north = EnumConnection.NONE;
	public EnumConnection south = EnumConnection.NONE;
	public EnumConnection east = EnumConnection.NONE;
	public EnumConnection west = EnumConnection.NONE;

	public static EnumConnection connectionFromInt(int value) {
		switch (value) {
		case 0: {
			return EnumConnection.NONE;
		}
		case 1: {
			return EnumConnection.LEVER;
		}
		}
		return EnumConnection.NONE;
	}

	public TileEmitter() {
		embersCap.setEmberCapacity(200.0);
		forgeCap = new EnergySettable(this);
	}

	public void updateNeighbors(IBlockAccess world) {
		down = getConnection(world, getPos().down(), EnumFacing.DOWN);
		up = getConnection(world, getPos().up(), EnumFacing.UP);
		north = getConnection(world, getPos().north(), EnumFacing.NORTH);
		south = getConnection(world, getPos().south(), EnumFacing.SOUTH);
		west = getConnection(world, getPos().west(), EnumFacing.WEST);
		east = getConnection(world, getPos().east(), EnumFacing.EAST);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("up", up.ordinal());
		tag.setInteger("down", down.ordinal());
		tag.setInteger("north", north.ordinal());
		tag.setInteger("south", south.ordinal());
		tag.setInteger("west", west.ordinal());
		tag.setInteger("east", east.ordinal());
		if (target != null) {
			tag.setInteger("targetX", target.getX());
			tag.setInteger("targetY", target.getY());
			tag.setInteger("targetZ", target.getZ());
		}
		embersCap.writeToNBT(tag);
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		up = connectionFromInt(tag.getInteger("up"));
		down = connectionFromInt(tag.getInteger("down"));
		north = connectionFromInt(tag.getInteger("north"));
		south = connectionFromInt(tag.getInteger("south"));
		west = connectionFromInt(tag.getInteger("west"));
		east = connectionFromInt(tag.getInteger("east"));
		if (tag.hasKey("targetX")) {
			target = new BlockPos(tag.getInteger("targetX"), tag.getInteger("targetY"), tag.getInteger("targetZ"));
		}
		embersCap.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	public EnumConnection getConnection(IBlockAccess world, BlockPos pos, EnumFacing side) {
		return Misc.isValidLever(world, pos, side) ? EnumConnection.LEVER : EnumConnection.NONE;
	}

	@Override
	public void markDirty() {
		super.markDirty();
		Misc.syncTE(this);
	}

	@Override
	public boolean activate(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		return false;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		invalidate();
		world.setTileEntity(pos, null);
	}

	@Override
	public void update() {
		TileEntity targetTile;
		++ticksExisted;
		IBlockState state = getWorld().getBlockState(getPos());
		EnumFacing facing = state.getValue(BlockEmberPulser.facing);
		TileEntity attachedTile = getWorld().getTileEntity(getPos().offset(facing.getOpposite()));
		if (ticksExisted % 5 == 0 && attachedTile != null && embersCap.getEmber() < embersCap.getEmberCapacity()) {
			if (attachedTile.hasCapability(EmbersCapabilities.EMBER_CAPABILITY, facing)) {
				if (attachedTile.getCapability(EmbersCapabilities.EMBER_CAPABILITY, facing).getEmber() > 0.0) {
					double removed = attachedTile.getCapability(EmbersCapabilities.EMBER_CAPABILITY, facing).removeAmount(10.0, true);
					embersCap.addAmount(removed, true);
					if (!getWorld().isRemote) {
						attachedTile.markDirty();
					}
				}
			}
		}
		if ((ticksExisted + offset) % 20 == 0 && getWorld().isBlockIndirectlyGettingPowered(getPos()) != 0 && target != null && !getWorld().isRemote && embersCap.getEmber() > PULL_RATE && (targetTile = getWorld().getTileEntity(target)) instanceof IEmberPacketReceiver && !((IEmberPacketReceiver) targetTile).isFull()) {
			EntityEmberPacket packet = new EntityEmberPacket(getWorld());
			Vec3d velocity = getBurstVelocity(facing);
			packet.initCustom(getPos(), target, velocity.x, velocity.y, velocity.z, Math.min(40.0, embersCap.getEmber()));
			embersCap.removeAmount(Math.min(40.0, embersCap.getEmber()), true);
			getWorld().spawnEntity(packet);
			getWorld().playSound(null, pos, SoundManager.EMBER_EMIT, SoundCategory.BLOCKS, 1.0f, 1.0f);
		}
	}

	private Vec3d getBurstVelocity(EnumFacing facing) {
		switch (facing) {
		case DOWN: {
			return new Vec3d(0.0, -0.5, 0.0);
		}
		case UP: {
			return new Vec3d(0.0, 0.5, 0.0);
		}
		case NORTH: {
			return new Vec3d(0.0, -0.01, -0.5);
		}
		case SOUTH: {
			return new Vec3d(0.0, -0.01, 0.5);
		}
		case WEST: {
			return new Vec3d(-0.5, -0.01, 0.0);
		}
		case EAST: {
			return new Vec3d(0.5, -0.01, 0.0);
		}
		}
		return Vec3d.ZERO;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == EmbersCapabilities.EMBER_CAPABILITY || (Options.forgeEnergyCanGenerateEmbers && capability == CapabilityEnergy.ENERGY)) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == EmbersCapabilities.EMBER_CAPABILITY) {
			return EmbersCapabilities.EMBER_CAPABILITY.cast(embersCap);
		}
		else if (Options.forgeEnergyCanGenerateEmbers && capability == CapabilityEnergy.ENERGY) {
			return CapabilityEnergy.ENERGY.cast(forgeCap);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void setTargetPosition(BlockPos pos, EnumFacing side) {
		target = pos;
		markDirty();
	}

	public IEmberCapability getEmbersCap() {
		return embersCap;
	}

	public static class EnergySettable implements IEnergyStorage {

		private final IEmberCapability embersCap;
		private final TileEmitter tile;

		public EnergySettable(@Nonnull TileEmitter tile) {
			embersCap = tile.getEmbersCap();
			this.tile = tile;
		}

		@Override
		public int receiveEnergy(int maxReceive, boolean simulate) {
			if (!canReceive()) {
				return 0;
			}
			int amountInEmbersCap = (int) embersCap.getEmber();
			int spaceLeftInEmbersCap = (int) embersCap.getEmberCapacity() - amountInEmbersCap;
			if (spaceLeftInEmbersCap <= 0) {
				return 0;
			}
			else {
				int leftOver = 0;
				double tryToPush = embersCap.addAmount(maxReceive / Options.mulitiplier, false);
				if (tryToPush > spaceLeftInEmbersCap) {
					leftOver = (int) tryToPush - spaceLeftInEmbersCap;
				}
				if (!simulate) {
					embersCap.addAmount(maxReceive / Options.mulitiplier, true);
					tile.markDirty();
					int ret = (int) (maxReceive - (leftOver * Options.mulitiplier));
					return ret;
				}
				return maxReceive;
			}
		}

		@Override
		public int extractEnergy(int maxExtract, boolean simulate) {
			return 0;
		}

		@Override
		public int getEnergyStored() {
			return (int) (embersCap.getEmber() * Options.mulitiplier);
		}

		@Override
		public int getMaxEnergyStored() {
			return (int) (embersCap.getEmberCapacity() * Options.mulitiplier);
		}

		@Override
		public boolean canExtract() {
			return false;
		}

		@Override
		public boolean canReceive() {
			return true;
		}

		public void setEnergy(int amount) {
			embersCap.setEmber(amount / Options.mulitiplier);
		}

	}

	public static enum EnumConnection {

			NONE, LEVER;

		private EnumConnection() {
		}

	}

}