package teamroots.embers.config.tool;

import net.minecraftforge.common.config.Config;

public class BlazingRayCategory {
    @Config.RequiresMcRestart
    @Config.Name("Ember Cost")
    @Config.Comment("Ember used up by each shot.")
    public double cost = 25.0;

    @Config.RequiresMcRestart
    @Config.Name("Charge")
    @Config.Comment("Time in ticks to fully charge.")
    public double charge = 20.0;

    @Config.RequiresMcRestart
    @Config.Name("Cooldown")
    @Config.Comment("Cooldown in ticks between each shot.")
    public int cooldown = 10;

    @Config.RequiresMcRestart
    @Config.Name("Damage")
    @Config.Comment("Damage dealt by one shot.")
    public float damage = 7.0f;

    @Config.RequiresMcRestart
    @Config.Name("Distance")
    @Config.Comment("Maximum shot distance.")
    public float distance = 96.0f;

    @Config.RequiresMcRestart
    @Config.Name("Spread")
    @Config.Comment("Maximum spread.")
    public double spread = 30.0;
}
