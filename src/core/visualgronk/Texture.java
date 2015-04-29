package core.visualgronk;

import java.awt.image.BufferedImage;

public class Texture {
	
	SpriteSheet ss,bs,ps;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	
	public BufferedImage[] block;
	
	public Texture(String path) {
		
		block = new BufferedImage[1];
		block[0] = null;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			block_sheet = loader.loadImage(path);
			//player_sheet = loader.loadImage("/file location");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		//ps = new SpriteSheet(player_sheet);
		
		fixAlpha();
		
	}
	
	public void getTextures(int row, int col, int width, int height) {
		block[0] = bs.grabImage(row, col, width, height);
	}
	
	public void fixAlpha() {
		
	}

}
