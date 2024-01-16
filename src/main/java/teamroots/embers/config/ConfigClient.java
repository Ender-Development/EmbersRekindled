package teamroots.embers.config;

import net.minecraftforge.common.config.Config;
import teamroots.embers.Embers;

@Config(modid = Embers.MODID, category = "client", name = Embers.CFG_FOLDER + "client")
@Config.LangKey("cfg.embers.client")
public class ConfigClient {
    @Config.Name("Enable Particle Collision")
    @Config.Comment({
            "Whether or not particles should collide with blocks.",
            "Disabling this might significantly improve performance."
    })
    public static boolean enableParticleCollisions = true;

    @Config.Name("Enable Particles")
    @Config.Comment({
            "Whether or not particles are enabled.",
            "Disabling this will change the gameplay experience but significantly improve performance."
    })
    public static boolean enableParticles = true;
}
