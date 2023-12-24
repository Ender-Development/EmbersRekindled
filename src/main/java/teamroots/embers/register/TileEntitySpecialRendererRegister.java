package teamroots.embers.register;


import net.minecraftforge.fml.client.registry.ClientRegistry;
import teamroots.embers.tileentity.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TileEntitySpecialRendererRegister implements IRegistrable<RegistryManager.tupleTESR>{
    public static final TileEntitySpecialRendererRegister INSTANCE = new TileEntitySpecialRendererRegister();
    private TileEntitySpecialRendererRegister(){}

    private final static List<RegistryManager.tupleTESR> REGISTRY = new ArrayList<>();
    @Override
    public Collection<RegistryManager.tupleTESR> load() {
        return REGISTRY;
    }

    @Override
    public void register() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTank.class, new TileEntityTankRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidPipe.class, new TileEntityFluidPipeRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidExtractor.class, new TileEntityFluidExtractorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFurnaceTop.class, new TileEntityFurnaceTopRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEmitter.class, new TileEntityEmitterRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityItemPipe.class, new TileEntityItemPipeRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityItemExtractor.class, new TileEntityItemExtractorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBin.class, new TileEntityBinRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStamper.class, new TileEntityStamperRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStampBase.class, new TileEntityStampBaseRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEmberBore.class, new TileEntityEmberBoreRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHeatCoil.class, new TileEntityHeatCoilRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLargeTank.class, new TileEntityLargeTankRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystalCell.class, new TileEntityCrystalCellRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCharger.class, new TileEntityChargerRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCinderPlinth.class, new TileEntityCinderPlinthRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKnowledgeTable.class, new TileEntityKnowledgeTableRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlchemyPedestal.class, new TileEntityAlchemyPedestalRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlchemyTablet.class, new TileEntityAlchemyTabletRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityItemTransfer.class, new TileEntityItemTransferRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBeamCannon.class, new TileEntityBeamCannonRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDawnstoneAnvil.class, new TileEntityDawnstoneAnvilRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAutoHammer.class, new TileEntityAutoHammerRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBreaker.class, new TileEntityBreakerRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySeed.class, new TileEntitySeedRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySeedNew.class, new TileEntitySeedNewRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFieldChart.class, new TileEntityFieldChartRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPulser.class, new TileEntityPulserRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInfernoForgeOpening.class, new TileEntityInfernoForgeOpeningRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPumpBottom.class, new TileEntityPumpRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidTransfer.class, new TileEntityFluidTransferRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGeoSeparator.class, new TileEntityGeoSeparatorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityItemRequisition.class, new TileEntityItemRequisitionRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEmberPipe.class, new TileEntityEmberPipeRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMiniBoiler.class, new TileEntityMiniBoilerRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityReactionChamber.class, new TileEntityReactionChamberRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurret.class, new TileEntityTurretRenderer());
    }

    @Override
    public void add(RegistryManager.tupleTESR registrable) {
        REGISTRY.add(registrable);
    }
}
