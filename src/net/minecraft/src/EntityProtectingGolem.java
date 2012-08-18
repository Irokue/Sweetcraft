package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class EntityProtectingGolem extends EntityMob {

	/**
	 * Pseudo du joueur à qui appartient le Golem.
	 */
	public String owner = "";
	
	/**
	 * Position que prend le golem par défaut. (x, y, z)
	 */
	public int i, j, k;
	
	public EntityProtectingGolem(World par1World) {
		super(par1World);
		this.texture = "/mob/protectinggolem.png";
		moveSpeed = 0.25F;
		attackStrength = 10;
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, 0.25F, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, net.minecraft.src.EntityPlayer.class, 8F, 0, true));
		targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
	}
	
	public EntityProtectingGolem(World world, int i, int j, int k, String owner){
		this(world);
		this.owner = owner;
		this.i = i;
		this.j = j;
		this.k = k;
		tasks.addTask(3, new EntityAIDefendOwner(this, owner));
		tasks.addTask(4, new EntityAIReturnToPos(this, i, j, k));
	}
	
	public int getMaxHealth()
	{
		return 40;
	}
	
	/**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
    	if(!owner.equalsIgnoreCase(par1EntityPlayer.username) || getHealth() <= 0) return false;
        Minecraft.theMinecraft.displayGuiScreen(new GuiControlEntity(this, Minecraft.theMinecraft, worldObj));
    	return true;
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    protected boolean isAIEnabled()
    {
        return true;
    }
    
    protected String getHurtSound()
    {
    	return "mob.irongolem.hit";
    }

    protected String getDeathSound()
    {
    	return "mob.irongolem.death";
    }
    
    public void writeEntityToNBT(NBTTagCompound compound)
    {
    	super.writeEntityToNBT(compound);
    	compound.setString("Owner", owner);
    }
    
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    	super.readEntityFromNBT(compound);
    	owner = compound.getString("Owner");
    }
}
