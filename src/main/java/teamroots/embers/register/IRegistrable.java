package teamroots.embers.register;

import java.util.Collection;

public interface IRegistrable<T> {
    Collection<T> load();
    void register();
}
