package net.minecraft.src;

public class EntityAIDefendOwner extends EntityAITarget {

	EntityLiving theEntity;
	World world;
	EntityPlayer owner;
	EntityLiving entityToAttack;
	
	public EntityAIDefendOwner(EntityLiving entity, String owner)
	{
		super(entity, 32F, false);
		this.theEntity = entity;
		this.world = entity.worldObj;
		this.owner = world.getPlayerEntityByName(owner);
		setMutexBits(3);
	}
	
	@Override
	public boolean shouldExecute() {
		if(owner == null) return false;
		entityToAttack = owner.getLastAttackingEntity();
		if(entityToAttack == null) return false;
		else
		{
			return func_48376_a(entityToAttack, false);
		}
	}

	public void startExecuting()
	{
		taskOwner.setAttackTarget(entityToAttack);
		super.startExecuting();
	}
	
}
