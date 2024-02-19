package teamroots.embers.mixin.mysticalgears;

import com.rcx.mystgears.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.config.ConfigMaterial;

@Mixin(value = ConfigHandler.class, remap = false)
public class ConfigHandlerMixin {

    @Redirect(method = "syncConfig", at = @At(value = "FIELD", target = "Lcom/rcx/mystgears/ConfigHandler;aluminium:Ljava/lang/Boolean;"))
    private static Boolean aluminium() {
        return ConfigMaterial.ALUMINUM.isNotOff() && ConfigHandler.aluminium;
    }

    @Redirect(method = "syncConfig", at = @At(value = "FIELD", target = "Lcom/rcx/mystgears/ConfigHandler;bronze:Ljava/lang/Boolean;"))
    private static Boolean bronze() {
        return ConfigMaterial.BRONZE.isNotOff() && ConfigHandler.bronze;
    }

    @Redirect(method = "syncConfig", at = @At(value = "FIELD", target = "Lcom/rcx/mystgears/ConfigHandler;electrum:Ljava/lang/Boolean;"))
    private static Boolean electrum() {
        return ConfigMaterial.ELECTRUM.isNotOff() && ConfigHandler.electrum;
    }

    @Redirect(method = "syncConfig", at = @At(value = "FIELD", target = "Lcom/rcx/mystgears/ConfigHandler;nickel:Ljava/lang/Boolean;"))
    private static Boolean nickel() {
        return ConfigMaterial.NICKEL.isNotOff() && ConfigHandler.nickel;
    }

    @Redirect(method = "syncConfig", at = @At(value = "FIELD", target = "Lcom/rcx/mystgears/ConfigHandler;tin:Ljava/lang/Boolean;"))
    private static Boolean tin() {
        return ConfigMaterial.TIN.isNotOff() && ConfigHandler.tin;
    }
}
