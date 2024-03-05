package teamroots.embers.recipe.register;

import net.minecraftforge.fluids.FluidStack;
import teamroots.embers.config.ConfigMaterial;
import teamroots.embers.recipe.FluidMixingRecipe;
import teamroots.embers.register.FluidRegister;

import static teamroots.embers.recipe.RecipeRegistry.mixingRecipes;

public class Mixing implements IRegister {
    public static final Mixing INSTANCE = new Mixing();

    private Mixing() {
    }

    @Override
    public void execute() {

        mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, 4), new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, 4)}, new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, 8)));
        if (ConfigMaterial.ELECTRUM.mustLoad()) {
            mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, 4), new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, 4)}, new FluidStack(FluidRegister.FLUID_MOLTEN_ELECTRUM, 8)));
        }
        if (ConfigMaterial.TIN.mustLoad() && ConfigMaterial.BRONZE.mustLoad()) {
            mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, 6), new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, 2)}, new FluidStack(FluidRegister.FLUID_MOLTEN_BRONZE, 8)));
        }
        mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(FluidRegister.FLUID_CRUDE_OIL, 5), new FluidStack(FluidRegister.FLUID_STEAM, 20)}, new FluidStack(FluidRegister.FLUID_OIL, 10), 0));
        mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(FluidRegister.FLUID_CRUDE_OIL, 10), new FluidStack(FluidRegister.FLUID_GAS, 5)}, new FluidStack(FluidRegister.FLUID_OIL, 30), 0));

    }
}
