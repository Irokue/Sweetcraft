package net.minecraft.src;

public class ItemliquideSuspect extends ItemFood {

	protected ItemliquideSuspect(int par1) {
		super(par1, 4, false);
		maxStackSize = 1;
	}
	
	public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.drink;
    }
	
	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        super.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
        return new ItemStack(Item.glassBottle);
    }

}
