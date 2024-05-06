package teamroots.embers.mixin.emberforged;

import com.Lenvill.StampInit;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.config.ConfigMisc;
import teamroots.embers.register.FluidRegister;
import teamroots.embers.register.ItemRegister;

@Mixin(value = StampInit.class, remap = false)
public class StampInitMixin {
    @ModifyArg(method = "initStamps", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fluids/FluidStack;<init>(Lnet/minecraftforge/fluids/Fluid;I)V"), index = 1)
    private static int modifyFluidStackAmount(int amount) {
        return ConfigMisc.ingotFluidAmount;
    }

    @Redirect(method = "initStamps", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;stamp_bar:Lnet/minecraft/item/Item;"))
    private static Item redirectStampBar() {
        return ItemRegister.STAMP_BAR;
    }

    @Redirect(method = "initStamps", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_molten_copper:Lnet/minecraftforge/fluids/Fluid;"))
    private static Fluid redirectMoltenCopper() {
        return FluidRegister.FLUID_MOLTEN_COPPER;
    }

    @Redirect(method = "initStamps", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_molten_tin:Lnet/minecraftforge/fluids/Fluid;"))
    private static Fluid redirectMoltenTin() {
        return FluidRegister.FLUID_MOLTEN_TIN;
    }

    @Redirect(method = "initStamps", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_molten_silver:Lnet/minecraftforge/fluids/Fluid;"))
    private static Fluid redirectMoltenSilver() {
        return FluidRegister.FLUID_MOLTEN_SILVER;
    }

    @Redirect(method = "initStamps", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_molten_iron:Lnet/minecraftforge/fluids/Fluid;"))
    private static Fluid redirectMoltenIron() {
        return FluidRegister.FLUID_MOLTEN_IRON;
    }

    @Redirect(method = "initStamps", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_molten_gold:Lnet/minecraftforge/fluids/Fluid;"))
    private static Fluid redirectMoltenGold() {
        return FluidRegister.FLUID_MOLTEN_GOLD;
    }

    @Redirect(method = "initStamps", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_molten_bronze:Lnet/minecraftforge/fluids/Fluid;"))
    private static Fluid redirectMoltenBronze() {
        return FluidRegister.FLUID_MOLTEN_BRONZE;
    }

    @Redirect(method = "initStamps", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_molten_nickel:Lnet/minecraftforge/fluids/Fluid;"))
    private static Fluid redirectMoltenNickel() {
        return FluidRegister.FLUID_MOLTEN_NICKEL;
    }

    @Redirect(method = "initStamps", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_molten_electrum:Lnet/minecraftforge/fluids/Fluid;"))
    private static Fluid redirectMoltenElectrum() {
        return FluidRegister.FLUID_MOLTEN_ELECTRUM;
    }

    @Redirect(method = "initStamps", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_molten_lead:Lnet/minecraftforge/fluids/Fluid;"))
    private static Fluid redirectMoltenLead() {
        return FluidRegister.FLUID_MOLTEN_LEAD;
    }

    @Redirect(method = "initStamps", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_molten_dawnstone:Lnet/minecraftforge/fluids/Fluid;"))
    private static Fluid redirectMoltenDawnstone() {
        return FluidRegister.FLUID_MOLTEN_DAWNSTONE;
    }

    @Redirect(method = "initStamps", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_molten_aluminum:Lnet/minecraftforge/fluids/Fluid;"))
    private static Fluid redirectMoltenAluminum() {
        return FluidRegister.FLUID_MOLTEN_ALUMINUM;
    }
}
