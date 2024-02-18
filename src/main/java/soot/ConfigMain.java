package soot;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import soot.config.ConfigSoot;
import teamroots.embers.Embers;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Mod.EventBusSubscriber(modid = Soot.MODID)
public class ConfigMain {

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Embers.MODID)) {
            ConfigManager.sync(Embers.MODID, Config.Type.INSTANCE);
        }
    }

    public static boolean isSulfurEnabled(int dimension) {
        return IntStream.of(ConfigSoot.SULFUR_ORE.sulfurBlacklist).boxed().collect(Collectors.toList()).contains(dimension) == ConfigSoot.SULFUR_ORE.sulfurBlacklistIsWhitelist || IntStream.of(ConfigSoot.SULFUR_ORE.sulfurBlacklist).boxed().collect(Collectors.toList()).contains(dimension);
    }
}
