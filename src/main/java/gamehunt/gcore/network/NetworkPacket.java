package gamehunt.gcore.network;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

import gamehunt.gcore.GCore;
import gamehunt.gcore.utils.Pair;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

//Vazkii NetworkMessage with little modifications

public class NetworkPacket<REQ extends NetworkPacket> implements Serializable, IMessage, IMessageHandler<REQ, IMessage>{
	
	static ArrayList<Pair<Class,Field[]>> field_cache = new ArrayList<Pair<Class, Field[]>>();
	private static final HashMap<Class, Pair<Reader, Writer>> handlers = new HashMap();
	static {
		mapHandler(byte.class, NetworkPacket::readByte, NetworkPacket::writeByte);
		mapHandler(short.class, NetworkPacket::readShort, NetworkPacket::writeShort);
		mapHandler(int.class, NetworkPacket::readInt, NetworkPacket::writeInt);
		mapHandler(long.class, NetworkPacket::readLong, NetworkPacket::writeLong);
		mapHandler(float.class, NetworkPacket::readFloat, NetworkPacket::writeFloat);
		mapHandler(double.class, NetworkPacket::readDouble, NetworkPacket::writeDouble);
		mapHandler(boolean.class, NetworkPacket::readBoolean, NetworkPacket::writeBoolean);
		mapHandler(char.class, NetworkPacket::readChar, NetworkPacket::writeChar);
		mapHandler(String.class, NetworkPacket::readString, NetworkPacket::writeString);
		mapHandler(NBTTagCompound.class, NetworkPacket::readNBT, NetworkPacket::writeNBT);
		mapHandler(ItemStack.class, NetworkPacket::readItemStack, NetworkPacket::writeItemStack);
		mapHandler(BlockPos.class, NetworkPacket::readBlockPos, NetworkPacket::writeBlockPos);
	}
	
	
	
	public IMessage handle(MessageContext c){
		return null;
	}

	@Override
	public IMessage onMessage(REQ message, MessageContext ctx) {
		return message.handle(ctx);
	}

	static Field[] getValues(Class packet){
		for(Pair<Class,Field[]> p : field_cache){
			if(p.first() == packet){
			//	GLib.getModLog().info("Found "+p.second().length+" accesible fields in class "+packet.getSimpleName());
				return p.second();
			}
		}
		Field[] f =  packet.getFields();
		//GLib.getModLog().info("Found "+f.length+" accesible fields in class "+packet.getSimpleName());
		return f;
	}

	@Override
	public final void fromBytes(ByteBuf buf) {
		try {
			Class<?> clazz = getClass();
			Field[] clFields = getValues(clazz);
			for(Field f : clFields) {
				Class<?> type = f.getType();
				if(acceptField(f, type))
					readField(f, type, buf);
			}
		} catch(Exception e) {
			throw new RuntimeException("Error at reading packet " + this, e);
		}
	}

	@Override
	public final void toBytes(ByteBuf buf) {
		try {
			Class<?> clazz = getClass();
			Field[] clFields = getValues(clazz);
			for(Field f : clFields) {
				Class<?> type = f.getType();
				if(acceptField(f, type))
					writeField(f, type, buf);
			}
		} catch(Exception e) {
			throw new RuntimeException("Error at writing packet " + this, e);
		}
	}
	private final void writeField(Field f, Class clazz, ByteBuf buf) throws IllegalArgumentException, IllegalAccessException {
		Pair<Reader, Writer> handler = getHandler(clazz);
		handler.second().write(f.get(this), buf);
	}

	private final void readField(Field f, Class clazz, ByteBuf buf) throws IllegalArgumentException, IllegalAccessException {
		Pair<Reader, Writer> handler = getHandler(clazz);
		f.set(this, handler.first().read(buf));
	}
	private static Pair<Reader, Writer> getHandler(Class<?> clazz) {
		Pair<Reader, Writer> pair = handlers.get(clazz);
		if(pair == null)
			throw new RuntimeException("No R/W handler for  " + clazz);
		return pair;
	}
	private static boolean acceptField(Field f, Class<?> type) {
		int mods = f.getModifiers();
		if(Modifier.isFinal(mods) || Modifier.isStatic(mods) || Modifier.isTransient(mods) || f.isAnnotationPresent(ASyncField.class))
			return false;

		return  handlers.containsKey(type);
	}

	public static <T extends Object>void mapHandler(Class<T> type, Reader<T> reader, Writer<T> writer) {
		handlers.put(type, Pair.make_pair(reader, writer));
	}

	private static byte readByte(ByteBuf buf) {
		return buf.readByte();
	}

	private static void writeByte(byte b, ByteBuf buf) {
		buf.writeByte(b);
	}

	private static short readShort(ByteBuf buf) {
		return buf.readShort();
	}

	private static void writeShort(short s, ByteBuf buf) {
		buf.writeShort(s);
	}

	private static int readInt(ByteBuf buf) {
		return buf.readInt();
	}

	private static void writeInt(int i, ByteBuf buf) {
		buf.writeInt(i);
	}

	private static long readLong(ByteBuf buf) {
		return buf.readLong();
	}

	private static void writeLong(long l, ByteBuf buf) {
		buf.writeLong(l);
	}

	private static float readFloat(ByteBuf buf) {
		return buf.readFloat();
	}

	private static void writeFloat(float f, ByteBuf buf) {
		buf.writeFloat(f);
	}

	private static double readDouble(ByteBuf buf) {
		return buf.readDouble();
	}

	private static void writeDouble(double d, ByteBuf buf) {
		buf.writeDouble(d);
	}

	private static boolean readBoolean(ByteBuf buf) {
		return buf.readBoolean();
	}

	private static void writeBoolean(boolean b, ByteBuf buf) {
		buf.writeBoolean(b);
	}

	private static char readChar(ByteBuf buf) {
		return buf.readChar();
	}

	private static void writeChar(char c, ByteBuf buf) {
		buf.writeChar(c);
	}

	private static String readString(ByteBuf buf) {
		return ByteBufUtils.readUTF8String(buf);
	}

	private static void writeString(String s, ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, s);
	}

	private static NBTTagCompound readNBT(ByteBuf buf) {
		return ByteBufUtils.readTag(buf);
	}

	private static void writeNBT(NBTTagCompound cmp, ByteBuf buf) {
		ByteBufUtils.writeTag(buf, cmp);
	}

	private static ItemStack readItemStack(ByteBuf buf) {
		return ByteBufUtils.readItemStack(buf);
	}

	private static void writeItemStack(ItemStack stack, ByteBuf buf) {
		ByteBufUtils.writeItemStack(buf, stack);
	}

	private static BlockPos readBlockPos(ByteBuf buf) {
		return BlockPos.fromLong(buf.readLong());
	}

	private static void writeBlockPos(BlockPos pos, ByteBuf buf) {
		buf.writeLong(pos.toLong());
	}

	// Functional interfaces
	public static interface Writer<T extends Object> {
		public void write(T t, ByteBuf buf);
	}

	public static interface Reader<T extends Object> {
		public T read(ByteBuf buf);
	}
	
}
