package gamehunt.gcore.worldgen.structure;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;

public interface IStructure {
	public ResourceLocation getStructure();
	public int getGenerationChance();
	public boolean canGenerateHere(World w,BlockPos p);
	public ITemplateProcessor getProcessor();
}
