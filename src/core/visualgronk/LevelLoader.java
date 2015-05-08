package core.visualgronk;

import java.awt.image.BufferedImage;

import core.Game;
import core.enums.EnemyType;
import core.enums.EntityID;
import core.gameobjects.Player;
import core.gameobjects.blocks.Chain;
import core.gameobjects.blocks.Claybrick;
import core.gameobjects.blocks.Cobblestone;
import core.gameobjects.blocks.Dirt;
import core.gameobjects.blocks.Grass;
import core.gameobjects.blocks.Lava;
import core.gameobjects.blocks.LavaSurface;
import core.gameobjects.blocks.Stonebrick;
import core.gameobjects.blocks.Water;
import core.gameobjects.blocks.WaterSurface;
import core.gameobjects.blocks.Wood;
import core.gameobjects.enemies.BasicEnemy;
import core.gameobjects.enemies.Tornado;
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
				@SuppressWarnings("unused")
				int alpha = (pixel >> 24) & 0xff; // alpha = 0 is transparent
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8 ) & 0xff;
				int blue = (pixel) & 0xff;
				
				//System.out.println(Integer.toHexString(image.getRGB(i, j)));
				
				if(red ==   0 && green ==  38 && blue == 255) Game.getWorldHandler().addPlayer(new Player(i*32, j*32, EntityID.Player));
				if(red == 255 && green ==   0 && blue ==   0) handler.addObject(new BasicEnemy(i*32, j*32, EntityID.Enemy, EnemyType.BasicEnemy));
				if(red == 255 && green ==   1 && blue ==   0) handler.addObject(new Tornado(i*32, j*32, EntityID.Enemy, EnemyType.Tornado));



				
				if(red == 177 && green == 130 && blue ==   4) handler.addObject(new Dirt(i*32, j*32) );
				if(red == 135 && green == 197 && blue == 255) handler.addObject(new WaterSurface(i*32, j*32) );
				if(red ==   0 && green == 132 && blue == 255) handler.addObject(new Water(i*32, j*32) );
				if(red == 174 && green == 174 && blue == 174) handler.addObject(new Cobblestone(i*32, j*32));
				if(red ==   0 && green == 154 && blue ==  22) handler.addObject(new Grass(i*32, j*32));
				if(red == 138 && green == 138 && blue == 138) handler.addObject(new Stonebrick(i*32, j*32));
				if(red == 124 && green ==   0 && blue ==   0) handler.addObject(new Claybrick(i*32, j*32));
				if(red ==  71 && green ==   0 && blue ==  25) handler.addObject(new Wood(i*32, j*32));
				if(red == 225 && green == 172 && blue == 191) handler.addObject(new Chain(i*32, j*32));
				if(red == 213 && green == 170 && blue ==  28) handler.addObject(new LavaSurface(i*32, j*32));
				if(red == 213 && green ==  62 && blue ==  28) handler.addObject(new Lava(i*32, j*32));
				
			}
		}
	}
	
	//public static Texture getInstance() {
		
		//return tex;
		
	//}
}
