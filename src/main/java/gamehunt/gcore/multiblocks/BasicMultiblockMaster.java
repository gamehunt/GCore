package gamehunt.gcore.multiblocks;

import gamehunt.gcore.blocks.BasicOrientedBlock;
import net.minecraft.block.material.Material;

public abstract class BasicMultiblockMaster<T extends  BasicMultiblockMasterTE> extends BasicOrientedBlock<T>{
	
	
	public BasicMultiblockMaster(Material materialIn, String name, boolean stdCreativeTab) {
		super(materialIn, name, stdCreativeTab);
		// TODO Auto-generated constructor stub
	}

	

}
