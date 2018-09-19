package gamehunt.glib.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BasicMetaItem extends BasicItem{

	String[] variants;
	
	public BasicMetaItem(String name,boolean stdCreativeTab,String[] variants) {
		super(name, stdCreativeTab);
		this.setHasSubtypes(true);
		this.variants = variants;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		for(int i=0;i<variants.length;i++){
			items.add(new ItemStack(this,1,i));
		}
	}
	
	public String[] getVariants(){
		return variants;
	}

}
