package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class GeoSeparatorCategory {
    @Config.RequiresMcRestart
    @Config.Name("Separator Capacity")
    @Config.Comment("How much fluid (in mb) fits into a Geologic Separator?")
    public int capacity = 1000;
}
