package gamehunt.gcore.multiblocks;

import java.util.ArrayList;

import gamehunt.gcore.GCore;
import gamehunt.gcore.utils.Pair;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;



public class MultiblockChecker {
	static IBlockFilter getFilter(ArrayList<Pair<Character,IBlockFilter>>v, char c){
		for(Pair<Character,IBlockFilter> pr : v){
			if(pr.first() == c){
				return pr.second();
			}
		}
		return null;
	}

	public static boolean checkAt(World w,BlockPos p,boolean activate){
			if(!w.isRemote){
				if(w.getTileEntity(p)!= null && w.getTileEntity(p) instanceof BasicMultiblockMasterTE){
					BasicMultiblockMasterTE e = (BasicMultiblockMasterTE) w.getTileEntity(p);
					IMultiblock m = e.getMultiblock();
					char[][][] pat = m.getPattern();
					for(int i=0;i<pat.length;i++){
						for(int j=0;j<pat.length;j++){
							if(pat[i].length != pat[i][j].length){
								GCore.getModLog().error("Multiblock"+m.getName()+"has invalid pattern!");
								return false;
							}
						}
					}
					char mval = 'm';
					int h = pat.length;
					int l = pat[0].length;
					EnumFacing f = (EnumFacing) w.getBlockState(p).getProperties().get(BasicMultiblockMaster.FACING);
					int ox=0,oy=0,oz=0;
					boolean fl = false;
					for(int z=0;z<h;z++){
						for(int i=0;i<l;i++){
							for(int j=0;j<l;j++){
							if(pat[z][i][j] == mval){
								ox = i;
								oz = j;
								oy = z;
								fl = true;
								break;
							}
						}
						if(fl){
							break;
						}
						}
						if(fl){
							break;
						}
					}
					BlockPos op = p.offset(f.getOpposite(),oz).offset(f.rotateYCCW(), ox).offset(EnumFacing.DOWN, oy);
					for(int z=0;z<h;z++){
						for(int i=0;i<l;i++){
							for(int j=0;j<l;j++){
							if(!getFilter(m.getValues(),pat[z][i][j]).check(w.getBlockState(op.offset(f.getOpposite(),j).offset(f.rotateY(), i).offset(EnumFacing.UP, z)).getBlock())){
								//GCore.getModLog().info("Got "+w.getBlockState(op.offset(f,j).offset(f.rotateY(), i).offset(EnumFacing.UP, z)).getBlock().getLocalizedName());
								//GCore.getModLog().info("Need "+getAssoc(m.getValues(),pat[z][i][j]));
								//GCore.getModLog().info("At "+op.offset(f,j).offset(f.rotateY(), i).offset(EnumFacing.UP, z).getX()+" "+op.offset(f,j).offset(f.rotateY(), i).offset(EnumFacing.UP, z).getY()+" "+op.offset(f,j).offset(f.rotateY(), i).offset(EnumFacing.UP, z).getZ());
								return false;
							}
						}
						}
					}
					if(activate){
						e.setActivated(w, p, true);
						
					}
					return true;
				}
			}
			return false;
		}
	
}
