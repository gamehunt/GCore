package gamehunt.gcore.utils.book;

import javax.annotation.Nullable;

import gamehunt.gcore.items.BasicItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class TutorialBookItem extends BasicItem{

	public TutorialBookItem(String name, boolean stdCreativeTab, String[] variants) {
		super(name, stdCreativeTab, variants);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack it = playerIn.getHeldItem(EnumHand.MAIN_HAND);
		if(it.getTagCompound() == null){
			it.setTagCompound(new NBTTagCompound());
			it.getTagCompound().setInteger("pg_cnt", 0);
			it.getTagCompound().setInteger("pg_c", 0);
			initBook(it);

		}
		//
		if(worldIn.isRemote){
			Minecraft.getMinecraft().displayGuiScreen(new TutorialBookGuiScreen(playerIn,it));
			
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	
	public NBTTagCompound addSection(String name, ItemStack s,@Nullable Integer reference){
		NBTTagCompound sects = s.getTagCompound();
		int last = sects.getInteger("total");
		//GCore.getModLog().info(last);
		NBTTagCompound sect = new NBTTagCompound();
		sect.setString("name", name);
		if(reference == null){
			reference = -1;
		}
		sect.setInteger("link", reference);
		sects.setTag("section#"+last,sect);
		last++;
		sects.setInteger("total", last);
		return sects.getCompoundTag("section#"+(last-1));
	}
	
	public NBTTagCompound addTitle(String name, NBTTagCompound section,@Nullable Integer reference){
		int last = section.getInteger("total");
		NBTTagCompound title = new NBTTagCompound();
		title.setString("name", name);
		if(reference == null){
			reference = -1;
		}
		title.setInteger("link", reference);
		section.setTag("title#"+last,title);
		last++;
		//GCore.getModLog().info(last);
		section.setInteger("total", last);
		return section.getCompoundTag("title#"+(last-1));
	}
	
	public NBTTagCompound addSubtitle(String name, NBTTagCompound title,@Nullable Integer reference){
		int last = title.getInteger("total");
		NBTTagCompound subtitle = new NBTTagCompound();
		subtitle.setString("name", name);
		if(reference == null){
			reference = -1;
		}
		subtitle.setInteger("link", reference);
		title.setTag("subtitle#"+last,subtitle);
		last++;
		title.setInteger("total", last);
		return title.getCompoundTag("subtitle#"+(last-1));
	}
	
	public void addPage(ResourceLocation page, ItemStack s){
		TutorialBookPage page_ = new TutorialBookPage(page);
		s.getTagCompound().setTag("page#"+s.getTagCompound().getInteger("pg_cnt"),page_.serialize());
		s.getTagCompound().setInteger("pg_cnt", s.getTagCompound().getInteger("pg_cnt")+1);
	}
	abstract public void initBook(ItemStack s);
	
}
