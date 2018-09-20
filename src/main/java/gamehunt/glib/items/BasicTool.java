package gamehunt.glib.items;

import java.util.Set;

import gamehunt.glib.utils.CreativeTabHandler;
import gamehunt.glib.utils.CreativeTabHandler.TabType;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.NonNullList;

public class BasicTool extends ItemTool implements IMetaHandler{
	String[] variants;
	String base_name;
	
	public BasicTool(String name,boolean stdCreativeTab,float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn,Set<Block> effectiveBlocksIn,String[] variants) {
		super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
		// TODO Auto-generated constructor stub
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
	}

	@Override
	public String[] getVariants() {
		// TODO Auto-generated method stub
		return variants;
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

}
