package net.minecraft.src;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

public class GuiCheckBox extends GuiButton{

	private boolean checked = false;
	
	public GuiCheckBox(int par1, int par2, int par3, int par4, int par5,
			String par6Str) {
		super(par1, par2, par3, par4, par5, par6Str);
	}
	
	public GuiCheckBox(int id, int xPosition, int yPosition, boolean checked){
		super(id, xPosition, yPosition, 20, 20, "");
		this.checked = checked;
	}

	 /**
     * Draws this button to the screen.
     */
	  public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
        if (!drawButton)
        {
            return;
        }

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, par1Minecraft.renderEngine.getTexture("/gui/gui.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        boolean flag = par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width && par3 < yPosition + height;
        int i = 106;

        if (checked)
        {
            i += height;
        }
        drawTexturedModalRect(xPosition, yPosition, 20, i, width, height);
    }

	public boolean isChecked(){
		return checked;
	}
	
	public void setChecked(boolean checked){
		this.checked = checked;
	}
	

}
