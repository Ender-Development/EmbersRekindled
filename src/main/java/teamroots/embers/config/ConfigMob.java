package teamroots.embers.config;

import net.minecraftforge.common.config.Config;
import teamroots.embers.Embers;
import teamroots.embers.config.mob.EmberGolemCategory;

@Config(modid = Embers.MODID, category = "mob", name = Embers.CFG_FOLDER + "mob")
@Config.LangKey("cfg.embers.mob")
public class ConfigMob {

    @Config.Name("Ember Golem")
    @Config.LangKey("cfg.embers.mob.ember_golem")
    @Config.Comment("Options about the Ember Golem")
    public static final EmberGolemCategory EMBER_GOLEM = new EmberGolemCategory();

}
