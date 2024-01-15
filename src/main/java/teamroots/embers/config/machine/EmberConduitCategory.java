package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class EmberConduitCategory {
    @Config.RequiresMcRestart
    @Config.Name("All machines connect to Ember Conduits")
    @Config.Comment("Whether ember conduits can attach to any ember consumer/producer")
    public boolean allAcceptVolatile = false;
}
