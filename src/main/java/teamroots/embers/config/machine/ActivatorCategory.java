package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class ActivatorCategory {

    @Config.RequiresMcRestart
    @Config.Name("Ember Capacity")
    @Config.Comment("How much ember the activator can store")
    public int ember_capacity = 16000;

    @Config.RequiresMcRestart
    @Config.Name("Process Time")
    @Config.Comment("How many ticks the activator takes to process an item")
    public int process_time = 40;
}
