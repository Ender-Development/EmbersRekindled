package teamroots.embers.compat.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.GroovyPropertyContainer;

public class GSContainer extends GroovyPropertyContainer {

    public final Melter melter = new Melter();

    public GSContainer() {
        addProperty(melter);
    }

}
