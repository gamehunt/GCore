package gamehunt.gcore.utils.book;

import gamehunt.gcore.gui.BasicButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class TutorialBookLinkButton extends BasicButton{

	int color,a_color;
	
	public TutorialBookLinkButton(GuiScreen b, int buttonId, int x, int y, String buttonText,int color,int active_color) {
		super(b, buttonId, x, y, buttonText);
		this.color = color;
		this.a_color = active_color;
		this.setWidth(buttonText.length()*5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if(this.visible){
			this.hovered = ((mouseX >= this.x) && (mouseY >= this.y) && (mouseX < this.x + this.width) && (mouseY < this.y + this.height));
			//GCore.getModLog().info(this.hovered+" "+(mouseX >= this.x)+" "+(mouseY >= this.y)+" "+(mouseX < this.x + this.width)+" "+(mouseY < this.y + this.height));
			int c = this.hovered?a_color:color;
			//GCore.getModLog().info(c);
			mc.fontRenderer.drawString("§n"+displayString, x, y, c);
			//mc.fontRenderer.
		}
	}
	
	

}
