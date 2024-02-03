package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class TankCategory {
    @Config.RequiresMcRestart
    @Config.Name("Capacity")
    @Config.Comment("How much fluid (in mb) fits into the Fluid Vessel?")
    public int capacity = 16000;
}
