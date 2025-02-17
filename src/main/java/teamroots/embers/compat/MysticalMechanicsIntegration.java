package teamroots.embers.compat;

import mysticalmechanics.api.IGearBehavior;
import mysticalmechanics.api.IGearData;
import mysticalmechanics.api.IMechCapability;
import mysticalmechanics.api.MysticalMechanicsAPI;
import mysticalmechanics.handler.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.oredict.ShapedOreRecipe;
import teamroots.embers.Embers;
import teamroots.embers.api.tile.IExtraCapabilityInformation;
import teamroots.embers.block.BlockMechActuator;
import teamroots.embers.block.BlockMechActuatorSingle;
import teamroots.embers.block.BlockSteamEngine;
import teamroots.embers.config.ConfigMachine;
import teamroots.embers.config.ConfigMaterial;
import teamroots.embers.item.ItemBase;
import teamroots.embers.particle.ParticleUtil;
import teamroots.embers.recipe.ItemStampingRecipe;
import teamroots.embers.recipe.RecipeRegistry;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.FluidRegister;
import teamroots.embers.register.ItemRegister;
import teamroots.embers.register.TileEntityRegister;
import teamroots.embers.research.ResearchBase;
import teamroots.embers.research.ResearchManager;
import teamroots.embers.research.subtypes.ResearchFakePage;
import teamroots.embers.research.subtypes.ResearchShowItem;
import teamroots.embers.tileentity.TileEntityMechActuator;
import teamroots.embers.tileentity.TileEntityMechActuatorRenderer;
import teamroots.embers.tileentity.TileEntityMechActuatorSingle;
import teamroots.embers.tileentity.TileEntitySteamEngine;
import teamroots.embers.util.Misc;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class MysticalMechanicsIntegration {
    public static final ResourceLocation IRON_GEAR_BEHAVIOR = new ResourceLocation("mysticalmechanics", "gear_iron");
    public static final ResourceLocation GOLD_GEAR_BEHAVIOR = new ResourceLocation("mysticalmechanics", "gear_gold");
    public static final ResourceLocation GOLD_GEAR_ON_BEHAVIOR = new ResourceLocation("mysticalmechanics", "gear_gold_on");
    public static final ResourceLocation GOLD_GEAR_OFF_BEHAVIOR = new ResourceLocation("mysticalmechanics", "gear_gold_off");
    public static final ResourceLocation DAWNSTONE_GEAR_BEHAVIOR = new ResourceLocation(Embers.MODID, "gear_dawnstone");
    public static final double IRON_GEAR_MAX_POWER = 80;
    public static final double GOLD_GEAR_MAX_POWER = 320;

    public static final Item GEAR_DAWNSTONE = new ItemBase("gear_dawnstone", true);

    public static final Block STEAM_ENGINE = new BlockSteamEngine(Material.ROCK, "steam_engine", true).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.0f);
    public static final Block MECH_ACTUATOR = new BlockMechActuator(Material.ROCK, "mech_actuator", true).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.0f);
    public static final Block MECH_ACTUATOR_SINGLE = new BlockMechActuatorSingle(Material.ROCK, "mech_actuator_single", true).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.0f);

    static Random random = new Random();

    public static ResourceLocation getRL(String s) {
        return new ResourceLocation(Embers.MODID, s);
    }

    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        Block axle_iron = Block.getBlockFromName("mysticalmechanics:axle_iron");
        event.getRegistry().register(new ShapedOreRecipe(getRL("gear_dawnstone"), new ItemStack(GEAR_DAWNSTONE, 1), true, new Object[]{
                " N ",
                "NCN",
                " N ",
                'C', "nuggetDawnstone",
                'N', "ingotDawnstone"}).setRegistryName(getRL("gear_dawnstone")));
        event.getRegistry().register(new ShapedOreRecipe(getRL("mech_actuator_single"), new ItemStack(MECH_ACTUATOR_SINGLE, 1), true, new Object[]{
                "SPI",
                'P', "gearIron",
                'S', BlockRegister.MECH_ACCESSOR,
                'I', axle_iron}).setRegistryName(getRL("mech_actuator_single")));
        event.getRegistry().register(new ShapedOreRecipe(getRL("mech_actuator"), new ItemStack(MECH_ACTUATOR, 1), true, new Object[]{
                " I ",
                "IPI",
                "SI ",
                'P', "gearIron",
                'S', BlockRegister.MECH_ACCESSOR,
                'I', axle_iron}).setRegistryName(getRL("mech_actuator")));
        event.getRegistry().register(new ShapedOreRecipe(getRL("steam_engine"), new ItemStack(STEAM_ENGINE, 1), true, new Object[]{
                " II",
                "APC",
                "FFC",
                'C', "plateCopper",
                'P', "gearIron",
                'A', axle_iron,
                'I', BlockRegister.PIPE,
                'F', "plateIron"}).setRegistryName(getRL("steam_engine")));

        Ingredient stampGear = Ingredient.fromItem(ItemRegister.STAMP_GEAR);
        int gearAmount = ConfigMachine.STAMPER.stampGearAmount * RecipeRegistry.INGOT_AMOUNT;
        RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_IRON, gearAmount), stampGear, new ItemStack(RegistryHandler.IRON_GEAR, 1)));
        RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_GOLD, gearAmount), stampGear, new ItemStack(RegistryHandler.GOLD_GEAR, 1)));
        RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(FluidRegister.FLUID_MOLTEN_DAWNSTONE, gearAmount), stampGear, new ItemStack(GEAR_DAWNSTONE, 1)));
    }

    public static void registerAll() //éw parté déux
    {
        if (ConfigMaterial.DAWNSTONE.isNotOff()) {
            ItemRegister.INSTANCE.add(GEAR_DAWNSTONE);
        }

        BlockRegister.INSTANCE.add(STEAM_ENGINE);
        BlockRegister.INSTANCE.add(MECH_ACTUATOR);
        BlockRegister.INSTANCE.add(MECH_ACTUATOR_SINGLE);

        TileEntityRegister.register(TileEntitySteamEngine.class, "tile_entity_steam_engine");
        TileEntityRegister.register(TileEntityMechActuator.class, "tile_entity_mech_actuator");
        TileEntityRegister.register(TileEntityMechActuatorSingle.class, "tile_entity_mech_actuator_single");
    }

    public static void initOreDict() {
        if (ConfigMaterial.DAWNSTONE.isNotOff()) {
            OreDictionary.registerOre("gearDawnstone", GEAR_DAWNSTONE);
        }
    }

    public static void init() {
        if (ConfigMaterial.DAWNSTONE.isNotOff()) {
            MysticalMechanicsAPI.IMPL.registerGear(DAWNSTONE_GEAR_BEHAVIOR, new OreIngredient("gearDawnstone"), new IGearBehavior() {
                @Override
                public double transformPower(TileEntity tile, @Nullable EnumFacing facing, ItemStack gear, IGearData data, double power) {
                    return power;
                }

                @Override
                public void visualUpdate(TileEntity tile, @Nullable EnumFacing facing, ItemStack gear, IGearData data, double powerIn, double powerOut) {
                    int particles = Math.min((int) Math.ceil(powerIn / 40), 5);
                    if (powerIn >= IRON_GEAR_MAX_POWER)
                        for (int i = 0; i < particles; i++) {
                            float xOff = 0.1f + random.nextFloat() * 0.8f;
                            float yOff = 0.1f + random.nextFloat() * 0.8f;
                            float zOff = 0.1f + random.nextFloat() * 0.8f;
                            switch (facing.getAxis()) {
                                case X:
                                    xOff = 0.5f + facing.getXOffset() / 2.0f;
                                    break;
                                case Y:
                                    yOff = 0.5f + facing.getYOffset() / 2.0f;
                                    break;
                                case Z:
                                    zOff = 0.5f + facing.getZOffset() / 2.0f;
                                    break;
                            }
                            ParticleUtil.spawnParticleGlow(tile.getWorld(), tile.getPos().getX() + xOff, tile.getPos().getY() + yOff, tile.getPos().getZ() + zOff, 0, 0, 0, 255, 64, 16, 2.0f, 24);
                        }
                }
            });
        }

        replaceBehavior(IRON_GEAR_BEHAVIOR, new OreIngredient("gearIron"), behavior -> wrapPowerLevelBehavior(behavior, IRON_GEAR_MAX_POWER, 1));
        replaceBehavior(GOLD_GEAR_BEHAVIOR, new OreIngredient("gearGold"), behavior -> wrapPowerLevelBehavior(behavior, GOLD_GEAR_MAX_POWER, 1));
        replaceBehavior(GOLD_GEAR_ON_BEHAVIOR, Ingredient.fromItem(RegistryHandler.GOLD_GEAR_ON), behavior -> wrapPowerLevelBehavior(behavior, GOLD_GEAR_MAX_POWER, 1));
        replaceBehavior(GOLD_GEAR_OFF_BEHAVIOR, Ingredient.fromItem(RegistryHandler.GOLD_GEAR_OFF), behavior -> wrapPowerLevelBehavior(behavior, GOLD_GEAR_MAX_POWER, 1));
    }

    private static void replaceBehavior(ResourceLocation name, Ingredient ingredient, Function<IGearBehavior, IGearBehavior> transformer) {
        IGearBehavior behavior = MysticalMechanicsAPI.IMPL.getGearBehavior(name);
        MysticalMechanicsAPI.IMPL.unregisterGear(name);
        MysticalMechanicsAPI.IMPL.registerGear(name, ingredient, transformer.apply(behavior));
    }

    private static IGearBehavior wrapPowerLevelBehavior(IGearBehavior behavior, double max_power, double slope) {
        return new IGearBehavior() {
            @Override
            public double transformPower(TileEntity tile, @Nullable EnumFacing facing, ItemStack gear, IGearData data, double power) {
                power = behavior.transformPower(tile, facing, gear, data, power);
                return Misc.getDiminishedPower(power, max_power, slope); //Diminishing returns
            }

            @Override
            public void visualUpdate(TileEntity tile, @Nullable EnumFacing facing, ItemStack gear, IGearData data, double powerIn, double powerOut) {
                behavior.visualUpdate(tile, facing, gear, data, powerIn, powerOut);
            }
        };
    }

    @SideOnly(Side.CLIENT)
    public static void registerClientSide() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMechActuator.class, new TileEntityMechActuatorRenderer());
    }

    public static void initMysticalMechanicsCategory() {
        ResearchManager.gearbox = new ResearchBase("gearbox", new ItemStack(RegistryHandler.GEARBOX_FRAME), 2, 4);
        ResearchManager.mergebox = new ResearchBase("mergebox", new ItemStack(RegistryHandler.MERGEBOX_FRAME), 1, 6).addAncestor(ResearchManager.gearbox);
        ResearchManager.axle_iron = new ResearchBase("axle_iron", new ItemStack(RegistryHandler.IRON_AXLE), 2, 0).addAncestor(ResearchManager.gearbox);
        ItemStack gearIron = new ItemStack(RegistryHandler.IRON_GEAR);
        ItemStack gearGold = new ItemStack(RegistryHandler.GOLD_GEAR);
        ItemStack gearDawnstone = new ItemStack(GEAR_DAWNSTONE);
        ItemStack gearGoldOn = new ItemStack(RegistryHandler.GOLD_GEAR_ON);
        ItemStack gearGoldOff = new ItemStack(RegistryHandler.GOLD_GEAR_OFF);
        ResearchManager.gear_iron = new ResearchShowItem("gear_iron", gearIron, 4, 1).addItem(new ResearchShowItem.DisplayItem(gearIron)).addAncestor(ResearchManager.gearbox)
                .addPage(new ResearchShowItem("gear_gold", gearGold, 0, 0).addItem(new ResearchShowItem.DisplayItem(gearGold)))
                .addPage(new ResearchShowItem("gear_redstone", gearGoldOn, 0, 0).addItem(new ResearchShowItem.DisplayItem(gearGoldOff, gearGoldOn)))
                .addPage(new ResearchShowItem("gear_dawnstone", gearDawnstone, 0, 0).addItem(new ResearchShowItem.DisplayItem(gearDawnstone)));
        ResearchManager.actuator = new ResearchBase("actuator", new ItemStack(MECH_ACTUATOR_SINGLE), 9, 5).addAncestor(ResearchManager.gearbox)
                .addPage(new ResearchShowItem("actuator_multi", ItemStack.EMPTY, 0, 0).addItem(new ResearchShowItem.DisplayItem(new ItemStack(MECH_ACTUATOR))))
                .addPage(new ResearchShowItem("actuator_bore", ItemStack.EMPTY, 0, 0).addItem(new ResearchShowItem.DisplayItem(new ItemStack(BlockRegister.EMBER_BORE))))
                .addPage(new ResearchShowItem("actuator_pump", ItemStack.EMPTY, 0, 0).addItem(new ResearchShowItem.DisplayItem(new ItemStack(BlockRegister.MECHANICAL_PUMP))))
                .addPage(new ResearchShowItem("actuator_stamper", ItemStack.EMPTY, 0, 0).addItem(new ResearchShowItem.DisplayItem(new ItemStack(BlockRegister.STAMPER))))
                .addPage(new ResearchShowItem("actuator_mixer", ItemStack.EMPTY, 0, 0).addItem(new ResearchShowItem.DisplayItem(new ItemStack(BlockRegister.MIXER))))
                .addPage(new ResearchShowItem("actuator_auto_hammer", ItemStack.EMPTY, 0, 0).addItem(new ResearchShowItem.DisplayItem(new ItemStack(BlockRegister.AUTO_HAMMER))));
        ResearchFakePage mechanical_mini_boiler = new ResearchFakePage(ResearchManager.mini_boiler, 12, 0);
        ResearchManager.steam_engine = new ResearchBase("steam_engine", new ItemStack(STEAM_ENGINE), 9, 2).addAncestor(ResearchManager.gearbox).addAncestor(mechanical_mini_boiler)
                .addPage(new ResearchBase("steam_engine_overclock", ItemStack.EMPTY, 0, 0));


        ResearchManager.subCategoryMechanicalPower.addResearch(ResearchManager.gearbox);
        ResearchManager.subCategoryMechanicalPower.addResearch(ResearchManager.mergebox);
        ResearchManager.subCategoryMechanicalPower.addResearch(ResearchManager.axle_iron);
        ResearchManager.subCategoryMechanicalPower.addResearch(ResearchManager.gear_iron);
        ResearchManager.subCategoryMechanicalPower.addResearch(ResearchManager.actuator);
        ResearchManager.subCategoryMechanicalPower.addResearch(ResearchManager.steam_engine);

        ResearchManager.subCategoryMechanicalPower.addResearch(mechanical_mini_boiler);
    }

    public static void addCapabilityInformation(List<String> text, TileEntity tileEntity, EnumFacing facing) {
        addCapabilityMechanicalDescription(text, tileEntity, facing);
    }

    public static void addCapabilityMechanicalDescription(List<String> text, TileEntity tile, EnumFacing facing) {
        Capability<IMechCapability> capability = MysticalMechanicsAPI.MECH_CAPABILITY;
        if (tile.hasCapability(capability, facing)) {
            IMechCapability mechCapability = tile.getCapability(capability, facing);
            boolean canInput = mechCapability.isInput(facing);
            boolean canOutput = mechCapability.isOutput(facing);
            IExtraCapabilityInformation.EnumIOType ioType = getEnumIOType(canInput, canOutput);
            if (tile instanceof IExtraCapabilityInformation && ((IExtraCapabilityInformation) tile).hasCapabilityDescription(capability)) {
                ((IExtraCapabilityInformation) tile).addCapabilityDescription(text, capability, facing);
            } else {
                text.add(IExtraCapabilityInformation.formatCapability(ioType, "embers.tooltip.goggles.mechanical", null));
            }
        }
    }

    private static IExtraCapabilityInformation.EnumIOType getEnumIOType(boolean canInput, boolean canOutput) {
        IExtraCapabilityInformation.EnumIOType ioType;
        if (canInput && canOutput)
            ioType = IExtraCapabilityInformation.EnumIOType.BOTH;
        else if (canInput)
            ioType = IExtraCapabilityInformation.EnumIOType.INPUT;
        else if (canOutput)
            ioType = IExtraCapabilityInformation.EnumIOType.OUTPUT;
        else
            ioType = IExtraCapabilityInformation.EnumIOType.NONE;
        return ioType;
    }

