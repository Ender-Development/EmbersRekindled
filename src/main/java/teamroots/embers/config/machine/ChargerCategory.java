package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class ChargerCategory {
    @Config.RequiresMcRestart
    @Config.Name("Max Transfer Rate")
    @Config.Comment("How much ember is transferred between item and charger per tick?")
    public double maxTransfer = 10.0;

    @Config.RequiresMcRestart
    @Config.Name("Max Capacity")
    @Config.Comment("How much ember can the charger hold?")
    public double capacity = 24000.0;
}
