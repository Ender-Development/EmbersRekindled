package teamroots.embers.config.tool;

import net.minecraftforge.common.config.Config;

public class ToolCategory {

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
