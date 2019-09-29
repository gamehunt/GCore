package gamehunt.gcore.network;

import gamehunt.gcore.Constants;
import gamehunt.gcore.GCore;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.MODID);
	
	private static int id = 0;

	@SuppressWarnings("unchecked")
	public static void register(@SuppressWarnings("rawtypes") Class clazz, Side handlerSide) {
		GCore.getModLog().info("Registered "+handlerSide.name().toLowerCase()+" side packet: "+clazz.getSimpleName());
		INSTANCE.registerMessage(clazz, clazz, id++, handlerSide);
	}
}
