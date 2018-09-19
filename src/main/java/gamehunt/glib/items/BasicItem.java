package gamehunt.glib.items;

import gamehunt.glib.utils.CreativeTabHandler;
import gamehunt.glib.utils.CreativeTabHandler.TabType;
import net.minecraft.item.Item;

public class BasicItem extends Item{
	public BasicItem(String name,boolean stdCreativeTab){
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		if(CreativeTabHandler.getTabByType(TabType.ITEM) != null && stdCreativeTab){
			this.setCreativeTab(CreativeTabHandler.getTabByType(TabType.ITEM));
		}
	}
	
	
}
