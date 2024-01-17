package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class BoilerCategory {

    @Config.RequiresMcRestart
    @Config.Name("Ember Capacity")
    @Config.Comment("The maximum amount of ember the Presssure Refinerey can hold")
    public int ember_capacity = 32000;

    @Config.RequiresMcRestart
    @Config.Name("Base Multiplier")
    @Config.Comment("The base ember multiplier for the Pressure Refinery")
    public double base_multiplier = 1.5;

    @Config.RequiresMcRestart
    @Config.Name("Per Block Multiplier")
    @Config.Comment({
            "The multiplier per block occupied by lava or fire around the metal block",
            "placed beneath the Pressure Refinery."
    })
    public double per_block_multiplier = 0.375;

    @Config.RequiresMcRestart
    @Config.Name("Fluid Consumed")
    @Config.Comment("The amount of fluid consumed per process")
    public int fluid_consumed = 25;

    @Config.RequiresMcRestart
    @Config.Name("Process Time")
    @Config.Comment("The amount of ticks it takes to process an item")
    public int process_time = 20;

    @Config.RequiresMcRestart
    @Config.Name("Tank Capacity")
    @Config.Comment("The amount of water the Pressure Refinery can hold")
    public int capacity = 8000;
}
