package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class MelterCategory {
    @Config.RequiresMcRestart
    @Config.Name("Ore Melting Amount")
    @Config.Comment({
            "The fluid output for melting an ore. This is multiplied with the fluid to ingot ratio.",
            "By default this melts a single ore into 2 ingots."
    })
    public int melterOreAmount = 2;

    @Config.RequiresMcRestart
    @Config.Name("Processing Time")
    @Config.Comment("The time in ticks it takes to process one recipe.")
    public int processTime = 200;

    @Config.RequiresMcRestart
    @Config.Name("Ember Cost Multiplier")
    @Config.Comment("The ember cost per tick.")
    public double emberCost = 1.0;
}
