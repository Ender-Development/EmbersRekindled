package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class MelterCategory {
    @Config.RequiresMcRestart
    @Config.Name("Ore Melting Amount")
    @Config.Comment({
            "How many mb of fluid are obtained per ore output in the melter?",
            "This is multiplied by the amount of output a melter would produce, so by default 288mb = 2 ingots."
    })
    public int melterOreAmount = 288;

    @Config.RequiresMcRestart
    @Config.Name("Processing Time")
    @Config.Comment("The time in ticks it takes to process one recipe.")
    public int processTime = 200;

    @Config.RequiresMcRestart
    @Config.Name("Ember Cost Multiplier")
    @Config.Comment("The ember cost per tick.")
    public double emberCost = 1.0;
}
