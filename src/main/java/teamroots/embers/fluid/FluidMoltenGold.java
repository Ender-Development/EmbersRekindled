package teamroots.embers.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import teamroots.embers.Embers;

import java.awt.*;

public class FluidMoltenGold extends Fluid {
	public FluidMoltenGold() {
		super("gold",new ResourceLocation(Embers.MODID,"blocks/molten_gold_still"),new ResourceLocation(Embers.MODID,"blocks/molten_gold_flowing"));
		setViscosity(6000);
		setDensity(2000);
		setLuminosity(15);
		setTemperature(900);
		setUnlocalizedName("gold");
	}
	
	@Override
	public int getColor(){
		return Color.WHITE.getRGB();
	}
}
