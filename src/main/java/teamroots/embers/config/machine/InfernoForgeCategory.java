package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class InfernoForgeCategory {
    @Config.RequiresMcRestart
    @Config.Name("Process Time")
    @Config.Comment("The time in ticks it takes to process one item.")
    public int processTime = 200;
    @Config.RequiresMcRestart
    @Config.Name("Ember Cost")
    @Config.Comment("The ember cost per tick.")
    public double emberCost = 16.0;
    @Config.RequiresMcRestart
    @Config.Name("Max Level")
    @Config.Comment("The maximum augment level that can be reforged to.")
    public int maxLevel = 5;
    @Config.RequiresMcRestart
    @Config.Name("Max Crystal Value")
    @Config.Comment("The maximum amount of ember items that can be placed in the forge, in ember energy.")
    public double maxCrystalValue = 115200.0;
    @Config.RequiresMcRestart
    @Config.Name("Chance Midpoint")
    @Config.Comment("At exactly this amount of ember items, the chance to successfully reforge is exactly 50%.")
    public double chanceMidpoint = 14400.0;

    @Config.RequiresMcRestart
    @Config.Name("Forge Capacity")
    @Config.Comment("How much ember fits into the Inferno Forge?")
    public int capacity = 32000;
}
