package gamehunt.gcore;

import gamehunt.gcore.blocks.BasicTileEntityHandler;
import gamehunt.gcore.blocks.BlocksRegistry;
import gamehunt.gcore.items.IMetaHandler;
import gamehunt.gcore.items.ItemsRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = Constants.MODID)
public class EventHandler {
	

	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> e){
		GCore.getModLog().info("Please, ignore warnings below - all's ok");
		e.getRegistry().registerAll(BlocksRegistry.getBlockRegistry());
		for(Block b : BlocksRegistry.getBlockRegistry()){
			if(b.hasTileEntity(b.getDefaultState())){
				BasicTileEntityHandler tb = (BasicTileEntityHandler) b;
				GameRegistry.registerTileEntity(tb.getTileEntityClass(), tb.getRegistryName().toString());
			}
		}
	}
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> e){
		GCore.getModLog().info("Please, ignore warnings below - all's ok");
			for(Block b : BlocksRegistry.getBlockRegistry()){
				e.getRegistry().register(new ItemBlock(b).setRegistryName(b.getRegistryName()));
			}
	
		e.getRegistry().registerAll(ItemsRegistry.getItemRegistry());
		
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent e){
		//if(!noBlockReg){
			for(Block b: BlocksRegistry.getBlockRegistry()){
				ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0, new ModelResourceLocation(b.getRegistryName(), "inventory"));
			}
		//}
		//if(!noItemReg){
			for(Item i : ItemsRegistry.getItemRegistry()){
				if(i.getHasSubtypes()){
					//GLib.getModLog().info(i.getUnlocalizedName()+" has metadata");
					IMetaHandler m = (IMetaHandler) i;
					String[] vars = m.getVariants();
					int iter = 0;
					for(String name : vars){
						ModelLoader.setCustomModelResourceLocation(i, iter, new ModelResourceLocation(i.getRegistryName()+"_"+name, "inventory"));
					}
					continue;
				}
				ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
			}
		//}
	}
}
