package teamroots.embers.mixin.embersifiedextended;

import embersified.blocks.tiles.TileFunnel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import teamroots.embers.config.ConfigMachine;

@Mixin(value = TileFunnel.class, remap = false)
public class TileFunnelMixin {

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lteamroots/embers/api/power/IEmberCapability;setEmberCapacity(D)V"), index = 0)
    private double modifyEmberCapacity(double capacity) {
        return ConfigMachine.EMBER_FUNNEL.capacity;
    }

    @ModifyArg(method = "update", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(DD)D"), index = 0)
    private double modifyTransferRate(double rate) {
        return ConfigMachine.EMBER_FUNNEL.transfer_speed;
    }
}
