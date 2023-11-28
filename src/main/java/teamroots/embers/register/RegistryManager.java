package teamroots.embers.register;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
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
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
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
import teamroots.embers.api.EmbersAPI;
import teamroots.embers.api.power.IEmberCapability;
import teamroots.embers.api.upgrades.IUpgradeProvider;
import teamroots.embers.block.IBlock;
import teamroots.embers.block.IModeledBlock;
import teamroots.embers.compat.BaublesIntegration;
import teamroots.embers.compat.MysticalMechanicsIntegration;
import teamroots.embers.config.ConfigMain;
import teamroots.embers.config.ConfigMaterial;
import teamroots.embers.config.ConfigMob;
import teamroots.embers.config.ConfigTool;
import teamroots.embers.damage.DamageEmber;
import teamroots.embers.entity.*;
import teamroots.embers.fluid.*;
import teamroots.embers.item.*;
import teamroots.embers.item.block.ItemBlockSlab;
import teamroots.embers.itemmod.*;
import teamroots.embers.power.DefaultEmberCapability;
import teamroots.embers.power.EmberCapabilityStorage;
import teamroots.embers.research.capability.DefaultResearchCapability;
import teamroots.embers.research.capability.IResearchCapability;
import teamroots.embers.tileentity.*;
import teamroots.embers.upgrade.UpgradeCatalyticPlug;
import teamroots.embers.util.*;
import teamroots.embers.world.WorldGenOres;
import teamroots.embers.world.WorldGenSmallRuin;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

// I am currently dead!

public class RegistryManager {
    public static ArrayList<Item> items = new ArrayList<Item>();
    public static ArrayList<Block> blocks = new ArrayList<Block>();

    public static ToolMaterial tool_mat_tyrfing, tool_mat_copper, tool_mat_silver, tool_mat_lead, tool_mat_dawnstone;
    public static ToolMaterial tool_mat_aluminum, tool_mat_bronze, tool_mat_tin, tool_mat_electrum, tool_mat_nickel;
    public static ArmorMaterial armor_mat_ashen_cloak;



    public static Fluid fluid_steam, fluid_molten_dawnstone, fluid_molten_gold, fluid_molten_copper, fluid_molten_lead, fluid_molten_silver, fluid_molten_iron,
            fluid_molten_aluminum, fluid_molten_tin, fluid_molten_bronze, fluid_molten_electrum, fluid_molten_nickel, fluid_alchemical_redstone;
    public static Fluid fluid_crude_oil, fluid_oil, fluid_gas;

    public static Item archaic_circuit, flame_barrier, eldritch_insignia, intelligent_apparatus, caster_orb, resonating_bell, superheater, jet_augment, blasting_core, codex, wildfire_core, ember_cluster, adhesive, tyrfing, isolated_materia, archaic_brick, ancient_motive_core, ashen_cloth, glimmer_shard, glimmer_lamp, inflictor_gem, ashen_cloak_head, ashen_cloak_chest, ashen_cloak_legs, ashen_cloak_boots, alchemic_waste, aspectus_iron, aspectus_copper, aspectus_dawnstone, aspectus_lead, aspectus_silver, golems_eye, dust_ash, grandhammer, pickaxe_clockwork, axe_clockwork, staff_ember, ignition_cannon, ember_jar, ember_cartridge, pickaxe_copper, axe_copper, shovel_copper, hoe_copper, sword_copper, pickaxe_silver, axe_silver, shovel_silver, hoe_silver, sword_silver, pickaxe_lead, axe_lead, shovel_lead, hoe_lead, sword_lead, pickaxe_dawnstone, axe_dawnstone, shovel_dawnstone, hoe_dawnstone, sword_dawnstone, debug, plate_gold, plate_iron, plate_caminite_raw, plate_mithril, stamp_bar_raw, stamp_plate_raw, stamp_flat_raw, nugget_dawnstone, plate_copper, plate_lead, plate_silver, plate_dawnstone, nugget_mithril, ingot_dawnstone, ingot_mithril, crystal_ember, shard_ember, stamp_bar, stamp_plate, stamp_flat, tinker_hammer, ember_detector, ingot_copper, ingot_silver, ingot_lead, nugget_copper, nugget_silver, nugget_lead, brick_caminite, blend_caminite, plate_caminite;
    public static Item ingot_nickel, nugget_nickel, plate_nickel, pickaxe_nickel, axe_nickel, shovel_nickel, hoe_nickel, sword_nickel;
    public static Item ingot_aluminum, nugget_aluminum, plate_aluminum, pickaxe_aluminum, axe_aluminum, shovel_aluminum, hoe_aluminum, sword_aluminum;
    public static Item ingot_tin, nugget_tin, plate_tin, pickaxe_tin, axe_tin, shovel_tin, hoe_tin, sword_tin;
    public static Item ingot_bronze, nugget_bronze, plate_bronze, pickaxe_bronze, axe_bronze, shovel_bronze, hoe_bronze, sword_bronze;
    public static Item ingot_electrum, nugget_electrum, plate_electrum, pickaxe_electrum, axe_electrum, shovel_electrum, hoe_electrum, sword_electrum;
    public static Item dust_ember;
    public static Item dust_metallurgic;
    public static Item diffraction_barrel;
    public static Item focal_lens;
    public static Item stamp_gear, stamp_gear_raw;
    public static Item tinker_lens, anti_tinker_lens;
    public static Item winding_gears;
    public static Item shifting_scales;
    public static Item spark_plug;
    public static Item core_stone;
    public static Item ingot_astralite;
    public static Item plate_astralite;
    public static Item nugget_astralite;
    public static Item ingot_umber_steel;
    public static Item plate_umber_steel;
    public static Item nugget_umber_steel;

    public static Item fulgurite;
    public static Item aster, shard_aster;

    public static Item alchemic_chunk,alchemic_cluster,alchemic_mote,alchemic_piece,alchemic_shard,burst_emitter,crystal_lens,eyepiece,glimmer_charm,intelligent_eye,karma_charm,mass_caldera,radiant_crown,sigil_cross,sigil_empty,sigil_explosion,sigil_eye,sigil_fork,sigil_home,sigil_ring,sigil_skull,sigil_sun,silver_eye_closed,silver_eye_open,truemetal_clump,sulfur_dust;

