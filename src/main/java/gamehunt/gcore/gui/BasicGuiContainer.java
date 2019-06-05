package gamehunt.gcore.gui;

import java.util.ArrayList;

import gamehunt.gcore.containers.BasicContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class BasicGuiContainer extends GuiContainer implements IGUIElementsHandler{

	int w,h;
	ResourceLocation back;
	ArrayList<IGUIElement> e;
	public BasicGuiContainer(BasicContainer inventorySlotsIn,int wi,int he,ResourceLocation _back) {
		super(inventorySlotsIn);
		
		w = wi;
		h = he;
		back = _back;
		
		this.xSize = w;
		this.ySize = h;
		
		e = new ArrayList<IGUIElement>();
		// TODO Auto-generated constructor stub
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
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		if(back != null){
			mc.getTextureManager().bindTexture(back);
			drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		}else{
			this.drawDefaultBackground();
		}
        for(IGUIElement e : getElements()){
			e.render(partialTicks, mouseX, mouseY);
		}
	}

	@Override
	public ArrayList<IGUIElement> getElements(){
		return e;
	}

}
