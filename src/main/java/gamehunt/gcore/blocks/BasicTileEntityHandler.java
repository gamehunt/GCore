package gamehunt.gcore.blocks;

import javax.annotation.Nullable;

import gamehunt.gcore.tile.BaseTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BasicTileEntityHandler<T extends BaseTileEntity> extends BasicBlock{

	public BasicTileEntityHandler(Material materialIn, String name, boolean stdCreativeTab) {
		super(materialIn, name, stdCreativeTab);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public abstract Class<T> getTileEntityClass();

    @SuppressWarnings("unchecked")
	public T getTileEntity(IBlockAccess world, BlockPos position) {

        return (T) world.getTileEntity(position);
    }

    @Override
    public boolean hasTileEntity(IBlockState blockState) {

        return true;
    }

    @Nullable
    @Override
    public abstract T createTileEntity(World world, IBlockState blockState);

}
