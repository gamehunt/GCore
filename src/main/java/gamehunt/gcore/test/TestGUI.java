package gamehunt.gcore.test;

import gamehunt.gcore.containers.BasicContainer;
import gamehunt.gcore.gui.BasicButton;
import gamehunt.gcore.gui.BasicGuiContainer;
import net.minecraft.util.ResourceLocation;

public class TestGUI extends BasicGuiContainer{

	public TestGUI(BasicContainer inventorySlotsIn, int wi, int he, ResourceLocation _back) {
		super(inventorySlotsIn, wi, he, _back);
		
		this.addElement(new BasicButton(this, 0, 0, 0, "CLICK"));
		// TODO Auto-generated constructor stub
	}

}
