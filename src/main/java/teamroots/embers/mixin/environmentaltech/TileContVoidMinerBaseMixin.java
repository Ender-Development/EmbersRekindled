package teamroots.embers.mixin.environmentaltech;

import com.valkyrieofnight.et.base.tile.ETControllerEConsumer;
import com.valkyrieofnight.et.m_multiblocks.m_voidminer.tile.TileContVoidMinerBase;
import com.valkyrieofnight.vliblegacy.lib.inventory.VLItemHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.items.ItemHandlerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamroots.embers.compat.environmentaltech.EmbersAttributes;
import teamroots.embers.recipe.BoreOutput;
import teamroots.embers.recipe.RecipeRegistry;
import teamroots.embers.util.EmberGenUtil;
import teamroots.embers.util.WeightedItemStack;

import java.util.Random;

@Mixin(value = TileContVoidMinerBase.class, remap = false)
public abstract class TileContVoidMinerBaseMixin extends ETControllerEConsumer {
    private TileContVoidMinerBaseMixin(int capacity) {
        super(capacity);
    }

    @Shadow
    private Random rand;

    @Shadow
    private BlockPos drillTip;

    @Shadow
    private VLItemHandler output;

    @Inject(method = "onProcessComplete", at = @At("HEAD"))
    private void checkEmber(CallbackInfo ci) {
        Object modifierExists = this.modifierHandler.getAttributeFinalValue(EmbersAttributes.EM_EMBER.getAttributeID());
        if (modifierExists != null) {
            if (rand.nextFloat() < EmberGenUtil.getEmberDensity(this.getWorld().getSeed(), drillTip.getX(), drillTip.getZ())) {
                BoreOutput possible_output = RecipeRegistry.getBoreOutput(this.getWorld(), drillTip);
                if (possible_output != null) {
                    if (!possible_output.stacks.isEmpty()) {
                        WeightedItemStack picked = WeightedRandom.getRandomItem(rand, possible_output.stacks);
                        ItemHandlerHelper.insertItem(output, picked.getStack().copy(), false);
                    }
                }
            }
        }

    }
}

