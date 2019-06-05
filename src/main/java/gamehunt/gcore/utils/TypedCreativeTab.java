package gamehunt.gcore.utils;

import gamehunt.gcore.utils.CreativeTabHandler.TabType;
import net.minecraft.creativetab.CreativeTabs;

public class TypedCreativeTab {
	
	CreativeTabs tab;
	TabType t;
	
	public TypedCreativeTab(CreativeTabs tab, TabType t) {
		this.tab = tab;
		this.t = t;
	}
	
	public CreativeTabs getTab() {
		return tab;
	}
	public void setTab(CreativeTabs tab) {
		this.tab = tab;
	}
	public TabType getType() {
		return t;
	}
	public void setType(TabType t) {
		this.t = t;
	}

	
}
