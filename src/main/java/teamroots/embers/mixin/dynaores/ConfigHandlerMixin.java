package teamroots.embers.mixin.dynaores;

import net.smileycorp.dynaores.common.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Locale;

@Mixin(value = ConfigHandler.class, remap = false)
public class ConfigHandlerMixin {
    // This is a mixin to prevent DynaOres to break Aetherworks progression
    @Inject(method = "isBlacklisted", at = @At("HEAD"), cancellable = true)
    private static void isBlacklisted(String name, CallbackInfoReturnable<Boolean> cir) {
        if ("aether".equals(name.toLowerCase(Locale.US))) {
            cir.setReturnValue(true);
        };
    }
}
