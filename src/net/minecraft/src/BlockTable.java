package net.minecraft.src;

public class BlockTable extends Block {
	
	public BlockTable(int par1, int par2){
		super(par1, par2, Material.wood);
		blockIndexInTexture = 4;
	}

	public BlockTable(int par1, int par2, Material par3Material) {
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
        return 31;
    }

}
