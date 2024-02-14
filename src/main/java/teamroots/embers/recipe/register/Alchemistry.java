package teamroots.embers.recipe.register;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.oredict.OreIngredient;
import teamroots.embers.api.alchemy.AspectList;
import teamroots.embers.api.alchemy.AspectList.AspectRangeList;
import teamroots.embers.block.BlockSeedNew;
import teamroots.embers.recipe.AlchemyRecipe;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.ItemRegister;
import teamroots.embers.util.IngredientSpecial;

import static teamroots.embers.recipe.RecipeRegistry.alchemyRecipes;

public class Alchemistry implements IRegister {
    public static final Alchemistry INSTANCE = new Alchemistry();

    private Alchemistry() {
    }

    @Override
    public void execute() {
        OreIngredient ingotIron = new OreIngredient("ingotIron");
        OreIngredient plateIron = new OreIngredient("plateIron");
        OreIngredient ingotGold = new OreIngredient("ingotGold");
        OreIngredient ingotSilver = new OreIngredient("ingotSilver");
        OreIngredient plateSilver = new OreIngredient("plateSilver");
        OreIngredient ingotCopper = new OreIngredient("ingotCopper");
        OreIngredient plateCopper = new OreIngredient("plateCopper");
        OreIngredient ingotLead = new OreIngredient("ingotLead");
        OreIngredient plateLead = new OreIngredient("plateLead");
        OreIngredient ingotAluminum = new OreIngredient("ingotAluminum");
        OreIngredient ingotNickel = new OreIngredient("ingotNickel");
        OreIngredient ingotTin = new OreIngredient("ingotTin");
        OreIngredient ingotDawnstone = new OreIngredient("ingotDawnstone");
        OreIngredient plateDawnstone = new OreIngredient("plateDawnstone");
        OreIngredient plateBronze = new OreIngredient("plateBronze");
        OreIngredient ingotBronze = new OreIngredient("ingotBronze");
        Ingredient emberShard = Ingredient.fromItem(ItemRegister.SHARD_EMBER);
        Ingredient emberCrystal = Ingredient.fromItem(ItemRegister.CRYSTAL_EMBER);
        OreIngredient quartz = new OreIngredient("gemQuartz");
        OreIngredient ash = new OreIngredient("dustAsh");
        OreIngredient string = new OreIngredient("string");
        Ingredient wool = Ingredient.fromItem(Item.getItemFromBlock(Blocks.WOOL));
        Ingredient coal = Ingredient.fromItem(Items.COAL);
        Ingredient clay = Ingredient.fromItem(Items.CLAY_BALL);
        OreIngredient diamond = new OreIngredient("gemDiamond");
        OreIngredient gunpowder = new OreIngredient("gunpowder");
        Ingredient bonemeal = Ingredient.fromStacks(new ItemStack(Items.DYE, 1, 15));
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
        Ingredient anyMetalSeed = new IngredientSpecial(stack -> Block.getBlockFromItem(stack.getItem()) instanceof BlockSeedNew);

        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 48, 64).setRange("copper", 48, 64), quartz, Lists.newArrayList(ingotCopper, ingotCopper, emberShard, emberShard), new ItemStack(BlockRegister.SEED_COPPER)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 48, 64).setRange("silver", 48, 64), quartz, Lists.newArrayList(ingotSilver, ingotSilver, emberShard, emberShard), new ItemStack(BlockRegister.SEED_SILVER)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 48, 64).setRange("lead", 48, 64), quartz, Lists.newArrayList(ingotLead, ingotLead, emberShard, emberShard), new ItemStack(BlockRegister.SEED_LEAD)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 48, 64).setRange("dawnstone", 48, 64), quartz, Lists.newArrayList(ingotGold, ingotGold, emberShard, emberShard), new ItemStack(BlockRegister.SEED_GOLD)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 72, 96).setRange("silver", 24, 32), quartz, Lists.newArrayList(ingotTin, ingotTin, emberShard, emberShard), new ItemStack(BlockRegister.SEED_TIN)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 48, 64).setRange("copper", 24, 32).setRange("silver", 24, 32), quartz, Lists.newArrayList(ingotAluminum, ingotAluminum, emberShard, emberShard), new ItemStack(BlockRegister.SEED_ALUMINUM)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 72, 96).setRange("lead", 24, 32), quartz, Lists.newArrayList(ingotNickel, ingotNickel, emberShard, emberShard), new ItemStack(BlockRegister.SEED_NICKEL)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 96, 128), quartz, Lists.newArrayList(ingotIron, ingotIron, emberShard, emberShard), new ItemStack(BlockRegister.SEED_IRON)));

        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 12, 24).setRange("lead", 12, 24), wool, Lists.newArrayList(ash, ash, string, string), new ItemStack(ItemRegister.ASHEN_CLOTH, 2)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 32, 48).setRange("lead", 24, 40), diamond, Lists.newArrayList(ingotDawnstone, coal, coal, coal), new ItemStack(ItemRegister.INFLICTOR_GEM, 1)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 64, 80), quartz, Lists.newArrayList(gunpowder, gunpowder, emberShard, emberShard), new ItemStack(ItemRegister.GLIMMER_SHARD, 1)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 24, 36), ingotIron, Lists.newArrayList(quartz, clay, lapis), new ItemStack(ItemRegister.ISOLATED_MATERIA, 4)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 12, 18), clay, Lists.newArrayList(bonemeal, bonemeal), new ItemStack(ItemRegister.ADHESIVE, 6)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("copper", 8, 16), redstone, Lists.newArrayList(ash, ash, cobblestone, cobblestone), new ItemStack(Blocks.NETHERRACK, 2)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("copper", 8, 16), ash, Lists.newArrayList(sand, sand, sand, sand), new ItemStack(Blocks.SOUL_SAND, 4)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 24, 48).setRange("copper", 24, 48), emberCrystal, Lists.newArrayList(gunpowder, emberShard, emberShard, emberShard), new ItemStack(ItemRegister.EMBER_CLUSTER, 1)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 32, 48).setRange("silver", 24, 32), Ingredient.fromItem(ItemRegister.ANCIENT_MOTIVE_CORE), Lists.newArrayList(ingotDawnstone, emberCluster, ingotDawnstone, plateCopper), new ItemStack(ItemRegister.WILDFIRE_CORE, 1)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 4, 8), archaicBrick, Lists.newArrayList(soulsand, soulsand, clay, clay), new ItemStack(ItemRegister.ARCHAIC_BRICK, 5)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 24, 32), emberShard, Lists.newArrayList(archaicBrick, archaicBrick, archaicBrick, archaicBrick), new ItemStack(ItemRegister.ANCIENT_MOTIVE_CORE, 1)));
        if (leadSword.getMatchingStacks().length > 0) {//If there is a lead sword in the game (there should be, but just in case...
            alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("silver", 64, 96).setRange("lead", 64, 96), leadSword, Lists.newArrayList(blockCoal, obsidian, ingotLead, ingotLead), new ItemStack(ItemRegister.TYRFING, 1)));
        }
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("copper", 16, 24),
                gunpowder,
                Lists.newArrayList(plateIron, plateIron, plateIron, ingotCopper),
                new ItemStack(ItemRegister.BLASTING_CORE, 1)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 16, 32).setRange("lead", 48, 72),
                archaicCircuit,
                Lists.newArrayList(archaicBrick, coal, archaicBrick, coal),
                new ItemStack(ItemRegister.ELDRITCH_INSIGNIA, 1)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("copper", 24, 48).setRange("lead", 40, 64),
                plateCopper,
                Lists.newArrayList(archaicCircuit, ingotCopper, archaicCircuit, ingotCopper),
                new ItemStack(ItemRegister.INTELLIGENT_APPARATUS, 1)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 16, 32).setRange("silver", 16, 32),
                emberCrystal,
                Lists.newArrayList(plateDawnstone, plateDawnstone, plateDawnstone, ingotSilver),
                new ItemStack(ItemRegister.FLAME_BARRIER, 1)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("copper", 8, 16).setRange("silver", 32, 64),
                emberCrystal,
                Lists.newArrayList(plateDawnstone, plateSilver, plateDawnstone, plateSilver),
                new ItemStack(ItemRegister.FOCAL_LENS, 1)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("lead", 32, 128),
                Ingredient.fromItem(ItemRegister.ASHEN_CLOTH),
                Lists.newArrayList(plateLead, plateLead, plateLead, plateLead),
                new ItemStack(ItemRegister.SHIFTING_SCALES, 1)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("copper", 32, 128),
                ingotBronze,
                Lists.newArrayList(plateBronze, plateBronze, plateBronze, plateBronze),
                new ItemStack(ItemRegister.WINDING_GEARS, 1)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("iron", 8, 16).setRange("silver", 8, 16).setRange("dawnstone", 8, 16).setRange("lead", 8, 16).setRange("copper", 8, 16).fixMathematicalError(),
                Ingredient.fromItem(ItemRegister.WILDFIRE_CORE),
                Lists.newArrayList(ingotSilver, plateDawnstone, ingotSilver, plateDawnstone),
                new ItemStack(BlockRegister.EMBER_PIPE, 8)));

        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 20, 30).setRange("silver", 32, 64),
                ingotSilver,
                Lists.newArrayList(fluidPipe, glass, fluidPipe, redstoneBlock),
                new ItemStack(BlockRegister.CATALYTIC_PLUG, 1)));
        alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList(AspectList.createStandard(1, 1, 1, 1, 1), AspectList.createStandard(16, 16, 16, 16, 16)).fixMathematicalError(),
                anyMetalSeed,
                Lists.newArrayList(Ingredient.fromItem(ItemRegister.DUST_EMBER), new OreIngredient("dustRedstone")),
                new ItemStack(ItemRegister.DUST_METALLURGIC, 3)));

    }
}
