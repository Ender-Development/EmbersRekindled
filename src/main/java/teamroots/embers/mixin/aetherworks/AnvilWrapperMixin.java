package teamroots.embers.mixin.aetherworks;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.ItemRegister;
import v0id.aw.compat.jei.Wrappers;

@Mixin(value = Wrappers.AnvilWrapper.class, remap = false)
public class AnvilWrapperMixin {
    @Redirect(method = "getEmbersStack", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;shard_ember:Lnet/minecraft/item/Item;"))
    private Item shard_embers() {
        return ItemRegister.SHARD_EMBER;
    }
}
