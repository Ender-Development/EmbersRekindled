package teamroots.embers.mixin.valkyrielib;

import com.valkyrieofnight.vliblegacy.lib.block.adv.VLBlockStandard;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamroots.embers.Embers;

@Mixin(value = VLBlockStandard.class, remap = false)
public class VLBlockStandardMixin {
    @Shadow
    protected String blockName;

    @ModifyArg(method = "<init>(Ljava/lang/String;Lnet/minecraft/block/material/Material;)V", at = @At(value = "INVOKE", target = "Lcom/valkyrieofnight/vliblegacy/lib/block/adv/VLBlockStandard;setRegistryName(Ljava/lang/String;Ljava/lang/String;)Lnet/minecraftforge/registries/IForgeRegistryEntry;"), index = 0)
    private String modifyRegistryName(String name) {
        if (blockName.equals("modifier_ember")) {
            return Embers.MODID;
        }
        return name;
    }

    @ModifyArg(method = "<init>(Ljava/lang/String;Lnet/minecraft/block/material/Material;)V", at = @At(value = "INVOKE", target = "Lcom/valkyrieofnight/vliblegacy/lib/block/adv/VLBlockStandard;setTranslationKey(Ljava/lang/String;)Lnet/minecraft/block/Block;"), index = 0)
    private String modifyTranslationKey(String name) {
        if (blockName.equals("modifier_ember")) {
            return Embers.MODID + "." + blockName;
        }
        return name;
    }

}
