package teamroots.embers.mixin.emberforged;

import com.Lenvill.fluids.MoltenFluid;
import net.minecraftforge.fluids.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.FluidRegister;

@Mixin(value = MoltenFluid.class, remap = false)
public class MoltenFluidMixin {
    @Redirect(method = "<init>", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_molten_tin:Lnet/minecraftforge/fluids/Fluid;"))
    private static Fluid redirectFluid() {
        return FluidRegister.FLUID_MOLTEN_TIN;
    }
}
