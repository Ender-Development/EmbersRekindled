package teamroots.embers.mixin.aetherworks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.ItemRegister;
import v0id.aw.common.handler.CommonHandler;

@Mixin(value = CommonHandler.class, remap = false)
public class CommonHandlerMixin {
    @Redirect(method = "onPlayerClickedBlock", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;block_dawnstone:Lnet/minecraft/block/Block;"))
    private Block block_dawnstone() {
        return BlockRegister.BLOCK_DAWNSTONE;
    }

    @Redirect(method = "onPlayerClickedBlock", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;tinker_hammer:Lnet/minecraft/item/Item;"))
    private Item tinker_hammer() {
        return ItemRegister.TINKER_HAMMER;
    }
}
