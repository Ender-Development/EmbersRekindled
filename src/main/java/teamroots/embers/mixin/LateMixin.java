package teamroots.embers.mixin;

import net.minecraftforge.fml.common.Loader;
import org.spongepowered.include.com.google.common.collect.ImmutableList;
import teamroots.embers.Embers;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;
import java.util.stream.Collectors;

public class LateMixin implements ILateMixinLoader {
    public static final List<String> modMixins = ImmutableList.of(
            "aetherworks",
            "jaopca",
            "mysticalgears"
    );

    @Override
    public List<String> getMixinConfigs() {
        return modMixins.stream().filter(Loader::isModLoaded).map(mod -> "mixin."+ Embers.MODID +"." + mod + ".json").collect(Collectors.toList());
    }
}
