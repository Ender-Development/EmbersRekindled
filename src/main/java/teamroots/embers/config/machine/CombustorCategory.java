package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class CombustorCategory {
    @Config.RequiresMcRestart
    @Config.Name("Process Time")
    @Config.Comment("How many ticks an item lasts.")
    public int process_time = 400;

    @Config.RequiresMcRestart
    @Config.Name("Possible Fuels")
    @Config.Comment({
            "A list of all fuels for the combustor.",
            "Format: [modname|ore]:[registryName|oreDict];[emberMultiplier]",
            "Example:",
            "    ore:oreIron;3.1",
            "    minecraft:apple;1.4"
    })
    public String[] fuel = new String[]{
            "minecraft:coal;2.0",
            "ore:ingotBrickNether;3.0",
            "minecraft:blaze_powder;4.0"
    };
}
