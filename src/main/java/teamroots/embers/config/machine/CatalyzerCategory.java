package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class CatalyzerCategory {
    @Config.RequiresMcRestart
    @Config.Name("Process Time")
    @Config.Comment("How many ticks an item lasts.")
    public int process_time = 400;

    @Config.RequiresMcRestart
    @Config.Name("Possible Fuels")
    @Config.Comment({
            "A list of all fuels for the catalyzer.",
            "Format: [modname|ore]:[registryName|oreDict];[emberMultiplier]",
            "Example:",
            "    ore:oreIron;3.1",
            "    minecraft:apple;1.4"
    })
    public String[] fuel = new String[]{
            "ore:dustRedstone;2.0",
            "ore:gunpowder;3.0",
            "ore:dustGlowstone;4.0"
    };
}
