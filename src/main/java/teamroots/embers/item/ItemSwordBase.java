package teamroots.embers.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.model.ModelLoader;
import teamroots.embers.Embers;

public class ItemSwordBase extends ItemSword implements IModeledItem {

	public ItemSwordBase(ToolMaterial material, String name, boolean addToTab) {
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
