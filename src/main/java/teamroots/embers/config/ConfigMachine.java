package teamroots.embers.config;

import net.minecraftforge.common.config.Config;
import teamroots.embers.Embers;
import teamroots.embers.config.machine.*;

@Config(modid = Embers.MODID, category = "machine", name = Embers.CFG_FOLDER + "machine")
@Config.LangKey("cfg.embers.machine")
public class ConfigMachine {

    @Config.Name("Ember Conduit")
    @Config.LangKey("cfg.embers.machine.ember_conduit")
    @Config.Comment("Options about the Ember Conduit")
    public static final EmberConduitCategory EMBER_CONDUIT = new EmberConduitCategory();

    @Config.Name("Melter")
    @Config.LangKey("cfg.embers.machine.melter")
    @Config.Comment("Options about the Melter")
    public static final MelterCategory MELTER = new MelterCategory();

    @Config.Name("Stamper")
    @Config.LangKey("cfg.embers.machine.stamper")
    @Config.Comment("Options about the Stamper")
    public static final StamperCategory STAMPER = new StamperCategory();

    @Config.Name("Inferno Forge")
    @Config.LangKey("cfg.embers.machine.inferno_forge")
    @Config.Comment("Options about the Inferno Forge")
    public static final InfernoForgeCategory INFERNO_FORGE = new InfernoForgeCategory();

    @Config.Name("Geo Separator")
    @Config.LangKey("cfg.embers.machine.geo_separator")
    @Config.Comment("Options about the Geo Separator")
    public static final GeoSeparatorCategory GEO_SEPARATOR = new GeoSeparatorCategory();

    @Config.Name("Dawnstone Anvil")
    @Config.LangKey("cfg.embers.machine.dawnstone_anvil")
    @Config.Comment("Options about the Dawnstone Anvil")
    public static final DawnStoneAnvilCategory DAWNSTONE_ANVIL = new DawnStoneAnvilCategory();

    @Config.Name("Charger")
    @Config.LangKey("cfg.embers.machine.charger")
    @Config.Comment("Options about the Charger")
    public static final ChargerCategory CHARGER = new ChargerCategory();

    @Config.Name("Cinder Plinth")
    @Config.LangKey("cfg.embers.machine.cinder_plinth")
    @Config.Comment("Options about the Cinder Plinth")
    public static final CinderPlinthCategory CINDER_PLINTH = new CinderPlinthCategory();

    @Config.Name("Fluid Vessel")
    @Config.LangKey("cfg.embers.machine.tank")
    @Config.Comment("Options about the Fluid Vessel")
    public static final TankCategory TANK = new TankCategory();

    @Config.Name("Reservoir")
    @Config.LangKey("cfg.embers.machine.reservoir")
    @Config.Comment("Options about the Reservoir")
    public static final ReservoirCategory RESERVOIR = new ReservoirCategory();

    @Config.Name("Mini Boiler")
    @Config.LangKey("cfg.embers.machine.mini_boiler")
    @Config.Comment("Options about the Mini Boiler")
    public static final MiniBoilerCategory MINI_BOILER = new MiniBoilerCategory();

    @Config.Name("Ember Bore")
    @Config.LangKey("cfg.embers.machine.ember_bore")
    @Config.Comment("Options about the Ember Bore")
    public static final EmberBoreCategory EMBER_BORE = new EmberBoreCategory();

    @Config.Name("Copper Cell")
    @Config.LangKey("cfg.embers.machine.copper_cell")
    @Config.Comment("Options about the Copper Cell")
    public static final CopperCellCategory COPPER_CELL = new CopperCellCategory();

    @Config.Name("Copper Cell")
    @Config.LangKey("cfg.embers.machine.creative_ember_source")
    @Config.Comment("Options about the Copper Cell")
    public static final CreativeEmberSourceCategory CREATIVE_EMBER_SOURCE = new CreativeEmberSourceCategory();

    @Config.Name("Ember Funnel")
    @Config.LangKey("cfg.embers.machine.ember_funnel")
    @Config.Comment("Options about the Ember Funnel")
    public static final EmberFunnelCategory EMBER_FUNNEL = new EmberFunnelCategory();

    @Config.Name("Emitter")
    @Config.LangKey("cfg.embers.machine.emitter")
    @Config.Comment("Options about the Emitter")
    public static final EmitterCategory EMITTER = new EmitterCategory();

    @Config.Name("Steam Engine [MM]")
    @Config.LangKey("cfg.embers.machine.steam_engine")
    @Config.Comment({
            "Requires Mystical Mechanics!",
            "Options about the Steam Engine"
    })
    public static final SteamEngineCategory STEAM_ENGINE = new SteamEngineCategory();

    @Config.RequiresMcRestart
    @Config.Name("Ingot to Fluid ratio")
    @Config.Comment("What is the liquid equivalent to an ingot in mb?")
    public static int ingotFluidAmount = 144;

    @Config.RequiresMcRestart
    @Config.Name("Nugget to Fluid ratio")
    @Config.Comment("What is the liquid equivalent to a nugget in mb?")
    public static int nuggetFluidAmount = 16;

}
