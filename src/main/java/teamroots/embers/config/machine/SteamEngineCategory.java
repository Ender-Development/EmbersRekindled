package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class SteamEngineCategory {
    @Config.RequiresMcRestart
    @Config.Name("Fluid Threshold")
    @Config.Comment("How much water (in mb) is necessary to start burning solid fuel.")
    public int fluidThreshold = 10;

    @Config.RequiresMcRestart
    @Config.Name("Fluid Consumption")
    @Config.Comment("How much water (in mb) is consumed every tick while burning solid fuel.")
    public int fluidConsumption = 4;

    @Config.RequiresMcRestart
    @Config.Name("Fuel Efficiency")
    @Config.Comment("How efficient, time-wise, solid fuel is in the steam turbine. 1 = fuel lasts as long as it would in a furnace.")
    public double fuelEfficiency = 2.0;

    @Config.RequiresMcRestart
    @Config.Name("Fuel Power")
    @Config.Comment("How much mechanical power is generated while burning solid fuel.")
    public double fuelPower = 20.0;

    @Config.RequiresMcRestart
    @Config.Name("Max Power")
    @Config.Comment("How much mechanical power can be generated at max.")
    public double maximumPower = 50.0;

    @Config.RequiresMcRestart
    @Config.Name("Gas Consumption")
    @Config.Comment("How much gas (in mb), such as steam, is consumed every tick.")
    public int gasConsumption = 20;

    @Config.RequiresMcRestart
    @Config.Name("Capacity")
    @Config.Comment("How much fluid (in mb) fits into a Steam Engine.")
    public int capacity = 8000;
}
