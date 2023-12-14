package teamroots.embers.compat;

import net.minecraftforge.fml.common.Loader;
import teamroots.embers.config.ConfigMain;

public final class Util {
    private Util(){}

    public static boolean isBaublesIntegrationEnabled() {
        return ConfigMain.COMPAT.enableBaublesIntegration && Loader.isModLoaded("baubles");
    }

    public static boolean isMysticalMechanicsIntegrationEnabled() {
        return ConfigMain.COMPAT.enableMysticalMechanicsIntegration && Loader.isModLoaded("mysticalmechanics");
    }
}
