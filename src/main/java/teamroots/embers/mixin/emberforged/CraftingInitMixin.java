package teamroots.embers.mixin.emberforged;

import com.Lenvill.CraftingInit;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.compat.BaublesIntegration;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.ItemRegister;

@Mixin(value = CraftingInit.class, remap = false)
public class CraftingInitMixin {
    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;reactor:Lnet/minecraft/block/Block;"))
    private static Block redirectReactor() {
        return BlockRegister.REACTOR;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;mixer:Lnet/minecraft/block/Block;"))
    private static Block redirectMixer() {
        return BlockRegister.MIXER;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;mini_boiler:Lnet/minecraft/block/Block;"))
    private static Block redirectMiniBoiler() {
        return BlockRegister.MINI_BOILER;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_bore:Lnet/minecraft/block/Block;"))
    private static Block redirectEmberBore() {
        return BlockRegister.EMBER_BORE;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_activator:Lnet/minecraft/block/Block;"))
    private static Block redirectEmberActivator() {
        return BlockRegister.EMBER_ACTIVATOR;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;crystal_cell:Lnet/minecraft/block/Block;"))
    private static Block redirectCrystalCell() {
        return BlockRegister.CRYSTAL_CELL;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;cinder_plinth:Lnet/minecraft/block/Block;"))
    private static Block redirectCinderPlinth() {
        return BlockRegister.CINDER_PLINTH;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;beam_cannon:Lnet/minecraft/block/Block;"))
    private static Block redirectBeamCannon() {
        return BlockRegister.BEAM_CANNON;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;alchemy_pedestal:Lnet/minecraft/block/Block;"))
    private static Block redirectAlchemyPedestal() {
        return BlockRegister.ALCHEMY_PEDESTAL;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;alchemy_tablet:Lnet/minecraft/block/Block;"))
    private static Block redirectAlchemyTablet() {
        return BlockRegister.ALCHEMY_TABLET;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ashen_cloak_chest:Lnet/minecraft/item/Item;"))
    private static Item redirectAshenCloakChest() {
        return ItemRegister.ASHEN_CLOAK_CHEST;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;beam_splitter:Lnet/minecraft/block/Block;"))
    private static Block redirectBeamSplitter() {
        return BlockRegister.BEAM_SPLITTER;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;bin:Lnet/minecraft/block/Block;"))
    private static Block redirectBin() {
        return BlockRegister.BIN;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;caster_orb:Lnet/minecraft/item/Item;"))
    private static Item redirectCasterOrb() {
        return ItemRegister.CASTER_ORB;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;catalyzer:Lnet/minecraft/block/Block;"))
    private static Block redirectCatalyzer() {
        return BlockRegister.CATALYZER;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;charger:Lnet/minecraft/block/Block;"))
    private static Block redirectCharger() {
        return BlockRegister.CHARGER;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;clockwork_attenuator:Lnet/minecraft/block/Block;"))
    private static Block redirectClockworkAttenuator() {
        return BlockRegister.CLOCKWORK_ATTENUATOR;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;axe_clockwork:Lnet/minecraft/item/Item;"))
    private static Item redirectAxeClockwork() {
        return ItemRegister.AXE_CLOCKWORK;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;combustor:Lnet/minecraft/block/Block;"))
    private static Block redirectCombustor() {
        return BlockRegister.COMBUSTOR;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/compat/BaublesIntegration;dawnstone_mail:Lnet/minecraft/item/Item;"))
    private static Item redirectDawnstoneMail() {
        return BaublesIntegration.DAWNSTONE_MAIL;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;diffraction_barrel:Lnet/minecraft/item/Item;"))
    private static Item redirectDiffractionBarrel() {
        return ItemRegister.DIFFRACTION_BARREL;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/compat/BaublesIntegration;ember_belt:Lnet/minecraft/item/Item;"))
    private static Item redirectEmberBelt() {
        return BaublesIntegration.EMBER_BELT;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/compat/BaublesIntegration;mantle_bulb:Lnet/minecraft/item/Item;"))
    private static Item redirectMantleBulb() {
        return BaublesIntegration.MANTLE_BULB;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_cartridge:Lnet/minecraft/item/Item;"))
    private static Item redirectEmberCartridge() {
        return ItemRegister.EMBER_CARTRIDGE;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_funnel:Lnet/minecraft/block/Block;"))
    private static Block redirectEmberFunnel() {
        return BlockRegister.EMBER_FUNNEL;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_gauge:Lnet/minecraft/block/Block;"))
    private static Block redirectEmberGauge() {
        return BlockRegister.EMBER_GAUGE;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_injector:Lnet/minecraft/block/Block;"))
    private static Block redirectEmberInjector() {
        return BlockRegister.EMBER_INJECTOR;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_pulser:Lnet/minecraft/block/Block;"))
    private static Block redirectEmberPulser() {
        return BlockRegister.EMBER_PULSER;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_relay:Lnet/minecraft/block/Block;"))
    private static Block redirectEmberRelay() {
        return BlockRegister.EMBER_RELAY;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_siphon:Lnet/minecraft/block/Block;"))
    private static Block redirectEmberSiphon() {
        return BlockRegister.EMBER_SIPHON;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;staff_ember:Lnet/minecraft/item/Item;"))
    private static Item redirectStaffEmber() {
        return ItemRegister.STAFF_EMBER;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_gauge:Lnet/minecraft/block/Block;"))
    private static Block redirectFluidGauge() {
        return BlockRegister.FLUID_GAUGE;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;pipe:Lnet/minecraft/block/Block;"))
    private static Block redirectPipe() {
        return BlockRegister.PIPE;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_transfer:Lnet/minecraft/block/Block;"))
    private static Block redirectFluidTransfer() {
        return BlockRegister.FLUID_TRANSFER;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;glimmer_lamp:Lnet/minecraft/item/Item;"))
    private static Item redirectGlimmerLamp() {
        return ItemRegister.GLIMMER_LAMP;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;heat_coil:Lnet/minecraft/block/Block;"))
    private static Block redirectHeatCoil() {
        return BlockRegister.HEAT_COIL;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ignition_cannon:Lnet/minecraft/item/Item;"))
    private static Item redirectIgnitionCannon() {
        return ItemRegister.IGNITION_CANNON;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;inferno_forge:Lnet/minecraft/block/Block;"))
    private static Block redirectInfernoForge() {
        return BlockRegister.INFERNO_FORGE;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;item_transfer:Lnet/minecraft/block/Block;"))
    private static Block redirectItemTransfer() {
        return BlockRegister.ITEM_TRANSFER;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;jet_augment:Lnet/minecraft/item/Item;"))
    private static Item redirectJetAugment() {
        return ItemRegister.JET_AUGMENT;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;block_lantern:Lnet/minecraft/block/Block;"))
    private static Block redirectBlockLantern() {
        return BlockRegister.BLOCK_LANTERN;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;mech_accessor:Lnet/minecraft/block/Block;"))
    private static Block redirectMechAccessor() {
        return BlockRegister.MECH_ACCESSOR;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;mech_core:Lnet/minecraft/block/Block;"))
    private static Block redirectMechCore() {
        return BlockRegister.MECH_CORE;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;pump:Lnet/minecraft/block/Block;"))
    private static Block redirectPump() {
        return BlockRegister.PUMP;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;resonating_bell:Lnet/minecraft/item/Item;"))
    private static Item redirectResonatingBell() {
        return ItemRegister.RESONATING_BELL;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;spark_plug:Lnet/minecraft/item/Item;"))
    private static Item redirectSparkPlug() {
        return ItemRegister.SPARK_PLUG;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stirling:Lnet/minecraft/block/Block;"))
    private static Block redirectStirling() {
        return BlockRegister.STIRLING;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;superheater:Lnet/minecraft/item/Item;"))
    private static Item redirectSuperheater() {
        return ItemRegister.SUPERHEATER;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;block_tank:Lnet/minecraft/block/Block;"))
    private static Block redirectBlockTank() {
        return BlockRegister.BLOCK_TANK;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;tinker_hammer:Lnet/minecraft/item/Item;"))
    private static Item redirectTinkerHammer() {
        return ItemRegister.TINKER_HAMMER;
    }

    @Redirect(method = "removeRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;tinker_lens:Lnet/minecraft/item/Item;"))
    private static Item redirectTinkerLens() {
        return ItemRegister.TINKER_LENS;
    }
}
