package teamroots.embers.config.tool;

import net.minecraftforge.common.config.Config;

public class ShiftingScalesCategory {
    @Config.RequiresMcRestart
    @Config.Name("Damage Passes")
    @Config.Comment({
            "Syntax is '<damagetype>:<rate>'.",
            "Determines which damage types are partially unaffected by the shifting scales augment.",
            "[Default Damage Types](https://skmedix.github.io/ForgeJavaDocs/javadoc/forge/1.12.2-14.23.5.2859/net/minecraft/util/DamageSource.html)"
    })
    public String[] damagePasses = new String[]{
            "drown:1.0",
            "starve:1.0",
    };

    @Config.RequiresMcRestart
    @Config.Name("Damage Rates")
    @Config.Comment({
            "Syntax is '<damagetype>:<rate>'.",
            "Specifies a separate damage rate for depleting the scales.",
            "[Default Damage Types](https://skmedix.github.io/ForgeJavaDocs/javadoc/forge/1.12.2-14.23.5.2859/net/minecraft/util/DamageSource.html)",
            "Example: cactus:0.5"
    })
    public String[] damageRates = new String[]{};
}
