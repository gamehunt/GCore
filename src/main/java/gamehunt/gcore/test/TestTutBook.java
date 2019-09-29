package gamehunt.gcore.test;

import gamehunt.gcore.utils.PreConstructed;
import gamehunt.gcore.utils.book.TutorialBookItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

@PreConstructed
public class TestTutBook extends TutorialBookItem{

	public TestTutBook() {
		super("test_tut_book", false, new String[]{});
		this.setCreativeTab(CreativeTabs.COMBAT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initBook(ItemStack s) {
		
		this.addSection("General", s, 1);
		this.addPage(new ResourceLocation("gcore:book/pg1"), s);
		this.addPage(new ResourceLocation("gcore:book/pg2"), s);
		this.addPage(new ResourceLocation("gcore:book/pg3"), s);
	}
	
	

}
