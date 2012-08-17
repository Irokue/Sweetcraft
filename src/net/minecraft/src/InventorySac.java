package net.minecraft.src;

import java.util.Random;

public class InventorySac implements IInventory
{
    private ItemStack sacContents[];
	private ItemStack Conteneur;
	private EntityPlayer joueur;

    public InventorySac()
    {
        sacContents = new ItemStack[36];
    }
	
	public InventorySac(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer)
	{
		sacContents = new ItemStack[36];
		readFromNBT(par1ItemStack.getTagCompound());
		joueur= par2EntityPlayer;
	}

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 27;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return sacContents[par1];
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (sacContents[par1] != null)
        {
            if (sacContents[par1].stackSize <= par2)
            {
                ItemStack itemstack = sacContents[par1];
                sacContents[par1] = null;
                onInventoryChanged();
                return itemstack;
            }

            ItemStack itemstack1 = sacContents[par1].splitStack(par2);

            if (sacContents[par1].stackSize == 0)
            {
                sacContents[par1] = null;
            }

            onInventoryChanged();
            return itemstack1;
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (sacContents[par1] != null)
        {
            ItemStack itemstack = sacContents[par1];
            sacContents[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {

        sacContents[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit())
        {
            par2ItemStack.stackSize = getInventoryStackLimit();
        }

        onInventoryChanged();
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.sac";
    }

    /**
     * Reads from the given tag list and fills the slots in the inventory with the correct items.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        sacContents = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound.getByte("Slot") & 0xff;

            if (j >= 0 && j < sacContents.length)
            {
                sacContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
    }


    public NBTTagList writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < sacContents.length; i++)
        {
            if (sacContents[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                sacContents[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);
		return par1NBTTagCompound.getTagList("Items");
    }

	public NBTTagCompound contenu()
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < sacContents.length; i++)
        {
            if (sacContents[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                sacContents[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }
		NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
        par1NBTTagCompound.setTag("Items", nbttaglist);
		return par1NBTTagCompound;
    }
	
    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
       return !par1EntityPlayer.isDead && par1EntityPlayer.inventory.getCurrentItem().getItem().shiftedIndex == Item.sac.shiftedIndex;
    }

	public void onInventoryChanged()
	{
		ItemStack stack = new ItemStack(Item.sac);
		if (joueur.inventory.getCurrentItem().itemID==Item.sac.shiftedIndex)
		{
			stack.setTagCompound(contenu());
			joueur.inventory.setInventorySlotContents(joueur.inventory.currentItem,stack);
		}
	}

    public void openChest()
    {
       
    }

    public void closeChest()
    {
    	ItemStack stack = new ItemStack(Item.sac);
		if (joueur.inventory.getCurrentItem().itemID==Item.sac.shiftedIndex)
		{
			stack.setTagCompound(contenu());
			joueur.inventory.setInventorySlotContents(joueur.inventory.currentItem,stack);
		}
    }

}
