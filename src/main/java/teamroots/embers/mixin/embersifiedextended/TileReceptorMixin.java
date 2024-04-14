package teamroots.embers.mixin.embersifiedextended;

import embersified.blocks.tiles.TileReceptor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamroots.embers.config.ConfigMachine;

@Mixin(value = TileReceptor.class, remap = false)
public class TileReceptorMixin {
    @Final
    @Mutable
    @Shadow
    public static int TRANSFER_RATE;

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lteamroots/embers/api/power/IEmberCapability;setEmberCapacity(D)V"), index = 0)
    private double modifyEmberCapacity(double capacity) {
        return ConfigMachine.RECEIVER.capacity;
    }

    @Inject(method = "update", at = @At(value = "HEAD"))
    private void update(CallbackInfo ci) {
        TRANSFER_RATE = (int) ConfigMachine.RECEIVER.transfer_rate;
    }

}
