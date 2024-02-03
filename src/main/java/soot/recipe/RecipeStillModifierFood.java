package soot.recipe;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import soot.recipe.breweffects.EffectAdd;
import soot.recipe.breweffects.EffectMax;

import java.util.Collection;

public class RecipeStillModifierFood extends RecipeStillModifier {
    public RecipeStillModifierFood(ResourceLocation id, Collection<Fluid> validFluids, Ingredient catalystInput, int catalystConsumed, int hunger, float saturation) {
        super(id, validFluids, catalystInput, catalystConsumed);
        addEffect(new EffectAdd("hunger", hunger, false));
        addEffect(new EffectMax("saturation", saturation, true));
    }
}
