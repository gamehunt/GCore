package gamehunt.gcore;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid = Constants.MODID)
public class ConfigHandler {
	@Comment("Log level")
	public static int log_level = 2;
	@Comment("Test code generation")
	public static boolean generateTestCode = false;
}
