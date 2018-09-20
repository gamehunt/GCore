package gamehunt.gcore.items;

import gamehunt.gcore.utils.CreativeTabHandler;
import gamehunt.gcore.utils.CreativeTabHandler.TabType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.NonNullList;

public class BasicSword extends ItemSword implements IMetaHandler{

	String[] variants;
	String base_name;
	
	public BasicSword(String name,boolean stdCreativeTab,ToolMaterial material,String[] variants) {
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		if(stdCreativeTab && CreativeTabHandler.getTabByType(TabType.ITEM)!=null){
			this.setCreativeTab(CreativeTabHandler.getTabByType(TabType.ITEM));
		}
		this.variants = variants;
		if(variants.length > 1){
			setHasSubtypes(true);
		}
		base_name = name;
		ItemsRegistry.register(this);
		// TODO Auto-generated constructor stub
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
