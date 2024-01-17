package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class HeatCoilCategory {

    @Config.RequiresMcRestart
    @Config.Name("Ember Cost Multiplier")
    @Config.Comment("Multiplier for ember cost of heating the Hearth Coil")
    public double ember_cost = 1.0;

    @Config.RequiresMcRestart
    @Config.Name("Heat Speed Multiplier")
    @Config.Comment("Multiplier for heating speed of the Hearth Coil")
	public double heating_speed = 1.0;

    @Config.RequiresMcRestart
    @Config.Name("Cooling Speed Multiplier")
    @Config.Comment("Multiplier for cooling speed of the Hearth Coil")
	public double cooling_speed = 1.0;

    @Config.RequiresMcRestart
    @Config.Name("Max Heat")
    @Config.Comment("Maximum heat of the Hearth Coil")
	public double max_heat = 280;

    @Config.RequiresMcRestart
    @Config.Name("Min Cook Time")
    @Config.Comment("Minimum cook time in ticks of the Hearth Coil")
	public int min_cook_time = 20;

    @Config.RequiresMcRestart
    @Config.Name("Max Cook Time")
    @Config.Comment("Maximum cook time in ticks of the Hearth Coil")
	public int max_cook_time = 300;
}
