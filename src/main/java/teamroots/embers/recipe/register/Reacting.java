package teamroots.embers.recipe.register;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import teamroots.embers.recipe.FluidReactionRecipe;
import teamroots.embers.register.FluidRegister;

import java.awt.*;

import static teamroots.embers.recipe.RecipeRegistry.fluidReactionRecipes;

public class Reacting implements IRegister {
    public static final Reacting INSTANCE = new Reacting();

    private Reacting() {
    }

    @Override
    public void execute() {
        fluidReactionRecipes.add(new FluidReactionRecipe(new FluidStack(FluidRegister.FLUID_STEAM, 5), new FluidStack(FluidRegistry.WATER, 1), new Color(255, 255, 255)));
        fluidReactionRecipes.add(new FluidReactionRecipe(new FluidStack(FluidRegister.FLUID_GAS, 1), new FluidStack(FluidRegister.FLUID_STEAM, 5), new Color(128, 192, 255)));

    }
}
