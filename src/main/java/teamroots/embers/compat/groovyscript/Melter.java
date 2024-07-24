package teamroots.embers.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.documentation.annotations.Comp;
import com.cleanroommc.groovyscript.api.documentation.annotations.Property;
import com.cleanroommc.groovyscript.helper.recipe.AbstractRecipeBuilder;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import org.jetbrains.annotations.Nullable;
import teamroots.embers.recipe.ItemMeltingRecipe;

public class Melter extends VirtualizedRegistry<ItemMeltingRecipe> {
    public RecipeBuilder recipeBuilder() {
        return new RecipeBuilder();
    }

    @Override
    public void onReload() {

    }

    @Property(property = "input", valid = @Comp("1"))
    @Property(property = "fluidOutput", valid = @Comp("1"))
    public static class RecipeBuilder extends AbstractRecipeBuilder<ItemMeltingRecipe> {

        @Override
        public String getErrorMsg() {
            return "Error adding Embers Melter recipe";
        }

        @Override
        public void validate(GroovyLog.Msg msg) {

        }

        @Override
        public @Nullable ItemMeltingRecipe register() {
            return null;
        }
    }
}
