package teamroots.embers.mixin.mysticalgears;

import com.rcx.mystgears.compatibility.EmbersCompat;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import soot.Registry;
import teamroots.embers.config.ConfigAddon;
import teamroots.embers.register.ItemRegister;

import java.util.Objects;

@Mixin(value = EmbersCompat.class, remap = false)
public class EmbersCompatMixin {
    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;shard_ember:Lnet/minecraft/item/Item;"))
    private static Item redirectShardEmber() {
        return ItemRegister.SHARD_EMBER;
    }

    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lsoot/Registry;WITCH_FIRE:Lnet/minecraft/item/Item;"))
    private static Item redirectWitchFire() {
        if (ConfigAddon.enableSoot) {
            return Registry.WITCH_FIRE;
        }
        return Items.FIRE_CHARGE;
    }

}
