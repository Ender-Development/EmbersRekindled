package teamroots.embers.compat.groovyscript;

import com.cleanroommc.groovyscript.documentation.linkgenerator.BasicLinkGenerator;
import teamroots.embers.Embers;

public class EmbersLinkGenerator extends BasicLinkGenerator {
    @Override
    public String id() {
        return Embers.MODID;
    }
    @Override
    protected String domain() {
        return "https://github.com/Ender-Development/Embers-Extended-Life/";
    }
}