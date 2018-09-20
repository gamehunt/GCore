package gamehunt.gcore.utils;

import org.reflections.Reflections;

import gamehunt.gcore.GCore;

public class RegistryHelper {
	public static void preConstructClasses(String classDir){
		Reflections ref = new Reflections(classDir);
        for (Class<?> cl : ref.getTypesAnnotatedWith(PreConstructed.class)) {
        	GCore.getModLog().info("Trying to pre construct class "+cl.getSimpleName());
            try {
				cl.getConstructor().newInstance();
			} catch (Exception e) {
				GCore.getModLog().error("Failed to pre-construct class "+cl.getSimpleName()+":");
				e.printStackTrace();
			}
        }
	}
}
