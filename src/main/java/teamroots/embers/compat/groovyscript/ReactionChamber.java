package teamroots.embers.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyBlacklist;
import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.api.documentation.annotations.*;
import com.cleanroommc.groovyscript.helper.SimpleObjectStream;
import com.cleanroommc.groovyscript.helper.recipe.AbstractRecipeBuilder;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import teamroots.embers.Embers;
import teamroots.embers.recipe.FluidReactionRecipe;
import teamroots.embers.recipe.RecipeRegistry;

import java.awt.*;

@RegistryDescription(linkGenerator = Embers.MODID)
public class ReactionChamber extends VirtualizedRegistry<FluidReactionRecipe> {
    @RecipeBuilderDescription(example = @Example(".fluidInput(fluid('lava') * 10).fluidOutput(fluid('steam') * 50)"))
    public RecipeBuilder recipeBuilder() {
        return new RecipeBuilder();
    }

    @Override
    @GroovyBlacklist
    public void onReload() {
        RecipeRegistry.fluidReactionRecipes.removeAll(removeScripted());
        RecipeRegistry.fluidReactionRecipes.addAll(restoreFromBackup());
    }

    public void add(FluidReactionRecipe recipe) {
        if (recipe != null) {
            addScripted(recipe);
            RecipeRegistry.fluidReactionRecipes.add(recipe);
        }
    }

    public boolean remove(FluidReactionRecipe recipe) {
        if (RecipeRegistry.fluidReactionRecipes.removeIf(r -> r == recipe)) {
            addBackup(recipe);
            return true;
        }
        return false;
    }

    @MethodDescription(type = MethodDescription.Type.REMOVAL, example = @Example("fluid('steam')"))
    public boolean removeByOutput(IIngredient output) {
        return RecipeRegistry.fluidReactionRecipes.removeIf(r -> {
            if (output.test(r.getOutput())) {
                addBackup(r);
                return true;
            }
            return false;
        });
    }

    @MethodDescription(type = MethodDescription.Type.QUERY)
    public SimpleObjectStream<FluidReactionRecipe> streamRecipes() {
        return new SimpleObjectStream<>(RecipeRegistry.fluidReactionRecipes).setRemover(this::remove);
    }

    @MethodDescription(type = MethodDescription.Type.REMOVAL, priority = 2000, example = @Example(commented = true))
    public void removeAll() {
        RecipeRegistry.fluidReactionRecipes.forEach(this::addBackup);
        RecipeRegistry.fluidReactionRecipes.clear();
    }

    @Property(property = "fluidInput", valid = @Comp("1"))
    @Property(property = "fluidOutput", valid = @Comp("1"))
    public static class RecipeBuilder extends AbstractRecipeBuilder<FluidReactionRecipe> {

        @Property(valid = {@Comp(type = Comp.Type.GTE, value = "0"), @Comp(type = Comp.Type.LTE, value = "1")})
        private float red;
        @Property(valid = {@Comp(type = Comp.Type.GTE, value = "0"), @Comp(type = Comp.Type.LTE, value = "1")})
        private float green;
        @Property(valid = {@Comp(type = Comp.Type.GTE, value = "0"), @Comp(type = Comp.Type.LTE, value = "1")})
        private float blue;

        @RecipeBuilderMethodDescription(field = {"red", "green", "blue"})
        public RecipeBuilder particleColor(float... color) {
            if (color.length != 3) {
                GroovyLog.get().warn("Error setting color in Embers Reaction Chamber recipe. Color must contain 3 floats, yet it contained {}", color.length);
                return this;
            }
            this.red = color[0];
            this.green = color[1];
            this.blue = color[2];
            return this;
        }

        @RecipeBuilderMethodDescription(field = {"red", "green", "blue"})
        public RecipeBuilder color(float... color) {
            return this.particleColor(color);
        }

        @RecipeBuilderMethodDescription(field = {"red", "green", "blue"})
        public RecipeBuilder particleColor(int hex) {
            Color color = new Color(hex);
            this.red = color.getRed() / 255.0f;
            this.green = color.getGreen() / 255.0f;
            this.blue = color.getBlue() / 255.0f;
            return this;
        }

        @RecipeBuilderMethodDescription(field = {"red", "green", "blue"})
        public RecipeBuilder color(int hex) {
            return this.particleColor(hex);
        }

        @RecipeBuilderMethodDescription(priority = 100)
        public RecipeBuilder red(float red) {
            this.red = red;
            return this;
        }

        @RecipeBuilderMethodDescription(priority = 100)
        public RecipeBuilder green(float green) {
            this.green = green;
            return this;
        }

        @RecipeBuilderMethodDescription(priority = 100)
        public RecipeBuilder blue(float blue) {
            this.blue = blue;
            return this;
        }

        @Override
        public String getErrorMsg() {
            return "Error adding Embers Reaction Chamber recipe";
        }

        @Override
        public void validate(GroovyLog.Msg msg) {
            validateItems(msg);
            validateFluids(msg,1,1,1,1);
            msg.add(red < 0 || red > 1, "red must be a float between 0 and 1, yet it was {}", red);
            msg.add(green < 0 || green > 1, "green must be a float between 0 and 1, yet it was {}", green);
            msg.add(blue < 0 || blue > 1, "blue must be a float between 0 and 1, yet it was {}", blue);
        }

        @Override
        @RecipeBuilderRegistrationMethod
        public @Nullable FluidReactionRecipe register() {
            if (!validate()) return null;
            FluidReactionRecipe recipe = new FluidReactionRecipe(fluidInput.get(0), fluidOutput.get(0), new Color(red, green, blue));
            GSPlugin.instance.reactionchamber.add(recipe);
            return recipe;
        }
    }
}
