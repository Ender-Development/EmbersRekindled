package p455w0rd.embersified.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import p455w0rd.embersified.blocks.tiles.TileEmitter;
import p455w0rd.embersified.client.render.TESREmitter;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.ItemRegister;

/**
 * @author p455w0rd
 *
 */
@EventBusSubscriber(modid = ModGlobals.MODID)
public class ModEvents {

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onBlockRegistryReady(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModBlocks.getArray());
		BlockRegister.INSTANCE.add(ModBlocks.EMITTER);
		BlockRegister.INSTANCE.add(ModBlocks.RECEPTOR);
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onItemRegistryReady(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModBlocks.getItemBlockArray());
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onModelRegistryReady(ModelRegistryEvent event) {
		ModBlocks.registerModels();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEmitter.class, new TESREmitter());
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public void onRecipeRegistryReady(RegistryEvent.Register<IRecipe> event) {
		//@formatter:off
		event.getRegistry().register(new ShapedOreRecipe(new ResourceLocation("embers", "ember_rf_receiver"), new ItemStack(ModBlocks.RECEPTOR, 4),
				"I I", "CPC", 'I', "ingotIron", 'C', "ingotCopper", 'P', ItemRegister.PLATE_CAMINITE
				).setRegistryName(new ResourceLocation("embers", "ember_rf_receiver")));

		event.getRegistry().register(new ShapedOreRecipe(new ResourceLocation("embers", "ember_rf_emitter"), new ItemStack(Item.getItemFromBlock(ModBlocks.EMITTER), 3),
						" a ", " a ", "bcb", 'b', "ingotIron", 'a', "ingotCopper", 'c', ItemRegister.PLATE_CAMINITE
						).setRegistryName(new ResourceLocation("embers", "ember_rf_emitter")));
		//@formatter:on
	}

}
