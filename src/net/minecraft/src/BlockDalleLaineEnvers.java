package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

public class BlockDalleLaineEnvers extends Block
{
 
	public static final String blockStepTypes[] =
	    {
	        "stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick", "netherBrick", "foin","lainerouge"
	    };
    /** Boolean used to seperate different states of blocks */
    private boolean blockType;

    public BlockDalleLaineEnvers(int par1, boolean par2)
    {
        super(par1, 64, Material.cloth);
        blockType = par2;
        setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
        setLightOpacity(255);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
        setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Adds to the supplied array any colliding bounding boxes with the passed in bounding box. Args: world, x, y, z,
     * axisAlignedBB, arrayList
     */
    public void getCollidingBoundingBoxes(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, ArrayList par6ArrayList)
    {
        setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        if (par2 == 0)
        {
            return blockIndexInTexture;
        }
        else
        {
            par2 = ~(par2 & 0xf);
            return 113 + ((par2 & 8) >> 3) + (par2 & 7) * 16;
        }
    }

    /**
     * Returns the block texture based on the side being looked at.  Args: side
     */
    public int getBlockTextureFromSide(int par1)
    {
        return getBlockTextureFromSideAndMetadata(par1, 0);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return blockType;
    }

    /**
     * Called when a block is placed using an item. Used often for taking the facing and figuring out how to position
     * the item. Args: x, y, z, facing
     */
    public void onBlockPlaced(World par1World, int par2, int par3, int par4, int par5)
    {
        if (par5 == 0 && !blockType)
        {
            int i = par1World.getBlockMetadata(par2, par3, par4);
            par1World.setBlockAndMetadataWithNotify(par2, par3, par4, Block.dalleLaine.blockID, i);
        }
        
    }


    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.demiDalleLaine.blockID;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return !blockType ? 1 : 2;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    protected int damageDropped(int par1)
    {
        return par1;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return blockType;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (blockType)
        {
            super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
        }

        if (par5 != 1 && par5 != 0 && !super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5))
        {
            return false;
        }

        int i = par2;
        int j = par3;
        int k = par4;
        i += Facing.offsetsXForSide[Facing.faceToSide[par5]];
        j += Facing.offsetsYForSide[Facing.faceToSide[par5]];
        k += Facing.offsetsZForSide[Facing.faceToSide[par5]];
        boolean flag = (par1IBlockAccess.getBlockId(i, j, k) == Block.demiDalleLaineEnvers.blockID);

        if (!flag)
        {
            if (par5 == 1)
            {
                return true;
            }

            if (par5 == 0 && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5))
            {
                return true;
            }
            else
            {
                return par1IBlockAccess.getBlockId(par2, par3, par4) != blockID || (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 32) != 0;
            }
        }

        if (par5 == 0)
        {
            return true;
        }

        if (par5 == 1 && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5))
        {
            return true;
        }
        else
        {
            return par1IBlockAccess.getBlockId(par2, par3, par4) != blockID || (par1IBlockAccess.getBlockId(par2, par3, par4)==Block.demiDalleLaineEnvers.blockID);
        }
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(Block.demiDalleLaine.blockID, 1, par1);
    }
}