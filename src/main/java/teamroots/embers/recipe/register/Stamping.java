package teamroots.embers.recipe.register;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import teamroots.embers.config.ConfigMachine;
import teamroots.embers.config.ConfigMaterial;
import teamroots.embers.recipe.ItemStampingRecipe;
import teamroots.embers.recipe.ItemWasteStampingRecipe;
import teamroots.embers.register.FluidRegister;
import teamroots.embers.register.ItemRegister;

import static teamroots.embers.recipe.RecipeRegistry.*;

public class Stamping implements IRegister {

    public static final Stamping INSTANCE = new Stamping();

    private Stamping() {
    }

    @Override
    public void execute() {
        Ingredient stampBar = Ingredient.fromItem(ItemRegister.STAMP_BAR);
        Ingredient stampPlate = Ingredient.fromItem(ItemRegister.STAMP_PLATE);
        Ingredient stampRod = Ingredient.fromItem(ItemRegister.STAMP_ROD);

        stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, INGOT_AMOUNT), stampBar, new ItemStack(Items.IRON_INGOT, 1)));
        stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, INGOT_AMOUNT), stampBar, new ItemStack(Items.GOLD_INGOT, 1)));

        if (!OreDictionary.getOres("ingotLead").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, INGOT_AMOUNT), stampBar, getItemStackFromOreDict("ingotLead", 1)));
        }
        if (!OreDictionary.getOres("ingotSilver").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, INGOT_AMOUNT), stampBar, getItemStackFromOreDict("ingotSilver", 1)));
        }
        if (!OreDictionary.getOres("ingotCopper").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, INGOT_AMOUNT), stampBar, getItemStackFromOreDict("ingotCopper", 1)));
        }
        if (!OreDictionary.getOres("ingotDawnstone").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, INGOT_AMOUNT), stampBar, getItemStackFromOreDict("ingotDawnstone", 1)));
        }
        if (!OreDictionary.getOres("ingotAluminum").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_ALUMINUM, INGOT_AMOUNT), stampBar, getItemStackFromOreDict("ingotAluminum", 1)));
        }
        if (!OreDictionary.getOres("ingotBronze").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_BRONZE, INGOT_AMOUNT), stampBar, getItemStackFromOreDict("ingotBronze", 1)));
        }
        if (!OreDictionary.getOres("ingotElectrum").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_ELECTRUM, INGOT_AMOUNT), stampBar, getItemStackFromOreDict("ingotElectrum", 1)));
        }
        if (!OreDictionary.getOres("ingotNickel").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, INGOT_AMOUNT), stampBar, getItemStackFromOreDict("ingotNickel", 1)));
        }
        if (!OreDictionary.getOres("ingotTin").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, INGOT_AMOUNT), stampBar, getItemStackFromOreDict("ingotTin", 1)));
        }

        stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, plateAmount), stampPlate, getItemStackFromOreDict("plateIron", 1)));
        stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, plateAmount), stampPlate, getItemStackFromOreDict("plateGold", 1)));
        if (!OreDictionary.getOres("ingotLead").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, plateAmount), stampPlate, getItemStackFromOreDict("plateLead", 1)));
        }
        if (!OreDictionary.getOres("plateSilver").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, plateAmount), stampPlate, getItemStackFromOreDict("plateSilver", 1)));
        }
        if (!OreDictionary.getOres("plateCopper").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, plateAmount), stampPlate, getItemStackFromOreDict("plateCopper", 1)));
        }
        if (!OreDictionary.getOres("plateDawnstone").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, plateAmount), stampPlate, getItemStackFromOreDict("plateDawnstone", 1)));
        }
        if (!OreDictionary.getOres("plateAluminum").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_ALUMINUM, plateAmount), stampPlate, getItemStackFromOreDict("plateAluminum", 1)));
        }
        if (!OreDictionary.getOres("plateBronze").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_BRONZE, plateAmount), stampPlate, getItemStackFromOreDict("plateBronze", 1)));
        }
        if (!OreDictionary.getOres("plateElectrum").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_ELECTRUM, plateAmount), stampPlate, getItemStackFromOreDict("plateElectrum", 1)));
        }
        if (!OreDictionary.getOres("plateNickel").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, plateAmount), stampPlate, getItemStackFromOreDict("plateNickel", 1)));
        }
        if (!OreDictionary.getOres("plateTin").isEmpty()) {
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, plateAmount), stampPlate, getItemStackFromOreDict("plateTin", 1)));
        }

        if (OreDictionary.doesOreNameExist("stickIron") && !OreDictionary.getOres("stickIron").isEmpty()) {
            ItemStack stickIron = OreDictionary.getOres("stickIron").get(0);
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, rodAmount), stampRod, stickIron));
        }
        if (OreDictionary.doesOreNameExist("stickGold") && !OreDictionary.getOres("stickGold").isEmpty()) {
            ItemStack stickGold = OreDictionary.getOres("stickGold").get(0);
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, rodAmount), stampRod, stickGold));
        }
        if (OreDictionary.doesOreNameExist("stickLead") && !OreDictionary.getOres("stickLead").isEmpty()) {
            ItemStack stickLead = OreDictionary.getOres("stickLead").get(0);
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, rodAmount), stampRod, stickLead));
        }
        if (OreDictionary.doesOreNameExist("stickSilver") && !OreDictionary.getOres("stickSilver").isEmpty()) {
            ItemStack stickSilver = OreDictionary.getOres("stickSilver").get(0);
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, rodAmount), stampRod, stickSilver));
        }
        if (OreDictionary.doesOreNameExist("stickCopper") && !OreDictionary.getOres("stickCopper").isEmpty()) {
            ItemStack stickCopper = OreDictionary.getOres("stickCopper").get(0);
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, rodAmount), stampRod, stickCopper));
        }
        if (OreDictionary.doesOreNameExist("stickDawnstone") && !OreDictionary.getOres("stickDawnstone").isEmpty()) {
            ItemStack stickDawnstone = OreDictionary.getOres("stickDawnstone").get(0);
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, rodAmount), stampRod, stickDawnstone));
        }
        if (ConfigMaterial.ALUMINUM.mustLoad() && OreDictionary.doesOreNameExist("stickAluminum") && !OreDictionary.getOres("stickAluminum").isEmpty()) {
            ItemStack stickAluminum = OreDictionary.getOres("stickAluminum").get(0);
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_ALUMINUM, rodAmount), stampRod, stickAluminum));
        }
        if (ConfigMaterial.BRONZE.mustLoad() && OreDictionary.doesOreNameExist("stickBronze") && !OreDictionary.getOres("stickBronze").isEmpty()) {
            ItemStack stickBronze = OreDictionary.getOres("stickBronze").get(0);
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_BRONZE, rodAmount), stampRod, stickBronze));
        }
        if (ConfigMaterial.ELECTRUM.mustLoad() && OreDictionary.doesOreNameExist("stickElectrum") && !OreDictionary.getOres("stickElectrum").isEmpty()) {
            ItemStack stickElectrum = OreDictionary.getOres("stickElectrum").get(0);
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_ELECTRUM, rodAmount), stampRod, stickElectrum));
        }
        if (ConfigMaterial.NICKEL.mustLoad() && OreDictionary.doesOreNameExist("stickNickel") && !OreDictionary.getOres("stickNickel").isEmpty()) {
            ItemStack stickNickel = OreDictionary.getOres("stickNickel").get(0);
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, rodAmount), stampRod, stickNickel));
        }
        if (ConfigMaterial.TIN.mustLoad() && OreDictionary.doesOreNameExist("stickTin") && !OreDictionary.getOres("stickTin").isEmpty()) {
            ItemStack stickTin = OreDictionary.getOres("stickTin").get(0);
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, rodAmount), stampRod, stickTin));
        }
        if (OreDictionary.doesOreNameExist("stickSteel") && !OreDictionary.getOres("stickSteel").isEmpty() && FluidRegistry.isFluidRegistered("steel")) {
            ItemStack stickSteel = OreDictionary.getOres("stickSteel").get(0);
            stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegistry.getFluid("steel"), rodAmount), stampRod, stickSteel));
        }

        stampingRecipes.add(new ItemWasteStampingRecipe());

        Ingredient emberShard = Ingredient.fromItem(ItemRegister.SHARD_EMBER);
        Ingredient emberCrystal = Ingredient.fromItem(ItemRegister.CRYSTAL_EMBER);
        Ingredient stampFlat = Ingredient.fromItem(ItemRegister.STAMP_FLAT);
        Ingredient blazeRod = Ingredient.fromItem(Items.BLAZE_ROD);

        stampingRecipes.add(new ItemStampingRecipe(emberShard, null, stampFlat, new ItemStack(ItemRegister.DUST_EMBER, 1)));
        stampingRecipes.add(new ItemStampingRecipe(emberCrystal, null, stampFlat, new ItemStack(ItemRegister.SHARD_EMBER, 6)));
        stampingRecipes.add(new ItemStampingRecipe(blazeRod, null, stampFlat, new ItemStack(Items.BLAZE_POWDER, 4)));

        int aspectusAmount = ConfigMachine.STAMPER.stampAspectusAmount * INGOT_AMOUNT;
        stampingRecipes.add(new ItemStampingRecipe(emberShard, new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, aspectusAmount), stampPlate, new ItemStack(ItemRegister.ASPECTUS_IRON, 1)));
        stampingRecipes.add(new ItemStampingRecipe(emberShard, new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, aspectusAmount), stampPlate, new ItemStack(ItemRegister.ASPECTUS_LEAD, 1)));
        stampingRecipes.add(new ItemStampingRecipe(emberShard, new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, aspectusAmount), stampPlate, new ItemStack(ItemRegister.ASPECTUS_SILVER, 1)));
        stampingRecipes.add(new ItemStampingRecipe(emberShard, new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, aspectusAmount), stampPlate, new ItemStack(ItemRegister.ASPECTUS_COPPER, 1)));
        stampingRecipes.add(new ItemStampingRecipe(emberShard, new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, aspectusAmount), stampPlate, new ItemStack(ItemRegister.ASPECTUS_DAWNSTONE, 1)));

    }
}
