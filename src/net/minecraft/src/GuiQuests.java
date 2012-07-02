package net.minecraft.src;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;

public class GuiQuests extends GuiScreen
{
    private Minecraft mc;
    private World world;

    private EntityPlayerSP player;
    
    private int xSize = 225, ySize = 256;
    protected int imgId;
    private GuiSelectableString closeButton;
    private GuiSmallButton abandonButton;
    private int guiLeft, guiTop;
    /**
     * x size of the inventory window in pixels. Defined as float, passed as int
     */
    private float xSize_lo;

    /**
     * y size of the inventory window in pixels. Defined as float, passed as int.
     */
    private float ySize_lo;
    
    public GuiQuests(Minecraft mc, World world, EntityPlayerSP entityplayer)
    {
    	this.player = entityplayer;
        this.mc = mc;
        this.world = world;
    }

	public void initGui()
    {
        Keyboard.enableRepeatEvents(false);
        controlList.clear();
        int displayX = (width - xSize) / 2;
        int displayY = (height - ySize) / 2;
        closeButton = new GuiSelectableString(102, width - displayX - 25, displayY + 20, 20, 20, "X", mc);
        abandonButton = new GuiSmallButton(1, displayX + 35, height - displayY - 50, "Abandonner");
        abandonButton.drawButton = !mc.thePlayer.questsTitles.equalsIgnoreCase("Aucune");
        controlList.add(closeButton);
        controlList.add(abandonButton);
        guiLeft = (width - xSize) / 2;
        guiTop = (height - ySize) / 2;
    }

    public void drawScreen(int par1, int par2, float par3)
    {
    	xSize_lo = par1;
    	ySize_lo = par2;
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        int displayX = (width - xSize) / 2;
        int displayY = (height - ySize) / 2;
        int j = guiLeft;
        int k = guiTop;
        drawBackgroundImage();
        super.drawScreen(par1, par2, par3);
       // fontRenderer.drawString("Liste des quêtes", displayX + fontRenderer.getStringWidth("Liste des quêtes"), displayY +20, 0x935907);
        //System.out.println(player.questsTitles)
        if(player.questsTitles.equalsIgnoreCase("Aucune")){
        //	System.out.println("Oui");
        	fontRenderer.drawString("Vous n'avez aucune quête en cours.", displayX + fontRenderer.getStringWidth("Vous n'avez aucune quête en cours.") / 10, displayY +100, 0xA58F6B);
        }else{
        	fontRenderer.drawString(player.questsTitles, displayX + fontRenderer.getStringWidth(player.questsTitles) + 20, displayY + 20, 0x935907);
        	fontRenderer.drawSplitString(player.questsDesc, displayX + 10, displayY + 50, (int)(xSize - 10), 0x935907);
        	//fontRenderer.drawString(player.questsDesc, displayX + 10, displayY + 50, 0xdddddd);
        }
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
       // GL11.glPushMatrix();
        //drawTexturedModalRect(displayX, displayY, 0, 0, xSize, ySize);
    }

    public void actionPerformed(GuiButton button)
    {
    	if(button.id == 102){
    		player.closeScreen();
    	}
    	if(button.id == 1){
    		player.sendChatMessage("/quest abort");
    		player.closeScreen();
    	}
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    public void updateScreen(){
    	super.updateScreen();
    	abandonButton.drawButton = !mc.thePlayer.questsTitles.equalsIgnoreCase("Aucune");
    }
    
    public void onGuiClosed(){
    	Keyboard.enableRepeatEvents(false);
    }
    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
    	if(par2 == mc.gameSettings.keyBindQuests.keyCode){
    		player.closeScreen();
    	}
    	super.keyTyped(par1, par2);
    }
    
    protected void drawBackgroundImage()
    {
        int displayX = (width - xSize) / 2;
        int displayY = (height - ySize) / 2;
        drawDefaultBackground();
        imgId = mc.renderEngine.getTexture("/gui/quests.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(imgId);
        GL11.glScalef(1F, 1F, 1.0F);
        drawTexturedModalRect(displayX, displayY, 0, 0, xSize, ySize);
        
    }
}
