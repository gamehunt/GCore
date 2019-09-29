package gamehunt.gcore.utils;

import java.util.ArrayList;

import gamehunt.gcore.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabHandler {
	
	public static enum TabType{
		BLOCK,
		ITEM,
		CUSTOM
	};
	private static ArrayList<Pair<TypedCreativeTab,String>> tabs = new ArrayList<Pair<TypedCreativeTab,String>>();
	
	public static void createTab(TabType t,Item it,String id){
		CreativeTabs tab = new CreativeTabs(Constants.MODID){

			@Override
			public ItemStack getTabIconItem() {
				// TODO Auto-generated method stub
				return new ItemStack(it);
			}
			
		};
		tabs.add(Pair.make_pair(new TypedCreativeTab(tab,t), id));
	}
	
	public static CreativeTabs getTabById(String id){
		for(Pair<TypedCreativeTab,String> p : tabs){
			if(p.second() == id){
				return p.first().getTab();
			}
		}
		return null;
	}
	
	public static CreativeTabs getTabByType(TabType id){
		for(Pair<TypedCreativeTab,String> p : tabs){
			if(p.first().getType() == id){
				return p.first().getTab();
			}
		}
		return null;
	}
}
