package gamehunt.glib;

import gamehunt.glib.network.NetworkHandler;
import gamehunt.glib.network.packets.CPacketSpawnParticles;
import gamehunt.glib.worldgen.OreGenerator;
import gamehunt.glib.worldgen.structure.StructureGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {

	public void preinit(FMLPreInitializationEvent e) {
		// TODO Auto-generated method stub
		NetworkHandler.register(CPacketSpawnParticles.class, Side.CLIENT);
	}

	public void init(FMLInitializationEvent e) {
		// TODO Auto-generated method stub
		GameRegistry.registerWorldGenerator(new OreGenerator(), 3);
		GameRegistry.registerWorldGenerator(new StructureGenerator(), 3);
	}

	public void postinit(FMLPostInitializationEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
