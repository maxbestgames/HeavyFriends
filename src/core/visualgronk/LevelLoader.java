package core.visualgronk;

import java.awt.image.BufferedImage;

import core.Game;
import core.enums.BlockType;
import core.enums.EntityID;
import core.gameobjects.Block;
import core.gameobjects.Claybrick;
import core.gameobjects.Cobblestone;
import core.gameobjects.Dirt;
import core.gameobjects.Grass;
import core.gameobjects.Player;
import core.gameobjects.Stonebrick;
import core.gameobjects.Water;
import core.gameobjects.WaterSurface;
import core.handlers.ObjectHandler;

public class LevelLoader {
	
	BufferedImageLoader loader;
	BufferedImage img;
	
	//static Texture tex;
	
	public LevelLoader(ObjectHandler handler, String path) {
		loader = new BufferedImageLoader();	
		img = loader.loadImage(path);
		LoadImageLevel(handler, img);
	}

	public void LoadImageLevel(ObjectHandler handler, BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		System.out.println("Loaded Map of Dimension [" + w + ", " + h + "]");

		for(int i = 0; i<w; i++){
			for( int j = 0; j<h; j++){
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8 ) & 0xff;
				int blue = (pixel) & 0xff;
				
				//System.out.println(Integer.toHexString(image.getRGB(i, j)));
				
				if(red == 0 && green == 38 && blue == 255) Game.getWorldHandler().addPlayer(new Player(i*32, j*32, EntityID.Player));
				
				if(red == 255 && green == 255 && blue == 255) handler.addObject(new Dirt(i*32, j*32) );
				if(red ==   1 && green == 255 && blue == 255) handler.addObject(new WaterSurface(i*32, j*32) );
				if(red ==   0 && green == 255 && blue == 255) handler.addObject(new Water(i*32, j*32) );
				if(red == 128 && green == 128 && blue == 128) handler.addObject(new Cobblestone(i*32, j*32));
				if(red ==   0 && green == 255 && blue ==  33) handler.addObject(new Grass(i*32, j*32));
				if(red ==  64 && green ==  64 && blue ==  64) handler.addObject(new Stonebrick(i*32, j*32));
				if(red == 124 && green ==  86 && blue ==  28) handler.addObject(new Claybrick(i*32, j*32));

			}
		}
	}
	
	//public static Texture getInstance() {
		
		//return tex;
		
	//}
}
