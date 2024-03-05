package teamroots.embers.mixin.embersified;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import p455w0rd.embersified.init.ModEvents;
import teamroots.embers.register.BlockRegister;

@Mixin(value = ModEvents.class, remap = false)
public class ModEventsMixin {
    @Redirect(method = "onBlockRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_emitter:Lnet/minecraft/block/Block;"))
    private static void ember_emitter(Block value) {
        BlockRegister.INSTANCE.add(value);
    }

    @Redirect(method = "onBlockRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_receiver:Lnet/minecraft/block/Block;"))
    private static void ember_receiver(Block value) {
        BlockRegister.INSTANCE.add(value);
    }
}
