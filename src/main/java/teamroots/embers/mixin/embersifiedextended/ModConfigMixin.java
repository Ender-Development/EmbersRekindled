package teamroots.embers.mixin.embersifiedextended;

import embersified.init.ModConfig;
import net.minecraftforge.common.config.Configuration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamroots.embers.Embers;

import java.io.File;

@Mixin(value = ModConfig.class, remap = false)
public class ModConfigMixin {
    @Mutable
    @Shadow
    public static Configuration CONFIG;

    @Inject(method = "load", at = @At("HEAD"))
    private void load(CallbackInfo ci) {
        CONFIG = new Configuration(new File("config/" + Embers.CFG_ADDONS_FOLDER + "embersifiedextended.cfg"));
    }
}
