package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class ReservoirCategory {
    @Config.RequiresMcRestart
    @Config.Name("Capacity")
    @Config.Comment("How much fluid (in mb) fits into each Caminite Ring on a Reservoir?")
    public int capacity = 40000;
}
