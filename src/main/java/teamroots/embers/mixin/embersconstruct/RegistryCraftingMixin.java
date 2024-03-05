package teamroots.embers.mixin.embersconstruct;

import com.peatral.embersconstruct.registry.RegistryCrafting;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.ItemRegister;

@Mixin(value = RegistryCrafting.class, remap = false)
public class RegistryCraftingMixin {
    @Redirect(method = "registerRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;tinker_hammer:Lnet/minecraft/item/Item;"))
    private static Item tinker_hammer() {
        return ItemRegister.TINKER_HAMMER;
    }

    @Redirect(method = "registerRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;block_caminite_brick:Lnet/minecraft/block/Block;"))
    private static Block block_caminite_brick() {
        return BlockRegister.BLOCK_CAMINITE_BRICK;
    }

    @Redirect(method = "registerRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;plate_caminite:Lnet/minecraft/item/Item;"))
    private static Item plate_caminite() {
        return ItemRegister.PLATE_CAMINITE;
    }
}
