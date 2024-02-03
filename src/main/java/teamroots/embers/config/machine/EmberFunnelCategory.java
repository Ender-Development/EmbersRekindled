package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class EmberFunnelCategory {
    @Config.RequiresMcRestart
    @Config.Name("Ember Capacity")
    @Config.Comment("The amount of embers that can be stored in an Ember Funnel.")
    public double capacity = 2000.0;

    @Config.RequiresMcRestart
    @Config.Name("Transfer Speed")
    @Config.Comment("Transfer speed of the Ember Funnel.")
    public int transfer_speed = 100;
}
