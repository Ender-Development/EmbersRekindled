package teamroots.embers.mixin.embersconstruct;

import com.peatral.embersconstruct.CreativeTabEmbersConstruct;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.ItemRegister;

@Mixin(value = CreativeTabEmbersConstruct.class, remap = false)
public class CreativeTabEmbersConstructMixin {
    @Redirect(method = "getTabIconItem", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_gear:Lnet/minecraft/item/Item;"))
    private Item getTabIconItem() {
        return ItemRegister.STAMP_GEAR;
    }
}
