package gamehunt.gcore.items;

import java.util.ArrayList;

import net.minecraft.item.Item;

public class ItemsRegistry{
	static ArrayList<Item> registry = new ArrayList<Item>();
	
	public static Item access(String reg_name){
		for(Item b : registry){
			if(b.getRegistryName().getResourcePath() == reg_name){
				return b;
			}
		}
		return null;
	}
	
	public static void register(Item b){
		if(!registry.contains(b)){
			registry.add(b);
		}
	}
	
	//Way to avoid cast problem
	public static Item[] getItemRegistry(){
		Item[] arr = new Item[registry.size()];
		for(int i=0;i<registry.size();i++){
			arr[i] = registry.get(i);
		}
		return arr;
	}
}
