package teamroots.embers.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.model.ModelLoader;
import teamroots.embers.Embers;

public class ItemArmorBase extends ItemArmor implements IModeledItem {

	public ItemArmorBase(ArmorMaterial material, int reduction, EntityEquipmentSlot slot, String name, boolean addToTab) {
		super(material, reduction, slot);
		switch(slot){
		case CHEST: {
			setTranslationKey(name+"_chest");
			break;
		}
		case FEET:{
			setTranslationKey(name+"_boots");
			break;
		}
		case HEAD:{
			setTranslationKey(name+"_head");
			break;
		}
		case LEGS:{
			setTranslationKey(name+"_legs");
			break;
		}
		default:
			break;
		}
		this.setMaxStackSize(1);
		
		setRegistryName(Embers.MODID+":"+getTranslationKey().substring(5));
		if (addToTab){
			setCreativeTab(Embers.tab);
		}
	}
	
	@Override
	public void initModel(){
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
	}
}
