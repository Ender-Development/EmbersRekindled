package teamroots.embers.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.Tuple;
import net.minecraftforge.oredict.OreIngredient;

import java.util.Objects;

public final class Util {
    private Util() {
    }

    public static Tuple<Ingredient, Double> registerCoefficient(String config_entry) {
        String[] parts = config_entry.split(";");
        String registry = parts[0];
        Double coefficient = Double.valueOf(parts[1]);

        String[] registryParts = registry.split(":");
        String type = registryParts[0];
        String name = registryParts[1];

        Ingredient ingredient;
        if (Objects.equals(type, "ore")) {
            ingredient = new OreIngredient(name);
        } else {
            ingredient = Ingredient.fromItem(Item.getByNameOrId(registry));
        }
        return new Tuple<>(ingredient, coefficient);
    }
}
