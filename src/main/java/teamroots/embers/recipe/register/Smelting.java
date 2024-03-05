package teamroots.embers.recipe.register;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamroots.embers.config.ConfigMaterial;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.ItemRegister;

public class Smelting implements IRegister {
    public static final Smelting INSTANCE = new Smelting();

    private Smelting() {
    }

    @Override
    public void execute() {
        if (ConfigMaterial.COPPER.mustLoad()) {
            GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_COPPER), new ItemStack(ItemRegister.INGOT_COPPER), 0.65f);
        }
        if (ConfigMaterial.SILVER.mustLoad()) {
            GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_SILVER), new ItemStack(ItemRegister.INGOT_SILVER), 0.35f);
        }
        if (ConfigMaterial.LEAD.mustLoad()) {
            GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_LEAD), new ItemStack(ItemRegister.INGOT_LEAD), 0.35f);
        }
        if (ConfigMaterial.ALUMINUM.mustLoad()) {
            GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_ALUMINUM), new ItemStack(ItemRegister.INGOT_ALUMINUM), 0.55f);
        }
        if (ConfigMaterial.TIN.mustLoad()) {
            GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_TIN), new ItemStack(ItemRegister.INGOT_TIN), 0.55f);
        }
        if (ConfigMaterial.NICKEL.mustLoad()) {
            GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_NICKEL), new ItemStack(ItemRegister.INGOT_NICKEL), 0.55f);
        }
        GameRegistry.addSmelting(new ItemStack(BlockRegister.ORE_QUARTZ), new ItemStack(Items.QUARTZ), 0.35f);

        GameRegistry.addSmelting(new ItemStack(ItemRegister.BLEND_CAMINITE), new ItemStack(ItemRegister.BRICK_CAMINITE), 0.35f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.PLATE_CAMINITE_RAW), new ItemStack(ItemRegister.PLATE_CAMINITE), 0.35f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.STAMP_BAR_RAW), new ItemStack(ItemRegister.STAMP_BAR), 0.35f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.STAMP_PLATE_RAW), new ItemStack(ItemRegister.STAMP_PLATE), 0.35f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.STAMP_FLAT_RAW), new ItemStack(ItemRegister.STAMP_FLAT), 0.35f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.STAMP_GEAR_RAW), new ItemStack(ItemRegister.STAMP_GEAR), 0.35f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.STAMP_ROD_RAW), new ItemStack(ItemRegister.STAMP_ROD), 0.35f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.STAMP_ROUND_RAW), new ItemStack(ItemRegister.STAMP_ROUND), 0.35f);

    }
}
