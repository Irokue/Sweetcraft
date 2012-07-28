package net.minecraft.src;

import java.util.Random;

public class BlockDirt extends Block
{
    protected BlockDirt(int par1, int par2)
    {
        super(par1, par2, Material.ground);
    }
    
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Item.liquideSuspect.shiftedIndex;
    }
    
}
