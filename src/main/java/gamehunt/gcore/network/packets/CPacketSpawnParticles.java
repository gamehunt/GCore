package gamehunt.gcore.network.packets;

import gamehunt.gcore.network.NetworkPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CPacketSpawnParticles extends NetworkPacket<CPacketSpawnParticles>{
	public int particle_id;
	public double x,y,z;
	public CPacketSpawnParticles(){}
	public CPacketSpawnParticles(EnumParticleTypes t,double x,double y,double z){
		this.particle_id = t.getParticleID();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	@Override
	public IMessage handle(MessageContext c) {
		// TODO Auto-generated method stub
		Minecraft.getMinecraft().player.world.spawnAlwaysVisibleParticle(particle_id, x, y, z, 0, 1.0f, 0, 0);
		return null;
	}
	
	
}
