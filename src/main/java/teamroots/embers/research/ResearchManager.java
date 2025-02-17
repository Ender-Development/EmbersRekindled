package teamroots.embers.research;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamroots.embers.Embers;
import teamroots.embers.compat.BaublesIntegration;
import teamroots.embers.compat.MysticalMechanicsIntegration;
import teamroots.embers.compat.Util;
import teamroots.embers.item.ItemEmberStorage;
import teamroots.embers.network.PacketHandler;
import teamroots.embers.network.message.MessageResearchData;
import teamroots.embers.network.message.MessageResearchTick;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.FluidRegister;
import teamroots.embers.register.ItemRegister;
import teamroots.embers.research.capability.DefaultResearchCapability;
import teamroots.embers.research.capability.IResearchCapability;
import teamroots.embers.research.capability.ResearchCapabilityProvider;
import teamroots.embers.research.subtypes.ResearchFakePage;
import teamroots.embers.research.subtypes.ResearchShowItem;
import teamroots.embers.research.subtypes.ResearchSwitchCategory;
import teamroots.embers.util.Vec2i;

import java.util.*;

import static teamroots.embers.research.subtypes.ResearchShowItem.DisplayItem;

public class ResearchManager {
    public static final ResourceLocation PLAYER_RESEARCH = new ResourceLocation(Embers.MODID, "research");
    public static final ResourceLocation PAGE_ICONS = new ResourceLocation(Embers.MODID, "textures/gui/codex_pageicons.png");
    public static final double PAGE_ICON_SIZE = 0.09375;
    public static List<ResearchCategory> researches = new ArrayList<ResearchCategory>();

    public static ResearchBase dials, boiler, mini_boiler, ores, hammer, ancient_golem, gauge, caminite, bore, crystals, activator, tinker_lens, reaction_chamber,//WORLD
            copper_cell, emitters, dawnstone, melter, stamper, mixer, breaker, hearth_coil, access, pump, clockwork_attenuator, geo_separator, //MECHANISMS
            beam_cannon, pulser, splitter, crystal_cell, cinder_staff, clockwork_tools, blazing_ray, charger, jars, alchemy, cinder_plinth, aspecti, catalytic_plug, ember_siphon, //METALLURGY
            tyrfing, waste, cluster, ashen_cloak, inflictor, materia, field_chart, glimmer, metallurgic_dust, //ALCHEMY
            modifiers, inferno_forge, heat, dawnstone_anvil, autohammer, dismantling //SMITHING
            ;
    public static ResearchBase pipes, tank, bin, dropper, reservoir, vacuum, transfer, golem_eye, requisition; //PIPES
    public static ResearchBase adhesive, hellish_synthesis, archaic_brick, motive_core, dwarven_oil; //SIMPLE ALCHEMY
    public static ResearchBase wildfire, combustor, catalyzer, reactor, injector, stirling, ember_pipe; //WILDFIRE
    public static ResearchBase superheater, caster_orb, resonating_bell, blasting_core, /*core_stone,*/
            winding_gears; //WEAPON AUGMENTS
    public static ResearchBase cinder_jet, eldritch_insignia, intelligent_apparatus, flame_barrier, tinker_lens_augment, anti_tinker_lens, shifting_scales; //ARMOR_AUGMENTS
    public static ResearchBase diffraction_barrel, focal_lens; //PROJECTILE_AUGMENTS
    public static ResearchBase cost_reduction, mantle_bulb, explosion_charm, nonbeliever_amulet, ashen_amulet, dawnstone_mail, explosion_pedestal; //BAUBLE
    public static ResearchBase gearbox, mergebox, axle_iron, gear_iron, actuator, steam_engine; //MECHANICAL POWER

    public static ResearchCategory categoryWorld;
    public static ResearchCategory categoryMechanisms;
    public static ResearchCategory categoryMetallurgy;
    public static ResearchCategory categoryAlchemy;
    public static ResearchCategory categorySmithing;

    public static ResearchCategory subCategoryPipes;
    public static ResearchCategory subCategoryWeaponAugments;
    public static ResearchCategory subCategoryArmorAugments;
    public static ResearchCategory subCategoryProjectileAugments;
    public static ResearchCategory subCategoryMiscAugments;
    public static ResearchCategory subCategoryMechanicalPower;
    public static ResearchCategory subCategoryBaubles;
    public static ResearchCategory subCategorySimpleAlchemy;
    public static ResearchCategory subCategoryWildfire;

    public static boolean isPathToLock(ResearchBase entry) {
        for (ResearchCategory category : researches) {
            for (ResearchBase target : category.prerequisites) {
                if (isPathTowards(entry, target))
                    return true;
            }
        }
        return false;
    }

