package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class EmberBoreCategory {
    @Config.RequiresMcRestart
    @Config.Name("Dimension Blacklist")
    @Config.Comment("A list of all dimension IDs in which Embers Ember Bore will not mine.")
    public int[] blacklist = new int[]{};

    @Config.RequiresMcRestart
    @Config.Name("Is Whitelist?")
    @Config.Comment("Whether the Ember Bore blacklist is a whitelist.")
    public boolean isWhiteList = false;

    @Config.RequiresMcRestart
    @Config.Name("Max Y-Height")
    @Config.Comment("The maximum y-level at which the Ember Bore can mine ember.")
    public int yMax = 7;

    @Config.RequiresMcRestart
    @Config.Name("Speed Modifier")
    @Config.Comment("The speed modifier of the Ember Bore before upgrades.")
    public float speedMod = 1.0f;

    @Config.RequiresMcRestart
    @Config.Name("Process Time")
    @Config.Comment("The time in ticks it takes to try one dig attempt.")
    public int processTime = 200;

    @Config.RequiresMcRestart
    @Config.Name("Fuel Cost")
    @Config.Comment("The amount of fuel consumed each tick")
    public double fuelCost = 3.0;
}
