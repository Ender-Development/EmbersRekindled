package teamroots.embers.mixin.embersconstruct;

import com.peatral.embersconstruct.registry.RegistryResearch;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamroots.embers.register.ItemRegister;
import teamroots.embers.research.ResearchCategory;
import teamroots.embers.research.ResearchManager;

@Mixin(value = RegistryResearch.class, remap = false)
public class RegistryResearchMixin {
    @Redirect(method = "main", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_flat_raw:Lnet/minecraft/item/Item;"))
    private static Item stamp_flat_raw() {
        return ItemRegister.STAMP_FLAT_RAW;
    }

    @Redirect(method = "main", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_gear:Lnet/minecraft/item/Item;"))
    private static Item stamp_gear() {
        return ItemRegister.STAMP_GEAR;
    }

    @Inject(method = "main", at = @At(value = "RETURN"))
    private static void remove_test_research(CallbackInfo ci) {
        ResearchManager.researches.remove(ResearchManager.researches.stream().filter(research -> research.name.equals("test")).findFirst().orElse(null));
    }
}
