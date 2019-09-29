package gamehunt.gcore;

import gamehunt.gcore.network.NetworkHandler;
import gamehunt.gcore.network.packets.CPacketSpawnParticles;
import gamehunt.gcore.test.GuiHandler;
import gamehunt.gcore.worldgen.OreGenerator;
import gamehunt.gcore.worldgen.structure.StructureGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
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
		NetworkRegistry.INSTANCE.registerGuiHandler(GCore.instance, new GuiHandler());
	}

	public void postinit(FMLPostInitializationEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
