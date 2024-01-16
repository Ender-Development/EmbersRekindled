package teamroots.embers.config;

import net.minecraftforge.common.config.Config;
import teamroots.embers.Embers;

@Config(modid = Embers.MODID, category = "compat", name = Embers.CFG_FOLDER + "compat")
@Config.LangKey("cfg.embers.compat")
public class ConfigCompat {
    @Config.Name("Enable JEI Cheats")
    @Config.Comment("If true, JEI will show the exact amount of Ash needed in an Alchemy recipe.")
    public static boolean enableJeiCheat = false;

    @Config.RequiresMcRestart
    @Config.Name("Enable Baubles Integration")
    @Config.Comment("If true, Embers will register items, blocks and recipes providing Baubles integration.")
    public static boolean enableBaublesIntegration = true;

    @Config.RequiresMcRestart
    @Config.Name("Enable Mystical Mechanics Integration")
    @Config.Comment("If true, Embers will register items, blocks and recipes providing Mystical Mechanics integration.")
    public static boolean enableMysticalMechanicsIntegration = true;
}
