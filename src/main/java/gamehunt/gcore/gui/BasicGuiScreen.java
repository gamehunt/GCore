package gamehunt.gcore.gui;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class BasicGuiScreen extends GuiScreen implements IGUIElementsHandler{
	
	int w,h;
	ResourceLocation back;
	ArrayList<IGUIElement> e;
	
	protected FontRenderer fontRenderer;
	
	public BasicGuiScreen(int wi,int he,ResourceLocation _back) {
		
		w = wi;
		h = he;
		back = _back;
		
		e = new ArrayList<IGUIElement>();
		
		fontRenderer = Minecraft.getMinecraft().fontRenderer;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if(back != null){
			mc.getTextureManager().bindTexture(back);
			drawTexturedModalRect(w/2, 0, 0, 0, w, h);
		}else{
			this.drawDefaultBackground();
		}
        for(IGUIElement e : getElements()){
			e.render(partialTicks, mouseX, mouseY);
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}


	public void addElement(IGUIElement el){
		e.add(el);
	}
	
	@Override
	public void initGui() {
		for(IGUIElement el : e){
			el.init();
		}
		super.initGui();
	}

	@Override
	public ArrayList<IGUIElement> getElements(){
		return e;
	}
	
}
