package teamroots.embers.register;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import teamroots.embers.Embers;
import teamroots.embers.block.BlockBase;
import teamroots.embers.config.ConfigMaterial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockRegister implements IRegistrable<Block>{

    public static final BlockRegister INSTANCE = new BlockRegister();
    private BlockRegister() {}


    public static final Block ADVANCED_EDGE;
    public static final Block ALCHEMY_PEDESTAL;
    public static final Block ALCHEMY_TABLET;
    public static final Block ARCHAIC_BRICKS;
    public static final Block ARCHAIC_EDGE;
    public static final Block ARCHAIC_GEYSIR;
    public static final Block ARCHAIC_LIGHT;
    public static final Block ARCHAIC_MECH_EDGE;
    public static final Block ARCHAIC_TILE;
    public static final Block ASHEN_BRICK;
    public static final Block ASHEN_BRICK_SLAB;
    public static final Block ASHEN_BRICK_SLAB_DOUBLE;
    public static final Block ASHEN_STONE;
    public static final Block ASHEN_STONE_SLAB;
    public static final Block ASHEN_STONE_SLAB_DOUBLE;
    public static final Block ASHEN_TILE;
    public static final Block ASHEN_TILE_SLAB;
    public static final Block ASHEN_TILE_SLAB_DOUBLE;
    public static final Block AUTO_HAMMER;
    public static final Block BEAM_CANNON;
    public static final Block BEAM_SPLITTER;
    public static final Block BIN;
    public static final Block BLOCK_ALCHEMICAL_REDSTONE;
    public static final Block BLOCK_BRONZE;
    public static final Block BLOCK_CAMINITE_BRICK;
    public static final Block BLOCK_CAMINITE_BRICK_SLAB;
    public static final Block BLOCK_CAMINITE_BRICK_SLAB_DOUBLE;
    public static final Block BLOCK_CAMINITE_LARGE_BRICK;
    public static final Block BLOCK_CAMINITE_LARGE_BRICK;
    public static final Block BLOCK_COPPER = new BlockBase(Material.ROCK, "block_copper", true)
            .setBeaconBase(true)
            .setHarvestProperties("pickaxe", 1)
            .setHardness(1.4f)
            .setLightOpacity(16)
            .setCreativeTab(Embers.resource_tab);
    public static final Block BLOCK_DAWNSTONE;
    public static final Block BLOCK_ELECTRUM;
    public static final Block BLOCK_FURNACE;
    public static final Block BLOCK_LANTERN;
    public static final Block BLOCK_LEAD;
    public static final Block BLOCK_MITHRIL;
    public static final Block BLOCK_MOLTEN_ALUMINUM;
    public static final Block BLOCK_MOLTEN_BRONZE;
    public static final Block BLOCK_MOLTEN_COPPER;
    public static final Block BLOCK_MOLTEN_DAWNSTONE;
    public static final Block BLOCK_MOLTEN_ELECTRUM;
    public static final Block BLOCK_MOLTEN_GOLD;
    public static final Block BLOCK_MOLTEN_IRON;
    public static final Block BLOCK_MOLTEN_LEAD;
    public static final Block BLOCK_MOLTEN_NICKEL;
    public static final Block BLOCK_MOLTEN_SILVER;
    public static final Block BLOCK_MOLTEN_TIN;
    public static final Block BLOCK_OVEN;
    public static final Block BLOCK_SILVER;
    public static final Block BLOCK_TANK;
    public static final Block BOILER;
    public static final Block BREAKER;
    public static final Block CAMINITE_LEVER;
    public static final Block CATALYTIC_PLUG;
    public static final Block CATALYZER;
    public static final Block CHARGER;
    public static final Block CINDER_PLINTH;
    public static final Block CLOCKWORK_ATTENUATOR;
    public static final Block COMBUSTOR;
    public static final Block COPPER_CELL;
    public static final Block CREATIVE_EMBER_SOURCE;
    public static final Block CRYSTAL_CELL;
    public static final Block DAWNSTONE_ANVIL;
    public static final Block DEEP_LINE;
    public static final Block EMBER_ACTIVATOR;
    public static final Block EMBER_BORE;
    public static final Block EMBER_EMITTER;
    public static final Block EMBER_FUNNEL;
    public static final Block EMBER_GAUGE;
    public static final Block EMBER_INJECTOR;
    public static final Block EMBER_PIPE;
    public static final Block EMBER_PULSER;
    public static final Block EMBER_RECEIVER;
    public static final Block EMBER_RELAY;
    public static final Block EMBER_SIPHON;
    public static final Block FIELD_CHART;
    public static final Block FLUID_DROPPER;
    public static final Block FLUID_GAUGE;
    public static final Block FLUID_TRANSFER;
    public static final Block GEO_SEPARATOR;
    public static final Block GLOW;
    public static final Block HEAT_COIL;
    public static final Block INFERNO_FORGE;
    public static final Block INFERNO_FORGE_EDGE;
    public static final Block ITEM_DROPPER;
    public static final Block ITEM_GAUGE;
    public static final Block ITEM_PIPE;
    public static final Block ITEM_PUMP;
    public static final Block ITEM_REQUEST;
    public static final Block ITEM_TRANSFER;
    public static final Block KNOWLEDGE_TABLE;
    public static final Block LARGE_TANK;
    public static final Block MECHANICAL_PUMP;
    public static final Block MECH_ACCESSOR;
    public static final Block MECH_CORE;
    public static final Block MECH_EDGE;
    public static final Block MINI_BOILER;
    public static final Block MIXER;
    public static final Block ORE_ALUMINUM, BLOCK_ALUMINUM;
    public static final Block ORE_COPPER = new BlockBase(Material.ROCK, "ore_copper", true)
            .setIsFullCube(true)
            .setIsOpaqueCube(true)
            .setHarvestProperties("pickaxe", 1)
            .setHardness(1.8f)
            .setLightOpacity(16)
            .setCreativeTab(Embers.resource_tab);
    public static final Block ORE_LEAD;
    public static final Block ORE_NICKEL, BLOCK_NICKEL;
    public static final Block ORE_QUARTZ;
    public static final Block ORE_SILVER;
    public static final Block ORE_TIN, BLOCK_TIN;
    public static final Block PIPE;
    public static final Block PUMP;
    public static final Block REACTION_CHAMBER;
    public static final Block REACTOR;
    public static final Block SEALED_PLANKS;
    public static final Block SEED_ALCHEMIC;
    public static final Block SEED_ALUMINUM;
    public static final Block SEED_COPPER;
    public static final Block SEED_DAWNSTONE;
    public static final Block SEED_GOLD;
    public static final Block SEED_IRON;
    public static final Block SEED_LEAD;
    public static final Block SEED_NICKEL;
    public static final Block SEED_SILVER;
    public static final Block SEED_TIN;
    public static final Block STAIRS_ASHEN_BRICK;
    public static final Block STAIRS_ASHEN_STONE;
    public static final Block STAIRS_ASHEN_TILE;
    public static final Block STAIRS_CAMINITE_BRICK;
    public static final Block STAMPER;
    public static final Block STAMP_BASE;
    public static final Block STIRLING;
    public static final Block STONE_EDGE;
    public static final Block STONE_VALVE;
    public static final Block STRUCTURE_MARKER;
    public static final Block TURRET;
    public static final Block VACUUM;
    public static final Block WALL_ASHEN_BRICK;
    public static final Block WALL_ASHEN_STONE;
    public static final Block WALL_ASHEN_TILE;
    public static final Block WALL_CAMINITE_BRICK;
    public static final Block WRAPPED_SEALED_PLANKS;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final static List<Block> ALWAYS_REGISTER = Collections.singletonList(
            null // FIXME
    );

    @Override
    public void register(RegistryEvent.Register<Block> event) {
        for (Block block : ALWAYS_REGISTER) {
            event.getRegistry().register(block);
        }
        if (ConfigMaterial.COPPER.mustLoad()) {
            event.getRegistry().register(BLOCK_COPPER);
            event.getRegistry().register(ORE_COPPER);
        }
    }
}
