package teamroots.embers.register;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import teamroots.embers.Embers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ArmorMaterialRegister implements IRegistrable<ItemArmor.ArmorMaterial> {
    public static final ArmorMaterialRegister INSTANCE = new ArmorMaterialRegister();

    private ArmorMaterialRegister() {
    }

    public static final ItemArmor.ArmorMaterial ARMOR_MAT_ASHEN_CLOAK = EnumHelper.addArmorMaterial(
            new ResourceLocation(Embers.MODID, "ashen_cloak").toString(),
            new ResourceLocation(Embers.MODID, "ashen_cloak").toString(),
            19, new int[]{3, 5, 7, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);

    private final static List<ItemArmor.ArmorMaterial> REGISTRY = new ArrayList<>(Collections.singletonList(
            ARMOR_MAT_ASHEN_CLOAK
    ));

    static {
        assert ARMOR_MAT_ASHEN_CLOAK != null;
        ARMOR_MAT_ASHEN_CLOAK.repairMaterial = new ItemStack(ItemRegister.ASHEN_CLOTH, 1);
    }
    @Override
    public Collection<ItemArmor.ArmorMaterial> load() {
        return REGISTRY;
    }

    @Override
    public void register() {

    }
}
