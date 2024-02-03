package teamroots.embers.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import teamroots.embers.Embers;

import java.awt.*;

public class FluidMoltenDawnstone extends Fluid {
	public FluidMoltenDawnstone() {
		super("dawnstone",new ResourceLocation(Embers.MODID,"blocks/molten_dawnstone_still"),new ResourceLocation(Embers.MODID,"blocks/molten_dawnstone_flowing"));
		setViscosity(6000);
		setDensity(2000);
		setLuminosity(15);
		setTemperature(900);
		setUnlocalizedName("dawnstone");
	}
	
	@Override
	public int getColor(){
		return Color.WHITE.getRGB();
	}
}
