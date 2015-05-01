package core.visualgronk;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class Texture {
	
	SpriteSheet bs;
	private BufferedImage block_sheet = null;
	
	public BufferedImage[][] sprite;
	
	public Texture(String path, int spriteWidth, int spriteHeight) {
		
		BufferedImageLoader loader = new BufferedImageLoader();
<<<<<<< HEAD
		try{
			block_sheet = loader.loadImage("assets/texture/blakcl.png");
			//player_sheet = loader.loadImage("/file location");
		}catch(Exception e) {
			e.printStackTrace();
		}
=======
>>>>>>> enemy-and-ai-start
		
		block_sheet = loader.loadImage(path);
	
		bs = new SpriteSheet(block_sheet);
		
		fixAlpha();
		
		getSprites(spriteWidth, spriteHeight);
	}
	
	public void getSprites(int width, int height) {
		sprite = new BufferedImage[(int) (bs.getWidth()/width)][(int) (bs.getHeight()/height)];
		
		for (int i = 0; i < bs.getHeight()/height; i++) { //Run down image
			for (int j = 0; j < bs.getWidth()/width; j++ ) { //run across image
				sprite[j][i] = bs.grabImage(j, i, width, height); //TODO test to make sure that I, J are correct way around.
			}
		}
	}
	
	public void fixAlpha() {
		
	}
	
	public BufferedImage getSprite(int row, int col) {
		return sprite[row][col];
	}

}
