package org.freecode.demo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageTool {
    
    //http://www.javalobby.org/articles/ultimate-image/
	
	public static void main(String[] args)
	{
		ImageTool imgTool = new ImageTool();
		String aFileName = "c:\\Temp\\Desert.jpg";
		String aNewFileName = "c:\\Temp\\Desert_r.jpg";
		String aGrayFileName = "c:\\Temp\\Desert_g.jpg";
		
		imgTool.writeImageFile( imgTool.resizeImage(imgTool.readImageFile( aFileName ), 50), aNewFileName);
		// System.out.println(imgTool.getFileExtension("c:/Temp/Desert.jpg"));
		imgTool.writeImageFile(imgTool.changeImageMode(imgTool.readImageFile(aFileName), BufferedImage.TYPE_BYTE_GRAY), aGrayFileName);
	}
	
	public BufferedImage changeImageMode( BufferedImage originalImage, int newType)
	{
	    BufferedImage newImage = null;
	    int originalType = originalImage.getType();
	    if ( originalType == 0 )
	    {
	        originalType = BufferedImage.TYPE_INT_ARGB;
	    }
	    
	    newImage = new BufferedImage(originalImage.getWidth(),  originalImage.getHeight(), newType);
	    Graphics2D g = newImage.createGraphics();
	    g.drawImage(originalImage, 0, 0, null);
	    g.dispose();	    
	    
	    return newImage;
	}

	public BufferedImage resizeImage( BufferedImage originalImage, int resizePercentage )
	{
		BufferedImage resizedImage = null;
		
		int imageType = originalImage.getType();
		if ( imageType == 0 )
		{
			imageType = BufferedImage.TYPE_INT_ARGB;
		}
		
		int newWidth = originalImage.getWidth() * resizePercentage / 100;
		int newHeight = originalImage.getHeight() * resizePercentage / 100;
		
		resizedImage = new BufferedImage(newWidth, newHeight, imageType);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, newWidth, newHeight, null );
		g.dispose();
		
		return resizedImage;
	}
	
	public BufferedImage readImageFile( String fileName )
	{
		BufferedImage img = null;
		try
		{
		  img = ImageIO.read(new File(fileName));
		}
	    catch ( IOException ioe )
	    {
	    	ioe.printStackTrace();
	    }
		
		return img;
	}
	
	public boolean writeImageFile(BufferedImage img, String fileName)
	{
		boolean isSuccessful = false;
		try
		{
		    isSuccessful = ImageIO.write(img, getFileExtension(fileName), new File( fileName ));
		}
		catch ( IOException ioe )
		{
			ioe.printStackTrace();
		}
		return isSuccessful;
	}
	
	private String getFileExtension(String fileName )
	{
		String fileExt = "";
		if ( fileName != null && fileName.length() > 0 )
		{
			fileExt = fileName.substring(fileName.indexOf(".") + 1, fileName.length() );
		}
		
		return fileExt;
	}
}
