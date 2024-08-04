package teamroots.embers.compat.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.GroovyPropertyContainer;

public class GSContainer extends GroovyPropertyContainer {

    public final Melter melter = new Melter();
    public final Mixer mixer = new Mixer();
    public final Stamper stamper = new Stamper();
    public final HeatCoil heatcoil = new HeatCoil();
    public final Alchemy alchemy = new Alchemy();
    public final ReactionChamber reactionchamber = new ReactionChamber();

    public GSContainer() {
        addProperty(melter);
        addProperty(mixer);
        addProperty(stamper);
        addProperty(heatcoil);
        addProperty(alchemy);
        addProperty(reactionchamber);
    }

}
