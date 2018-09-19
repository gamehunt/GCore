package gamehunt.glib.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;

public  class  BlocksRegistry {
	static ArrayList<Block> registry = new ArrayList<Block>();
	
	public static Block access(String reg_name){
		for(Block b : registry){
			if(b.getRegistryName().getResourcePath() == reg_name){
				return b;
			}
		}
		return null;
	}
	
	public static void register(Block b){
		if(!registry.contains(b)){
			registry.add(b);
		}
	}
	
	//Way to avoid cast problem
	public static Block[] getBlockRegistry(){
		Block[] arr = new Block[registry.size()];
		for(int i=0;i<registry.size();i++){
			arr[i] = registry.get(i);
		}
		return arr;
	}
}
