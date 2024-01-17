package teamroots.embers.config.world;

import net.minecraftforge.common.config.Config;

public class TransmutationCategory {

    @Config.RequiresMcRestart
    @Config.Name("Iterations per tick")
    @Config.Comment("How many blocks to transmute per tick. Higher values will cause more lag.")
    public int iterations_per_tick = 10;

    @Config.RequiresMcRestart
    @Config.Name("Max blocks")
    @Config.Comment("Maximum number of blocks to transmute. Higher values will cause more lag.")
    public int max_blocks = 16;

    @Config.RequiresMcRestart
    @Config.Name("Fail chance")
    @Config.Comment("Chance for a transmutation to fail.")
    public double fail_chance = 0.1;
}
