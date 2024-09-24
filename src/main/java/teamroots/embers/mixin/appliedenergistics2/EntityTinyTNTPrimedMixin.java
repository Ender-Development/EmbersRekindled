package teamroots.embers.mixin.appliedenergistics2;

import appeng.entity.EntityTinyTNTPrimed;
import net.minecraft.world.Explosion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.ExplosionEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityTinyTNTPrimed.class, remap = false)
public class EntityTinyTNTPrimedMixin {
    @Inject(method = "explode", at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"), cancellable = true)
    public void explodeMixin(CallbackInfo ci) {
        EntityTinyTNTPrimed tntPrimed = (EntityTinyTNTPrimed) (Object) this;
        boolean isCanceled = MinecraftForge.EVENT_BUS.post(new ExplosionEvent.Start(tntPrimed.world, new Explosion(tntPrimed.world, tntPrimed, tntPrimed.posX, tntPrimed.posY, tntPrimed.posZ, 0.2F, false, false)));
        if (isCanceled) {
            ci.cancel();
        }
    }
}
