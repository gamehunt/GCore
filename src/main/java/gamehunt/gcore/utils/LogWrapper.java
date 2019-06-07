package gamehunt.gcore.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import gamehunt.gcore.ConfigHandler;

public class LogWrapper {
	Logger l;
	public LogWrapper(Logger logger){
		l = logger;
	}
	
	public Logger getLogger(){
		return l;
	}
	
	public void message(Level info,String string){
		if(ConfigHandler.general.log_level != -1){
			l.log(info,string);
		}
	}
	
	public void leveledMessage(Level ml,String message, int level){
		if(level <= ConfigHandler.general.log_level && level >= 0){
			l.log(ml,message);
		}
	}
}
