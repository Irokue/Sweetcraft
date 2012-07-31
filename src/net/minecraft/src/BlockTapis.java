package net.minecraft.src;
 
import java.util.Random;
 
public class BlockTapis extends Block
{
    protected BlockTapis(int par1, int par2)
    {
        super(par1, par2, Material.grass);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.1F, 1.0F);
        setTickRandomly(true);
    }
 
    //Ceci est pour passer à travers le bloc. Enlevez-le si vous ne vous les pas avoir un effet "noclip".
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        int i = par1World.getBlockMetadata(par2, par3, par4) & 7;
 
        if (i >= 3)
        {
            return AxisAlignedBB.getBoundingBoxFromPool((double)par2 + minX, (double)par3 + minY, (double)par4 + minZ, (double)par2 + maxX, (float)par3 + 0.5F, (double)par4 + maxZ);
        }
        else
        {
            return null;
        }
    }
   
 
    public boolean isOpaqueCube()
    {
        return false;
    }
 
    public boolean renderAsNormalBlock()
    {
        return false;
    }
}