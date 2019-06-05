package gamehunt.gcore.test;

import gamehunt.gcore.multiblocks.BasicMultiblockMaster;
import gamehunt.gcore.utils.PreConstructed;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

@PreConstructed
public class TestMB_Master extends BasicMultiblockMaster<TestMB_MasterTE>{

	public TestMB_Master() {
		super(Material.IRON, "test_masterblock", true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<TestMB_MasterTE> getTileEntityClass() {
		// TODO Auto-generated method stub
		return TestMB_MasterTE.class;
	}

	@Override
	public TestMB_MasterTE createTileEntity(World world, IBlockState blockState) {
		// TODO Auto-generated method stub
		return new TestMB_MasterTE();
	}
	
	

}
