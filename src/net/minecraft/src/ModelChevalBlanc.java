package net.minecraft.src;

public class ModelChevalBlanc extends ModelBase
{
//fields
 ModelRenderer Shape3;
 ModelRenderer Shape4;
 ModelRenderer head;
 ModelRenderer body;
 ModelRenderer leg1;
 ModelRenderer leg2;
 ModelRenderer leg3;
 ModelRenderer leg4;
 ModelRenderer Shape1;
 ModelRenderer Shape2;

public ModelChevalBlanc()
{
 textureWidth = 128;
 textureHeight = 64;
 
   Shape3 = new ModelRenderer(this, 8, 20);
   Shape3.addBox(0F, 0F, 0F, 1, 2, 2);
   Shape3.setRotationPoint(1F, -6F, -11F);
   Shape3.setTextureSize(128, 64);
   Shape3.mirror = true;
   setRotation(Shape3, 0.5576792F, 0F, 0F);
   Shape4 = new ModelRenderer(this, 77, 49);
   Shape4.addBox(-1.5F, 0F, 0F, 3, 10, 3);
   Shape4.setRotationPoint(0F, 2.2F, 14.33333F);
   Shape4.setTextureSize(128, 64);
   Shape4.mirror = true;
   setRotation(Shape4, 0.3346075F, 0F, 0F);
   head = new ModelRenderer(this, 0, 0);
   head.addBox(-4F, -4F, -9F, 6, 4, 12);
   head.setRotationPoint(1F, -6F, -11F);
   head.setTextureSize(128, 64);
   head.mirror = true;
   setRotation(head, 0.5576792F, 0F, 0F);
   body = new ModelRenderer(this, 83, 0);
   body.addBox(-6F, -10F, -7F, 12, 24, 10);
   body.setRotationPoint(0F, 5F, 2F);
   body.setTextureSize(128, 64);
   body.mirror = true;
   setRotation(body, 1.570796F, 0F, 0F);
   leg1 = new ModelRenderer(this, 32, 48);
   leg1.addBox(-3F, 0F, 0F, 4, 12, 4);
   leg1.setRotationPoint(-3F, 12F, 11.7F);
   leg1.setTextureSize(128, 64);
   leg1.mirror = true;
   setRotation(leg1, 0F, 0F, 0F);
   leg2 = new ModelRenderer(this, 48, 48);
   leg2.addBox(0F, 0F, 0F, 4, 12, 4);
   leg2.setRotationPoint(2F, 12F, 11.5F);
   leg2.setTextureSize(128, 64);
   leg2.mirror = true;
   setRotation(leg2, 0F, 0F, 0F);
   leg3 = new ModelRenderer(this, 17, 48);
   leg3.addBox(-3F, 0F, -3F, 4, 12, 4);
   leg3.setRotationPoint(-3F, 12F, -5F);
   leg3.setTextureSize(128, 64);
   leg3.mirror = true;
   setRotation(leg3, 0F, 0F, 0F);
   leg4 = new ModelRenderer(this, 0, 48);
   leg4.addBox(-1F, 0F, -3F, 4, 12, 4);
   leg4.setRotationPoint(3F, 12F, -5F);
   leg4.setTextureSize(128, 64);
   leg4.mirror = true;
   setRotation(leg4, 0F, 0F, 0F);
   Shape1 = new ModelRenderer(this, 43, 0);
   Shape1.addBox(-2.5F, -5F, -13F, 5, 7, 15);
   Shape1.setRotationPoint(0F, 5F, -6F);
   Shape1.setTextureSize(128, 64);
   Shape1.mirror = true;
   setRotation(Shape1, -0.9529016F, 0F, 0F);
   Shape2 = new ModelRenderer(this, 1, 20);
   Shape2.addBox(0F, 0F, 0F, 1, 2, 2);
   Shape2.setRotationPoint(1F, -6F, -11F);
   Shape2.setTextureSize(128, 64);
   Shape2.mirror = true;
   setRotation(Shape2, 0.5759587F, 0F, 0F);
   body.setRotationPoint(0.0F, 5F, 2.0F);
   leg1.rotationPointX--;
   leg2.rotationPointX++;
   leg1.rotationPointZ += 0.0F;
   leg2.rotationPointZ += 0.0F;
   leg3.rotationPointX--;
   leg4.rotationPointX++;
   leg3.rotationPointZ--;
   leg4.rotationPointZ--;
}

public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
{
// super.render(entity, f, f1, f2, f3, f4, f5);
 setRotationAngles(f, f1, f2, f3, f4, f5);
 Shape3.render(f5);
 Shape4.render(f5);
 head.render(f5);
 body.render(f5);
 leg1.render(f5);
 leg2.render(f5);
 leg3.render(f5);
 leg4.render(f5);
 Shape1.render(f5);
 Shape2.render(f5);
}

private void setRotation(ModelRenderer model, float x, float y, float z)
{
 model.rotateAngleX = x;
 model.rotateAngleY = y;
 model.rotateAngleZ = z;
}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6)
	{
	   // head.rotateAngleX = par5 / (180F / (float)Math.PI);
	    head.rotateAngleY = par4 / (180F / (float)Math.PI);
	    body.rotateAngleX = ((float)Math.PI / 2F);
	    leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.2F * par2;
	    leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.2F * par2;
	    leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.2F * par2;
	    leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.2F * par2;
	}
}

