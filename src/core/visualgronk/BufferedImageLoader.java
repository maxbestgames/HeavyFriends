package core.visualgronk;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class BufferedImageLoader {
	
	public BufferedImage loadImage(String path) {
		
		BufferedImage level = null;
		
		URL urlToImage = this.getClass().getResource(path);
		
		try{
			level = ImageIO.read(urlToImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return level;
	}

}
