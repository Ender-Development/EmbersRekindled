package teamroots.embers.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyPlugin;
import com.cleanroommc.groovyscript.compat.mods.GroovyContainer;
import com.cleanroommc.groovyscript.compat.mods.GroovyPropertyContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamroots.embers.Embers;

public class GSPlugin implements GroovyPlugin {

    @Override
    public @Nullable GroovyPropertyContainer createGroovyPropertyContainer() {
        return GSContainer.instance = new GSContainer();
    }

    @Override
    public @NotNull String getModId() {
        return Embers.MODID;
    }

    @Override
    public @NotNull String getContainerName() {
        return Embers.MODNAME;
    }

    @Override
    public void onCompatLoaded(GroovyContainer<?> container) {

    }
}
