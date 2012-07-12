package net.minecraft.src;

public class ItemVin extends ItemFood {

	protected ItemVin(int par1) {
		super(par1, 4, false);
		maxStackSize = 1;
	}
	
	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        super.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
        return new ItemStack(Item.vinVide);
    }

	public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.drink;
    }
	
}
