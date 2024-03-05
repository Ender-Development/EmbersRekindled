package teamroots.embers.mixin.embersconstruct;

import com.peatral.embersconstruct.registry.RegistryResearch;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.ItemRegister;

@Mixin(value = RegistryResearch.class, remap = false)
public class RegistryResearchMixin {
    @Redirect(method = "main", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_flat_raw:Lnet/minecraft/item/Item;"))
    private static Item stamp_flat_raw() {
        return ItemRegister.STAMP_FLAT_RAW;
    }

    @Redirect(method = "main", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_gear:Lnet/minecraft/item/Item;"))
    private static Item stamp_gear() {
        return ItemRegister.STAMP_GEAR;
    }
}
