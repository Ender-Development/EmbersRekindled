package teamroots.embers.mixin.embersifiedextended;

import embersified.blocks.tiles.TileEmitter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamroots.embers.config.ConfigMachine;

@Mixin(value = TileEmitter.class, remap = false)
public class TileEmitterMixin {
    @Final
    @Mutable
    @Shadow
    public static double TRANSFER_RATE;

    @Final
    @Mutable
    @Shadow
    public static double PULL_RATE;

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lteamroots/embers/api/power/IEmberCapability;setEmberCapacity(D)V"), index = 0)
    private double modifyEmberCapacity(double capacity) {
        return ConfigMachine.EMITTER.capacity;
    }

    @Inject(method = "update", at = @At(value = "HEAD"))
    private void update(CallbackInfo info) {
        TRANSFER_RATE = ConfigMachine.EMITTER.transfer_rate;
        PULL_RATE = ConfigMachine.EMITTER.pull_rate;
    }
}
