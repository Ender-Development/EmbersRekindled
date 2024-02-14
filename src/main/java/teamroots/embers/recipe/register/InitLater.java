package teamroots.embers.recipe.register;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import teamroots.embers.compat.MysticalMechanicsIntegration;
import teamroots.embers.compat.Util;
import teamroots.embers.config.ConfigMaterial;
import teamroots.embers.config.ConfigMisc;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.ItemRegister;

public class InitLater implements IRegister {
    public static final InitLater INSTANCE = new InitLater();

    private InitLater() {
    }

    @Override
    public void execute() {
        OreDictionary.registerOre("dustAsh", ItemRegister.DUST_ASH);
        OreDictionary.registerOre("dustAshes", ItemRegister.DUST_ASH);
        OreDictionary.registerOre("oreQuartz", BlockRegister.ORE_QUARTZ);
        OreDictionary.registerOre("slimeball", ItemRegister.ADHESIVE);

        if (ConfigMaterial.COPPER.mustLoad()) {
            OreDictionary.registerOre("oreCopper", BlockRegister.ORE_COPPER);
            OreDictionary.registerOre("blockCopper", BlockRegister.BLOCK_COPPER);
            OreDictionary.registerOre("plateCopper", ItemRegister.PLATE_COPPER);
            OreDictionary.registerOre("nuggetCopper", ItemRegister.NUGGET_COPPER);
            OreDictionary.registerOre("ingotCopper", ItemRegister.INGOT_COPPER);
        }

        if (ConfigMaterial.LEAD.mustLoad()) {
            OreDictionary.registerOre("oreLead", BlockRegister.ORE_LEAD);
            OreDictionary.registerOre("blockLead", BlockRegister.BLOCK_LEAD);
            OreDictionary.registerOre("plateLead", ItemRegister.PLATE_LEAD);
            OreDictionary.registerOre("nuggetLead", ItemRegister.NUGGET_LEAD);
            OreDictionary.registerOre("ingotLead", ItemRegister.INGOT_LEAD);
        }

        if (ConfigMaterial.SILVER.mustLoad()) {
            OreDictionary.registerOre("oreSilver", BlockRegister.ORE_SILVER);
            OreDictionary.registerOre("blockSilver", BlockRegister.BLOCK_SILVER);
            OreDictionary.registerOre("plateSilver", ItemRegister.PLATE_SILVER);
            OreDictionary.registerOre("nuggetSilver", ItemRegister.NUGGET_SILVER);
            OreDictionary.registerOre("ingotSilver", ItemRegister.INGOT_SILVER);
        }

        if (ConfigMaterial.DAWNSTONE.mustLoad()) {
            OreDictionary.registerOre("ingotDawnstone", ItemRegister.INGOT_DAWNSTONE);
            OreDictionary.registerOre("nuggetDawnstone", ItemRegister.NUGGET_DAWNSTONE);
            OreDictionary.registerOre("plateDawnstone", ItemRegister.PLATE_DAWNSTONE);
            OreDictionary.registerOre("blockDawnstone", BlockRegister.BLOCK_DAWNSTONE);
        }

        if (ConfigMaterial.IRON.mustLoad()) {
            OreDictionary.registerOre("plateIron", ItemRegister.PLATE_IRON);
        }

        if (ConfigMaterial.GOLD.mustLoad()) {
            OreDictionary.registerOre("plateGold", ItemRegister.PLATE_GOLD);
        }

        if (ConfigMaterial.ALUMINUM.mustLoad()) {
            OreDictionary.registerOre("blockAluminum", BlockRegister.BLOCK_ALUMINUM);
            OreDictionary.registerOre("ingotAluminum", ItemRegister.INGOT_ALUMINUM);
            OreDictionary.registerOre("nuggetAluminum", ItemRegister.NUGGET_ALUMINUM);
            OreDictionary.registerOre("plateAluminum", ItemRegister.PLATE_ALUMINUM);
            OreDictionary.registerOre("oreAluminum", BlockRegister.ORE_ALUMINUM);
        }

        if (ConfigMaterial.TIN.mustLoad()) {
            OreDictionary.registerOre("blockTin", BlockRegister.BLOCK_TIN);
            OreDictionary.registerOre("ingotTin", ItemRegister.INGOT_TIN);
            OreDictionary.registerOre("nuggetTin", ItemRegister.NUGGET_TIN);
            OreDictionary.registerOre("plateTin", ItemRegister.PLATE_TIN);
            OreDictionary.registerOre("oreTin", BlockRegister.ORE_TIN);
        }

        if (ConfigMaterial.NICKEL.mustLoad()) {
            OreDictionary.registerOre("blockNickel", BlockRegister.BLOCK_NICKEL);
            OreDictionary.registerOre("ingotNickel", ItemRegister.INGOT_NICKEL);
            OreDictionary.registerOre("nuggetNickel", ItemRegister.NUGGET_NICKEL);
            OreDictionary.registerOre("plateNickel", ItemRegister.PLATE_NICKEL);
            OreDictionary.registerOre("oreNickel", BlockRegister.ORE_NICKEL);
        }

        if (ConfigMaterial.BRONZE.mustLoad()) {
            OreDictionary.registerOre("blockBronze", BlockRegister.BLOCK_BRONZE);
            OreDictionary.registerOre("ingotBronze", ItemRegister.INGOT_BRONZE);
            OreDictionary.registerOre("nuggetBronze", ItemRegister.NUGGET_BRONZE);
            OreDictionary.registerOre("plateBronze", ItemRegister.PLATE_BRONZE);
        }

        if (ConfigMaterial.ELECTRUM.mustLoad()) {
            OreDictionary.registerOre("blockElectrum", BlockRegister.BLOCK_ELECTRUM);
            OreDictionary.registerOre("ingotElectrum", ItemRegister.INGOT_ELECTRUM);
            OreDictionary.registerOre("nuggetElectrum", ItemRegister.NUGGET_ELECTRUM);
            OreDictionary.registerOre("plateElectrum", ItemRegister.PLATE_ELECTRUM);
        }

        if (ConfigMisc.enableWipStuff) {
            OreDictionary.registerOre("dustSulfur", ItemRegister.SULFUR_DUST);
        }
    }
}

