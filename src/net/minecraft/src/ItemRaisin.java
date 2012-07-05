package net.minecraft.src;

public class ItemRaisin extends ItemFood {

	public ItemRaisin(int par1, int par2, boolean par3) {
		super(par1, par2, par3);
		
	}
	
	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        super.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
        return new ItemStack(Item.bowlEmpty);
    }

}
