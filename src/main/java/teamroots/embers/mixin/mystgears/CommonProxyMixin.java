package teamroots.embers.mixin.mystgears;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.config.ConfigMachine;
import teamroots.embers.register.ItemRegister;

@Mixin(targets = {
        "com/rcx/mystgears/proxy/CommonProxy$3",
        "com/rcx/mystgears/proxy/CommonProxy$4",
        "com/rcx/mystgears/proxy/CommonProxy$6",
        "com/rcx/mystgears/proxy/CommonProxy$8",
        "com/rcx/mystgears/proxy/CommonProxy$9",
        "com/rcx/mystgears/proxy/CommonProxy$15"

}, remap = false)
public class CommonProxyMixin {
    @Redirect(method = "registerRecipe", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_gear:Lnet/minecraft/item/Item;"))
    private Item redirectStampGear() {
        return ItemRegister.STAMP_GEAR;
    }

    @Redirect(method = "registerRecipe", at = @At(value = "FIELD", target = "Lteamroots/embers/ConfigManager;stampGearAmount:I"))
    private int redirectStampGearAmount() {
        return ConfigMachine.STAMPER.stampGearAmount;
    }

}
