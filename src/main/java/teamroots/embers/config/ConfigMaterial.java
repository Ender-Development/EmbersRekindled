package teamroots.embers.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Loader;
import teamroots.embers.Embers;

import java.util.Arrays;

@Config(modid = Embers.MODID, category = "material", name = Embers.CFG_FOLDER + "material")
@Config.LangKey("cfg.embers.material")
public class ConfigMaterial {
    public enum RegistrationModes {
        /**
         * FORCE - Register the material independent of other mods
         * OFF - Don't register the material at all
         * SMART - Don't register the material if it already exists in another mod
         */
        FORCE, OFF, SMART
    }

    @Config.RequiresMcRestart
    @Config.Name("*SMART* Mods")
    @Config.Comment("A list of all mods, that will be used in the SMART registration check.")
    public static String[] smartMods = new String[]{
            "thermalfoundation"
    };

    @Config.Name("Copper")
    @Config.LangKey("cfg.embers.material.copper")
    @Config.Comment("Options for Copper")
    public static final Material COPPER = new Material(RegistrationModes.FORCE);

    @Config.Name("Silver")
    @Config.LangKey("cfg.embers.material.silver")
    @Config.Comment("Options for Silver")
    public static final Material SILVER = new Material(RegistrationModes.FORCE);

    @Config.Name("Lead")
    @Config.LangKey("cfg.embers.material.lead")
    @Config.Comment("Options for Lead")
    public static final Material LEAD = new Material(RegistrationModes.FORCE);

    @Config.Name("Aluminum")
    @Config.LangKey("cfg.embers.material.aluminum")
    @Config.Comment("Options for Aluminum")
    public static final Material ALUMINUM = new Material(RegistrationModes.SMART);

    @Config.Name("Bronze")
    @Config.LangKey("cfg.embers.material.bronze")
    @Config.Comment("Options for Bronze")
    public static final Material BRONZE = new Material(RegistrationModes.SMART);

    @Config.Name("Electrum")
    @Config.LangKey("cfg.embers.material.electrum")
    @Config.Comment("Options for Electrum")
    public static final Material ELECTRUM = new Material(RegistrationModes.SMART);

    @Config.Name("Nickel")
    @Config.LangKey("cfg.embers.material.nickel")
    @Config.Comment("Options for Nickel")
    public static final Material NICKEL = new Material(RegistrationModes.SMART);

    @Config.Name("Tin")
    @Config.LangKey("cfg.embers.material.tin")
    @Config.Comment("Options for Tin")
    public static final Material TIN = new Material(RegistrationModes.SMART);

    @Config.Name("Iron")
    @Config.LangKey("cfg.embers.material.iron")
    @Config.Comment("Options for Iron")
    public static final Material IRON = new Material(RegistrationModes.SMART);

    @Config.Name("Gold")
    @Config.LangKey("cfg.embers.material.gold")
    @Config.Comment("Options for Gold")
    public static final Material GOLD = new Material(RegistrationModes.SMART);

    @Config.Name("Dawnstone")
    @Config.LangKey("cfg.embers.material.dawnstone")
    @Config.Comment("Options for Dawnstone")
    public static final Material DAWNSTONE = new Material(RegistrationModes.SMART);

    @Config.Name("Mithril")
    @Config.LangKey("cfg.embers.material.mithril")
    @Config.Comment("[WIP] Options for Mithril")
    public static final Material MITHRIL = new Material(RegistrationModes.OFF);

    @Config.Name("Astralite")
    @Config.LangKey("cfg.embers.material.astralite")
    @Config.Comment("[WIP] Options for Astralite")
    public static final Material ASTRALITE = new Material(RegistrationModes.OFF);

    @Config.Name("Umber Steel")
    @Config.LangKey("cfg.embers.material.umber_steel")
    @Config.Comment("[WIP] Options for Umber Steel")
    public static final Material UMBERSTEEL = new Material(RegistrationModes.OFF);

    public static class Material {
        @Config.RequiresMcRestart
        @Config.Name("Registration Mode")
        @Config.Comment({
                "FORCE - Register the material independent of other mods",
                "OFF - Don't register the material at all",
                "SMART - Don't register the material if it already exists"
        })
        public RegistrationModes registrationMode;

        /**
         * @return if the extra items should be registered.
         * It's used for stuff like the metal seeds
         * as well as the special metals added by the mod.
         */
        public boolean isNotOff() {
            return this.registrationMode != RegistrationModes.OFF;
        }

        /**
         * @return if the base items should be registered.
         * It's used fot stuff like plates, ingots, nuggets, blocks
         */
        public boolean mustLoad() {
            return this.registrationMode == RegistrationModes.FORCE || Arrays.stream(ConfigMaterial.smartMods).noneMatch(Loader::isModLoaded);
        }

        private Material(RegistrationModes registrationMode) {
            this.registrationMode = registrationMode;
        }
    }
}
