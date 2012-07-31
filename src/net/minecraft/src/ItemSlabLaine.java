package net.minecraft.src;

public class ItemSlabLaine extends ItemBlock
{
    public ItemSlabLaine(int par1)
    {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public int getIconFromDamage(int par1)
    {
        return Block.dalleLaine.getBlockTextureFromSideAndMetadata(2, par1);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int par1)
    {
        return par1;
    }

    public String getItemNameIS(ItemStack par1ItemStack)
    {
        int i = par1ItemStack.getItemDamage();

        if (i < 0 || i >= BlockDalleLaine.blockStepTypes.length)
        {
            i = 0;
        }

        return (new StringBuilder()).append(super.getItemName()).append(".").append(BlockDalleLaine.blockStepTypes[i]).toString();
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS !
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7)
    {
        if (par1ItemStack.stackSize == 0)
        {
            return false;
        }

        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6))
        {
            return false;
        }

        int i = par3World.getBlockId(par4, par5, par6);
        int j = par3World.getBlockMetadata(par4, par5, par6);
		if ((par7 == 1) && i == Block.demiDalleLaine.blockID && j == par1ItemStack.getItemDamage())
		{
		
			if (par3World.checkIfAABBIsClear(Block.dalleLaine.getCollisionBoundingBoxFromPool(par3World, par4, par5, par6)) && par3World.setBlockAndMetadataWithNotify(par4, par5, par6, Block.dalleLaine.blockID, j))
            {
                par3World.playSoundEffect((float)par4 + 0.5F, (float)par5 + 0.5F, (float)par6 + 0.5F, Block.dalleLaine.stepSound.getStepSound(), (Block.dalleLaine.stepSound.getVolume() + 1.0F) / 2.0F, Block.dalleLaine.stepSound.getPitch() * 0.8F);
                par1ItemStack.stackSize--;
            }

            return true;
		}
		if ((par7 == 0) && i == Block.demiDalleLaineEnvers.blockID && j == par1ItemStack.getItemDamage())
		{
		
			if (par3World.checkIfAABBIsClear(Block.dalleLaine.getCollisionBoundingBoxFromPool(par3World, par4, par5, par6)) && par3World.setBlockAndMetadataWithNotify(par4, par5, par6, Block.dalleLaine.blockID, j))
            {
                par3World.playSoundEffect((float)par4 + 0.5F, (float)par5 + 0.5F, (float)par6 + 0.5F, Block.dalleLaine.stepSound.getStepSound(), (Block.dalleLaine.stepSound.getVolume() + 1.0F) / 2.0F, Block.dalleLaine.stepSound.getPitch() * 0.8F);
                par1ItemStack.stackSize--;
            }

            return true;
		}

        if (func_50087_b(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7))
        {
            return true;
        }
        else
        {
            return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7);
        }
    }

    private static boolean func_50087_b(ItemStack par0ItemStack, EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5, int par6)
    {
        if (par6 == 0)
        {
            par4--;
        }

        if (par6 == 1)
        {
            par4++;
        }

        if (par6 == 2)
        {
            par5--;
        }

        if (par6 == 3)
        {
            par5++;
        }

        if (par6 == 4)
        {
            par3--;
        }

        if (par6 == 5)
        {
            par3++;
        }

        int i = par2World.getBlockId(par3, par4, par5);
        int j = par2World.getBlockMetadata(par3, par4, par5);

        if (i == Block.demiDalleLaine.blockID && j == par0ItemStack.getItemDamage())
        {
            if (par2World.checkIfAABBIsClear(Block.dalleLaine.getCollisionBoundingBoxFromPool(par2World, par3, par4, par5)) && par2World.setBlockAndMetadataWithNotify(par3, par4, par5, Block.dalleLaine.blockID, j))
            {
                par2World.playSoundEffect((float)par3 + 0.5F, (float)par4 + 0.5F, (float)par5 + 0.5F, Block.dalleLaine.stepSound.getStepSound(), (Block.dalleLaine.stepSound.getVolume() + 1.0F) / 2.0F, Block.dalleLaine.stepSound.getPitch() * 0.8F);
                par0ItemStack.stackSize--;
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
