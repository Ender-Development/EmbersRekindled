package teamroots.embers.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import teamroots.embers.item.ItemAlchemicWaste;
import teamroots.embers.register.ItemRegister;

import java.util.Random;

public class ItemWasteStampingRecipe extends ItemStampingRecipe{
	Random random = new Random();
	public ItemWasteStampingRecipe(){
		super(Ingredient.fromItem(ItemRegister.ALCHEMIC_WASTE), null, Ingredient.fromItem(ItemRegister.STAMP_FLAT),new ItemStack(ItemRegister.DUST_ASH,1));
	}

	@Override
	public ItemStack getResult(TileEntity tile, ItemStack input, FluidStack fluid, ItemStack stamp) {
		if (input.hasTagCompound() && input.getItem() instanceof ItemAlchemicWaste){
			int ash = input.getTagCompound().getInteger("totalAsh");
			return new ItemStack(ItemRegister.DUST_ASH,(ash*4)/5);
		}
		return new ItemStack(ItemRegister.DUST_ASH,1);
	}
}
