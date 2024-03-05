package teamroots.embers.recipe.register;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.Tuple;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import teamroots.embers.api.EmbersAPI;
import teamroots.embers.config.ConfigMachine;
import teamroots.embers.config.ConfigMisc;
import teamroots.embers.config.ConfigTool;
import teamroots.embers.recipe.BoreOutput;
import teamroots.embers.register.ItemRegister;
import teamroots.embers.util.AlchemyUtil;
import teamroots.embers.util.WeightedItemStack;

import java.awt.*;

import static teamroots.embers.recipe.RecipeRegistry.setDefaultBoreOutput;
import static teamroots.embers.recipe.Util.registerCoefficient;

public class InitEarly implements IRegister{
    public static final InitEarly INSTANCE = new InitEarly();

    private InitEarly() {
    }

    public void execute() {

        AlchemyUtil.registerAspect("iron", Ingredient.fromItem(ItemRegister.ASPECTUS_IRON));
        AlchemyUtil.registerAspect("copper", Ingredient.fromItem(ItemRegister.ASPECTUS_COPPER));
        AlchemyUtil.registerAspect("dawnstone", Ingredient.fromItem(ItemRegister.ASPECTUS_DAWNSTONE));
        AlchemyUtil.registerAspect("lead", Ingredient.fromItem(ItemRegister.ASPECTUS_LEAD));
        AlchemyUtil.registerAspect("silver", Ingredient.fromItem(ItemRegister.ASPECTUS_SILVER));

        BoreOutput defaultOutput = new BoreOutput(Sets.newHashSet(), Sets.newHashSet(), Lists.newArrayList(
                new WeightedItemStack(new ItemStack(ItemRegister.CRYSTAL_EMBER), 20),
                new WeightedItemStack(new ItemStack(ItemRegister.SHARD_EMBER), 60),
                new WeightedItemStack(new ItemStack(ItemRegister.DUST_EMBER), 20)
        ));
        setDefaultBoreOutput(defaultOutput);

        if (ConfigTool.METAL_TOOL.DAWNSTONE.register) {
            Ingredient anyDawnstoneTool = Ingredient.fromItems(ItemRegister.AXE_DAWNSTONE, ItemRegister.HOE_DAWNSTONE, ItemRegister.SWORD_DAWNSTONE, ItemRegister.SHOVEL_DAWNSTONE, ItemRegister.PICKAXE_DAWNSTONE);
            EmbersAPI.registerEmberToolEffeciency(anyDawnstoneTool, 2.0);
        }

        for (String s : ConfigMisc.ember_fuel) {
            String[] tokens = s.split(";");
            Item fuel = Item.getByNameOrId(tokens[0]);
            if (tokens.length == 2 && fuel != null) {
                EmbersAPI.registerEmberFuel(Ingredient.fromItem(fuel), Integer.parseInt(tokens[1]));
            }
        }

        for (String s : ConfigMisc.block_coefficient) {
            String[] tokens = s.split(";");
            if (tokens.length == 2 && !OreDictionary.getOres(tokens[0]).isEmpty()) {
                EmbersAPI.registerMetalCoefficient(tokens[0], Double.parseDouble(tokens[1]));
            }
        }

        for (String s : ConfigMachine.COMBUSTOR.fuel) {
            Tuple<Ingredient, Double> result = registerCoefficient(s);
            EmbersAPI.registerCombustionFuel(result.getFirst(), result.getSecond());
        }

        for (String s : ConfigMachine.CATALYZER.fuel) {
            Tuple<Ingredient, Double> result = registerCoefficient(s);
            EmbersAPI.registerCatalysisFuel(result.getFirst(), result.getSecond());
        }

        EmbersAPI.registerBoilerFluid(FluidRegistry.WATER, FluidRegistry.getFluid("steam"), 5.0, new Color(255, 255, 255));
        EmbersAPI.registerBoilerFluid(FluidRegistry.getFluid("oil_dwarf"), FluidRegistry.getFluid("gas_dwarf"), 1.0, new Color(192, 255, 128));

        EmbersAPI.registerSteamEngineFuel(FluidRegistry.getFluid("steam"), 2.0, 1, new Color(255, 255, 255));
        EmbersAPI.registerSteamEngineFuel(FluidRegistry.getFluid("gas_dwarf"), 2.5, 5, new Color(128, 192, 255));
    }
}
