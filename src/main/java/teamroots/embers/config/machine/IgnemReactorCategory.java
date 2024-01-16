package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class IgnemReactorCategory {
    @Config.RequiresMcRestart
    @Config.Name("Ember Capacity")
    @Config.Comment("The amount of embers that can be stored in an ignem reactor.")
    public double capacity = 128000.0;

    @Config.RequiresMcRestart
    @Config.Name("Process Time")
    @Config.Comment("The amount of ticks it takes to complete one ignition cycle.")
    public int process_time = 20;

    @Config.RequiresMcRestart
    @Config.Name("Base Multiplier")
    @Config.Comment("Base multiplier for ember production.")
    public double base_multiplier = 1.0;
}
