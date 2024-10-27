package teamroots.embers.compat.environmentaltech;

import com.valkyrieofnight.et.ETMod;
import com.valkyrieofnight.et.m_multiblocks.m_components.m_modifiers.features.MBlocks;
import com.valkyrieofnight.et.m_multiblocks.m_voidminer.features.VMBlocks;
import com.valkyrieofnight.et.m_resources.features.ETRBlocks;
import com.valkyrieofnight.et.m_resources.features.ETRItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import teamroots.embers.Embers;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.ItemRegister;

import static teamroots.embers.recipe.RecipeRegistry.getRL;

public class EnvironmentalTechIntegration {
    public static Block MODIFIER_EMBER = new BlockModifierEmber();

    public static void registerAll() {
        VMBlocks.getInstance().addBlock(MODIFIER_EMBER);
    }

    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        event.getRegistry().register(new ShapedOreRecipe(new ResourceLocation(Embers.MODID, "modifier_ember"), new ItemStack(MODIFIER_EMBER, 1), true, new Object[]{
                "ABA",
                "CDC",
                "EFE",
                'A', ItemRegister.EMBER_CLUSTER,
                'B', BlockRegister.EMBER_BORE,
                'C', ETRBlocks.MICA,
                'D', MBlocks.MODIFIER_NULL,
                'E', "plateDawnstone",
                'F', ETRItems.LONSDALEITE_CRYSTAL}).setMirrored(true).setRegistryName(getRL("modifier_ember")));
    }
}
