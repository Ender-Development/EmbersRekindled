package teamroots.embers.config;

import net.minecraftforge.common.config.Config;
import teamroots.embers.Embers;
import teamroots.embers.config.world.OreCategory;
import teamroots.embers.config.world.SmallRuinCategory;

@Config(modid = Embers.MODID, category = "world", name = Embers.CFG_FOLDER + "world")
@Config.LangKey("cfg.embers.world")
public class ConfigWorld {

    @Config.Name("Ore Generation")
    @Config.LangKey("cfg.embers.world.ore")
    @Config.Comment("Options about the ore generation")
    public static final OreCategory ORE = new OreCategory();

    @Config.Name("Small Ruin")
    @Config.LangKey("cfg.embers.world.small_ruin")
    @Config.Comment("Options about the small ruin generation")
    public static final SmallRuinCategory SMALL_RUIN = new SmallRuinCategory();

}
