package teamroots.embers.mixin.soot;


import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import soot.Registry;

import teamroots.embers.register.BlockRegister;
import teamroots.embers.RegistryManager;


@Mixin(value = Registry.class, remap = false)
public class RegistryMixin {
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lsoot/util/HeatManager;register(Lnet/minecraft/block/Block;I)V"), index = 0)
    private static Block embers_registry_fix_1(Block block) {
        return (block == Blocks.FIRE) ? block : BlockRegister.ARCHAIC_LIGHT;
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lsoot/util/HeatManager;register(Lnet/minecraft/block/Block;Lsoot/util/IHeatProvider;)V"), index = 0)
    private static Block embers_registry_fix_2(Block block) {
        return BlockRegister.HEAT_COIL;
    }

    @Redirect(method = "registerBlocks()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getDefaultState()Lnet/minecraft/block/state/IBlockState;"))
    private static IBlockState embers_registry_fix_3(Block block) {
        if (block == RegistryManager.archaic_tile) {
            return BlockRegister.ARCHAIC_TILE.getDefaultState();
        } else if (block == RegistryManager.archaic_bricks) {
            return BlockRegister.ARCHAIC_BRICKS.getDefaultState();
        } else if (block == RegistryManager.sealed_planks) {
            return BlockRegister.SEALED_PLANKS.getDefaultState();
        }
        return block.getDefaultState();
    }
}
