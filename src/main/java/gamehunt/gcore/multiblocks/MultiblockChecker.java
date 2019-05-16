package gamehunt.gcore.multiblocks;

import java.util.ArrayList;

import gamehunt.gcore.GCore;
import gamehunt.gcore.utils.Pair;
import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;



public class MultiblockChecker {
	static Block getAssoc(ArrayList<Pair<Character,Block>>v, char c){
		for(Pair<Character,Block> pr : v){
			if(pr.first() == c){
				return pr.second();
			}
		}
		return null;
	}
	public static boolean checkAt(World w,BlockPos p){
	if(!w.isRemote){
		if(w.getTileEntity(p)!= null && w.getTileEntity(p) instanceof BasicMultiblockMasterTE){
			BasicMultiblockMasterTE e = (BasicMultiblockMasterTE) w.getTileEntity(p);
			IMultiblock m = e.getMultiblock();
			char[][][] pat = m.getPattern();
			for(int i=0;i<pat.length;i++){
				for(int j=0;j<pat.length;j++){
					if(pat[i].length != pat.length || pat.length != pat[i][j].length || pat[i].length != pat[i][j].length){
						GCore.getModLog().error("Multiblock"+m.getName()+"has invalid pattern!");
						return false;
					}
				}
			}
			char mval = '\0';
			for(Pair<Character,Block> pr : m.getValues()){
				if(pr.second() instanceof BasicMultiblockMaster){
					mval = pr.first();
					break;
				}
			}
			if(mval == '\0'){
				GCore.getModLog().error("Multiblock"+m.getName()+"has invalid pattern!");
				return false;
			}
			int l = pat.length;
			EnumFacing f = (EnumFacing) w.getBlockState(p).getProperties().get(BasicMultiblockMaster.FACING);
			int ox=0,oy=0,oz=0;
			boolean fl = false;
			for(int z=0;z<l;z++){
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
			for(int z=0;z<l;z++){
				for(int i=0;i<l;i++){
					for(int j=0;j<l;j++){
					if(!w.getBlockState(op.offset(f,j).offset(f.rotateY(), i).offset(EnumFacing.UP, z)).getBlock().equals(getAssoc(m.getValues(),pat[z][i][j]))){
						return false;
					}
				}
				}
			}
			return true;
		}
	}
	return false;
	}
}
