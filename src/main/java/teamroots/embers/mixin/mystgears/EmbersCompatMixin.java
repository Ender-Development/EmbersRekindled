package teamroots.embers.mixin.mystgears;

import com.rcx.mystgears.compatibility.EmbersCompat;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.ItemRegister;

@Mixin(value = EmbersCompat.class, remap = false)
public class EmbersCompatMixin {
    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;shard_ember:Lnet/minecraft/item/Item;"))
    private static Item redirectShardEmber() {
        return ItemRegister.SHARD_EMBER;
    }
}
