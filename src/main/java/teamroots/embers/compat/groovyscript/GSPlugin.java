package teamroots.embers.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyBlacklist;
import com.cleanroommc.groovyscript.api.GroovyPlugin;
import com.cleanroommc.groovyscript.compat.mods.GroovyContainer;
import com.cleanroommc.groovyscript.compat.mods.GroovyPropertyContainer;
import com.cleanroommc.groovyscript.documentation.linkgenerator.LinkGeneratorHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamroots.embers.Embers;

public class GSPlugin implements GroovyPlugin {

    @GroovyBlacklist
    public static GSContainer instance;

    @Override
    public @Nullable GroovyPropertyContainer createGroovyPropertyContainer() {
        GSPlugin.instance = new GSContainer();
        return GSPlugin.instance;
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
        LinkGeneratorHooks.registerLinkGenerator(new EmbersLinkGenerator());
    }
}
