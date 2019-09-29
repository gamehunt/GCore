package gamehunt.gcore.multiblocks.filters;

import gamehunt.gcore.multiblocks.IBlockFilter;
import net.minecraft.block.Block;

public class BasicBlockFilter implements IBlockFilter{
	Block b;
	public BasicBlockFilter(Block _b){
		b = _b;
	}

	@Override
	public boolean check(Block b) {
		// TODO Auto-generated method stub
		return b.equals(this.b);
	}

}
