package gamehunt.gcore;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid = Constants.MODID,category="")
public class ConfigHandler {
	 public static GeneralCategory general = new GeneralCategory();

	 public static class GeneralCategory{
		  @Comment("Log level")
			public int log_level = 2;
			@Comment("Test code execution")
			public boolean executeTestCode = false;
	 }
	
}
