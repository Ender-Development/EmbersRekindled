package teamroots.embers.mixin.embersifiedextended;

import embersified.blocks.tiles.TileCharger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.config.ConfigMachine;

@Mixin(value = TileCharger.class, remap = false)
public class TileChargerMixin {
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lteamroots/embers/api/power/IEmberCapability;setEmberCapacity(D)V"), index = 0)
    private double modifyEmberCapacity(double value) {
        return ConfigMachine.CHARGER.capacity;
    }

    @Redirect(method = "update", at = @At(value = "FIELD", target = "Lembersified/blocks/tiles/TileCharger;MAX_TRANSFER:D"))
    private double redirectMaxTransfer() {
        return ConfigMachine.CHARGER.maxTransfer;
    }

}