/*    public static void loadConfig() {
        //Steam Engine
        TileEntitySteamEngine.NORMAL_FLUID_THRESHOLD = ConfigManager.loadInteger("parameters.steamEngine.fluidThreshold",  TileEntitySteamEngine.NORMAL_FLUID_THRESHOLD, "How much water (in mb) is necessary to start burning solid fuel.");
        TileEntitySteamEngine.NORMAL_FLUID_CONSUMPTION = ConfigManager.loadInteger("parameters.steamEngine.fluidConsumption", TileEntitySteamEngine.NORMAL_FLUID_CONSUMPTION,  "How much water (in mb) is consumed every tick while burning solid fuel.");
        TileEntitySteamEngine.FUEL_MULTIPLIER = ConfigManager.loadDouble("parameters.steamEngine.fuelEfficiency", TileEntitySteamEngine.FUEL_MULTIPLIER,  "How efficient, time-wise, solid fuel is in the steam turbine. 1 = fuel lasts as long as it would in a furnace.");
        TileEntitySteamEngine.SOLID_POWER = ConfigManager.loadDouble("parameters.steamEngine.fuelPower",  TileEntitySteamEngine.SOLID_POWER,  "How much mechanical power is generated while burning solid fuel.");
        TileEntitySteamEngine.MAX_POWER = ConfigManager.loadDouble("parameters.steamEngine.maximumPower",  TileEntitySteamEngine.MAX_POWER,  "How much mechanical power can be generated at max.");
        TileEntitySteamEngine.GAS_CONSUMPTION = ConfigManager.loadInteger("parameters.steamEngine.gasConsumption",  TileEntitySteamEngine.GAS_CONSUMPTION,  "How much gas (in mb), such as steam, is consumed every tick.");
        TileEntitySteamEngine.CAPACITY = ConfigManager.loadInteger("parameters.steamEngine.capacity",  TileEntitySteamEngine.CAPACITY,  "How much fluid (in mb) fits into a Steam Engine.");

    }*/
}
