package net.minecraft.src;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

public class GuiCheckBox extends GuiButton{

	private boolean checked = false;
	private Minecraft mc;
	private String text;
	
	public GuiCheckBox(int par1, int par2, int par3, int par4, int par5,
			String par6Str) {
		super(par1, par2, par3, par4, par5, par6Str);
	}
	
	public GuiCheckBox(int id, int xPosition, int yPosition, String text, boolean checked, Minecraft mc){
		super(id, xPosition, yPosition, 20, 20, "");
		this.mc = mc;
		this.text = text;
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

        int textColor = 0x444444;
        
        if (checked && !flag)
        {
            i += height;
        }
        
        if(flag && !checked){
        	i+= height*2;
        	textColor = 0x222222;
        }
        
        if(flag && checked){
        	i+= height*3;
        	textColor = 0x222222;
        }
        
        drawTexturedModalRect(xPosition, yPosition, 20, i, width, height);
        mc.fontRenderer.drawString(text, xPosition + 25, yPosition + 6 , textColor);
    }
	  
	/**
	 * Renvoie la valeur booléenne en entier
	 * @return valeur booléenne en entier, 1 = true, 0 = false
	 */
	public int intValue(){
		if(checked) return 1;
		else return 0;
	}

	public boolean isChecked(){
		return checked;
	}
	
	public void setChecked(boolean checked){
		this.checked = checked;
	}
	

}
