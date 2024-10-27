package teamroots.embers.mixin.environmentaltech;

import com.valkyrieofnight.et.m_multiblocks.m_components.m_modifiers.block.BlockModifierSpeed;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import teamroots.embers.config.ConfigCompat;

@Mixin(value = BlockModifierSpeed.class, remap = false)
public class BlockModifierSpeedMixin {
     @ModifyArg(method = "addAttributes", at = @At(value = "INVOKE", target = "Lcom/valkyrieofnight/et/api/m_multiblocks/m_components/m_modifiers/attribute/AttributeForgeEnergyMultiplier;<init>(F)V"))
     private float modifyEnergy(float energy) {
         return (float) ConfigCompat.ENVIRONMENTAL_TECH.speed_energy_increase / 100.0f;
     }
}
