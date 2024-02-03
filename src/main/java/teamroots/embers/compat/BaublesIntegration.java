package teamroots.embers.compat;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import teamroots.embers.Embers;
import teamroots.embers.api.alchemy.AspectList.AspectRangeList;
import teamroots.embers.api.capabilities.EmbersCapabilities;
import teamroots.embers.api.power.IEmberCapability;
import teamroots.embers.block.BlockExplosionPedestal;
import teamroots.embers.config.ConfigMisc;
import teamroots.embers.item.ItemEmberStorage;
import teamroots.embers.item.bauble.*;
import teamroots.embers.recipe.AlchemyRecipe;
import teamroots.embers.recipe.RecipeRegistry;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.ItemRegister;
import teamroots.embers.register.TileEntityRegister;
import teamroots.embers.research.ResearchBase;
import teamroots.embers.research.ResearchManager;
import teamroots.embers.research.subtypes.ResearchShowItem;
import teamroots.embers.tileentity.TileEntityExplosionPedestal;

public class BaublesIntegration {

    public static final Item ASHEN_AMULET = new ItemAshenAmulet("ashen_amulet", true);
    public static final Item CLIMBERS_BELT = new ItemTrinket("climbers_belt", true);
    public static final Item CRYSTAL_LENSES = new ItemTrinket("crystal_lenses", true);
    public static final Item DAWNSTONE_MAIL = new ItemDawnstoneMail("dawnstone_mail", true);
    public static final Item EMBER_AMULET = new ItemEmberAmulet("ember_amulet", true);
    public static final Item EMBER_BELT = new ItemEmberBelt("ember_belt", true);
    public static final Item EMBER_RING = new ItemEmberRing("ember_ring", true);
    public static final Item EXPLOSION_CHARM = new ItemExplosionCharm("explosion_charm", true);
    public static final Item GLIMMER_CHARM_BAUBLE = new ItemTrinket("glimmer_charm", true);
    public static final Item MANTLE_BULB = new ItemEmberBulb();
    public static final Item NONBELIEVER_AMULET = new ItemNonbelieverAmulet("nonbeliever_amulet", true);
    public static final Item RADIANT_CROWN_BAUBLE = new ItemTrinket("radiant_crown", true);
    public static final Item ROCKET_BOOSTER = new ItemTrinket("rocket_booster", true);
    public static final Block EXPLOSION_PEDESTAL = new BlockExplosionPedestal(Material.ROCK, "explosion_pedestal", true).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.6f);

    public static ResourceLocation getRL(String s) {
        return new ResourceLocation(Embers.MODID, s);
    }

    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        event.getRegistry().register(new ShapedOreRecipe(getRL("ember_ring"), new ItemStack(EMBER_RING, 1), true, new Object[]{
                "CN ",
                "N N",
                " N ",
                'C', ItemRegister.EMBER_CLUSTER,
                'N', "nuggetDawnstone"}).setMirrored(true).setRegistryName(getRL("ember_ring")));
        event.getRegistry().register(new ShapedOreRecipe(getRL("ember_amulet"), new ItemStack(EMBER_AMULET, 1), true, new Object[]{
                " L ",
                "L L",
                "NCN",
                'C', ItemRegister.EMBER_CLUSTER,
                'N', "nuggetDawnstone",
                'L', Items.LEATHER}).setRegistryName(getRL("ember_amulet")));
        event.getRegistry().register(new ShapedOreRecipe(getRL("ember_belt"), new ItemStack(EMBER_BELT, 1), true, new Object[]{
                "LIL",
                "L L",
                "PCP",
                'C', ItemRegister.EMBER_CLUSTER,
                'I', "ingotDawnstone",
                'P', "plateDawnstone",
                'L', Items.LEATHER}).setRegistryName(getRL("ember_belt")));
        event.getRegistry().register(new ShapedOreRecipe(getRL("ember_bulb"), new ItemStack(MANTLE_BULB, 1), true, new Object[]{
                " CI",
                "GSG",
                " G ",
                'I', "ingotLead",
                'S', ItemRegister.EMBER_CLUSTER,
                'C', "plateDawnstone",
                'G', "blockGlass"}).setMirrored(true).setRegistryName(getRL("ember_bulb")));
        event.getRegistry().register(new ShapedOreRecipe(getRL("ashen_amulet"), new ItemStack(ASHEN_AMULET, 1), true, new Object[]{
                " L ",
                "L L",
                "NCN",
                'C', "dustAsh",
                'N', ItemRegister.ARCHAIC_BRICK,
                'L', Items.LEATHER}).setRegistryName(getRL("ashen_amulet")));
        /*event.getRegistry().register(new ShapedOreRecipe(getRL("explosion_charm"), new ItemStack(explosion_charm, 1), true, new Object[]{
                "LB ",
                "BCB",
                " B ",
                'L', Items.LEATHER,
                'C', RegistryManager.ember_cluster,
                'B', RegistryManager.archaic_brick}).setMirrored(true).setRegistryName(getRL("explosion_charm")));*/
        event.getRegistry().register(new ShapedOreRecipe(getRL("dawnstone_mail"), new ItemStack(DAWNSTONE_MAIL, 1), true, new Object[]{
                "P P",
                "PPP",
                "PPP",
                'P', "plateDawnstone"}).setMirrored(true).setRegistryName(getRL("dawnstone_mail")));
        /*event.getRegistry().register(new ShapedOreRecipe(getRL("nonbeliever_amulet"), new ItemStack(nonbeliever_amulet, 1), true, new Object[]{
                " L ",
                "L L",
                "NCN",
                'C', RegistryManager.eldritch_insignia,
                'N', RegistryManager.archaic_circuit,
                'L', Items.LEATHER}).setRegistryName(getRL("nonbeliever_amulet")));*/

        Ingredient leather = Ingredient.fromItem(Items.LEATHER);
        Ingredient archaicBrick = Ingredient.fromItem(ItemRegister.ARCHAIC_BRICK);
        Ingredient eldritchInsignia = Ingredient.fromItem(ItemRegister.ELDRITCH_INSIGNIA);
        Ingredient archaicCircuit = Ingredient.fromItem(ItemRegister.ARCHAIC_CIRCUIT);

        RecipeRegistry.alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 8, 32).setRange("copper", 8, 32), Ingredient.fromItem(ItemRegister.EMBER_CLUSTER), Lists.newArrayList(archaicBrick, leather, archaicBrick, archaicBrick), new ItemStack(EXPLOSION_CHARM, 1)));
        RecipeRegistry.alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 32, 64).setRange("silver", 32, 64), Ingredient.fromItem(ASHEN_AMULET), Lists.newArrayList(archaicCircuit, archaicBrick, archaicCircuit, eldritchInsignia), new ItemStack(NONBELIEVER_AMULET, 1)));
        RecipeRegistry.alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("copper", 16, 24).setRange("silver", 16, 24),
                Ingredient.fromItem(ItemRegister.DUST_EMBER),
                Lists.newArrayList(Ingredient.EMPTY, Ingredient.fromItem(EXPLOSION_CHARM), Ingredient.EMPTY, Ingredient.fromStacks(new ItemStack(BlockRegister.ALCHEMY_PEDESTAL))),
                new ItemStack(EXPLOSION_PEDESTAL, 1)));
    }

    public static void registerAll() //éw
    {
        if (ConfigMisc.enableWipStuff) {
            ItemRegister.INSTANCE.add(BaublesIntegration.CLIMBERS_BELT);
            ItemRegister.INSTANCE.add(BaublesIntegration.CRYSTAL_LENSES);
            ItemRegister.INSTANCE.add(BaublesIntegration.GLIMMER_CHARM_BAUBLE);
            ItemRegister.INSTANCE.add(BaublesIntegration.RADIANT_CROWN_BAUBLE);
            ItemRegister.INSTANCE.add(BaublesIntegration.ROCKET_BOOSTER);
        }
        ItemRegister.INSTANCE.add(BaublesIntegration.ASHEN_AMULET);
        ItemRegister.INSTANCE.add(BaublesIntegration.DAWNSTONE_MAIL);
        ItemRegister.INSTANCE.add(BaublesIntegration.EMBER_AMULET);
        ItemRegister.INSTANCE.add(BaublesIntegration.EMBER_BELT);
        ItemRegister.INSTANCE.add(BaublesIntegration.EMBER_RING);
        ItemRegister.INSTANCE.add(BaublesIntegration.EXPLOSION_CHARM);
        ItemRegister.INSTANCE.add(BaublesIntegration.MANTLE_BULB);
        ItemRegister.INSTANCE.add(BaublesIntegration.NONBELIEVER_AMULET);

        BlockRegister.INSTANCE.add(EXPLOSION_PEDESTAL);

        TileEntityRegister.register(TileEntityExplosionPedestal.class, "tile_entity_explosion_pedestal");
    }

    public static void init() {

    }

    @SideOnly(Side.CLIENT)
    public static void registerClientSide() {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemEmberStorage.ColorHandler(), MANTLE_BULB);
    }

    public static double getEmberCapacityTotal(EntityPlayer player) {
        IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
        double amount = 0;
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack bauble = handler.getStackInSlot(i);
            if (bauble.hasCapability(EmbersCapabilities.EMBER_CAPABILITY, null)) {
                IEmberCapability capability = bauble.getCapability(EmbersCapabilities.EMBER_CAPABILITY, null);
                amount += capability.getEmberCapacity();
            }
        }
        return amount;
    }

    public static double getEmberTotal(EntityPlayer player) {
        IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
        double amount = 0;
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack bauble = handler.getStackInSlot(i);
            if (bauble.hasCapability(EmbersCapabilities.EMBER_CAPABILITY, null)) {
                IEmberCapability capability = bauble.getCapability(EmbersCapabilities.EMBER_CAPABILITY, null);
                amount += capability.getEmber();
            }
        }
        return amount;
    }

    public static double removeEmber(EntityPlayer player, double amount) {
        IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack bauble = handler.getStackInSlot(i);
            if (bauble.hasCapability(EmbersCapabilities.EMBER_CAPABILITY, null)) {
                IEmberCapability capability = bauble.getCapability(EmbersCapabilities.EMBER_CAPABILITY, null);
                amount -= capability.removeAmount(amount, true);
            }
        }
        return amount;
    }

    public static void initBaublesCategory() {
        ItemStack fullBulb = ((ItemEmberBulb) MANTLE_BULB).withFill(((ItemEmberBulb) MANTLE_BULB).getCapacity());

        ResearchManager.cost_reduction = new ResearchShowItem("cost_reduction", new ItemStack(EMBER_AMULET), 5, 5).addItem(new ResearchShowItem.DisplayItem(new ItemStack(EMBER_AMULET), new ItemStack(EMBER_BELT), new ItemStack(EMBER_RING)));
        ResearchManager.mantle_bulb = new ResearchBase("mantle_bulb", fullBulb, 7, 3);
        ResearchManager.explosion_charm = new ResearchBase("explosion_charm", new ItemStack(EXPLOSION_CHARM), 9, 2);
        ResearchManager.nonbeliever_amulet = new ResearchBase("nonbeliever_amulet", new ItemStack(NONBELIEVER_AMULET), 1, 3);
        ResearchManager.ashen_amulet = new ResearchBase("ashen_amulet", new ItemStack(ASHEN_AMULET), 4, 3);
        ResearchManager.dawnstone_mail = new ResearchBase("dawnstone_mail", new ItemStack(DAWNSTONE_MAIL), 3, 7);
        ResearchManager.explosion_pedestal = new ResearchBase("explosion_pedestal", new ItemStack(EXPLOSION_PEDESTAL), 11, 1).addAncestor(ResearchManager.explosion_charm);

        ResearchManager.subCategoryBaubles.addResearch(ResearchManager.cost_reduction);
        ResearchManager.subCategoryBaubles.addResearch(ResearchManager.mantle_bulb);
        ResearchManager.subCategoryBaubles.addResearch(ResearchManager.explosion_charm);
        ResearchManager.subCategoryBaubles.addResearch(ResearchManager.nonbeliever_amulet);
        ResearchManager.subCategoryBaubles.addResearch(ResearchManager.ashen_amulet);
        ResearchManager.subCategoryBaubles.addResearch(ResearchManager.dawnstone_mail);
        ResearchManager.subCategoryBaubles.addResearch(ResearchManager.explosion_pedestal);
    }

    public static void loadConfig() {
        //NOOP
    }
}
