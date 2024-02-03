package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class MiniBoilerCategory {
    @Config.RequiresMcRestart
    @Config.Name("Capacity")
    @Config.Comment("How much fluid (in mb) fits into a mini boiler?")
    public int capacity = 16000;

    @Config.RequiresMcRestart
    @Config.Name("Heat Multiplier")
    @Config.Comment("How efficient, heat-wise, the mini boiler is at making steam?")
    public float heatMultiplier = 1.0f;

    @Config.RequiresMcRestart
    @Config.Name("Can Explode?")
    @Config.Comment("Whether or not the mini boiler should explode when at maximum steam pressure")
    public boolean canExplode = true;
}
