package teamroots.embers.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraftforge.fml.common.Loader;
import teamroots.embers.Embers;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;
import java.util.stream.Collectors;

public class LateMixin implements ILateMixinLoader {
    public static final List<String> modMixins = ImmutableList.of(
            "aetheriumashenarmor",
            "aetherworks",
            "embersconstruct",
            "embersified",
            "embersifiedextended",
            "jaopca",
            "moreclimate",
            "mystgears",
            "osv",
            "planarartifice",
            "polymancy",
            "thaumicperiphery"
    );

    @Override
    public List<String> getMixinConfigs() {
        return modMixins.stream().filter(Loader::isModLoaded).map(mod -> "mixin."+ Embers.MODID +"." + mod + ".json").collect(Collectors.toList());
    }
}
