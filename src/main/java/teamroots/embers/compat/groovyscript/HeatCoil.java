package teamroots.embers.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyBlacklist;
import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.api.documentation.annotations.*;
import com.cleanroommc.groovyscript.helper.SimpleObjectStream;
import com.cleanroommc.groovyscript.helper.recipe.AbstractRecipeBuilder;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import teamroots.embers.recipe.HeatCoilRecipe;
import teamroots.embers.recipe.RecipeRegistry;

import java.util.Arrays;

public class HeatCoil extends VirtualizedRegistry<HeatCoilRecipe> {
    @RecipeBuilderDescription(example = {
            @Example(".input(item('minecraft:clay')).output(item('minecraft:gravel'))"),
            @Example(".input(item('minecraft:gravel')).output(item('minecraft:grass'))")
    })
    public RecipeBuilder recipeBuilder() {
        return new RecipeBuilder();
    }

    @Override
    @GroovyBlacklist
    public void onReload() {
        RecipeRegistry.heatCoilRecipes.removeAll(removeScripted());
        RecipeRegistry.heatCoilRecipes.addAll(restoreFromBackup());
    }

    public void add(HeatCoilRecipe recipe) {
        if (recipe != null) {
            addScripted(recipe);
            RecipeRegistry.heatCoilRecipes.add(recipe);
        }
    }

    public boolean remove(HeatCoilRecipe recipe) {
        if (RecipeRegistry.heatCoilRecipes.removeIf(r -> r == recipe)) {
            addBackup(recipe);
            return true;
        }
        return false;
    }

    @MethodDescription(example = @Example("item('minecraft:iron_ore')"))
    public boolean removeByInput(IIngredient input) {
        return RecipeRegistry.heatCoilRecipes.removeIf(r -> {
            if (Arrays.stream(r.getInput().getMatchingStacks()).anyMatch(input)) {
                addBackup(r);
                return true;
            }
            return false;
        });
    }

    @MethodDescription(example = {@Example("item('minecraft:iron_ingot')"), @Example(value = "item('minecraft:glass')", commented = true)})
    public boolean removeByOutput(IIngredient output) {
        return RecipeRegistry.heatCoilRecipes.removeIf(r -> {
            if (output.test(r.getOutput())) {
                addBackup(r);
                return true;
            }
            return false;
        });
    }

    @MethodDescription(type = MethodDescription.Type.QUERY)
    public SimpleObjectStream<HeatCoilRecipe> streamRecipes() {
        return new SimpleObjectStream<>(RecipeRegistry.heatCoilRecipes).setRemover(this::remove);
    }

    @MethodDescription(priority = 2000, example = @Example(commented = true))
    public void removeAll() {
        RecipeRegistry.heatCoilRecipes.forEach(this::addBackup);
        RecipeRegistry.heatCoilRecipes.clear();
    }

    @Property(property = "input", valid = @Comp("1"))
    @Property(property = "output", valid = @Comp("1"))
    public static class RecipeBuilder extends AbstractRecipeBuilder<HeatCoilRecipe> {

        @Override
        public String getErrorMsg() {
            return "Error adding Embers Heat Coil recipe";
        }

        @Override
        public void validate(GroovyLog.Msg msg) {
            validateItems(msg, 1, 1, 1, 1);
            validateFluids(msg);
        }

        @Override
        @RecipeBuilderRegistrationMethod
        public @Nullable HeatCoilRecipe register() {
            if (!validate()) return null;
            HeatCoilRecipe recipe = new HeatCoilRecipe(output.get(0), input.get(0).toMcIngredient());
            GSPlugin.instance.heatcoil.add(recipe);
            return recipe;
        }
    }
}
