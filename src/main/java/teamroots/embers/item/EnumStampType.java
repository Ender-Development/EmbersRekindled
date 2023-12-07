package teamroots.embers.item;

import net.minecraft.item.ItemStack;
import teamroots.embers.register.ItemRegister;

@Deprecated
public enum EnumStampType {
	TYPE_FLAT, TYPE_BAR, TYPE_PLATE, TYPE_NULL;
	public static EnumStampType getType(ItemStack stack){
		if (!stack.isEmpty()){
			if (stack.getItem() == ItemRegister.STAMP_BAR){
				return TYPE_BAR;
			}
			if (stack.getItem() == ItemRegister.STAMP_FLAT){
				return TYPE_FLAT;
			}
			if (stack.getItem() == ItemRegister.STAMP_PLATE){
				return TYPE_PLATE;
			}
		}
		return TYPE_NULL;
	}

	public static ItemStack getStack(EnumStampType type) {
		switch (type) {
			case TYPE_FLAT:
				return new ItemStack(ItemRegister.STAMP_FLAT);
			case TYPE_BAR:
				return new ItemStack(ItemRegister.STAMP_BAR);
			case TYPE_PLATE:
				return new ItemStack(ItemRegister.STAMP_PLATE);
			default:
				return ItemStack.EMPTY;
		}
	}
}
