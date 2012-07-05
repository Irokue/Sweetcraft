package net.minecraft.src;

import java.util.Random;

public class BlockRedstoneBlock extends Block {

	private boolean glowing;
	
	protected BlockRedstoneBlock(int par1, int par2, boolean par3) {
		super(par1, par2, Material.iron);

        if (par3)
        {
            setTickRandomly(true);
        }
        
		glowing = par3;
	}
	
	 public int tickRate()
	    {
	        return 30;
	    }
	 
	  /**
	     * Called whenever the block is added into the world. Args: world, x, y, z
	     */
	    public void onBlockAdded(World par1World, int par2, int par3, int par4)
	    {
	        if (par1World.getBlockMetadata(par2, par3, par4) == 0)
	        {
	            super.onBlockAdded(par1World, par2, par3, par4);
	        }

	        if (glowing)
	        {
	            par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, blockID);
	        }
	    }

	    /**
	     * Called whenever the block is removed.
	     */
	    public void onBlockRemoval(World par1World, int par2, int par3, int par4)
	    {
	        if (glowing)
	        {
	            par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, blockID);
	        }
	    }
	 
	 public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
	    {
	        glow(par1World, par2, par3, par4);
	        super.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);
	    }

	    /**
	     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
	     */
	    public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
	    {
	        glow(par1World, par2, par3, par4);
	        super.onEntityWalking(par1World, par2, par3, par4, par5Entity);
	    }

	    /**
	     * Called upon block activation (left or right click on the block.). The three integers represent x,y,z of the
	     * block.
	     */
	    public boolean blockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
	    {
	        glow(par1World, par2, par3, par4);
	        return super.blockActivated(par1World, par2, par3, par4, par5EntityPlayer);
	    }

	    /**
	     * The redstone ore glows.
	     */
	    private void glow(World par1World, int par2, int par3, int par4)
	    {
	        sparkle(par1World, par2, par3, par4);

	        if (blockID == Block.blockRedstone.blockID)
	        {
	            par1World.setBlockWithNotify(par2, par3, par4, Block.blockRedstoneGlowing.blockID);
	        }
	    }

	    /**
	     * Ticks the block if it's been scheduled
	     */
	    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	    {
	        if (blockID == Block.blockRedstoneGlowing.blockID)
	        {
	        	par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, blockID);
	            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, blockID);
	            par1World.setBlockWithNotify(par2, par3, par4, Block.blockRedstone.blockID);
	        }
	    }

	    /**
	     * A randomly called display update to be able to add particles or other items for display
	     */
	    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	    {
	        if (glowing)
	        {
	            sparkle(par1World, par2, par3, par4);
	        }
	    }
	    
	    /**
	     * Is this block indirectly powering the block on the specified side
	     */
	    public boolean isIndirectlyPoweringTo(World par1World, int par2, int par3, int par4, int par5)
	    {
	        if (par5 == 0)
	        {
	            return isPoweringTo(par1World, par2, par3, par4, par5);
	        }
	        else
	        {
	            return false;
	        }
	    }
	    
	    public boolean isPoweringTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	    {
	        if (!glowing)
	        {
	            return false;
	        }

	        int i = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

	        if (i == 5 && par5 == 1)
	        {
	            return false;
	        }

	        if (i == 3 && par5 == 3)
	        {
	            return false;
	        }

	        if (i == 4 && par5 == 2)
	        {
	            return false;
	        }

	        if (i == 1 && par5 == 5)
	        {
	            return false;
	        }

	        return i != 2 || par5 != 4;
	    }
	    
	    public boolean canProvidePower(){
	    	return true;
	    }

	    /**
	     * The redstone ore sparkles.
	     */
	    private void sparkle(World par1World, int par2, int par3, int par4)
	    {
	        Random random = par1World.rand;
	        double d = 0.0625D;

	        for (int i = 0; i < 6; i++)
	        {
	            double d1 = (float)par2 + random.nextFloat();
	            double d2 = (float)par3 + random.nextFloat();
	            double d3 = (float)par4 + random.nextFloat();

	            if (i == 0 && !par1World.isBlockOpaqueCube(par2, par3 + 1, par4))
	            {
	                d2 = (double)(par3 + 1) + d;
	            }

	            if (i == 1 && !par1World.isBlockOpaqueCube(par2, par3 - 1, par4))
	            {
	                d2 = (double)(par3 + 0) - d;
	            }

	            if (i == 2 && !par1World.isBlockOpaqueCube(par2, par3, par4 + 1))
	            {
	                d3 = (double)(par4 + 1) + d;
	            }

	            if (i == 3 && !par1World.isBlockOpaqueCube(par2, par3, par4 - 1))
	            {
	                d3 = (double)(par4 + 0) - d;
	            }

	            if (i == 4 && !par1World.isBlockOpaqueCube(par2 + 1, par3, par4))
	            {
	                d1 = (double)(par2 + 1) + d;
	            }

	            if (i == 5 && !par1World.isBlockOpaqueCube(par2 - 1, par3, par4))
	            {
	                d1 = (double)(par2 + 0) - d;
	            }

	            if (d1 < (double)par2 || d1 > (double)(par2 + 1) || d2 < 0.0D || d2 > (double)(par3 + 1) || d3 < (double)par4 || d3 > (double)(par4 + 1))
	            {
	                par1World.spawnParticle("reddust", d1, d2, d3, 0.0D, 0.0D, 0.0D);
	            }
	        }
	    }

}
