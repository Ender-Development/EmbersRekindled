package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class CinderPlinthCategory {
    @Config.RequiresMcRestart
    @Config.Name("Process Time")
    @Config.Comment("The time in ticks it takes to process one item")
    public int processTime = 40;

    @Config.RequiresMcRestart
    @Config.Name("Ember Cost")
    @Config.Comment("The ember cost per tick")
    public double emberCost = 0.5;
}
