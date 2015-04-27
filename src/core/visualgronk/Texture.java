package core.visualgronk;

import java.awt.image.BufferedImage;

public class Texture {
	
	SpriteSheet ss;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			block_sheet = loader.loadImage("/pblocksheet adress");
			player_sheet = loader.loadImage("/file location");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		
	}
	
	private void getTextures() {
		
	}

}
