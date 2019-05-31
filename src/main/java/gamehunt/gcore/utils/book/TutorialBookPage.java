package gamehunt.gcore.utils.book;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import gamehunt.gcore.GCore;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class TutorialBookPage {
	List<String> tokens = new ArrayList<String>();
	
	ResourceLocation r;
	public TutorialBookPage(ResourceLocation res){
		r = res;
	}
	public List<String> getTokens(){
		InputStream is = null;
		try {
			//GCore.getModLog().info(r.getResourcePath());
			is = Minecraft.getMinecraft().getResourceManager().getResource(r).getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		Scanner inFile = new Scanner(is);
		while(inFile.hasNext()){
			String str = inFile.next();
			tokens.add(str);
		}
		inFile.close();
		return tokens;
	}
	public NBTTagCompound serialize(){
		NBTTagCompound nbt  = new NBTTagCompound();
		nbt.setString("domain", r.getResourceDomain());
		nbt.setString("path", r.getResourcePath());
	
		return nbt;
	}
	public void deserialize(NBTTagCompound nbt){
		r = new ResourceLocation(nbt.getString("domain"),nbt.getString("path"));
	}
}
