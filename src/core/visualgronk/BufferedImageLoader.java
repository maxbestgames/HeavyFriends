package core.visualgronk;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class BufferedImageLoader {
	
	public BufferedImage loadImage(String path) {
		
		BufferedImage image = null;
		
		URL urlToImage = this.getClass().getResource(path);
		File imageFile = new File(urlToImage.getFile());
		
		try{
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.out.println("Failed to get " + imageFile.getAbsolutePath());
			e.printStackTrace();
			System.exit(0);
		}
		
		return image;
	}

}
