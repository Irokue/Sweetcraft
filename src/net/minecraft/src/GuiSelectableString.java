package net.minecraft.src;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

public class GuiSelectableString extends GuiButton {

	private String text;
	private FontRenderer fontRenderer;
	private boolean selected = false;
	private Minecraft mc;
	
	public GuiSelectableString(int par1, int par2, int par3, int par4,
			int par5, String par6Str, Minecraft mc) {
		super(par1, par2, par3, par4, par5, par6Str);
		this.drawButton = true;
		this.text = par6Str;
		this.mc = mc;
	}
	
	 /**
     * Draws this button to the screen.
     */
	  public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
        	this.height = mc.fontRenderer.FONT_HEIGHT;
        	this.width = mc.fontRenderer.getStringWidth(text);
            boolean flag = par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width && par3 < yPosition + height;
            int textColor = 0xffffff;
            if(flag || selected)
            	textColor = 0x6AA31A;
            else textColor = 0xffffff;
            mc.fontRenderer.drawString(text, xPosition, yPosition, textColor);
    }
	  
	  public int intValue(){
			if(selected) return 1;
			else return 0;
		}

		public boolean isSelected(){
			return selected;
		}
		
		public void setSelected(boolean selected){
			this.selected = selected;
		}
  public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
    {
        return enabled && par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width && par3 < yPosition + height;
    }

}
