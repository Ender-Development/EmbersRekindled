package teamroots.embers.mixin.polymancy;

import de.zpenguin.polymancy.compat.embers.EmbersCompat;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.ItemRegister;

@Mixin(value = EmbersCompat.class, remap = false)
public class EmbersCompatMixin {
    @Redirect(method = "initRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;wildfire_core:Lnet/minecraft/item/Item;"))
    private Item wildfire_core() {
        return ItemRegister.WILDFIRE_CORE;
    }

    @Redirect(method = "initRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;shard_ember:Lnet/minecraft/item/Item;"))
    private Item shard_ember() {
        return ItemRegister.SHARD_EMBER;
    }

    @Redirect(method = "initRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;crystal_ember:Lnet/minecraft/item/Item;"))
    private Item crystal_ember() {
        return ItemRegister.CRYSTAL_EMBER;
    }

    @Redirect(method = "initRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ancient_motive_core:Lnet/minecraft/item/Item;"))
    private Item ancient_motive_core() {
        return ItemRegister.ANCIENT_MOTIVE_CORE;
    }

    @Redirect(method = "initRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;archaic_brick:Lnet/minecraft/item/Item;"))
    private Item archaic_brick() {
        return ItemRegister.ARCHAIC_BRICK;
    }

    @Redirect(method = "initRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;brick_caminite:Lnet/minecraft/item/Item;"))
    private Item brick_caminite() {
        return ItemRegister.BRICK_CAMINITE;
    }

    @Redirect(method = "initResearch", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_bore:Lnet/minecraft/block/Block;"))
    private Block ember_bore() {
        return BlockRegister.EMBER_BORE;
    }
}
