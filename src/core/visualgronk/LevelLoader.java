package core.visualgronk;

import java.awt.image.BufferedImage;

import core.Game;
import core.enums.BlockType;
import core.enums.EntityID;
import core.gameobjects.Block;
import core.gameobjects.Player;
import core.handlers.ObjectHandler;

public class LevelLoader {
	
	BufferedImageLoader loader;
	BufferedImage img;
	
	static Texture tex;
	
	public LevelLoader(ObjectHandler handler, String path) {
		loader = new BufferedImageLoader();
		img = null;		
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

				if(red == 255 && green == 255 & blue == 255) handler.addObject(new Block(i*32, j*32, EntityID.Block, BlockType.Dirt) );
				if(red == 0 && green == 38 & blue == 255) Game.getWorldHandler().addPlayer(new Player(i*32, j*32, EntityID.Player));

			}
		}
	}
	
	public static Texture getInstance() {
		
		return tex;
		
	}
}
