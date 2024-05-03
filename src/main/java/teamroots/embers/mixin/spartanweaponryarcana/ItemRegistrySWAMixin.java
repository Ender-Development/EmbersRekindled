package teamroots.embers.mixin.spartanweaponryarcana;

import com.vanyabaou.spartanweaponryarcana.init.ItemRegistrySWA;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.register.ToolMaterialRegister;

@Mixin(value = ItemRegistrySWA.class, remap = false)
public class ItemRegistrySWAMixin {
    @Redirect(method = "registerItems", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;tool_mat_dawnstone:Lnet/minecraft/item/Item$ToolMaterial;"))
    private static Item.ToolMaterial getToolMatDawnstone() {
        return ToolMaterialRegister.TOOL_MAT_DAWNSTONE;
    }
}
