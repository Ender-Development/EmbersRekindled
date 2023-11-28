package teamroots.embers.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import teamroots.embers.Embers;
import teamroots.embers.register.BlockRegister;

import java.awt.*;

public class FluidMoltenNickel extends Fluid {
	public FluidMoltenNickel() {
		super("nickel",new ResourceLocation(Embers.MODID,"blocks/molten_nickel_still"),new ResourceLocation(Embers.MODID,"blocks/molten_nickel_flowing"));
		setViscosity(6000);
		setDensity(2000);
		setLuminosity(15);
		setTemperature(900);
		setBlock(BlockRegister.BLOCK_MOLTEN_NICKEL);
		setUnlocalizedName("nickel");
	}
	
	@Override
	public int getColor(){
		return Color.WHITE.getRGB();
	}
}
