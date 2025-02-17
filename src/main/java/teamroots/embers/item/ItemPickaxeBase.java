package teamroots.embers.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.client.model.ModelLoader;
import teamroots.embers.Embers;

public class ItemPickaxeBase extends ItemPickaxe implements IModeledItem {

	public ItemPickaxeBase(ToolMaterial material, String name, boolean addToTab) {
		super(material);
		setTranslationKey(name);
		setRegistryName(Embers.MODID+":"+name);
		if (addToTab){
			setCreativeTab(Embers.tab);
		}
	}
	
	@Override
	public void initModel(){
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
	}
}
