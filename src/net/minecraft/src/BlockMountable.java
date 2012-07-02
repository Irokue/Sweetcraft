package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class BlockMountable extends Block
{
    public BlockMountable(int i, Material material)
    {
        super(i, material);
    }

    public BlockMountable(int i, int j, Material material)
    {
        super(i, j, material);
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        return blockActivated(world, i, j, k, entityplayer, 0.5F, 0.5F, 0.5F, 0, 0, 0, 0);
    }

    public static boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, float f)
    {
        return blockActivated(world, i, j, k, entityplayer, 0.5F, f, 0.5F, 0, 0, 0, 0);
    }

    public static boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, float f, float f1, float f2, int l, int i1, int j1, int k1)
    {
        if (!world.isRemote)
        {
            List list = world.getEntitiesWithinAABB(net.minecraft.src.EntityMountableBlock.class, AxisAlignedBB.getBoundingBoxFromPool(i, j, k, (double)i + 1.0D, (double)j + 1.0D, (double)k + 1.0D).expand(1.0D, 1.0D, 1.0D));
            Iterator iterator = list.iterator();

            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }

                EntityMountableBlock entitymountableblock = (EntityMountableBlock)iterator.next();

                if (entitymountableblock.orgBlockPosX != i || entitymountableblock.orgBlockPosY != j || entitymountableblock.orgBlockPosZ != k)
                {
                    continue;
                }

                if (entitymountableblock.riddenByEntity == entityplayer)
                {
                    entitymountableblock.interact(entityplayer);
                    return true;
                }

                if (entitymountableblock.riddenByEntity != entityplayer && entitymountableblock.riddenByEntity != null)
                {
                    return true;
                }

                entitymountableblock.setDead();
                break;
            }
            while (true);

            float f3 = (float)i + f;
            float f4 = (float)j + f1;
            float f5 = (float)k + f2;

            if (l != i1)
            {
                int l1 = world.getBlockMetadata(i, j, k);

                if (l1 == j1)
                {
                    f3 = (float)(i + 1) - f2;
                    f5 = (float)k + f;
                }
                else if (l1 == i1)
                {
                    f3 = (float)(i + 1) - f;
                    f5 = (float)(k + 1) - f2;
                }
                else if (l1 == k1)
                {
                    f3 = (float)i + f2;
                    f5 = (float)(k + 1) - f;
                }
            }

            EntityMountableBlock entitymountableblock1 = new EntityMountableBlock(world, entityplayer, i, j, k, f3, f4, f5);
            world.spawnEntityInWorld(entitymountableblock1);
            entitymountableblock1.interact(entityplayer);
            return true;
        }
        else
       {
           return true;
        }
    }
}