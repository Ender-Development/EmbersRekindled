package soot.compat.jei;

import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import soot.Registry;
import soot.compat.jei.category.AlchemicalMixerCategory;
import soot.compat.jei.category.StillCategory;
import soot.compat.jei.wrapper.AlchemicalMixerWrapper;
import soot.compat.jei.wrapper.StillWrapper;
import soot.recipe.CraftingRegistry;
import soot.recipe.RecipeAlchemicalMixer;
import soot.recipe.RecipeStill;
import teamroots.embers.compat.jei.EmbersJEIPlugin;
import teamroots.embers.register.BlockRegister;

@mezz.jei.api.JEIPlugin
public class JEI implements IModPlugin {
    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
        subtypeRegistry.useNbtForSubtypes(Registry.ESSENCE);
        subtypeRegistry.useNbtForSubtypes(Registry.MUG);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new AlchemicalMixerCategory(guiHelper), new StillCategory(guiHelper));
    }

    @Override
    public void register(IModRegistry registry) {
        IJeiHelpers helpers = registry.getJeiHelpers();

        registry.addRecipes(EmbersJEIPlugin.expandRecipes(CraftingRegistry.alchemicalMixingRecipes), AlchemicalMixerCategory.UID);
        registry.addRecipes(EmbersJEIPlugin.expandRecipes(CraftingRegistry.stillRecipes), StillCategory.UID);

        registry.handleRecipes(RecipeAlchemicalMixer.class, AlchemicalMixerWrapper::new, AlchemicalMixerCategory.UID);
        registry.handleRecipes(RecipeStill.class, StillWrapper::new, StillCategory.UID);

        registry.addRecipeCatalyst(new ItemStack(BlockRegister.MIXER), AlchemicalMixerCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(Registry.ALCHEMY_GLOBE), AlchemicalMixerCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(Registry.STILL), StillCategory.UID);
    }
}
