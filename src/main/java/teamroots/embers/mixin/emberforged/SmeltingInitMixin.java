package teamroots.embers.mixin.emberforged;

import com.Lenvill.SmeltingInit;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.BlockRegister;

@Mixin(value = SmeltingInit.class, remap = false)
public class SmeltingInitMixin {
    @Redirect(method = "allsmelt", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ore_aluminum:Lnet/minecraft/block/Block;"))
    private static Block redirectAluminum() {
        return BlockRegister.ORE_ALUMINUM;
    }

    @Redirect(method = "allsmelt", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ore_copper:Lnet/minecraft/block/Block;"))
    private static Block redirectCopper() {
        return BlockRegister.ORE_COPPER;
    }

    @Redirect(method = "allsmelt", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ore_lead:Lnet/minecraft/block/Block;"))
    private static Block redirectLead() {
        return BlockRegister.ORE_LEAD;
    }

    @Redirect(method = "allsmelt", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ore_silver:Lnet/minecraft/block/Block;"))
    private static Block redirectSilver() {
        return BlockRegister.ORE_SILVER;
    }

    @Redirect(method = "allsmelt", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ore_nickel:Lnet/minecraft/block/Block;"))
    private static Block redirectNickel() {
        return BlockRegister.ORE_NICKEL;
    }

    @Redirect(method = "allsmelt", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ore_tin:Lnet/minecraft/block/Block;"))
    private static Block redirectTin() {
        return BlockRegister.ORE_TIN;
    }
}
