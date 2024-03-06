package teamroots.embers.mixin.embersconstruct;

import com.peatral.embersconstruct.EmbersConstruct;
import com.peatral.embersconstruct.tileentity.TileEntityBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import teamroots.embers.util.Misc;

@Mixin(value = TileEntityBase.class, remap = false)
public abstract class TileEntityBaseMixin {
    @Shadow
    public abstract String getNameKey();

    /**
     * @author MasterEnderman
     * @reason Fix ghost items when breaking the block
     */
    @Overwrite
    public void breakBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        TileEntityBase tile = (TileEntityBase) (Object) this;
        tile.invalidate();
        if (tile.getNameKey().equals("container.embersconstruct.bloomery.top")) {
            return;
        }
        InventoryHelper.dropInventoryItems(world, pos, tile);
        // Misc.spawnInventoryInWorld(world, pos.getX(), pos.getY(), pos.getZ(), (IInventory) tile);
    }
}
