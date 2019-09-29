package gamehunt.gcore.multiblocks;

import net.minecraft.block.Block;

public interface IBlockFilter {
	boolean check(Block b);
}
