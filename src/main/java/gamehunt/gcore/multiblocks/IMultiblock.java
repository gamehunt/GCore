package gamehunt.gcore.multiblocks;

import java.util.ArrayList;

import gamehunt.gcore.utils.Pair;
import net.minecraft.block.Block;

public interface IMultiblock {
	char[][][] getPattern();
	ArrayList<Pair<Character,Block>> getValues();
	String getName();
}
