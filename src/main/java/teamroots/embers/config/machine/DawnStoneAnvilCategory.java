package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class DawnStoneAnvilCategory {
    @Config.RequiresMcRestart
    @Config.Name("Hits per process")
    @Config.Comment("Number of hammer hits it takes to finish one process")
    public int maxHits = 40;
}
