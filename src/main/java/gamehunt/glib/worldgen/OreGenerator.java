package gamehunt.glib.worldgen;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.RandomUtils;

import gamehunt.glib.GLib;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreGenerator implements IWorldGenerator{

	static ArrayList<BasicOre> ores = new ArrayList<BasicOre>();
	
	WorldGenMinable generator;
	
	public static void addOre(BasicOre ore){
		if(!ores.contains(ore)){
			ores.add(ore);
		}
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		// TODO Auto-generated method stub
		
		for(BasicOre ore : ores){
			int chance = ore.getChance();
			int min = ore.getMinHeight();
			int max = ore.getMaxHeight();
			int minVein = ore.getMinVeinSize();
			int maxVein = ore.getMaxVeinSize();
			int diff = max-min+1;
			for(int i=0;i<chance;i++){
				int x = chunkX * 16 + random.nextInt(16);
	            int y = min + random.nextInt(diff);
	            int z = chunkZ * 16 + random.nextInt(16);
	            GLib.getModLog().info("Generating at: "+x+" "+y+" "+z);
				if(ore.getDimension() == world.provider.getDimension()){
					if(ore.getPredicate() == null){
						generator = new WorldGenMinable(ore.getDefaultState(),RandomUtils.nextInt(minVein, maxVein+1));
					}else{
						generator = new WorldGenMinable(ore.getDefaultState(),RandomUtils.nextInt(minVein, maxVein+1),ore.getPredicate());
					}
					generator.generate(world, random, new BlockPos(x,y,z));
				}
			}
		}
		
	}

}
