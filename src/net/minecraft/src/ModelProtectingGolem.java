package net.minecraft.src;

import java.util.Random;

public class ModelProtectingGolem extends ModelBase {

	public ModelRenderer field_48234_a;
	public ModelRenderer field_48232_b;
	public ModelRenderer field_48233_c;
	public ModelRenderer field_48230_d;
	public ModelRenderer bottom;

	public ModelProtectingGolem() {
		this(0.0F);
	}

	public ModelProtectingGolem(float par1) {
		this(par1, -7F);
	}

	public ModelProtectingGolem(float par1, float par2) {
		char c = '\200';
		char c1 = '\200';
		field_48234_a = (new ModelRenderer(this)).setTextureSize(c, c1);
		field_48234_a.setRotationPoint(0.0F, 0.0F + par2, -2F);
		field_48234_a.setTextureOffset(0, 0).addBox(-4F, -20F, -2.5F, 8, 10, 8, par1);
		field_48232_b = (new ModelRenderer(this)).setTextureSize(c, c1);
		field_48232_b.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
		field_48232_b.setTextureOffset(0, 40).addBox(-9F, -9F, -6F, 18, 12, 11, par1);
		field_48232_b.setTextureOffset(0, 70).addBox(-4.5F, 2F, -3F, 9, 5, 6, par1 + 0.5F);
		field_48233_c = (new ModelRenderer(this)).setTextureSize(c, c1);
		field_48233_c.setRotationPoint(2.0F, 0F, 0.0F);
		field_48233_c.setTextureOffset(60, 21).addBox(9F, -7F, -3F, 4, 15, 6, par1);
		field_48230_d = (new ModelRenderer(this)).setTextureSize(c, c1);
		field_48230_d.setRotationPoint(-2F, 0F, 0F);
		field_48230_d.setTextureOffset(60, 58).addBox(-13F, -7F, -3F, 4, 15, 6, par1);
		bottom = (new ModelRenderer(this)).setTextureSize(c, c1);
		bottom.setRotationPoint(0F, 1F, 0F);
		bottom.setTextureOffset(0, 40).addBox(-9F, 10F, -9F, 18, 1, 18, par1 + 0.5F);
	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5,
			float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7);
		field_48234_a.render(par7);
		field_48232_b.render(par7);
		field_48233_c.render(par7);
		field_48230_d.render(par7);
		bottom.render(par7);
	}

	/**
	 * Sets the models various rotation angles.
	 */
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5,
			float par6) {
		float f = MathHelper.sin(onGround * (float)Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - onGround) * (1.0F - onGround)) * (float)Math.PI);
        field_48230_d.rotateAngleX = 0F;
        field_48233_c.rotateAngleX = 0F;
        field_48230_d.rotateAngleX -= f * 1.2F - f1 * 0.4F;
        field_48233_c.rotateAngleX -= f * 1.2F - f1 * 0.4F;
        field_48230_d.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        field_48233_c.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        field_48234_a.rotationPointY =(MathHelper.sin(((float)((System.currentTimeMillis()) % 1000L) / 1000F) * (float)Math.PI * 2.0F));
        field_48230_d.rotationPointY =(MathHelper.sin(((float)((System.currentTimeMillis()) % 1000L) / 1000F) * (float)Math.PI * 2.0F));
        field_48233_c.rotationPointY =(MathHelper.sin(((float)((System.currentTimeMillis()) % 1000L) / 1000F) * (float)Math.PI * 2.0F));
        field_48232_b.rotationPointY =(MathHelper.sin(((float)((System.currentTimeMillis()) % 1000L) / 1000F) * (float)Math.PI * 2.0F));

	}

	public void setLivingAnimations(EntityLiving par1EntityLiving, float par2, float par3,
			float par4) {
		bottom.rotateAngleY = (((float) ((System.currentTimeMillis() / 1L) % 10000L) / 100F));
		field_48233_c.rotationPointY = (((float) ((System.currentTimeMillis() / 1L) % 10000L) / 100F));
		field_48230_d.rotationPointY = (((float) ((System.currentTimeMillis() / 1L) % 10000L) / 100F));
	}

	private float func_48228_a(float par1, float par2) {
		return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
	}

}
