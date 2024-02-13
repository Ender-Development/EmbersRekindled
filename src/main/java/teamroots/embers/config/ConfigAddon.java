package teamroots.embers.config;

import net.minecraftforge.common.config.Config;
import teamroots.embers.Embers;

@Config(modid = Embers.MODID, category = "addon", name = Embers.CFG_FOLDER + "addon")
@Config.LangKey("cfg.embers.addon")
public class ConfigAddon {

    @Config.RequiresMcRestart
    @Config.Name("Enable Soot Addon")
    @Config.Comment("If true, Soot will be loaded.")
    public static boolean enableSoot = true;
}