    public static Item creative_heat;

    public static DamageSource damage_ember;

    public static Biome biome_cave;

    public static DimensionType dimension_cave;

    public static WorldGenOres world_gen_ores;

    public static IWorldGenerator world_gen_small_ruin;


    @Deprecated
    public static void registerAll() {
        registerCapabilities();

        damage_ember = new DamageEmber();

        tool_mat_copper = Util.createToolMaterial("copper", ConfigTool.METAL_TOOL.COPPER);
        tool_mat_silver = Util.createToolMaterial("silver", ConfigTool.METAL_TOOL.SILVER);
        tool_mat_lead = Util.createToolMaterial("lead", ConfigTool.METAL_TOOL.LEAD);
        tool_mat_dawnstone = Util.createToolMaterial("dawnstone", ConfigTool.METAL_TOOL.DAWNSTONE);
        tool_mat_tyrfing = Util.createToolMaterial("tyrfing", ConfigTool.METAL_TOOL.TYRFING);

        armor_mat_ashen_cloak = EnumHelper.addArmorMaterial(Embers.MODID + ":ashen_cloak", Embers.MODID + ":ashen_cloak", 19, new int[]{3, 5, 7, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);

        items.add(new ItemBlockSlab(BlockRegister.BLOCK_CAMINITE_BRICK_SLAB, BlockRegister.BLOCK_CAMINITE_BRICK_SLAB_DOUBLE));
        items.add(new ItemBlockSlab(BlockRegister.ASHEN_STONE_SLAB, BlockRegister.ASHEN_STONE_SLAB_DOUBLE));
        items.add(new ItemBlockSlab(BlockRegister.ASHEN_BRICK_SLAB, BlockRegister.ASHEN_BRICK_SLAB_DOUBLE));
        items.add(new ItemBlockSlab(BlockRegister.ASHEN_TILE_SLAB, BlockRegister.ASHEN_TILE_SLAB_DOUBLE));

        items.add(ingot_copper = new ItemBase("ingot_copper", true).setCreativeTab(Embers.resource_tab));
        items.add(ingot_lead = new ItemBase("ingot_lead", true).setCreativeTab(Embers.resource_tab));
        items.add(ingot_silver = new ItemBase("ingot_silver", true).setCreativeTab(Embers.resource_tab));
        items.add(ingot_dawnstone = new ItemBase("ingot_dawnstone", true).setCreativeTab(Embers.resource_tab));
        if (ConfigMaterial.MITHRIL.isNotOff()) {
            items.add(ingot_mithril = new ItemBase("ingot_mithril", true).setCreativeTab(Embers.resource_tab));
            // Gloomshroud - Add Mithril Nugget
            items.add(nugget_mithril = new ItemBase("nugget_mithril", true).setCreativeTab(Embers.resource_tab));
            // Gloomshroud - Add Mithril Plate
            items.add(plate_mithril = new ItemBase("plate_mithril", true).setCreativeTab(Embers.resource_tab));
        }
        items.add(nugget_copper = new ItemBase("nugget_copper", true).setCreativeTab(Embers.resource_tab));
        items.add(nugget_lead = new ItemBase("nugget_lead", true).setCreativeTab(Embers.resource_tab));
        items.add(nugget_silver = new ItemBase("nugget_silver", true).setCreativeTab(Embers.resource_tab));
        items.add(nugget_dawnstone = new ItemBase("nugget_dawnstone", true).setCreativeTab(Embers.resource_tab));
        items.add(plate_copper = new ItemBase("plate_copper", true).setCreativeTab(Embers.resource_tab));
        items.add(plate_lead = new ItemBase("plate_lead", true).setCreativeTab(Embers.resource_tab));
        items.add(plate_silver = new ItemBase("plate_silver", true).setCreativeTab(Embers.resource_tab));
        items.add(plate_dawnstone = new ItemBase("plate_dawnstone", true).setCreativeTab(Embers.resource_tab));
        if (ConfigMaterial.GOLD.mustLoad()) {
            items.add(plate_iron = new ItemBase("plate_iron", true).setCreativeTab(Embers.resource_tab));
        }
        if (ConfigMaterial.IRON.mustLoad()) {
            items.add(plate_gold = new ItemBase("plate_gold", true).setCreativeTab(Embers.resource_tab));
            // items.add(nugget_iron = new ItemBase("nugget_iron",true).setCreativeTab(Embers.resource_tab));
        }
        items.add(brick_caminite = new ItemBase("brick_caminite", true));
        items.add(blend_caminite = new ItemBase("blend_caminite", true));
        items.add(plate_caminite = new ItemBase("plate_caminite", true));
        items.add(plate_caminite_raw = new ItemBase("plate_caminite_raw", true));
        items.add(tinker_hammer = new ItemTinkerHammer());
        items.add(stamp_bar = new ItemBase("stamp_bar", true));
        items.add(stamp_flat = new ItemBase("stamp_flat", true));
        items.add(stamp_plate = new ItemBase("stamp_plate", true));
        items.add(stamp_gear = new ItemBase("stamp_gear", true));
        items.add(stamp_bar_raw = new ItemBase("stamp_bar_raw", true));
        items.add(stamp_flat_raw = new ItemBase("stamp_flat_raw", true));
        items.add(stamp_plate_raw = new ItemBase("stamp_plate_raw", true));
        items.add(stamp_gear_raw = new ItemBase("stamp_gear_raw", true));
        items.add(ember_detector = new ItemEmberGauge());
        items.add(dust_ember = new ItemBase("dust_ember", true));
        items.add(shard_ember = new ItemBase("shard_ember", true));
        items.add(crystal_ember = new ItemBase("crystal_ember", true));
        if (ConfigTool.METAL_TOOL.COPPER.register && ConfigMaterial.COPPER.isNotOff()) {
            items.add(pickaxe_copper = new ItemPickaxeBase(tool_mat_copper, "pickaxe_copper", true).setCreativeTab(Embers.resource_tab));
            items.add(axe_copper = new ItemAxeBase2(tool_mat_copper, "axe_copper", true, 8.5f, 0.9f).setCreativeTab(Embers.resource_tab));
            items.add(shovel_copper = new ItemShovelBase(tool_mat_copper, "shovel_copper", true).setCreativeTab(Embers.resource_tab));
            items.add(hoe_copper = new ItemHoeBase(tool_mat_copper, "hoe_copper", true).setCreativeTab(Embers.resource_tab));
            items.add(sword_copper = new ItemSwordBase(tool_mat_copper, "sword_copper", true).setCreativeTab(Embers.resource_tab));
        }
        if (ConfigTool.METAL_TOOL.SILVER.register && ConfigMaterial.SILVER.isNotOff()) {
            items.add(pickaxe_silver = new ItemPickaxeBase(tool_mat_silver, "pickaxe_silver", true).setCreativeTab(Embers.resource_tab));
            items.add(axe_silver = new ItemAxeBase2(tool_mat_silver, "axe_silver", true, 9f, 1.0f).setCreativeTab(Embers.resource_tab));
            items.add(shovel_silver = new ItemShovelBase(tool_mat_silver, "shovel_silver", true).setCreativeTab(Embers.resource_tab));
            items.add(hoe_silver = new ItemHoeBase(tool_mat_silver, "hoe_silver", true).setCreativeTab(Embers.resource_tab));
            items.add(sword_silver = new ItemSwordBase(tool_mat_silver, "sword_silver", true).setCreativeTab(Embers.resource_tab));
        }
        if (ConfigTool.METAL_TOOL.LEAD.register && ConfigMaterial.LEAD.isNotOff()) {
            items.add(pickaxe_lead = new ItemPickaxeBase(tool_mat_lead, "pickaxe_lead", true).setCreativeTab(Embers.resource_tab));
            items.add(axe_lead = new ItemAxeBase2(tool_mat_lead, "axe_lead", true, 9f, 0.9f).setCreativeTab(Embers.resource_tab));
            items.add(shovel_lead = new ItemShovelBase(tool_mat_lead, "shovel_lead", true).setCreativeTab(Embers.resource_tab));
            items.add(hoe_lead = new ItemHoeBase(tool_mat_lead, "hoe_lead", true).setCreativeTab(Embers.resource_tab));
            items.add(sword_lead = new ItemSwordBase(tool_mat_lead, "sword_lead", true).setCreativeTab(Embers.resource_tab));
        }
        if (ConfigTool.METAL_TOOL.DAWNSTONE.register && ConfigMaterial.DAWNSTONE.isNotOff()) {
            items.add(pickaxe_dawnstone = new ItemPickaxeBase(tool_mat_dawnstone, "pickaxe_dawnstone", true).setCreativeTab(Embers.resource_tab));
            items.add(axe_dawnstone = new ItemAxeBase2(tool_mat_dawnstone, "axe_dawnstone", true, 9.5f, 1f).setCreativeTab(Embers.resource_tab));
            items.add(shovel_dawnstone = new ItemShovelBase(tool_mat_dawnstone, "shovel_dawnstone", true).setCreativeTab(Embers.resource_tab));
            items.add(hoe_dawnstone = new ItemHoeBase(tool_mat_dawnstone, "hoe_dawnstone", true).setCreativeTab(Embers.resource_tab));
            items.add(sword_dawnstone = new ItemSwordBase(tool_mat_dawnstone, "sword_dawnstone", true).setCreativeTab(Embers.resource_tab));
        }
        items.add(ember_jar = new ItemEmberJar());
        items.add(ember_cartridge = new ItemEmberCartridge());
        items.add(ignition_cannon = new ItemIgnitionCannon());
        items.add(staff_ember = new ItemCinderStaff());
        items.add(axe_clockwork = new ItemClockworkAxe("axe_clockwork", true));
        items.add(pickaxe_clockwork = new ItemClockworkPickaxe("pickaxe_clockwork", true));
        items.add(grandhammer = new ItemGrandhammer("grandhammer", true));
        items.add(dust_ash = new ItemBase("dust_ash", true));
        items.add(aspectus_iron = new ItemBase("aspectus_iron", true));
        items.add(aspectus_copper = new ItemBase("aspectus_copper", true));
        items.add(aspectus_lead = new ItemBase("aspectus_lead", true));
        items.add(aspectus_silver = new ItemBase("aspectus_silver", true));
        items.add(aspectus_dawnstone = new ItemBase("aspectus_dawnstone", true));
        items.add(alchemic_waste = new ItemAlchemicWaste());

        items.add(ashen_cloak_head = new ItemAshenCloak(armor_mat_ashen_cloak, 3, EntityEquipmentSlot.HEAD));
        items.add(ashen_cloak_chest = new ItemAshenCloak(armor_mat_ashen_cloak, 7, EntityEquipmentSlot.CHEST));
        items.add(ashen_cloak_legs = new ItemAshenCloak(armor_mat_ashen_cloak, 5, EntityEquipmentSlot.LEGS));
        items.add(ashen_cloak_boots = new ItemAshenCloak(armor_mat_ashen_cloak, 3, EntityEquipmentSlot.FEET));
        items.add(inflictor_gem = new ItemInflictorGem());
        items.add(glimmer_shard = new ItemGlimmerShard());
        items.add(glimmer_lamp = new ItemGlimmerLamp());
        items.add(ashen_cloth = new ItemBase("ashen_cloth", true));
        items.add(archaic_brick = new ItemBase("archaic_brick", true));
        items.add(ancient_motive_core = new ItemBase("ancient_motive_core", true));
        items.add(isolated_materia = new ItemBase("isolated_materia", true));
        items.add(tyrfing = new ItemTyrfing(tool_mat_tyrfing, "tyrfing", true));
        items.add(adhesive = new ItemBase("adhesive", true));
        items.add(ember_cluster = new ItemBase("ember_cluster", true));
        items.add(wildfire_core = new ItemBase("wildfire_core", true));
        items.add(codex = new ItemCodex());
        items.add(superheater = new ItemBase("superheater", true));
        items.add(jet_augment = new ItemBase("jet_augment", true));
        items.add(blasting_core = new ItemBase("blasting_core", true));
        items.add(caster_orb = new ItemBase("caster_orb", true));
        items.add(resonating_bell = new ItemBase("resonating_bell", true));
        items.add(flame_barrier = new ItemBase("flame_barrier", true));
        items.add(eldritch_insignia = new ItemBase("eldritch_insignia", true));
        items.add(intelligent_apparatus = new ItemBase("intelligent_apparatus", true));
        items.add(archaic_circuit = new ItemBase("archaic_circuit", true));

        items.add(dust_metallurgic = new ItemMetallurgicDust("dust_metallurgic", true));
        items.add(diffraction_barrel = new ItemBase("diffraction_barrel", true));
        items.add(focal_lens = new ItemBase("focal_lens", true));
        items.add(tinker_lens = new ItemTinkerLens("tinker_lens", true));
        items.add(anti_tinker_lens = new ItemBase("anti_tinker_lens", true));
        items.add(shifting_scales = new ItemBase("shifting_scales", true));
        items.add(winding_gears = new ItemBase("winding_gears", true));
        if (ConfigMain.enableWipStuff) {
            items.add(core_stone = new ItemBase("core_stone", true));
        }
        items.add(creative_heat = new ItemHidden("creative_heat"));
        items.add(spark_plug = new ItemBase("spark_plug", true));
        items.add(golems_eye = new ItemGolemEye("golems_eye"));

        if (ConfigMaterial.ALUMINUM.mustLoad()) {
            items.add(ingot_aluminum = new ItemBase("ingot_aluminum", true).setCreativeTab(Embers.resource_tab));
            items.add(plate_aluminum = new ItemBase("plate_aluminum", true).setCreativeTab(Embers.resource_tab));
            items.add(nugget_aluminum = new ItemBase("nugget_aluminum", true).setCreativeTab(Embers.resource_tab));

            if (ConfigTool.METAL_TOOL.ALUMINUM.register) {
                tool_mat_aluminum = Util.createToolMaterial("aluminum", ConfigTool.METAL_TOOL.ALUMINUM);
                tool_mat_aluminum.setRepairItem(new ItemStack(ingot_aluminum));
                items.add(pickaxe_aluminum = new ItemPickaxeBase(tool_mat_aluminum, "pickaxe_aluminum", true).setCreativeTab(Embers.resource_tab));
                items.add(axe_aluminum = new ItemAxeBase(tool_mat_aluminum, "axe_aluminum", true).setCreativeTab(Embers.resource_tab));
                items.add(shovel_aluminum = new ItemShovelBase(tool_mat_aluminum, "shovel_aluminum", true).setCreativeTab(Embers.resource_tab));
                items.add(sword_aluminum = new ItemSwordBase(tool_mat_aluminum, "sword_aluminum", true).setCreativeTab(Embers.resource_tab));
                items.add(hoe_aluminum = new ItemHoeBase(tool_mat_aluminum, "hoe_aluminum", true).setCreativeTab(Embers.resource_tab));
            }
        }

        if (ConfigMaterial.BRONZE.mustLoad()) {
            items.add(ingot_bronze = new ItemBase("ingot_bronze", true).setCreativeTab(Embers.resource_tab));
            items.add(plate_bronze = new ItemBase("plate_bronze", true).setCreativeTab(Embers.resource_tab));
            items.add(nugget_bronze = new ItemBase("nugget_bronze", true).setCreativeTab(Embers.resource_tab));

            if (ConfigTool.METAL_TOOL.BRONZE.register) {
                tool_mat_bronze = Util.createToolMaterial("bronze", ConfigTool.METAL_TOOL.BRONZE);
                tool_mat_bronze.setRepairItem(new ItemStack(ingot_bronze));
                items.add(pickaxe_bronze = new ItemPickaxeBase(tool_mat_bronze, "pickaxe_bronze", true).setCreativeTab(Embers.resource_tab));
                items.add(axe_bronze = new ItemAxeBase(tool_mat_bronze, "axe_bronze", true).setCreativeTab(Embers.resource_tab));
                items.add(shovel_bronze = new ItemShovelBase(tool_mat_bronze, "shovel_bronze", true).setCreativeTab(Embers.resource_tab));
                items.add(sword_bronze = new ItemSwordBase(tool_mat_bronze, "sword_bronze", true).setCreativeTab(Embers.resource_tab));
                items.add(hoe_bronze = new ItemHoeBase(tool_mat_bronze, "hoe_bronze", true).setCreativeTab(Embers.resource_tab));
            }
        }

        if (ConfigMaterial.ELECTRUM.mustLoad()) {
            items.add(ingot_electrum = new ItemBase("ingot_electrum", true).setCreativeTab(Embers.resource_tab));
            items.add(plate_electrum = new ItemBase("plate_electrum", true).setCreativeTab(Embers.resource_tab));
            items.add(nugget_electrum = new ItemBase("nugget_electrum", true).setCreativeTab(Embers.resource_tab));
            if (ConfigTool.METAL_TOOL.ELECTRUM.register) {
                tool_mat_electrum = Util.createToolMaterial("electrum", ConfigTool.METAL_TOOL.ELECTRUM);
                tool_mat_electrum.setRepairItem(new ItemStack(ingot_electrum));
                items.add(pickaxe_electrum = new ItemPickaxeBase(tool_mat_electrum, "pickaxe_electrum", true).setCreativeTab(Embers.resource_tab));
                items.add(axe_electrum = new ItemAxeBase(tool_mat_electrum, "axe_electrum", true).setCreativeTab(Embers.resource_tab));
                items.add(shovel_electrum = new ItemShovelBase(tool_mat_electrum, "shovel_electrum", true).setCreativeTab(Embers.resource_tab));
                items.add(sword_electrum = new ItemSwordBase(tool_mat_electrum, "sword_electrum", true).setCreativeTab(Embers.resource_tab));
                items.add(hoe_electrum = new ItemHoeBase(tool_mat_electrum, "hoe_electrum", true).setCreativeTab(Embers.resource_tab));
            }
        }

        if (ConfigMaterial.NICKEL.mustLoad()) {
            items.add(ingot_nickel = new ItemBase("ingot_nickel", true).setCreativeTab(Embers.resource_tab));
            items.add(plate_nickel = new ItemBase("plate_nickel", true).setCreativeTab(Embers.resource_tab));
            items.add(nugget_nickel = new ItemBase("nugget_nickel", true).setCreativeTab(Embers.resource_tab));
            if (ConfigTool.METAL_TOOL.NICKEL.register) {
                tool_mat_nickel = Util.createToolMaterial("nickel", ConfigTool.METAL_TOOL.NICKEL);
                tool_mat_nickel.setRepairItem(new ItemStack(ingot_nickel));
                items.add(pickaxe_nickel = new ItemPickaxeBase(tool_mat_nickel, "pickaxe_nickel", true).setCreativeTab(Embers.resource_tab));
                items.add(axe_nickel = new ItemAxeBase(tool_mat_nickel, "axe_nickel", true).setCreativeTab(Embers.resource_tab));
                items.add(shovel_nickel = new ItemShovelBase(tool_mat_nickel, "shovel_nickel", true).setCreativeTab(Embers.resource_tab));
                items.add(sword_nickel = new ItemSwordBase(tool_mat_nickel, "sword_nickel", true).setCreativeTab(Embers.resource_tab));
                items.add(hoe_nickel = new ItemHoeBase(tool_mat_nickel, "hoe_nickel", true).setCreativeTab(Embers.resource_tab));
            }
        }

        if (ConfigMaterial.TIN.mustLoad()) {
            items.add(ingot_tin = new ItemBase("ingot_tin", true).setCreativeTab(Embers.resource_tab));
            items.add(plate_tin = new ItemBase("plate_tin", true).setCreativeTab(Embers.resource_tab));
            items.add(nugget_tin = new ItemBase("nugget_tin", true).setCreativeTab(Embers.resource_tab));
            if (ConfigTool.METAL_TOOL.TIN.register) {
                tool_mat_tin = Util.createToolMaterial("tin", ConfigTool.METAL_TOOL.TIN);
                tool_mat_tin.setRepairItem(new ItemStack(ingot_tin));
                items.add(pickaxe_tin = new ItemPickaxeBase(tool_mat_tin, "pickaxe_tin", true).setCreativeTab(Embers.resource_tab));
                items.add(axe_tin = new ItemAxeBase(tool_mat_tin, "axe_tin", true).setCreativeTab(Embers.resource_tab));
                items.add(shovel_tin = new ItemShovelBase(tool_mat_tin, "shovel_tin", true).setCreativeTab(Embers.resource_tab));
                items.add(sword_tin = new ItemSwordBase(tool_mat_tin, "sword_tin", true).setCreativeTab(Embers.resource_tab));
                items.add(hoe_tin = new ItemHoeBase(tool_mat_tin, "hoe_tin", true).setCreativeTab(Embers.resource_tab));
            }
        }

        tool_mat_copper.setRepairItem(new ItemStack(ingot_copper));
        tool_mat_silver.setRepairItem(new ItemStack(ingot_silver));
        tool_mat_lead.setRepairItem(new ItemStack(ingot_lead));
        tool_mat_dawnstone.setRepairItem(new ItemStack(ingot_dawnstone));
        tool_mat_tyrfing.setRepairItem(new ItemStack(dust_ash));

        armor_mat_ashen_cloak.repairMaterial = new ItemStack(ashen_cloth, 1);

        if (ConfigMaterial.ASTRALITE.isNotOff()) {
            items.add(ingot_astralite = new ItemBase("ingot_astralite", true).setCreativeTab(Embers.resource_tab));
            items.add(plate_astralite = new ItemBase("plate_astralite", true).setCreativeTab(Embers.resource_tab));
            items.add(nugget_astralite = new ItemBase("nugget_astralite", true).setCreativeTab(Embers.resource_tab));
        }
        if (ConfigMaterial.UMBERSTEEL.isNotOff()) {
            items.add(ingot_umber_steel = new ItemBase("ingot_umber_steel", true).setCreativeTab(Embers.resource_tab));
            items.add(plate_umber_steel = new ItemBase("plate_umber_steel", true).setCreativeTab(Embers.resource_tab));
            items.add(nugget_umber_steel = new ItemBase("nugget_umber_steel", true).setCreativeTab(Embers.resource_tab));
        }
        if (ConfigMain.enableWipStuff) {

            items.add(fulgurite = new ItemBase("fulgurite", true));
            items.add(aster = new ItemBase("aster", true));
            items.add(shard_aster = new ItemBase("shard_aster", true));
            items.add(alchemic_chunk = new ItemBase("alchemic_chunk", true));
            items.add(alchemic_cluster = new ItemBase("alchemic_cluster", true));
            items.add(alchemic_mote = new ItemBase("alchemic_mote", true));
            items.add(alchemic_piece = new ItemBase("alchemic_piece", true));
            items.add(alchemic_shard = new ItemBase("alchemic_shard", true));
            items.add(burst_emitter = new ItemBase("burst_emitter", true));
            items.add(crystal_lens = new ItemBase("crystal_lens", true));
            items.add(eyepiece = new ItemBase("eyepiece", true));
            items.add(glimmer_charm = new ItemBase("glimmer_charm", true));
            items.add(intelligent_eye = new ItemBase("intelligent_eye", true));
            items.add(karma_charm = new ItemBase("karma_charm", true));
            items.add(mass_caldera = new ItemBase("mass_caldera", true));
            items.add(radiant_crown = new ItemBase("radiant_crown", true));
            items.add(sigil_cross = new ItemBase("sigil_cross", true));
            items.add(sigil_empty = new ItemBase("sigil_empty", true));
            items.add(sigil_explosion = new ItemBase("sigil_explosion", true));
            items.add(sigil_eye = new ItemBase("sigil_eye", true));
            items.add(sigil_fork = new ItemBase("sigil_fork", true));
            items.add(sigil_home = new ItemBase("sigil_home", true));
            items.add(sigil_ring = new ItemBase("sigil_ring", true));
            items.add(sigil_skull = new ItemBase("sigil_skull", true));
            items.add(sigil_sun = new ItemBase("sigil_sun", true));
            items.add(silver_eye_closed = new ItemBase("silver_eye_closed", true));
            items.add(silver_eye_open = new ItemBase("silver_eye_open", true));
            items.add(truemetal_clump = new ItemBase("truemetal_clump", true));
            items.add(sulfur_dust = new ItemBase("sulfur_dust", true));

        }


        registerFluids();
        registerTileEntities();
        registerEntities();
        registerItemModifiers();

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

        if (CompatUtil.isBaublesIntegrationEnabled())
            BaublesIntegration.registerAll();
        if (CompatUtil.isMysticalMechanicsIntegrationEnabled())
            MysticalMechanicsIntegration.registerAll();

        UpgradeCatalyticPlug.registerBlacklistedTile(TileEntityBeamCannon.class);
    }

    private static void registerEntities() {
        int id = 0;

        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID + ":ember_packet"), EntityEmberPacket.class, "ember_packet", id++, Embers.instance, 64, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID + ":ember_projectile"), EntityEmberProjectile.class, "ember_projectile", id++, Embers.instance, 64, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID + ":ancient_golem"), EntityAncientGolem.class, "ancient_golem", id++, Embers.instance, 64, 1, true);
        EntityRegistry.registerEgg(new ResourceLocation(Embers.MODID + ":ancient_golem"), Misc.intColor(48, 38, 35), Misc.intColor(79, 66, 61));
        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID + ":ember_light"), EntityEmberLight.class, "ember_light", id++, Embers.instance, 64, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID, "magma_worm"), EntityMagmaWorm.class, "magma_worm", id++, Embers.instance, 128, 1, true);
        EntityRegistry.registerEgg(new ResourceLocation(Embers.MODID, "magma_worm"), Misc.intColor(48, 38, 35), Misc.intColor(79, 66, 61));
        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID, "magma_projectile"), EntityMagmaProjectile.class, "magma_projectile", id++, Embers.instance, 64, 1, true);
    }

    private static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityTank.class, Embers.MODID + ":tile_entity_tank");
        GameRegistry.registerTileEntity(TileEntityFluidPipe.class, Embers.MODID + ":tile_entity_pipe");
        GameRegistry.registerTileEntity(TileEntityFluidExtractor.class, Embers.MODID + ":tile_entity_pump");
        GameRegistry.registerTileEntity(TileEntityFurnaceTop.class, Embers.MODID + ":tile_entity_furnace_top");
        GameRegistry.registerTileEntity(TileEntityFurnaceBottom.class, Embers.MODID + ":tile_entity_furnace_bottom");
        GameRegistry.registerTileEntity(TileEntityEmitter.class, Embers.MODID + ":tile_entity_emitter");
        GameRegistry.registerTileEntity(TileEntityReceiver.class, Embers.MODID + ":tile_entity_receiver");
        GameRegistry.registerTileEntity(TileEntityCopperCell.class, Embers.MODID + ":tile_entity_copper_cell");
        GameRegistry.registerTileEntity(TileEntityItemPipe.class, Embers.MODID + ":tile_entity_item_pipe");
        GameRegistry.registerTileEntity(TileEntityItemExtractor.class, Embers.MODID + ":tile_entity_item_pump");
        GameRegistry.registerTileEntity(TileEntityBin.class, Embers.MODID + ":tile_entity_bin");
        GameRegistry.registerTileEntity(TileEntityStamper.class, Embers.MODID + ":tile_entity_stamper");
        GameRegistry.registerTileEntity(TileEntityStampBase.class, Embers.MODID + ":tile_entity_stamp_base");
        GameRegistry.registerTileEntity(TileEntityEmberBore.class, Embers.MODID + ":tile_entity_ember_bore");
        GameRegistry.registerTileEntity(TileEntityMechAccessor.class, Embers.MODID + ":tile_entity_mech_accessor");
        GameRegistry.registerTileEntity(TileEntityMechCore.class, Embers.MODID + ":tile_entity_mech_core");
        GameRegistry.registerTileEntity(TileEntityActivatorTop.class, Embers.MODID + ":tile_entity_activator_top");
        GameRegistry.registerTileEntity(TileEntityActivatorBottom.class, Embers.MODID + ":tile_entity_activator_bottom");
        GameRegistry.registerTileEntity(TileEntityMixerTop.class, Embers.MODID + ":tile_entity_mixer_top");
        GameRegistry.registerTileEntity(TileEntityMixerBottom.class, Embers.MODID + ":tile_entity_mixer_bottom");
        GameRegistry.registerTileEntity(TileEntityHeatCoil.class, Embers.MODID + ":tile_entity_heat_coil");
        GameRegistry.registerTileEntity(TileEntityDropper.class, Embers.MODID + ":tile_entity_dropper");
        GameRegistry.registerTileEntity(TileEntityLargeTank.class, Embers.MODID + ":tile_entity_large_tank");
        GameRegistry.registerTileEntity(TileEntityBeamSplitter.class, Embers.MODID + ":tile_entity_beam_splitter");
        GameRegistry.registerTileEntity(TileEntityRelay.class, Embers.MODID + ":tile_entity_relay");
        GameRegistry.registerTileEntity(TileEntityCrystalCell.class, Embers.MODID + ":tile_entity_crystal_cell");
        GameRegistry.registerTileEntity(TileEntityCharger.class, Embers.MODID + ":tile_entity_charger");
        GameRegistry.registerTileEntity(TileEntityCinderPlinth.class, Embers.MODID + ":tile_entity_cinder_plinth");
        GameRegistry.registerTileEntity(TileEntityKnowledgeTable.class, Embers.MODID + ":tile_entity_knowledge_table");
        GameRegistry.registerTileEntity(TileEntityAlchemyPedestal.class, Embers.MODID + ":tile_entity_alchemy_pedestal");
        GameRegistry.registerTileEntity(TileEntityAlchemyTablet.class, Embers.MODID + ":tile_entity_alchemy_tablet");
        GameRegistry.registerTileEntity(TileEntityItemTransfer.class, Embers.MODID + ":tile_entity_item_transfer");
        GameRegistry.registerTileEntity(TileEntityBeamCannon.class, Embers.MODID + ":tile_entity_beam_cannon");
        GameRegistry.registerTileEntity(TileEntityDawnstoneAnvil.class, Embers.MODID + ":tile_entity_dawnstone_anvil");
        GameRegistry.registerTileEntity(TileEntityAutoHammer.class, Embers.MODID + ":tile_entity_auto_hammer");
        GameRegistry.registerTileEntity(TileEntityItemVacuum.class, Embers.MODID + ":tile_entity_vacuum");
        GameRegistry.registerTileEntity(TileEntityBreaker.class, Embers.MODID + ":tile_entity_breaker");
        GameRegistry.registerTileEntity(TileEntitySeed.class, Embers.MODID + ":tile_entity_seed");
        GameRegistry.registerTileEntity(TileEntitySeedNew.class, Embers.MODID + ":tile_entity_seed_new");
        GameRegistry.registerTileEntity(TileEntityEmberInjector.class, Embers.MODID + ":tile_entity_ember_injector");
        GameRegistry.registerTileEntity(TileEntityBoilerBottom.class, Embers.MODID + ":tile_entity_boiler_bottom");
        GameRegistry.registerTileEntity(TileEntityBoilerTop.class, Embers.MODID + ":tile_entity_boiler_top");
        GameRegistry.registerTileEntity(TileEntityReactor.class, Embers.MODID + ":tile_entity_reactor");
        GameRegistry.registerTileEntity(TileEntityCombustor.class, Embers.MODID + ":tile_entity_combustor");
        GameRegistry.registerTileEntity(TileEntityCatalyzer.class, Embers.MODID + ":tile_entity_catalyzer");
        GameRegistry.registerTileEntity(TileEntityFieldChart.class, Embers.MODID + ":tile_entity_field_chart");
        GameRegistry.registerTileEntity(TileEntityPulser.class, Embers.MODID + ":tile_entity_pulser");
        GameRegistry.registerTileEntity(TileEntityInfernoForge.class, Embers.MODID + ":tile_entity_inferno_forge");
        GameRegistry.registerTileEntity(TileEntityInfernoForgeOpening.class, Embers.MODID + ":tile_entity_inferno_forge_opening");
        GameRegistry.registerTileEntity(TileEntityCreativeEmberSource.class, Embers.MODID + ":tile_entity_creative_ember_source");
        GameRegistry.registerTileEntity(TileEntityPumpBottom.class, Embers.MODID + ":tile_entity_pump_bottom");
        GameRegistry.registerTileEntity(TileEntityPumpTop.class, Embers.MODID + ":tile_entity_pump_top");
        GameRegistry.registerTileEntity(TileEntityCatalyticPlug.class, Embers.MODID + ":tile_entity_catalytic_plug");
        GameRegistry.registerTileEntity(TileEntityEmberFunnel.class, Embers.MODID + ":tile_entity_ember_funnel");
        GameRegistry.registerTileEntity(TileEntityMiniBoiler.class, Embers.MODID + ":tile_entity_mini_boiler");
        GameRegistry.registerTileEntity(TileEntityReactionChamber.class, Embers.MODID + ":tile_entity_reaction_chamber");
        GameRegistry.registerTileEntity(TileEntityEmberGauge.class, Embers.MODID + ":tile_entity_ember_gauge");
        GameRegistry.registerTileEntity(TileEntityFluidGauge.class, Embers.MODID + ":tile_entity_fluid_gauge");
        //GameRegistry.registerTileEntity(TileEntityItemGauge.class, Embers.MODID+":tile_entity_item_gauge");
        GameRegistry.registerTileEntity(TileEntityFluidTransfer.class, Embers.MODID + ":tile_entity_fluid_transfer");
        GameRegistry.registerTileEntity(TileEntityStirling.class, Embers.MODID + ":tile_entity_stirling");
        GameRegistry.registerTileEntity(TileEntityEmberSiphon.class, Embers.MODID + ":tile_entity_ember_siphon");
        GameRegistry.registerTileEntity(TileEntityClockworkAttenuator.class, Embers.MODID + ":tile_entity_clockwork_attenuator");
        GameRegistry.registerTileEntity(TileEntityArchaicGeysir.class, Embers.MODID + ":tile_entity_archaic_geysir");
        GameRegistry.registerTileEntity(TileEntityStoneValve.class, Embers.MODID + ":tile_entity_stone_valve");
        GameRegistry.registerTileEntity(TileEntityGeoSeparator.class, Embers.MODID + ":tile_entity_geo_separator");
        GameRegistry.registerTileEntity(TileEntityItemRequisition.class, Embers.MODID + ":tile_entity_item_request");
        GameRegistry.registerTileEntity(TileEntityEmberPipe.class, Embers.MODID + ":tile_entity_ember_pipe");
        GameRegistry.registerTileEntity(TileEntityTurret.class, Embers.MODID + ":tile_entity_turret");
        GameRegistry.registerTileEntity(TileEntityFluidDropper.class, Embers.MODID + ":tile_entity_fluid_dropper");
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

    public static void registerItemModifiers() {
        EmbersAPI.CORE = new ModifierCore();
        EmbersAPI.SUPERHEATER = new ModifierSuperheater();
        EmbersAPI.JET_AUGMENT = new ModifierCinderJet();
        EmbersAPI.CASTER_ORB = new ModifierCasterOrb();
        EmbersAPI.RESONATING_BELL = new ModifierResonatingBell();
        EmbersAPI.BLASTING_CORE = new ModifierBlastingCore();
        EmbersAPI.FLAME_BARRIER = new ModifierFlameBarrier();
        EmbersAPI.ELDRITCH_INSIGNIA = new ModifierEldritchInsignia();
        EmbersAPI.INTELLIGENT_APPARATUS = new ModifierIntelligentApparatus();
        EmbersAPI.DIFFRACTION = new ModifierDiffraction();
        EmbersAPI.FOCAL_LENS = new ModifierFocalLens();
        EmbersAPI.TINKER_LENS = new ModifierTinkerLens("tinker_lens", false);
        EmbersAPI.ANTI_TINKER_LENS = new ModifierTinkerLens("anti_tinker_lens", true);
        EmbersAPI.SHIFTING_SCALES = new ModifierShiftingScales();
        EmbersAPI.WINDING_GEARS = new ModifierWindingGears();

        EmbersAPI.registerModifier(ancient_motive_core, EmbersAPI.CORE);
        EmbersAPI.registerModifier(superheater, EmbersAPI.SUPERHEATER);
        EmbersAPI.registerModifier(jet_augment, EmbersAPI.JET_AUGMENT);
        EmbersAPI.registerModifier(caster_orb, EmbersAPI.CASTER_ORB);
        EmbersAPI.registerModifier(resonating_bell, EmbersAPI.RESONATING_BELL);
        EmbersAPI.registerModifier(blasting_core, EmbersAPI.BLASTING_CORE);
        EmbersAPI.registerModifier(flame_barrier, EmbersAPI.FLAME_BARRIER);
        EmbersAPI.registerModifier(eldritch_insignia, EmbersAPI.ELDRITCH_INSIGNIA);
        EmbersAPI.registerModifier(intelligent_apparatus, EmbersAPI.INTELLIGENT_APPARATUS);
        EmbersAPI.registerModifier(diffraction_barrel, EmbersAPI.DIFFRACTION);
        EmbersAPI.registerModifier(focal_lens, EmbersAPI.FOCAL_LENS);
        EmbersAPI.registerModifier(tinker_lens, EmbersAPI.TINKER_LENS);
        EmbersAPI.registerModifier(anti_tinker_lens, EmbersAPI.ANTI_TINKER_LENS);
        EmbersAPI.registerModifier(shifting_scales, EmbersAPI.SHIFTING_SCALES);
        EmbersAPI.registerModifier(winding_gears, EmbersAPI.WINDING_GEARS);
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        BlockRegister.INSTANCE.register(event);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        for (Item i : items) {
            event.getRegistry().register(i);
        }
        for (Block b : blocks) {
            if (b instanceof IBlock) {
                Item itemBlock = ((IBlock) b).getItemBlock();
                if (itemBlock != null)
                    event.getRegistry().register(itemBlock);
            }
        }
    }

    public static void registerFluids() {
        FluidRegistry.registerFluid(fluid_steam = new FluidSteam());

        FluidRegistry.registerFluid(fluid_crude_oil = new FluidMoltenMetal("oil_soul", "oil_soul"));
        FluidRegistry.addBucketForFluid(fluid_crude_oil);

        FluidRegistry.registerFluid(fluid_oil = new FluidOilDwarven());
        FluidRegistry.addBucketForFluid(fluid_oil);

        FluidRegistry.registerFluid(fluid_gas = new FluidGas());

        FluidRegistry.registerFluid(fluid_molten_iron = new FluidMoltenIron());
        FluidRegistry.addBucketForFluid(fluid_molten_iron);

        FluidRegistry.registerFluid(fluid_molten_gold = new FluidMoltenGold());
        FluidRegistry.addBucketForFluid(fluid_molten_gold);

        FluidRegistry.registerFluid(fluid_molten_lead = new FluidMoltenLead());
        FluidRegistry.addBucketForFluid(fluid_molten_lead);

        FluidRegistry.registerFluid(fluid_molten_copper = new FluidMoltenCopper());
        FluidRegistry.addBucketForFluid(fluid_molten_copper);

        FluidRegistry.registerFluid(fluid_molten_silver = new FluidMoltenSilver());
        FluidRegistry.addBucketForFluid(fluid_molten_silver);

        FluidRegistry.registerFluid(fluid_molten_dawnstone = new FluidMoltenDawnstone());
        FluidRegistry.addBucketForFluid(fluid_molten_dawnstone);

        FluidRegistry.registerFluid(fluid_molten_tin = new FluidMoltenTin());
        FluidRegistry.addBucketForFluid(fluid_molten_tin);

        FluidRegistry.registerFluid(fluid_molten_aluminum = new FluidMoltenAluminum());
        FluidRegistry.addBucketForFluid(fluid_molten_aluminum);

        FluidRegistry.registerFluid(fluid_molten_nickel = new FluidMoltenNickel());
        FluidRegistry.addBucketForFluid(fluid_molten_nickel);

        FluidRegistry.registerFluid(fluid_molten_bronze = new FluidMoltenBronze());
        FluidRegistry.addBucketForFluid(fluid_molten_bronze);

        FluidRegistry.registerFluid(fluid_molten_electrum = new FluidMoltenElectrum());
        FluidRegistry.addBucketForFluid(fluid_molten_electrum);

        FluidRegistry.registerFluid(fluid_alchemical_redstone = new FluidMoltenMetal("alchemical_redstone", "alchemic_slurry"));
        FluidRegistry.addBucketForFluid(fluid_alchemical_redstone);
    }

    @SideOnly(Side.CLIENT)
    public static void registerColorHandlers() {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemEmberJar.ColorHandler(), ember_jar);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemEmberCartridge.ColorHandler(), ember_cartridge);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemTyrfing.ColorHandler(), tyrfing);
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

        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i) instanceof IModeledBlock) {
                ((IModeledBlock) blocks.get(i)).initModel();
            }
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof IModeledItem) {
                ((IModeledItem) items.get(i)).initModel();
            }
        }
    }

    @SubscribeEvent
    public void init(RegistryEvent.Register<DataSerializerEntry> event) {
        event.getRegistry().register(new DataSerializerEntry(ExtraSerializers.FLOAT_ARRAY).setRegistryName(Embers.MODID, "serializer_float_array"));
    }
}
