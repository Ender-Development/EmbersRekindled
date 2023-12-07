package teamroots.embers.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import teamroots.embers.Embers;

import java.awt.*;

public class FluidMoltenElectrum extends Fluid {
	public FluidMoltenElectrum() {
		super("electrum",new ResourceLocation(Embers.MODID,"blocks/molten_electrum_still"),new ResourceLocation(Embers.MODID,"blocks/molten_electrum_flowing"));
		setViscosity(6000);
		setDensity(2000);
		setLuminosity(15);
		setTemperature(900);
		setUnlocalizedName("electrum");
	}
	
	@Override
	public int getColor(){
		return Color.WHITE.getRGB();
	}
}
