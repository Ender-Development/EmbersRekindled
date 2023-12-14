package teamroots.embers.register;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.DataSerializerEntry;
import teamroots.embers.Embers;
import teamroots.embers.api.power.IEmberCapability;
import teamroots.embers.api.upgrades.IUpgradeProvider;
import teamroots.embers.block.IBlock;
import teamroots.embers.block.IModeledBlock;
import teamroots.embers.compat.BaublesIntegration;
import teamroots.embers.compat.MysticalMechanicsIntegration;
import teamroots.embers.compat.Util;
import teamroots.embers.config.ConfigMob;
import teamroots.embers.entity.*;
import teamroots.embers.item.IModeledItem;
import teamroots.embers.item.ItemEmberCartridge;
import teamroots.embers.item.ItemEmberJar;
import teamroots.embers.item.ItemTyrfing;
import teamroots.embers.power.DefaultEmberCapability;
import teamroots.embers.power.EmberCapabilityStorage;
import teamroots.embers.research.capability.DefaultResearchCapability;
import teamroots.embers.research.capability.IResearchCapability;
import teamroots.embers.tileentity.*;
import teamroots.embers.upgrade.UpgradeCatalyticPlug;
import teamroots.embers.util.DefaultUpgradeProvider;
import teamroots.embers.util.EmbersFuelHandler;
import teamroots.embers.util.ExtraSerializers;
import teamroots.embers.world.WorldGenOres;
import teamroots.embers.world.WorldGenSmallRuin;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

// I am currently dead!

public class RegistryManager {

    public static Biome biome_cave;

    public static DimensionType dimension_cave;

    public static WorldGenOres world_gen_ores;

    public static IWorldGenerator world_gen_small_ruin;

    public static void registerAll() {
        registerCapabilities();

        FluidRegister.INSTANCE.register();
        EntityRegister.INSTANCE.register();
        ItemModifierRegister.INSTANCE.register();
        TileEntityRegister.INSTANCE.register();

        List<BiomeEntry> biomeEntries = new ArrayList<BiomeEntry>();
        biomeEntries.addAll(BiomeManager.getBiomes(BiomeType.COOL));
        biomeEntries.addAll(BiomeManager.getBiomes(BiomeType.DESERT));
        biomeEntries.addAll(BiomeManager.getBiomes(BiomeType.ICY));
        biomeEntries.addAll(BiomeManager.getBiomes(BiomeType.WARM));
        List<Biome> biomes = new ArrayList<Biome>();
        for (BiomeEntry b : biomeEntries) {
            biomes.add(b.biome);
        }
        biomes.addAll(BiomeManager.oceanBiomes);

        EntityRegistry.addSpawn(EntityAncientGolem.class, ConfigMob.EMBER_GOLEM.spawnWeight, 1, 1, EnumCreatureType.MONSTER, biomes.toArray(new Biome[biomes.size()]));

        world_gen_ores = new WorldGenOres();
        GameRegistry.registerWorldGenerator(world_gen_ores, 1);
        int weight = 400;
        GameRegistry.registerWorldGenerator(world_gen_small_ruin = new WorldGenSmallRuin(), weight++);

        //GameRegistry.register(biomeCave = new BiomeCave());

        //dimensionCave = DimensionType.register("cave", "cave", 90, CaveProvider.class, false);
        //BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(biomeCave, 10000));

        MinecraftForge.EVENT_BUS.register(EmbersFuelHandler.class);
        UpgradeCatalyticPlug.registerBlacklistedTile(TileEntityBeamCannon.class);

        if (Util.isBaublesIntegrationEnabled())
            BaublesIntegration.registerAll();
        if (Util.isMysticalMechanicsIntegrationEnabled())
            MysticalMechanicsIntegration.registerAll();
    }

    private static void registerCapabilities() {
        CapabilityManager.INSTANCE.register(IEmberCapability.class, new EmberCapabilityStorage(), DefaultEmberCapability.class);
        CapabilityManager.INSTANCE.register(IUpgradeProvider.class, new Capability.IStorage<IUpgradeProvider>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<IUpgradeProvider> capability, IUpgradeProvider instance, EnumFacing side) {
                return null;
            }

            @Override
            public void readNBT(Capability<IUpgradeProvider> capability, IUpgradeProvider instance, EnumFacing side, NBTBase nbt) {
                //NOOP
            }
        }, () -> {
            return new DefaultUpgradeProvider("none", null);
        });
        CapabilityManager.INSTANCE.register(IResearchCapability.class, new Capability.IStorage<IResearchCapability>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<IResearchCapability> capability, IResearchCapability instance, EnumFacing side) {
                NBTTagCompound compound = new NBTTagCompound();
                instance.writeToNBT(compound);
                return compound;
            }

            @Override
            public void readNBT(Capability<IResearchCapability> capability, IResearchCapability instance, EnumFacing side, NBTBase nbt) {
                NBTTagCompound compound = (NBTTagCompound) nbt;
                instance.readFromNBT(compound);
            }
        }, DefaultResearchCapability::new);
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block b : BlockRegister.INSTANCE.load()) {
            event.getRegistry().register(b);
        }
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        for (Item i : ItemRegister.INSTANCE.load()) {
            event.getRegistry().register(i);
        }
        for (Block b : BlockRegister.INSTANCE.load()) {
            if (b instanceof IBlock) {
                Item itemBlock = ((IBlock) b).getItemBlock();
                if (itemBlock != null)
                    event.getRegistry().register(itemBlock);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerColorHandlers() {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemEmberJar.ColorHandler(), ItemRegister.EMBER_JAR);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemEmberCartridge.ColorHandler(), ItemRegister.EMBER_CARTRIDGE);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemTyrfing.ColorHandler(), ItemRegister.TYRFING);
    }

    @SideOnly(Side.CLIENT)
    public static void registerEntityRendering() {
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

        RenderingRegistry.registerEntityRenderingHandler(EntityEmberPacket.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityEmberProjectile.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityAncientGolem.class, new RenderAncientGolem.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityEmberLight.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityMagmaWorm.class, new RenderMagmaWorm.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityMagmaProjectile.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerRendering(ModelRegistryEvent event) {

        for (Block block : BlockRegister.INSTANCE.load()) {
            if (block instanceof IModeledBlock) {
                ((IModeledBlock) block).initModel();
            }
        }
        for (Item item : ItemRegister.INSTANCE.load()) {
            if (item instanceof IModeledItem) {
                ((IModeledItem) item).initModel();
            }
        }
    }

    @SubscribeEvent
    public void init(RegistryEvent.Register<DataSerializerEntry> event) {
        event.getRegistry().register(new DataSerializerEntry(ExtraSerializers.FLOAT_ARRAY).setRegistryName(Embers.MODID, "serializer_float_array"));
    }

    public static class dummyTileEntity {
        protected final Class<? extends TileEntity> te_class;
        protected final ResourceLocation location;

        public dummyTileEntity(Class<? extends TileEntity> te_class, String name) {
            this.te_class = te_class;
            this.location = new ResourceLocation(Embers.MODID, name);
        }
    }
}
