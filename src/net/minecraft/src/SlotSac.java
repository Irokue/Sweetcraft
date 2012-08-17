package net.minecraft.src;

public class SlotSac extends Slot {

    public SlotSac(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}

	public boolean isItemValid(ItemStack par1ItemStack)
    {
    	if (par1ItemStack.itemID == Item.sac.shiftedIndex)
    	{
    		return false;
    	}
    	return true;
    }
}
