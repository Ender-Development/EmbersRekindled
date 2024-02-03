package teamroots.embers.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamroots.embers.Embers;

@Mod.EventBusSubscriber(modid = Embers.MODID)
public class ConfigMain {

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Embers.MODID)) {
            ConfigManager.sync(Embers.MODID, Config.Type.INSTANCE);
        }
    }
}

