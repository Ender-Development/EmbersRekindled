package teamroots.embers.compat.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.GroovyPropertyContainer;

public class GSContainer extends GroovyPropertyContainer {

    public final Melter melter = new Melter();
    public final Mixer mixer = new Mixer();

    public GSContainer() {
        addProperty(melter);
        addProperty(mixer);
    }

}
