package gamehunt.gcore.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class BasicButton extends GuiButton implements IGUIElement{

	GuiScreen base;
	
	public BasicButton(GuiScreen b,int buttonId, int x, int y, String buttonText) {
		super(buttonId, x, y, buttonText);
		
		base = b;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float partialTicks, int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		base.addButton(this);
	}

}
