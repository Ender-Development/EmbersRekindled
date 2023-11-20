package p455w0rd.embersified.blocks.tiles;

import java.util.Random;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import p455w0rd.embersified.blocks.BlockEmitter;
import p455w0rd.embersified.init.ModConfig.Options;
import teamroots.embers.api.capabilities.EmbersCapabilities;
import teamroots.embers.api.power.IEmberCapability;
import teamroots.embers.api.power.IEmberPacketReceiver;
import teamroots.embers.entity.EntityEmberPacket;
import teamroots.embers.power.DefaultEmberCapability;
import teamroots.embers.tileentity.ITileEntityBase;
import teamroots.embers.util.Misc;

/**
 * @author p455w0rd
 *
 */
public class TileReceptor extends TileEntity implements ITileEntityBase, ITickable, IEmberPacketReceiver {

	public static final int TRANSFER_RATE = 10;
	public IEmberCapability embersCap = new DefaultEmberCapability();
	Random random = new Random();
	long ticksExisted = 0;

	public TileReceptor() {
		embersCap.setEmberCapacity(2000.0);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		embersCap.writeToNBT(tag);
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
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
		++ticksExisted;
		BlockPos pos = getPos();
		IBlockState state = getWorld().getBlockState(pos);
		EnumFacing facing = state.getValue(BlockEmitter.FACING);
		TileEntity attachedTile = getWorld().getTileEntity(pos.offset(facing.getOpposite()));
		if (ticksExisted % 2 == 0 && attachedTile != null && embersCap.getEmber() > 0.0) {
			//
			if (attachedTile.hasCapability(EmbersCapabilities.EMBER_CAPABILITY, facing) && attachedTile.getCapability(EmbersCapabilities.EMBER_CAPABILITY, facing) != null) {
				IEmberCapability cap = attachedTile.getCapability(EmbersCapabilities.EMBER_CAPABILITY, facing);
				if (cap.getEmber() < cap.getEmberCapacity()) {
					double added = cap.addAmount(Math.min(TRANSFER_RATE, embersCap.getEmber()), true);
					embersCap.removeAmount(added, true);
					if (!getWorld().isRemote) {
						attachedTile.markDirty();
					}
				}
			}
			else if (Options.embersEnergyCanGenerateForgeEnergy && attachedTile.hasCapability(CapabilityEnergy.ENERGY, facing) && attachedTile.getCapability(CapabilityEnergy.ENERGY, facing) != null) {
				IEnergyStorage cap = attachedTile.getCapability(CapabilityEnergy.ENERGY, facing);
				if (cap.canReceive() && cap.getEnergyStored() < cap.getMaxEnergyStored()) {
					int added = cap.receiveEnergy((int) Math.min(TRANSFER_RATE * Options.mulitiplier, embersCap.getEmber() * Options.mulitiplier), true);
					if (added > 0) {
						cap.receiveEnergy((int) Math.min(TRANSFER_RATE * Options.mulitiplier, embersCap.getEmber() * Options.mulitiplier), false);
						embersCap.removeAmount(added / Options.mulitiplier, true);
					}
					if (!getWorld().isRemote) {
						attachedTile.markDirty();
					}
				}
			}
		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == EmbersCapabilities.EMBER_CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == EmbersCapabilities.EMBER_CAPABILITY) {
			return EmbersCapabilities.EMBER_CAPABILITY.cast(embersCap);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean isFull() {
		return embersCap.getEmber() >= embersCap.getEmberCapacity();
	}

	@Override
	public boolean onReceive(EntityEmberPacket packet) {
		return true;
	}

	@Override
	public void markDirty() {
		super.markDirty();
		Misc.syncTE(this);
	}
}