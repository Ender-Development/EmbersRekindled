package teamroots.embers.mixin.embersified;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import p455w0rd.embersified.init.ModEvents;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.ItemRegister;

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

    @Inject(method = "onRecipeRegistryReady", at = @At("HEAD"), cancellable = true)
    private void onRecipeRegistryReady(CallbackInfo ci) {
        ci.cancel();
    }

}
