package teamroots.embers.register;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IRegistrable<T extends IForgeRegistryEntry<T>> {
    void register(RegistryEvent.Register<T> event);
}
