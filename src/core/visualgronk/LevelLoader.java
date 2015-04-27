package core.visualgronk;

import java.awt.image.BufferedImage;

import core.enums.ID;
import core.gameobjects.Block;
import core.handlers.ObjectHandler;

public class LevelLoader {
	
	BufferedImageLoader loader;
	BufferedImage img;
	
	public LevelLoader(ObjectHandler handler, String path) {
		loader = new BufferedImageLoader();
		img = null;		
		img = loader.loadImage(path);
		LoadImageLevel(handler, img);
	}

	public void LoadImageLevel(ObjectHandler handler, BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		System.out.println("width, height" + w + ", " + h);

		for(int i = 0; i<h; i++){
			for( int j = 0; j<w; j++){
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8 ) & 0xff;
				int blue = (pixel) & 0xff;

				if(red == 255 && green == 255 & blue == 255) handler.addObject(new Block(i*32, j*32, ID.Block, handler));

			}
		}
	}
}
