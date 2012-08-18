package net.minecraft.src;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;

public class GuiControlEntity extends GuiScreen {
	
	private EntityLiving entityControlled;
	private Minecraft mc;
	private World world;
	protected int imgId;
	
	private float xSize_lo;
	private float ySize_lo;
	
	private int xSize = 256, ySize = 95;
	private int guiLeft, guiTop;
	
	public GuiControlEntity(EntityLiving entityControlled, Minecraft mc,
			World world) {
		this.entityControlled = entityControlled;
		this.mc = mc;
		this.world = world;
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		controlList.clear();
		guiLeft = (width - xSize) / 2;
		guiTop = (height - ySize) / 2;
		controlList.add(new GuiButton(1, guiLeft + 100, guiTop + 55, 110, 20, "Désactiver le Golem"));
	}
	
	public void drawScreen(int par1, int par2, int par3)
	{
		xSize_lo = par1;
		ySize_lo = par2;
		drawBackgroundImage();
		if(entityControlled.getHealth() > entityControlled.getMaxHealth() - (entityControlled.getMaxHealth() / 4))
			GL11.glColor3f(0.5F, 1F, 0.3F);
		else if(entityControlled.getHealth() > entityControlled.getMaxHealth() / 2)
			GL11.glColor3f(0.6F, 0.8F, 0.3F);
		else if(entityControlled.getHealth() > entityControlled.getMaxHealth() / 4)
			GL11.glColor3f(0.8F, 0.8F, 0.2F);
		else if(entityControlled.getHealth() < entityControlled.getMaxHealth() / 4)
			GL11.glColor3f(1.0F, 0.3F, 0.3F);
		drawTexturedModalRect(
				guiLeft + 84,
				guiTop + 24,
				0,
				96,
				(int) (entityControlled.getHealth() / entityControlled.getMaxHealth()*150),
				15);
		super.drawScreen(par1, par2, par3);
		fontRenderer.drawString("Status du Golem", width / 2 - fontRenderer.getStringWidth("Status du Golem") / 2 + 30, guiTop + 7, 0x555555);
		fontRenderer.drawString("HP: " + entityControlled.getHealth() + "/" + entityControlled.getMaxHealth() , width / 2 - fontRenderer.getStringWidth("HP: " + entityControlled.getHealth() + "/" + entityControlled.getMaxHealth()) / 2 + 30, guiTop + 28, 0xffffff);
		 GL11.glEnable(GL11.GL_LIGHTING);
	        GL11.glEnable(GL11.GL_DEPTH_TEST);
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	        GL11.glPushMatrix();
	        GL11.glTranslatef(guiLeft + 35, guiTop + 90, 10F);
	        float f = 30F;
	        GL11.glScalef(-f, f, f);
	        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
	        float f1 = entityControlled.renderYawOffset;
	        float f2 = entityControlled.rotationYaw;
	        float f3 = entityControlled.rotationPitch;
	        float f4 = -10F;
	        float f5 = -100F;
	        GL11.glRotatef(9F, 0.0F, 1.0F, 0.0F);
	        GL11.glRotatef(-(float)Math.atan(f5 / 40F) * 5F, 1.0F, 0.0F, 0.0F);
	        entityControlled.renderYawOffset = (float)Math.atan(f4 / 40F) * 20F;
	        entityControlled.rotationYaw = (float)Math.atan(f4 / 40F) * 40F;
	        entityControlled.rotationPitch = -(float)Math.atan(f5 / 40F) * 20F;
	        entityControlled.rotationYawHead = entityControlled.rotationYaw;
	        GL11.glTranslatef(0.0F, entityControlled.yOffset, 0.0F);
	        RenderManager.instance.playerViewY = 180F;
	        RenderManager.instance.renderEntityWithPosYaw(entityControlled, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
	        entityControlled.renderYawOffset = f1;
	        entityControlled.rotationYaw = f2;
	        entityControlled.rotationPitch = f3;
	        GL11.glPopMatrix();
	        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		
	}
	
	public void actionPerformed(GuiButton button)
	{
		if(button.id == 1)
		{
			entityControlled.setDead();
			EntityItem item = new EntityItem(world, entityControlled.posX, entityControlled.posY, entityControlled.posZ, new ItemStack(Item.appleGold));
			item.motionY += world.rand.nextGaussian() * 0.05F;
            item.motionX += (world.rand.nextFloat() - world.rand.nextGaussian()) * 0.1F;
            item.motionZ += (world.rand.nextFloat() - world.rand.nextGaussian()) * 0.1F;
            world.spawnEntityInWorld(item);
            mc.thePlayer.closeScreen();
		}
	}
	
	protected void drawBackgroundImage()
	{
		drawDefaultBackground();
		imgId = mc.renderEngine.getTexture("gui/controlgui.png");
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.renderEngine.bindTexture(imgId);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
}
