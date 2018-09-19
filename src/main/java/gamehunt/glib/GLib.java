package gamehunt.glib;

import org.apache.logging.log4j.Logger;

import gamehunt.glib.utils.RegistryHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = Constants.MODID,name = Constants.NAME,version = Constants.VERSION,dependencies = Constants.DEPENDENCIES)
public class GLib {
	static Logger logger;
	@Instance(owner = Constants.MODID)
	public static GLib instance = null;
	@SidedProxy(serverSide = Constants.COMMON_PROXY,clientSide = Constants.CLIENT_PROXY)
	public static CommonProxy proxy = null;
	public static Logger getModLog(){
		return logger;
	}
	@EventHandler
	public void preinit(FMLPreInitializationEvent e){
		logger = e.getModLog();
		logger.info("PreInit phase start");
		RegistryHelper.preConstructClasses("gamehunt.glib.blocks");
		RegistryHelper.preConstructClasses("gamehunt.glib.items");
		proxy.preinit(e);
		logger.info("PreInit phase end");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		logger.info("Init phase start");
		proxy.init(e);
		logger.info("Init phase end");
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent e){
		logger.info("PostInit phase start");
		proxy.postinit(e);
		logger.info("PostInit phase end");
	}
}
