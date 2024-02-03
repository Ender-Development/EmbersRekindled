package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class CopperCellCategory {
    @Config.RequiresMcRestart
    @Config.Name("Ember Capacity")
    @Config.Comment("The amount of embers that can be stored in a copper cell.")
    public double capacity = 24000.0;
}
