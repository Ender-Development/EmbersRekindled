package teamroots.embers.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyBlacklist;
import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.api.documentation.annotations.*;
import com.cleanroommc.groovyscript.helper.SimpleObjectStream;
import com.cleanroommc.groovyscript.helper.recipe.AbstractRecipeBuilder;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import net.minecraftforge.fluids.FluidStack;
import teamroots.embers.Embers;
import teamroots.embers.recipe.FluidMixingRecipe;
import teamroots.embers.recipe.RecipeRegistry;

import java.util.Arrays;

@RegistryDescription(linkGenerator = Embers.MODID)
public class Mixer extends VirtualizedRegistry<FluidMixingRecipe> {
    @RecipeBuilderDescription(example = {
            @Example(".fluidInput(fluid('water') * 100, fluid('lava') * 100).fluidOutput(fluid('dawnstone') * 100)")
    })
    public RecipeBuilder recipeBuilder() {
        return new RecipeBuilder();
    }

    @Override
    @GroovyBlacklist
    public void onReload() {
        RecipeRegistry.mixingRecipes.removeAll(removeScripted());
        RecipeRegistry.mixingRecipes.addAll(restoreFromBackup());
    }

    public void add(FluidMixingRecipe recipe) {
        if (recipe != null) {
            addScripted(recipe);
            RecipeRegistry.mixingRecipes.add(recipe);
        }
    }

    public boolean remove(FluidMixingRecipe recipe) {
        if (RecipeRegistry.mixingRecipes.removeIf(r -> r == recipe)) {
            addBackup(recipe);
            return true;
        }
        return false;
    }

    @MethodDescription(type = MethodDescription.Type.REMOVAL, example = {@Example("fluid('dawnstone')")})
    public boolean removeByOutput(IIngredient output) {
        return RecipeRegistry.mixingRecipes.removeIf(r -> {
            if (output.test(r.getResult(r.getInput()))) {
                addBackup(r);
                return true;
            }
            return false;
        });
    }

    @MethodDescription(type = MethodDescription.Type.QUERY)
    public SimpleObjectStream<FluidMixingRecipe> streamRecipes() {
        return new SimpleObjectStream<>(RecipeRegistry.mixingRecipes).setRemover(this::remove);
    }

    @MethodDescription(type = MethodDescription.Type.REMOVAL, priority = 2000, example = @Example(commented = true))
    public void removeAll() {
        RecipeRegistry.mixingRecipes.forEach(this::addBackup);
        RecipeRegistry.mixingRecipes.clear();
    }

    @Property(property = "fluidInput", valid = {@Comp(value = "2", type = Comp.Type.GTE), @Comp(value = "3", type = Comp.Type.LTE)})
    @Property(property = "fluidOutput", valid = {@Comp(value = "1")})
    public static class RecipeBuilder extends AbstractRecipeBuilder<FluidMixingRecipe> {

        @Override
        public String getErrorMsg() {
            return "Error adding Embers Mixing recipe";
        }

        @Property(defaultValue = "0.5", valid = @Comp(value = "0", type = Comp.Type.GTE), value = "groovyscript.wiki.embers.mixer.powerratio.value")
        private double powerRatio = 0.5;

        @RecipeBuilderMethodDescription(field = "powerRatio")
        public RecipeBuilder powerRatio(int powerRatio) {
            this.powerRatio = powerRatio;
            return this;
        }

        @Override
        public void validate(GroovyLog.Msg msg) {
            validateItems(msg);
            validateFluids(msg, 2, 3, 1, 1);
            msg.add(powerRatio < 0, "Power ratio must be greater than or equal to 0, yet it was {}", powerRatio);
        }

        @Override
        @RecipeBuilderRegistrationMethod
        public @Nullable FluidMixingRecipe register() {
            if (!validate()) return null;
            FluidMixingRecipe recipe = new FluidMixingRecipe(fluidInput.toArray(new FluidStack[0]), fluidOutput.get(0), powerRatio);
            GSPlugin.instance.mixer.add(recipe);
            return recipe;
        }
    }
}
