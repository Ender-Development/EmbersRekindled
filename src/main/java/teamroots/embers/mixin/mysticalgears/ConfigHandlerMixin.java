package teamroots.embers.mixin.mysticalgears;

import com.rcx.mystgears.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.config.ConfigMaterial;

@Mixin(value = ConfigHandler.class, remap = false)
public class ConfigHandlerMixin {

    @Redirect(method = "syncConfig", at = @At(value = "FIELD", target = "Lteamroots/embers/ConfigManager;enableAluminum:Z"))
    private static boolean aluminium() {
        return ConfigMaterial.ALUMINUM.isNotOff();
    }

    @Redirect(method = "syncConfig", at = @At(value = "FIELD", target = "Lteamroots/embers/ConfigManager;enableBronze:Z"))
    private static boolean bronze() {
        return ConfigMaterial.BRONZE.isNotOff();
    }

    @Redirect(method = "syncConfig", at = @At(value = "FIELD", target = "Lteamroots/embers/ConfigManager;enableElectrum:Z"))
    private static boolean electrum() {
        return ConfigMaterial.ELECTRUM.isNotOff() && ConfigHandler.electrum;
    }

    @Redirect(method = "syncConfig", at = @At(value = "FIELD", target = "Lteamroots/embers/ConfigManager;enableNickel:Z"))
    private static boolean nickel() {
        return ConfigMaterial.NICKEL.isNotOff() && ConfigHandler.nickel;
    }

    @Redirect(method = "syncConfig", at = @At(value = "FIELD", target = "Lteamroots/embers/ConfigManager;enableTin:Z"))
    private static boolean tin() {
        return ConfigMaterial.TIN.isNotOff() && ConfigHandler.tin;
    }
}
