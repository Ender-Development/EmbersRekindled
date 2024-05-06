package teamroots.embers.mixin.emberforged;

import com.Lenvill.ResearchInit;
import minefantasy.mfr.mechanics.knowledge.InformationBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.ItemRegister;

@Mixin(value = ResearchInit.class, remap = false)
public class ResearchInitMixin {
    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ancient_motive_core:Lnet/minecraft/item/Item;"))
    private static Item redirectAncientMotiveCore() {
        return ItemRegister.ANCIENT_MOTIVE_CORE;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;tinker_hammer:Lnet/minecraft/item/Item;"))
    private static Item redirectTinkerHammer() {
        return ItemRegister.TINKER_HAMMER;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_gauge:Lnet/minecraft/block/Block;"))
    private static Block redirectEmberGauge() {
        return BlockRegister.EMBER_GAUGE;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;item_pipe:Lnet/minecraft/block/Block;"))
    private static Block redirectItemPipe() {
        return BlockRegister.ITEM_PIPE;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;mech_core:Lnet/minecraft/block/Block;"))
    private static Block redirectMechCore() {
        return BlockRegister.MECH_CORE;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_emitter:Lnet/minecraft/block/Block;"))
    private static Block redirectEmberEmitter() {
        return BlockRegister.EMBER_EMITTER;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;mixer:Lnet/minecraft/block/Block;"))
    private static Block redirectMixer() {
        return BlockRegister.MIXER;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamper:Lnet/minecraft/block/Block;"))
    private static Block redirectStamper() {
        return BlockRegister.STAMPER;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;golems_eye:Lnet/minecraft/item/Item;"))
    private static Item redirectGolemsEye() {
        return ItemRegister.GOLEMS_EYE;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;bin:Lnet/minecraft/block/Block;"))
    private static Block redirectBin() {
        return BlockRegister.BIN;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_bore:Lnet/minecraft/block/Block;"))
    private static Block redirectEmberBore() {
        return BlockRegister.EMBER_BORE;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;crystal_ember:Lnet/minecraft/item/Item;"))
    private static Item redirectCrystalEmber() {
        return ItemRegister.CRYSTAL_EMBER;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_activator:Lnet/minecraft/block/Block;"))
    private static Block redirectEmberActivator() {
        return BlockRegister.EMBER_ACTIVATOR;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;boiler:Lnet/minecraft/block/Block;"))
    private static Block redirectBoiler() {
        return BlockRegister.BOILER;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;mini_boiler:Lnet/minecraft/block/Block;"))
    private static Block redirectMiniBoiler() {
        return BlockRegister.MINI_BOILER;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;reaction_chamber:Lnet/minecraft/block/Block;"))
    private static Block redirectReactionChamber() {
        return BlockRegister.REACTION_CHAMBER;
    }

    @Redirect(method = "initArtefacts", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ingot_copper:Lnet/minecraft/item/Item;"))
    private static Item redirectIngotCopper() {
        return ItemRegister.INGOT_COPPER;
    }

    @Redirect(method = "initArtefacts", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;plate_caminite:Lnet/minecraft/item/Item;"))
    private static Item redirectPlateCaminite() {
        return ItemRegister.PLATE_CAMINITE;
    }

    @Redirect(method = "initArtefacts", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;crystal_ember:Lnet/minecraft/item/Item;"))
    private static Item redirectCrystalEmberArtefact() {
        return ItemRegister.CRYSTAL_EMBER;
    }

    @Redirect(method = "initArtefacts", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;mech_core:Lnet/minecraft/block/Block;"))
    private static Block redirectMechCoreArtefact() {
        return BlockRegister.MECH_CORE;
    }

    @Redirect(method = "initArtefacts", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;spark_plug:Lnet/minecraft/item/Item;"))
    private static Item redirectSparkPlug() {
        return ItemRegister.SPARK_PLUG;
    }
}
