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

public class TileEntityRegister implements IRegistrable<RegistryManager.tupleTileEntity> {
    public static final TileEntityRegister INSTANCE = new TileEntityRegister();

    private TileEntityRegister() {
    }

    private final static List<RegistryManager.tupleTileEntity> REGISTRY = new ArrayList<>(Arrays.asList(
            new RegistryManager.tupleTileEntity(TileEntityTank.class, "tile_entity_tank"),
            new RegistryManager.tupleTileEntity(TileEntityFluidPipe.class, "tile_entity_pipe"),
            new RegistryManager.tupleTileEntity(TileEntityFluidExtractor.class, "tile_entity_pump"),
            new RegistryManager.tupleTileEntity(TileEntityFurnaceTop.class, "tile_entity_furnace_top"),
            new RegistryManager.tupleTileEntity(TileEntityFurnaceBottom.class, "tile_entity_furnace_bottom"),
            new RegistryManager.tupleTileEntity(TileEntityEmitter.class, "tile_entity_emitter"),
            new RegistryManager.tupleTileEntity(TileEntityReceiver.class, "tile_entity_receiver"),
            new RegistryManager.tupleTileEntity(TileEntityCopperCell.class, "tile_entity_copper_cell"),
            new RegistryManager.tupleTileEntity(TileEntityItemPipe.class, "tile_entity_item_pipe"),
            new RegistryManager.tupleTileEntity(TileEntityItemExtractor.class, "tile_entity_item_pump"),
            new RegistryManager.tupleTileEntity(TileEntityBin.class, "tile_entity_bin"),
            new RegistryManager.tupleTileEntity(TileEntityStamper.class, "tile_entity_stamper"),
            new RegistryManager.tupleTileEntity(TileEntityStampBase.class, "tile_entity_stamp_base"),
            new RegistryManager.tupleTileEntity(TileEntityEmberBore.class, "tile_entity_ember_bore"),
            new RegistryManager.tupleTileEntity(TileEntityMechAccessor.class, "tile_entity_mech_accessor"),
            new RegistryManager.tupleTileEntity(TileEntityMechCore.class, "tile_entity_mech_core"),
            new RegistryManager.tupleTileEntity(TileEntityActivatorTop.class, "tile_entity_activator_top"),
            new RegistryManager.tupleTileEntity(TileEntityActivatorBottom.class, "tile_entity_activator_bottom"),
            new RegistryManager.tupleTileEntity(TileEntityMixerTop.class, "tile_entity_mixer_top"),
            new RegistryManager.tupleTileEntity(TileEntityMixerBottom.class, "tile_entity_mixer_bottom"),
            new RegistryManager.tupleTileEntity(TileEntityHeatCoil.class, "tile_entity_heat_coil"),
            new RegistryManager.tupleTileEntity(TileEntityDropper.class, "tile_entity_dropper"),
            new RegistryManager.tupleTileEntity(TileEntityLargeTank.class, "tile_entity_large_tank"),
            new RegistryManager.tupleTileEntity(TileEntityBeamSplitter.class, "tile_entity_beam_splitter"),
            new RegistryManager.tupleTileEntity(TileEntityRelay.class, "tile_entity_relay"),
            new RegistryManager.tupleTileEntity(TileEntityCrystalCell.class, "tile_entity_crystal_cell"),
            new RegistryManager.tupleTileEntity(TileEntityCharger.class, "tile_entity_charger"),
            new RegistryManager.tupleTileEntity(TileEntityCinderPlinth.class, "tile_entity_cinder_plinth"),
            new RegistryManager.tupleTileEntity(TileEntityKnowledgeTable.class, "tile_entity_knowledge_table"),
            new RegistryManager.tupleTileEntity(TileEntityAlchemyPedestal.class, "tile_entity_alchemy_pedestal"),
            new RegistryManager.tupleTileEntity(TileEntityAlchemyTablet.class, "tile_entity_alchemy_tablet"),
            new RegistryManager.tupleTileEntity(TileEntityItemTransfer.class, "tile_entity_item_transfer"),
            new RegistryManager.tupleTileEntity(TileEntityBeamCannon.class, "tile_entity_beam_cannon"),
            new RegistryManager.tupleTileEntity(TileEntityDawnstoneAnvil.class, "tile_entity_dawnstone_anvil"),
            new RegistryManager.tupleTileEntity(TileEntityAutoHammer.class, "tile_entity_auto_hammer"),
            new RegistryManager.tupleTileEntity(TileEntityItemVacuum.class, "tile_entity_vacuum"),
            new RegistryManager.tupleTileEntity(TileEntityBreaker.class, "tile_entity_breaker"),
            new RegistryManager.tupleTileEntity(TileEntitySeed.class, "tile_entity_seed"),
            new RegistryManager.tupleTileEntity(TileEntitySeedNew.class, "tile_entity_seed_new"),
            new RegistryManager.tupleTileEntity(TileEntityEmberInjector.class, "tile_entity_ember_injector"),
            new RegistryManager.tupleTileEntity(TileEntityBoilerBottom.class, "tile_entity_boiler_bottom"),
            new RegistryManager.tupleTileEntity(TileEntityBoilerTop.class, "tile_entity_boiler_top"),
            new RegistryManager.tupleTileEntity(TileEntityReactor.class, "tile_entity_reactor"),
            new RegistryManager.tupleTileEntity(TileEntityCombustor.class, "tile_entity_combustor"),
            new RegistryManager.tupleTileEntity(TileEntityCatalyzer.class, "tile_entity_catalyzer"),
            new RegistryManager.tupleTileEntity(TileEntityFieldChart.class, "tile_entity_field_chart"),
            new RegistryManager.tupleTileEntity(TileEntityPulser.class, "tile_entity_pulser"),
            new RegistryManager.tupleTileEntity(TileEntityInfernoForge.class, "tile_entity_inferno_forge"),
            new RegistryManager.tupleTileEntity(TileEntityInfernoForgeOpening.class, "tile_entity_inferno_forge_opening"),
            new RegistryManager.tupleTileEntity(TileEntityCreativeEmberSource.class, "tile_entity_creative_ember_source"),
            new RegistryManager.tupleTileEntity(TileEntityPumpBottom.class, "tile_entity_pump_bottom"),
            new RegistryManager.tupleTileEntity(TileEntityPumpTop.class, "tile_entity_pump_top"),
            new RegistryManager.tupleTileEntity(TileEntityCatalyticPlug.class, "tile_entity_catalytic_plug"),
            new RegistryManager.tupleTileEntity(TileEntityEmberFunnel.class, "tile_entity_ember_funnel"),
            new RegistryManager.tupleTileEntity(TileEntityMiniBoiler.class, "tile_entity_mini_boiler"),
            new RegistryManager.tupleTileEntity(TileEntityReactionChamber.class, "tile_entity_reaction_chamber"),
            new RegistryManager.tupleTileEntity(TileEntityEmberGauge.class, "tile_entity_ember_gauge"),
            new RegistryManager.tupleTileEntity(TileEntityFluidGauge.class, "tile_entity_fluid_gauge"),
            new RegistryManager.tupleTileEntity(TileEntityItemGauge.class, "tile_entity_item_gauge"),
            new RegistryManager.tupleTileEntity(TileEntityFluidTransfer.class, "tile_entity_fluid_transfer"),
            new RegistryManager.tupleTileEntity(TileEntityStirling.class, "tile_entity_stirling"),
            new RegistryManager.tupleTileEntity(TileEntityEmberSiphon.class, "tile_entity_ember_siphon"),
            new RegistryManager.tupleTileEntity(TileEntityClockworkAttenuator.class, "tile_entity_clockwork_attenuator"),
            new RegistryManager.tupleTileEntity(TileEntityArchaicGeysir.class, "tile_entity_archaic_geysir"),
            new RegistryManager.tupleTileEntity(TileEntityStoneValve.class, "tile_entity_stone_valve"),
            new RegistryManager.tupleTileEntity(TileEntityGeoSeparator.class, "tile_entity_geo_separator"),
            new RegistryManager.tupleTileEntity(TileEntityItemRequisition.class, "tile_entity_item_request"),
            new RegistryManager.tupleTileEntity(TileEntityEmberPipe.class, "tile_entity_ember_pipe"),
            new RegistryManager.tupleTileEntity(TileEntityTurret.class, "tile_entity_turret"),
            new RegistryManager.tupleTileEntity(TileEntityFluidDropper.class, "tile_entity_fluid_dropper")
    ));

    @Override
    public Collection<RegistryManager.tupleTileEntity> load() {
        return REGISTRY;
    }

    @Override
    public void register() {
        for (RegistryManager.tupleTileEntity r : INSTANCE.load()) {
            GameRegistry.registerTileEntity(r.te_class, r.location);
        }
    }

    public static void register(Class<? extends TileEntity> te_class, String name) {
        ResourceLocation location = new ResourceLocation(Embers.MODID,name);
        GameRegistry.registerTileEntity(te_class, location);
    }

    @Override
    public void add(RegistryManager.tupleTileEntity registrable) {
        REGISTRY.add(registrable);
    }
}
