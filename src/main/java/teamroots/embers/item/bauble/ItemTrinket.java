package teamroots.embers.item.bauble;

import baubles.api.BaubleType;
import net.minecraftforge.common.MinecraftForge;

public class ItemTrinket extends ItemBaubleBase{
    public ItemTrinket(String name, boolean addToTab) {
        super(name, BaubleType.TRINKET, addToTab);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
