package teamroots.embers.config;

import net.minecraftforge.common.config.Config;
import teamroots.embers.Embers;
import teamroots.embers.config.tool.BlazingRayCategory;
import teamroots.embers.config.tool.CinderStaffCategory;
import teamroots.embers.config.tool.ShiftingScalesCategory;
import teamroots.embers.config.tool.ToolCategory;

@Config(modid = Embers.MODID, category = "tool", name = Embers.CFG_FOLDER + "tool")
@Config.LangKey("cfg.embers.tool")
public class ConfigTool {
    @Config.Name("Blazing Ray")
    @Config.LangKey("cfg.embers.tool.blazing_ray")
    @Config.Comment("Options about the Blazing Ray")
    public static final BlazingRayCategory BLAZING_RAY = new BlazingRayCategory();

    @Config.Name("Cinder Staff")
    @Config.LangKey("cfg.embers.tool.cinder_staff")
    @Config.Comment("Options about the Cinder Staff")
    public static final CinderStaffCategory CINDER_STAFF = new CinderStaffCategory();

    @Config.Name("Shifting Scales")
    @Config.LangKey("cfg.embers.tool.shifting_scales")
    @Config.Comment("Options about the Shifting Scales")
    public static final ShiftingScalesCategory SHIFTING_SCALES = new ShiftingScalesCategory();

    @Config.Name("Metal Tools")
    @Config.LangKey("cfg.embers.tool.metal")
    @Config.Comment({
            "Options about various tool stats.",
            "Each tool material can be disabled.",
            "A tool material won't be registered,",
            "if the corresponding material is missing."
    })
    public static final ToolCategory METAL_TOOL = new ToolCategory();

}
