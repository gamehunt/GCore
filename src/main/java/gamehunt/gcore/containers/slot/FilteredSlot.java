package gamehunt.gcore.containers.slot;

import akka.japi.Predicate;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class FilteredSlot extends Slot{
private final Predicate<ItemStack> pred;
	
	public FilteredSlot(IInventory inventoryIn, int index, int xPosition, int yPosition, Predicate<ItemStack> pred) {
		super(inventoryIn, index, xPosition, yPosition);
		this.pred = pred;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return pred.test(stack);
	}
}
