package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class ReactionChamberCategory {
    @Config.RequiresMcRestart
    @Config.Name("Capacity")
    @Config.Comment("How much fluid (in mb) fits into a reaction chamber?")
    public int capacity = 16000;
}
