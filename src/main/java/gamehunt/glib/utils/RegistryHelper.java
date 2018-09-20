package gamehunt.glib.utils;

import org.reflections.Reflections;

import gamehunt.glib.GLib;

public class RegistryHelper {
	public static void preConstructClasses(String classDir){
		Reflections ref = new Reflections(classDir);
        for (Class<?> cl : ref.getTypesAnnotatedWith(PreConstructed.class)) {
        	GLib.getModLog().info("Trying to pre construct class "+cl.getSimpleName());
            try {
				cl.getConstructor().newInstance();
			} catch (Exception e) {
				GLib.getModLog().error("Failed to pre-construct class "+cl.getSimpleName()+":");
				e.printStackTrace();
			}
        }
	}
}
