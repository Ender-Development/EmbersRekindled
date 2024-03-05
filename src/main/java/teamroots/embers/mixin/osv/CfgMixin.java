package teamroots.embers.mixin.osv;

import com.personthecat.orestonevariants.config.Cfg;
import com.personthecat.orestonevariants.util.Lazy;
import net.minecraftforge.fml.common.Loader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import teamroots.embers.config.ConfigMaterial;

import java.util.Set;

import static com.personthecat.orestonevariants.config.Cfg.vanillaEnabled;

@Mixin(value = Cfg.class, remap = false)
public class CfgMixin {
    @Shadow @Final private static Lazy<Set<String>> PROPERTIES;

    /**
     * @author MasterEnderman
     * @reason Special case for Embers
     */
    @Overwrite
    public static boolean oreEnabled(String ore_name) {
        switch (ore_name) {
            case "embers_copper_ore":
                return ConfigMaterial.COPPER.mustLoad();
            case "embers_tin_ore":
                return ConfigMaterial.TIN.mustLoad();
            case "embers_lead_ore":
                return ConfigMaterial.LEAD.mustLoad();
            case "embers_silver_ore":
                return ConfigMaterial.SILVER.mustLoad();
            case "embers_nickel_ore":
                return ConfigMaterial.NICKEL.mustLoad();
            case "embers_aluminum_ore":
                return ConfigMaterial.ALUMINUM.mustLoad();
            default:
                return PROPERTIES.get().contains(ore_name);
        }
    }
}
