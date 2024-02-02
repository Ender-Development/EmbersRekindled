package teamroots.embers.compat;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import teamroots.embers.config.ConfigMachine;
import teamroots.embers.recipe.ItemStampingRecipe;
import teamroots.embers.recipe.RecipeRegistry;
import teamroots.embers.register.ItemRegister;

public class EnderioIntegration {
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        Ingredient stampRound = Ingredient.fromItem(ItemRegister.STAMP_ROUND);
        int roundAmount = ConfigMachine.STAMPER.stampRoundAmount;
        if (FluidRegistry.isFluidRegistered("electrical_steel") && OreDictionary.doesOreNameExist("ballElectricalSteel")) {
            ItemStack ballElectricalSteel = OreDictionary.getOres("ballElectricalSteel").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("electrical_steel"), roundAmount), stampRound, ballElectricalSteel));
        }
        if (FluidRegistry.isFluidRegistered("pulsating_iron") && OreDictionary.doesOreNameExist("ballPulsatingIron")) {
            ItemStack ballPulsatingIron = OreDictionary.getOres("ballPulsatingIron").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("pulsating_iron"), roundAmount), stampRound, ballPulsatingIron));
        }
        if (FluidRegistry.isFluidRegistered("vibrant_alloy") && OreDictionary.doesOreNameExist("ballVibrantAlloy")) {
            ItemStack ballVibrantAlloy = OreDictionary.getOres("ballVibrantAlloy").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("vibrant_alloy"), roundAmount), stampRound, ballVibrantAlloy));
        }
        if (FluidRegistry.isFluidRegistered("energetic_alloy") && OreDictionary.doesOreNameExist("ballEnergeticAlloy")) {
            ItemStack ballEnergeticAlloy = OreDictionary.getOres("ballEnergeticAlloy").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("energetic_alloy"), roundAmount), stampRound, ballEnergeticAlloy));
        }
        if (FluidRegistry.isFluidRegistered("redstone_alloy") && OreDictionary.doesOreNameExist("ballRedstoneAlloy")) {
            ItemStack ballRedstoneAlloy = OreDictionary.getOres("ballRedstoneAlloy").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("redstone_alloy"), roundAmount), stampRound, ballRedstoneAlloy));
        }
        if (FluidRegistry.isFluidRegistered("conductive_iron") && OreDictionary.doesOreNameExist("ballConductiveIron")) {
            ItemStack ballConductiveIron = OreDictionary.getOres("ballConductiveIron").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("conductive_iron"), roundAmount), stampRound, ballConductiveIron));
        }
        if (FluidRegistry.isFluidRegistered("soularium") && OreDictionary.doesOreNameExist("ballSoularium")) {
            ItemStack ballSoularium = OreDictionary.getOres("ballSoularium").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("soularium"), roundAmount), stampRound, ballSoularium));
        }
        if (FluidRegistry.isFluidRegistered("dark_steel") && OreDictionary.doesOreNameExist("ballDarkSteel")) {
            ItemStack ballDarkSteel = OreDictionary.getOres("ballDarkSteel").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("dark_steel"), roundAmount), stampRound, ballDarkSteel));
        }
        if (FluidRegistry.isFluidRegistered("end_steel") && OreDictionary.doesOreNameExist("ballEndSteel")) {
            ItemStack ballEndSteel = OreDictionary.getOres("ballEndSteel").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("end_steel"), roundAmount), stampRound, ballEndSteel));
        }
        if (FluidRegistry.isFluidRegistered("construction_alloy") && OreDictionary.doesOreNameExist("ballConstructionAlloy")) {
            ItemStack ballConstructionAlloy = OreDictionary.getOres("ballConstructionAlloy").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("construction_alloy"), roundAmount), stampRound, ballConstructionAlloy));
        }
        if (FluidRegistry.isFluidRegistered("crude_steel") && OreDictionary.doesOreNameExist("ballCrudeSteel")) {
            ItemStack ballCrudeSteel = OreDictionary.getOres("ballCrudeSteel").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("crude_steel"), roundAmount), stampRound, ballCrudeSteel));
        }
        if (FluidRegistry.isFluidRegistered("melodic_alloy") && OreDictionary.doesOreNameExist("ballMelodicAlloy")) {
            ItemStack ballMelodicAlloy = OreDictionary.getOres("ballMelodicAlloy").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("melodic_alloy"), roundAmount), stampRound, ballMelodicAlloy));
        }
        if (FluidRegistry.isFluidRegistered("energetic_silver") && OreDictionary.doesOreNameExist("ballEnergeticSilver")) {
            ItemStack ballEnergeticSilver = OreDictionary.getOres("ballEnergeticSilver").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("energetic_silver"), roundAmount), stampRound, ballEnergeticSilver));
        }
        if (FluidRegistry.isFluidRegistered("vivid_alloy") && OreDictionary.doesOreNameExist("ballVividAlloy")) {
            ItemStack ballVividAlloy = OreDictionary.getOres("ballVividAlloy").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("vivid_alloy"), roundAmount), stampRound, ballVividAlloy));
        }
        if (FluidRegistry.isFluidRegistered("crystalline_alloy") && OreDictionary.doesOreNameExist("ballCrystallineAlloy")) {
            ItemStack ballCrystallineAlloy = OreDictionary.getOres("ballCrystallineAlloy").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("crystalline_alloy"), roundAmount), stampRound, ballCrystallineAlloy));
        }
        if (FluidRegistry.isFluidRegistered("stellar_alloy") && OreDictionary.doesOreNameExist("ballStellarAlloy")) {
            ItemStack ballStellarAlloy = OreDictionary.getOres("ballStellarAlloy").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("stellar_alloy"), roundAmount), stampRound, ballStellarAlloy));
        }
        if (FluidRegistry.isFluidRegistered("crystalline_pink_slime") && OreDictionary.doesOreNameExist("ballCrystallinePinkSlime")) {
            ItemStack ballCrystallinePinkSlime = OreDictionary.getOres("ballCrystallinePinkSlime").get(0);
            RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("crystalline_pink_slime"), roundAmount), stampRound, ballCrystallinePinkSlime));
        }
    }
}
