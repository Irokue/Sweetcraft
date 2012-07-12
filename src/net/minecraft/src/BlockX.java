package net.minecraft.src;


public class BlockX extends Block
{
    public BlockX(int par1, int par2)
    {
        super(par1, par2, Material.ground);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public int getRenderType()
    {
        return 34;
    }

}