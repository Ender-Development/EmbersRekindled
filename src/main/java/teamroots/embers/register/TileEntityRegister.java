package teamroots.embers.register;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamroots.embers.Embers;
import teamroots.embers.tileentity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TileEntityRegister implements IRegistrable<RegistryManager.dummyTileEntity> {
    public static final TileEntityRegister INSTANCE = new TileEntityRegister();

    private TileEntityRegister() {
    }

    private final static List<RegistryManager.dummyTileEntity> REGISTRY = new ArrayList<>(Arrays.asList(
            new RegistryManager.dummyTileEntity(TileEntityTank.class, "tile_entity_tank"),
            new RegistryManager.dummyTileEntity(TileEntityFluidPipe.class, "tile_entity_pipe"),
            new RegistryManager.dummyTileEntity(TileEntityFluidExtractor.class, "tile_entity_pump"),
            new RegistryManager.dummyTileEntity(TileEntityFurnaceTop.class, "tile_entity_furnace_top"),
            new RegistryManager.dummyTileEntity(TileEntityFurnaceBottom.class, "tile_entity_furnace_bottom"),
            new RegistryManager.dummyTileEntity(TileEntityEmitter.class, "tile_entity_emitter"),
            new RegistryManager.dummyTileEntity(TileEntityReceiver.class, "tile_entity_receiver"),
            new RegistryManager.dummyTileEntity(TileEntityCopperCell.class, "tile_entity_copper_cell"),
            new RegistryManager.dummyTileEntity(TileEntityItemPipe.class, "tile_entity_item_pipe"),
            new RegistryManager.dummyTileEntity(TileEntityItemExtractor.class, "tile_entity_item_pump"),
            new RegistryManager.dummyTileEntity(TileEntityBin.class, "tile_entity_bin"),
            new RegistryManager.dummyTileEntity(TileEntityStamper.class, "tile_entity_stamper"),
            new RegistryManager.dummyTileEntity(TileEntityStampBase.class, "tile_entity_stamp_base"),
            new RegistryManager.dummyTileEntity(TileEntityEmberBore.class, "tile_entity_ember_bore"),
            new RegistryManager.dummyTileEntity(TileEntityMechAccessor.class, "tile_entity_mech_accessor"),
            new RegistryManager.dummyTileEntity(TileEntityMechCore.class, "tile_entity_mech_core"),
            new RegistryManager.dummyTileEntity(TileEntityActivatorTop.class, "tile_entity_activator_top"),
            new RegistryManager.dummyTileEntity(TileEntityActivatorBottom.class, "tile_entity_activator_bottom"),
            new RegistryManager.dummyTileEntity(TileEntityMixerTop.class, "tile_entity_mixer_top"),
            new RegistryManager.dummyTileEntity(TileEntityMixerBottom.class, "tile_entity_mixer_bottom"),
            new RegistryManager.dummyTileEntity(TileEntityHeatCoil.class, "tile_entity_heat_coil"),
            new RegistryManager.dummyTileEntity(TileEntityDropper.class, "tile_entity_dropper"),
            new RegistryManager.dummyTileEntity(TileEntityLargeTank.class, "tile_entity_large_tank"),
            new RegistryManager.dummyTileEntity(TileEntityBeamSplitter.class, "tile_entity_beam_splitter"),
            new RegistryManager.dummyTileEntity(TileEntityRelay.class, "tile_entity_relay"),
            new RegistryManager.dummyTileEntity(TileEntityCrystalCell.class, "tile_entity_crystal_cell"),
            new RegistryManager.dummyTileEntity(TileEntityCharger.class, "tile_entity_charger"),
            new RegistryManager.dummyTileEntity(TileEntityCinderPlinth.class, "tile_entity_cinder_plinth"),
            new RegistryManager.dummyTileEntity(TileEntityKnowledgeTable.class, "tile_entity_knowledge_table"),
            new RegistryManager.dummyTileEntity(TileEntityAlchemyPedestal.class, "tile_entity_alchemy_pedestal"),
            new RegistryManager.dummyTileEntity(TileEntityAlchemyTablet.class, "tile_entity_alchemy_tablet"),
            new RegistryManager.dummyTileEntity(TileEntityItemTransfer.class, "tile_entity_item_transfer"),
            new RegistryManager.dummyTileEntity(TileEntityBeamCannon.class, "tile_entity_beam_cannon"),
            new RegistryManager.dummyTileEntity(TileEntityDawnstoneAnvil.class, "tile_entity_dawnstone_anvil"),
            new RegistryManager.dummyTileEntity(TileEntityAutoHammer.class, "tile_entity_auto_hammer"),
            new RegistryManager.dummyTileEntity(TileEntityItemVacuum.class, "tile_entity_vacuum"),
            new RegistryManager.dummyTileEntity(TileEntityBreaker.class, "tile_entity_breaker"),
            new RegistryManager.dummyTileEntity(TileEntitySeed.class, "tile_entity_seed"),
            new RegistryManager.dummyTileEntity(TileEntitySeedNew.class, "tile_entity_seed_new"),
            new RegistryManager.dummyTileEntity(TileEntityEmberInjector.class, "tile_entity_ember_injector"),
            new RegistryManager.dummyTileEntity(TileEntityBoilerBottom.class, "tile_entity_boiler_bottom"),
            new RegistryManager.dummyTileEntity(TileEntityBoilerTop.class, "tile_entity_boiler_top"),
            new RegistryManager.dummyTileEntity(TileEntityReactor.class, "tile_entity_reactor"),
            new RegistryManager.dummyTileEntity(TileEntityCombustor.class, "tile_entity_combustor"),
            new RegistryManager.dummyTileEntity(TileEntityCatalyzer.class, "tile_entity_catalyzer"),
            new RegistryManager.dummyTileEntity(TileEntityFieldChart.class, "tile_entity_field_chart"),
            new RegistryManager.dummyTileEntity(TileEntityPulser.class, "tile_entity_pulser"),
            new RegistryManager.dummyTileEntity(TileEntityInfernoForge.class, "tile_entity_inferno_forge"),
            new RegistryManager.dummyTileEntity(TileEntityInfernoForgeOpening.class, "tile_entity_inferno_forge_opening"),
            new RegistryManager.dummyTileEntity(TileEntityCreativeEmberSource.class, "tile_entity_creative_ember_source"),
            new RegistryManager.dummyTileEntity(TileEntityPumpBottom.class, "tile_entity_pump_bottom"),
            new RegistryManager.dummyTileEntity(TileEntityPumpTop.class, "tile_entity_pump_top"),
            new RegistryManager.dummyTileEntity(TileEntityCatalyticPlug.class, "tile_entity_catalytic_plug"),
            new RegistryManager.dummyTileEntity(TileEntityEmberFunnel.class, "tile_entity_ember_funnel"),
            new RegistryManager.dummyTileEntity(TileEntityMiniBoiler.class, "tile_entity_mini_boiler"),
            new RegistryManager.dummyTileEntity(TileEntityReactionChamber.class, "tile_entity_reaction_chamber"),
            new RegistryManager.dummyTileEntity(TileEntityEmberGauge.class, "tile_entity_ember_gauge"),
            new RegistryManager.dummyTileEntity(TileEntityFluidGauge.class, "tile_entity_fluid_gauge"),
            new RegistryManager.dummyTileEntity(TileEntityItemGauge.class, "tile_entity_item_gauge"),
            new RegistryManager.dummyTileEntity(TileEntityFluidTransfer.class, "tile_entity_fluid_transfer"),
            new RegistryManager.dummyTileEntity(TileEntityStirling.class, "tile_entity_stirling"),
            new RegistryManager.dummyTileEntity(TileEntityEmberSiphon.class, "tile_entity_ember_siphon"),
            new RegistryManager.dummyTileEntity(TileEntityClockworkAttenuator.class, "tile_entity_clockwork_attenuator"),
            new RegistryManager.dummyTileEntity(TileEntityArchaicGeysir.class, "tile_entity_archaic_geysir"),
            new RegistryManager.dummyTileEntity(TileEntityStoneValve.class, "tile_entity_stone_valve"),
            new RegistryManager.dummyTileEntity(TileEntityGeoSeparator.class, "tile_entity_geo_separator"),
            new RegistryManager.dummyTileEntity(TileEntityItemRequisition.class, "tile_entity_item_request"),
            new RegistryManager.dummyTileEntity(TileEntityEmberPipe.class, "tile_entity_ember_pipe"),
            new RegistryManager.dummyTileEntity(TileEntityTurret.class, "tile_entity_turret"),
            new RegistryManager.dummyTileEntity(TileEntityFluidDropper.class, "tile_entity_fluid_dropper")
    ));

    @Override
    public Collection<RegistryManager.dummyTileEntity> load() {
        return REGISTRY;
    }

    @Override
    public void register() {
        for (RegistryManager.dummyTileEntity r : INSTANCE.load()) {
            GameRegistry.registerTileEntity(r.te_class, r.location);
        }
    }

    public static void register(Class<? extends TileEntity> te_class, String name) {
        ResourceLocation location = new ResourceLocation(Embers.MODID,name);
        GameRegistry.registerTileEntity(te_class, location);
    }

    @Override
    public void add(RegistryManager.dummyTileEntity registrable) {
        REGISTRY.add(registrable);
    }
}
