package teamroots.embers.mixin.planarartifice;

import leppa.planarartifice.compat.embers.EmbersHandler;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.ItemRegister;

@Mixin(value = EmbersHandler.class, remap = false)
public class EmbersHandlerMixin {
    @Redirect(method = "registerAspects", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;codex:Lnet/minecraft/item/Item;"))
    private Item codex() {
        return ItemRegister.CODEX;
    }

    @Redirect(method = "registerAspects", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;golems_eye:Lnet/minecraft/item/Item;"))
    private Item golems_eye() {
        return ItemRegister.GOLEMS_EYE;
    }
}