    public static boolean isPathTowards(ResearchBase entry, ResearchBase target) {
        if (entry.isPathTowards(target))
            return true;
        for (ResearchBase ancestor : target.ancestors) {
            if (isPathTowards(entry, ancestor))
                return true;
        }
        return false;
    }

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityPlayerMP && !event.getWorld().isRemote) {
            EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
            sendResearchData(player);
        }
    }

    public static void sendResearchData(EntityPlayerMP player) {
        IResearchCapability research = getPlayerResearch(player);
        if (research != null) {
            PacketHandler.INSTANCE.sendTo(new MessageResearchData(research.getCheckmarks()), player);
        }
    }

    public static void receiveResearchData(Map<String, Boolean> checkmarks) {
        for (ResearchBase research : getAllResearch()) {
            Boolean checked = checkmarks.get(research.name);
            if (checked != null)
                research.check(checked);
        }
    }

    public static void sendCheckmark(ResearchBase research, boolean checked) {
        PacketHandler.INSTANCE.sendToServer(new MessageResearchTick(research.name, checked));
    }

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer && !event.getCapabilities().containsKey(PLAYER_RESEARCH)) {
            event.addCapability(PLAYER_RESEARCH, new ResearchCapabilityProvider(new DefaultResearchCapability()));
        }
    }

    @SubscribeEvent
    public void onClone(PlayerEvent.Clone event) {
        IResearchCapability oldCap = getPlayerResearch(event.getOriginal());
        IResearchCapability newCap = getPlayerResearch(event.getEntityPlayer());
        if (oldCap != null && newCap != null) {
            NBTTagCompound compound = new NBTTagCompound();
            oldCap.writeToNBT(compound);
            newCap.readFromNBT(compound);
        }
    }

    public static IResearchCapability getPlayerResearch(EntityPlayer player) {
        if (player.hasCapability(ResearchCapabilityProvider.researchCapability, null)) {
            //Object o = player.getCapability(ResearchCapabilityProvider.researchCapability,null);
            return player.getCapability(ResearchCapabilityProvider.researchCapability, null);
        }
        return null;
    }

    public static List<ResearchBase> getAllResearch() {
        Set<ResearchBase> result = new HashSet<>();
        for (ResearchCategory category : researches) {
            category.getAllResearch(result);
        }
        return new ArrayList<>(result);
    }

    public static Map<ResearchBase, Integer> findByTag(String match) {
        HashMap<ResearchBase, Integer> result = new HashMap<>();
        HashSet<ResearchCategory> categories = new HashSet<>();
        if (!match.isEmpty())
            for (ResearchCategory category : researches) {
                category.findByTag(match, result, categories);
            }
        return result;
    }

    public static void initResearches() {
        categoryWorld = new ResearchCategory("world", 16);
        categoryMechanisms = new ResearchCategory("mechanisms", 32);
        categoryMetallurgy = new ResearchCategory("metallurgy", 48);
        categoryAlchemy = new ResearchCategory("alchemy", 64);
        categorySmithing = new ResearchCategory("smithing", 80);
        Vec2i[] ringPositions = {new Vec2i(1, 1), new Vec2i(0, 3), new Vec2i(0, 5), new Vec2i(1, 7), new Vec2i(11, 7), new Vec2i(12, 5), new Vec2i(12, 3), new Vec2i(11, 1), new Vec2i(4, 1), new Vec2i(2, 4), new Vec2i(4, 7), new Vec2i(8, 7), new Vec2i(10, 4), new Vec2i(8, 1)};
        subCategoryWeaponAugments = new ResearchCategory("weapon_augments", 0).pushGoodLocations(ringPositions);
        subCategoryArmorAugments = new ResearchCategory("armor_augments", 0).pushGoodLocations(ringPositions);
        subCategoryProjectileAugments = new ResearchCategory("projectile_augments", 0).pushGoodLocations(ringPositions);
        subCategoryMiscAugments = new ResearchCategory("misc_augments", 0).pushGoodLocations(ringPositions);
        subCategoryPipes = new ResearchCategory("pipes", 0);
        subCategoryMechanicalPower = new ResearchCategory("mystical_mechanics", 0);
        subCategoryBaubles = new ResearchCategory("baubles", 0);
        subCategorySimpleAlchemy = new ResearchCategory("simple_alchemy", 0);
        subCategoryWildfire = new ResearchCategory("wildfire", 0);

        //WORLD
        ores = new ResearchBase("ores", new ItemStack(Blocks.IRON_ORE), 0, 7);
        hammer = new ResearchBase("hammer", new ItemStack(ItemRegister.TINKER_HAMMER), 0, 3).addAncestor(ores);
        ancient_golem = new ResearchBase("ancient_golem", ItemStack.EMPTY, 0, 0).setIconBackground(PAGE_ICONS, PAGE_ICON_SIZE * 1, PAGE_ICON_SIZE * 0);
        gauge = new ResearchBase("gauge", new ItemStack(ItemRegister.EMBER_DETECTOR), 4, 3).addAncestor(ores);
        caminite = new ResearchBase("caminite", new ItemStack(ItemRegister.BRICK_CAMINITE), 6, 7);
        bore = new ResearchBase("bore", new ItemStack(BlockRegister.EMBER_BORE), 9, 0).addAncestor(hammer).addAncestor(caminite);
        crystals = new ResearchBase("crystals", new ItemStack(ItemRegister.CRYSTAL_EMBER), 12, 3).addAncestor(bore);
        activator = new ResearchBase("activator", new ItemStack(BlockRegister.EMBER_ACTIVATOR), 9, 5).addAncestor(crystals);
        boiler = new ResearchBase("boiler", new ItemStack(BlockRegister.BOILER), 9, 7).addAncestor(activator);
        mini_boiler = new ResearchBase("mini_boiler", new ItemStack(BlockRegister.MINI_BOILER), 11, 7).addAncestor(activator);
        dials = new ResearchBase("dials", new ItemStack(BlockRegister.EMBER_GAUGE), 5, 5).addAncestor(hammer);
        tinker_lens = new ResearchBase("tinker_lens", new ItemStack(ItemRegister.TINKER_LENS), 4, 7).addAncestor(hammer);
        reaction_chamber = new ResearchBase("reaction_chamber", new ItemStack(BlockRegister.REACTION_CHAMBER), 12, 5).addAncestor(mini_boiler);

        pipes = new ResearchBase("pipes", new ItemStack(BlockRegister.PUMP), 2, 4);
        pipes.addPage(new ResearchShowItem("routing", ItemStack.EMPTY, 0, 0).addItem(new DisplayItem(new ItemStack(BlockRegister.ITEM_PIPE), new ItemStack(BlockRegister.PIPE))));
        pipes.addPage(new ResearchShowItem("valves", ItemStack.EMPTY, 0, 0).addItem(new DisplayItem(new ItemStack(BlockRegister.ITEM_PUMP), new ItemStack(BlockRegister.PUMP))));
        pipes.addPage(new ResearchShowItem("pipe_tools", ItemStack.EMPTY, 0, 0).addItem(new DisplayItem(new ItemStack(ItemRegister.TINKER_HAMMER), new ItemStack(Items.STICK))));
        golem_eye = new ResearchBase("golem_eye", new ItemStack(ItemRegister.GOLEMS_EYE), 5, 7)
                .addPage(new ResearchShowItem("filter_existing", new ItemStack(BlockRegister.ITEM_REQUEST), 0, 0).addItem(new DisplayItem(new ItemStack(BlockRegister.ITEM_REQUEST))))
                .addPage(new ResearchShowItem("filter_not_existing", new ItemStack(BlockRegister.DAWNSTONE_ANVIL), 0, 0).addItem(new DisplayItem(new ItemStack(BlockRegister.DAWNSTONE_ANVIL))));
        transfer = new ResearchBase("transfer", new ItemStack(BlockRegister.ITEM_TRANSFER), 5, 5).addAncestor(pipes).addAncestor(golem_eye);
        transfer.addPage(new ResearchShowItem("fluid_transfer", ItemStack.EMPTY, 0, 0).addItem(new DisplayItem(new ItemStack(BlockRegister.FLUID_TRANSFER))));
        vacuum = new ResearchBase("vacuum", new ItemStack(BlockRegister.VACUUM), 8, 4).addPage(new ResearchBase("vacuum_transfer", ItemStack.EMPTY, 0, 0)).addAncestor(pipes);
        dropper = new ResearchBase("dropper", new ItemStack(BlockRegister.ITEM_DROPPER), 8, 6).addAncestor(pipes);
        bin = new ResearchBase("bin", new ItemStack(BlockRegister.BIN), 4, 3).addAncestor(pipes);
        tank = new ResearchBase("tank", new ItemStack(BlockRegister.BLOCK_TANK), 3, 1).addAncestor(pipes);
        reservoir = new ResearchBase("reservoir", new ItemStack(BlockRegister.LARGE_TANK), 6, 0).addAncestor(tank)
                .addPage(new ResearchShowItem("reservoir_valve", new ItemStack(BlockRegister.STONE_VALVE), 0, 0).addItem(new DisplayItem(new ItemStack(BlockRegister.STONE_VALVE))));
        requisition = new ResearchBase("requisition", new ItemStack(BlockRegister.ITEM_REQUEST), 3, 6).addAncestor(pipes).addAncestor(golem_eye);

        //MECHANISMS
        emitters = new ResearchShowItem("emitters", new ItemStack(BlockRegister.EMBER_EMITTER), 0, 2).addItem(new DisplayItem(new ItemStack(BlockRegister.EMBER_EMITTER)))
                .addPage(new ResearchShowItem("receivers", new ItemStack(BlockRegister.EMBER_RECEIVER), 0, 0).addItem(new DisplayItem(new ItemStack(BlockRegister.EMBER_RECEIVER))))
                .addPage(new ResearchShowItem("linking", ItemStack.EMPTY, 0, 0).addItem(new DisplayItem(new ItemStack(BlockRegister.EMBER_RECEIVER), new ItemStack(ItemRegister.TINKER_HAMMER), new ItemStack(BlockRegister.EMBER_EMITTER))));
        melter = new ResearchBase("melter", new ItemStack(BlockRegister.BLOCK_FURNACE), 2, 0).addAncestor(emitters);
        geo_separator = new ResearchBase("geo_separator", new ItemStack(BlockRegister.GEO_SEPARATOR), 0, 0).addAncestor(melter);
        stamper = new ResearchBase("stamper", new ItemStack(BlockRegister.STAMPER), 2, 4).addAncestor(melter).addAncestor(emitters);
        access = new ResearchBase("access", new ItemStack(BlockRegister.MECH_CORE), 7, 5).addAncestor(stamper);
        hearth_coil = new ResearchBase("hearth_coil", new ItemStack(BlockRegister.HEAT_COIL), 10, 1).addAncestor(access);
        mixer = new ResearchBase("mixer", new ItemStack(BlockRegister.MIXER), 5, 2).addAncestor(stamper).addAncestor(melter);
        pump = new ResearchBase("pump", new ItemStack(BlockRegister.MECHANICAL_PUMP), 8, 0).addAncestor(mixer);
        breaker = new ResearchBase("breaker", new ItemStack(BlockRegister.BREAKER), 4, 7).addAncestor(stamper);
        dawnstone = new ResearchBase("dawnstone", new ItemStack(ItemRegister.INGOT_DAWNSTONE), 11, 4).addAncestor(mixer);
        copper_cell = new ResearchBase("copper_cell", new ItemStack(BlockRegister.COPPER_CELL), 0, 5).addAncestor(emitters);
        clockwork_attenuator = new ResearchBase("clockwork_attenuator", new ItemStack(BlockRegister.CLOCKWORK_ATTENUATOR), 12, 7);

        //METALLURGY
        crystal_cell = new ResearchBase("crystal_cell", new ItemStack(BlockRegister.CRYSTAL_CELL), 0, 1);
        pulser = new ResearchShowItem("pulser", new ItemStack(BlockRegister.EMBER_PULSER), 0, 3.5).addItem(new DisplayItem(new ItemStack(BlockRegister.EMBER_PULSER))).addAncestor(crystal_cell)
                .addPage(new ResearchShowItem("ember_funnel", new ItemStack(BlockRegister.EMBER_FUNNEL), 0, 0).addItem(new DisplayItem(new ItemStack(BlockRegister.EMBER_FUNNEL))));
        charger = new ResearchBase("charger", new ItemStack(BlockRegister.CHARGER), 4, 0);
        ember_siphon = new ResearchBase("ember_siphon", new ItemStack(BlockRegister.EMBER_SIPHON), 2, 0).addAncestor(ResearchManager.charger);
        ItemStack fullJar = ((ItemEmberStorage) ItemRegister.EMBER_JAR).withFill(((ItemEmberStorage) ItemRegister.EMBER_JAR).getCapacity());
        jars = new ResearchBase("jars", fullJar, 7, 1).addAncestor(charger);
        clockwork_tools = new ResearchBase("clockwork_tools", new ItemStack(ItemRegister.AXE_CLOCKWORK), 2, 2).addAncestor(jars)
                .addPage(new ResearchShowItem("clockwork_pickaxe", ItemStack.EMPTY, 0, 0).addItem(new DisplayItem(new ItemStack(ItemRegister.PICKAXE_CLOCKWORK))))
                .addPage(new ResearchShowItem("clockwork_hammer", ItemStack.EMPTY, 0, 0).addItem(new DisplayItem(new ItemStack(ItemRegister.GRANDHAMMER))))
                .addPage(new ResearchShowItem("clockwork_axe", ItemStack.EMPTY, 0, 0).addItem(new DisplayItem(new ItemStack(ItemRegister.AXE_CLOCKWORK))));
        //pulser = new ResearchBase("pulser", new ItemStack(RegistryManager.ember_pulser), 2, 5);
        splitter = new ResearchBase("splitter", new ItemStack(BlockRegister.BEAM_SPLITTER), 0, 6).addAncestor(pulser);
        cinder_staff = new ResearchBase("cinder_staff", new ItemStack(ItemRegister.STAFF_EMBER), 4, 4).addAncestor(jars);
        blazing_ray = new ResearchBase("blazing_ray", new ItemStack(ItemRegister.IGNITION_CANNON), 6, 5).addAncestor(jars);
        aspecti = new ResearchBase("aspecti", new ItemStack(ItemRegister.ASPECTUS_DAWNSTONE), 12, 1);
        cinder_plinth = new ResearchBase("cinder_plinth", new ItemStack(BlockRegister.CINDER_PLINTH), 9, 0);
        beam_cannon = new ResearchBase("beam_cannon", new ItemStack(BlockRegister.BEAM_CANNON), 9, 7);
        alchemy = new ResearchBase("alchemy", new ItemStack(BlockRegister.ALCHEMY_TABLET), 9, 4).addAncestor(cinder_plinth).addAncestor(aspecti).addAncestor(beam_cannon);
        catalytic_plug = new ResearchBase("catalytic_plug", new ItemStack(BlockRegister.CATALYTIC_PLUG), 12, 5).addAncestor(ResearchManager.alchemy);

        //TRANSMUTATION
        waste = new ResearchBase("waste", new ItemStack(ItemRegister.ALCHEMIC_WASTE), 6, 2);
        materia = new ResearchBase("materia", new ItemStack(ItemRegister.ISOLATED_MATERIA), 6, 5).addAncestor(waste);
        cluster = new ResearchBase("cluster", new ItemStack(ItemRegister.EMBER_CLUSTER), 3, 4).addAncestor(waste);
        ashen_cloak = new ResearchShowItem("ashen_cloak", new ItemStack(ItemRegister.ASHEN_CLOAK_CHEST), 9, 4).addItem(new DisplayItem(new ItemStack(ItemRegister.ASHEN_CLOAK_HEAD), new ItemStack(ItemRegister.ASHEN_CLOAK_CHEST), new ItemStack(ItemRegister.ASHEN_CLOAK_LEGS), new ItemStack(ItemRegister.ASHEN_CLOAK_BOOTS))).addAncestor(waste);
        field_chart = new ResearchBase("field_chart", new ItemStack(BlockRegister.FIELD_CHART), 0, 5).addAncestor(cluster);
        inflictor = new ResearchBase("inflictor", new ItemStack(ItemRegister.INFLICTOR_GEM), 11, 7).addAncestor(ashen_cloak);
        tyrfing = new ResearchBase("tyrfing", new ItemStack(ItemRegister.TYRFING), 8, 6).addAncestor(waste);
        glimmer = new ResearchBase("glimmer", new ItemStack(ItemRegister.GLIMMER_SHARD), 9, 0).addAncestor(waste);
        metallurgic_dust = new ResearchBase("metallurgic_dust", new ItemStack(ItemRegister.DUST_METALLURGIC), 0, 2).addAncestor(waste);

        adhesive = new ResearchBase("adhesive", new ItemStack(ItemRegister.ADHESIVE), 10, 1);
        hellish_synthesis = new ResearchBase("hellish_synthesis", new ItemStack(Blocks.NETHERRACK), 2, 1);
        archaic_brick = new ResearchBase("archaic_brick", new ItemStack(ItemRegister.ARCHAIC_BRICK), 5, 2).addAncestor(hellish_synthesis);
        motive_core = new ResearchBase("motive_core", new ItemStack(ItemRegister.ANCIENT_MOTIVE_CORE), 4, 4).addAncestor(archaic_brick);
        dwarven_oil = new ResearchBase("dwarven_oil", FluidUtil.getFilledBucket(new FluidStack(FluidRegister.FLUID_OIL, Fluid.BUCKET_VOLUME)), 1, 4).addAncestor(hellish_synthesis);

        wildfire = new ResearchBase("wildfire", new ItemStack(ItemRegister.WILDFIRE_CORE), 1, 5);
        injector = new ResearchBase("injector", new ItemStack(BlockRegister.EMBER_INJECTOR), 0, 7).addAncestor(wildfire)
                .addPage(new ResearchShowItem("crystal_level", ItemStack.EMPTY, 0, 0)
                        .addItem(new DisplayItem(new ItemStack(BlockRegister.SEED_IRON), new ItemStack(BlockRegister.SEED_GOLD), new ItemStack(BlockRegister.SEED_COPPER), new ItemStack(BlockRegister.SEED_TIN)))
                        .addItem(new DisplayItem(new ItemStack(BlockRegister.SEED_SILVER), new ItemStack(BlockRegister.SEED_LEAD), new ItemStack(BlockRegister.SEED_NICKEL), new ItemStack(BlockRegister.SEED_ALUMINUM))));
        combustor = new ResearchBase("combustor", new ItemStack(BlockRegister.COMBUSTOR), 6, 5).addAncestor(wildfire);
        combustor.addPage(new ResearchShowItem("empty", ItemStack.EMPTY, 0, 0)
                .addItem(new DisplayItem("combustor_coal", new ItemStack(Items.COAL)))
                .addItem(new DisplayItem("combustor_nether_brick", new ItemStack(Items.NETHERBRICK)))
                .addItem(new DisplayItem("combustor_blaze_powder", new ItemStack(Items.BLAZE_POWDER)))
        );
        catalyzer = new ResearchBase("catalyzer", new ItemStack(BlockRegister.CATALYZER), 5, 7).addAncestor(wildfire);
        catalyzer.addPage(new ResearchShowItem("empty", ItemStack.EMPTY, 0, 0)
                .addItem(new DisplayItem("catalyzer_redstone", new ItemStack(Items.REDSTONE)))
                .addItem(new DisplayItem("catalyzer_gunpowder", new ItemStack(Items.GUNPOWDER)))
                .addItem(new DisplayItem("catalyzer_glowstone", new ItemStack(Items.GLOWSTONE_DUST)))
        );
        reactor = new ResearchBase("reactor", new ItemStack(BlockRegister.REACTOR), 9, 7).addAncestor(combustor).addAncestor(catalyzer);
        stirling = new ResearchBase("stirling", new ItemStack(BlockRegister.STIRLING), 0, 2).addAncestor(ResearchManager.wildfire);
        ember_pipe = new ResearchBase("ember_pipe", new ItemStack(BlockRegister.EMBER_PIPE), 12, 6).addAncestor(ResearchManager.reactor);

        //SMITHING
        dawnstone_anvil = new ResearchBase("dawnstone_anvil", new ItemStack(BlockRegister.DAWNSTONE_ANVIL), 12, 7);
        autohammer = new ResearchBase("autohammer", new ItemStack(BlockRegister.AUTO_HAMMER), 9, 5).addAncestor(dawnstone_anvil);
        heat = new ResearchBase("heat", new ItemStack(ItemRegister.CRYSTAL_EMBER), 7, 7).addAncestor(dawnstone_anvil);
        modifiers = new ResearchBase("modifiers", new ItemStack(ItemRegister.ANCIENT_MOTIVE_CORE), 5, 7).addAncestor(heat);
        dismantling = new ResearchBase("dismantling", ItemStack.EMPTY, 3, 5).setIconBackground(PAGE_ICONS, PAGE_ICON_SIZE * 2, PAGE_ICON_SIZE * 0).addAncestor(modifiers);
        inferno_forge = new ResearchBase("inferno_forge", new ItemStack(BlockRegister.INFERNO_FORGE), 6, 4).addAncestor(heat);

        superheater = new ResearchBase("superheater", new ItemStack(ItemRegister.SUPERHEATER), subCategoryWeaponAugments.popGoodLocation());
        blasting_core = new ResearchBase("blasting_core", new ItemStack(ItemRegister.BLASTING_CORE), subCategoryWeaponAugments.popGoodLocation());
        caster_orb = new ResearchBase("caster_orb", new ItemStack(ItemRegister.CASTER_ORB), subCategoryWeaponAugments.popGoodLocation()).addPage(new ResearchBase("caster_orb_addendum", ItemStack.EMPTY, 0, 0));
        resonating_bell = new ResearchBase("resonating_bell", new ItemStack(ItemRegister.RESONATING_BELL), subCategoryWeaponAugments.popGoodLocation());
        //core_stone = new ResearchBase("core_stone", new ItemStack(RegistryManager.core_stone), subCategoryWeaponAugments.popGoodLocation());
        winding_gears = new ResearchBase("winding_gears", new ItemStack(ItemRegister.WINDING_GEARS), subCategoryWeaponAugments.popGoodLocation()).addPage(new ResearchShowItem("winding_gears_boots", ItemStack.EMPTY, 0, 0).addItem(new DisplayItem(new ItemStack(Items.IRON_BOOTS))));

        eldritch_insignia = new ResearchBase("eldritch_insignia", new ItemStack(ItemRegister.ELDRITCH_INSIGNIA), subCategoryArmorAugments.popGoodLocation());
        intelligent_apparatus = new ResearchBase("intelligent_apparatus", new ItemStack(ItemRegister.INTELLIGENT_APPARATUS), subCategoryArmorAugments.popGoodLocation());
        flame_barrier = new ResearchBase("flame_barrier", new ItemStack(ItemRegister.FLAME_BARRIER), subCategoryArmorAugments.popGoodLocation());
        cinder_jet = new ResearchBase("cinder_jet", new ItemStack(ItemRegister.JET_AUGMENT), subCategoryArmorAugments.popGoodLocation());
        tinker_lens_augment = new ResearchBase("tinker_lens_augment", new ItemStack(ItemRegister.TINKER_LENS), subCategoryArmorAugments.popGoodLocation());
        anti_tinker_lens = new ResearchBase("anti_tinker_lens", new ItemStack(ItemRegister.ANTI_TINKER_LENS), subCategoryArmorAugments.popGoodLocation()).addAncestor(tinker_lens_augment);
        shifting_scales = new ResearchBase("shifting_scales", new ItemStack(ItemRegister.SHIFTING_SCALES), subCategoryArmorAugments.popGoodLocation());

        diffraction_barrel = new ResearchBase("diffraction_barrel", new ItemStack(ItemRegister.DIFFRACTION_BARREL), subCategoryProjectileAugments.popGoodLocation());
        focal_lens = new ResearchBase("focal_lens", new ItemStack(ItemRegister.FOCAL_LENS), subCategoryProjectileAugments.popGoodLocation());

        tinker_lens.addPage(tinker_lens_augment);

        ResearchBase infernoForgeWeapon = new ResearchFakePage(inferno_forge, 6, 4);
        subCategoryWeaponAugments.addResearch(infernoForgeWeapon);
        subCategoryWeaponAugments.addResearch(superheater.addAncestor(infernoForgeWeapon));
        subCategoryWeaponAugments.addResearch(blasting_core.addAncestor(infernoForgeWeapon));
        subCategoryWeaponAugments.addResearch(caster_orb.addAncestor(infernoForgeWeapon));
        subCategoryWeaponAugments.addResearch(resonating_bell.addAncestor(infernoForgeWeapon));
        //subCategoryWeaponAugments.addResearch(core_stone.addAncestor(infernoForgeWeapon));
        subCategoryWeaponAugments.addResearch(winding_gears.addAncestor(infernoForgeWeapon));

        ResearchBase infernoForgeArmor = new ResearchFakePage(inferno_forge, 6, 4);
        subCategoryArmorAugments.addResearch(infernoForgeArmor);
        subCategoryArmorAugments.addResearch(cinder_jet.addAncestor(infernoForgeArmor));
        subCategoryArmorAugments.addResearch(eldritch_insignia.addAncestor(infernoForgeArmor));
        subCategoryArmorAugments.addResearch(intelligent_apparatus.addAncestor(infernoForgeArmor));
        subCategoryArmorAugments.addResearch(flame_barrier.addAncestor(infernoForgeArmor));
        subCategoryArmorAugments.addResearch(new ResearchFakePage(blasting_core, subCategoryArmorAugments.popGoodLocation()).addAncestor(infernoForgeArmor));
        subCategoryArmorAugments.addResearch(tinker_lens_augment.addAncestor(infernoForgeArmor));
        subCategoryArmorAugments.addResearch(anti_tinker_lens.addAncestor(infernoForgeArmor));
        subCategoryArmorAugments.addResearch(shifting_scales.addAncestor(infernoForgeArmor));
        subCategoryArmorAugments.addResearch(new ResearchFakePage(winding_gears, subCategoryArmorAugments.popGoodLocation()).addAncestor(infernoForgeArmor));
        //subCategoryArmorAugments.addResearch(new ResearchFakePage(core_stone,subCategoryArmorAugments.popGoodLocation()).addAncestor(infernoForgeArmor));

        ResearchBase infernoForgeProjectile = new ResearchFakePage(inferno_forge, 6, 4);
        subCategoryProjectileAugments.addResearch(infernoForgeProjectile);
        subCategoryProjectileAugments.addResearch(diffraction_barrel.addAncestor(infernoForgeProjectile));
        subCategoryProjectileAugments.addResearch(focal_lens.addAncestor(infernoForgeProjectile));

        ResearchBase infernoForgeMisc = new ResearchFakePage(inferno_forge, 6, 4);
        subCategoryMiscAugments.addResearch(infernoForgeMisc);

        subCategoryPipes.addResearch(pipes);
        subCategoryPipes.addResearch(bin);
        subCategoryPipes.addResearch(tank);
        subCategoryPipes.addResearch(reservoir);
        subCategoryPipes.addResearch(transfer);
        subCategoryPipes.addResearch(vacuum);
        subCategoryPipes.addResearch(dropper);
        subCategoryPipes.addResearch(requisition);
        subCategoryPipes.addResearch(golem_eye);

        subCategorySimpleAlchemy.addResearch(hellish_synthesis);
        subCategorySimpleAlchemy.addResearch(archaic_brick);
        subCategorySimpleAlchemy.addResearch(motive_core);
        subCategorySimpleAlchemy.addResearch(adhesive);
        subCategorySimpleAlchemy.addResearch(dwarven_oil);

        subCategoryWildfire.addResearch(wildfire);
        subCategoryWildfire.addResearch(injector);
        subCategoryWildfire.addResearch(combustor);
        subCategoryWildfire.addResearch(catalyzer);
        subCategoryWildfire.addResearch(reactor);
        subCategoryWildfire.addResearch(stirling);
        subCategoryWildfire.addResearch(ember_pipe);


        ResearchBase mechanicalPowerSwitch;
        if (Util.isMysticalMechanicsIntegrationEnabled()) {
            mechanicalPowerSwitch = makeCategorySwitch(subCategoryMechanicalPower, 8, 7, ItemStack.EMPTY, 4, 1);

            MysticalMechanicsIntegration.initMysticalMechanicsCategory();
        } else
            mechanicalPowerSwitch = new ResearchBase("mystical_mechanics", ItemStack.EMPTY, 8, 7).setIconBackground(PAGE_ICONS, PAGE_ICON_SIZE * 0, PAGE_ICON_SIZE * 2);
        mechanicalPowerSwitch.addAncestor(access);

        ResearchBase baublesSwitch;
        if (Util.isBaublesIntegrationEnabled()) {
            baublesSwitch = makeCategorySwitch(subCategoryBaubles, 5, 7, ItemStack.EMPTY, 5, 1);

            BaublesIntegration.initBaublesCategory();
        } else
            baublesSwitch = new ResearchBase("baubles", ItemStack.EMPTY, 5, 7).setIconBackground(PAGE_ICONS, PAGE_ICON_SIZE * 1, PAGE_ICON_SIZE * 2);
        baublesSwitch.addAncestor(cluster);


        ResearchBase pipeSwitch = makeCategorySwitch(subCategoryPipes, 3, 0, new ItemStack(BlockRegister.PIPE), 0, 1).addAncestor(hammer);
        ResearchBase weaponAugmentSwitch = makeCategorySwitch(subCategoryWeaponAugments, 2, 1, ItemStack.EMPTY, 1, 1).setMinEntries(2).addAncestor(inferno_forge);
        ResearchBase armorAugmentSwitch = makeCategorySwitch(subCategoryArmorAugments, 1, 3, ItemStack.EMPTY, 2, 1).setMinEntries(2).addAncestor(inferno_forge);
        ResearchBase projectileAugmentSwitch = makeCategorySwitch(subCategoryProjectileAugments, 11, 3, ItemStack.EMPTY, 3, 1).setMinEntries(2).addAncestor(inferno_forge);
        ResearchBase miscAugmentSwitch = makeCategorySwitch(subCategoryMiscAugments, 10, 1, ItemStack.EMPTY, 0, 1).setMinEntries(2).addAncestor(inferno_forge);
        ResearchBase wildfireSwitch = makeCategorySwitch(subCategoryWildfire, 1, 7, new ItemStack(ItemRegister.WILDFIRE_CORE), 0, 1).addAncestor(cluster);
        ResearchBase simpleAlchemySwitch = makeCategorySwitch(subCategorySimpleAlchemy, 12, 1, new ItemStack(Blocks.SOUL_SAND), 0, 1).addAncestor(waste);

        categoryWorld
                .addResearch(ores)
                .addResearch(hammer)
                .addResearch(ancient_golem)
                .addResearch(gauge)
                .addResearch(tinker_lens)
                .addResearch(caminite)
                .addResearch(bore)
                .addResearch(pipeSwitch)
                .addResearch(crystals)
                .addResearch(activator)
                .addResearch(boiler)
                .addResearch(mini_boiler)
                .addResearch(reaction_chamber)
                .addResearch(dials);
        categoryMechanisms
                .addResearch(melter)
                .addResearch(stamper)
                .addResearch(hearth_coil)
                .addResearch(mixer)
                .addResearch(pump)
                .addResearch(access)
                .addResearch(mechanicalPowerSwitch)
                .addResearch(breaker)
                .addResearch(dawnstone)
                .addResearch(emitters)
                .addResearch(copper_cell)
                .addResearch(clockwork_attenuator)
                .addResearch(geo_separator);
        categoryMetallurgy
                .addResearch(splitter)
                .addResearch(pulser)
                .addResearch(crystal_cell)
                .addResearch(charger)
                .addResearch(ember_siphon)
                .addResearch(jars)
                .addResearch(clockwork_tools)
                .addResearch(cinder_staff)
                .addResearch(blazing_ray)
                .addResearch(cinder_plinth)
                .addResearch(aspecti)
                .addResearch(alchemy)
                .addResearch(beam_cannon)
                .addResearch(catalytic_plug);
        categoryAlchemy
                .addResearch(waste)
                .addResearch(simpleAlchemySwitch)
                .addResearch(cluster)
                .addResearch(ashen_cloak)
                .addResearch(inflictor)
                .addResearch(field_chart)
                .addResearch(materia)
                .addResearch(tyrfing)
                .addResearch(glimmer)
                .addResearch(metallurgic_dust)
                .addResearch(baublesSwitch)
                .addResearch(wildfireSwitch);
        categorySmithing
                .addResearch(dawnstone_anvil)
                .addResearch(autohammer)
                .addResearch(heat)
                .addResearch(modifiers)
                .addResearch(dismantling)
                .addResearch(inferno_forge)
                .addResearch(weaponAugmentSwitch)
                .addResearch(armorAugmentSwitch)
                .addResearch(projectileAugmentSwitch)
                .addResearch(miscAugmentSwitch);

        categoryMechanisms.addPrerequisite(activator);
        categoryMetallurgy.addPrerequisite(dawnstone);
        categoryAlchemy.addPrerequisite(alchemy);
        categorySmithing.addPrerequisite(wildfire);

        researches.add(categoryWorld);
        researches.add(categoryMechanisms);
        researches.add(categoryMetallurgy);
        researches.add(categoryAlchemy);
        researches.add(categorySmithing);
        //researches.add(new ResearchCategory("materia", 80));
        //researches.add(new ResearchCategory("core", 96));
    }

    private static ResearchSwitchCategory makeCategorySwitch(ResearchCategory targetCategory, int x, int y, ItemStack icon, int u, int v) {
        return (ResearchSwitchCategory) new ResearchSwitchCategory(targetCategory.name + "_category", icon, x, y).setTargetCategory(targetCategory).setIconBackground(PAGE_ICONS, PAGE_ICON_SIZE * u, PAGE_ICON_SIZE * v);
    }
}
