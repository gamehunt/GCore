package gamehunt.gcore.test;

import gamehunt.gcore.multiblocks.BasicMultiblockMasterTE;
import gamehunt.gcore.multiblocks.IMultiblock;

public class TestMB_MasterTE extends  BasicMultiblockMasterTE{

	@Override
	public IMultiblock getMultiblock() {
		// TODO Auto-generated method stub
		return new TestMB();
	}

}
