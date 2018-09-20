package gamehunt.gcore.items;

import gamehunt.gcore.GCore;
import gamehunt.gcore.utils.CreativeTabHandler;
import gamehunt.gcore.utils.CreativeTabHandler.TabType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BasicItem extends Item implements IMetaHandler{
	
	String[] variants;
	String base_name;
	
	public BasicItem(String name,boolean stdCreativeTab,String[] variants){
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		if(CreativeTabHandler.getTabByType(TabType.ITEM) != null && stdCreativeTab){
			this.setCreativeTab(CreativeTabHandler.getTabByType(TabType.ITEM));
		}
		this.variants = variants;
		//GLib.getModLog().info(variants.length);
		if(variants.length > 1){
			//GLib.getModLog().info("Item "+name+" has metadata");
			setHasSubtypes(true);
		}
		base_name = name;
		ItemsRegistry.register(this);
	}
	
	

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		// TODO Auto-generated method stub
		if(getHasSubtypes() && stack.getMetadata() >= 0 && stack.getMetadata() < variants.length){
			return "item."+base_name+"_"+variants[stack.getMetadata()];
		}
		return base_name;
	}



	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(tab == this.getCreativeTab()){
			for(int i=0;i<variants.length;i++){
				items.add(new ItemStack(this,1,i));
			}
		}
	}



	@Override
	public String[] getVariants() {
		// TODO Auto-generated method stub
		return variants;
	}
	
	
}
