package net.minecraft.src;

import java.util.ArrayList;

public class BlockChaise extends BlockMountable{
	
	public BlockChaise(int par1, int par2){
		super(par1, par2, Material.wood);
		blockIndexInTexture = 4;
	}

	public BlockChaise(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
	}

	/**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public static boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, float f)
    {
        return blockActivated(world, i, j, k, entityplayer, 0.5F, 0.5f, 0.5F, 0, 0, 0, 0);
    }


    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 30;
    }

	
    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int i = MathHelper.floor_double((double)((par5EntityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        int j = par1World.getBlockMetadata(par2, par3, par4) & 4;

        if (i == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2 | j);
        }

        if (i == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 1 | j);
        }

        if (i == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3 | j);
        }

        if (i == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0 | j);
        }
    }
    
    public void setBlockBoundsForItemRender(){
    	for (int k1 = 0; k1 < 10; k1++) //à voir par rapport aux nombres de bounds
        {
            if (k1 == 0)
            {
                setBlockBounds(0.2F, 0.0F, 0.2F, 0.3F, 0.4F, 0.3F);// faire tes coordonnées
             
            }
            if (k1 == 1)
            {
                setBlockBounds(0.2F, 0.0F, 0.7F, 0.3F, 0.4F, 0.8F);// faire tes coordonnées
            }
            if (k1 == 2)
            {
                setBlockBounds(0.7F, 0.0F, 0.2F, 0.8F, 0.4F, 0.2F);// faire tes coordonnées
            }
            if(k1 == 3)
            {
           	 setBlockBounds(0.7F,0.0F,0.7F, 0.8F,0.4F,0.7F);
            }
            if(k1 == 4)
            {
           	 setBlockBounds(0.2F, 0.0F, 0.2F, 0.3F, 0.4F, 0.3F);
            }
            if(k1 == 5)
            {
           	 setBlockBounds(0.2F, 0.4F, 0.2F, 0.8F, 0.5F, 0.8F);
            }
            if(k1 == 6)
            {
           	 setBlockBounds(0.2F, 0.5F, 0.2F, 0.3F, 1F, 0.3F);
            }
            if(k1 == 7)
            {
           	 setBlockBounds(0.7F, 0.5F, 0.2F, 0.8F, 1F, 0.3F);
            }
            if(k1 == 8)
            {
           	 setBlockBounds(0.2F, 0.6F, 0.2F, 0.8F, 0.7F, 0.3F);
            }
            if(k1 == 9)
            {
           	 setBlockBounds(0.2F, 0.8F, 0.2F, 0.8F, 0.9F, 0.3F);
            }
        }
    }
    
    /**
     * Adds to the supplied array any colliding bounding boxes with the passed in bounding box. Args: world, x, y, z,
     * axisAlignedBB, arrayList
     */
     public void getCollidingBoundingBoxes(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, ArrayList par6ArrayList)
     {
         int i = par1World.getBlockMetadata(par2, par3, par4);
         int j = i & 3;
         float f = 0.0F;
         float f1 = 0.5F;
         float f2 = 0.5F;
         float f3 = 1.0F;
  
         if ((i & 4) != 0)
         {
             f = 0.5F;
             f1 = 1.0F;
             f2 = 0.0F;
             f3 = 0.5F;
         }
  
         setBlockBounds(0.2F, 0.0f, 0.2f, 0.8F, 0.5f, 0.8F);
         super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
  
         if (j == 0)
         {
         	 setBlockBounds(0.7F, 0.5F, 0.2F, 0.8F, 1F, 0.8F);
             super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
         }
         else if (j == 1)
         {
         	 setBlockBounds(0.2F, 0.5F, 0.2F, 0.3F, 1F, 0.8F);
             super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
         }
         else if (j == 2)
         {
         	 setBlockBounds(0.2F, 0.5F, 0.7F, 0.8F, 1F, 0.8F);
             super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
         }
         else if (j == 3)
         {
         	 setBlockBounds(0.2F, 0.5F, 0.2F, 0.8F, 1F, 0.3F);
             super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
         }
  
         setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
     }
     
    
}
