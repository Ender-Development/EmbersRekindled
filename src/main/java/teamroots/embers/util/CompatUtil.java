package teamroots.embers.util;

import net.minecraftforge.fml.common.Loader;
import teamroots.embers.config.ConfigMain;

public class CompatUtil {
    public static boolean isBaublesIntegrationEnabled() {
        return ConfigMain.COMPAT.enableBaublesIntegration && Loader.isModLoaded("baubles") || ConfigMain.COMPAT.forceBaublesIntegration;
    }

    public static boolean isMysticalMechanicsIntegrationEnabled() {
        return ConfigMain.COMPAT.enableMysticalMechanicsIntegration && Loader.isModLoaded("mysticalmechanics") || ConfigMain.COMPAT.forceMysticalMechanicsIntegration;
    }
}
