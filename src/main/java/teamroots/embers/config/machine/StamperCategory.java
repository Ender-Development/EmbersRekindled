package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class StamperCategory {
    @Config.RequiresMcRestart
    @Config.Name("Ingot to Plate ratio")
    @Config.Comment("How many ingots are required to make one plate in the stamper?")
    public int stampPlateAmount = 1;

    @Config.RequiresMcRestart
    @Config.Name("Ingot to Aspectus ratio")
    @Config.Comment("How many ingots are required to make one aspectus in the stamper?")
    public int stampAspectusAmount = 1;

    @Config.RequiresMcRestart
    @Config.Name("Ingot to Gear ratio")
    @Config.Comment("How many ingots are required to make one gear in the stamper?")
    public int stampGearAmount = 2;

    @Config.RequiresMcRestart
    @Config.Name("Ingot to Rod ratio")
    @Config.Comment("How many rods can be made from one ingot in the stamper?")
    public int stampRodAmount = 2;

    @Config.RequiresMcRestart
    @Config.Name("Fluid amount per Grinding Ball (EnderIO)")
    @Config.Comment("How much fluid (in mb) is required to make a grinding ball in the stamper?")
    public int stampRoundAmount = 30;

    @Config.RequiresMcRestart
    @Config.Name("Stamper Capacity")
    @Config.Comment("How much fluid (in mb) fits into the Stamp Base?")
    public int capacity = 1500;

    @Config.RequiresMcRestart
    @Config.Name("Ember Cost")
    @Config.Comment("How much ember is required to stamp an item?")
    public double ember_cost = 80.0;

    @Config.RequiresMcRestart
    @Config.Name("Stamp Time")
    @Config.Comment("How many ticks does it take to stamp an item?")
    public int stamp_time = 70;

    @Config.RequiresMcRestart
    @Config.Name("Retract Time")
    @Config.Comment("How many ticks does it take to retract the stamp?")
    public int retract_time = 10;
}
