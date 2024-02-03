package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class BeamCanonCategory {
    @Config.RequiresMcRestart
    @Config.Name("Pull Rate")
    @Config.Comment("The amount of ember pulled per tick.")
    public double pull_rate = 2000.0;

    @Config.RequiresMcRestart
    @Config.Name("Fire Threshold")
    @Config.Comment("The amount of ember required to fire.")
	public int fire_threshold = 400;

    @Config.RequiresMcRestart
    @Config.Name("Damage")
    @Config.Comment("The amount of damage dealt per shot.")
	public double damage = 25.0;

    @Config.RequiresMcRestart
    @Config.Name("Max Distance")
    @Config.Comment("The maximum distance the beam can travel.")
	public int max_distance = 64;
}
