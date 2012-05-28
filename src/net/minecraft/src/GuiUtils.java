package net.minecraft.src;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;

public class GuiUtils extends GuiScreen
{
    private Minecraft mc;
    private World world;

    private EntityPlayer player;
    
    private int xSize = 256, ySize = 217;
    protected int imgId;
    private GuiSmallButton spawnButton;
    private GuiSmallButton guildButton;
    private GuiTextField playerNameTextField;
    private GuiTextField moneyPayTextField;
    private GuiSmallButton payButton;
    private String playerToPay = "";
    private String moneyToPay = "";
    private boolean pvp = true;
    private int guiLeft, guiTop;
    /**
     * x size of the inventory window in pixels. Defined as float, passed as int
     */
    private float xSize_lo;

    /**
     * y size of the inventory window in pixels. Defined as float, passed as int.
     */
    private float ySize_lo;
    
    public GuiUtils(Minecraft mc, World world, EntityPlayer entityplayer)
    {
    	this.player = entityplayer;
        this.mc = mc;
        this.world = world;
    }

    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        controlList.clear();
        int displayX = (width - xSize) / 2;
        int displayY = (height - ySize) / 2;
        spawnButton = new GuiSmallButton(100, displayX + 133, displayY + 134, 115, 20, "Retourner au Spawn");
        guildButton = new GuiSmallButton(102, displayX + 133, height / 2, 115, 20, "Hall de Guilde");
        payButton = new GuiSmallButton(101, displayX + 75, displayY + 134, 50, 20, "Payer");
        controlList.add(spawnButton);
        controlList.add(guildButton);
        controlList.add(payButton);
        playerNameTextField = new GuiTextField(fontRenderer, displayX + 10, height / 2, 114, 20);
        moneyPayTextField = new GuiTextField(fontRenderer, displayX + 10, height / 2 + 26, 60, 20);
        playerNameTextField.setFocused(false);
        playerNameTextField.func_50032_g(16);
        playerNameTextField.setMaxStringLength(16);
        playerNameTextField.setText("Pseudo");
        moneyPayTextField.setFocused(false);
        moneyPayTextField.func_50032_g(8);
        moneyPayTextField.setMaxStringLength(8);
        moneyPayTextField.setText("Montant");
        ((GuiButton)controlList.get(2)).enabled = false;
        guiLeft = (width - xSize) / 2;
        guiTop = (height - ySize) / 2;
    }

    protected void mouseClicked(int i, int j, int k)
    {
        super.mouseClicked(i, j, k);
        playerNameTextField.mouseClicked(i, j, k);
        moneyPayTextField.mouseClicked(i, j, k);
        if(playerNameTextField.getIsFocused() && playerNameTextField.getText() == "Pseudo")
    		playerNameTextField.setText("");
        if(moneyPayTextField.getIsFocused() && moneyPayTextField.getText() == "Montant")
    		moneyPayTextField.setText("");
    }
    
    public void drawScreen(int par1, int par2, float par3)
    {
    	xSize_lo = par1;
    	ySize_lo = par2;
        int displayX = (width - xSize) / 2;
        int displayY = (height - ySize) / 2;
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        int j = guiLeft;
        int k = guiTop;
        drawBackgroundImage();
        drawTexturedModalRect(displayX, displayY, 0, 0, xSize, ySize);
        playerNameTextField.drawTextBox();
        moneyPayTextField.drawTextBox();
        super.drawScreen(par1, par2, par3);
        fontRenderer.drawString(mc.thePlayer.username, displayX + 200 - mc.thePlayer.username.length(), displayY+ 9, 0x555555);
        fontRenderer.drawString("Caractéristiques", displayX + 14, displayY+ 9, 0x555555);
        String playerMoney = String.valueOf(player.money) + " PO";
        int color = 0xffffff;
        if(player.money < 0) color = 0xff0000;
        String jobLevel = "Lv." + player.jobLevel;
        fontRenderer.drawString(player.guildeRank, displayX + 165, displayY + 57, color);
        fontRenderer.drawString(player.guilde, displayX + 92, displayY + 32, color);
        fontRenderer.drawString(playerMoney, displayX + 92, displayY + 57, color);
        fontRenderer.drawString(player.job + " " + String.valueOf(jobLevel) + " - " +String.valueOf(player.jobExperience)+"/"+String.valueOf(player.jobMaxExperience) + "xp", displayX + 92, displayY + 83, color);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef(displayX + 35, displayY + 90, 10F);
        float f = 30F;
        GL11.glScalef(-f, f, f);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        float f1 = mc.thePlayer.renderYawOffset;
        float f2 = mc.thePlayer.rotationYaw;
        float f3 = mc.thePlayer.rotationPitch;
        float f4 = (float)(j + 51) - xSize_lo;
        float f5 = (float)((k + 75) - 50) - ySize_lo;
        GL11.glRotatef(9F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-(float)Math.atan(f5 / 40F) * 5F, 1.0F, 0.0F, 0.0F);
        mc.thePlayer.renderYawOffset = (float)Math.atan(f4 / 40F) * 20F;
        mc.thePlayer.rotationYaw = (float)Math.atan(f4 / 40F) * 40F;
        mc.thePlayer.rotationPitch = -(float)Math.atan(f5 / 40F) * 20F;
        mc.thePlayer.rotationYawHead = mc.thePlayer.rotationYaw;
        GL11.glTranslatef(0.0F, mc.thePlayer.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180F;
        RenderManager.instance.renderEntityWithPosYaw(mc.thePlayer, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        mc.thePlayer.renderYawOffset = f1;
        mc.thePlayer.rotationYaw = f2;
        mc.thePlayer.rotationPitch = f3;
        GL11.glPopMatrix();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    }

    public void actionPerformed(GuiButton button)
    {
    	if (button.id == 100)
        {
            mc.thePlayer.sendChatMessage("/spawn");
            mc.thePlayer.closeScreen();
        }
    	if(button.id == 102){
    		mc.thePlayer.sendChatMessage("/guilde hall");
    		mc.thePlayer.closeScreen();
    	}
    	if (button.id == 101)
    	{
    		mc.thePlayer.sendChatMessage("/money pay "+playerToPay+" "+moneyToPay);
    		mc.thePlayer.closeScreen();
    	}
    }

    public void updateScreen(){
    	super.updateScreen();
    	 if (!mc.thePlayer.isEntityAlive() || mc.thePlayer.isDead)
         {
             mc.thePlayer.closeScreen();
         }
    	playerNameTextField.updateCursorCounter();
    	moneyPayTextField.updateCursorCounter();
    	((GuiButton) controlList.get(1)).enabled = !player.guilde.contains("Aucune");
    }
    
    public void onGuiClosed(){
    	Keyboard.enableRepeatEvents(false);
    }
    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
    	playerNameTextField.textboxKeyTyped(par1, par2);
    	moneyPayTextField.textboxKeyTyped(par1, par2);
        if (par2 == 1 || par2 == mc.gameSettings.keyBindSweetcraft.keyCode && !playerNameTextField.getIsFocused() || par2 == mc.gameSettings.keyBindInventory.keyCode && !playerNameTextField.getIsFocused())
        {
            mc.thePlayer.closeScreen();
        }
        playerToPay = playerNameTextField.getText();
        moneyToPay = moneyPayTextField.getText();
        ((GuiButton)controlList.get(2)).enabled = !playerNameTextField.getText().contains("Pseudo") && playerNameTextField.getText().length() >= 3 && moneyPayTextField.getText().length() > 0 && !moneyPayTextField.getText().contains("Montant") || !moneyPayTextField.getText().contains("Montant") && playerNameTextField.getText().length() >= 3 && moneyPayTextField.getText().length() > 0 && !playerNameTextField.getText().contains("Pseudo") ;
    }

    protected void drawBackgroundImage()
    {
        int displayX = (width - xSize) / 2;
        int displayY = (height - ySize) / 2;
        drawDefaultBackground();
        imgId = mc.renderEngine.getTexture("/gui/sweetgui.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(imgId);
        drawTexturedModalRect(displayX, displayY, 0, 0, xSize, ySize);
    }
}
