package teamroots.embers.mixin.mystgears;

import com.rcx.mystgears.item.ItemGear;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.config.ConfigMachine;
import teamroots.embers.register.ItemRegister;

@Mixin(value = ItemGear.class, remap = false)
public class ItemGearMixin {

    @Shadow public String name;

    @Redirect(method = "registerRecipe", at = @At(value = "FIELD", target = "Lteamroots/embers/ConfigManager;stampGearAmount:I"))
    private int stampGearAmount() {
        return ConfigMachine.STAMPER.stampGearAmount;
    }

    @Redirect(method = "registerRecipe", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_gear:Lnet/minecraft/item/Item;"))
    private Item stampGear() {
        return ItemRegister.STAMP_GEAR;
    }

//    @ModifyArg(method = "registerRecipe", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fluids/FluidRegistry;getFluidStack(Ljava/lang/String;I)Lnet/minecraftforge/fluids/FluidStack;"), index = 1)
//    private int stampGearAmount(int amount) {
//        return ConfigMachine.STAMPER.stampGearAmount * ConfigMisc.ingotFluidAmount;
//    }
//
//    @ModifyArg(method = "registerRecipe", at = @At(value = "INVOKE", target = "Lteamroots/embers/recipe/ItemStampingRecipe;<init>(Lnet/minecraft/item/crafting/Ingredient;Lnet/minecraftforge/fluids/FluidStack;Lnet/minecraft/item/crafting/Ingredient;Lnet/minecraft/item/ItemStack;)V"), index = 2)
//    private Ingredient stampGearOutput(Ingredient input) {
//        return Ingredient.fromItem(ItemRegister.STAMP_GEAR);
//    }
//
//    @Redirect(method = "registerRecipe", at = @At(value = "INVOKE", target = "Lteamroots/embers/recipe/ItemStampingRecipe;<init>(Lnet/minecraft/item/crafting/Ingredient;Lnet/minecraftforge/fluids/FluidStack;Lnet/minecraft/item/crafting/Ingredient;Lnet/minecraft/item/ItemStack;)V"))
//    private void stampGearRecipe(ItemStampingRecipe instance, Ingredient ingredient, FluidStack fluidStack, Ingredient stamp, ItemStack output) {
//        new ItemStampingRecipe(ingredient, FluidRegistry.getFluidStack(this.name.toLowerCase(),ConfigMachine.STAMPER.stampGearAmount * ConfigMisc.ingotFluidAmount), Ingredient.fromItem(ItemRegister.STAMP_GEAR), output);
//    }
}
