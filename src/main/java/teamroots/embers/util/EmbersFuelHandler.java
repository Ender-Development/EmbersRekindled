package teamroots.embers.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamroots.embers.register.ItemRegister;

public class EmbersFuelHandler {
	@SubscribeEvent
	public static void handleFuel(FurnaceFuelBurnTimeEvent event) {
		ItemStack stack = event.getItemStack();
		if (stack.getItem() == ItemRegister.DUST_ASH)
			event.setBurnTime(200);
		if (stack.getItem() == ItemRegister.DUST_EMBER)
			event.setBurnTime(1600);
	}
}
