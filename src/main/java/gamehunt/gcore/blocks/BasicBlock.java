package gamehunt.gcore.blocks;

import gamehunt.gcore.utils.CreativeTabHandler;
import gamehunt.gcore.utils.CreativeTabHandler.TabType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

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

	@Override
	public boolean hasTileEntity(IBlockState state) {
		// TODO Auto-generated method stub
		return false;
	}

}
