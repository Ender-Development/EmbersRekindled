package teamroots.embers.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import teamroots.embers.Embers;

import java.awt.*;

public class FluidMoltenBronze extends Fluid {
	public FluidMoltenBronze() {
		super("bronze",new ResourceLocation(Embers.MODID,"blocks/molten_bronze_still"),new ResourceLocation(Embers.MODID,"blocks/molten_bronze_flowing"));
		setViscosity(6000);
		setDensity(2000);
		setLuminosity(15);
		setTemperature(900);
		setUnlocalizedName("bronze");
	}
	
	@Override
	public int getColor(){
		return Color.WHITE.getRGB();
	}
}
