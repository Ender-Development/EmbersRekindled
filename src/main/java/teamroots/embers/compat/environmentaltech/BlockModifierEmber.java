package teamroots.embers.compat.environmentaltech;

import com.valkyrieofnight.et.api.m_multiblocks.m_components.m_modifiers.attribute.AttributeForgeEnergyMultiplier;
import com.valkyrieofnight.et.m_multiblocks.m_components.m_modifiers.block.BlockModifier;
import com.valkyrieofnight.um.api.attribute.IAttribute;
import com.valkyrieofnight.um.api.modifier.ModifierID;
import com.valkyrieofnight.vlib.core.util.client.LangUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.embers.Embers;
import teamroots.embers.config.ConfigCompat;

import java.util.List;

public class BlockModifierEmber extends BlockModifier {
    public static final ModifierID EMBER = new ModifierID(Embers.MODID, "ember");

    public BlockModifierEmber() {
        super("ember", TileModifierEmber.class, new ConfigCategory(""));
    }

    @SideOnly(Side.CLIENT)
    public boolean hasShiftTooltipInfo() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addShiftTooltipInfo(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(LangUtil.toLoc("tooltip.embers.modifier_ember.info"));
    }

    @Override
    public void addAttributes(List<IAttribute> list, ConfigCategory configCategory) {
        list.add(EmbersAttributes.EM_EMBER);
        list.add(new AttributeForgeEnergyMultiplier((float) ConfigCompat.ENVIRONMENTAL_TECH.energy_increase / 100.0F));
    }

    @Override
    public ModifierID getModifierID() {
        return EMBER;
    }

}
