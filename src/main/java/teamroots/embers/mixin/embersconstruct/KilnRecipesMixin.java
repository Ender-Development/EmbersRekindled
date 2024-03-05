package teamroots.embers.mixin.embersconstruct;

import com.peatral.embersconstruct.registry.KilnRecipes;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.ItemRegister;

@Mixin(value = KilnRecipes.class, remap = false)
public class KilnRecipesMixin {
    @Redirect(method = "main", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_gear:Lnet/minecraft/item/Item;"))
    private static Item stamp_gear() {
        return ItemRegister.STAMP_GEAR;
    }

    @Redirect(method = "main", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_plate:Lnet/minecraft/item/Item;"))
    private static Item stamp_plate() {
        return ItemRegister.STAMP_PLATE;
    }

    @Redirect(method = "main", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_bar:Lnet/minecraft/item/Item;"))
    private static Item stamp_bar() {
        return ItemRegister.STAMP_BAR;
    }

    @Redirect(method = "main", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_flat:Lnet/minecraft/item/Item;"))
    private static Item stamp_flat() {
        return ItemRegister.STAMP_FLAT;
    }

    @Redirect(method = "main", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_bar_raw:Lnet/minecraft/item/Item;"))
    private static Item stamp_bar_raw() {
        return ItemRegister.STAMP_BAR_RAW;
    }

    @Redirect(method = "main", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_plate_raw:Lnet/minecraft/item/Item;"))
    private static Item stamp_plate_raw() {
        return ItemRegister.STAMP_PLATE_RAW;
    }

    @Redirect(method = "main", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_gear_raw:Lnet/minecraft/item/Item;"))
    private static Item stamp_gear_raw() {
        return ItemRegister.STAMP_GEAR_RAW;
    }

    @Redirect(method = "main", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_flat_raw:Lnet/minecraft/item/Item;"))
    private static Item stamp_flat_raw() {
        return ItemRegister.STAMP_FLAT_RAW;
    }
}
