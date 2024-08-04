package teamroots.embers.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyBlacklist;
import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.api.documentation.annotations.*;
import com.cleanroommc.groovyscript.helper.SimpleObjectStream;
import com.cleanroommc.groovyscript.helper.ingredient.IngredientHelper;
import com.cleanroommc.groovyscript.helper.recipe.AbstractRecipeBuilder;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import com.google.common.collect.Lists;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import net.minecraft.item.crafting.Ingredient;
import teamroots.embers.api.alchemy.AspectList;
import teamroots.embers.recipe.AlchemyRecipe;
import teamroots.embers.recipe.RecipeRegistry;
import teamroots.embers.util.AlchemyUtil;

import java.util.ArrayList;
import java.util.Arrays;

@RegistryDescription
public class Alchemy extends VirtualizedRegistry<AlchemyRecipe> {
    @RecipeBuilderDescription(example = {
            @Example(".input(item('minecraft:clay'),item('minecraft:clay'),item('minecraft:clay'),item('minecraft:clay')).output(item('minecraft:gravel')).setAspect('dawnstone', 2, 17)"),
            @Example(".input(item('minecraft:gravel'),ore('dyeGreen'),ore('dyeGreen'),ore('dyeGreen'),item('minecraft:rotten_flesh')).output(item('minecraft:grass')).setAspect('iron', 2, 17).setAspect('gold', 1, 8)")
    })
    public RecipeBuilder recipeBuilder() {
        return new RecipeBuilder();
    }

    @Override
    @GroovyBlacklist
    public void onReload() {
        RecipeRegistry.alchemyRecipes.removeAll(removeScripted());
        RecipeRegistry.alchemyRecipes.addAll(restoreFromBackup());
    }

    public void add(AlchemyRecipe recipe) {
        if (recipe != null) {
            addScripted(recipe);
            RecipeRegistry.alchemyRecipes.add(recipe);
        }
    }

    public boolean remove(AlchemyRecipe recipe) {
        if (RecipeRegistry.alchemyRecipes.removeIf(r -> r == recipe)) {
            addBackup(recipe);
            return true;
        }
        return false;
    }

    @MethodDescription(type = MethodDescription.Type.REMOVAL, example = @Example("item('minecraft:wool')"))
    public boolean removeByCenter(IIngredient input) {
        return RecipeRegistry.alchemyRecipes.removeIf(r -> {
            if (Arrays.stream(r.getCenter().getMatchingStacks()).anyMatch(input)) {
                addBackup(r);
                return true;
            }
            return false;
        });
    }

    @MethodDescription(type = MethodDescription.Type.REMOVAL, example = @Example("item('embers:ember_pipe')"))
    public boolean removeByOutput(IIngredient output) {
        return RecipeRegistry.alchemyRecipes.removeIf(r -> {
            if (output.test(r.getOutput())) {
                addBackup(r);
                return true;
            }
            return false;
        });
    }

    @MethodDescription(type = MethodDescription.Type.VALUE, example = @Example("'gold',item('minecraft:gold_ingot')"))
    public boolean addAspect(String aspect, IIngredient item) {
        if (getAspect(item) != null) return false;
        AlchemyUtil.registerAspect(aspect, item.toMcIngredient());
        return true;
    }

    @MethodDescription(type = MethodDescription.Type.VALUE, description = "groovyscript.wiki.embers.alchemy.getaspect", example = @Example("item('embers:aspectus_iron')"))
    public String getAspect(IIngredient item) {
        return AlchemyUtil.getAspect(IngredientHelper.toItemStack(item));
    }

    @MethodDescription(type = MethodDescription.Type.QUERY)
    public SimpleObjectStream<AlchemyRecipe> streamRecipes() {
        return new SimpleObjectStream<>(RecipeRegistry.alchemyRecipes).setRemover(this::remove);
    }

    @MethodDescription(type = MethodDescription.Type.REMOVAL, priority = 2000, example = @Example(commented = true))
    public void removeAll() {
        RecipeRegistry.alchemyRecipes.forEach(this::addBackup);
        RecipeRegistry.alchemyRecipes.clear();
    }

    @Property(property = "input", valid = {@Comp(value = "1", type = Comp.Type.GTE), @Comp(value = "5", type = Comp.Type.LTE)})
    @Property(property = "output", valid = @Comp("1"))
    public static class RecipeBuilder extends AbstractRecipeBuilder<AlchemyRecipe> {
        private final AspectList.AspectRangeList aspects = new AspectList.AspectRangeList();

        @RecipeBuilderMethodDescription
        public RecipeBuilder setAspect(String aspect, int min, int max) {
            this.aspects.setRange(aspect, min, max);
            return this;
        }

        @Override
        public String getErrorMsg() {
            return "Error adding Embers Alchemy recipe";
        }

        @Override
        public void validate(GroovyLog.Msg msg) {
            validateItems(msg, 1, 5, 1, 1);
            validateFluids(msg);
            msg.add(aspects.isEmpty(), "Aspects are required!");
        }

        @Override
        @RecipeBuilderRegistrationMethod
        public @Nullable AlchemyRecipe register() {
            if (!validate()) return null;
            ArrayList<Ingredient> outerIngredients = Lists.newArrayList();
            for (int i = 1; i < input.size(); i++) {
                outerIngredients.add(input.get(i).toMcIngredient());
            }
            AlchemyRecipe recipe = new AlchemyRecipe(aspects, input.get(0).toMcIngredient(), outerIngredients, output.get(0));
            GSPlugin.instance.alchemy.add(recipe);
            return recipe;
        }
    }
}
