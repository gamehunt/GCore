package gamehunt.gcore.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class BasicProgressBar extends BasicGUIElement{

	int prog;
	ResourceLocation text;
	int w,h;
	public BasicProgressBar(GuiScreen base,int x, int y,ResourceLocation tex,int width,int height) {
		super(base,x, y);
		text = tex;
		this.w = width;
		this.h = height;
		// TODO Auto-generated constructor stub
	}

	
	public void setProgress(int p){
		prog = p;
	}
	
	@Override
	public void render(float partialTicks, int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		if(prog != 0){
			Minecraft.getMinecraft().getTextureManager().bindTexture(text);
			getBase().drawTexturedModalRect(x, y, 0, 0, prog*w/100, h);
		}
	}


	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
