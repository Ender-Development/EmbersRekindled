package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class MixerCategory {

    @Config.RequiresMcRestart
    @Config.Name("Ember Capacity")
    @Config.Comment("The amount of ember the Mixer Centrifuge can hold")
	public double ember_capacity = 8000.0;

    @Config.RequiresMcRestart
    @Config.Name("Ember Cost Multiplier")
    @Config.Comment("Multiplier for the ember cost of the Mixer Centrifuge")
    public double ember_cost = 2.0;

    @Config.RequiresMcRestart
    @Config.Name("Output Tank Capacity")
    @Config.Comment("The amount of fluid the output tank can hold")
    public int output_capacity = 8000;

    @Config.RequiresMcRestart
    @Config.Name("Input Tank Capacity")
    @Config.Comment("The amount of fluid the input tanks can hold")
    public int input_capacity = 8000;

}
