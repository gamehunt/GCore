package gamehunt.gcore.gui;

import net.minecraft.client.gui.GuiScreen;

public abstract class BasicGUIElement implements IGUIElement{
	int x,y;
	GuiScreen base;
	public BasicGUIElement(GuiScreen base,int x,int y){
		this.x = x;
		this.y = y;
		this.base = base;
	}
	public GuiScreen getBase(){
		return base;
	}
	public abstract void render(float partialTicks,int mouseX, int mouseY);
}
