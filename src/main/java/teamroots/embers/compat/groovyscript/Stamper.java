package teamroots.embers.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyBlacklist;
import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.api.documentation.annotations.*;
import com.cleanroommc.groovyscript.helper.SimpleObjectStream;
import com.cleanroommc.groovyscript.helper.ingredient.IngredientHelper;
import com.cleanroommc.groovyscript.helper.recipe.AbstractRecipeBuilder;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import net.minecraft.item.crafting.Ingredient;
import teamroots.embers.Embers;
import teamroots.embers.recipe.ItemStampingRecipe;
import teamroots.embers.recipe.RecipeRegistry;

import java.util.Arrays;

@RegistryDescription(linkGenerator = Embers.MODID)
class Stamper extends VirtualizedRegistry<ItemStampingRecipe>{
     @RecipeBuilderDescription(example = {
            @Example(".stamp(item('minecraft:clay')).fluidInput(fluid('water') * 100).output(item('minecraft:brick'))"),
            @Example(".input(item('minecraft:gravel')).stamp(item('minecraft:flint')).output(item('minecraft:glass'))")
    })
    public RecipeBuilder recipeBuilder() {
        return new RecipeBuilder();
    }

    @Override
    @GroovyBlacklist
    public void onReload() {
        RecipeRegistry.stampingRecipes.removeAll(removeScripted());
        RecipeRegistry.stampingRecipes.addAll(restoreFromBackup());
    }

    public void add(ItemStampingRecipe recipe) {
        if (recipe != null) {
            addScripted(recipe);
            RecipeRegistry.stampingRecipes.add(recipe);
        }
    }

    public boolean remove(ItemStampingRecipe recipe) {
        if (RecipeRegistry.stampingRecipes.removeIf(r -> r == recipe)) {
            addBackup(recipe);
            return true;
        }
        return false;
    }

    @MethodDescription(type = MethodDescription.Type.REMOVAL, example = @Example("item('embers:shard_ember')"))
    public boolean removeByInput(IIngredient input) {
        return RecipeRegistry.stampingRecipes.removeIf(r -> {
            if (Arrays.stream(r.input.getMatchingStacks()).anyMatch(input)) {
                addBackup(r);
                return true;
            }
            return false;
        });
    }

    @MethodDescription(type = MethodDescription.Type.REMOVAL, example = {@Example("item('embers:plate_iron')"), @Example(value = "item('embers:dust_ash')", commented = true)})
    public boolean removeByOutput(IIngredient output) {
        return RecipeRegistry.stampingRecipes.removeIf(r -> {
            if (output.test(r.getOutputs().get(0))) {
                addBackup(r);
                return true;
            }
            return false;
        });
    }

    @MethodDescription(type = MethodDescription.Type.QUERY)
    public SimpleObjectStream<ItemStampingRecipe> streamRecipes() {
        return new SimpleObjectStream<>(RecipeRegistry.stampingRecipes).setRemover(this::remove);
    }

    @MethodDescription(type = MethodDescription.Type.REMOVAL, priority = 2000, example = @Example(commented = true))
    public void removeAll() {
        RecipeRegistry.stampingRecipes.forEach(this::addBackup);
        RecipeRegistry.stampingRecipes.clear();
    }

    @Property(property = "input", valid = {@Comp(value = "0", type = Comp.Type.GTE), @Comp(value = "1", type = Comp.Type.LTE)})
    @Property(property = "fluidInput", valid = {@Comp(value = "0", type = Comp.Type.GTE), @Comp(value = "1", type = Comp.Type.LTE)})
    @Property(property = "output", valid = @Comp("1"))
    public static class RecipeBuilder extends AbstractRecipeBuilder<ItemStampingRecipe> {

        @Property(defaultValue = "IIngredient.EMPTY", requirement = "groovyscript.wiki.embers.stamper.stamp.required")
        private IIngredient stamp = IIngredient.EMPTY;

        @RecipeBuilderMethodDescription(field = "stamp")
        public RecipeBuilder stamp(IIngredient stamp) {
            this.stamp = stamp;
            return this;
        }

        @Override
        public String getErrorMsg() {
            return "Error adding Embers Stamper recipe";
        }

        @Override
        public void validate(GroovyLog.Msg msg) {
            validateItems(msg, 0, 1, 1, 1);
            validateFluids(msg, 0, 1, 0, 0);
            msg.add(IngredientHelper.isEmpty(stamp), "stamp is required");
        }

        @Override
        @RecipeBuilderRegistrationMethod
        public @Nullable ItemStampingRecipe register() {
            if (!validate()) return null;
            ItemStampingRecipe recipe;
            if (IngredientHelper.isEmpty(input)) {
                recipe = new ItemStampingRecipe(Ingredient.EMPTY, fluidInput.getOrEmpty(0), stamp.toMcIngredient(), output.get(0));
            } else {
                recipe = new ItemStampingRecipe(input.get(0).toMcIngredient(), fluidInput.getOrEmpty(0), stamp.toMcIngredient(), output.get(0));
            }
            GSPlugin.instance.stamper.add(recipe);
            return recipe;
        }
    }
}
