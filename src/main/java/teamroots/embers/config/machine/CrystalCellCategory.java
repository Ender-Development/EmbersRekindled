package teamroots.embers.config.machine;

import net.minecraftforge.common.config.Config;

public class CrystalCellCategory {

    @Config.RequiresMcRestart
    @Config.Name("Max Capacity")
    @Config.Comment("The max capacity of the crystal cell")
    public int max_capacity = 1440000;

    @Config.RequiresMcRestart
    @Config.Name("Base Capacity")
    @Config.Comment("The base capacity of the crystal cell")
    public int base_capacity = 64000;
}
