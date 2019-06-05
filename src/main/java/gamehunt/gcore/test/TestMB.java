package gamehunt.gcore.test;

import java.util.ArrayList;

import gamehunt.gcore.GCore;
import gamehunt.gcore.blocks.BlocksRegistry;
import gamehunt.gcore.multiblocks.IBlockFilter;
import gamehunt.gcore.multiblocks.IMultiblock;
import gamehunt.gcore.multiblocks.filters.AnyBlockFilter;
import gamehunt.gcore.multiblocks.filters.BasicBlockFilter;
import gamehunt.gcore.utils.Pair;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestMB implements IMultiblock{

	@Override
	public char[][][] getPattern() {
		// TODO Auto-generated method stub
		char [][][] pat = {
				{
				{'c','c','c'},//0
				{'c','c','c'},
				{'c','c','c'}
				},
				{
				{'c','c','c'},//1
				{'m','a','c'},
				{'c','c','c'}
				},
				{
				{'c','c','c'},//2
				{'c','c','c'},
				{'c','c','c'}
				}
		};
		return pat;
	}

	@Override
	public ArrayList<Pair<Character, IBlockFilter>> getValues() {
		// TODO Auto-generated method stub
		ArrayList<Pair<Character, IBlockFilter>> l = new ArrayList<>();
		l.add(Pair.make_pair('m',new BasicBlockFilter(BlocksRegistry.access("test_masterblock"))));
		l.add(Pair.make_pair('c', new BasicBlockFilter(Blocks.COBBLESTONE)));
		l.add(Pair.make_pair('a', new AnyBlockFilter()));
		return l;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "test_multiblock";
	}

	@Override
	public Item getActivationItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onStructureActivated(World worldIn, BlockPos pos) {
		// TODO Auto-generated method stub
		GCore.getModLog().info("Formed");
		return false;
	}

	@Override
	public boolean onStructureDestroyed(World worldIn, BlockPos pos) {
		GCore.getModLog().info("Destroyed");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onStructureClicked(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		// TODO Auto-generated method stub
		GCore.getModLog().info("Clicked");
		if(!worldIn.isRemote){
			playerIn.openGui(GCore.instance, 0, worldIn, pos.getX(), pos.getY(),pos.getZ());
		}
		return true;
	}

	

}
