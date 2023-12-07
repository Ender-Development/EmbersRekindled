package teamroots.embers.register;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import teamroots.embers.fluid.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FluidRegister implements IRegistrable<Fluid> {

    public static final FluidRegister INSTANCE = new FluidRegister();

    private FluidRegister(){}

    public final static Fluid FLUID_STEAM = new FluidSteam();
    public final static Fluid FLUID_CRUDE_OIL = new FluidMoltenMetal("oil_soul", "oil_soul");
    public final static Fluid FLUID_OIL = new FluidOilDwarven();
    public final static Fluid FLUID_GAS = new FluidGas();
    public final static Fluid FLUID_MOLTEN_IRON = new FluidMoltenIron();
    public final static Fluid FLUID_MOLTEN_GOLD = new FluidMoltenGold();
    public final static Fluid FLUID_MOLTEN_LEAD = new FluidMoltenLead();
    public final static Fluid FLUID_MOLTEN_COPPER = new FluidMoltenCopper();
    public final static Fluid FLUID_MOLTEN_SILVER = new FluidMoltenSilver();
    public final static Fluid FLUID_MOLTEN_DAWNSTONE = new FluidMoltenDawnstone();
    public final static Fluid FLUID_MOLTEN_TIN = new FluidMoltenTin();
    public final static Fluid FLUID_MOLTEN_ALUMINUM = new FluidMoltenAluminum();
    public final static Fluid FLUID_MOLTEN_NICKEL = new FluidMoltenNickel();
    public final static Fluid FLUID_MOLTEN_BRONZE = new FluidMoltenBronze();
    public final static Fluid FLUID_MOLTEN_ELECTRUM = new FluidMoltenElectrum();
    public final static Fluid FLUID_ALCHEMICAL_REDSTONE = new FluidMoltenMetal("alchemical_redstone", "alchemic_slurry");

    private final static List<Fluid> REGISTRY = new ArrayList<>(Arrays.asList(
            FLUID_STEAM,
            FLUID_CRUDE_OIL,
            FLUID_OIL,
            FLUID_GAS,
            FLUID_MOLTEN_IRON,
            FLUID_MOLTEN_GOLD,
            FLUID_MOLTEN_LEAD,
            FLUID_MOLTEN_COPPER,
            FLUID_MOLTEN_SILVER,
            FLUID_MOLTEN_DAWNSTONE,
            FLUID_MOLTEN_TIN,
            FLUID_MOLTEN_ALUMINUM,
            FLUID_MOLTEN_NICKEL,
            FLUID_MOLTEN_BRONZE,
            FLUID_MOLTEN_ELECTRUM,
            FLUID_ALCHEMICAL_REDSTONE
    ));

    @Override
    public Collection<Fluid> load() {
        return REGISTRY;
    }

    @Override
    public void register() {
        for (Fluid f : INSTANCE.load()) {
            FluidRegistry.registerFluid(f);
            FluidRegistry.addBucketForFluid(f);
        }
    }
}
