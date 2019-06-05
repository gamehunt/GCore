package gamehunt.gcore.multiblocks;



import gamehunt.gcore.blocks.BasicOrientedBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BasicMultiblockMaster<T extends  BasicMultiblockMasterTE> extends BasicOrientedBlock<T>{
	
	
	public BasicMultiblockMaster(Material materialIn, String name, boolean stdCreativeTab) {
		super(materialIn, name, stdCreativeTab);
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		// TODO Auto-generated method stub
		//GCore.getModLog().info("CALL");
		if(worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof BasicMultiblockMasterTE){
			BasicMultiblockMasterTE te = (BasicMultiblockMasterTE) worldIn.getTileEntity(pos);
			IMultiblock mb = te.getMultiblock();
			if(te.isActivated() && MultiblockChecker.checkAt(worldIn, pos, false)){
				return mb.onStructureClicked(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
			}else if(te.isActivated()){
				te.setActivated(worldIn, pos, false);
			}else if(mb.getActivationItem() == null || playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(mb.getActivationItem())){
				if(MultiblockChecker.checkAt(worldIn, pos, true)){
					return this.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
				}
			}
		}
		return true;
	}


	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		// TODO Auto-generated method stub
		if(worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof BasicMultiblockMasterTE){
			BasicMultiblockMasterTE te = (BasicMultiblockMasterTE) worldIn.getTileEntity(pos);
			IMultiblock mb = te.getMultiblock();
			if(te.isActivated()){
				mb.onStructureDestroyed(worldIn, pos);
			}
		}
	}


}

