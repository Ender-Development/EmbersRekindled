package teamroots.embers.mixin.moreclimate;

import claustra01.moreclimate.compat.Embers;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.BlockRegister;

@Mixin(value = Embers.class, remap = false)
public class EmbersMixin {
    @Redirect(method = "onBlockTempEvent", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;beam_cannon:Lnet/minecraft/block/Block;"))
    private Block beamCannon() {
        return BlockRegister.BEAM_CANNON;
    }

    @Redirect(method = "onBlockTempEvent", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;block_furnace:Lnet/minecraft/block/Block;"))
    private Block blockFurnace() {
        return BlockRegister.BLOCK_FURNACE;
    }

    @Redirect(method = "onBlockTempEvent", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;boiler:Lnet/minecraft/block/Block;"))
    private Block boiler() {
        return BlockRegister.BOILER;
    }

    @Redirect(method = "onBlockTempEvent", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;cinder_plinth:Lnet/minecraft/block/Block;"))
    private Block cinderPlinth() {
        return BlockRegister.CINDER_PLINTH;
    }

    @Redirect(method = "onBlockTempEvent", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;crystal_cell:Lnet/minecraft/block/Block;"))
    private Block crystalCell() {
        return BlockRegister.CRYSTAL_CELL;
    }

    @Redirect(method = "onBlockTempEvent", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_activator:Lnet/minecraft/block/Block;"))
    private Block emberActivator() {
        return BlockRegister.EMBER_ACTIVATOR;
    }

    @Redirect(method = "onBlockTempEvent", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_bore:Lnet/minecraft/block/Block;"))
    private Block emberBore() {
        return BlockRegister.EMBER_BORE;
    }

    @Redirect(method = "onBlockTempEvent", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_injector:Lnet/minecraft/block/Block;"))
    private Block emberInjector() {
        return BlockRegister.EMBER_INJECTOR;
    }

    @Redirect(method = "onBlockTempEvent", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;heat_coil:Lnet/minecraft/block/Block;"))
    private Block heatCoil() {
        return BlockRegister.HEAT_COIL;
    }

    @Redirect(method = "onBlockTempEvent", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;inferno_forge:Lnet/minecraft/block/Block;"))
    private Block infernoForge() {
        return BlockRegister.INFERNO_FORGE;
    }

    @Redirect(method = "onBlockTempEvent", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;mini_boiler:Lnet/minecraft/block/Block;"))
    private Block miniBoiler() {
        return BlockRegister.MINI_BOILER;
    }

    @Redirect(method = "onBlockTempEvent", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;reaction_chamber:Lnet/minecraft/block/Block;"))
    private Block reactionChamber() {
        return BlockRegister.REACTION_CHAMBER;
    }

    @Redirect(method = "onBlockTempEvent", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;reactor:Lnet/minecraft/block/Block;"))
    private Block reactor() {
        return BlockRegister.REACTOR;
    }
}
