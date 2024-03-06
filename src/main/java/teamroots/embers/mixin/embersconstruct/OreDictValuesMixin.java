package teamroots.embers.mixin.embersconstruct;

import com.peatral.embersconstruct.util.OreDictValues;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.config.ConfigMachine;
import teamroots.embers.config.ConfigMisc;

@Mixin(value = OreDictValues.class, remap = false)
public class OreDictValuesMixin {
    // Redirect ConfigManager calls
    @Redirect(method = "lambda$static$1", at = @At(value = "FIELD", target = "Lteamroots/embers/ConfigManager;melterOreAmount:I"))
    private static int melterOreAmount() {
        return ConfigMachine.MELTER.melterOreAmount;
    }

    @Redirect(method = "lambda$static$6", at = @At(value = "FIELD", target = "Lteamroots/embers/ConfigManager;stampPlateAmount:I"))
    private static int stampPlateAmount() {
        return ConfigMachine.STAMPER.stampPlateAmount;
    }

    @Redirect(method = "lambda$static$8", at = @At(value = "FIELD", target = "Lteamroots/embers/ConfigManager;stampGearAmount:I"))
    private static int stampGearAmount() {
        return ConfigMachine.STAMPER.stampGearAmount;
    }

    // Redirect all returns to actual config values
    @Redirect(method = "lambda$static$0", at = @At(value = "INVOKE", target = "Ljava/lang/Integer;valueOf(I)Ljava/lang/Integer;"))
    private static Integer amount_ingot(int i) {
        return ConfigMisc.ingotFluidAmount;
    }

    @Redirect(method = "lambda$static$1", at = @At(value = "INVOKE", target = "Ljava/lang/Integer;valueOf(I)Ljava/lang/Integer;"))
    private static Integer amount_ore(int i) {
        return ConfigMisc.ingotFluidAmount * ConfigMachine.MELTER.melterOreAmount;
    }

    @Redirect(method = "lambda$static$2", at = @At(value = "INVOKE", target = "Ljava/lang/Integer;valueOf(I)Ljava/lang/Integer;"))
    private static Integer amount_block(int i) {
        return ConfigMisc.blockFluidAmount;
    }

    @Redirect(method = "lambda$static$3", at = @At(value = "INVOKE", target = "Ljava/lang/Integer;valueOf(I)Ljava/lang/Integer;"))
    private static Integer amount_nugget(int i) {
        return ConfigMisc.nuggetFluidAmount;
    }

    @Redirect(method = "lambda$static$4", at = @At(value = "INVOKE", target = "Ljava/lang/Integer;valueOf(I)Ljava/lang/Integer;"))
    private static Integer amount_dust(int i) {
        return ConfigMisc.ingotFluidAmount;
    }

    @Redirect(method = "lambda$static$6", at = @At(value = "INVOKE", target = "Ljava/lang/Integer;valueOf(I)Ljava/lang/Integer;"))
    private static Integer amount_plate(int i) {
        return ConfigMisc.ingotFluidAmount * ConfigMachine.STAMPER.stampPlateAmount;
    }

    @Redirect(method = "lambda$static$8", at = @At(value = "INVOKE", target = "Ljava/lang/Integer;valueOf(I)Ljava/lang/Integer;"))
    private static Integer amount_gear(int i) {
        return ConfigMisc.ingotFluidAmount * ConfigMachine.STAMPER.stampGearAmount;
    }
}
