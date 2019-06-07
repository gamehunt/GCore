package gamehunt.gcore;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import gamehunt.gcore.utils.LogWrapper;
import gamehunt.gcore.utils.RegistryHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = Constants.MODID,name = Constants.NAME,version = Constants.VERSION)
public class GCore {
	static LogWrapper logger;
	@Instance(owner = Constants.MODID)
	public static GCore instance = null;
	@SidedProxy(serverSide = Constants.COMMON_PROXY,clientSide = Constants.CLIENT_PROXY)
	public static CommonProxy proxy = null;
	public static LogWrapper getModLog(){
		return logger;
	}
	@EventHandler
	public void preinit(FMLPreInitializationEvent e){
		logger = new LogWrapper(e.getModLog());
		logger.message(Level.INFO,"PreInit phase start");
		if(ConfigHandler.general.executeTestCode){
			RegistryHelper.preConstructClasses("gamehunt.gcore.test");
		}
		proxy.preinit(e);
		logger.message(Level.INFO,"PreInit phase end");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		logger.message(Level.INFO,"Init phase start");
		proxy.init(e);
		logger.message(Level.INFO,"Init phase end");
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent e){
		logger.message(Level.INFO,"PostInit phase start");
		proxy.postinit(e);
		logger.message(Level.INFO,"PostInit phase end");
	}
}
