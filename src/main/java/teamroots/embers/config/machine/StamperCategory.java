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
    @Config.Name("Stamper Capacity")
    @Config.Comment("How much fluid (in mb) fits into the Stamp Base?")
    public int capacity = 1500;
}
