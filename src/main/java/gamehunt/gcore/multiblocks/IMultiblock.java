package gamehunt.gcore.multiblocks;

import java.util.ArrayList;

import gamehunt.gcore.utils.Pair;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IMultiblock {
	char[][][] getPattern();
	ArrayList<Pair<Character,IBlockFilter>> getValues();
	String getName();
	Item getActivationItem();
	
	boolean onStructureActivated(World worldIn, BlockPos pos);
	boolean onStructureDestroyed(World worldIn, BlockPos pos);
	
	boolean onStructureClicked(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ);
	
}
