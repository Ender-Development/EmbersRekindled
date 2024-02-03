package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class EmitterCategory {
    @Config.RequiresMcRestart
    @Config.Name("Ember Capacity")
    @Config.Comment("The amount of embers that can be stored in an Emitter.")
    public double capacity = 200.0;

    @Config.RequiresMcRestart
    @Config.Name("Transfer Rate")
    @Config.Comment("Transfer rate of the Emitter.")
    public double transfer_rate = 40.0;

    @Config.RequiresMcRestart
    @Config.Name("Pull Rate")
    @Config.Comment("Pull rate of the Emitter.")
    public double pull_rate = 10.0;
}
