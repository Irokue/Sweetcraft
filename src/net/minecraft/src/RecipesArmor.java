package net.minecraft.src;

public class RecipesArmor
{
    private String recipePatterns[][] =
    {
        {
            "XXX", "X X"
        }, {
            "X X", "XXX", "XXX"
        }, {
            "XXX", "X X", "X X"
        }, {
            "X X", "X X"
        }
    };
    private Object recipeItems[][];

    public RecipesArmor()
    {
        recipeItems = (new Object[][]
                {
                    new Object[] {
                        Item.leather, Item.maille, Item.ingotIron, Item.diamond, Item.ingotGold, Block.dirt
                    }, new Object[] {
                        Item.helmetLeather, Item.helmetChain, Item.helmetSteel, Item.helmetDiamond, Item.helmetGold, Item.helmetGlow
                    }, new Object[] {
                        Item.plateLeather, Item.plateChain, Item.plateSteel, Item.plateDiamond, Item.plateGold, Item.plateGlow
                    }, new Object[] {
                        Item.legsLeather, Item.legsChain, Item.legsSteel, Item.legsDiamond, Item.legsGold, Item.legsGlow
                    }, new Object[] {
                        Item.bootsLeather, Item.bootsChain, Item.bootsSteel, Item.bootsDiamond, Item.bootsGold, Item.bootsGlow
                    }
                });
    }

    /**
     * Adds the armor recipes to the CraftingManager.
     */
    public void addRecipes(CraftingManager par1CraftingManager)
    {
        for (int i = 0; i < recipeItems[0].length; i++)
        {
            Object obj = recipeItems[0][i];

            for (int j = 0; j < recipeItems.length - 1; j++)
            {
                Item item = (Item)recipeItems[j + 1][i];
                par1CraftingManager.addRecipe(new ItemStack(item), new Object[]
                        {
                            recipePatterns[j], 'X', obj
                        });
            }
        }
    }
}
