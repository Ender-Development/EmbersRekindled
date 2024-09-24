package teamroots.embers.mixin.appliedintegrations;

import AppliedIntegrations.Integration.IntegrationsHelper;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.ItemRegister;

@Mixin(value= IntegrationsHelper.class, remap=false)
public class IntegrationsHelperMixin {
    @Redirect(method = "registerTunnelTypes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;shard_ember:Lnet/minecraft/item/Item;"))
    private Item getShardEmber() {
        return ItemRegister.SHARD_EMBER;
    }
}
