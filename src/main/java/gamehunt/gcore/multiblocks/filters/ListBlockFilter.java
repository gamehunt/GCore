package gamehunt.gcore.multiblocks.filters;

import java.util.ArrayList;

import gamehunt.gcore.multiblocks.IBlockFilter;
import net.minecraft.block.Block;

public class ListBlockFilter implements IBlockFilter{

	ArrayList<Block> accepted;
	
	public ListBlockFilter(ArrayList<Block> l){
		accepted = l;
	}
	
	@Override
	public boolean check(Block b) {
		// TODO Auto-generated method stub
		return accepted.contains(b);
	}

}
