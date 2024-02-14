package teamroots.embers.recipe.register;

import teamroots.embers.recipe.*;

import static teamroots.embers.recipe.RecipeRegistry.dawnstoneAnvilRecipes;

public class Smithing implements IRegister {
    public static final Smithing INSTANCE = new Smithing();

    private Smithing() {
    }

    @Override
    public void execute() {
        dawnstoneAnvilRecipes.add(new AnvilAddCoreRecipe());
        dawnstoneAnvilRecipes.add(new AnvilAddModifierRecipe());
        dawnstoneAnvilRecipes.add(new AnvilRemoveModifierRecipe());
        dawnstoneAnvilRecipes.add(new AnvilRepairRecipe()); //Repair with repair item
        dawnstoneAnvilRecipes.add(new AnvilRepairMateriaRecipe()); //Repair with Isolated Materia
        dawnstoneAnvilRecipes.add(new AnvilBreakdownRecipe()); //BREAKDOWN BREAKDOWN
        dawnstoneAnvilRecipes.add(new RecipeRegistry.CreativeHeatRecipe()); //Creative Heat
    }
}
