package teamroots.embers.mixin.mystgears;

import com.rcx.mystgears.item.ItemGear;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.config.ConfigMachine;
import teamroots.embers.register.ItemRegister;

@Mixin(value = ItemGear.class, remap = false)
public class ItemGearMixin {

    @Shadow public String name;

    @Redirect(method = "registerRecipe", at = @At(value = "FIELD", target = "Lteamroots/embers/ConfigManager;stampGearAmount:I"))
    private int stampGearAmount() {
        return ConfigMachine.STAMPER.stampGearAmount;
    }

    @Redirect(method = "registerRecipe", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_gear:Lnet/minecraft/item/Item;"))
    private Item stampGear() {
        return ItemRegister.STAMP_GEAR;
    }

}
