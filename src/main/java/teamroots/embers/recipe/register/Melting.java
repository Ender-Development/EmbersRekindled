package teamroots.embers.recipe.register;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreIngredient;
import teamroots.embers.recipe.ItemMeltingRecipe;
import teamroots.embers.register.FluidRegister;

import static teamroots.embers.recipe.RecipeRegistry.*;

public class Melting implements IRegister{
    public static final Melting INSTANCE = new Melting();

    private Melting(){}
    public void execute() {

        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("ingotIron"), new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, INGOT_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetIron"), new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, NUGGET_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateIron"), new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, plateAmount)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreIron"), new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, oreMeltAmount))
                .addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, NUGGET_AMOUNT))
        );

        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("ingotGold"), new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, INGOT_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetGold"), new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, NUGGET_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateGold"), new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, plateAmount)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreGold"), new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, oreMeltAmount))
                .addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, NUGGET_AMOUNT))
        );

        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("ingotSilver"), new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, INGOT_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetSilver"), new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, NUGGET_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateSilver"), new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, plateAmount)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreSilver"), new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, oreMeltAmount))
                .addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, NUGGET_AMOUNT))
        );

        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("ingotCopper"), new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, INGOT_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetCopper"), new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, NUGGET_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateCopper"), new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, plateAmount)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreCopper"), new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, oreMeltAmount))
                .addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, NUGGET_AMOUNT))
        );

        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("ingotLead"), new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, INGOT_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetLead"), new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, NUGGET_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateLead"), new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, plateAmount)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreLead"), new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, oreMeltAmount))
                .addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, NUGGET_AMOUNT))
        );

        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("ingotAluminum"), new FluidStack(FluidRegister.FLUID_MOLTEN_ALUMINUM, INGOT_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetAluminum"), new FluidStack(FluidRegister.FLUID_MOLTEN_ALUMINUM, NUGGET_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateAluminum"), new FluidStack(FluidRegister.FLUID_MOLTEN_ALUMINUM, plateAmount)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreAluminum"), new FluidStack(FluidRegister.FLUID_MOLTEN_ALUMINUM, oreMeltAmount))
                .addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, NUGGET_AMOUNT))
        );

        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("ingotNickel"), new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, INGOT_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetNickel"), new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, NUGGET_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateNickel"), new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, plateAmount)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreNickel"), new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, oreMeltAmount))
                .addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, NUGGET_AMOUNT))
        );

        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("ingotTin"), new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, INGOT_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetTin"), new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, NUGGET_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateTin"), new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, plateAmount)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreTin"), new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, oreMeltAmount))
                .addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, NUGGET_AMOUNT))
        );

        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("ingotDawnstone"), new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, INGOT_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetDawnstone"), new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, NUGGET_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateDawnstone"), new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, plateAmount)));

        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("ingotBronze"), new FluidStack(FluidRegister.FLUID_MOLTEN_BRONZE, INGOT_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetBronze"), new FluidStack(FluidRegister.FLUID_MOLTEN_BRONZE, NUGGET_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateBronze"), new FluidStack(FluidRegister.FLUID_MOLTEN_BRONZE, plateAmount)));

        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("ingotElectrum"), new FluidStack(FluidRegister.FLUID_MOLTEN_ELECTRUM, INGOT_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetElectrum"), new FluidStack(FluidRegister.FLUID_MOLTEN_ELECTRUM, NUGGET_AMOUNT)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateElectrum"), new FluidStack(FluidRegister.FLUID_MOLTEN_ELECTRUM, plateAmount)));

        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("dustRedstone"), new FluidStack(FluidRegister.FLUID_ALCHEMICAL_REDSTONE, 144)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("blockRedstone"), new FluidStack(FluidRegister.FLUID_ALCHEMICAL_REDSTONE, 1296)));
        meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreRedstone"), new FluidStack(FluidRegister.FLUID_ALCHEMICAL_REDSTONE, 1008))
                .addBonusOutput(FluidRegistry.getFluidStack("mercury", NUGGET_AMOUNT))
        );

        meltingRecipes.add(new ItemMeltingRecipe(Ingredient.fromStacks(new ItemStack(Blocks.SOUL_SAND)), new FluidStack(FluidRegister.FLUID_CRUDE_OIL, 100)));

    }
}
