package gamehunt.glib.blocks;

import gamehunt.glib.utils.CreativeTabHandler;
import gamehunt.glib.utils.CreativeTabHandler.TabType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BasicBlock extends Block{

	public BasicBlock(Material materialIn,String name,boolean stdCreativeTab) {
		super(materialIn);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		if(CreativeTabHandler.getTabByType(TabType.BLOCK) != null && stdCreativeTab){
			this.setCreativeTab(CreativeTabHandler.getTabByType(TabType.BLOCK));
		}
		BlocksRegistry.register(this);
		// TODO Auto-generated constructor stub
	}

}
