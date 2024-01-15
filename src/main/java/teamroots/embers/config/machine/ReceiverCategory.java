package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class ReceiverCategory {
    @Config.RequiresMcRestart
    @Config.Name("Ember Capacity")
    @Config.Comment("The amount of embers that can be stored in an Receiver.")
    public double capacity = 2000.0;

    @Config.RequiresMcRestart
    @Config.Name("Transfer Rate")
    @Config.Comment("Transfer rate of the Receiver.")
    public double transfer_rate = 10.0;
}
