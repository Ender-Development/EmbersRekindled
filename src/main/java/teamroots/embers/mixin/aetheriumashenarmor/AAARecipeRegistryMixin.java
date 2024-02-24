package teamroots.embers.mixin.aetheriumashenarmor;

import com.aetheriumashenarmor.recipe.AAARecipeRegistry;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.ItemRegister;

@Mixin(value = AAARecipeRegistry.class, remap = false)
public class AAARecipeRegistryMixin {
    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_bar:Lnet/minecraft/item/Item;"))
    private static Item stamp_bar() {
        return ItemRegister.STAMP_BAR;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ashen_cloak_boots:Lnet/minecraft/item/Item;"))
    private static Item ashen_cloak_boots() {
        return ItemRegister.ASHEN_CLOAK_BOOTS;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ashen_cloak_chest:Lnet/minecraft/item/Item;"))
    private static Item ashen_cloak_chest() {
        return ItemRegister.ASHEN_CLOAK_CHEST;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ashen_cloak_head:Lnet/minecraft/item/Item;"))
    private static Item ashen_cloak_head() {
        return ItemRegister.ASHEN_CLOAK_HEAD;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ashen_cloak_legs:Lnet/minecraft/item/Item;"))
    private static Item ashen_cloak_legs() {
        return ItemRegister.ASHEN_CLOAK_LEGS;
    }
}
