package teamroots.embers.register;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import teamroots.embers.config.ConfigMaterial;
import teamroots.embers.config.ConfigTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ToolMaterialRegister implements IRegistrable<Item.ToolMaterial> {
    public static final ToolMaterialRegister INSTANCE = new ToolMaterialRegister();

    private ToolMaterialRegister(){}

    public static final Item.ToolMaterial TOOL_MAT_ALUMINUM = Util.createToolMaterial("aluminum", ConfigTool.METAL_TOOL.ALUMINUM);
    public static final Item.ToolMaterial TOOL_MAT_BRONZE = Util.createToolMaterial("bronze", ConfigTool.METAL_TOOL.BRONZE);
    public static final Item.ToolMaterial TOOL_MAT_COPPER = Util.createToolMaterial("copper", ConfigTool.METAL_TOOL.COPPER);
    public static final Item.ToolMaterial TOOL_MAT_DAWNSTONE = Util.createToolMaterial("dawnstone", ConfigTool.METAL_TOOL.DAWNSTONE);
    public static final Item.ToolMaterial TOOL_MAT_ELECTRUM = Util.createToolMaterial("electrum", ConfigTool.METAL_TOOL.ELECTRUM);
    public static final Item.ToolMaterial TOOL_MAT_LEAD = Util.createToolMaterial("lead", ConfigTool.METAL_TOOL.LEAD);
    public static final Item.ToolMaterial TOOL_MAT_NICKEL = Util.createToolMaterial("nickel", ConfigTool.METAL_TOOL.NICKEL);
    public static final Item.ToolMaterial TOOL_MAT_SILVER = Util.createToolMaterial("silver", ConfigTool.METAL_TOOL.SILVER);
    public static final Item.ToolMaterial TOOL_MAT_TIN = Util.createToolMaterial("tin", ConfigTool.METAL_TOOL.TIN);
    public static final Item.ToolMaterial TOOL_MAT_TYRFING = Util.createToolMaterial("tyrfing", ConfigTool.METAL_TOOL.TYRFING);

    private final static List<Item.ToolMaterial> REGISTRY = new ArrayList<>(Arrays.asList(
            TOOL_MAT_ALUMINUM,
            TOOL_MAT_BRONZE,
            TOOL_MAT_COPPER,
            TOOL_MAT_DAWNSTONE,
            TOOL_MAT_ELECTRUM,
            TOOL_MAT_LEAD,
            TOOL_MAT_NICKEL,
            TOOL_MAT_SILVER,
            TOOL_MAT_TIN,
            TOOL_MAT_TYRFING
    ));

    static {
        TOOL_MAT_COPPER.setRepairItem(new ItemStack(ItemRegister.INGOT_COPPER));
        TOOL_MAT_DAWNSTONE.setRepairItem(new ItemStack(ItemRegister.INGOT_DAWNSTONE));
        TOOL_MAT_LEAD.setRepairItem(new ItemStack(ItemRegister.INGOT_LEAD));
        TOOL_MAT_SILVER.setRepairItem(new ItemStack(ItemRegister.INGOT_SILVER));
        TOOL_MAT_TYRFING.setRepairItem(new ItemStack(ItemRegister.DUST_ASH));

        if (ConfigMaterial.ALUMINUM.mustLoad() && ConfigTool.METAL_TOOL.ALUMINUM.register) {
            TOOL_MAT_ALUMINUM.setRepairItem(new ItemStack(ItemRegister.INGOT_ALUMINUM));
        }
        if (ConfigMaterial.BRONZE.mustLoad() && ConfigTool.METAL_TOOL.BRONZE.register) {
            TOOL_MAT_BRONZE.setRepairItem(new ItemStack(ItemRegister.INGOT_BRONZE));
        }
        if (ConfigMaterial.ELECTRUM.mustLoad() && ConfigTool.METAL_TOOL.ELECTRUM.register) {
            TOOL_MAT_ELECTRUM.setRepairItem(new ItemStack(ItemRegister.INGOT_ELECTRUM));
        }
        if (ConfigMaterial.NICKEL.mustLoad() && ConfigTool.METAL_TOOL.NICKEL.register) {
            TOOL_MAT_NICKEL.setRepairItem(new ItemStack(ItemRegister.INGOT_NICKEL));
        }
        if (ConfigMaterial.TIN.mustLoad() && ConfigTool.METAL_TOOL.TIN.register) {
            TOOL_MAT_TIN.setRepairItem(new ItemStack(ItemRegister.INGOT_TIN));
        }
    }

    @Override
    public Collection<Item.ToolMaterial> load() {
        return REGISTRY;
    }

    @Override
    public void register() {

    }

    @Override
    public void add(Item.ToolMaterial registrable) {
        REGISTRY.add(registrable);
    }
}
