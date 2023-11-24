package teamroots.embers.config;

import net.minecraftforge.common.config.Config;
import teamroots.embers.Embers;

@Config(modid = Embers.MODID, category = "tool", name = Embers.CFG_FOLDER + "tool")
@Config.LangKey("cfg.embers.tool")
public class ConfigTool {
    @Config.Name("Blazing Ray")
    @Config.LangKey("cfg.embers.tool.blazing_ray")
    @Config.Comment("Options about the Blazing Ray")
    public static final BlazingRayCategory BLAZING_RAY = new BlazingRayCategory();

    @Config.Name("Cinder Staff")
    @Config.LangKey("cfg.embers.tool.cinder_staff")
    @Config.Comment("Options about the Cinder Staff")
    public static final CinderStaffCategory CINDER_STAFF = new CinderStaffCategory();

    @Config.Name("Shifting Scales")
    @Config.LangKey("cfg.embers.tool.shifting_scales")
    @Config.Comment("Options about the Shifting Scales")
    public static final ShiftingScalesCategory SHIFTING_SCALES = new ShiftingScalesCategory();
    @Config.Name("Metal Tools")
    @Config.LangKey("cfg.embers.tool.metal")
    @Config.Comment({
            "Options about various tool stats.",
            "Each tool material can be disabled.",
            "A tool material won't be registered,",
            "if the corresponding material is missing."
    })
    public static final ToolCategory METAL_TOOL = new ToolCategory();

    public static class BlazingRayCategory {
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

    public static class CinderStaffCategory {
        @Config.RequiresMcRestart
        @Config.Name("Ember Cost")
        @Config.Comment("Ember used up by each shot.")
        public double cost = 25.0;

        @Config.RequiresMcRestart
        @Config.Name("Charge")
        @Config.Comment("Time in ticks to fully charge.")
        public double charge = 60.0;

        @Config.RequiresMcRestart
        @Config.Name("Cooldown")
        @Config.Comment("Cooldown in ticks between each shot.")
        public int cooldown = 10;

        @Config.RequiresMcRestart
        @Config.Name("Damage")
        @Config.Comment("Damage dealt by one shot.")
        public float damage = 17.0f;

        @Config.RequiresMcRestart
        @Config.Name("Size")
        @Config.Comment("Size of the projectile.")
        public float size = 17.0f;

        @Config.RequiresMcRestart
        @Config.Name("AOE Size")
        @Config.Comment("Area of Effect on impact.")
        public float aoe = 2.125f;

        @Config.RequiresMcRestart
        @Config.Name("Lifetime")
        @Config.Comment("Lifetime in ticks of projectile.")
        public int lifetime = 160;
    }

    public static class ShiftingScalesCategory {
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

    public static class ToolCategory {

        @Config.Name("Copper Tools")
        @Config.LangKey("cfg.embers.tool.metal.copper")
        @Config.Comment("Options for Copper Tools")
        public final Tool COPPER = new Tool(true, 2, 181, 5.4f, 1.5f, 16);

        @Config.Name("Silver Tools")
        @Config.LangKey("cfg.embers.tool.metal.silver")
        @Config.Comment("Options for Silver Tools")
        public final Tool SILVER = new Tool(true, 2, 202, 7.6f, 2.0f, 20);

        @Config.Name("Lead Tools")
        @Config.LangKey("cfg.embers.tool.metal.lead")
        @Config.Comment("Options for Lead Tools")
        public final Tool LEAD = new Tool(true, 2, 168, 6.0f, 2.0f, 4);

        @Config.Name("Aluminum Tools")
        @Config.LangKey("cfg.embers.tool.metal.aluminum")
        @Config.Comment("Options for Aluminum Tools")
        public final Tool ALUMINUM = new Tool(true, 2, 220, 5.2f, 1.5f, 14);

        @Config.Name("Bronze Tools")
        @Config.LangKey("cfg.embers.tool.metal.bronze")
        @Config.Comment("Options for Bronze Tools")
        public final Tool BRONZE = new Tool(true, 2, 510, 6.5f, 2.0f, 20);

        @Config.Name("Electrum Tools")
        @Config.LangKey("cfg.embers.tool.metal.electrum")
        @Config.Comment("Options for Electrum Tools")
        public final Tool ELECTRUM = new Tool(true, 2, 71, 10.8f, 1.0f, 30);

        @Config.Name("Nickel Tools")
        @Config.LangKey("cfg.embers.tool.metal.nickel")
        @Config.Comment("Options for Nickel Tools")
        public final Tool NICKEL = new Tool(true, 2, 331, 6.4f, 2.0f, 18);

        @Config.Name("Tin Tools")
        @Config.LangKey("cfg.embers.tool.metal.tin")
        @Config.Comment("Options for Tin Tools")
        public final Tool TIN = new Tool(true, 1, 145, 4.9f, 1.0f, 12);

        @Config.Name("Dawnstone Tools")
        @Config.LangKey("cfg.embers.tool.metal.dawnstone")
        @Config.Comment("Options for Dawnstone Tools")
        public final Tool DAWNSTONE = new Tool(true, 2, 512, 7.5f, 0.0f, 24);

        @Config.Name("Tyrfing")
        @Config.LangKey("cfg.embers.tool.metal.tyrfing")
        @Config.Comment("Options for the Tyrfing")
        public final Tool TYRFING = new Tool(true, 2, 512, 7.5f, 0.0f, 24);

        public static class Tool {
            @Config.RequiresMcRestart
            @Config.Name("Should register?")
            @Config.Comment("If the tools for this material should be registered.")
            public boolean register;

            @Config.RequiresMcRestart
            @Config.Name("Harvest Level")
            @Config.Comment("The harvest level of this material.")
            public int harvestLevel;

            @Config.RequiresMcRestart
            @Config.Name("Base Durability")
            @Config.Comment("The base durability of this material.")
            public int durability;

            @Config.RequiresMcRestart
            @Config.Name("Base Efficiency")
            @Config.Comment("The base efficiency of this material.")
            public float efficiency;

            @Config.RequiresMcRestart
            @Config.Name("Base Damage")
            @Config.Comment("The base damage of this material.")
            public float damage;

            @Config.RequiresMcRestart
            @Config.Name("Enchantability")
            @Config.Comment("The enchantability of this material.")
            public int enchantability;

            private Tool(boolean register, int harvestLevel, int durability, float efficiency, float damage, int enchantability) {
                this.register = register;
                this.harvestLevel = harvestLevel;
                this.durability = durability;
                this.efficiency = efficiency;
                this.damage = damage;
                this.enchantability = enchantability;
            }
        }
    }
}
