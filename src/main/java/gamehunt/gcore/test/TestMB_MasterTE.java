package gamehunt.gcore.test;

import gamehunt.gcore.multiblocks.BasicMultiblockMasterTE;
import gamehunt.gcore.multiblocks.IMultiblock;

public class TestMB_MasterTE extends  BasicMultiblockMasterTE{

	public TestMB_MasterTE() {
		super(0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IMultiblock getMultiblock() {
		// TODO Auto-generated method stub
		return new TestMB();
	}

}
