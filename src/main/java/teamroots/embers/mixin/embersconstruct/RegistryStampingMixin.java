package teamroots.embers.mixin.embersconstruct;

import com.peatral.embersconstruct.registry.RegistryStamping;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.ItemRegister;

@Mixin(value = RegistryStamping.class, remap = false)
public class RegistryStampingMixin {
    @Redirect(method = "registerOreDictRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_flat:Lnet/minecraft/item/Item;"))
    private static Item stamp_flat() {
        return ItemRegister.STAMP_FLAT;
    }

    @Redirect(method = "registerOreDictRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_bar:Lnet/minecraft/item/Item;"))
    private static Item stamp_bar() {
        return ItemRegister.STAMP_BAR;
    }

    @Redirect(method = "registerOreDictRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_plate:Lnet/minecraft/item/Item;"))
    private static Item stamp_plate() {
        return ItemRegister.STAMP_PLATE;
    }

    @Redirect(method = "registerOreDictRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_gear:Lnet/minecraft/item/Item;"))
    private static Item stamp_gear() {
        return ItemRegister.STAMP_GEAR;
    }

    @Redirect(method = "registerTinkerRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_flat:Lnet/minecraft/item/Item;"))
    private static Item stamp_flat2() {
        return ItemRegister.STAMP_FLAT;
    }
}
