package gamehunt.gcore.utils.book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gamehunt.gcore.GCore;
import gamehunt.gcore.gui.BasicButton;
import gamehunt.gcore.gui.BasicGuiScreen;
import gamehunt.gcore.utils.Pair;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.util.ITooltipFlag.TooltipFlags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class TutorialBookGuiScreen extends BasicGuiScreen{
	private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation("textures/gui/book.png");
	private static final int ENTRIES_PER_PAGE = 12;
	ItemStack stack;
	int pg_cnt;
	int pg_c;
	int occupied;
	int entries_cnt;
	int links;
	EntityPlayer player;
	int ticks,upd_ticks;
	ArrayList<Pair<Integer,NBTTagCompound>> entries;
	public TutorialBookGuiScreen(EntityPlayer p,ItemStack s) {
		super(192, 192, BOOK_GUI_TEXTURES);
		player = p;
		BasicButton next = new BasicButton(this,-1,215,150,"Next");
		next.setWidth(30);
		this.addElement(next);
		BasicButton pr = new BasicButton(this,-2,130,150,"Prev");
		pr.setWidth(30);
		this.addElement(pr);
		// TODO Auto-generated constructor stub
		stack = s;
		if(s.getTagCompound() == null){
			s.setTagCompound(new NBTTagCompound());
		}
		pg_cnt = s.getTagCompound().getInteger("pg_cnt");
		pg_c = s.getTagCompound().getInteger("pg_c");
		ticks = 0;
		entries = new ArrayList<Pair<Integer,NBTTagCompound>>();
		NBTTagCompound nbt = stack.getTagCompound();
		int total = nbt.getInteger("total");
		entries_cnt+=total;
		for(int i=0;i<total;i++){
			entries.add(Pair.make_pair(0,nbt.getCompoundTag("section#"+i)));
			int tlt = nbt.getCompoundTag("section#"+i).getInteger("total");
			entries_cnt+=tlt;
			for(int j=0;j<tlt;j++){
			//	GCore.getModLog().info(1);
				int subtlts = nbt.getCompoundTag("section#"+i).getCompoundTag("title#"+j).getInteger("total");
				entries_cnt+=subtlts;
				entries.add(Pair.make_pair(1,nbt.getCompoundTag("section#"+i).getCompoundTag("title#"+j)));
				for(int z=0;z<subtlts;z++){
					entries.add(Pair.make_pair(2,nbt.getCompoundTag("section#"+i).getCompoundTag("title#"+j).getCompoundTag("subtitle#"+z)));
				}
			}
		}
		occupied = entries_cnt/ENTRIES_PER_PAGE;
		links = 0;
	}
	
	boolean checkToken(String tok){
		return tok.startsWith("<tag_") && tok.endsWith(">");
	}
	
	private void drawTooltip(List<String> tooltip, int mouseX, int mouseY, int posX, int posY, int itemWidth, int itemHeight){
        if(mouseX >= posX - itemWidth && mouseX <= posX +itemWidth && mouseY >= posY - itemHeight && mouseY <= posY + itemHeight) {
            drawHoveringText(tooltip, mouseX, mouseY);
        }
    }

	Pair<Integer,Integer> parseSpecToken(String tok,int x,int y,int mx,int my){
		String f = tok.substring(5,tok.length()-1);
		String[] args = f.split("_");
		if(args[0].equals("img")){
			GlStateManager.color(1F, 1F, 1F);
			String path = args[1].replace('|', '_');
			this.mc.renderEngine.bindTexture(new ResourceLocation(path));
			int w = Integer.valueOf(args[2]);
			int h = Integer.valueOf(args[3]);
			drawModalRectWithCustomSizedTexture(x, y, 0, 0, w, h, w, h);
			x = 130;
			y += h + 10;
		}else if(args[0].equals("recipe")){
			GlStateManager.color(1F, 1F, 1F);
			this.mc.renderEngine.bindTexture(new ResourceLocation("gcore:book/recipe_background.png"));
			int w = 200;
			int h = 200;
		//	GCore.getModLog().info(x+" "+y);
			int by = y;
			int bx = x;
			GlStateManager.pushMatrix();
			x = (int) Math.round(x/1.8);
			y = (int) Math.round(y/1.8);
			//GCore.getModLog().info(x+" "+y);
			GlStateManager.scale(1.8, 1.8, 1.8);
			drawModalRectWithCustomSizedTexture(x-35, y-35, 0, 0, w, h, w, h);
			GlStateManager.popMatrix();
			y = by;
			x = bx;
			String path = args[1].replace('|', '_');
			IRecipe r = CraftingManager.getRecipe(new ResourceLocation(path));
			if(r == null){
				return Pair.make_pair(x, y);
			}
			int size = r.canFit(2, 2)?2:3;
			x+=3;
			//y+=1;
			bx = x;
			by = y;
			RenderHelper.disableStandardItemLighting();
			RenderHelper.enableGUIStandardItemLighting();
			if(Boolean.valueOf(args[2])){
				
				for(int it=0;it<size;it++){
					for(int jt=0;jt<size;jt++){
						ItemStack[] stacks = it+jt*size >= r.getIngredients().size() ? Ingredient.EMPTY.getMatchingStacks() : r.getIngredients().get(it+jt*size).getMatchingStacks();
						//GCore.getModLog().info(stacks.length);
						if(stacks.length > 0){
							this.mc.getRenderItem().renderItemIntoGUI(stacks[upd_ticks%stacks.length], x, y);
							drawTooltip(stacks[upd_ticks%stacks.length].getTooltip(player, TooltipFlags.NORMAL),mx,my,x+8,y+8,10,10);
						}
						x+=17;
					}
					x = bx;
					y += 17;
				}
			}else{
				for(int jt=0;jt<size;jt++){
					for(int it=0;it<size;it++){
	                    ItemStack[] stacks = it+jt*size >= r.getIngredients().size() ? Ingredient.EMPTY.getMatchingStacks() : r.getIngredients().get(it+jt*size).getMatchingStacks();
	    				if(stacks.length > 0){
	    					this.mc.getRenderItem().renderItemIntoGUI(stacks[upd_ticks%stacks.length], x, y);
	    					itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, stack, x, y, "");
	    					drawTooltip(stacks[upd_ticks%stacks.length].getTooltip(player, TooltipFlags.NORMAL),mx,my,x+8,y+8,10,10);
	    				}
	    				x+=17;
					}
					x = bx;
					y += 17;
				}
			}
			ItemStack res = r.getRecipeOutput();
			this.mc.getRenderItem().renderItemIntoGUI(res, bx+98, by+15);
			itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, stack,  bx+98, by+15, "");
			drawTooltip(res.getTooltip(player, TooltipFlags.NORMAL),mx,my,bx+106,by+23,10,10);
			RenderHelper.enableStandardItemLighting();
			x = 130;
			y += h + 10;
		}else if(args[0].equals("model")){
			//GCore.getModLog().info(x+" "+y);
			ResourceLocation l = new ResourceLocation(args[2]);
			Item it = null;
			if(args[1].equals("block")){
				it = Item.getItemFromBlock(Block.REGISTRY.getObject(l));
			}else if(args[1].equals("item")){
				it = Item.REGISTRY.getObject(l);
			}else if(args[1].equals("entity")){
				GuiInventory.drawEntityOnScreen(x+Integer.valueOf(args[4]), y+Integer.valueOf(args[5]), Integer.valueOf(args[3]),-mx+x, -my+y,(EntityLivingBase) EntityList.createEntityByIDFromName(l, this.mc.world));					
				return Pair.make_pair(x+Integer.valueOf(args[4]), y+Integer.valueOf(args[5]));
			}else{
				GCore.getModLog().error("Unsupported model type: "+args[1]);
				return Pair.make_pair(x, y);
			}
			if(it == null){
				GCore.getModLog().error("Can't find model for "+args[1]);
				return Pair.make_pair(x, y);
			}
			GlStateManager.pushMatrix();
			x = (int) Math.round(x/Float.valueOf(args[3]));
			y = (int) Math.round(y/Float.valueOf(args[4]));
			GlStateManager.scale(Float.valueOf(args[3]), Float.valueOf(args[4]), Float.valueOf(args[5]));
			RenderHelper.disableStandardItemLighting();
			RenderHelper.enableGUIStandardItemLighting();
			this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(it), x, y);
			RenderHelper.enableStandardItemLighting();
			GlStateManager.popMatrix();
		}else if(args[0].equals("multiblock")){
			GCore.getModLog().warn("Multiblock tag is unsupported yet");
		}else if(args[0].equals("link")){
			int link = Integer.valueOf(args[2]);
			this.addButton(new TutorialBookLinkButton(this,link, x, y, args[1], 0, 0xffa500));
			x += args[1].length()*5;
			if(x >= 250){
				x = 130;
				y+=10;
			}
		}else{
			this.fontRenderer.drawString(tok, x, y, 0);
			x+=(tok.length()+1)*5;
			if(x>=250){
				x = 130;
				y+= 10;
			}
		}
		return Pair.make_pair(x, y);
	}
	
	void parsePage(NBTTagCompound page,int mx,int my){
		TutorialBookPage p = new TutorialBookPage(null);
		p.deserialize(page);

		int x = 130,y = 17;
		List<String> toks = p.getTokens();
		if(toks == null){
			return;
		}
		for(String tok : toks){
			if(tok.equals("\\t")){
				x+=20;
				if(x>=250){
					x = 130;
					y+=10;
				}
				continue;
			}
			if(tok.equals("\\n")){
				y+=10;
				x = 130;
				continue;
			}
			if(!checkToken(tok)){
				this.fontRenderer.drawString(tok, x, y,0);
				x+=(tok.length()+1)*5;
				if(x>=250){
					x = 130;
					y+=10;
				}
			}else{
				Pair<Integer,Integer> crds = parseSpecToken(tok,x,y,mx,my);
				x = crds.first();
				y = crds.second();
			}
		}

	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		if(pg_c <= occupied){
			for(int i=0;i<ENTRIES_PER_PAGE;i++){
				if(i+pg_c*ENTRIES_PER_PAGE >= entries.size()){
					break;
				}
				if(entries.get(i+pg_c*ENTRIES_PER_PAGE).second().getInteger("link") >= 0){
					this.addButton(new TutorialBookLinkButton(this,entries.get(i+pg_c*ENTRIES_PER_PAGE).second().getInteger("link"),130+entries.get(i+pg_c*ENTRIES_PER_PAGE).first()*10,17+i*10,entries.get(i+pg_c*ENTRIES_PER_PAGE).second().getString("name"),0,0xffa500));
				}else{
					this.fontRenderer.drawString(entries.get(i+pg_c*ENTRIES_PER_PAGE).second().getString("name"),130+entries.get(i+pg_c*ENTRIES_PER_PAGE).first()*10,17+i*10,0);
				}
			}
		}else{
			parsePage(stack.getTagCompound().getCompoundTag("page#"+(pg_c-occupied-1)),mouseX,mouseY);
		}
		this.fontRenderer.drawString(pg_c + 1 + " of " + (pg_cnt+occupied+1), this.width/2-40, 150, 0);
		ticks++;
		if(ticks + 1 == Integer.MAX_VALUE){
			ticks = 0;
		}
		if(ticks % 50 == 0){
			upd_ticks++;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		//GCore.getModLog().info(button.id+" "+(pg_cnt+occupied));
		if(button.id == -1){
			if(pg_c < pg_cnt+occupied){
				pg_c++;
			}
		}else if(button.id == -2){
			if(pg_c > 0){
				pg_c--;
			}
		}else{
			if(button.id >= 0 && button.id <= pg_cnt+occupied){
				
				pg_c = button.id;
			}
		}
		updateBook();
		super.actionPerformed(button);
	}
	
	private void updateBook(){
		stack.getTagCompound().setInteger("pg_cnt", pg_cnt);
		stack.getTagCompound().setInteger("pg_c", pg_c);
		this.buttonList.clear();
		this.initGui();
	}

}
