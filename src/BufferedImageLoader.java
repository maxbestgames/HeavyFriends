import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class BufferedImageLoader {
	
	private BufferedImage image;
	
	public BufferedImage loadImage(String path) {
		
		try{
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
/*private void LoadImageLevel(BufferedImage Image) {
	int w image.getWidth();
	int h image.getHeight();
	
	System.out.println("width, height" + w ", " + h);
	
	for(int i = 0; i<h; i++){
		for( int j = 0; j<w; j++){
			int pixel = image.getRGB(i, j);
			int red = (pixel >> 16) & 0xff;
			int green = (pixel >> 8 ) & 0xff;
			int blue = (pixel) & 0xff;
			
			if(red == 255 && green == 255 & blue == 255) handler.addObject(new Block(i*32, j*32, ObjectId.Block));
			
		}
	}
}*/