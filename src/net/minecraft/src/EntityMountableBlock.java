package net.minecraft.src;

public class EntityMountableBlock extends Entity
{
    protected int orgBlockPosX;
    protected int orgBlockPosY;
    protected int orgBlockPosZ;
    protected int orgBlockID;

    public EntityMountableBlock(World world, double d, double d1, double d2)
    {
        super(world);
        noClip = true;
        preventEntitySpawning = true;
        width = 0.0F;
        height = 0.0F;
        setPosition(d, d1, d2);
    }

    public EntityMountableBlock(World world, EntityPlayer entityplayer, int i, int j, int k, float f, float f1, float f2)
    {
        super(world);
        noClip = true;
        preventEntitySpawning = true;
        width = 0.0F;
        height = 0.0F;
        orgBlockPosX = i;
        orgBlockPosY = j;
        orgBlockPosZ = k;
        orgBlockID = world.getBlockId(i, j, k);
        setPosition(f, f1, f2);
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer entityplayer)
    {
        if (!super.interact(entityplayer))
        {
            if (!worldObj.isRemote && (riddenByEntity == null || riddenByEntity == entityplayer))
            {
                entityplayer.mountEntity(this);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        onEntityUpdate();
    }

    /**
     * Gets called every tick from main Entity class
     */
    public void onEntityUpdate()
    {
        Profiler.startSection("entityBaseTick");

        if (riddenByEntity == null || riddenByEntity.isDead)
        {
            setDead();
        }
        else if (worldObj.getBlockId(orgBlockPosX, orgBlockPosY, orgBlockPosZ) != orgBlockID)
        {
            interact((EntityPlayer)riddenByEntity);
        }

        ticksExisted++;
        Profiler.endSection();
    }

    public void entityInit()
    {
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
    }
}