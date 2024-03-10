package teamroots.embers.mixin.embersconstruct;

import com.peatral.embersconstruct.util.OreDictValues;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
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

    /**
     * @author MasterEnderman
     * @reason Use actual config values
     */
    @Overwrite
    public int getValue() {
        OreDictValues odv = (OreDictValues) (Object) this;
        switch (odv) {
            case INGOT:
            case DUST:
                return ConfigMisc.ingotFluidAmount;
            case ORE:
                return ConfigMisc.ingotFluidAmount * ConfigMachine.MELTER.melterOreAmount;
            case BLOCK:
                return ConfigMisc.blockFluidAmount;
            case NUGGET:
                return ConfigMisc.nuggetFluidAmount;
            case SHARD:
                return 72;
            case PLATE:
                return ConfigMisc.ingotFluidAmount * ConfigMachine.STAMPER.stampPlateAmount;
            case GEM:
                return 666;
            case GEAR:
                return ConfigMisc.ingotFluidAmount * ConfigMachine.STAMPER.stampGearAmount;
            default:
                return 0;
        }
    }
}
