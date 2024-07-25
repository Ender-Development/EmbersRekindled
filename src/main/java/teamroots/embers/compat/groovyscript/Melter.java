package teamroots.embers.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyBlacklist;
import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.api.documentation.annotations.*;
import com.cleanroommc.groovyscript.helper.SimpleObjectStream;
import com.cleanroommc.groovyscript.helper.recipe.AbstractRecipeBuilder;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import org.jetbrains.annotations.Nullable;
import teamroots.embers.recipe.ItemMeltingRecipe;
import teamroots.embers.recipe.RecipeRegistry;

import java.util.Arrays;

@RegistryDescription
public class Melter extends VirtualizedRegistry<ItemMeltingRecipe> {

    @RecipeBuilderDescription(example = {
            @Example(".input(item('minecraft:clay')).fluidOutput(fluid('water') * 100)"),
            @Example(".input(item('minecraft:gravel')).fluidOutput(fluid('lava') * 50, fluid('water') * 50)")
    })
    public RecipeBuilder recipeBuilder() {
        return new RecipeBuilder();
    }

    @Override
    @GroovyBlacklist
    public void onReload() {
        RecipeRegistry.meltingRecipes.removeAll(removeScripted());
        RecipeRegistry.meltingRecipes.addAll(restoreFromBackup());
    }

    public void add(ItemMeltingRecipe recipe) {
        if (recipe != null) {
            addScripted(recipe);
            RecipeRegistry.meltingRecipes.add(recipe);
        }
    }

    public boolean remove(ItemMeltingRecipe recipe) {
        if (RecipeRegistry.meltingRecipes.removeIf(r -> r == recipe)) {
            addBackup(recipe);
            return true;
        }
        return false;
    }

    @MethodDescription(example = @Example("item('minecraft:redstone_block')"))
    public boolean removeByInput(IIngredient input) {
        return RecipeRegistry.meltingRecipes.removeIf(r -> {
            if (Arrays.stream(r.getInput().getMatchingStacks()).anyMatch(input)) {
                addBackup(r);
                return true;
            }
            return false;
        });
    }

    @MethodDescription(example = {@Example("fluid('oil_soul')"), @Example(value = "fluid('iron')", commented = true)})
    public boolean removeByOutput(IIngredient output) {
        return RecipeRegistry.meltingRecipes.removeIf(r -> {
            if (output.test(r.getFluid()) || output.test(r.getBonusOutput())) {
                addBackup(r);
                return true;
            }
            return false;
        });
    }

    @MethodDescription(type = MethodDescription.Type.QUERY)
    public SimpleObjectStream<ItemMeltingRecipe> streamRecipes() {
        return new SimpleObjectStream<>(RecipeRegistry.meltingRecipes).setRemover(this::remove);
    }

    @MethodDescription(priority = 2000, example = @Example(commented = true))
    public void removeAll() {
        RecipeRegistry.meltingRecipes.forEach(this::addBackup);
        RecipeRegistry.meltingRecipes.clear();
    }

    @Property(property = "input", valid = @Comp("1"))
    @Property(property = "fluidOutput", valid = {@Comp(value = "1", type = Comp.Type.GTE), @Comp(value = "2", type = Comp.Type.LTE)})
    public static class RecipeBuilder extends AbstractRecipeBuilder<ItemMeltingRecipe> {

        @Override
        public String getErrorMsg() {
            return "Error adding Embers Melter recipe";
        }

        @Override
        public void validate(GroovyLog.Msg msg) {
            validateItems(msg, 1, 1, 0, 0);
            validateFluids(msg, 0, 0, 1, 2);
        }

        @Override
        @RecipeBuilderRegistrationMethod
        public @Nullable ItemMeltingRecipe register() {
            if (!validate()) return null;
            ItemMeltingRecipe recipe = new ItemMeltingRecipe(input.get(0).toMcIngredient(), fluidOutput.get(0));
            if (fluidOutput.size() == 2) recipe.addBonusOutput(fluidOutput.get(1));
            GSPlugin.instance.melter.add(recipe);
            return recipe;
        }
    }
}
