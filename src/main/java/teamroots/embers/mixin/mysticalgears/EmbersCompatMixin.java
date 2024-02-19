package teamroots.embers.mixin.mysticalgears;

import com.rcx.mystgears.compatibility.EmbersCompat;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import soot.Registry;
import teamroots.embers.register.ItemRegister;

import java.util.Objects;

@Mixin(value = EmbersCompat.class, remap = false)
public class EmbersCompatMixin {

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/registry/GameRegistry;addShapedRecipe(Lnet/minecraft/util/ResourceLocation;Lnet/minecraft/util/ResourceLocation;Lnet/minecraft/item/ItemStack;[Ljava/lang/Object;)V"), index = 3)
    private static Object[] modifyEmbersCompatRecipe(Object[] params) {
        if (Objects.equals(params[0].toString(), "IDD")) {
            return new Object[]{"IDD", "C I", "IDD", 'I', "plateIron", 'D', "plateDawnstone", 'C', new ItemStack(ItemRegister.SHARD_EMBER)};
        }
        return new Object[]{"LNN", "B L", "LNN", 'L', "plateLead", 'N', "plateNickel", 'B', new ItemStack(Registry.WITCH_FIRE)};
    }
}
