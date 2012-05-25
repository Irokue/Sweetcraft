package net.minecraft.src;

public class GuiCheckBox extends GuiButton{

	private boolean checked = false;
	private boolean enabled = true;
	
	public GuiCheckBox(int par1, int par2, int par3, int par4, int par5,
			String par6Str) {
		super(par1, par2, par3, par4, par5, par6Str);
	}
	
	public GuiCheckBox(int par1, int par2, int par3, boolean checked){
		super(par1, par2, par3, 20, 20, "X");
		this.checked = checked;
	}

	public boolean isChecked(){
		return checked;
	}
	
	public void setChecked(boolean checked){
		enabled = !checked;
		this.checked = checked;
	}
	

}
