package net.minecraft.src;

import net.minecraft.client.Minecraft;
import java.util.*;

public class ItemSac extends Item
{

	public ItemSac(int par3) {
		super(par3);
		maxStackSize = 1;
		
	}
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if (par2World.isRemote)
        {
            return null ;
        }
		if (!par1ItemStack.hasTagCompound())
		{
			par1ItemStack.setTagCompound(new NBTTagCompound("sac"));
		}
		par3EntityPlayer.displayGUISac(par1ItemStack);
		return par1ItemStack;
    }
}



