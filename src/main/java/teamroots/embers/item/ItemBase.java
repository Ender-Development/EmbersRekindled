package teamroots.embers.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import teamroots.embers.Embers;

public class ItemBase extends Item implements IModeledItem {
	public ItemBase(String name, boolean addToTab){
		super();
		setTranslationKey(name);
		setRegistryName(new ResourceLocation(Embers.MODID,name));
		if (addToTab){
			setCreativeTab(Embers.tab);
		}
	}
	
	@Override
	public void initModel(){
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
	}
}
