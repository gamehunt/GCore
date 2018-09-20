package gamehunt.gcore.containers;


import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public abstract class BasicContainer extends Container{
	
	public BasicContainer(InventoryPlayer inv){
		 for (int l = 0; l < 3; ++l) {

	            for (int j1 = 0; j1 < 9; ++j1) {
	                this.addSlotToContainer(new Slot(inv, j1 + (l + 1) * 9, 8 + j1 * 18, 84 + l * 18));
	            }
	        }

	        for (int i1 = 0; i1 < 9; ++i1) {

	            this.addSlotToContainer(new Slot(inv, i1, 8 + i1 * 18, 142));
	        }
	}

}
