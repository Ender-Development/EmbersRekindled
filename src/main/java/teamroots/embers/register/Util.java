package teamroots.embers.register;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import teamroots.embers.Embers;
import teamroots.embers.block.BlockSeedNew;
import teamroots.embers.config.tool.ToolCategory;
import teamroots.embers.tileentity.TileEntitySeedNew;

import java.util.function.BiFunction;
import java.util.stream.IntStream;

public final class Util {
    private Util(){}


    static BlockSeedNew createSimpleSeed(Material material, String name, ResourceLocation texture, BiFunction<TileEntitySeedNew, Integer, ItemStack> nuggetGenerator) {
        return new BlockSeedNew(material, name, true) {
            @Override
            public ResourceLocation getTexture(TileEntitySeedNew tile) {
                return texture;
            }

            @Override
            public ItemStack[] getNuggetDrops(TileEntitySeedNew tile, int n) {
                return IntStream.range(0, n).mapToObj(i -> nuggetGenerator.apply(tile, n)).toArray(ItemStack[]::new);
            }
        };
    }

    static Item.ToolMaterial createToolMaterial(String name, ToolCategory.Tool toolMaterial) {
        return EnumHelper.addToolMaterial(
                new ResourceLocation(Embers.MODID,name).toString(),
                toolMaterial.harvestLevel,
                toolMaterial.durability,
                toolMaterial.efficiency,
                toolMaterial.damage,
                toolMaterial.enchantability
        );
    }
}
