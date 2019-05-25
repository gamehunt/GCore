package gamehunt.gcore.multiblocks;

import gamehunt.gcore.tile.BaseTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BasicMultiblockMasterTE extends BaseTileEntity implements ITickable{
	int tickCounter;
	boolean activated;
	
	public BasicMultiblockMasterTE(){
		tickCounter = 0;
	}
	
	public abstract IMultiblock getMultiblock();
	public boolean isActivated(){
		return activated;
	}
	public void setActivated(World w,BlockPos p,boolean a){
		activated = a;
		if(a){
			getMultiblock().onStructureActivated(w, p);
		}else{
			getMultiblock().onStructureDestroyed(w, p);
		}
		
	}
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		activated = compound.getBoolean("act");
		super.readFromNBT(compound);
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		compound.setBoolean("act", activated);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void update() {
		if(tickCounter % 10 == 0){
			if(isActivated()){
				if(!MultiblockChecker.checkAt(world, pos, false)){
					setActivated(world,pos,false);
				}
			}else if(MultiblockChecker.checkAt(world, pos, false)){
				setActivated(world,pos,true);
			}
		}
		tickCounter++;
		if(tickCounter-1 == Integer.MAX_VALUE){
			tickCounter = 0;
		}
	}
	
}
