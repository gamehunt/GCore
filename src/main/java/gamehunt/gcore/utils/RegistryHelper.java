package gamehunt.gcore.utils;

import org.reflections.Reflections;

public class RegistryHelper {
	public static void preConstructClasses(String classDir){
		Reflections ref = new Reflections(classDir);
        for (Class<?> cl : ref.getTypesAnnotatedWith(PreConstructed.class)) {
        	System.out.println("[INFO] Trying to pre construct class "+cl.getSimpleName());
            try {
				cl.getConstructor().newInstance();
			} catch (Exception e) {
				System.out.println("[ERROR] Failed to pre-construct class "+cl.getSimpleName()+":");
				e.printStackTrace();
			}
        }
	}
}
