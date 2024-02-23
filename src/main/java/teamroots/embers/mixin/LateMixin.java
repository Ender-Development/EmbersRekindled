package teamroots.embers.mixin;

import org.spongepowered.include.com.google.common.collect.ImmutableList;
import teamroots.embers.Embers;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;
import java.util.stream.Collectors;

public class LateMixin implements ILateMixinLoader {
    public static final List<String> modMixins = ImmutableList.of(
            "jaopca",
            "mysticalgears"
    );

    @Override
    public List<String> getMixinConfigs() {
        return modMixins.stream().map(mod -> "mixin."+ Embers.MODID +"." + mod + ".json").collect(Collectors.toList());
    }
}
