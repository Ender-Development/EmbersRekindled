package teamroots.embers.mixin.embersifiedextended;

import embersified.blocks.tiles.TileVPipe;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamroots.embers.config.ConfigMachine;

@Mixin(value = TileVPipe.class, remap = false)
public class TileVPipeMixin {
    @Final
    @Mutable
    @Shadow
    public static double INIT_PUSH;

    @Final
    @Mutable
    @Shadow
    public static double INCREMENT_PUSH;

    @Inject(method = "update", at = @At(value = "HEAD"))
    private void update(CallbackInfo ci) {
        INIT_PUSH = ConfigMachine.EMBER_CONDUIT.initial_push;
        INCREMENT_PUSH = ConfigMachine.EMBER_CONDUIT.incremental_push;
    }
}
