package net.minecraft.src;

import java.util.Random;

public class ItemSarbacane extends Item
{
    public ItemSarbacane(int par1)
    {
        super(par1);
        maxStackSize = 1;
		setMaxDamage(2000);
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        boolean flag = par3EntityPlayer.capabilities.isCreativeMode;

        if (flag || par3EntityPlayer.inventory.hasItem(Item.flechette.shiftedIndex))
        {
        	 int i = getMaxItemUseDuration(par1ItemStack) - par4;
             float f = (float)i / 20F;
             f = (f * f + f * 2.0F) / 3F;
        	
            EntityFlechette entityflechette = new EntityFlechette(par2World, par3EntityPlayer, f * 2.0F);
            
            if (f > 0.5F)
            {
                f = 0.5F;
            }
            
            if (f == 0.5F)
            {
                entityflechette.arrowCritical = true;
            }

            

            par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (!flag)
            {
                par3EntityPlayer.inventory.consumeInventoryItem(Item.flechette.shiftedIndex);
            }
            else
            {
                entityflechette.doesArrowBelongToPlayer = false;
            }

            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(entityflechette);
            }
        }
    }

    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 0x11940;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Item.flechette.shiftedIndex))
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 0;
    }
}
