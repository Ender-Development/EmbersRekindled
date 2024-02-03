package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class EmberConduitCategory {
    @Config.RequiresMcRestart
    @Config.Name("All machines connect to Ember Conduits")
    @Config.Comment("Whether ember conduits can attach to any ember consumer/producer")
    public boolean all_accept_volatile = false;

    @Config.RequiresMcRestart
    @Config.Name("Initial Push")
    @Config.Comment("The amount of ember to push into the conduit when it is first activated")
    public double initial_push = 10.0;

    @Config.RequiresMcRestart
    @Config.Name("Incremental Push")
    @Config.Comment("The amount added to the initial push each tick after it is activated")
    public double incremental_push = 10.0;
}
