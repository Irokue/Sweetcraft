package net.minecraft.src;

public class RecipesIngots
{
    private Object recipeItems[][];

    public RecipesIngots()
    {
        recipeItems = (new Object[][]
                {
                    new Object[] {
                        Block.blockGold, new ItemStack(Item.ingotGold, 9)
                    }, new Object[] {
                        Block.blockSteel, new ItemStack(Item.ingotIron, 9)
                    }, new Object[] {
                        Block.blockDiamond, new ItemStack(Item.diamond, 9)
                    }, new Object[] {
                        Block.blockLapis, new ItemStack(Item.dyePowder, 9, 4)
                    }, new Object[] {
                        Block.blockCoal, new ItemStack(Item.coal, 9)
                    }, new Object[] {
                        Block.blockRedstone, new ItemStack(Item.redstone, 9)
                    }
                });
    }

    /**
     * Adds the ingot recipes to the CraftingManager.
     */
    public void addRecipes(CraftingManager par1CraftingManager)
    {
        for (int i = 0; i < recipeItems.length; i++)
        {
            Block block = (Block)recipeItems[i][0];
            ItemStack itemstack = (ItemStack)recipeItems[i][1];
            par1CraftingManager.addRecipe(new ItemStack(block), new Object[]
                    {
                        "###", "###", "###", '#', itemstack
                    });
            par1CraftingManager.addRecipe(itemstack, new Object[]
                    {
                        "#", '#', block
                    });
        }

        par1CraftingManager.addRecipe(new ItemStack(Block.foin, 3), new Object[]
                {
                    "###", "###", "###", '#', Item.wheat
                });
        par1CraftingManager.addRecipe(new ItemStack(Item.wheat, 3), new Object[]
                {
                    "#", '#', Block.foin
                });

        par1CraftingManager.addRecipe(new ItemStack(Item.ingotGold), new Object[]
                {
                    "###", "###", "###", '#', Item.goldNugget
                });
        par1CraftingManager.addRecipe(new ItemStack(Item.goldNugget, 9), new Object[]
                {
                    "#", '#', Item.ingotGold
                });
    }
}
