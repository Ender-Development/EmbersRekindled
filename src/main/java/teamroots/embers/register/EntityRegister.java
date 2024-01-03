package teamroots.embers.register;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import teamroots.embers.Embers;
import teamroots.embers.entity.*;
import teamroots.embers.util.Misc;

import java.util.Collection;

public class EntityRegister implements IRegistrable<Entity> {
    public static final EntityRegister INSTANCE = new EntityRegister();

    private EntityRegister(){}

    @Override
    public Collection<Entity> load() {
        return null;
    }

    @Override
    public void register() {
        int id = 0;

        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID, "ember_packet"), EntityEmberPacket.class, "ember_packet", id++, Embers.instance, 64, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID, "ember_projectile"), EntityEmberProjectile.class, "ember_projectile", id++, Embers.instance, 64, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID, "ancient_golem"), EntityAncientGolem.class, "ancient_golem", id++, Embers.instance, 64, 1, true);
        EntityRegistry.registerEgg(new ResourceLocation(Embers.MODID, "ancient_golem"), Misc.intColor(48, 38, 35), Misc.intColor(79, 66, 61));
        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID, "ember_light"), EntityEmberLight.class, "ember_light", id++, Embers.instance, 64, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID, "magma_worm"), EntityMagmaWorm.class, "magma_worm", id++, Embers.instance, 128, 1, true);
        EntityRegistry.registerEgg(new ResourceLocation(Embers.MODID, "magma_worm"), Misc.intColor(48, 38, 35), Misc.intColor(79, 66, 61));
        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID, "magma_projectile"), EntityMagmaProjectile.class, "magma_projectile", id, Embers.instance, 64, 1, true);

    }

    @Override
    public void add(Entity registrable) {
        //NOOP
    }
}
