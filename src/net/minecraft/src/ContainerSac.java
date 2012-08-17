package net.minecraft.src;

import java.util.List;

public class ContainerSac extends Container
{
    private IInventory lowerChestInventory;
    private InventoryPlayer inventaire;
    private int numRows;

    public ContainerSac(IInventory par1IInventory, IInventory par2IInventory)
    {
    	inventaire=(InventoryPlayer) par1IInventory;
        lowerChestInventory = par2IInventory;
        numRows = par2IInventory.getSizeInventory() / 9;
        par2IInventory.openChest();
        int i = (numRows - 4) * 18;

        for (int j = 0; j < numRows; j++)
        {
            for (int i1 = 0; i1 < 9; i1++)
            {
                addSlot(new SlotSac(par2IInventory, i1 + j * 9, 8 + i1 * 18, 18 + j * 18));
            }
        }

        for (int k = 0; k < 3; k++)
        {
            for (int j1 = 0; j1 < 9; j1++)
            {
                addSlot(new Slot(par1IInventory, j1 + k * 9 + 9, 8 + j1 * 18, 103 + k * 18 + i));
            }
        }

        for (int l = 0; l < 9; l++)
        {
			if(inventaire.currentItem==l)
			{
				addSlot(new SlotBloque(inventaire, l, 8 + l * 18, 161 + i));
			}else
			{
				addSlot(new Slot(par1IInventory, l, 8 + l * 18, 161 + i));
			}
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return true;
    }

    /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    public ItemStack transferStackInSlot(int par1)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(par1);

        if (slot != null && slot.getHasStack())
        {
        	if (slot.getStack().itemID== Item.sac.shiftedIndex)
        	{
        		return null;
        	}
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par1 < numRows * 9)
            {
                if (!mergeItemStack(itemstack1, numRows * 9, inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, 0, numRows * 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    /**
     * Callback for when the crafting gui is closed.
     */
    public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
        super.onCraftGuiClosed(par1EntityPlayer);
        lowerChestInventory.closeChest();
    }
}
