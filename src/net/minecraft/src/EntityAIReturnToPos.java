package net.minecraft.src;

public class EntityAIReturnToPos extends EntityAIBase {

	EntityLiving entity;
	int i, j, k;
	
	public EntityAIReturnToPos(EntityLiving entity, int i, int j, int k)
	{
		this.entity = entity;
		this.i = i;
		this.j = j;
		this.k = k;
		setMutexBits(3);
	}
	
	public boolean shouldExecute() {
		if(entity.posX != i || entity.posY != j || entity.posZ != k) return true;
		else return false;
	}
	
	public void startExecuting()
	{
		entity.getNavigator().tryMoveToXYZ(i, j, k, entity.moveSpeed);
		super.startExecuting();
	}

}
