package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class EjectorCategory {
    @Config.RequiresMcRestart
    @Config.Name("Ember Capacity")
    @Config.Comment("The amount of embers that can be stored in an Ejector.")
    public double capacity = 2000.0;

    @Config.RequiresMcRestart
    @Config.Name("Transfer Rate")
    @Config.Comment("Transfer rate of the Ejector.")
    public double transfer_rate = 400.0;

    @Config.RequiresMcRestart
    @Config.Name("Pull Rate")
    @Config.Comment("Pull rate of the Ejector.")
    public double pull_rate = 100.0;
}
