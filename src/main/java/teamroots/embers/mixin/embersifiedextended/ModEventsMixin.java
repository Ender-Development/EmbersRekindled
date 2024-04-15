package teamroots.embers.mixin.embersifiedextended;

import embersified.init.ModEvents;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamroots.embers.register.BlockRegister;

@Mixin(value = ModEvents.class, remap = false)
public class ModEventsMixin {

    @Redirect(method = "onBlockRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_emitter:Lnet/minecraft/block/Block;"))
    private static void ember_emitter(Block value) {
        BlockRegister.EMBER_EMITTER = value;
    }

    @Redirect(method = "onBlockRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_receiver:Lnet/minecraft/block/Block;"))
    private static void ember_receiver(Block value) {
        BlockRegister.EMBER_RECEIVER = value;
    }

    @Redirect(method = "onBlockRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_pulser:Lnet/minecraft/block/Block;"))
    private static void ember_pulser(Block value) {
        BlockRegister.EMBER_PULSER = value;
    }

    @Redirect(method = "onBlockRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_funnel:Lnet/minecraft/block/Block;"))
    private static void ember_funnel(Block value) {
        BlockRegister.EMBER_FUNNEL = value;
    }

    @Redirect(method = "onBlockRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;charger:Lnet/minecraft/block/Block;"))
    private static void charger(Block value) {
        BlockRegister.CHARGER = value;
    }

    @Redirect(method = "onBlockRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_pipe:Lnet/minecraft/block/Block;"))
    private static void ember_pipe(Block value) {
        BlockRegister.EMBER_PIPE = value;
    }

    @Inject(method = "onRecipeRegistryReady", at = @At("HEAD"), cancellable = true)
    private void onRecipeRegistryReady(CallbackInfo ci) {
        ci.cancel();
    }
}
