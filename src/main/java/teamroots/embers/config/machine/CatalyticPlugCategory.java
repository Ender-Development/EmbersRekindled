package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class CatalyticPlugCategory {
    @Config.RequiresMcRestart
    @Config.Name("Capacity")
    @Config.Comment("How much fluid (in mb) fits into the Catalytic Plug?")
    public int capacity = 4000;
}
