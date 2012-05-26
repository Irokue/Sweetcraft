package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;

public class GuiChatOptions extends GuiScreen {

	private int xSize = 200, ySize = 100;
	private Minecraft mc;
	private GuiScreen guiParent;
	protected int imgId;
	
	private GuiCheckBox global, commerce, local;
	
	public GuiChatOptions(GuiScreen parent, Minecraft mc){
		this.guiParent = parent;
		this.mc = mc;
	}
	
	public void initGui(){
		controlList.clear();
		int displayX = (width - xSize) / 2;
        int displayY = (height - ySize) / 2;
        global = new GuiCheckBox(1, displayX + 5, displayY + 20, "Global", mc.gameSettings.getOptionOrdinalValue(EnumOptions.SEE_GLOBAL), mc.theMinecraft);
        commerce = new GuiCheckBox(2, displayX + 5 + 20 + 10 + fontRenderer.getStringWidth("Global"), displayY + 20, "Commerce", mc.gameSettings.getOptionOrdinalValue(EnumOptions.SEE_COMMERCE), mc.theMinecraft);
        local = new GuiCheckBox(3, displayX + 5 + 40 + 20 + fontRenderer.getStringWidth("Global") + fontRenderer.getStringWidth("Commerce"), displayY + 20, "Local", mc.gameSettings.getOptionOrdinalValue(EnumOptions.SEE_LOCAL), mc.theMinecraft);
        controlList.add(global);
        controlList.add(commerce);
        controlList.add(local);
	}
	
	public void drawScreen(int par1, int par2, float par3)
    {
        int displayX = (width - xSize) / 2;
        int displayY = (height - ySize) / 2;
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        drawBackgroundImage();
        drawTexturedModalRect(displayX, displayY, 0, 0, xSize, ySize);
        super.drawScreen(par1, par2, par3);
        fontRenderer.drawString("Vous souhaîtez voir..", displayX + 10, displayY + 7, 0x555555);
        fontRenderer.drawString("Vous souhaîtez parler dans..", displayX + 10, displayY + 47, 0x555555);
    }
	
	public void onGuiClosed(){
	}
	
	protected void actionPerformed(GuiButton checkbox){
		if(checkbox.id == 1){
			global.setChecked(!global.isChecked());
			mc.gameSettings.setOptionValue(EnumOptions.SEE_GLOBAL, global.intValue());
		}else if(checkbox.id == 2){
			commerce.setChecked(!commerce.isChecked());
			mc.gameSettings.setOptionValue(EnumOptions.SEE_COMMERCE, commerce.intValue());
		}else if(checkbox.id == 3){
			local.setChecked(!local.isChecked());
			mc.gameSettings.setOptionValue(EnumOptions.SEE_LOCAL, local.intValue());
		}
	}
	
	protected void drawBackgroundImage()
    {
        int displayX = (width - xSize) / 2;
        int displayY = (height - ySize) / 2;
        drawDefaultBackground();
        imgId = mc.renderEngine.getTexture("/gui/chatoptions.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(imgId);
        drawTexturedModalRect(displayX, displayY, 0, 0, xSize, ySize);
    }
	
}
