package net.minecraft.src;

import java.awt.Graphics;
import java.awt.image.*;

public class ImageBufferDownload implements ImageBuffer
{
    private int imageData[];
    private int imageWidth;
    private int imageHeight;

    public ImageBufferDownload()
    {
    }

    public BufferedImage parseUserSkin(BufferedImage bufferedimage)
    {
	  if (bufferedimage == null)
	  {
	    return null;
	  }
	  imageWidth = bufferedimage.getWidth();
	  imageHeight = bufferedimage.getHeight();
	  if(imageHeight != imageWidth/2){
	      return null;
	  }
	  BufferedImage bufferedimage1 = new BufferedImage(imageWidth, imageHeight, 2);
	  Graphics g = bufferedimage1.getGraphics();
	  g.drawImage(bufferedimage, 0, 0, null);
	  g.dispose();
	  imageData = ((DataBufferInt)bufferedimage1.getRaster().getDataBuffer()).getData();
	  func_884_b(0, 0, imageHeight, imageHeight / 2);
	  func_885_a(imageHeight, 0, imageWidth, imageHeight);
	  func_884_b(0, imageHeight / 2, imageWidth, imageHeight);
	  boolean flag = false;
	  for (int i = imageHeight; i < imageWidth; i++)
	  {
	    for (int k = 0; k < (imageHeight / 2); k++)
	    {
	    int i1 = imageData[i + k * 64];
	    if ((i1 >> 24 & 0xff) < 128)
	    {
	    flag = true;
	    }
	    }
	  }
	 
	  if (!flag)
	  {
	    for (int j = 32; j < 64; j++)
	    {
	    for (int l = 0; l < 16; l++)
	    {
	    int j1 = imageData[j + l * 64];
	    boolean flag1;
	    if ((j1 >> 24 & 0xff) < 128)
	    {
	    flag1 = true;
	    }
	    }
	    }
	  }
	  return bufferedimage1;
    }
    
    private void func_885_a(int par1, int par2, int par3, int par4)
    {
        if (func_886_c(par1, par2, par3, par4))
        {
            return;
        }

        for (int i = par1; i < par3; i++)
        {
            for (int j = par2; j < par4; j++)
            {
                imageData[i + j * imageWidth] &= 0xffffff;
            }
        }
    }

    private void func_884_b(int par1, int par2, int par3, int par4)
    {
        for (int i = par1; i < par3; i++)
        {
            for (int j = par2; j < par4; j++)
            {
                imageData[i + j * imageWidth] |= 0xff000000;
            }
        }
    }

    private boolean func_886_c(int par1, int par2, int par3, int par4)
    {
        for (int i = par1; i < par3; i++)
        {
            for (int j = par2; j < par4; j++)
            {
                int k = imageData[i + j * imageWidth];

                if ((k >> 24 & 0xff) < 128)
                {
                    return true;
                }
            }
        }

        return false;
    }
}
