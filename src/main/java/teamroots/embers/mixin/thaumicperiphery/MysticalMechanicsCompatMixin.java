package teamroots.embers.mixin.thaumicperiphery;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.config.ConfigMachine;
import teamroots.embers.config.ConfigMisc;
import teamroots.embers.register.ItemRegister;
import thaumicperiphery.compat.MysticalMechanicsCompat;

@Mixin(value = MysticalMechanicsCompat.class, remap = false)
public class MysticalMechanicsCompatMixin {
    @Redirect(method = "initRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_gear:Lnet/minecraft/item/Item;"))
    private static Item stamp_gear() {
        return ItemRegister.STAMP_GEAR;
    }

    @Redirect(method = "initRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/ConfigManager;stampGearAmount:I"))
    private static int stamp_gear_amount() {
        return ConfigMachine.STAMPER.stampGearAmount;
    }

    @ModifyArg(method = "initRecipes", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fluids/FluidRegistry;getFluidStack(Ljava/lang/String;I)Lnet/minecraftforge/fluids/FluidStack;"), index = 1)
    private static int modifyFluidStack(int amount) {
        return ConfigMachine.STAMPER.stampGearAmount * ConfigMisc.ingotFluidAmount;
    }
}
