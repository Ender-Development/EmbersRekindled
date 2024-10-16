package teamroots.embers.mixin.survivaltools;

import com.invadermonky.survivaltools.items.ItemMantleCloak;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.ItemRegister;

@Mixin(value = ItemMantleCloak.class, remap = false)
public class ItemMantleCloakMixin {
    @Redirect(method="registerRecipe", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ashen_cloth:Lnet/minecraft/item/Item;"))
    private Item replaceAshenCloth() {
        return ItemRegister.ASHEN_CLOTH;
    }

    @Redirect(method="registerRecipe", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_cluster:Lnet/minecraft/item/Item;"))
    private Item replaceEmberCluster() {
        return ItemRegister.EMBER_CLUSTER;
    }
}
