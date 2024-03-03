package teamroots.embers.mixin.osv;

import com.personthecat.orestonevariants.config.Cfg;
import com.personthecat.orestonevariants.properties.OreProperties;
import com.personthecat.orestonevariants.properties.PropertyGroup;
import org.spongepowered.asm.mixin.*;
import teamroots.embers.config.ConfigMaterial;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Mixin(value = PropertyGroup.class, remap = false)
public class PropertyGroupMixin {
    /**
     * @author MasterEnderman
     * @reason Special case for Embers
     */
    @Overwrite
    public static Set<PropertyGroup> setupPropertyGroups() {
        Set<PropertyGroup> groups = new HashSet<>();
        Cfg.BlockRegistryCat.propertyGroups.forEach((name, entries) -> {
            if (Cfg.groupListed(name) && (!Cfg.modFamiliar(name) || Cfg.modEnabled(name))) {
                if (!name.equals("embers")) {
                    groups.add(new PropertyGroup(name, OreProperties.of(entries)));
                } else {
                    String[] result = Arrays.stream(entries).filter(PropertyGroupMixin::embers_Rekindled$checkConf).toArray(String[]::new);
                    groups.add(new PropertyGroup(name, OreProperties.of(result)));
                }
            }
        });
        return groups;
    }

    @Unique
    private static boolean embers_Rekindled$checkConf(String ore) {
        switch (ore) {
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
                return false;
        }
    }

}
