package teamroots.embers.recipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import teamroots.embers.Embers;
import teamroots.embers.api.EmbersAPI;
import teamroots.embers.api.alchemy.AspectList;
import teamroots.embers.api.alchemy.AspectList.AspectRangeList;
import teamroots.embers.api.capabilities.EmbersCapabilities;
import teamroots.embers.api.filter.*;
import teamroots.embers.api.itemmod.ItemModUtil;
import teamroots.embers.api.power.IEmberCapability;
import teamroots.embers.block.BlockSeedNew;
import teamroots.embers.compat.BaublesIntegration;
import teamroots.embers.compat.MysticalMechanicsIntegration;
import teamroots.embers.compat.Util;
import teamroots.embers.config.ConfigMachine;
import teamroots.embers.config.ConfigMaterial;
import teamroots.embers.item.EnumStampType;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.FluidRegister;
import teamroots.embers.register.ItemRegister;
import teamroots.embers.util.AlchemyUtil;
import teamroots.embers.util.FilterUtil;
import teamroots.embers.util.IngredientSpecial;
import teamroots.embers.util.WeightedItemStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeRegistry {
	public static ArrayList<ItemMeltingRecipe> meltingRecipes = new ArrayList<>();
	@Deprecated
	public static ArrayList<ItemMeltingOreRecipe> meltingOreRecipes = new ArrayList<>();

	public static ArrayList<ItemStampingRecipe> stampingRecipes = new ArrayList<>();
	@Deprecated
	public static ArrayList<ItemStampingOreRecipe> stampingOreRecipes = new ArrayList<>();

	public static ArrayList<FluidMixingRecipe> mixingRecipes = new ArrayList<>();

	public static ArrayList<FluidReactionRecipe> fluidReactionRecipes = new ArrayList<>();

	public static ArrayList<AlchemyRecipe> alchemyRecipes = new ArrayList<>();

	public static ArrayList<DawnstoneAnvilRecipe> dawnstoneAnvilRecipes = new ArrayList<>();
	public static ArrayList<Ingredient> dawnstoneRepairBlacklist = new ArrayList<>();
	public static ArrayList<Ingredient> dawnstoneMateriaBlacklist = new ArrayList<>();
	public static ArrayList<Ingredient> dawnstoneBreakdownBlacklist = new ArrayList<>();

	public static ArrayList<HeatCoilRecipe> heatCoilRecipes = new ArrayList<>();

	public static ArrayList<BoreOutput> boreOutputSets = new ArrayList<>();
	public static BoreOutput defaultBoreOutput;
	public static final int INGOT_AMOUNT = ConfigMachine.ingotFluidAmount;
	public static final int NUGGET_AMOUNT = ConfigMachine.nuggetFluidAmount;

	public static ResourceLocation getRL(String s){
    	return new ResourceLocation(Embers.MODID,s);
    }

	public static void registerBoreOutput(BoreOutput output) {
		boreOutputSets.add(output);
	}

	public static void setDefaultBoreOutput(BoreOutput output) {
		defaultBoreOutput = output;
	}

	public static void registerMaterialSet(RegistryEvent.Register<IRecipe> event, String ingotKey, String nuggetKey, String blockKey,
		Item ingot, Item nugget, Item plate, Block block, Item pickaxe, Item axe, Item shovel, Item hoe, Item sword){
		event.getRegistry().register(new ShapedOreRecipe(getRL(ingotKey+"_block"),new ItemStack(block),true,new Object[]{
				"XXX",
				"XXX",
				"XXX",
				'X', ingotKey}).setRegistryName(getRL(ingotKey+"_block")));
		event.getRegistry().register(new ShapedOreRecipe(getRL(ingotKey+"_ingot"),new ItemStack(ingot),true,new Object[]{
				"XXX",
				"XXX",
				"XXX",
				'X', nuggetKey}).setRegistryName(getRL(ingotKey+"_ingot")));
		event.getRegistry().register(new ShapelessOreRecipe(getRL(ingotKey+"_block_decomp"),new ItemStack(ingot,9),new Object[]{
				blockKey}).setRegistryName(getRL(ingotKey+"_block_decomp")));
		event.getRegistry().register(new ShapelessOreRecipe(getRL(ingotKey+"_ingot_decomp"),new ItemStack(nugget,9),new Object[]{
				ingotKey}).setRegistryName(getRL(ingotKey+"_ingot_decomp")));
		event.getRegistry().register(new ShapedOreRecipe(getRL(ingotKey+"_sword"),new ItemStack(sword,1),true,new Object[]{
				" C ",
				" C ",
				" S ",
				'C', ingotKey,
				'S', "stickWood"}).setRegistryName(getRL(ingotKey+"_sword")));
		event.getRegistry().register(new ShapedOreRecipe(getRL(ingotKey+"_pickaxe"),new ItemStack(pickaxe,1),true,new Object[]{
				"CCC",
				" S ",
				" S ",
				'C', ingotKey,
				'S', "stickWood"}).setRegistryName(getRL(ingotKey+"_pickaxe")));
		event.getRegistry().register(new ShapedOreRecipe(getRL(ingotKey+"_shovel"),new ItemStack(shovel,1),true,new Object[]{
				" C ",
				" S ",
				" S ",
				'C', ingotKey,
				'S', "stickWood"}).setRegistryName(getRL(ingotKey+"_shovel")));
		event.getRegistry().register(new ShapedOreRecipe(getRL(ingotKey+"_axe"),new ItemStack(axe,1),true,new Object[]{
				" CC",
				" SC",
				" S ",
				'C', ingotKey,
				'S', "stickWood"}).setMirrored(true).setRegistryName(getRL(ingotKey+"_axe")));
		event.getRegistry().register(new ShapedOreRecipe(getRL(ingotKey+"_hoe"),new ItemStack(hoe,1),true,new Object[]{
				" CC",
				" S ",
				" S ",
				'C', ingotKey,
				'S', "stickWood"}).setMirrored(true).setRegistryName(getRL(ingotKey+"_hoe")));
		event.getRegistry().register(new ShapelessOreRecipe(getRL(ingotKey+"_plate"),new ItemStack(plate,1),new Object[]{
				ingotKey, ingotKey, ItemRegister.TINKER_HAMMER}).setRegistryName(getRL(ingotKey+"_plate")));
	}

	public void initOreDict(){
		OreDictionary.registerOre("dustAsh", ItemRegister.DUST_ASH);
		OreDictionary.registerOre("dustAshes", ItemRegister.DUST_ASH);
		//OreDictionary.registerOre("nuggetIron", RegistryManager.nugget_iron);
		OreDictionary.registerOre("ingotCopper", ItemRegister.INGOT_COPPER);
		OreDictionary.registerOre("ingotLead", ItemRegister.INGOT_LEAD);
		OreDictionary.registerOre("ingotSilver", ItemRegister.INGOT_SILVER);
		OreDictionary.registerOre("ingotDawnstone", ItemRegister.INGOT_DAWNSTONE);
		OreDictionary.registerOre("nuggetCopper", ItemRegister.NUGGET_COPPER);
		OreDictionary.registerOre("nuggetLead", ItemRegister.NUGGET_LEAD);
		OreDictionary.registerOre("nuggetSilver", ItemRegister.NUGGET_SILVER);
		OreDictionary.registerOre("nuggetDawnstone", ItemRegister.NUGGET_DAWNSTONE);
		OreDictionary.registerOre("plateGold", ItemRegister.PLATE_GOLD);
		OreDictionary.registerOre("plateIron", ItemRegister.PLATE_IRON);
		OreDictionary.registerOre("plateCopper", ItemRegister.PLATE_COPPER);
		OreDictionary.registerOre("plateLead", ItemRegister.PLATE_LEAD);
		OreDictionary.registerOre("plateSilver", ItemRegister.PLATE_SILVER);
		OreDictionary.registerOre("plateDawnstone", ItemRegister.PLATE_DAWNSTONE);
		OreDictionary.registerOre("blockCopper", BlockRegister.BLOCK_COPPER);
		OreDictionary.registerOre("blockLead", BlockRegister.BLOCK_LEAD);
		OreDictionary.registerOre("blockSilver", BlockRegister.BLOCK_SILVER);
		OreDictionary.registerOre("blockDawnstone", BlockRegister.BLOCK_DAWNSTONE);
		OreDictionary.registerOre("oreCopper", BlockRegister.ORE_COPPER);
		OreDictionary.registerOre("oreLead", BlockRegister.ORE_LEAD);
		OreDictionary.registerOre("oreSilver", BlockRegister.ORE_SILVER);
		OreDictionary.registerOre("oreQuartz", BlockRegister.ORE_QUARTZ);
		OreDictionary.registerOre("slimeball", ItemRegister.ADHESIVE);

		if (ConfigMaterial.ALUMINUM.mustLoad()){
			OreDictionary.registerOre("blockAluminum", BlockRegister.BLOCK_ALUMINUM);
			OreDictionary.registerOre("ingotAluminum", ItemRegister.INGOT_ALUMINUM);
			OreDictionary.registerOre("nuggetAluminum", ItemRegister.NUGGET_ALUMINUM);
			OreDictionary.registerOre("plateAluminum", ItemRegister.PLATE_ALUMINUM);
			OreDictionary.registerOre("oreAluminum", BlockRegister.ORE_ALUMINUM);

			OreDictionary.registerOre("blockAluminium", BlockRegister.BLOCK_ALUMINUM);
			OreDictionary.registerOre("ingotAluminium", ItemRegister.INGOT_ALUMINUM);
			OreDictionary.registerOre("nuggetAluminium", ItemRegister.NUGGET_ALUMINUM);
			OreDictionary.registerOre("plateAluminium", ItemRegister.PLATE_ALUMINUM);
			OreDictionary.registerOre("oreAluminium", BlockRegister.ORE_ALUMINUM);
		}

		if (ConfigMaterial.TIN.mustLoad()){
			OreDictionary.registerOre("blockTin", BlockRegister.BLOCK_TIN);
			OreDictionary.registerOre("ingotTin", ItemRegister.INGOT_TIN);
			OreDictionary.registerOre("nuggetTin", ItemRegister.NUGGET_TIN);
			OreDictionary.registerOre("plateTin", ItemRegister.PLATE_TIN);
			OreDictionary.registerOre("oreTin", BlockRegister.ORE_TIN);
		}

		if (ConfigMaterial.NICKEL.mustLoad()){
			OreDictionary.registerOre("blockNickel", BlockRegister.BLOCK_NICKEL);
			OreDictionary.registerOre("ingotNickel", ItemRegister.INGOT_NICKEL);
			OreDictionary.registerOre("nuggetNickel", ItemRegister.NUGGET_NICKEL);
			OreDictionary.registerOre("plateNickel", ItemRegister.PLATE_NICKEL);
			OreDictionary.registerOre("oreNickel", BlockRegister.ORE_NICKEL);
		}

		if (ConfigMaterial.BRONZE.mustLoad()){
			OreDictionary.registerOre("blockBronze", BlockRegister.BLOCK_BRONZE);
			OreDictionary.registerOre("ingotBronze", ItemRegister.INGOT_BRONZE);
			OreDictionary.registerOre("nuggetBronze", ItemRegister.NUGGET_BRONZE);
			OreDictionary.registerOre("plateBronze", ItemRegister.PLATE_BRONZE);
		}

		if (ConfigMaterial.ELECTRUM.mustLoad()){
			OreDictionary.registerOre("blockElectrum", BlockRegister.BLOCK_ELECTRUM);
			OreDictionary.registerOre("ingotElectrum", ItemRegister.INGOT_ELECTRUM);
			OreDictionary.registerOre("nuggetElectrum", ItemRegister.NUGGET_ELECTRUM);
			OreDictionary.registerOre("plateElectrum", ItemRegister.PLATE_ELECTRUM);
		}
	}

	public static void mergeOreRecipes() {
		stampingRecipes.addAll(stampingOreRecipes);
		meltingRecipes.addAll(meltingOreRecipes);
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public void initLater(RegistryEvent.Register<Item> event){
		initOreDict();

		if(Util.isMysticalMechanicsIntegrationEnabled())
			MysticalMechanicsIntegration.initOreDict();
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void initEarly(RegistryEvent.Register<IRecipe> event) {
		//initOreDict();

		AlchemyUtil.registerAspect("iron", Ingredient.fromItem(ItemRegister.ASPECTUS_IRON));
		AlchemyUtil.registerAspect("copper", Ingredient.fromItem(ItemRegister.ASPECTUS_COPPER));
		AlchemyUtil.registerAspect("dawnstone", Ingredient.fromItem(ItemRegister.ASPECTUS_DAWNSTONE));
		AlchemyUtil.registerAspect("lead", Ingredient.fromItem(ItemRegister.ASPECTUS_LEAD));
		AlchemyUtil.registerAspect("silver", Ingredient.fromItem(ItemRegister.ASPECTUS_SILVER));

		BoreOutput defaultOutput = new BoreOutput(Sets.newHashSet(), Sets.newHashSet(), Lists.newArrayList(
				new WeightedItemStack(new ItemStack(ItemRegister.CRYSTAL_EMBER),20),
				new WeightedItemStack(new ItemStack(ItemRegister.SHARD_EMBER),60),
				new WeightedItemStack(new ItemStack(ItemRegister.DUST_EMBER),20)
		));
		setDefaultBoreOutput(defaultOutput);

		EmbersAPI.registerEmberFuel(Ingredient.fromItem(ItemRegister.DUST_EMBER),0);
		EmbersAPI.registerEmberFuel(Ingredient.fromItem(ItemRegister.SHARD_EMBER),400);
		EmbersAPI.registerEmberFuel(Ingredient.fromItem(ItemRegister.CRYSTAL_EMBER),2400);
		EmbersAPI.registerEmberFuel(Ingredient.fromItem(ItemRegister.EMBER_CLUSTER),3600);

		Ingredient anyDawnstoneTool = Ingredient.fromItems(ItemRegister.AXE_DAWNSTONE, ItemRegister.HOE_DAWNSTONE, ItemRegister.SWORD_DAWNSTONE, ItemRegister.SHOVEL_DAWNSTONE, ItemRegister.PICKAXE_DAWNSTONE);
		EmbersAPI.registerEmberToolEffeciency(anyDawnstoneTool, 2.0);
		// TODO -> These can all be made into config options
		EmbersAPI.registerMetalCoefficient("blockGold",1.0);
		EmbersAPI.registerMetalCoefficient("blockSilver",1.0);
		EmbersAPI.registerMetalCoefficient("blockCopper",1.0);
		if (ConfigMaterial.ELECTRUM.isNotOff()){
			EmbersAPI.registerMetalCoefficient("blockElectrum",1.0);
		}
		if (ConfigMaterial.ALUMINUM.isNotOff()){
			EmbersAPI.registerMetalCoefficient("blockAluminum",0.9);
		}
		if (ConfigMaterial.NICKEL.isNotOff()){
			EmbersAPI.registerMetalCoefficient("blockNickel",0.9);
		}
		if (ConfigMaterial.TIN.isNotOff()){
			EmbersAPI.registerMetalCoefficient("blockTin",0.9);
		}
		EmbersAPI.registerMetalCoefficient("blockIron",0.75);
		EmbersAPI.registerMetalCoefficient("blockLead",0.75);
		if (ConfigMaterial.BRONZE.isNotOff()){
			EmbersAPI.registerMetalCoefficient("blockBronze",0.75);
		}

		EmbersAPI.registerCombustionFuel(Ingredient.fromItem(Items.COAL),2.0);
		EmbersAPI.registerCombustionFuel(new OreIngredient("ingotBrickNether"),3.0);
		EmbersAPI.registerCombustionFuel(Ingredient.fromItem(Items.BLAZE_POWDER),4.0);

		EmbersAPI.registerCatalysisFuel(new OreIngredient("dustRedstone"),2.0);
		EmbersAPI.registerCatalysisFuel(new OreIngredient("gunpowder"),3.0);
		EmbersAPI.registerCatalysisFuel(new OreIngredient("dustGlowstone"),4.0);

		EmbersAPI.registerBoilerFluid(FluidRegistry.WATER,FluidRegistry.getFluid("steam"),5.0, new Color(255,255,255));
		EmbersAPI.registerBoilerFluid(FluidRegistry.getFluid("oil_dwarf"), FluidRegistry.getFluid("gas_dwarf"),1.0, new Color(192, 255, 128));

		EmbersAPI.registerSteamEngineFuel(FluidRegistry.getFluid("steam"),2.0,1, new Color(255,255,255));
		EmbersAPI.registerSteamEngineFuel(FluidRegistry.getFluid("gas_dwarf"),2.5,5, new Color(128,192,255));
	}

	@SubscribeEvent
	public void init(RegistryEvent.Register<IRecipe> event){
		//((ForgeRegistry<IRecipe>)event.getRegistry()).remove(new ResourceLocation("brewing_stand"));

		event.getRegistry().register(new ShapedOreRecipe(getRL("crystal_ember"),new ItemStack(ItemRegister.CRYSTAL_EMBER,1),true,new Object[]{
				"XXX",
				"XXX",
				'X', ItemRegister.SHARD_EMBER}).setRegistryName(getRL("crystal_ember")));
		event.getRegistry().register(new ShapelessOreRecipe(getRL("shard_ember"),new ItemStack(ItemRegister.SHARD_EMBER,6),new Object[]{
				ItemRegister.CRYSTAL_EMBER}).setRegistryName(getRL("shard_ember")));

		//event.getRegistry().register(new ShapelessOreRecipe(getRL("iron_nugget"),new ItemStack(RegistryManager.nugget_iron,9),new Object[]{"ingotIron"}).setRegistryName(getRL("iron_nugget")));


		event.getRegistry().register(new ShapelessOreRecipe(getRL("plate_gold"),new ItemStack(ItemRegister.PLATE_GOLD,1),new Object[]{
		"ingotGold","ingotGold", ItemRegister.TINKER_HAMMER}).setRegistryName(getRL("plate_gold")));
		event.getRegistry().register(new ShapelessOreRecipe(getRL("plate_iron"),new ItemStack(ItemRegister.PLATE_IRON,1),new Object[]{
		"ingotIron","ingotIron", ItemRegister.TINKER_HAMMER}).setRegistryName(getRL("plate_iron")));

		RecipeRegistry.registerMaterialSet(event,"ingotLead", "nuggetLead", "blockLead",
				ItemRegister.INGOT_LEAD,
				ItemRegister.NUGGET_LEAD,
				ItemRegister.PLATE_LEAD,
				BlockRegister.BLOCK_LEAD,
				ItemRegister.PICKAXE_LEAD,
				ItemRegister.AXE_LEAD,
				ItemRegister.SHOVEL_LEAD,
				ItemRegister.HOE_LEAD,
				ItemRegister.SWORD_LEAD);

		RecipeRegistry.registerMaterialSet(event,"ingotCopper", "nuggetCopper", "blockCopper",
				ItemRegister.INGOT_COPPER,
				ItemRegister.NUGGET_COPPER,
				ItemRegister.PLATE_COPPER,
				BlockRegister.BLOCK_COPPER,
				ItemRegister.PICKAXE_COPPER,
				ItemRegister.AXE_COPPER,
				ItemRegister.SHOVEL_COPPER,
				ItemRegister.HOE_COPPER,
				ItemRegister.SWORD_COPPER);

		RecipeRegistry.registerMaterialSet(event,"ingotSilver", "nuggetSilver", "blockSilver",
				ItemRegister.INGOT_SILVER,
				ItemRegister.NUGGET_SILVER,
				ItemRegister.PLATE_SILVER,
				BlockRegister.BLOCK_SILVER,
				ItemRegister.PICKAXE_SILVER,
				ItemRegister.AXE_SILVER,
				ItemRegister.SHOVEL_SILVER,
				ItemRegister.HOE_SILVER,
				ItemRegister.SWORD_SILVER);

		RecipeRegistry.registerMaterialSet(event,"ingotDawnstone", "nuggetDawnstone", "blockDawnstone",
				ItemRegister.INGOT_DAWNSTONE,
				ItemRegister.NUGGET_DAWNSTONE,
				ItemRegister.PLATE_DAWNSTONE,
				BlockRegister.BLOCK_DAWNSTONE,
				ItemRegister.PICKAXE_DAWNSTONE,
				ItemRegister.AXE_DAWNSTONE,
				ItemRegister.SHOVEL_DAWNSTONE,
				ItemRegister.HOE_DAWNSTONE,
				ItemRegister.SWORD_DAWNSTONE);

		if (ConfigMaterial.ALUMINUM.mustLoad()){
			RecipeRegistry.registerMaterialSet(event,"ingotAluminum", "nuggetAluminum", "blockAluminum",
					ItemRegister.INGOT_ALUMINUM,
					ItemRegister.NUGGET_ALUMINUM,
					ItemRegister.PLATE_ALUMINUM,
					BlockRegister.BLOCK_ALUMINUM,
					ItemRegister.PICKAXE_ALUMINUM,
					ItemRegister.AXE_ALUMINUM,
					ItemRegister.SHOVEL_ALUMINUM,
					ItemRegister.HOE_ALUMINUM,
					ItemRegister.SWORD_ALUMINUM);
		}

		if (ConfigMaterial.BRONZE.mustLoad()){
			RecipeRegistry.registerMaterialSet(event,"ingotBronze", "nuggetBronze", "blockBronze",
					ItemRegister.INGOT_BRONZE,
					ItemRegister.NUGGET_BRONZE,
					ItemRegister.PLATE_BRONZE,
					BlockRegister.BLOCK_BRONZE,
					ItemRegister.PICKAXE_BRONZE,
					ItemRegister.AXE_BRONZE,
					ItemRegister.SHOVEL_BRONZE,
					ItemRegister.HOE_BRONZE,
					ItemRegister.SWORD_BRONZE);
		}

		if (ConfigMaterial.ELECTRUM.mustLoad()){
			RecipeRegistry.registerMaterialSet(event,"ingotElectrum", "nuggetElectrum", "blockElectrum",
					ItemRegister.INGOT_ELECTRUM,
					ItemRegister.NUGGET_ELECTRUM,
					ItemRegister.PLATE_ELECTRUM,
					BlockRegister.BLOCK_ELECTRUM,
					ItemRegister.PICKAXE_ELECTRUM,
					ItemRegister.AXE_ELECTRUM,
					ItemRegister.SHOVEL_ELECTRUM,
					ItemRegister.HOE_ELECTRUM,
					ItemRegister.SWORD_ELECTRUM);
		}

		if (ConfigMaterial.NICKEL.mustLoad()){
			RecipeRegistry.registerMaterialSet(event,"ingotNickel", "nuggetNickel", "blockNickel",
					ItemRegister.INGOT_NICKEL,
					ItemRegister.NUGGET_NICKEL,
					ItemRegister.PLATE_NICKEL,
					BlockRegister.BLOCK_NICKEL,
					ItemRegister.PICKAXE_NICKEL,
					ItemRegister.AXE_NICKEL,
					ItemRegister.SHOVEL_NICKEL,
					ItemRegister.HOE_NICKEL,
					ItemRegister.SWORD_NICKEL);
		}

		if (ConfigMaterial.TIN.mustLoad()){
			RecipeRegistry.registerMaterialSet(event,"ingotTin", "nuggetTin", "blockTin",
					ItemRegister.INGOT_TIN,
					ItemRegister.NUGGET_TIN,
					ItemRegister.PLATE_TIN,
					BlockRegister.BLOCK_TIN,
					ItemRegister.PICKAXE_TIN,
					ItemRegister.AXE_TIN,
					ItemRegister.SHOVEL_TIN,
					ItemRegister.HOE_TIN,
					ItemRegister.SWORD_TIN);
		}

		event.getRegistry().register(new ShapelessOreRecipe(getRL("blend_caminite"),new ItemStack(ItemRegister.BLEND_CAMINITE,8),new Object[]{
		Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL,"sand"}).setRegistryName(getRL("blend_caminite")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("plate_caminite_raw"),new ItemStack(ItemRegister.PLATE_CAMINITE_RAW,1),true,new Object[]{
				"XX",
				"XX",
				'X', ItemRegister.BLEND_CAMINITE}).setRegistryName(getRL("plate_caminite_raw")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("stamp_bar_raw"),new ItemStack(ItemRegister.STAMP_BAR_RAW,1),true,new Object[]{
				" X ",
				"X X",
				" X ",
				'X', ItemRegister.BLEND_CAMINITE}).setRegistryName(getRL("stamp_bar_raw")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("stamp_flat_raw"),new ItemStack(ItemRegister.STAMP_FLAT_RAW,1),true,new Object[]{
				"XXX",
				"X X",
				"XXX",
				'X', ItemRegister.BLEND_CAMINITE}).setRegistryName(getRL("stamp_flat_raw")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("stamp_plate_raw"),new ItemStack(ItemRegister.STAMP_PLATE_RAW,1),true,new Object[]{
				"X X",
				"   ",
				"X X",
				'X', ItemRegister.BLEND_CAMINITE}).setRegistryName(getRL("stamp_plate_raw")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("stamp_gear_raw"),new ItemStack(ItemRegister.STAMP_GEAR_RAW,1),true,new Object[]{
				"X X",
				" X ",
				"X X",
				'X', ItemRegister.BLEND_CAMINITE}).setRegistryName(getRL("stamp_gear_raw")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("block_caminite_brick"),new ItemStack(BlockRegister.BLOCK_CAMINITE_BRICK,1),true,new Object[]{
				"XX",
				"XX",
				'X', ItemRegister.BRICK_CAMINITE}).setRegistryName(getRL("block_caminite_brick")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("block_caminite_brick_slab"),new ItemStack(BlockRegister.BLOCK_CAMINITE_BRICK_SLAB,6),true,new Object[]{
				"XXX",
				'X', BlockRegister.BLOCK_CAMINITE_BRICK}).setRegistryName(getRL("block_caminite_brick_slab")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("stairs_caminite_brick"),new ItemStack(BlockRegister.STAIRS_CAMINITE_BRICK,4),true,new Object[]{
				"X  ",
				"XX ",
				"XXX",
				'X', BlockRegister.BLOCK_CAMINITE_BRICK}).setRegistryName(getRL("stairs_caminite_brick")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("wall_caminite_brick"),new ItemStack(BlockRegister.WALL_CAMINITE_BRICK,6),true,new Object[]{
				"XXX",
				"XXX",
				'X', BlockRegister.BLOCK_CAMINITE_BRICK}).setRegistryName(getRL("wall_caminite_brick")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ashen_stone"),new ItemStack(BlockRegister.ASHEN_STONE,4),true,new Object[]{
				" S ",
				"SAS",
				" S ",
				'S', "stone",
				'A', "dustAsh"}).setRegistryName(getRL("ashen_stone")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ashen_stone_slab"),new ItemStack(BlockRegister.ASHEN_STONE_SLAB,6),true,new Object[]{
				"XXX",
				'X', BlockRegister.ASHEN_STONE}).setRegistryName(getRL("ashen_stone_slab")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("stairs_ashen_stone"),new ItemStack(BlockRegister.STAIRS_ASHEN_STONE,4),true,new Object[]{
				"X  ",
				"XX ",
				"XXX",
				'X', BlockRegister.ASHEN_STONE}).setRegistryName(getRL("stairs_ashen_stone")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("wall_ashen_stone"),new ItemStack(BlockRegister.WALL_ASHEN_STONE,6),true,new Object[]{
				"XXX",
				"XXX",
				'X', BlockRegister.ASHEN_STONE}).setRegistryName(getRL("wall_ashen_stone")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ashen_brick"),new ItemStack(BlockRegister.WALL_ASHEN_BRICK,4),true,new Object[]{
				" S ",
				"SAS",
				" S ",
				'S', Blocks.STONEBRICK,
				'A', "dustAsh"}).setRegistryName(getRL("ashen_brick")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ashen_brick"),new ItemStack(BlockRegister.ASHEN_BRICK,4),true,new Object[]{
				"SS",
				"SS",
				'S', BlockRegister.ASHEN_STONE}).setRegistryName(getRL("ashen_brick2")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ashen_brick_slab"),new ItemStack(BlockRegister.ASHEN_BRICK_SLAB,6),true,new Object[]{
				"XXX",
				'X', BlockRegister.ASHEN_BRICK}).setRegistryName(getRL("ashen_brick_slab")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("stairs_ashen_brick"),new ItemStack(BlockRegister.STAIRS_ASHEN_BRICK,4),true,new Object[]{
				"X  ",
				"XX ",
				"XXX",
				'X', BlockRegister.ASHEN_BRICK}).setRegistryName(getRL("stairs_ashen_brick")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("wall_ashen_brick"),new ItemStack(BlockRegister.WALL_ASHEN_BRICK,6),true,new Object[]{
				"XXX",
				"XXX",
				'X', BlockRegister.ASHEN_BRICK}).setRegistryName(getRL("wall_ashen_brick")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ashen_tile"),new ItemStack(BlockRegister.ASHEN_TILE,4),true,new Object[]{
				"SS",
				"SS",
				'S', BlockRegister.ASHEN_BRICK}).setRegistryName(getRL("ashen_tile")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ashen_tile_slab"),new ItemStack(BlockRegister.ASHEN_TILE_SLAB,6),true,new Object[]{
				"XXX",
				'X', BlockRegister.ASHEN_TILE}).setRegistryName(getRL("ashen_tile_slab")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("stairs_ashen_tile"),new ItemStack(BlockRegister.STAIRS_ASHEN_TILE,4),true,new Object[]{
				"X  ",
				"XX ",
				"XXX",
				'X', BlockRegister.ASHEN_TILE}).setRegistryName(getRL("stairs_ashen_tile")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("wall_ashen_tile"),new ItemStack(BlockRegister.WALL_ASHEN_TILE,6),true,new Object[]{
				"XXX",
				"XXX",
				'X', BlockRegister.ASHEN_TILE}).setRegistryName(getRL("wall_ashen_tile")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ashen_stone"),new ItemStack(BlockRegister.ASHEN_STONE,4),true,new Object[]{
				"SS",
				"SS",
				'S', BlockRegister.ASHEN_TILE}).setRegistryName(getRL("ashen_stone2")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("block_tank"),new ItemStack(BlockRegister.BLOCK_TANK,1),true,new Object[]{
				"B B",
				"P P",
				"BIB",
				'I', "ingotIron",
				'P', "plateIron",
				'B', ItemRegister.BRICK_CAMINITE}).setRegistryName(getRL("block_tank")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("pipe"),new ItemStack(BlockRegister.PIPE,8),true,new Object[]{
				"IPI",
				'P', "plateIron",
				'I', "ingotIron"}).setRegistryName(getRL("pipe")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("pump"),new ItemStack(BlockRegister.PIPE,1),true,new Object[]{
				" R ",
				"PBP",
				" R ",
				'P', BlockRegister.PIPE,
				'B', ItemRegister.PLATE_CAMINITE,
				'R', "dustRedstone"}).setRegistryName(getRL("pump")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("block_furnace"),new ItemStack(BlockRegister.BLOCK_FURNACE,1),true,new Object[]{
				"BPB",
				"BCB",
				"IFI",
				'P', ItemRegister.PLATE_CAMINITE,
				'B', ItemRegister.BRICK_CAMINITE,
				'F', Blocks.FURNACE,
				'I', "ingotIron",
				'C', "ingotCopper"}).setRegistryName(getRL("block_furnace")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_receiver"),new ItemStack(BlockRegister.EMBER_RECEIVER,4),true,new Object[]{
				"I I",
				"CPC",
				'I', "ingotIron",
				'C', "ingotCopper",
				'P', ItemRegister.PLATE_CAMINITE}).setRegistryName(getRL("ember_receiver")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_emitter"),new ItemStack(BlockRegister.EMBER_EMITTER,4),true,new Object[]{
				" C ",
				" C ",
				"IPI",
				'I', "ingotIron",
				'C', "ingotCopper",
				'P', ItemRegister.PLATE_CAMINITE}).setRegistryName(getRL("ember_emitter")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("copper_cell"),new ItemStack(BlockRegister.COPPER_CELL,1),true,new Object[]{
				"BIB",
				"ICI",
				"BIB",
				'I', "ingotIron",
				'C', "blockCopper",
				'B', BlockRegister.BLOCK_CAMINITE_BRICK}).setRegistryName(getRL("copper_cell")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("item_pipe"),new ItemStack(BlockRegister.ITEM_PIPE,8),true,new Object[]{
				"IPI",
				'P', "plateLead",
				'I', "ingotLead"}).setRegistryName(getRL("item_pipe")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("item_pump"),new ItemStack(BlockRegister.ITEM_PUMP,1),true,new Object[]{
				" R ",
				"PBP",
				" R ",
				'P', BlockRegister.ITEM_PIPE,
				'B', ItemRegister.PLATE_CAMINITE,
				'R', "dustRedstone"}).setRegistryName(getRL("item_pump")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("bin"),new ItemStack(BlockRegister.BIN,1),true,new Object[]{
				"I I",
				"I I",
				"IPI",
				'P', "plateIron",
				'I', "ingotIron"}).setRegistryName(getRL("bin")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("stamper"),new ItemStack(BlockRegister.STAMPER,1),true,new Object[]{
				"XCX",
				"XBX",
				"X X",
				'B', "blockIron",
				'C', "ingotCopper",
				'X', ItemRegister.BRICK_CAMINITE}).setRegistryName(getRL("stamper")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("stamp_base"),new ItemStack(BlockRegister.STAMP_BASE,1),true,new Object[]{
				"I I",
				"XBX",
				'I', "ingotIron",
				'B', Items.BUCKET,
				'X', BlockRegister.BLOCK_CAMINITE_BRICK}).setRegistryName(getRL("stamp_base")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_bore"),new ItemStack(BlockRegister.EMBER_BORE,1),true,new Object[]{
				"YCY",
				"YBY",
				"III",
				'I', "ingotIron",
				'B', BlockRegister.MECH_CORE,
				'Y', BlockRegister.STAIRS_CAMINITE_BRICK,
				'C', "ingotCopper"}).setRegistryName(getRL("ember_bore")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("mech_core"),new ItemStack(BlockRegister.MECH_CORE,1),true,new Object[]{
				"IBI",
				" P ",
				"I I",
				'I', "ingotIron",
				'P', "plateLead",
				'B', Items.COMPASS}).setRegistryName(getRL("mech_core")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("mech_accessor"),new ItemStack(BlockRegister.MECH_ACCESSOR,1),true,new Object[]{
				"SPI",
				'P', "plateIron",
				'S', BlockRegister.STAIRS_CAMINITE_BRICK,
				'I', "ingotIron"}).setRegistryName(getRL("mech_accessor")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_activator"),new ItemStack(BlockRegister.EMBER_ACTIVATOR,1),true,new Object[]{
				"CCC",
				"CCC",
				"IFI",
				'C', "ingotCopper",
				'F', Blocks.FURNACE,
				'I', "plateIron"}).setRegistryName(getRL("ember_activator")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("stone_edge"),new ItemStack(BlockRegister.STONE_EDGE,1),true,new Object[]{
				"XXX",
				"Y Y",
				"XXX",
				'Y', ItemRegister.BRICK_CAMINITE,
				'X', new ItemStack(BlockRegister.WALL_CAMINITE_BRICK)}).setRegistryName(getRL("stone_edge")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("stone_valve"),new ItemStack(BlockRegister.STONE_VALVE,1),true,new Object[]{
				"XXX",
				"Y Y",
				"XXX",
				'Y', BlockRegister.MECH_ACCESSOR,
				'X', new ItemStack(BlockRegister.WALL_CAMINITE_BRICK)}).setRegistryName(getRL("stone_valve")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("mixer"),new ItemStack(BlockRegister.MIXER,1),true,new Object[]{
				"PPP",
				"PCP",
				"IMI",
				'P', "plateIron",
				'C', "ingotCopper",
				'M', BlockRegister.MECH_CORE,
				'I', "ingotIron"}).setRegistryName(getRL("mixer")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("heat_coil"),new ItemStack(BlockRegister.HEAT_COIL,1),true,new Object[]{
				"PPP",
				"IBI",
				" M ",
				'P', "plateCopper",
				'B', "blockCopper",
				'M', BlockRegister.MECH_CORE,
				'I', "ingotIron"}).setRegistryName(getRL("heat_coil")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("item_dropper"),new ItemStack(BlockRegister.ITEM_DROPPER,1),true,new Object[]{
				" P ",
				"I I",
				'P', BlockRegister.ITEM_PIPE,
				'I', "ingotLead"}).setRegistryName(getRL("item_dropper")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("fluid_dropper"),new ItemStack(BlockRegister.FLUID_DROPPER,1),true,new Object[]{
				" P ",
				"I I",
				'P', BlockRegister.PIPE,
				'I', "ingotIron"}).setRegistryName(getRL("fluid_dropper")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("large_tank"),new ItemStack(BlockRegister.LARGE_TANK,1),true,new Object[]{
				"Y Y",
				"I I",
				"YTY",
				'Y', BlockRegister.STAIRS_CAMINITE_BRICK,
				'I', "ingotIron",
				'T', BlockRegister.BLOCK_TANK}).setRegistryName(getRL("large_tank")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_gauge"),new ItemStack(BlockRegister.EMBER_GAUGE,1),true,new Object[]{
				"B",
				"P",
				"C",
				'P', Items.PAPER,
				'B', "dustRedstone",
				'C', "plateCopper"}).setRegistryName(getRL("ember_gauge")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("fluid_gauge"),new ItemStack(BlockRegister.FLUID_GAUGE,1),true,new Object[]{
				"B",
				"P",
				"I",
				'P', Items.PAPER,
				'I', "plateIron",
				'B', "dustRedstone"}).setRegistryName(getRL("fluid_gauge")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("block_lantern"),new ItemStack(BlockRegister.BLOCK_LANTERN,4),true,new Object[]{
				"P",
				"E",
				"I",
				'E', ItemRegister.SHARD_EMBER,
				'I', "ingotIron",
				'P', "plateIron"}).setRegistryName(getRL("block_lantern")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("tinker_hammer"),new ItemStack(ItemRegister.TINKER_HAMMER,1),true,new Object[]{
				"IBI",
				"ISI",
				" S ",
				'B', "ingotLead",
				'I', "ingotIron",
				'S', "stickWood"}).setRegistryName(getRL("tinker_hammer")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_detector"),new ItemStack(ItemRegister.EMBER_DETECTOR,1),true,new Object[]{
				" I ",
				"CRC",
				"CIC",
				'C', "ingotCopper",
				'I', "ingotIron",
				'R', "dustRedstone"}).setRegistryName(getRL("ember_detector")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("beam_splitter"),new ItemStack(BlockRegister.BEAM_SPLITTER,1),true,new Object[]{
				" D ",
				"CPC",
				" I ",
				'C', "ingotCopper",
				'I', "ingotIron",
				'P', "plateIron",
				'D', "ingotDawnstone"}).setRegistryName(getRL("beam_splitter")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_relay"),new ItemStack(BlockRegister.EMBER_RELAY,4),true,new Object[]{
				" C ",
				"C C",
				" P ",
				'C', "ingotCopper",
				'P', "plateIron"}).setRegistryName(getRL("ember_relay")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("crystal_cell"),new ItemStack(BlockRegister.CRYSTAL_CELL,1),true,new Object[]{
				" E ",
				"DED",
				"CBC",
				'C', "blockCopper",
				'B', "blockDawnstone",
				'D', "plateDawnstone",
				'E', ItemRegister.CRYSTAL_EMBER}).setRegistryName(getRL("crystal_cell")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_jar"),new ItemStack(ItemRegister.EMBER_JAR,1),true,new Object[]{
				" C ",
				"ISI",
				" G ",
				'I', "ingotIron",
				'S', ItemRegister.SHARD_EMBER,
				'C', "ingotCopper",
				'G', "blockGlass"}).setRegistryName(getRL("ember_jar")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_cartridge"),new ItemStack(ItemRegister.EMBER_CARTRIDGE,1),true,new Object[]{
				"ICI",
				"GSG",
				" G ",
				'I', "ingotIron",
				'S', ItemRegister.CRYSTAL_EMBER,
				'C', "plateCopper",
				'G', "blockGlass"}).setRegistryName(getRL("ember_cartridge")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("charger"),new ItemStack(BlockRegister.CHARGER,1),true,new Object[]{
				" X ",
				"DCD",
				"IPI",
				'D', "ingotDawnstone",
				'P', "plateCopper",
				'C', "ingotCopper",
				'I', "ingotIron",
				'X', "plateIron"}).setRegistryName(getRL("charger")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("axe_clockwork"),new ItemStack(ItemRegister.AXE_CLOCKWORK,1),true,new Object[]{
				"PCP",
				"ISI",
				" W ",
				'C', "plateCopper",
				'P', "plateDawnstone",
				'I', "ingotDawnstone",
				'S', ItemRegister.SHARD_EMBER,
				'W', "stickWood"}).setRegistryName(getRL("axe_clockwork")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("pickaxe_clockwork"),new ItemStack(ItemRegister.PICKAXE_CLOCKWORK,1),true,new Object[]{
				"ISI",
				" C ",
				" W ",
				'C', "ingotCopper",
				'I', "ingotDawnstone",
				'S', ItemRegister.SHARD_EMBER,
				'W', "stickWood"}).setRegistryName(getRL("pickaxe_clockwork")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("grandhammer"),new ItemStack(ItemRegister.GRANDHAMMER,1),true,new Object[]{
				"BIB",
				" C ",
				" W ",
				'C', "ingotCopper",
				'I', "ingotDawnstone",
				'B', "blockDawnstone",
				'W', "stickWood"}).setRegistryName(getRL("grandhammer")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("staff_ember"),new ItemStack(ItemRegister.STAFF_EMBER,1),true,new Object[]{
				"SES",
				"IWI",
				" W ",
				'S', "plateSilver",
				'I', "ingotDawnstone",
				'E', ItemRegister.SHARD_EMBER,
				'W', "stickWood"}).setRegistryName(getRL("staff_ember")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ignition_cannon"),new ItemStack(ItemRegister.IGNITION_CANNON,1),true,new Object[]{
				" DP",
				"DPI",
				"SW ",
				'I', "ingotIron",
				'D', "ingotDawnstone",
				'P', "plateDawnstone",
				'S', ItemRegister.SHARD_EMBER,
				'W', "stickWood"}).setRegistryName(getRL("ignition_cannon")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("cinder_plinth"),new ItemStack(BlockRegister.CINDER_PLINTH,1),true,new Object[]{
				" P ",
				"SFS",
				"PBP",
				'P', "plateLead",
				'B', BlockRegister.BLOCK_CAMINITE_BRICK,
				'S', "ingotSilver",
				'F', Blocks.FURNACE}).setRegistryName(getRL("cinder_plinth")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("alchemy_pedestal"),new ItemStack(BlockRegister.ALCHEMY_PEDESTAL,1),true,new Object[]{
				"D D",
				"ICI",
				"SBS",
				'D', "plateDawnstone",
				'I', "ingotDawnstone",
				'B', "blockCopper",
				'S', BlockRegister.STAIRS_CAMINITE_BRICK,
				'C', ItemRegister.CRYSTAL_EMBER}).setRegistryName(getRL("alchemy_pedestal")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("alchemy_tablet"),new ItemStack(BlockRegister.ALCHEMY_TABLET,1),true,new Object[]{
				" D ",
				"SXS",
				"BIB",
				'D', "plateDawnstone",
				'I', "ingotDawnstone",
				'B', BlockRegister.BLOCK_CAMINITE_BRICK,
				'S', BlockRegister.STAIRS_CAMINITE_BRICK,
				'X', "plateCopper"}).setRegistryName(getRL("alchemy_tablet")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("item_transfer"),new ItemStack(BlockRegister.ITEM_TRANSFER,4),true,new Object[]{
				"PLP",
				"ILI",
				"I I",
				'P', "plateLead",
				'I', "ingotLead",
				'L', BlockRegister.ITEM_PIPE}).setRegistryName(getRL("item_transfer")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("fluid_transfer"),new ItemStack(BlockRegister.FLUID_TRANSFER,4),true,new Object[]{
				"PLP",
				"ILI",
				"I I",
				'P', "plateIron",
				'I', "ingotIron",
				'L', BlockRegister.PIPE}).setRegistryName(getRL("fluid_transfer")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("beam_cannon"),new ItemStack(BlockRegister.BEAM_CANNON,1),true,new Object[]{
				"PSP",
				"PSP",
				"IBI",
				'S', ItemRegister.CRYSTAL_EMBER,
				'P', "plateCopper",
				'I', "ingotDawnstone",
				'B', BlockRegister.BLOCK_CAMINITE_BRICK}).setRegistryName(getRL("beam_cannon")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ashen_cloak_head"),new ItemStack(ItemRegister.ASHEN_CLOAK_HEAD,1),true,new Object[]{
				" S ",
				"C C",
				"DCD",
				'S', "string",
				'D', "ingotDawnstone",
				'C', ItemRegister.ASHEN_CLOTH}).setRegistryName(getRL("ashen_cloak_head")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ashen_cloak_chest"),new ItemStack(ItemRegister.ASHEN_CLOAK_CHEST,1),true,new Object[]{
				"P P",
				"CDC",
				"CDC",
				'D', "ingotDawnstone",
				'P', "plateDawnstone",
				'C', ItemRegister.ASHEN_CLOTH}).setRegistryName(getRL("ashen_cloak_chest")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ashen_cloak_legs"),new ItemStack(ItemRegister.ASHEN_CLOAK_LEGS,1),true,new Object[]{
				"CCC",
				"D D",
				"D D",
				'D', "ingotDawnstone",
				'C', ItemRegister.ASHEN_CLOTH}).setRegistryName(getRL("ashen_cloak_legs")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ashen_cloak_boots"),new ItemStack(ItemRegister.ASHEN_CLOAK_BOOTS,1),true,new Object[]{
				"C C",
				"C C",
				"C C",
				'C', ItemRegister.ASHEN_CLOTH}).setRegistryName(getRL("ashen_cloak_boots")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("sealed_planks"),new ItemStack(BlockRegister.SEALED_PLANKS,8),true,new Object[]{
				"PPP",
				"PSP",
				"PPP",
				'S', "slimeball",
				'P', "plankWood"}).setRegistryName(getRL("sealed_planks")));
		event.getRegistry().register(new ShapelessOreRecipe(getRL("wrapped_sealed_planks"),new ItemStack(BlockRegister.WRAPPED_SEALED_PLANKS,1),new Object[]{
				Blocks.IRON_BARS, BlockRegister.SEALED_PLANKS}).setRegistryName(getRL("wrapped_sealed_planks")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("archaic_bricks"),new ItemStack(BlockRegister.ARCHAIC_BRICKS,1),true,new Object[]{
				"BB",
				"BB",
				'B', ItemRegister.ARCHAIC_BRICK}).setRegistryName(getRL("archaic_bricks")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("archaic_light"),new ItemStack(BlockRegister.ARCHAIC_LIGHT,1),true,new Object[]{
				" B ",
				"BSB",
				" B ",
				'B', ItemRegister.ARCHAIC_BRICK,
				'S', ItemRegister.SHARD_EMBER}).setRegistryName(getRL("archaic_light")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("dawnstone_anvil"),new ItemStack(BlockRegister.DAWNSTONE_ANVIL,1),true,new Object[]{
				"BBB",
				"cIc",
				"CCC",
				'B', "blockDawnstone",
				'I', "ingotDawnstone",
				'C', BlockRegister.BLOCK_CAMINITE_BRICK,
				'c', ItemRegister.BRICK_CAMINITE}).setRegistryName(getRL("dawnstone_anvil")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("auto_hammer"),new ItemStack(BlockRegister.AUTO_HAMMER,1),true,new Object[]{
				"cc ",
				"CIB",
				"cc ",
				'B', "blockIron",
				'I', "ingotIron",
				'C', "blockCopper",
				'c', BlockRegister.STAIRS_CAMINITE_BRICK}).setMirrored(true).setRegistryName(getRL("auto_hammer")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("vacuum"),new ItemStack(BlockRegister.VACUUM,1),true,new Object[]{
				" LL",
				"P  ",
				" LL",
				'P', BlockRegister.ITEM_PIPE,
				'L', "ingotLead"}).setMirrored(true).setRegistryName(getRL("vacuum")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("archaic_edge"),new ItemStack(BlockRegister.ARCHAIC_EDGE,2),true,new Object[]{
				"BBB",
				"BCB",
				"BBB",
				'B', ItemRegister.ARCHAIC_BRICK,
				'C', ItemRegister.SHARD_EMBER}).setRegistryName(getRL("archaic_edge")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("archaic_tile"),new ItemStack(BlockRegister.ARCHAIC_TILE,4),true,new Object[]{
				"BB",
				"BB",
				'B', BlockRegister.ARCHAIC_BRICKS}).setRegistryName(getRL("archaic_tile")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("archaic_bricks"),new ItemStack(BlockRegister.ARCHAIC_BRICKS,4),true,new Object[]{
				"BB",
				"BB",
				'B', BlockRegister.ARCHAIC_TILE}).setRegistryName(getRL("archaic_bricks_2")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("breaker"),new ItemStack(BlockRegister.BREAKER,1),true,new Object[]{
				"PIP",
				"LRL",
				"L L",
				'P', "plateIron",
				'I', Items.IRON_INGOT,
				'L', "ingotLead",
				'R', Items.REDSTONE}).setRegistryName(getRL("breaker")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_injector"),new ItemStack(BlockRegister.EMBER_INJECTOR,1),true,new Object[]{
				"S S",
				"DCD",
				"BPB",
				'P', "plateSilver",
				'S', "ingotSilver",
				'D', "plateDawnstone",
				'B', BlockRegister.BLOCK_CAMINITE_BRICK,
				'C', ItemRegister.WILDFIRE_CORE}).setRegistryName(getRL("ember_injector")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("boiler"),new ItemStack(BlockRegister.BOILER,1),true,new Object[]{
				"CCC",
				"IFI",
				"IBI",
				'B', "blockCopper",
				'I', Items.IRON_INGOT,
				'C', "ingotCopper",
				'F', Blocks.FURNACE}).setRegistryName(getRL("boiler")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("reactor"),new ItemStack(BlockRegister.REACTOR,1),true,new Object[]{
				"CCC",
				"CWC",
				"SBS",
				'B', BlockRegister.BLOCK_CAMINITE_BRICK,
				'W', ItemRegister.WILDFIRE_CORE,
				'C', "ingotCopper",
				'S', "plateSilver"}).setRegistryName(getRL("reactor")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("combustor"),new ItemStack(BlockRegister.COMBUSTOR,1),true,new Object[]{
				" C ",
				"PEP",
				"CMC",
				'M', BlockRegister.MECH_CORE,
				'P', "plateCopper",
				'C', "ingotCopper",
				'E', ItemRegister.EMBER_CLUSTER}).setRegistryName(getRL("combustor")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("catalyzer"),new ItemStack(BlockRegister.CATALYZER,1),true,new Object[]{
				" C ",
				"PEP",
				"CMC",
				'M', BlockRegister.MECH_CORE,
				'P', "plateSilver",
				'C', "ingotSilver",
				'E', ItemRegister.EMBER_CLUSTER}).setRegistryName(getRL("catalyzer")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("codex"),new ItemStack(ItemRegister.CODEX,1),true,new Object[]{
				" B ",
				" E ",
				" B ",
				'B', ItemRegister.ARCHAIC_BRICK,
				'E', ItemRegister.ANCIENT_MOTIVE_CORE}).setRegistryName(getRL("codex")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("field_chart"),new ItemStack(BlockRegister.FIELD_CHART,1),true,new Object[]{
				"BBB",
				"BCB",
				"BBB",
				'B', ItemRegister.ARCHAIC_BRICK,
				'C', ItemRegister.EMBER_CLUSTER}).setRegistryName(getRL("field_chart")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("archaic_circuit"),new ItemStack(ItemRegister.ARCHAIC_CIRCUIT,1),true,new Object[]{
				" B ",
				"BCB",
				" B ",
				'B', ItemRegister.ARCHAIC_BRICK,
				'C', "ingotCopper"}).setRegistryName(getRL("archaic_circuit")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("inferno_forge"),new ItemStack(BlockRegister.INFERNO_FORGE,1),true,new Object[]{
				"BPB",
				"DCD",
				"SWS",
				'B', BlockRegister.BLOCK_DAWNSTONE,
				'D', "ingotDawnstone",
				'C', "blockCopper",
				'W', ItemRegister.WILDFIRE_CORE,
				'P', "plateIron",
				'S', BlockRegister.BLOCK_CAMINITE_BRICK}).setRegistryName(getRL("inferno_forge")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("superheater"),new ItemStack(ItemRegister.SUPERHEATER,1),true,new Object[]{
				" ID",
				"PPI",
				"PP ",
				'I', "ingotDawnstone",
				'D', "plateDawnstone",
				'P', "ingotCopper"}).setRegistryName(getRL("superheater")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("resonating_bell"),new ItemStack(ItemRegister.RESONATING_BELL,1),true,new Object[]{
				"IIP",
				" sI",
				"S I",
				'I', "ingotIron",
				's', "ingotSilver",
				'P', "plateIron",
				'S', "plateSilver"}).setRegistryName(getRL("resonating_bell")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("jet_augment"),new ItemStack(ItemRegister.JET_AUGMENT,1),true,new Object[]{
				"PP ",
				"IsD",
				"PP ",
				'I', "ingotIron",
				's', ItemRegister.SHARD_EMBER,
				'P', "plateDawnstone",
				'D', "ingotDawnstone"}).setRegistryName(getRL("jet_augment")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("caster_orb"),new ItemStack(ItemRegister.CASTER_ORB,1),true,new Object[]{
				"DCD",
				"D D",
				" P ",
				'C', ItemRegister.CRYSTAL_EMBER,
				'P', "plateDawnstone",
				'D', "ingotDawnstone"}).setRegistryName(getRL("caster_orb")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_pulser"),new ItemStack(BlockRegister.EMBER_PULSER,1),true,new Object[]{
				"D",
				"E",
				"I",
				'E', BlockRegister.EMBER_EMITTER,
				'I', "ingotIron",
				'D', "plateDawnstone"}).setRegistryName(getRL("ember_pulser")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_funnel"),new ItemStack(BlockRegister.EMBER_FUNNEL,1),true,new Object[]{
				"D D",
				"CRC",
				" D ",
				'R', BlockRegister.EMBER_RECEIVER,
				'C', "ingotCopper",
				'D', "plateDawnstone"}).setRegistryName(getRL("ember_funnel")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("caminite_lever"),new ItemStack(BlockRegister.CAMINITE_LEVER,4),true,new Object[]{
				"S",
				"P",
				'S', "stickWood",
				'P', new ItemStack(ItemRegister.PLATE_CAMINITE)}).setRegistryName(getRL("caminite_lever")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("mini_boiler"),new ItemStack(BlockRegister.MINI_BOILER,1),true,new Object[]{
				"PPP",
				"E P",
				"PPP",
				'E', "ingotCopper",
				'P', "plateIron"}).setRegistryName(getRL("mini_boiler")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("diffraction_barrel"),new ItemStack(ItemRegister.DIFFRACTION_BARREL,1),true,new Object[]{
				"XPI",
				'X', Blocks.IRON_BARS,
				'P', "plateIron",
				'I', ItemRegister.SUPERHEATER}).setRegistryName(getRL("diffraction_barrel")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("mechanical_pump"),new ItemStack(BlockRegister.MECHANICAL_PUMP,1),true,new Object[]{
				"EPE",
				"PPP",
				"BIB",
				'E', BlockRegister.PIPE,
				'I', BlockRegister.PUMP,
				'P', "plateIron",
				'B', ItemRegister.BRICK_CAMINITE}).setRegistryName(getRL("mechanical_pump")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("tinker_lens"),new ItemStack(ItemRegister.TINKER_LENS,1),true,new Object[]{
				"BE ",
				"IPE",
				"BE ",
				'E', "nuggetLead",
				'I', "plateLead",
				'P', "paneGlass",
				'B', "ingotIron"}).setRegistryName(getRL("tinker_lens")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("anti_tinker_lens"),new ItemStack(ItemRegister.ANTI_TINKER_LENS,1),true,new Object[]{
				" A ",
				"APA",
				" A ",
				'P', ItemRegister.TINKER_LENS,
				'A', "dustAsh"}).setRegistryName(getRL("anti_tinker_lens")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("glimmer_lamp"),new ItemStack(ItemRegister.GLIMMER_LAMP,1),true,new Object[]{
				" P ",
				"XGX",
				" P ",
				'G', ItemRegister.GLIMMER_SHARD,
				'X', Blocks.IRON_BARS,
				'P', "plateIron"}).setRegistryName(getRL("glimmer_lamp")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_siphon"),new ItemStack(BlockRegister.EMBER_SIPHON,1),true,new Object[]{
				"PGP",
				"XGX",
				"BBB",
				'G', "ingotCopper",
				'X', "plateNickel",
				'P', new ItemStack(BlockRegister.WALL_CAMINITE_BRICK),
				'B', ItemRegister.BRICK_CAMINITE}).setRegistryName(getRL("ember_siphon")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("stirling"),new ItemStack(BlockRegister.STIRLING,1),true,new Object[]{
				"XGX",
				"XGX",
				"BPB",
				'G', "blockCopper",
				'X', "plateDawnstone",
				'P', ItemRegister.WILDFIRE_CORE,
				'B', ItemRegister.SHARD_EMBER}).setRegistryName(getRL("stirling")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("clockwork_attenuator"),new ItemStack(BlockRegister.CLOCKWORK_ATTENUATOR,1),true,new Object[]{
				"GBG",
				" P ",
				"GIG",
				'P', Items.PAPER,
				'I', "plateElectrum",
				'B', "dustRedstone",
				'G', "nuggetElectrum"}).setRegistryName(getRL("clockwork_attenuator")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("geo_separator"),new ItemStack(BlockRegister.GEO_SEPARATOR,1),true,new Object[]{
				"  B",
				"GIG",
				'I', BlockRegister.BLOCK_TANK,
				'B', "blockNickel",
				'G', ItemRegister.BRICK_CAMINITE}).setRegistryName(getRL("geo_separator")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("reaction_chamber"),new ItemStack(BlockRegister.REACTION_CHAMBER,1),true,new Object[]{
				"DDD",
				"D D",
				"BIB",
				'I', ItemRegister.SPARK_PLUG,
				'B', "ingotBronze",
				'D', "plateBronze"}).setRegistryName(getRL("reaction_chamber")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("spark_plug"),new ItemStack(ItemRegister.SPARK_PLUG,1),true,new Object[]{
				"N N",
				" I ",
				" B ",
				'I', "plateIron",
				'B', ItemRegister.ASPECTUS_SILVER,
				'N', "ingotAluminium"}).setRegistryName(getRL("spark_plug")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("item_request"),new ItemStack(BlockRegister.ITEM_REQUEST,1),true,new Object[]{
				"ASA",
				"IPS",
				"ASA",
				'P', BlockRegister.ITEM_PUMP,
				'I', ItemRegister.INTELLIGENT_APPARATUS,
				'S', "plateSilver",
				'A', ItemRegister.ASPECTUS_SILVER}).setRegistryName(getRL("item_request")));

		event.getRegistry().register(new TankClearingRecipe(getRL("block_tank_clear"),new ItemStack(BlockRegister.BLOCK_TANK)).setRegistryName(getRL("block_tank_clear")));
		event.getRegistry().register(new AshenCloakSocketRecipe().setRegistryName(getRL("cloak_socketing")));
		event.getRegistry().register(new AshenCloakUnsocketRecipe().setRegistryName(getRL("cloak_unsocketing")));

		if(Util.isBaublesIntegrationEnabled())
			BaublesIntegration.registerRecipes(event);
		if(Util.isMysticalMechanicsIntegrationEnabled())
			MysticalMechanicsIntegration.registerRecipes(event);

		GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_COPPER), new ItemStack(ItemRegister.INGOT_COPPER), 0.65f);
		GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_SILVER), new ItemStack(ItemRegister.INGOT_SILVER), 0.35f);
		GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_LEAD), new ItemStack(ItemRegister.INGOT_LEAD), 0.35f);
		if (ConfigMaterial.ALUMINUM.mustLoad()){
			GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_ALUMINUM), new ItemStack(ItemRegister.INGOT_ALUMINUM), 0.55f);
		}
		if (ConfigMaterial.TIN.mustLoad()){
			GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_TIN), new ItemStack(ItemRegister.INGOT_TIN), 0.55f);
		}
		if (ConfigMaterial.NICKEL.mustLoad()){
			GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_NICKEL), new ItemStack(ItemRegister.INGOT_NICKEL), 0.55f);
		}
		GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_QUARTZ), new ItemStack(Items.QUARTZ), 0.35f);

		GameRegistry.addSmelting(new ItemStack(ItemRegister.BLEND_CAMINITE), new ItemStack(ItemRegister.BRICK_CAMINITE), 0.35f);
		GameRegistry.addSmelting(new ItemStack(ItemRegister.PLATE_CAMINITE_RAW), new ItemStack(ItemRegister.PLATE_CAMINITE), 0.35f);
		GameRegistry.addSmelting(new ItemStack(ItemRegister.STAMP_BAR_RAW), new ItemStack(ItemRegister.STAMP_BAR), 0.35f);
		GameRegistry.addSmelting(new ItemStack(ItemRegister.STAMP_PLATE_RAW), new ItemStack(ItemRegister.STAMP_PLATE), 0.35f);
		GameRegistry.addSmelting(new ItemStack(ItemRegister.STAMP_FLAT_RAW), new ItemStack(ItemRegister.STAMP_FLAT), 0.35f);
		GameRegistry.addSmelting(new ItemStack(ItemRegister.STAMP_GEAR_RAW), new ItemStack(ItemRegister.STAMP_GEAR), 0.35f);

		int plateAmount = ConfigMachine.STAMPER.stampPlateAmount * INGOT_AMOUNT;

		OreIngredient ingotIron = new OreIngredient("ingotIron");
		OreIngredient plateIron = new OreIngredient("plateIron");
		int oreMeltAmount = ConfigMachine.MELTER.melterOreAmount;
		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreIron"), new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, oreMeltAmount))
				.addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, NUGGET_AMOUNT))
		);
		meltingRecipes.add(new ItemMeltingRecipe(ingotIron,new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, INGOT_AMOUNT)));
		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetIron"),new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, NUGGET_AMOUNT)));
		meltingRecipes.add(new ItemMeltingRecipe(plateIron,new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, plateAmount)));

		OreIngredient ingotGold = new OreIngredient("ingotGold");
		OreIngredient plateGold = new OreIngredient("plateGold");
		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreGold"), new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, oreMeltAmount))
				.addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, NUGGET_AMOUNT))
		);
		meltingRecipes.add(new ItemMeltingRecipe(ingotGold,new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, INGOT_AMOUNT)));
		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetGold"),new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, NUGGET_AMOUNT)));
		meltingRecipes.add(new ItemMeltingRecipe(plateGold,new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, plateAmount)));

		OreIngredient ingotSilver = new OreIngredient("ingotSilver");
		OreIngredient plateSilver = new OreIngredient("plateSilver");
		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreSilver"), new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, oreMeltAmount))
				.addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, NUGGET_AMOUNT))
		);
		meltingRecipes.add(new ItemMeltingRecipe(ingotSilver,new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, INGOT_AMOUNT)));
		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetSilver"),new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, NUGGET_AMOUNT)));
		meltingRecipes.add(new ItemMeltingRecipe(plateSilver,new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, plateAmount)));

		OreIngredient ingotCopper = new OreIngredient("ingotCopper");
		OreIngredient plateCopper = new OreIngredient("plateCopper");
		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreCopper"), new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, oreMeltAmount))
				.addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, NUGGET_AMOUNT))
		);
		meltingRecipes.add(new ItemMeltingRecipe(ingotCopper,new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, INGOT_AMOUNT)));
		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetCopper"),new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, NUGGET_AMOUNT)));
		meltingRecipes.add(new ItemMeltingRecipe(plateCopper,new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, plateAmount)));

		OreIngredient ingotLead = new OreIngredient("ingotLead");
		OreIngredient plateLead = new OreIngredient("plateLead");
		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreLead"), new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, oreMeltAmount))
				.addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, NUGGET_AMOUNT))
		);
		meltingRecipes.add(new ItemMeltingRecipe(ingotLead,new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, INGOT_AMOUNT)));
		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetLead"),new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, NUGGET_AMOUNT)));
		meltingRecipes.add(new ItemMeltingRecipe(plateLead,new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, plateAmount)));

		OreIngredient ingotAluminum = new OreIngredient("ingotAluminum");
		if (ConfigMaterial.ALUMINUM.mustLoad()){
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreAluminum"), new FluidStack(FluidRegister.FLUID_MOLTEN_ALUMINUM, oreMeltAmount))
					.addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, NUGGET_AMOUNT))
			);
			meltingRecipes.add(new ItemMeltingRecipe(ingotAluminum,new FluidStack(FluidRegister.FLUID_MOLTEN_ALUMINUM, INGOT_AMOUNT)));
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetAluminum"),new FluidStack(FluidRegister.FLUID_MOLTEN_ALUMINUM, NUGGET_AMOUNT)));
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateAluminum"),new FluidStack(FluidRegister.FLUID_MOLTEN_ALUMINUM, plateAmount)));
		}

		OreIngredient ingotNickel = new OreIngredient("ingotNickel");
		if (ConfigMaterial.NICKEL.mustLoad()){
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreNickel"), new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, oreMeltAmount))
					.addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, NUGGET_AMOUNT))
			);
			meltingRecipes.add(new ItemMeltingRecipe(ingotNickel,new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, INGOT_AMOUNT)));
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetNickel"),new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, NUGGET_AMOUNT)));
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateNickel"),new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, plateAmount)));
		}

		OreIngredient ingotTin = new OreIngredient("ingotTin");
		if (ConfigMaterial.TIN.mustLoad()){
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreTin"), new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, oreMeltAmount))
					.addBonusOutput(new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, NUGGET_AMOUNT))
			);
			meltingRecipes.add(new ItemMeltingRecipe(ingotTin,new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, INGOT_AMOUNT)));
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetTin"),new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, NUGGET_AMOUNT)));
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateTin"),new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, plateAmount)));
		}

		OreIngredient ingotDawnstone = new OreIngredient("ingotDawnstone");
		OreIngredient plateDawnstone = new OreIngredient("plateDawnstone");
		meltingRecipes.add(new ItemMeltingRecipe(ingotDawnstone,new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, INGOT_AMOUNT)));
		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetDawnstone"),new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, NUGGET_AMOUNT)));
		meltingRecipes.add(new ItemMeltingRecipe(plateDawnstone,new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, plateAmount)));

		OreIngredient ingotBronze = new OreIngredient("ingotBronze");
		OreIngredient plateBronze = new OreIngredient("plateBronze");
		if (ConfigMaterial.BRONZE.mustLoad()){
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("ingotBronze"),new FluidStack(FluidRegister.FLUID_MOLTEN_BRONZE, INGOT_AMOUNT)));
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetBronze"),new FluidStack(FluidRegister.FLUID_MOLTEN_BRONZE, NUGGET_AMOUNT)));
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateBronze"),new FluidStack(FluidRegister.FLUID_MOLTEN_BRONZE, plateAmount)));
		}

		if (ConfigMaterial.ELECTRUM.mustLoad()){
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("ingotElectrum"),new FluidStack(FluidRegister.FLUID_MOLTEN_ELECTRUM, INGOT_AMOUNT)));
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("nuggetElectrum"),new FluidStack(FluidRegister.FLUID_MOLTEN_ELECTRUM, NUGGET_AMOUNT)));
			meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("plateElectrum"),new FluidStack(FluidRegister.FLUID_MOLTEN_ELECTRUM, plateAmount)));
		}

		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("oreRedstone"),new FluidStack(FluidRegister.FLUID_ALCHEMICAL_REDSTONE,1008))
            .addBonusOutput(FluidRegistry.getFluidStack("mercury",NUGGET_AMOUNT))
        );
		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("dustRedstone"),new FluidStack(FluidRegister.FLUID_ALCHEMICAL_REDSTONE, 144)));
		meltingRecipes.add(new ItemMeltingRecipe(new OreIngredient("blockRedstone"),new FluidStack(FluidRegister.FLUID_ALCHEMICAL_REDSTONE,1296)));

		meltingRecipes.add(new ItemMeltingRecipe(Ingredient.fromStacks(new ItemStack(Blocks.SOUL_SAND)),new FluidStack(FluidRegister.FLUID_CRUDE_OIL,100)));

		Ingredient stampBar = Ingredient.fromItem(ItemRegister.STAMP_BAR);
		Ingredient stampPlate = Ingredient.fromItem(ItemRegister.STAMP_PLATE);
		stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, INGOT_AMOUNT), stampBar,new ItemStack(Items.IRON_INGOT,1)));
		stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, INGOT_AMOUNT),stampBar,new ItemStack(Items.GOLD_INGOT,1)));
		stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, INGOT_AMOUNT),stampBar,new ItemStack(ItemRegister.INGOT_LEAD,1)));
		stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, INGOT_AMOUNT),stampBar,new ItemStack(ItemRegister.INGOT_SILVER,1)));
		stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, INGOT_AMOUNT),stampBar,new ItemStack(ItemRegister.INGOT_COPPER,1)));
		stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, INGOT_AMOUNT),stampBar,new ItemStack(ItemRegister.INGOT_DAWNSTONE,1)));
		if (ConfigMaterial.ALUMINUM.mustLoad()){
			stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_ALUMINUM, INGOT_AMOUNT),stampBar,new ItemStack(ItemRegister.INGOT_ALUMINUM,1)));
		}
		if (ConfigMaterial.BRONZE.mustLoad()){
			stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_BRONZE, INGOT_AMOUNT),stampBar,new ItemStack(ItemRegister.INGOT_BRONZE,1)));
		}
		if (ConfigMaterial.ELECTRUM.mustLoad()){
			stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_ELECTRUM, INGOT_AMOUNT),stampBar,new ItemStack(ItemRegister.INGOT_ELECTRUM,1)));
		}
		if (ConfigMaterial.NICKEL.mustLoad()){
			stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, INGOT_AMOUNT),stampBar,new ItemStack(ItemRegister.INGOT_NICKEL,1)));
		}
		if (ConfigMaterial.TIN.mustLoad()){
			stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, INGOT_AMOUNT),stampBar,new ItemStack(ItemRegister.INGOT_TIN,1)));
		}

		stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, plateAmount), stampPlate,new ItemStack(ItemRegister.PLATE_IRON,1)));
		stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, plateAmount), stampPlate,new ItemStack(ItemRegister.PLATE_GOLD,1)));
		stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, plateAmount), stampPlate,new ItemStack(ItemRegister.PLATE_LEAD,1)));
		stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, plateAmount), stampPlate,new ItemStack(ItemRegister.PLATE_SILVER,1)));
		stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, plateAmount), stampPlate,new ItemStack(ItemRegister.PLATE_COPPER,1)));
		stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, plateAmount), stampPlate,new ItemStack(ItemRegister.PLATE_DAWNSTONE,1)));
		if (ConfigMaterial.ALUMINUM.mustLoad()){
			stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_ALUMINUM, plateAmount),stampPlate,new ItemStack(ItemRegister.PLATE_ALUMINUM,1)));
		}
		if (ConfigMaterial.BRONZE.mustLoad()){
			stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_BRONZE, plateAmount),stampPlate,new ItemStack(ItemRegister.PLATE_BRONZE,1)));
		}
		if (ConfigMaterial.ELECTRUM.mustLoad()){
			stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_ELECTRUM, plateAmount),stampPlate,new ItemStack(ItemRegister.PLATE_ELECTRUM,1)));
		}
		if (ConfigMaterial.NICKEL.mustLoad()){
			stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_NICKEL, plateAmount),stampPlate,new ItemStack(ItemRegister.PLATE_NICKEL,1)));
		}
		if (ConfigMaterial.TIN.mustLoad()){
			stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY,new FluidStack(FluidRegister.FLUID_MOLTEN_TIN, plateAmount),stampPlate,new ItemStack(ItemRegister.PLATE_TIN,1)));
		}

		stampingRecipes.add(new ItemWasteStampingRecipe());

		Ingredient emberShard = Ingredient.fromItem(ItemRegister.SHARD_EMBER);
		Ingredient emberCrystal = Ingredient.fromItem(ItemRegister.CRYSTAL_EMBER);
		Ingredient stampFlat = Ingredient.fromItem(ItemRegister.STAMP_FLAT);
		Ingredient blazeRod = Ingredient.fromItem(Items.BLAZE_ROD);

		//stampingRecipes.add(new ItemStampingRecipe(emberShard,null,stampFlat,new ItemStack(RegistryManager.dust_ember,1))); //Maybe one day
		stampingRecipes.add(new ItemStampingRecipe(emberCrystal,null,stampFlat,new ItemStack(ItemRegister.SHARD_EMBER,6)));
		stampingRecipes.add(new ItemStampingRecipe(blazeRod,null,stampFlat,new ItemStack(Items.BLAZE_POWDER,4)));

		int aspectusAmount = ConfigMachine.STAMPER.stampAspectusAmount * INGOT_AMOUNT;
		stampingRecipes.add(new ItemStampingRecipe(emberShard,new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, aspectusAmount),stampPlate,new ItemStack(ItemRegister.ASPECTUS_IRON,1)));
		stampingRecipes.add(new ItemStampingRecipe(emberShard,new FluidStack(FluidRegister.FLUID_MOLTEN_LEAD, aspectusAmount),stampPlate,new ItemStack(ItemRegister.ASPECTUS_LEAD,1)));
		stampingRecipes.add(new ItemStampingRecipe(emberShard,new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER, aspectusAmount),stampPlate,new ItemStack(ItemRegister.ASPECTUS_SILVER,1)));
		stampingRecipes.add(new ItemStampingRecipe(emberShard,new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER, aspectusAmount),stampPlate,new ItemStack(ItemRegister.ASPECTUS_COPPER,1)));
		stampingRecipes.add(new ItemStampingRecipe(emberShard,new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, aspectusAmount),stampPlate,new ItemStack(ItemRegister.ASPECTUS_DAWNSTONE,1)));

		mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER,4),new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD,4)}, new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE,8)));
		if (ConfigMaterial.ELECTRUM.mustLoad()){
			mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(FluidRegister.FLUID_MOLTEN_SILVER,4),new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD,4)}, new FluidStack(FluidRegister.FLUID_MOLTEN_ELECTRUM,8)));
		}
		if (ConfigMaterial.TIN.mustLoad() && ConfigMaterial.BRONZE.mustLoad()){
			mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(FluidRegister.FLUID_MOLTEN_COPPER,6),new FluidStack(FluidRegister.FLUID_MOLTEN_TIN,2)}, new FluidStack(FluidRegister.FLUID_MOLTEN_BRONZE,8)));
		}
		mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(FluidRegister.FLUID_CRUDE_OIL,5),new FluidStack(FluidRegister.FLUID_STEAM,20)}, new FluidStack(FluidRegister.FLUID_OIL,10), 0));
		mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(FluidRegister.FLUID_CRUDE_OIL,10),new FluidStack(FluidRegister.FLUID_GAS,5)}, new FluidStack(FluidRegister.FLUID_OIL,30), 0));

		fluidReactionRecipes.add(new FluidReactionRecipe(new FluidStack(FluidRegister.FLUID_STEAM, 5), new FluidStack(FluidRegistry.WATER, 1), new Color(255,255,255)));
		fluidReactionRecipes.add(new FluidReactionRecipe(new FluidStack(FluidRegister.FLUID_GAS, 1), new FluidStack(FluidRegister.FLUID_STEAM, 5), new Color(128,192,255)));

		OreIngredient quartz = new OreIngredient("gemQuartz");
		OreIngredient ash = new OreIngredient("dustAsh");
		OreIngredient string = new OreIngredient("string");
		Ingredient wool = Ingredient.fromItem(Item.getItemFromBlock(Blocks.WOOL));
		Ingredient coal = Ingredient.fromItem(Items.COAL);
		Ingredient clay = Ingredient.fromItem(Items.CLAY_BALL);
		OreIngredient diamond = new OreIngredient("gemDiamond");
		OreIngredient gunpowder = new OreIngredient("gunpowder");
		Ingredient bonemeal = Ingredient.fromStacks(new ItemStack(Items.DYE,1,15));
		OreIngredient lapis = new OreIngredient("gemLapis");
		OreIngredient redstone = new OreIngredient("dustRedstone");
		OreIngredient cobblestone = new OreIngredient("cobblestone");
		OreIngredient sand = new OreIngredient("sand");
		Ingredient soulsand = Ingredient.fromItem(Item.getItemFromBlock(Blocks.SOUL_SAND));
		OreIngredient obsidian = new OreIngredient("obsidian");
		OreIngredient blockCoal = new OreIngredient("blockCoal");
		Ingredient leadSword = new IngredientSpecial(stack -> {
			Item item = stack.getItem();
			return item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().toLowerCase().contains("lead");
		}); //Any lead sword.
		Ingredient emberCluster = Ingredient.fromItem(ItemRegister.EMBER_CLUSTER);
		Ingredient archaicBrick = Ingredient.fromItem(ItemRegister.ARCHAIC_BRICK);
		Ingredient archaicCircuit = Ingredient.fromItem(ItemRegister.ARCHAIC_CIRCUIT);
		OreIngredient redstoneBlock = new OreIngredient("blockRedstone");
		OreIngredient glass = new OreIngredient("blockGlass");
		Ingredient fluidPipe = Ingredient.fromStacks(new ItemStack(BlockRegister.PIPE));

		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 48, 64).setRange("copper", 48, 64), quartz, Lists.newArrayList(ingotCopper, ingotCopper, emberShard, emberShard), new ItemStack(BlockRegister.SEED_COPPER)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 48, 64).setRange("silver", 48, 64), quartz, Lists.newArrayList(ingotSilver, ingotSilver, emberShard, emberShard), new ItemStack(BlockRegister.SEED_SILVER)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 48, 64).setRange("lead", 48, 64), quartz, Lists.newArrayList(ingotLead, ingotLead, emberShard, emberShard), new ItemStack(BlockRegister.SEED_LEAD)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 48, 64).setRange("dawnstone", 48, 64), quartz, Lists.newArrayList(ingotGold, ingotGold, emberShard, emberShard), new ItemStack(BlockRegister.SEED_GOLD)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 72, 96).setRange("silver", 24, 32), quartz, Lists.newArrayList(ingotTin, ingotTin, emberShard, emberShard), new ItemStack(BlockRegister.SEED_TIN)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 48, 64).setRange("copper", 24, 32).setRange("silver", 24, 32), quartz, Lists.newArrayList(ingotAluminum, ingotAluminum, emberShard, emberShard), new ItemStack(BlockRegister.SEED_ALUMINUM)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 72, 96).setRange("lead", 24, 32), quartz, Lists.newArrayList(ingotNickel, ingotNickel, emberShard, emberShard), new ItemStack(BlockRegister.SEED_NICKEL)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 96, 128), quartz, Lists.newArrayList(ingotIron, ingotIron, emberShard, emberShard), new ItemStack(BlockRegister.SEED_IRON)));

		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 12, 24).setRange("lead", 12, 24), wool, Lists.newArrayList(ash, ash, string, string), new ItemStack(ItemRegister.ASHEN_CLOTH,2)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 32, 48).setRange("lead", 24, 40), diamond, Lists.newArrayList(ingotDawnstone, coal, coal, coal), new ItemStack(ItemRegister.INFLICTOR_GEM,1)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 64, 80), quartz,  Lists.newArrayList(gunpowder, gunpowder, emberShard, emberShard), new ItemStack(ItemRegister.GLIMMER_SHARD,1)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 24, 36), ingotIron, Lists.newArrayList(quartz, clay, lapis), new ItemStack(ItemRegister.ISOLATED_MATERIA,4)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 12, 18), clay, Lists.newArrayList(bonemeal, bonemeal), new ItemStack(ItemRegister.ADHESIVE,6)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("copper",  8, 16), redstone, Lists.newArrayList(ash, ash, cobblestone, cobblestone), new ItemStack(Blocks.NETHERRACK,2)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("copper",  8, 16), ash, Lists.newArrayList(sand, sand, sand, sand), new ItemStack(Blocks.SOUL_SAND,4)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("silver", 64, 96).setRange("lead", 64, 96), leadSword, Lists.newArrayList(blockCoal, obsidian, ingotLead, ingotLead), new ItemStack(ItemRegister.TYRFING,1)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 24, 48).setRange("copper", 24, 48), emberCrystal, Lists.newArrayList(gunpowder, emberShard, emberShard, emberShard), new ItemStack(ItemRegister.EMBER_CLUSTER,1)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 32, 48).setRange("silver", 24, 32), Ingredient.fromItem(ItemRegister.ANCIENT_MOTIVE_CORE), Lists.newArrayList(ingotDawnstone, emberCluster, ingotDawnstone, plateCopper), new ItemStack(ItemRegister.WILDFIRE_CORE,1)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 4, 8), archaicBrick,Lists.newArrayList(soulsand, soulsand, clay, clay), new ItemStack(ItemRegister.ARCHAIC_BRICK,5)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 24, 32), emberShard, Lists.newArrayList(archaicBrick, archaicBrick, archaicBrick, archaicBrick), new ItemStack(ItemRegister.ANCIENT_MOTIVE_CORE,1)));

		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("copper", 16, 24),
				gunpowder,
				Lists.newArrayList(plateIron, plateIron, plateIron, ingotCopper),
				new ItemStack(ItemRegister.BLASTING_CORE,1)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 16, 32).setRange("lead", 48, 72),
				archaicCircuit,
				Lists.newArrayList(archaicBrick, coal, archaicBrick, coal),
				new ItemStack(ItemRegister.ELDRITCH_INSIGNIA,1)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("copper",  24, 48).setRange("lead",  40, 64),
				plateCopper,
				Lists.newArrayList(archaicCircuit, ingotCopper, archaicCircuit, ingotCopper),
				new ItemStack(ItemRegister.INTELLIGENT_APPARATUS,1)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 16, 32).setRange("silver", 16, 32),
				emberCrystal,
				Lists.newArrayList(plateDawnstone, plateDawnstone, plateDawnstone, ingotSilver),
				new ItemStack(ItemRegister.FLAME_BARRIER,1)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("copper",  8, 16).setRange("silver", 32, 64),
				emberCrystal,
				Lists.newArrayList(plateDawnstone, plateSilver, plateDawnstone, plateSilver),
				new ItemStack(ItemRegister.FOCAL_LENS,1)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("lead",  32, 128),
				Ingredient.fromItem(ItemRegister.ASHEN_CLOTH),
				Lists.newArrayList(plateLead, plateLead, plateLead, plateLead),
				new ItemStack(ItemRegister.SHIFTING_SCALES,1)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("copper",  32, 128),
				ingotBronze,
				Lists.newArrayList(plateBronze, plateBronze, plateBronze, plateBronze),
				new ItemStack(ItemRegister.WINDING_GEARS,1)));
		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 8, 16).setRange("silver", 8, 16).setRange("dawnstone", 8, 16).setRange("lead", 8, 16).setRange("copper",  8, 16).fixMathematicalError(),
				Ingredient.fromItem(ItemRegister.WILDFIRE_CORE),
				Lists.newArrayList(ingotSilver, plateDawnstone, ingotSilver, plateDawnstone),
				new ItemStack(BlockRegister.EMBER_PIPE,8)));

		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 20, 30).setRange("silver", 32, 64),
				ingotSilver,
				Lists.newArrayList(fluidPipe, glass, fluidPipe, redstoneBlock),
				new ItemStack(BlockRegister.CATALYTIC_PLUG,1)));

		Ingredient anyMetalSeed = new IngredientSpecial(stack -> Block.getBlockFromItem(stack.getItem()) instanceof BlockSeedNew);

		alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList(AspectList.createStandard(1, 1, 1, 1, 1), AspectList.createStandard(16, 16, 16, 16, 16)).fixMathematicalError(),
				anyMetalSeed,
				Lists.newArrayList(Ingredient.fromItem(ItemRegister.DUST_EMBER), new OreIngredient("dustRedstone")),
				new ItemStack(ItemRegister.DUST_METALLURGIC,3)));

		heatCoilRecipes.add(new HeatCoilFurnaceRecipe());

		dawnstoneAnvilRecipes.add(new AnvilAddCoreRecipe());
		dawnstoneAnvilRecipes.add(new AnvilAddModifierRecipe());
		dawnstoneAnvilRecipes.add(new AnvilRemoveModifierRecipe());
		dawnstoneAnvilRecipes.add(new AnvilRepairRecipe()); //Repair with repair item
		dawnstoneAnvilRecipes.add(new AnvilRepairMateriaRecipe()); //Repair with Isolated Materia
		dawnstoneAnvilRecipes.add(new AnvilBreakdownRecipe()); //BREAKDOWN BREAKDOWN
		dawnstoneAnvilRecipes.add(new CreativeHeatRecipe()); //Creative Heat

		FilterUtil.registerComparator(FilterUtil.ANY);
		/*FilterUtil.registerComparator(new IFilterComparator() {
			@Override
			public int getPriority() {
				return 0;
			}

			@Override
			public String getName() {
				return "or";
			}

			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return stack1.getItem() instanceof IFilterItem && stack2.getItem() instanceof IFilterItem;
			}

			@Override
			public Comparable getCompare(ItemStack stack) {
				return 0;
			}

			@Override
			public boolean isBetween(ItemStack stack1, ItemStack stack2, ItemStack testStack, EnumFilterSetting setting) {
				IFilterItem filterItem1 = (IFilterItem) stack1.getItem();
				IFilterItem filterItem2 = (IFilterItem) stack2.getItem();
				return filterItem1.acceptsItem(stack1,testStack) || filterItem2.acceptsItem(stack2,testStack);
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				IFilterItem filterItem1 = (IFilterItem) stack1.getItem();
				IFilterItem filterItem2 = (IFilterItem) stack2.getItem();
				return I18n.format("embers.filter.or",filterItem1.formatFilter(stack1), filterItem2.formatFilter(stack2));
			}
		}); //OR
		FilterUtil.registerComparator(new IFilterComparator() {
			@Override
			public int getPriority() {
				return 1;
			}

			@Override
			public String getName() {
				return "and";
			}

			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return stack1.getItem() instanceof IFilterItem && stack2.getItem() instanceof IFilterItem;
			}

			@Override
			public Comparable getCompare(ItemStack stack) {
				return 0;
			}

			@Override
			public boolean isBetween(ItemStack stack1, ItemStack stack2, ItemStack testStack, EnumFilterSetting setting) {
				IFilterItem filterItem1 = (IFilterItem) stack1.getItem();
				IFilterItem filterItem2 = (IFilterItem) stack2.getItem();
				return filterItem1.acceptsItem(stack1,testStack) && filterItem2.acceptsItem(stack2,testStack);
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				IFilterItem filterItem1 = (IFilterItem) stack1.getItem();
				IFilterItem filterItem2 = (IFilterItem) stack2.getItem();
				return I18n.format("embers.filter.and",filterItem1.formatFilter(stack1), filterItem2.formatFilter(stack2));
			}
		}); //AND*/
		FilterUtil.registerComparator(new ComparatorMatch("strict",35) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return ItemHandlerHelper.canItemStacksStack(stack1,stack2);
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				return I18n.format("embers.filter.strict",stack1.getDisplayName());
			}
		}); //STRICT (ITEM + META + TAG)
		FilterUtil.registerComparator(new ComparatorMatch("normal",30) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return stack1.isItemEqual(stack2);
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				return I18n.format("embers.filter.normal",stack1.getDisplayName());
			}
		}); //NORMAL (ITEM + META)
		FilterUtil.registerComparator(new ComparatorMatch("item",15) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return stack1.getItem() == stack2.getItem();
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				return I18n.format("embers.filter.item",stack1.getDisplayName());
			}
		}); //ITEM
		FilterUtil.registerComparator(new ComparatorMatch("meta",20) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return stack1.getHasSubtypes() && stack1.getMetadata() == stack2.getMetadata();
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				return I18n.format("embers.filter.metadata",stack1.getMetadata());
			}
		}); //META
		FilterUtil.registerComparator(new ComparatorMatch("nbt",25) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return stack1.hasTagCompound() && ItemStack.areItemStackTagsEqual(stack1,stack2);
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				return I18n.format("embers.filter.nbt");
			}
		}); //NBT
		FilterUtil.registerComparator(new ComparatorMatch("name",50) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return stack1.hasDisplayName() && stack2.hasDisplayName() && stack1.getDisplayName().equals(stack2.getDisplayName());
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				return I18n.format("embers.filter.name",stack1.getDisplayName());
			}
		}); //Name
		FilterUtil.registerComparator(new ComparatorMatch("mod",50) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				ResourceLocation resLoc1 = stack1.getItem().getRegistryName();
				ResourceLocation resLoc2 = stack2.getItem().getRegistryName();
				return resLoc1.getResourceDomain().equals(resLoc2.getResourceDomain());
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				return I18n.format("embers.filter.mod",stack1.getItem().getRegistryName().getResourceDomain());
			}
		});
		FilterUtil.registerComparator(new ComparatorNormal("durability",30) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return stack1.isItemStackDamageable() && stack2.isItemStackDamageable();
			}

			@Override
			public Comparable getCompare(ItemStack stack) {
				return stack.getMaxDamage() - stack.getItemDamage();
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				String format;
				Comparable a = getCompare(stack1);
				Comparable b = getCompare(stack2);
				if (a.compareTo(b) != 0)
					format = I18n.format("embers.filter.durability", min(a, b), max(a, b));
				else if (setting == EnumFilterSetting.STRICT)
					format = I18n.format("embers.filter.durability.strict", min(a, b), max(a, b));
				else
					format = I18n.format("embers.filter.durability.fuzzy", min(a, b), max(a, b));
				if(inverted)
					return I18n.format("embers.filter.invert",format);
				else
					return format;
			}
		}); //DURABILITY
		FilterUtil.registerComparator(new ComparatorNormal("fuel",40) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return TileEntityFurnace.getItemBurnTime(stack1) > 0 && TileEntityFurnace.getItemBurnTime(stack2) > 0;
			}

			@Override
			public Comparable getCompare(ItemStack stack) {
				return TileEntityFurnace.getItemBurnTime(stack);
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				String format;
				Comparable a = getCompare(stack1);
				Comparable b = getCompare(stack2);
				if (a.compareTo(b) != 0)
					format = I18n.format("embers.filter.fuel", min(a, b), max(a, b));
				else if (setting == EnumFilterSetting.STRICT)
					format = I18n.format("embers.filter.fuel.strict", min(a, b), max(a, b));
				else
					format = I18n.format("embers.filter.fuel.fuzzy", min(a, b), max(a, b));
				if(inverted)
					return I18n.format("embers.filter.invert",format);
				else
					return format;
			}
		}); //FUEL
		FilterUtil.registerComparator(new ComparatorMatch("has_fluid",120) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return stack1.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY,null) && stack2.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY,null);
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				return I18n.format("embers.filter.has_fluid");
			}
		}); //HAS FLUID
		FilterUtil.registerComparator(new ComparatorNormal("fluid",125) {
			private boolean areFluidsEqual(FluidStack a, FluidStack b) {
				if(a == null && b == null)
					return true;
				else return a != null && b != null && a.isFluidEqual(b);
			}

			private int getAmount(FluidStack fluid) {
				return fluid != null ? fluid.amount : 0;
			}

			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				IFluidHandler capability1 = FluidUtil.getFluidHandler(stack1);
				IFluidHandler capability2 = FluidUtil.getFluidHandler(stack2);
				if(capability1 != null && capability2 != null) {
					FluidStack fluid1 = capability1.drain(Integer.MAX_VALUE, false);
					FluidStack fluid2 = capability2.drain(Integer.MAX_VALUE, false);
					return areFluidsEqual(fluid1,fluid2);
				}
				return false;
			}

			@Override
			public Comparable getCompare(ItemStack stack) {
				IFluidHandler capability = FluidUtil.getFluidHandler(stack);
				FluidStack fluid = capability.drain(Integer.MAX_VALUE,false);
				return getAmount(fluid);
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				IFluidHandler capability = FluidUtil.getFluidHandler(stack1);
				FluidStack fluid = capability.drain(Integer.MAX_VALUE, false);
				Comparable a = getCompare(stack1);
				Comparable b = getCompare(stack2);
				String name = fluid != null ? fluid.getLocalizedName() : "";
				if (a.compareTo(b) != 0)
					return I18n.format("embers.filter.fluid", min(a, b), max(a, b), name);
				else if (setting == EnumFilterSetting.STRICT)
					return I18n.format("embers.filter.fluid.strict", min(a, b), max(a, b), name);
				else
					return I18n.format("embers.filter.fluid.fuzzy", min(a, b), max(a, b), name);
			}
		}); //FLUID
		FilterUtil.registerComparator(new ComparatorMatch("has_ember",150) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return stack1.hasCapability(EmbersCapabilities.EMBER_CAPABILITY,null) && stack2.hasCapability(EmbersCapabilities.EMBER_CAPABILITY,null);
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				return I18n.format("embers.filter.has_ember");
			}
		}); //HAS EMBER
		FilterUtil.registerComparator(new ComparatorNormal("ember",155) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return stack1.hasCapability(EmbersCapabilities.EMBER_CAPABILITY,null) && stack2.hasCapability(EmbersCapabilities.EMBER_CAPABILITY,null);
			}

			@Override
			public Comparable getCompare(ItemStack stack) {
				if(stack.hasCapability(EmbersCapabilities.EMBER_CAPABILITY,null)) {
					IEmberCapability capability = stack.getCapability(EmbersCapabilities.EMBER_CAPABILITY, null);
					return capability.getEmber();
				} else {
					return 0;
				}
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				Comparable a = getCompare(stack1);
				Comparable b = getCompare(stack2);
				if (a.compareTo(b) != 0)
					return I18n.format("embers.filter.ember", min(a, b), max(a, b));
				else if (setting == EnumFilterSetting.STRICT)
					return I18n.format("embers.filter.ember.strict", min(a, b), max(a, b));
				else
					return I18n.format("embers.filter.ember.fuzzy", min(a, b), max(a, b));
			}
		}); //EMBER
		FilterUtil.registerComparator(new ComparatorNormal("ember_fuel",160) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return EmbersAPI.getEmberValue(stack1) > 0 && EmbersAPI.getEmberValue(stack2) > 0;
			}

			@Override
			public Comparable getCompare(ItemStack stack) {
				double ember = EmbersAPI.getEmberValue(stack);
				if(ember > 0) {
					return ember;
				} else {
					return 0;
				}
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				Comparable a = getCompare(stack1);
				Comparable b = getCompare(stack2);
				if (a.compareTo(b) != 0)
					return I18n.format("embers.filter.ember_fuel", min(a, b), max(a, b));
				else if (setting == EnumFilterSetting.STRICT)
					return I18n.format("embers.filter.ember_fuel.strict", min(a, b), max(a, b));
				else
					return I18n.format("embers.filter.ember_fuel.fuzzy", min(a, b), max(a, b));
			}
		}); //EMBER FUEL
		FilterUtil.registerComparator(new ComparatorMatch("has_forge_energy",200) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return stack1.hasCapability(CapabilityEnergy.ENERGY,null) && stack2.hasCapability(CapabilityEnergy.ENERGY,null);
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				return I18n.format("embers.filter.has_forge_energy");
			}
		}); //HAS FE
		FilterUtil.registerComparator(new ComparatorNormal("forge_energy",205) {
			@Override
			public boolean match(ItemStack stack1, ItemStack stack2) {
				return stack1.hasCapability(CapabilityEnergy.ENERGY,null) && stack2.hasCapability(CapabilityEnergy.ENERGY,null);
			}

			@Override
			public Comparable getCompare(ItemStack stack) {
				if(stack.hasCapability(CapabilityEnergy.ENERGY,null)) {
					IEnergyStorage capability = stack.getCapability(CapabilityEnergy.ENERGY, null);
					return capability.getEnergyStored();
				} else {
					return 0;
				}
			}

			@Override
			public String format(ItemStack stack1, ItemStack stack2, EnumFilterSetting setting, boolean inverted) {
				Comparable a = getCompare(stack1);
				Comparable b = getCompare(stack2);
				if (a.compareTo(b) != 0)
					return I18n.format("embers.filter.forge_energy", min(a, b), max(a, b));
				else if (setting == EnumFilterSetting.STRICT)
					return I18n.format("embers.filter.forge_energy.strict", min(a, b), max(a, b));
				else
					return I18n.format("embers.filter.forge_energy.fuzzy", min(a, b), max(a, b));
			}
		}); //FE

		FilterUtil.registerFilter(FilterUtil.FILTER_ANY);
		FilterUtil.registerFilter(FilterUtil.FILTER_EXISTING);
		FilterUtil.registerFilter(FilterUtil.FILTER_NOT_EXISTING);
		FilterUtil.registerFilter(FilterSieve.RESOURCE_LOCATION, FilterSieve::new);
		FilterUtil.registerFilter(FilterItem.RESOURCE_LOCATION, FilterItem::new);
	}

	private Comparable min(Comparable a, Comparable b) {
		if(a.compareTo(b) < 0)
			return a;
		else
			return b;
	}

	private Comparable max(Comparable a, Comparable b) {
		if(a.compareTo(b) > 0)
			return a;
		else
			return b;
	}

	public static BoreOutput getBoreOutput(World world, BlockPos pos) {
		int dimensionId = world.provider.getDimension();
		Biome biome = world.getBiome(pos);
		for(BoreOutput output : boreOutputSets) {
			if(output.dimensionIds.contains(dimensionId) && output.biomeIds.contains(biome.getRegistryName()))
				return output;
		}
		return defaultBoreOutput;
	}

	public static ItemStampingRecipe getStampingRecipe(ItemStack input, FluidStack fluid, ItemStack stamp) {
		ItemStampingRecipe matchedRecipe = null;

		for (ItemStampingRecipe recipe : stampingRecipes) {
			if (recipe.matches(input, fluid, stamp))
				matchedRecipe = recipe;
		}

		return matchedRecipe;
	}

	@Deprecated
	public static ItemStampingRecipe getStampingRecipe(ItemStack stack, FluidStack fluid, EnumStampType type){
		for (int i = 0; i < stampingRecipes.size(); i ++){
			if (stampingRecipes.get(i).matches(stack, fluid, type)){
				return stampingRecipes.get(i);
			}
		}
		return null;
	}

	@Deprecated
	public static ItemStampingOreRecipe getStampingOreRecipe(ItemStack stack, FluidStack fluid, EnumStampType type){
		for (int i = 0; i < stampingOreRecipes.size(); i ++){
			if (stampingOreRecipes.get(i).matches(stack, fluid, type)){
				return stampingOreRecipes.get(i);
			}
		}
		return null;
	}

	public static ItemMeltingRecipe getMeltingRecipe(ItemStack stack){
		for (int i = 0; i < meltingRecipes.size(); i ++){
			if (meltingRecipes.get(i).matches(stack)){
				return meltingRecipes.get(i);
			}
		}
		return null;
	}

	@Deprecated
	public static ItemMeltingOreRecipe getMeltingOreRecipe(ItemStack stack){
		for (int i = 0; i < meltingOreRecipes.size(); i ++){
			if (meltingOreRecipes.get(i).matches(stack)){
				return meltingOreRecipes.get(i);
			}
		}
		return null;
	}

	public static FluidMixingRecipe getMixingRecipe(ArrayList<FluidStack> fluids){
		for (int i = 0; i < mixingRecipes.size(); i ++){
			if (mixingRecipes.get(i).matches(fluids)){
				return mixingRecipes.get(i);
			}
		}
		return null;
	}

	public static FluidReactionRecipe getFluidReactionRecipe(FluidStack fluid){
		for (int i = 0; i < fluidReactionRecipes.size(); i ++){
			if (fluidReactionRecipes.get(i).matches(fluid)){
				return fluidReactionRecipes.get(i);
			}
		}
		return null;
	}

	public static AlchemyRecipe getAlchemyRecipe(ItemStack center, List<ItemStack> outside) {
		AlchemyRecipe matchedRecipe = null;

		for (AlchemyRecipe recipe : alchemyRecipes) {
			if (recipe.matches(center, outside) && (matchedRecipe == null || recipe.outsideIngredients.size() > matchedRecipe.outsideIngredients.size()))
				matchedRecipe = recipe;
		}

		return matchedRecipe;
	}

	@Deprecated
	public static AlchemyRecipe getAlchemyRecipe(ItemStack center, ItemStack stack1, ItemStack stack2, ItemStack stack3, ItemStack stack4){
		return getAlchemyRecipe(center, Lists.newArrayList(stack1,stack2,stack3,stack4));
	}

	public static DawnstoneAnvilRecipe getDawnstoneAnvilRecipe(ItemStack bottom, ItemStack top) {
		for (DawnstoneAnvilRecipe recipe : dawnstoneAnvilRecipes) {
			if (recipe.matches(bottom, top))
				return recipe;
		}

		return null;
	}

	public static boolean isBlacklistedFromRepair(ItemStack stack) {
		return dawnstoneRepairBlacklist.stream().anyMatch(ingredient -> ingredient.apply(stack));
	}

	public static boolean isBlacklistedFromMateriaRepair(ItemStack stack) {
		return dawnstoneMateriaBlacklist.stream().anyMatch(ingredient -> ingredient.apply(stack));
	}

	public static boolean isBlacklistedFromBreakdown(ItemStack stack) {
		return dawnstoneBreakdownBlacklist.stream().anyMatch(ingredient -> ingredient.apply(stack));
	}


	public static HeatCoilRecipe getHeatCoilRecipe(ItemStack input) {
		for (HeatCoilRecipe recipe : heatCoilRecipes) {
			if (recipe.matches(input))
				return recipe;
		}

		return null;
	}

	private static class CreativeHeatRecipe extends DawnstoneAnvilRecipe implements IWrappableRecipe {

		@Override
		public boolean matches(ItemStack input1, ItemStack input2) {
			return ItemModUtil.hasHeat(input1) && input2.getItem() == ItemRegister.CREATIVE_HEAT;
		}

		@Override
		public List<ItemStack> getResult(TileEntity tile, ItemStack input1, ItemStack input2) {
			ItemStack result = input1.copy();
			ItemModUtil.setHeat(result,ItemModUtil.getMaxHeat(result));
			return Lists.newArrayList(result);
		}

		@Override
		public List<DawnstoneAnvilRecipe> getWrappers() {
			return Lists.newArrayList();
		}
	}
}
