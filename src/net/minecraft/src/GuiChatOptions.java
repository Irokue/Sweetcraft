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
	private GuiRadioButton talkGlobal, talkCommerce, talkLocal;
	
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
        talkGlobal = new GuiRadioButton(4, displayX + 5, displayY + 60, "Global", true, mc.theMinecraft);
        talkCommerce = new GuiRadioButton(5, displayX + 5 + 20 + 10 + fontRenderer.getStringWidth("Global"), displayY + 60, "Commerce", true, mc.theMinecraft);
        talkLocal = new GuiRadioButton(6, displayX + 5 + 40 + 20 + fontRenderer.getStringWidth("Global") + fontRenderer.getStringWidth("Commerce"), displayY + 60, "Local", true, mc.theMinecraft);
        controlList.add(global);
        controlList.add(commerce);
        controlList.add(local);
        controlList.add(talkGlobal);
        controlList.add(talkCommerce);
        controlList.add(talkLocal);
        talkGlobal.setChecked(mc.gameSettings.getOptionOrdinalValue(EnumOptions.TALK_GLOBAL));
        talkCommerce.setChecked(mc.gameSettings.getOptionOrdinalValue(EnumOptions.TALK_COMMERCE));
        talkLocal.setChecked(mc.gameSettings.getOptionOrdinalValue(EnumOptions.TALK_LOCAL));
        global.setChecked(mc.gameSettings.getOptionOrdinalValue(EnumOptions.SEE_GLOBAL) || talkGlobal.isChecked());
        commerce.setChecked(mc.gameSettings.getOptionOrdinalValue(EnumOptions.SEE_COMMERCE) || talkCommerce.isChecked());
        local.setChecked(mc.gameSettings.getOptionOrdinalValue(EnumOptions.SEE_LOCAL) || talkLocal.isChecked());
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
			if(global.isChecked()) mc.thePlayer.sendChatMessage("/ch join g");
			else if(!talkGlobal.isChecked()) mc.thePlayer.sendChatMessage("/ch leave g");
			mc.gameSettings.setOptionValue(EnumOptions.SEE_GLOBAL, global.intValue());
		}else if(checkbox.id == 2){
			commerce.setChecked(!commerce.isChecked());
			if(commerce.isChecked()) mc.thePlayer.sendChatMessage("/ch join c");
			else if(!talkCommerce.isChecked()) mc.thePlayer.sendChatMessage("/ch leave c");
			mc.gameSettings.setOptionValue(EnumOptions.SEE_COMMERCE, commerce.intValue());
		}else if(checkbox.id == 3){
			local.setChecked(!local.isChecked());
			if(local.isChecked()) mc.thePlayer.sendChatMessage("/ch join l");
			else if(!talkLocal.isChecked()) mc.thePlayer.sendChatMessage("/ch leave l");
			mc.gameSettings.setOptionValue(EnumOptions.SEE_LOCAL, local.intValue());
		}else if(checkbox.id == 4){
			talkGlobal.setChecked(true);
			mc.gameSettings.setOptionValue(EnumOptions.TALK_GLOBAL, talkGlobal.intValue());
			talkCommerce.setChecked(false);
			talkLocal.setChecked(false);
			mc.thePlayer.sendChatMessage("/ch g");
		}else if(checkbox.id == 5){
			talkGlobal.setChecked(false);
			talkCommerce.setChecked(true);
			mc.gameSettings.setOptionValue(EnumOptions.TALK_COMMERCE, talkCommerce.intValue());
			mc.thePlayer.sendChatMessage("/ch c");
			talkLocal.setChecked(false);
		}else if(checkbox.id == 6){
			talkGlobal.setChecked(false);
			talkCommerce.setChecked(false);
			talkLocal.setChecked(true);
			mc.gameSettings.setOptionValue(EnumOptions.TALK_LOCAL, talkLocal.intValue());
			mc.thePlayer.sendChatMessage("/ch l");
		}
		
		if(talkGlobal.isChecked() && !global.isChecked()){
			mc.thePlayer.sendChatMessage("/ch join g");
			global.setChecked(true);
		}else
		if(talkCommerce.isChecked() && !commerce.isChecked()){
			mc.thePlayer.sendChatMessage("/ch join c");
			commerce.setChecked(true);
		}else
		if(talkLocal.isChecked() && !local.isChecked()){
			mc.thePlayer.sendChatMessage("/ch join l");
			local.setChecked(true);
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
