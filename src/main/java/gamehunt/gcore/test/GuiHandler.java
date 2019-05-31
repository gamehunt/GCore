package gamehunt.gcore.test;

import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TestMB_MasterTE) {
            return new TestContainer(player.inventory, (TestMB_MasterTE) te);
        }
        return null; 
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		 BlockPos pos = new BlockPos(x, y, z);
	        TileEntity te = world.getTileEntity(pos);
	        if (te instanceof TestMB_MasterTE) {
	        	TestMB_MasterTE containerTileEntity = (TestMB_MasterTE) te;
	            return new TestGUI(new TestContainer(player.inventory, containerTileEntity),176,165,GuiFurnace.INVENTORY_BACKGROUND);
	        }
	        return null;
	}

}
