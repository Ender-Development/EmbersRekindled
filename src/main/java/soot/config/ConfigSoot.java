package soot.config;

import net.minecraftforge.common.config.Config;
import soot.Soot;
import teamroots.embers.Embers;


@Config(modid = Soot.MODID, category = "soot", name = Embers.CFG_ADDONS_FOLDER + "soot")
@Config.LangKey("cfg.soot.soot")
public class ConfigSoot {

    @Config.Name("Debug Mode")
    @Config.Comment("Enables full stack traces when something goes wrong")
    public static boolean DEBUG_MODE = false;

    @Config.Name("Trading Antimony")
    @Config.Comment("Allows trading signet of antimony with villagers instead of emeralds.")
    public static boolean TRADING_ANTIMONY = true;

    @Config.Name("Goelms Tyrfing Weakness")
    @Config.Comment("Golems take extra damage from the Tyrfing.")
    public static boolean GOLEMS_TYRFING_WEAK = true;

    @Config.Name("Golems Poison Immunity")
    @Config.Comment("Golems are immune to poison.")
    public static boolean GOLEMS_POISON_IMMUNE = true;

    @Config.Name("Ash First")
    @Config.Comment("Ash is removed before the aspect from pedestals.")
    public static boolean ASH_FIRST = true;

    @Config.Name("Enable Renaming Stamp")
    @Config.Comment("Enable Renaming Recipe for stamper.")
    public static boolean RENAME_STAMP = true;

    @Config.Name("Overrides")
    @Config.LangKey("cfg.soot.overrides")
    @Config.Comment("Overrides for various blocks and items.")
    public static final Overrides OVERRIDES = new Overrides();

    @Config.Name("Sulfur Ore")
    @Config.LangKey("cfg.soot.sulfur_ore")
    @Config.Comment("Sulfur Ore Generation")
    public static final Sulfur_Ore SULFUR_ORE = new Sulfur_Ore();

}
