package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class CreativeEmberSourceCategory {
    @Config.RequiresMcRestart
    @Config.Name("Ember Capacity")
    @Config.Comment("The amount of embers that is stored in the Creative Ember Source.")
    public double capacity = 80000.0;
}
