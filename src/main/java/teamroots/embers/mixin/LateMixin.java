package teamroots.embers.mixin;

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
            "jaopca",
            "moreclimate",
            "mystgears",
            "osv",
            "planarartifice",
            "polymancy",
            "spartanweaponryarcana",
            "survivaltools",
            "thaumicperiphery"
    );

    @Override
    public List<String> getMixinConfigs() {
        return modMixins.stream().filter(this::checkMod).map(mod -> "mixin."+ Embers.MODID +"." + mod + ".json").collect(Collectors.toList());
    }

    private boolean checkMod(String modid) {
        if (modid.equals("survivaltools")) {
            return Loader.isModLoaded(modid) && SurvivalTools.VERSION.equals("1.12.2-1.0.0");
        }
        return Loader.isModLoaded(modid);
    }
}
