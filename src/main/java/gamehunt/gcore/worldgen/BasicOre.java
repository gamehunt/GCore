package gamehunt.gcore.worldgen;

import javax.annotation.Nullable;

import gamehunt.gcore.blocks.BasicBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.pattern.BlockMatcher;

public class BasicOre extends BasicBlock{

	int minh,maxh,vein,minvein,chance,dim;
	
	

	BlockMatcher predicate;
	public BasicOre(Material materialIn, String name, boolean stdCreativeTab,int minh,int maxh,int maxveinsize,int minveinsize,int chance,int dim,@Nullable BlockMatcher predicate) {
		super(materialIn, name, stdCreativeTab);
		// TODO Auto-generated constructor stub
		
		this.minh = minh;
		this.maxh = maxh;
		this.vein = maxveinsize;
		this.minvein = minveinsize;
		this.chance = chance;
		this.dim = dim;
		this.predicate = predicate;
	}
	
	public int getMinHeight(){
		return minh;
	}
	public int getMaxHeight(){
		return maxh;
	}
	public int getMaxVeinSize(){
		return vein;
	}
	public int getMinVeinSize(){
		return minvein;
	}
	public int getChance(){
		return chance;
	}
	public int getDimension() {
		return dim;
	}

	public BlockMatcher getPredicate() {
		return predicate;
	}
	

}
