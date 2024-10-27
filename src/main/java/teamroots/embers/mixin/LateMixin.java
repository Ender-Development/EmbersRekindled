package teamroots.embers.mixin;

import com.Lenvill.EmberforgedMain;
import com.google.common.collect.ImmutableList;
import com.invadermonky.survivaltools.SurvivalTools;
import net.minecraftforge.fml.common.Loader;
import teamroots.embers.Embers;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;
import java.util.stream.Collectors;

public class LateMixin implements ILateMixinLoader {
    public static final List<String> modMixins = ImmutableList.of(
            "aetheriumashenarmor",
            "appliedenergistics2",
            "appliedintegrations",
            "dynaores",
            "emberforged",
            "embersconstruct",
            "embersified",
            "embersifiedextended",
            "environmentaltech",
            "jaopca",
            "moreclimate",
            "mystgears",
            "osv",
            "planarartifice",
            "polymancy",
            "spartanweaponryarcana",
            "survivaltools",
            "thaumicperiphery",
            "valkyrielib"
    );

    @Override
    public List<String> getMixinConfigs() {
        return modMixins.stream().filter(this::checkMod).map(mod -> "mixin."+ Embers.MODID +"." + mod + ".json").collect(Collectors.toList());
    }

    private boolean checkMod(String modid) {
        boolean check = false;
        switch (modid) {
            case "survivaltools":
                check = Loader.isModLoaded(modid) && SurvivalTools.VERSION.equals("1.12.2-1.0.0");
                break;
            case "emberforged":
                check = Loader.isModLoaded(modid) && !EmberforgedMain.VERSION.equals("1.12.2-2.0.0");
                break;
            default:
                check = Loader.isModLoaded(modid);
                break;
        }
        Embers.LOG.debug("Checking for mod {}. Load Mixins: {}", modid, check);
        return check;
    }
}
