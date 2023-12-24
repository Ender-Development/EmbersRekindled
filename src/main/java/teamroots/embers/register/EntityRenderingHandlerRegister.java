package teamroots.embers.register;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import teamroots.embers.entity.*;

import java.util.Collection;

public class EntityRenderingHandlerRegister implements IRegistrable<RegistryManager.tupleERH> {
    public static final EntityRenderingHandlerRegister INSTANCE = new EntityRenderingHandlerRegister();

    private EntityRenderingHandlerRegister() {
    }

    @Override
    public Collection<RegistryManager.tupleERH> load() {
        return null;
    }

    @Override
    public void register() {
        RenderingRegistry.registerEntityRenderingHandler(EntityEmberPacket.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityEmberProjectile.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityAncientGolem.class, new RenderAncientGolem.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityEmberLight.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityMagmaWorm.class, new RenderMagmaWorm.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityMagmaProjectile.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
    }

    @Override
    public void add(RegistryManager.tupleERH registrable) {
        // NOOP
    }
}
