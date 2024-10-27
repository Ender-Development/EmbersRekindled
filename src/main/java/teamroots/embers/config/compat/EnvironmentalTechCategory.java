package teamroots.embers.config.compat;

import net.minecraftforge.common.config.Config;

public class EnvironmentalTechCategory {
    @Config.RequiresMcRestart
    @Config.Name("Energy Increase")
    @Config.RangeInt(min = 0)
    @Config.Comment({
            "How much more energy the modifier will consume",
            "The value is entered as a percentage, so 20 is 20% more energy"
    })
    public int energy_increase = 20;
}
