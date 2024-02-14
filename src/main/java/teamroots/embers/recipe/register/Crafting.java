package teamroots.embers.recipe.register;

import net.minecraftforge.oredict.OreIngredient;

public class Crafting implements IRegister {
    public static final Crafting INSTANCE = new Crafting();

    private Crafting() {
    }

    @Override
    public void execute() {
        OreIngredient ingotIron = new OreIngredient("ingotIron");
        OreIngredient plateIron = new OreIngredient("plateIron");
        OreIngredient ingotGold = new OreIngredient("ingotGold");
        OreIngredient plateGold = new OreIngredient("plateGold");
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
    }
}
