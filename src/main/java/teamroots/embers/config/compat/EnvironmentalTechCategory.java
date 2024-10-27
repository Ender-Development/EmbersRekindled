package teamroots.embers.config.compat;

import net.minecraftforge.common.config.Config;

public class EnvironmentalTechCategory {
    @Config.RequiresMcRestart
    @Config.Name("Ember Modifier Energy Increase")
    @Config.Comment({
            "How much more energy the ember modifier will consume.",
            "The value is treated as a percentage, so 20 increases the energy consumption by 20%."
    })
    @Config.RangeInt(min = 0)
    public int ember_energy_increase = 20;

    @Config.RequiresMcRestart
    @Config.Name("Luck Modifier Energy Increase")
    @Config.Comment({
            "How much more energy the luck modifier will consume.",
            "The value is treated as a percentage, so 90 increases the energy consumption by 90%.",
            "NOTE: This is an additional config option, because Environmental Tech",
            "doesn't provide an option for this by default."
    })
    @Config.RangeInt(min = 0)
    public int luck_energy_increase = 90;

    @Config.RequiresMcRestart
    @Config.Name("Speed Modifier Energy Increase")
    @Config.Comment({
            "How much more energy the speed modifier will consume.",
            "The value is treated as a percentage, so 100 increases the energy consumption by 100%.",
            "NOTE: This is an additional config option, because Environmental Tech",
            "doesn't provide an option for this by default."
    })
    @Config.RangeInt(min = 0)
    public int speed_energy_increase = 100;

    @Config.RequiresMcRestart
    @Config.Name("Accuracy Modifier Energy Increase")
    @Config.Comment({
            "How much more energy the accuracy modifier will consume.",
            "The value is treated as a percentage, so 50 increases the energy consumption by 50%.",
            "NOTE: This is an additional config option, because Environmental Tech",
            "doesn't provide an option for this by default."
    })
    @Config.RangeInt(min = 0)
    public int accuracy_energy_increase = 50;
}
